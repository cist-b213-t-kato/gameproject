package tetris;

import java.util.Random;

import game_ui.Game;
import game_ui.Game.InputKey;
import game_ui.AbstractGameApp;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import sequence.Sequence;

public class TetrisMainSequence implements Sequence {

	private BlockCell[][] board;
	private int timeCounter;
	private Tetrimino currentTetrimino;
	private Tetrimino nextTetrimino;
	private int score;
	private int maxScore;
	private boolean isAlive;
	private int recessTime;
	private Random rnd;
	private int musicNum;
	private InputKey inputKey;
	private GraphicsContext gc;

	private TetrisService service;

	class NoneBlockCell extends BlockCell {
		public NoneBlockCell(int dx, int dy) {
			super(dx, dy);
			setColor(Color.hsb(0, 0, 0.95));
		}
	}

	public TetrisMainSequence(GraphicsContext gc) {
		this.gc = gc;
		this.isAlive = true;
		this.service = new TetrisService();
		rnd = new Random();
		board = new BlockCell[10][20];
		recessTime = 0;

		inputKey = InputKey.getInstance();
		timeCounter = 0;
		musicNum = -1;
		score = 0;
		maxScore = service.getMaxScore();
		nextTetrimino = createTetrimino();

//		GameApp.mediaplay("cyber07.mp3");

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				board[i][j] = new NoneBlockCell(0, 0);
			}
		}

		loopInit();

	}

	private Tetrimino createTetrimino() {

		int r = rnd.nextInt(7);
		int r2 = rnd.nextInt(256);
		Color color = Color.hsb(r2, 1 - (double) r2 / 512, 0.8);// list.get(rnd.nextInt(list.size()));

		if (r == 0) {
			return new Tetrimino(new BlockCell(0, -1),
					new BlockCell(-1, 0), new BlockCell(1, 0), color);
		} else if (r == 1) {
			return new Tetrimino(new BlockCell(0, -1),
					new BlockCell(1, 0), new BlockCell(1, -1), color);
		} else if (r == 2) {
			return new Tetrimino(new BlockCell(-1, 0),
					new BlockCell(0, 1), new BlockCell(1, 1), color);
		} else if (r == 3) {
			return new Tetrimino(new BlockCell(1, -1),
					new BlockCell(0, -1), new BlockCell(-1, 0), color);
		} else if (r == 4) {
			return new Tetrimino(new BlockCell(-1, 0),
					new BlockCell(1, 0), new BlockCell(1, -1), color);
		} else if (r == 5) {
			return new Tetrimino(new BlockCell(-1, 0),
					new BlockCell(1, 0), new BlockCell(1, 1), color);
		} else {
			return new Tetrimino(new BlockCell(-1, 0),
					new BlockCell(1, 0), new BlockCell(2, 0), color);
		}

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
			int xx = currentTetrimino.getX() + b.dx + x;
			int yy = currentTetrimino.getY() + b.dy + y;
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
			int xx = currentTetrimino.getX() + b.dy;
			int yy = currentTetrimino.getY() - b.dx;
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
		int bottom = -1;
		int head = 21;
		for (BlockCell b : currentTetrimino.blocks) {
			if (checkDeleteLine(currentTetrimino.getY() + b.dy)) {
				if ( bottom < currentTetrimino.getY() + b.dy ) {
					bottom = currentTetrimino.getY() + b.dy;
				}
				if ( head > currentTetrimino.getY() + b.dy ) {
					head = currentTetrimino.getY() + b.dy;
				}
			}
		}

		if (head < 21 && bottom > -1) {
			int deleteCount = ( bottom - head ) + 1;
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

	public void loopInit(){
		currentTetrimino = nextTetrimino;
		currentTetrimino.setX(4);
		currentTetrimino.setY(0);

		for (int i=0; i<currentTetrimino.blocks.length; i++) {
			int xx = currentTetrimino.getX() + currentTetrimino.blocks[i].dx;
			int yy = currentTetrimino.getY() + currentTetrimino.blocks[i].dy;
			if ( !isInArea(xx, yy) ) {
				continue;
			}
			if(!(board[xx][yy] instanceof NoneBlockCell)){
				isAlive = false;
			}
		}

		nextTetrimino = createTetrimino();
		nextTetrimino.setX(13);

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

				if (inputKey.isPushed(KeyCode.LEFT)) {
					xx += -1;
				}
				if (inputKey.isPushed(KeyCode.RIGHT)) {
					xx += 1;
				}
				if (inputKey.isPushed(KeyCode.DOWN)) {
					yy += 1;
				}
				if (inputKey.isPushed(KeyCode.UP)) {
					if (checkRotate()) {
						rotate(currentTetrimino);
					}
				}

				if(checkMove(xx, yy)){
					currentTetrimino.setX(currentTetrimino.getX() + xx);
					currentTetrimino.setY(currentTetrimino.getY() + yy);
					yy = 0;
				}

				if(inputKey.isPushed(KeyCode.SPACE)){
					while(checkMove(xx, yy+1)){
						yy++;
					}

					currentTetrimino.setY(currentTetrimino.getY() + yy);
					recessTime = 61;
				}

				boolean isTouched = false; //いずれかのブロックが積みブロックと接しているときtrue
				for (BlockCell b : currentTetrimino.blocks) {
					int xxxx = currentTetrimino.getX() + b.dx;
					int yyyy = currentTetrimino.getY() + b.dy+1;
					if( yyyy>=0 && (yyyy>=20 || !(board[xxxx][yyyy] instanceof NoneBlockCell)) ){
						isTouched = true;
						break;
					}
				}
				if (isTouched) {
					recessTime += 1;
					if(recessTime > 60) {
						for (BlockCell bb : currentTetrimino.blocks) {
							int xxx = currentTetrimino.getX() + bb.dx;
							int yyy = currentTetrimino.getY() + bb.dy;
							if ( isInArea(xxx, yyy) ) {
								board[xxx][yyy] = bb;
							}
						}
						recessTime = 0;
						deleteLines();
						loopInit();
					}
				} else {
					recessTime = 0;
				}

			} else {

				if(inputKey.isPushed(KeyCode.R)){
					service.insertScore(score);
					AbstractGameApp.mediaStop();
					return new TetrisMainSequence(gc);
				}
			}

			Game.loopEnd();

		}


	}

}