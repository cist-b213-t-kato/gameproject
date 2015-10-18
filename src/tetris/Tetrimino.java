package tetris;

import game_ui.GameApp;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import tetris.BlockCell.BlockCellColor;

public class Tetrimino {

	public int x, y;	//軸ブロックの絶対位置
	BlockCell[] blocks = new BlockCell[4];
	BlockCellColor color;

	public Tetrimino(BlockCell b1, BlockCell b2, BlockCell b3, BlockCellColor c){
		blocks[0] = new BlockCell(0, 0); //軸ブロック
		blocks[1] = b1;
		blocks[2] = b2;
		blocks[3] = b3;
		color = c;
		blocks[0].setColor(color);
		blocks[1].setColor(color);
		blocks[2].setColor(color);
		blocks[3].setColor(color);
	}

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

	public void rotate(){
		for(BlockCell b : blocks){
			int xt = b.dx, yt = b.dy;
			b.dx = yt;
			b.dy = -xt;
		}
	}

	public void draw(){
		for(BlockCell b : blocks){
			Shape rect = b.rect;
			GameApp.getGC().setFill(rect.getFill());
			GameApp.getGC().fillRect(rect.getLayoutX() + 34 * (x+b.dx) + 100,
					rect.getLayoutY() + 34 * (y+b.dy) + 100, rect.getScaleX(),
					rect.getScaleY());
		}
	}

}
