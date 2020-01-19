package tetris;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import sequence.Sequence;

public class TetrisMainSequenceEx extends TetrisMainSequence {
	private GraphicsContext gc;

	public TetrisMainSequenceEx(GraphicsContext gc) {
		this.gc = gc;
	}

	@Override
	public Sequence next() {
		return new TetrisMainSequenceEx(gc);
	}

	public void draw() {
		Platform.runLater(() -> {
			// 画面をクリアする
			gc.clearRect(0, 0, 720, 960);
			gc.setFill(Color.GRAY);// 塗りつぶしの色を灰色に
			gc.setFont(new Font("Meiryo", 66));
			gc.fillText("" + score, 500, 700);
			gc.fillText("" + maxScore, 500, 750);

			for (int j = 0; j < 10; j++) {
				for (int i = 0; i < 20; i++) {
					Shape rect = board[j][i].rect;
					gc.setFill(rect.getFill());
					gc.fillRect(
						rect.getLayoutX() + (BlockCell.blockWidth + BlockCell.blockWidthPadding) * j + 100,
						rect.getLayoutY() + (BlockCell.blockHeight + BlockCell.blockHeightPadding) * i + 100,
						rect.getScaleX(),
						rect.getScaleY());
				}
			}

			// ここ
//			for (BlockCell b : currentTetrimino.blocks) {
//				int xx = currentTetrimino.x + b.dx;
//				for (int i=currentTetrimino.y+b.dy+1; i<20 && board[xx][i] instanceof NoneBlockCell; i++) {
//				    	Shape rect = new Rectangle();
//				    	rect.setScaleX(BlockCell.blockWidth);
//				    	rect.setScaleY(BlockCell.blockHeight);
//					gc.setFill(Color.hsb(180, 0.2, 0.9));
//					gc.fillRect(
//							rect.getLayoutX() + (BlockCell.blockWidth + BlockCell.blockWidthPadding) * (xx) + 100,
//							rect.getLayoutY() + (BlockCell.blockHeight + BlockCell.blockHeightPadding) * (i) + 100,
//							rect.getScaleX(),
//							rect.getScaleY());
//				}
//			}

			int minY = 20;
			for (BlockCell b : currentTetrimino.blocks) {
				for (int i=0; i < minY; i++) {
					int xxxx = currentTetrimino.getX() + b.dx;
					int yyyy = currentTetrimino.getY() + b.dy+i;
					if( yyyy+1>=0 && (yyyy+1>=20 || !(board[xxxx][yyyy+1] instanceof NoneBlockCell)) ){
						minY = i;
						break;
					}
				}
			}
			for (BlockCell b : currentTetrimino.blocks) {
			    	Shape rect = new Rectangle();
			    	rect.setScaleX(BlockCell.blockWidth);
			    	rect.setScaleY(BlockCell.blockHeight);
				gc.setFill(Color.hsb(180, 0.2, 0.9));
				gc.fillRect(
						rect.getLayoutX() + (BlockCell.blockWidth + BlockCell.blockWidthPadding) * (currentTetrimino.getX()+b.dx) + 100,
						rect.getLayoutY() + (BlockCell.blockHeight + BlockCell.blockHeightPadding) * (currentTetrimino.getY()+b.dy+minY) + 100,
						rect.getScaleX(),
						rect.getScaleY());
			}

			for(BlockCell b : currentTetrimino.blocks){
				Shape rect = b.rect;
				if ( currentTetrimino.getY()+b.dy < 0 ) {
					continue;
				}
				gc.setFill(rect.getFill());
				gc.fillRect(
						rect.getLayoutX() + (BlockCell.blockWidth + BlockCell.blockWidthPadding) * (currentTetrimino.getX()+b.dx) + 100,
						rect.getLayoutY() + (BlockCell.blockHeight + BlockCell.blockHeightPadding) * (currentTetrimino.getY()+b.dy) + 100,
						rect.getScaleX(),
						rect.getScaleY());
			}

			for(BlockCell b : nextTetrimino.blocks){
				Shape rect = b.rect;
				gc.setFill(rect.getFill());
				gc.fillRect(
						rect.getLayoutX() + (BlockCell.blockWidth + BlockCell.blockWidthPadding) * (nextTetrimino.getX()+b.dx) + 100,
						rect.getLayoutY() + (BlockCell.blockHeight + BlockCell.blockHeightPadding) * (nextTetrimino.getY()+b.dy) + 100,
						rect.getScaleX(),
						rect.getScaleY());
			}

			if (!isAlive) {
				gc.setFill(Color.hsb(1.0, 0.0, 0.0, 0.5));
				gc.fillRect(0, 0, 720, 960);
				gc.setFill(Color.hsb(1.0, 0.0, 1.0));
				gc.setFont(new Font("Meiryo Bold", 66));
				gc.fillText("げーむおーばー", 150, 400);
				gc.setFont(new Font("Meiryo", 32));
				gc.fillText("push R key !", 260, 450);
			}

		});
	}
}
