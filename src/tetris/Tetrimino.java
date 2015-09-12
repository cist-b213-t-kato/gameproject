package tetris;

import tetris.BlockCell.BlockCellColor;

public class Tetrimino {

	public int x, y;	//軸ブロックの絶対位置
	BlockCell axisBlock = new BlockCell(0, 0);	//軸ブロック
	BlockCell[] blocks = new BlockCell[3];

	BlockCellColor color;

	public Tetrimino(BlockCell b1, BlockCell b2, BlockCell b3, BlockCellColor c){
		color = c;
		b1.setColor(color);
		b2.setColor(color);
		b3.setColor(color);
		blocks[0] = b1;
		blocks[1] = b2;
		blocks[2] = b3;
	}

}
