package castle;

import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits=new HashMap<String,Room>();


    public Room(String description) 
    {
        this.description = description;
    }

    //N：北部的缩写北
//    E：缩写为东，东
//    S：为south的缩写，南
//    W：西缩写部
    public void setExit(String dir,Room room){
    	exits.put(dir, room);
    	
    }

    @Override
    public String toString()
    {
        return description;
    }
    
    public String getExitDesc(){
    	StringBuffer sb= new StringBuffer();
    	for(String dir : exits.keySet()){
    		sb.append(dir);
    		sb.append(' ');
    	}
    	return sb.toString();
    	
    }
    
    public Room getExit(String direction){
    	return exits.get(direction);
    	
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((exits == null) ? 0 : exits.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (exits == null) {
			if (other.exits != null)
				return false;
		} else if (!exits.equals(other.exits))
			return false;
		return true;
	}
    
}
