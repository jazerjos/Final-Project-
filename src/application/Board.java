package application;

import java.util.Random;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class Board {
	
		private int[][] board= new int[3][3];
		private int row,col;
		private String pieceType=" ";
		Random rand=new Random();
		
		public boolean isCellEmpty(int row,int col){
			
				if(board[row][col]==0) return true;
							
			return false;
		}
		
		public boolean placePieceRandomly(GridPane Pane,Ellipse Circle,Line Line1, Line Line2,String CPU){
	      
			 boolean wasPiecePlaced = false;
			 row=rand.nextInt(3);
			 col=rand.nextInt(3);
			 
			 pieceType=CPU;
			 while(!wasPiecePlaced && !isBoardFull()){	 
				
				 if(isCellEmpty(row, col)){
						
					if(placeToplay(row, col, CPU)&&!isCellEmpty(row, col)) {
						
					 placeToplay(row,col,pieceType);
					 
					 if(CPU.equals("O")){
					
						 Pane.add(Circle, row, col);
						 return true;
						 
					 }else if(CPU.equals("X")) {
					
						 Pane.add(Line1, row, col);
						 Pane.add(Line2, row, col);
						 return true;	 
					 }
					  				 	
			 
			 }
			  	  
		 }else {
			int i=3;
			row=rand.nextInt(i);
			col=rand.nextInt(i);
			
			 i--;
			 if(i==0)i=3;
			}
	
	}
			return false;
			 
	}
		public boolean placeToplay(int row,int col,String pieceType) { 
				
			if(pieceType.equals("X")&& board[row][col]==0) { 
				board [row][col]=1;
				return true;  
			}
			else if(pieceType.equals("O")&& board[row][col]==0) {
				board [row][col]=2;
				return true; 
			}
			else  return false;
				
}
			
		public boolean isBoardFull() {
			
			for(int row=0;row<3;row++) {
				
				for(int col=0;col<3;col++) {
					
						if(board[row][col]==0) {
							
							if(board[row][col]==0)return false;
						}
				}
			}
				return true;			
		}
		
		public int[][] getBoard(){
	        
			return board;
	    }
		
		public int getPosition(int row,int col){
			
			return board[row][col];
		}
						
}
