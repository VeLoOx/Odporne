package pl.reader.view;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pl.reader.control.Controler;

public class MainStage {
private Controler controler;
	
	public final int BOXSPACING = 10;
	public final int HW = 650;
	public final int WW = 1200;
	public final int SLAVENUMBER = 4;
	
	private Scene scene;
	private BorderPane borderPane;
	
	private HBox topMenu;
	private VBox console;
	private Button startButton = new Button("START");
	private Button stopButton = new Button("STOP");
	private Button pauseButton = new Button("PAUSE");
	private Button resumeButton = new Button("RESUME");
	private Button animButton = new Button("CLICKANIMATION");
	
	private TextArea consoleArea = new TextArea();
	private Label activeSlave = new Label();
	
	
	private BorderPane centerBorderPane;
	private HBox leftCompVBox, rightCompVBox;
	private HBox topCompHBox, bottomCompHBox;
	private HBox centerMasterCompHBox;
	
	private VBox[] slaveButtonBox = new VBox[SLAVENUMBER];
	private Button[] stopSlaveNoButton = new Button[SLAVENUMBER];
	private Button[] faultSlaveNoButton = new Button[SLAVENUMBER];
	
	private ImageView[] slaveImg = new ImageView[SLAVENUMBER];
	private ImageView masterImg;
	private int OFFSETX=0;
	
	private Group[] slaveImgBook;
	private static boolean[] activeAnimSlave = new boolean[4];
	private static Timeline gameLoop;
	
	private int actualSlaveNo = 0;
	
	private boolean stoped = false;
	
	private URL toURL(String path){
		String dirPath = new File("").getAbsolutePath();

		System.out.println(dirPath+path);
		URL u = null;
		try {
			u = new File(dirPath + path).toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			System.out.println("WYJAAAAAAAAAAAAAAATEk");
			e1.printStackTrace();
		}
		
		return u;
	}
	private int przelotka(){
		return actualSlaveNo;
	}
	
