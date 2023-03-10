package application;


public class Referee{

		public enum GameState{
			
			IN_PROGRESS,X_WON,O_WON;
		}
		
		public static GameState calculateGameState(int [][]board,int row, int col ) {

			 int gamePiece = board[row][col];
			
			if(gamePiece==0) return GameState.IN_PROGRESS;
			

			 // DOWN DIRECTION
			 boolean isCellOutOfBound =(row-1<0);
			if(!isCellOutOfBound && board[row-1][col] == gamePiece  ) {
				
				 isCellOutOfBound =(row-2<0);
				 
				 if(!isCellOutOfBound && board[row-2][col] == gamePiece)
				
					 return gamePiece==1?GameState.X_WON:GameState.O_WON;
			}
			
			 // RIGHT UP DIRECTION
			
			isCellOutOfBound =(row-1<0 ||col+1>board[0].length-1);
			if(!isCellOutOfBound && (board[row-1][col+1]==gamePiece)){
				
				isCellOutOfBound =(row-2<0 ||col+2>board[0].length-1);
				
				if(!isCellOutOfBound && (board[row-2][col+2]==gamePiece)) {
					
					return gamePiece==1?GameState.X_WON:GameState.O_WON;
				}
			}
			
			
			// RIGHT DIRECTION
			isCellOutOfBound =(col+1> board.length-1);
			if(!isCellOutOfBound && board[row][col+1] == gamePiece ) {
				
				 isCellOutOfBound =(col+2> board[0].length-1);
				 
				 if(!isCellOutOfBound && board[row][col+2] == gamePiece)
				
					 return gamePiece==1?GameState.X_WON:GameState.O_WON;
			}
			
			//RIGHT DOWN DIRECTION
			isCellOutOfBound =row+1 > board.length-1 || col+1>board[0].length-1;
			if(!isCellOutOfBound && board[row+1][col+1] == gamePiece  ) {
						
			isCellOutOfBound =row+2> board.length-1|| col+2>board[0].length-1;
						 
			if(!isCellOutOfBound && board[row+2][col+2] == gamePiece)
						
			 return gamePiece==1?GameState.X_WON:GameState.O_WON;
					}
					
			 // DOWN DIRECTION
			isCellOutOfBound = row+1>board.length-1;
	        if ( !isCellOutOfBound && (board[row+1][col] == gamePiece)){
	        	isCellOutOfBound = row+2>board.length-1;
	            if(!isCellOutOfBound && (board[row+2][col] == gamePiece)){
	                return gamePiece==1?GameState.X_WON:GameState.O_WON;
	            }
	        }
			
	     // LEFT DOWN DIRECTION
	        isCellOutOfBound =row+1>board.length-1 || (col-1<0);
	        if ( !isCellOutOfBound && (board[row+1][col] == gamePiece)){
	        	isCellOutOfBound =row+2>board.length-1 || (col-2<0);
	            if(!isCellOutOfBound && (board[row+2][col-2] == gamePiece)){
	                return gamePiece==1?GameState.X_WON:GameState.O_WON;
	            }
	        }
	       
	        // LEFT DIRECTION
	        isCellOutOfBound = (col-1<0);
	        if ( !isCellOutOfBound && (board[row][col-1] == gamePiece)){
	        	isCellOutOfBound =(col-2<0);
	            if(!isCellOutOfBound && (board[row][col-2] == gamePiece)){
	                return gamePiece==1?GameState.X_WON:GameState.O_WON;
	            }
	        }

	        // LEFT UP DIRECTION
	        isCellOutOfBound = (row-1<0) || (col-1<0);
	        if ( !isCellOutOfBound && (board[row-1][col-1] == gamePiece)){
	        	isCellOutOfBound = (row-2<0) || (col-2<0);
	            if(!isCellOutOfBound && (board[row-2][col-2] == gamePiece)){
	                return gamePiece==1?GameState.X_WON:GameState.O_WON;
	            }
	        }
	        
			return GameState.IN_PROGRESS;
		}
			
		public static GameState resolve(int[][]board){
			
			for(int row=0;row<3;row++) {
				for(int col=0;col<3; col++) {
			
				GameState gameState = calculateGameState(board,row,col);	
					
				if(gameState!=GameState.IN_PROGRESS) {
					
					return gameState;
				}
					
				}		
			}
			
			return GameState.IN_PROGRESS;
		}
		
		
		
	}
	

