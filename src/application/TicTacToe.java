package application;
import java.util.Random;

import application.PlayerType.humanPlayer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;


public class TicTacToe extends Application {
	
	private Scene scene1,scene2,scene3;
	private boardInPane[][]board=new boardInPane[3][3];
	private Label statusMsg = new Label("X MUST PLAY");
	private Label WrongPiece = new Label(" ");
	private Label IsOver = new Label("");
	private Label name=new Label("Hello");
	Label label=new Label("Tic Tac Toe");
	public String currentPlayer=" ";
	GridPane pane = new GridPane();
	private TextField Name = new TextField();
	private TextField piece = new TextField();
	humanPlayer PlayerH=new humanPlayer();
	Board booardInPane=new Board();
	private int row=0,col=0;
	private ChoiceBox<String> Choices = new ChoiceBox();
	private String s="Solo Player";
	private VBox root=new VBox(10);
	BorderPane boderpane = new BorderPane();
	Button button1=new Button("START GAME");
	Button button2=new Button("Exit");
	Button buttonContinue=new Button("continue");
	GridPane paneInfo=new GridPane();
	
	public void start(Stage primaryStage) {
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				board[i][j]=new boardInPane();
				pane.add(board[i][j], i, j);
				
			}
		}
			
		scene1=createScene1();
		// Create a scene and place it in the stage
		
		boderpane.setCenter(pane);
		boderpane.setTop(statusMsg);
		statusMsg.setFont(new Font("Verdana",16));
		boderpane.setBottom(name);
		
		//-----------------------
			
		// Create a scene and place it in the stage
		scene2=createScene2();
		boderpane.setCenter(pane);
		
		//-------------------------------
		
		scene3 = new Scene(boderpane, 500, 500);
		primaryStage.setTitle("Tic Tac Toe");
		primaryStage.setScene(scene1);
		primaryStage.show();
		
	}	
		
	//scene 1
	private Scene createScene1() {
		
		root.setAlignment(Pos.CENTER);
		Label label=new Label("Tic Tac Toe");
		Button button2=new Button("Exit");
		button1.setOnAction(e->{
			setScene(scene2,e);
	
			
		});
		
		button2.setOnAction(e->{
			Platform.exit();
					
				});

		root.getChildren().addAll(label,button1,button2);
		Scene scene=new Scene(root,400,300);

		
		return scene;
	}
	
//scene 2
	private Scene createScene2() {
			
	paneInfo.setAlignment(Pos.CENTER);
	Choices.getItems().addAll("Solo Player","vs CPU");
	buttonContinue.setOnAction(e->{
			
		if(!piece.getText().toUpperCase().equals("X")&&!piece.getText().toUpperCase().equals("O")) {
			
			try {	
				int i=1/0;		
			}catch(Exception e1){		
				WrongPiece.setText("Please enter X/O");
			}
			}else {
		     currentPlayer=piece.getText().trim().toUpperCase();		
			 setScene(scene3,e);		 
			}
	
	               
	});
	piece.setMaxWidth(25);
	paneInfo.setPadding(new Insets(10,20,10,10));
	paneInfo.setHgap(5);
	paneInfo.setVgap(5);
	paneInfo.add(new Label("Enter a name"), 1, 0);
	paneInfo.add(Name, 2,0);
	paneInfo.add(new Label("Enter a piece O/X"), 1,1);
	paneInfo.add(piece, 2, 1);
	paneInfo.add(WrongPiece,0,1);
	Choices.setValue(s);
	paneInfo.add(Choices,3,1);
	//--------------------------------------------------
	
	paneInfo.add(buttonContinue, 1, 2);
	
	paneInfo.add(button2, 2, 2);
	button2.setOnAction(e->{
		Platform.exit();
				
			});
	
	Scene scene=new Scene(paneInfo,500,300);

	return scene;
}

//this provide  the scene
	public void setScene(Scene scene,ActionEvent e){
	Node node=(Node)e.getSource();
	Stage stage=(Stage)node.getScene().getWindow();
	stage.setScene(scene);
	
}
	
	public void setScene2(Scene scene,MouseEvent e){
		Node node=(Node)e.getSource();
		Stage stage=(Stage)node.getScene().getWindow();
		stage.setScene(scene);
		
	}
	
	
