package tetris;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Tetrimino {

	public int x, y;	//軸ブロックの絶対位置
	BlockCell[] blocks = new BlockCell[4];

	public Tetrimino(BlockCell b1, BlockCell b2, BlockCell b3, Color c){
		blocks[0] = new BlockCell(0, 0); //軸ブロック
		blocks[1] = b1;
		blocks[2] = b2;
		blocks[3] = b3;
		blocks[0].setColor(c);
		blocks[1].setColor(c);
		blocks[2].setColor(c);
		blocks[3].setColor(c);
	}

	public void draw(GraphicsContext gc){
		for(BlockCell b : blocks){
			Shape rect = b.rect;
			gc.setFill(rect.getFill());
			gc.fillRect(
					rect.getLayoutX() + (BlockCell.blockWidth + BlockCell.blockWidthPadding) * (x+b.dx) + 100,
					rect.getLayoutY() + (BlockCell.blockHeight + BlockCell.blockHeightPadding) * (y+b.dy) + 100,
					rect.getScaleX(),
					rect.getScaleY());
		}
	}

}
