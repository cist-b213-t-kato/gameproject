package game_ui;

import game_ui.Game.InputKey;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import tetris.TetrisGame;

public class GameApp extends Application {

//	private static GraphicsContext gc;
	public static Pane pane;// Paneインスタンスの生成
	public static MediaView view;

//	public static GraphicsContext getGC() {
//		return gc;
//	}

	@Override
	public void start(Stage primaryStage) {

		InputKey inputKey = InputKey.getInstance();

		pane = new Pane();

		Canvas canvas = new Canvas(720, 960);

		pane.getChildren().add(canvas);// canvasをboardにadd
		Scene scene = new Scene(pane);// シーンインスタンスの生成

		// キーを押した時のイベントを設定
		// GameのInputKeyのKeyPressedメソッドを呼び出す
		scene.setOnKeyPressed(e->inputKey.keyPressed(e.getCode()));
//		scene.setOnKeyTyped(e->inputKey.keyPressed(e.getCode()));

		// キーを離した時のイベントを設定
		// GameのInputKeyのKeyReleasedメソッドを呼び出す
		scene.setOnKeyReleased(e -> inputKey.keyReleased(e.getCode()));

		//暗黙的なプログラムの終了？ Platform.exit()を使わない場合
		Platform.setImplicitExit(true);

		// ウィンドウが閉じられた時のイベントを設定
		// ゲームのデータを保存するような場合はこの設計は正さなければならない
		primaryStage.setOnCloseRequest(e -> System.exit(0));

		primaryStage.setScene(scene);
		primaryStage.show();

		//音声の再生
		view = new MediaView();
		GameApp.pane.getChildren().add(view);

		GraphicsContext gc = canvas.getGraphicsContext2D();
		Game game = new TetrisGame(gc);
		new Thread(game).start();// ゲームスレッドの開始

	}

	public static void main(String[] args) {
		launch(args);
	}

}
