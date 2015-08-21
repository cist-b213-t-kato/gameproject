package rpg;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sequence.GameTitleSequence;
import sequence.Sequence;
import cargamesample.InputKey;

public class RPGApp extends Application  {

	public final Scene scene;
	public static final InputKey inputKey = new InputKey();
	public static Game game;
	public static Boolean gameContinue = true;
    Canvas canvas = new Canvas(640,480);//640*480pxのCanvasインスタンスの生成
    public static GraphicsContext gc;

	public RPGApp(){


        Pane pane = new Pane();//Paneインスタンスの生成
        pane.getChildren().add(canvas);//canvasをboardにadd
        scene = new Scene(pane);//シーンインスタンスの生成


		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
//				input.keyPressed(event.getCode());
			}
        });

        game = new Game(){
			@Override
			public Sequence getPrimarySequence() {
				return new GameTitleSequence();
			}

        };//ゲームインスタンスの生成

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new RPGApp();

        gc = canvas.getGraphicsContext2D();

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

        new Thread(game).start();//ゲームスレッドの開始

	}

    public static void main(String[] args) {
        launch(args);
    }

}
