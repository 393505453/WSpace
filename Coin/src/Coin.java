import java.util.HashMap;
import java.util.Scanner;

public class Coin {

	private HashMap<Integer, String>coinnames = new HashMap<Integer,String>();
	//nteger «Key String «value
	public Coin(){
		coinnames.put(1, "penny");
		coinnames.put(10, "penny");
		coinnames.put(25, "penny");
		coinnames.put(50, "penny");
		
	}
	public String getName(int amount){
		if(coinnames.containsKey(amount)){
			return coinnames.get(amount);}
		else
			return "false";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		int amount = in.nextInt();
		Coin coin = new Coin();
		String name = coin.getName(amount);
		System.out.println(name);
	}

}
