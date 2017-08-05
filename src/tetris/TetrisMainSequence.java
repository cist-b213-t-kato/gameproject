package tetris;

import java.util.Random;

import game_ui.Game;
import game_ui.Game.InputKey;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import sequence.Sequence;

public class TetrisMainSequence extends Sequence {

	private BlockCell[][] board;
	private int timeCounter;
	private Tetrimino currentTetrimino;
	private Tetrimino nextTetrimino;
	private int musicNum;
	private int score;
	private GraphicsContext gc;
	private boolean isAlive;
	private InputKey inputKey;
	private Random rnd;

	class NoneBlockCell extends BlockCell {
		public NoneBlockCell(int dx, int dy) {
			super(dx, dy);
			setColor(Color.hsb(0, 0, 0.95));
		}
	}

	public TetrisMainSequence(GraphicsContext gc) {
		this.gc = gc;
		this.isAlive = true;
		rnd = new Random();
		board = new BlockCell[10][20];

		inputKey = InputKey.getInstance();
		timeCounter = 0;
		musicNum = -1;
		score = 0;
		nextTetrimino = createTetrimino();

//		Game.mediaplay("cyber07.mp3");

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				board[i][j] = new NoneBlockCell(0, 0);
			}
		}

		loopInit();

	}

	private Tetrimino createTetrimino() {

		Tetrimino tetrimino;

		int r = rnd.nextInt(7);
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
			tetrimino = new Tetrimino(new BlockCell(1, -1), new BlockCell(0, -1),
					new BlockCell(-1, 0), color);
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
			int tmpx = b.dx;
			int tmpy = b.dy;
			b.dx = tmpy;
			b.dy = -tmpx;
		}
	}

	public boolean isInArea(int x, int y){
		return x>=0 && x<10	&& y>=0 && y<20;// && board[x][y] instanceof NoneBlockCell;
	}

	public boolean checkMove(int x, int y){
		for (BlockCell b : currentTetrimino.blocks) {
			int xx = currentTetrimino.x + b.dx + x;
			int yy = currentTetrimino.y + b.dy + y;
			if ( yy < 0 ) {
				continue;
			}
			if( !(xx>=0 && xx<10	&& yy<20) || !(board[xx][yy] instanceof NoneBlockCell)){
				return false;
			}
		}
		return true;
	}

	public boolean checkRotate(){
		for (BlockCell b : currentTetrimino.blocks) {
			int xx = currentTetrimino.x + b.dy;
			int yy = currentTetrimino.y - b.dx;
			if ( yy < 0 ) {
				continue;
			}
			if( !(xx>=0 && xx<10	&& yy<20) || !(board[xx][yy] instanceof NoneBlockCell)){
				return false;
			}
		}
		return true;
	}

	public boolean checkDeleteLine(int y){
		if ( y<0 || y>=20 ) {
			return false;
		}
		for (int j = 0; j < 10; j++) {
			if (board[j][y] instanceof NoneBlockCell) {
				return false;
			}
		}
		return true;
	}

	public void deleteLines(){
		int deleteCount = 0;
		int bottom = 0;
		for (BlockCell b : currentTetrimino.blocks) {
			if (checkDeleteLine(currentTetrimino.y + b.dy)) {
				if(bottom < currentTetrimino.y + b.dy ){
					bottom = currentTetrimino.y + b.dy;
					deleteCount += 1;
				}
			}
		}

		if (deleteCount>0) {
			score += deleteCount * 100;
			for (int i = bottom; i >= deleteCount; i--) {
				for (int j = 0; j < 10; j++) {
					board[j][i] = board[j][i - deleteCount];
				}
			}
		}
	}

	public void draw(){
		Platform.runLater(() -> {
			// 画面をクリアする
			gc.clearRect(0, 0, 720, 960);
			gc.setFill(Color.GRAY);// 塗りつぶしの色を灰色に
			gc.setFont(new Font("Meiryo", 66));
			gc.fillText("" + score, 500, 700);

			for (int j = 0; j < 10; j++) {
				boolean match = false;
				int yy = 0;
				for (int i = 0; i < currentTetrimino.blocks.length; i++) {
					if ( j == currentTetrimino.x + currentTetrimino.blocks[i].dx ) {
						match = true;
						if ( yy < currentTetrimino.y + currentTetrimino.blocks[i].dy ) {
							yy = currentTetrimino.y + currentTetrimino.blocks[i].dy;
						}
					}
				}
				for (int i = 0; i < 20; i++) {
					Shape rect = board[j][i].rect;
					if (board[j][i] instanceof NoneBlockCell && match && i > yy) {
						gc.setFill(Color.hsb(180, 0.2, 0.95));
					} else {
						gc.setFill(rect.getFill());
					}
					gc.fillRect(
						rect.getLayoutX() + (BlockCell.blockWidth + 1) * j + 100,
						rect.getLayoutY() + (BlockCell.blockHeight + 1) * i + 100,
						rect.getScaleX(), rect.getScaleY());
				}
			}

			for(BlockCell b : currentTetrimino.blocks){
				Shape rect = b.rect;
				if ( currentTetrimino.y+b.dy < 0 ) {
					continue;
				}
				gc.setFill(rect.getFill());
				gc.fillRect(
						rect.getLayoutX() + (BlockCell.blockWidth + BlockCell.blockWidthPadding) * (currentTetrimino.x+b.dx) + 100,
						rect.getLayoutY() + (BlockCell.blockHeight + BlockCell.blockHeightPadding) * (currentTetrimino.y+b.dy) + 100,
						rect.getScaleX(),
						rect.getScaleY());
			}

			for(BlockCell b : nextTetrimino.blocks){
				Shape rect = b.rect;
				gc.setFill(rect.getFill());
				gc.fillRect(
						rect.getLayoutX() + (BlockCell.blockWidth + BlockCell.blockWidthPadding) * (nextTetrimino.x+b.dx) + 100,
						rect.getLayoutY() + (BlockCell.blockHeight + BlockCell.blockHeightPadding) * (nextTetrimino.y+b.dy) + 100,
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

	public void loopInit(){
		currentTetrimino = nextTetrimino;
		currentTetrimino.x = 4;
		currentTetrimino.y = 0;

		for (int i=0; i<currentTetrimino.blocks.length; i++) {
			int xx = currentTetrimino.x + currentTetrimino.blocks[i].dx;
			int yy = currentTetrimino.y + currentTetrimino.blocks[i].dy;
			if ( !isInArea(xx, yy) ) {
				continue;
			}
			if(!(board[xx][yy] instanceof NoneBlockCell)){
				isAlive = false;
			}
		}

		nextTetrimino = createTetrimino();
		nextTetrimino.x = 13;

//		if (musicNum < score / 500) {
//			musicNum = score / 500;
//			// if(musicNum%2 == 0){
//			// Game.mediaplay("cyber07.mp3");
//			// }else if(musicNum%2 == 1){
//			// Game.mediaplay("cyber08.mp3");
//			// }
//		}
	}

	@Override
	public Sequence update() {

		while ( true ) {

			draw();

			if ( isAlive ) {

				int xx = 0, yy = 0;

				timeCounter = (timeCounter + 1) % (600000);
				if (timeCounter%(200000/(score+5000)) == 0) {
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
						rotate(currentTetrimino);
					}
				}

				if(checkMove(xx, yy)){
					currentTetrimino.x += xx;
					currentTetrimino.y += yy;
					yy = 0;
				}

				if(inputKey.checkStateKey(KeyCode.SPACE)){
					while(checkMove(xx, yy+1)){
						yy++;
					}

					currentTetrimino.y += yy;
//					yy = 1;
				}

				for (BlockCell b : currentTetrimino.blocks) {
					int xxxx = currentTetrimino.x + b.dx;
					int yyyy = currentTetrimino.y + b.dy+1;
					if( yyyy>=0 && (yyyy>=20 || !(board[xxxx][yyyy] instanceof NoneBlockCell)) ){
						for (BlockCell bb : currentTetrimino.blocks) {
							int xxx = currentTetrimino.x + bb.dx;
							int yyy = currentTetrimino.y + bb.dy;
							if ( isInArea(xxx, yyy) ) {
								board[xxx][yyy] = bb;
							}
						}
						deleteLines();
						loopInit();
					}
				}

			} else {
				if(inputKey.checkStateKey(KeyCode.R)){
					Game.mediaStop();
					return new TetrisMainSequence(gc);
				}
			}

			Game.loopEnd();

		}


	}

}