//scene 3
	class boardInPane extends Pane{

	
	private String player =" ";
	private Line line1,line2;
	private Ellipse ellipse;
	private  boolean stillplaying;
	
	public boardInPane(){
		
		drawEllipse();
		name.setText(Name.getText());
		setStyle("-fx-border-color : black" );
		setPrefSize(300, 300);
		setOnMouseClicked(e->handleClick(e));;
		
	}

	public static void main(String[] args) {
		launch(args);
		}
	
	//hadling the event 
	private  void  handleClick(MouseEvent e) {
			
		String SoloOrCpu = Choices.getValue();
		
			name.setText("Player Name "+Name.getText());
			
			if(player.equals(" ") && (currentPlayer.equals("X")||currentPlayer.equals("O"))) {
				//getting the column and row of the array
				
				Node source = (Node)e.getSource() ;
				Integer colIndex = GridPane.getColumnIndex(source);
			    Integer rowIndex = GridPane.getRowIndex(source);
			    row=rowIndex.intValue();
			    col=colIndex.intValue();
			  
			    	setPlayer(currentPlayer);    
					booardInPane.placeToplay(row, col, currentPlayer);   
			   
			stillplaying=Referee.resolve(booardInPane.getBoard())==Referee.GameState.IN_PROGRESS;
			
			  if(!stillplaying){  //if game is over
					
					statusMsg.setText(currentPlayer + " won" );
					 currentPlayer=" ";
				
				}
				else if(booardInPane.isBoardFull()) {   //if the board is full is over
					
					statusMsg.setText("draw" );
					currentPlayer = " ";
				
					
				}
				else {   //keep playing
					
						currentPlayer = (currentPlayer.equals("X"))? "O":"X";
						player=" ";
						statusMsg.setText(currentPlayer+" Must play");
						
				
				}
			if(SoloOrCpu.equals("vs CPU")) handleclick2();
			 
			}

	}
	
	public  void setPlayer(String c) {
		
		 player = c;
		  
		if(player.equals("X")) {
			
			if(booardInPane.getPosition(row,col)==0) {
				
				drawLines();                        //drawing the x in the pane
				getChildren().addAll(line1,line2);
				
			}
			
		}
		if(player.equals("O")) {       //drawing the o in the pane
			if(booardInPane.getPosition(row,col)==0) {
				
				getChildren().add(ellipse);
					
			}
			
			}
	}
	
	public void drawEllipse(){
		//setting up the o
		ellipse=new Ellipse(this.getWidth()/2,this.getHeight()/2,this.getWidth()/2-10,
		this.getHeight()/2-10);
		ellipse.centerXProperty().bind(this.widthProperty().divide(2));
		ellipse.centerYProperty().bind(this.heightProperty().divide(2));
		ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
		ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
		 	
	}
	
	public void drawLines(){
		//setting up the x
		line1=new Line(10,10,this.getWidth()-10,this.getHeight()-10);
		line1.endXProperty().bind(this.widthProperty().subtract(10));
		line1.endXProperty().bind(this.heightProperty().subtract(10));
		
		line2=new Line(10,this.getHeight()-10,this.getWidth()-10,10);
		line2.endXProperty().bind(this.widthProperty().subtract(10));
		line2.endXProperty().bind(this.heightProperty().subtract(10));
	}
	
	public String currentPlayer(){
		
		return player;
		
	}
	 
	public boolean handleclick2() {
		
		
		if(player.equals(" ") && (currentPlayer.equals("X")||currentPlayer.equals("O"))) {
			
			 drawLines();
			 
			 booardInPane.placePieceRandomly(pane,ellipse, line1,line2,currentPlayer);
			
		stillplaying=Referee.resolve(booardInPane.getBoard())==Referee.GameState.IN_PROGRESS;
		
		  if(!stillplaying){
				
				statusMsg.setText(currentPlayer + " won" );
				currentPlayer=" ";
				
			}
			else if(booardInPane.isBoardFull()) {
				
				statusMsg.setText("draw" );
				currentPlayer = " ";

				
			}
			else {
				currentPlayer = (currentPlayer.equals("X"))? "O":"X";
				statusMsg.setText(currentPlayer+" Must play");
				
			}
			
		}
			
		return false;	
	}
	
	}

	public static void main(String[] args) {
		launch(args);

	}
		
}
