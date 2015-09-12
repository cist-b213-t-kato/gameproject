package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BlockCell{

	//(0,0)からの相対位置
	protected int dx, dy;

	enum BlockCellColor{
		RED, NONE, BLUE
	};

	public void setColor(BlockCellColor color){
		switch (color){
    	case RED:
        	rect.setFill(Color.hsb(0, 0.7, 0.8));
        	break;
    	case BLUE:
    		rect.setFill(Color.hsb(200, 0.7, 0.8));
        	break;
    	case NONE:
    		rect.setFill(Color.hsb(0, 0, 0.95));
    		break;
    	}
	}

	public void setColor(Color c){
		rect.setFill(c);
	}

    Shape rect;
    public BlockCell(int dx, int dy){
    	this.dx = dx;
    	this.dy = dy;
    	rect = new Rectangle();
    	rect.setScaleX(30);
    	rect.setScaleY(30);
    	setColor(BlockCellColor.NONE);
    }




}
