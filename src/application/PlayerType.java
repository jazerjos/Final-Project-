package application;


public abstract class PlayerType {
	private String name;
	private String pieceType;
	 
	PlayerType(String name,String piece){
		
		this.name=name;
		this.pieceType=piece;
		
	}
	
	PlayerType(){
	}
	
	public abstract String getName(String name);
	
	public abstract String getPiece(String piece);
	
	
public static class humanPlayer extends PlayerType{

	
		humanPlayer(String name, String pieceType) {
			super(name,pieceType);
		}

		public humanPlayer() {
		
		};


		@Override
		public String getPiece(String piece) {
			
			return piece;
		}

		@Override
		public String getName(String name) {
			
			return name;
		}
		
		
	}

public static class randomPlayer extends PlayerType{
	

	randomPlayer(String name, String pieceType) {
		super(name, pieceType);	
	}

	public String getName(String name) {
		
		return name;
	}
	
	public String getPiece(String piece) {
		humanPlayer human=new humanPlayer();
		if(human.getPiece(piece).equals("X")) return "O";
		else return "X";
	}
	
}

	 


}