	public void myinit(){
		controler = new Controler(this);
		
		String dir = "";
		dir+="\\GFX\\slave.png";
		URL tmpu = toURL(dir);
		for (int i=0;i<SLAVENUMBER;i++){
			//rec[i] = new Rectangle(50,50,javafx.scene.paint.Color.RED); 
			Image img = new Image(tmpu.toExternalForm(),512,128,false,false);
			if(img==null) System.out.println("NULLLLLLL");
			actualSlaveNo=i;
			slaveImg[i] = new ImageView(img);
			slaveImg[i].setViewport(new Rectangle2D(0, 0, 128, 128));
			slaveButtonBox[i] = new VBox();
			
			stopSlaveNoButton[i] = new Button("S");
			stopSlaveNoButton[i].setMaxSize(10, 10);
			stopSlaveNoButton[i].setOnAction(new EventHandler<ActionEvent>() {
				int my = przelotka();
	            @Override
	            public void handle(ActionEvent event) {
	              controler.pause(my);
	              MainStage.pauseAnim(my);
	            }
	        });
			//stopSlaveNoButton[i].setAlignment(Pos.CENTER_RIGHT);
			faultSlaveNoButton[i] = new Button("F");
			faultSlaveNoButton[i].setMaxSize(10, 10);
			//faultSlaveNoButton[i].setAlignment(Pos.CENTER);
			
			
			((VBox)slaveButtonBox[i]).getChildren().add(stopSlaveNoButton[i]);
			((VBox)slaveButtonBox[i]).getChildren().add(faultSlaveNoButton[i]);
			slaveButtonBox[i].setAlignment(Pos.CENTER);
		}
		
		dir = "";
		dir+="\\GFX\\master.png";
		tmpu = toURL(dir);
		
		Image mimg = new Image(tmpu.toExternalForm(),512,128,false,false);
		masterImg = new ImageView(mimg);
		masterImg.setViewport(new Rectangle2D(0, 0, 128, 128));
		
		borderPane = new BorderPane();
		
		centerBorderPane = new BorderPane();
		
		leftCompVBox = new HBox();
		leftCompVBox.setSpacing(BOXSPACING);
		leftCompVBox.setPadding(new Insets(15, 12, 15, 12));
		leftCompVBox.setStyle("-fx-background-color: #AAFFFF;");
		leftCompVBox.setAlignment(Pos.CENTER);
		leftCompVBox.getChildren().add(slaveImg[0]);
		leftCompVBox.getChildren().add(slaveButtonBox[0]);
		
		 
		
		rightCompVBox = new HBox();
		rightCompVBox.setSpacing(BOXSPACING);
		rightCompVBox.setPadding(new Insets(15, 12, 15, 12));
		rightCompVBox.setStyle("-fx-background-color: #AAFFFF;");
		rightCompVBox.setAlignment(Pos.CENTER);
		rightCompVBox.getChildren().add(slaveImg[2]);
		rightCompVBox.getChildren().add(slaveButtonBox[2]);
		
		topCompHBox = new HBox();
		topCompHBox.setSpacing(BOXSPACING);
		topCompHBox.setPadding(new Insets(15, 12, 15, 12));
		topCompHBox.setStyle("-fx-background-color: #AAFFFF;");
		topCompHBox.setAlignment(Pos.CENTER);
		topCompHBox.getChildren().add(slaveImg[1]);
		topCompHBox.getChildren().add(slaveButtonBox[1]);
		
		bottomCompHBox = new HBox();
		bottomCompHBox.setSpacing(BOXSPACING);
		bottomCompHBox.setPadding(new Insets(15, 12, 15, 12));
		bottomCompHBox.setStyle("-fx-background-color: #AAFFFF;");
		bottomCompHBox.setAlignment(Pos.CENTER);
		bottomCompHBox.getChildren().add(slaveImg[3]);
		bottomCompHBox.getChildren().add(slaveButtonBox[3]);
		
		Rectangle masterRec =  new Rectangle(100,100,javafx.scene.paint.Color.GREEN); 
		centerMasterCompHBox = new HBox();
		centerMasterCompHBox.setAlignment(Pos.CENTER);
		centerMasterCompHBox.getChildren().add(masterImg);
		
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
		topMenu.getChildren().add(stopButton);
		topMenu.getChildren().add(pauseButton);
		topMenu.getChildren().add(resumeButton);
		topMenu.getChildren().add(animButton);
		topMenu.getChildren().add(activeSlave);
		stopButton.setDisable(true);
		pauseButton.setDisable(true);
		resumeButton.setDisable(true);
		 startButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	stoped=false;
	               controler.init();
	               controler.start();
	               startButton.setDisable(true);
	               stopButton.setDisable(false);
	               pauseButton.setDisable(false);
	            }
	        });
		 stopButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	stoped=true;
	               controler.stopAll();
	               startButton.setDisable(false);
	               stopButton.setDisable(true);
	               pauseButton.setDisable(true);
	               resumeButton.setDefaultButton(true);
	            }
	        });
		 
		 pauseButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	stoped=true;
	               controler.pauseAll();
	               pauseButton.setDisable(true);
	               resumeButton.setDisable(false);
	            }
	        });
		 resumeButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	stoped=false;
	               controler.resumeAll();
	               pauseButton.setDisable(false);
	            }
	        });
		 animButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	              shuffleAnimation();
	            }
	        });
		
		
		console = new VBox();
		console.setSpacing(20);
		console.setPadding(new Insets(15, 12, 15, 12));
		consoleArea.setText("Console Text");
		consoleArea.setPrefRowCount(100);
		console.getChildren().add(consoleArea);
		
		borderPane.setTop(topMenu);
		borderPane.setLeft(console);
		
		scene = new Scene(borderPane,WW,HW);
		buildAndSetGameLoop();
		gameLoop.play();
		
	}
	
	public void shuffleAnimation(){
		if(controler.checkAnimRound()){
		OFFSETX+=128;
		if(OFFSETX>384)OFFSETX=0;
		
		masterImg.setViewport(new Rectangle2D(OFFSETX, 0, 128, 128));
		for(int i=0;i<SLAVENUMBER;i++){
			if(activeAnimSlave[i]) continue;
			slaveImg[i].setViewport(new Rectangle2D(OFFSETX, 0, 128, 128));
		}
		}
		
	}
	
	public void consoleActualisation(){
if(stoped) return;
		consoleArea.setText(controler.getOUT());
		consoleArea.setScrollTop(Double.MAX_VALUE);
		activeSlave.setText(Integer.toString(controler.getActiveSlave()));
	}
	
	protected final void buildAndSetGameLoop() {

		final Duration oneFrameAmt = Duration
				.millis(1000 / 15);

		final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,

		new EventHandler() {

			@Override
			public void handle(javafx.event.Event event) {
				
				shuffleAnimation();
				consoleActualisation();
				
			}

		}); // oneFrame
			// sets the game world's game loop (Timeline)

		setGameLoop(TimelineBuilder.create().cycleCount(Animation.INDEFINITE)
				.keyFrames(oneFrame).build());

	}

	private void setGameLoop(Timeline build) {
		// TODO Auto-generated method stub
		gameLoop = build;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public ImageView[] getSlaveImg() {
		return slaveImg;
	}

	public void setSlaveImg(ImageView[] slaveImg) {
		this.slaveImg = slaveImg;
	}

	public ImageView getMasterImg() {
		return masterImg;
	}

	public void setMasterImg(ImageView masterImg) {
		this.masterImg = masterImg;
	}
	
	private static void pauseAnim(int i){
		activeAnimSlave[i]=true;
	}
	
	
}
