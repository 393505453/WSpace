package castle;

import java.util.HashMap;
import java.util.Scanner;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class Game {
    private Room currentRoom;
    private HashMap<String,Handler> handlers = new HashMap<String,Handler>();
    int step=20;
        
    public Game() 
    {
//    	handlers.put("go", HandlerGo());
    	handlers.put("bye", new HandlerBye(this));
    	handlers.put("help", new HandlerHelp(this));
    	handlers.put("go", new HandlerGo(this));
        createRooms();
    }

    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom;
      
        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");
        
        //	初始化房间的出口
        outside.setExit("east" , lobby);
        outside.setExit("south" , study);
        outside.setExit("west" , pub);
        lobby.setExit("west" ,outside);
        pub.setExit("east" , outside);
        study.setExit("nouth" , outside);
        study.setExit("east" , bedroom);
        bedroom.setExit("west", study);
        lobby.setExit("up", pub);
        pub.setExit("down",lobby);

        currentRoom = outside;  //	从城堡门外开始
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到死亡挑战,我的朋友！");
        System.out.println("(你发现你的脚上绑着一颗炸弹,远处似乎有声音传来.)");
        System.out.println("好了,如果你想活着出去,就不要走错路.");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        showPrompt();
    }

    // 以下为用户命令


    public void goRoom(String direction) 
    {
       Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("那里没有路！");
        }
        else {
            currentRoom = nextRoom;
            showPrompt();
            step();
        }
    }
	

    public void showPrompt(){
        System.out.print("现在你在" + currentRoom);
        System.out.print(",现在的路有：");
        System.out.print(currentRoom.getExitDesc());
        System.out.println();
    	
    }
    
    public void step(){
		System.out.print(" 你还有"+step+"次机会.");
		step--;
		if(step==10){
			System.out.print("远处传来了讥笑声,似乎在嘲讽着你那将会消逝的生命.");
		}
    }
    public void play(){
    	Scanner in = new Scanner(System.in);
        while ( true ) {
        	String line = in.nextLine();
    		String[] words = line.split(" ");
    		Handler handler = handlers.get(words[0]);
    		String value="";
    		if(words.length>1)
    			value = words[1];
    		if (handler !=null){
    			handler.doCmd(value);
    			if(handler.isBye())
    				break;
    		}
    		
    }
    }
	public static void main(String[] args) {
		Game game = new Game();
		game.printWelcome();
		game.play();


        
        System.out.println("感谢您的光临。再见！");
	}

}
