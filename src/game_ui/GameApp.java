package game_ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import game_ui.Game.InputKey;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public abstract class GameApp extends Application {

	private static MediaView view;
	private static MediaPlayer player;

	@Override
	public void start(Stage primaryStage) {

		InputKey inputKey = InputKey.getInstance();

		Pane pane = new Pane();

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
		pane.getChildren().add(view);

		GraphicsContext gc = canvas.getGraphicsContext2D();
		Game game = createGame(gc);
		new Thread(game).start();// ゲームスレッドの開始

	}

	abstract protected Game createGame(GraphicsContext gc);

	public static void mediaplay(String p){
		Path path = Paths.get(p);
		String movieUri = path.toUri().toString();
		Media media = new Media(movieUri);

		if(player!=null){
			player.stop();
		}

		player = new MediaPlayer(media);
		view.setMediaPlayer(player);
		player.setCycleCount(Integer.MAX_VALUE);
		player.play();
	}

	public static void mediaStop(){
		if (player!=null) {
			player.stop();
		}
	}

}
