package tetris;

import game_ui.Game;
import game_ui.Game.InputKey;
import game_ui.GameApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import sequence.Sequence;
import tetris.BlockCell.BlockCellColor;

public class TetrisMainSequence implements Sequence {

	BlockCell[][] board = new BlockCell[10][20];

	class NoneBlockCell extends BlockCell {
		public NoneBlockCell(int dx, int dy) {
			super(dx, dy);
			setColor(BlockCellColor.NONE);
		}
	}

	public TetrisMainSequence() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				board[i][j] = new NoneBlockCell(0, 0);
			}
		}
	}

	int counter = 0;
	Tetrimino tetrimino;
	Tetrimino nextTetrimino;

	private Tetrimino randomTetrimino() {

		Tetrimino tetrimino;

		Random rnd = new Random();

		int r = rnd.nextInt(7);
		List<BlockCellColor> list = new ArrayList<>(Arrays.asList(
				BlockCellColor.RED, BlockCellColor.BLUE));
		// Color color = Color.hsb(rnd.nextInt(256), 0.8,
		// 0.8);//list.get(rnd.nextInt(list.size()));
		int r2 = rnd.nextInt(256);
		Color color = Color.hsb(r2, 1 - (double) r2 / 512, 0.8);// list.get(rnd.nextInt(list.size()));

		if (r == 0) {
			tetrimino = new Tetrimino(new BlockCell(0, -1),
					new BlockCell(-1, 0), new BlockCell(1, 0), color);
		} else if (r == 1) {
			tetrimino = new Tetrimino(new BlockCell(0, -1),
					new BlockCell(1, 0), new BlockCell(1, -1), color);
		} else if (r == 2) {
			tetrimino = new Tetrimino(new BlockCell(-1, 0),
					new BlockCell(0, 1), new BlockCell(1, 1), color);
		} else if (r == 3) {
			// ┏┛
			tetrimino = new Tetrimino(new BlockCell(1, 0), new BlockCell(0, 1),
					new BlockCell(-1, 1), color);
		} else if (r == 4) {
			tetrimino = new Tetrimino(new BlockCell(-1, 0),
					new BlockCell(1, 0), new BlockCell(1, -1), color);
		} else if (r == 5) {
			tetrimino = new Tetrimino(new BlockCell(-1, 0),
					new BlockCell(1, 0), new BlockCell(1, 1), color);
		} else {
			tetrimino = new Tetrimino(new BlockCell(-1, 0),
					new BlockCell(1, 0), new BlockCell(2, 0), color);
		}

		return tetrimino;
	}



	public void rotate(Tetrimino t){
		for(BlockCell b : t.blocks){
			int xt = b.dx, yt = b.dy;
			b.dx = yt;
			b.dy = -xt;
		}
	}

	public boolean checkMove(int xx, int yy){
		for (BlockCell b : tetrimino.blocks) {
			if (tetrimino.x + b.dx + xx < 0
					|| tetrimino.x + b.dx + xx >= 10
					|| tetrimino.y + b.dy + yy < 0
					|| tetrimino.y + b.dy + yy >= 20
					|| !(board[tetrimino.x + b.dx + xx][tetrimino.y
							+ b.dy + yy] instanceof NoneBlockCell)) {
				return false;
			}
		}
		return true;
	}

	public boolean checkRotate(){
		for (BlockCell b : tetrimino.blocks) {
			if (tetrimino.x + b.dy < 0 || tetrimino.x + b.dy >= 10
					|| tetrimino.y - b.dx < 0
					|| tetrimino.y - b.dx > 20) {
				return false;
			}
		}
		return true;
	}

	public void putTetrimino(){
		for (BlockCell b2 : tetrimino.blocks) {
			board[tetrimino.x + b2.dx][tetrimino.y + b2.dy] = b2;
		}
	}

	public boolean checkDeleteLine(int y){
		for (int j = 0; j < 10; j++) {
			if (board[j][y] instanceof NoneBlockCell) {
				return false;
			}
		}
		return true;
	}


	int score;

	@Override
	public Sequence update() {

		InputKey inputKey = InputKey.getInstance();

		nextTetrimino = this.randomTetrimino();

		int musicNum = -1;

		Game.mediaplay("cyber07.mp3");

		LOOP: while (true) {

			tetrimino = nextTetrimino;
			tetrimino.x = 4;

			nextTetrimino = this.randomTetrimino();
			nextTetrimino.x = 13;

			if (musicNum < score / 500) {
				musicNum = score / 500;
				// if(musicNum%2 == 0){
				// Game.mediaplay("cyber07.mp3");
				// }else if(musicNum%2 == 1){
				// Game.mediaplay("cyber08.mp3");
				// }
			}

			while (true) {
				int xx = 0, yy = 0;

				counter = (counter + 1) % (60);
				if (counter == 0) {
					yy += 1;
				}

				if (inputKey.checkStateKey(KeyCode.LEFT)) {
					xx += -1;
				}
				if (inputKey.checkStateKey(KeyCode.RIGHT)) {
					xx += 1;
				}
				if (inputKey.checkStateKey(KeyCode.DOWN)) {
					yy += 1;
				}
				if (inputKey.checkStateKey(KeyCode.UP)) {
					if (checkRotate()) {
						rotate(tetrimino);
					}
				}

				if(checkMove(xx, yy)){
					tetrimino.x += xx;
					tetrimino.y += yy;
					yy = 0;
				}


				for (BlockCell b : tetrimino.blocks) {
					if (tetrimino.y + b.dy <= 0 || tetrimino.x + b.dx < 0
							|| tetrimino.x + b.dx >= 10) {
						continue;
					}
					if ((tetrimino.y + b.dy >= 19
							|| !(board[tetrimino.x + b.dx][tetrimino.y + b.dy + 1] instanceof NoneBlockCell))
							&& yy>=1) {
						putTetrimino();

						// ブロック消去
						int deleteLine = 0;
						int deleteLineHeight = 0;
						boolean flag2 = false;
						for (BlockCell b3 : tetrimino.blocks) {
							if (tetrimino.y + b3.dy < 0) {
								continue;
							}
							if (checkDeleteLine(tetrimino.y + b3.dy)) {
								flag2 = true;
								if (deleteLineHeight == tetrimino.y + b3.dy) {
									continue;
								}
								deleteLine += 1;
								deleteLineHeight = deleteLineHeight < tetrimino.y
										+ b3.dy ? tetrimino.y + b3.dy
										: deleteLineHeight;
							}
						}
						if (flag2) {
							score += deleteLine * 100;
							for (int i = deleteLineHeight; i >= 0 + deleteLine; i--) {
								for (int j = 0; j < 10; j++) {
									board[j][i] = board[j][i - deleteLine];
								}
							}
						}

						continue LOOP;
					}
				}

				Platform.runLater(() -> {
					// 画面をクリアする
					GameApp.getGC().clearRect(0, 0, 720, 960);

					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 20; j++) {
							Shape rect = board[i][j].rect;
							GameApp.getGC().setFill(rect.getFill());
							GameApp.getGC().fillRect(
									rect.getLayoutX() + 34 * i + 100,
									rect.getLayoutY() + 34 * j + 100,
									rect.getScaleX(), rect.getScaleY());
							GameApp.getGC().setFill(Color.GRAY);// 塗りつぶしの色を灰色に
							GameApp.getGC().setFont(new Font("Meiryo", 66));
							GameApp.getGC().fillText("" + score, 500, 700);
						}
					}

					tetrimino.draw();
					nextTetrimino.draw();

				});

				Game.loopEnd();

			}
		}
	}

}
