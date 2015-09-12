package game_ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tetris.TetrisGame;

public class GameApp extends Application {

	private static Canvas canvas;// 640*480pxのCanvasインスタンスの生成
	private static GraphicsContext gc;
	public Pane pane;// Paneインスタンスの生成

	public static GraphicsContext getGC() {
		return gc;
	}

	@Override
	public void start(Stage primaryStage) {

		InputKey inputKey = InputKey.getInstance();

		pane = new Pane();

		canvas = new Canvas(720, 960);

//		Pane pane = new Pane();// Paneインスタンスの生成
		pane.getChildren().add(canvas);// canvasをboardにadd
		Scene scene = new Scene(pane);// シーンインスタンスの生成

		gc = canvas.getGraphicsContext2D();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				inputKey.keyPressed(event.getCode());
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				inputKey.keyReleased(event.getCode());
			}
		});

		Game game = new TetrisGame(this);

		// キーを押した時のイベントを設定
		// GameのInputKeyのKeyPressedメソッドを呼び出す。引数はキーコード。
//		scene.setOnKeyPressed(e -> {
//			inputKey.keyPressed(e.getCode());
//			System.out.println("なんかが押されてる");
//
//		});

		scene.setOnKeyPressed(e->{
			inputKey.keyPressed(e.getCode());
		});

		// キーを離した時のイベントを設定
		// GameのInputKeyのKeyReleasedメソッドを呼び出す。引数はキーコード。
		scene.setOnKeyReleased(e -> inputKey.keyReleased(e.getCode()));

		// Gameのrunメソッドを終了させる。
		// ウィンドウが閉じられた時のイベントを設定
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			Game.loopFlag = false;
		});
		primaryStage.setScene(scene);
		primaryStage.show();

		new Thread(game).start();// ゲームスレッドの開始

	}

	public static void main(String[] args) {
		launch(args);
	}

}
