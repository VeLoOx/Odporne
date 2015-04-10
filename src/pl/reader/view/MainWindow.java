package pl.reader.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainWindow extends Application{
	
	public final int BOXSPACING = 10;
	public final int HW = 650;
	public final int WW = 1200;
	public final int SLAVENUMBER = 4;
	
	private Scene scene;
	private BorderPane borderPane;
	
	private HBox topMenu;
	private VBox console;
	private Button startButton = new Button("START");
	private TextArea consoleArea = new TextArea();
	
	
	private BorderPane centerBorderPane;
	private VBox leftCompVBox, rightCompVBox;
	private HBox topCompHBox, bottomCompHBox;
	private HBox centerMasterCompHBox;
	
	
	public MainWindow(){
	
	}
	
	public void myinit(){
		
		Rectangle rec[] = new Rectangle[SLAVENUMBER];
		for (int i=0;i<SLAVENUMBER;i++){
			rec[i] = new Rectangle(50,50,javafx.scene.paint.Color.RED); 
		}
		
		borderPane = new BorderPane();
		
		centerBorderPane = new BorderPane();
		
		leftCompVBox = new VBox();
		leftCompVBox.setSpacing(BOXSPACING);
		leftCompVBox.setPadding(new Insets(15, 12, 15, 12));
		leftCompVBox.setStyle("-fx-background-color: #AAFFFF;");
		leftCompVBox.setAlignment(Pos.CENTER);
		leftCompVBox.getChildren().add(rec[0]);
		
		 
		
		rightCompVBox = new VBox();
		rightCompVBox.setSpacing(BOXSPACING);
		rightCompVBox.setPadding(new Insets(15, 12, 15, 12));
		rightCompVBox.setStyle("-fx-background-color: #AAFFFF;");
		rightCompVBox.setAlignment(Pos.CENTER);
		rightCompVBox.getChildren().add(rec[2]);
		
		topCompHBox = new HBox();
		topCompHBox.setSpacing(BOXSPACING);
		topCompHBox.setPadding(new Insets(15, 12, 15, 12));
		topCompHBox.setStyle("-fx-background-color: #AAFFFF;");
		topCompHBox.setAlignment(Pos.CENTER);
		topCompHBox.getChildren().add(rec[1]);
		
		bottomCompHBox = new HBox();
		bottomCompHBox.setSpacing(BOXSPACING);
		bottomCompHBox.setPadding(new Insets(15, 12, 15, 12));
		bottomCompHBox.setStyle("-fx-background-color: #AAFFFF;");
		bottomCompHBox.setAlignment(Pos.CENTER);
		bottomCompHBox.getChildren().add(rec[3]);
		
		Rectangle masterRec =  new Rectangle(100,100,javafx.scene.paint.Color.GREEN); 
		centerMasterCompHBox = new HBox();
		centerMasterCompHBox.setAlignment(Pos.CENTER);
		centerMasterCompHBox.getChildren().add(masterRec);
		
		centerBorderPane.setLeft(leftCompVBox);
		centerBorderPane.setRight(rightCompVBox);
		centerBorderPane.setTop(topCompHBox);
		centerBorderPane.setBottom(bottomCompHBox);
		centerBorderPane.setCenter(centerMasterCompHBox);
		
		
		
		borderPane.setCenter(centerBorderPane);
		borderPane.setPadding(new Insets(15, 12, 15, 12));
		
		topMenu = new HBox();
		topMenu.setSpacing(20);
		topMenu.setPadding(new Insets(15, 12, 15, 12));
		topMenu.getChildren().add(startButton);
		
		console = new VBox();
		console.setSpacing(20);
		console.setPadding(new Insets(15, 12, 15, 12));
		consoleArea.setText("Console Text");
		console.getChildren().add(consoleArea);
		
		borderPane.setTop(topMenu);
		borderPane.setLeft(console);
		
		scene = new Scene(borderPane,WW,HW);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("SONB");
		this.myinit();
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }
	

}
