package pl.reader.view;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import pl.reader.control.Controler;

public class MainWindow extends Application{
	
	MainStage ms = new MainStage();

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("SONB");
		ms.myinit();
		primaryStage.setScene(ms.getScene());
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }
	

}
