package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BlockCell{

	public final static int blockWidth = 26;
	public final static int blockHeight = 26;
	public final static int blockWidthPadding = 1;
	public final static int blockHeightPadding = 1;

    Shape rect;

	//(0,0)からの相対位置
	protected int dx, dy;

	public void setColor(Color c){
		rect.setFill(c);
	}

    public BlockCell(int dx, int dy){
	    	this.dx = dx;
	    	this.dy = dy;
	    	rect = new Rectangle();
	    	rect.setScaleX(blockWidth);
	    	rect.setScaleY(blockHeight);
    }




}
