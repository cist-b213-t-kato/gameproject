package rpg;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import cargamesample.InputKey;

public class RPGApp extends Application  {

	public static Boolean gameContinue = true;
    private static Canvas canvas;//640*480pxのCanvasインスタンスの生成
    private static GraphicsContext gc;

    public static GraphicsContext getGC(){
    	return gc;
    }

	@Override
	public void start(Stage primaryStage) {

		InputKey inputKey = InputKey.getInstance();

		canvas = new Canvas(640,480);

        Pane pane = new Pane();//Paneインスタンスの生成
        pane.getChildren().add(canvas);//canvasをboardにadd
        Scene scene = new Scene(pane);//シーンインスタンスの生成

        gc = canvas.getGraphicsContext2D();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				inputKey.keyPressed(event.getCode());
			}
        });

		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				inputKey.keyReleased(event.getCode());
			}
        });

        Game game = new Game();

        //キーを押した時のイベントを設定
        //GameのInputKeyのKeyPressedメソッドを呼び出す。引数はキーコード。
        scene.setOnKeyPressed( e -> inputKey.keyPressed(e.getCode()) );

        //キーを離した時のイベントを設定
        //GameのInputKeyのKeyReleasedメソッドを呼び出す。引数はキーコード。
        scene.setOnKeyReleased( e -> inputKey.keyReleased(e.getCode()) );

        //Gameのrunメソッドを終了させる。
        //ウィンドウが閉じられた時のイベントを設定
        primaryStage.setOnCloseRequest( e->gameContinue = false );
        //ウィンドウが閉じられた時のイベントを設定
        primaryStage.setScene(scene);
        primaryStage.show();

//		gc.fillText("HelloWorld", 320, 240);

        new Thread(game).start();//ゲームスレッドの開始

	}

    public static void main(String[] args) {
        launch(args);
    }

}
