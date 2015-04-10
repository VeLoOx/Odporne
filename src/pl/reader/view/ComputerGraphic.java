package pl.reader.view;

import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class ComputerGraphic extends HBox {
	
	private HBox myBox = new HBox();
	private int sizex, sizey;
	
	private Rectangle backgr;
	
	
	public ComputerGraphic(){
		sizex=sizey=150;
	}

}
