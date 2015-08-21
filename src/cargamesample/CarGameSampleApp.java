/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cargamesample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CarGameSampleApp extends Application {
    
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(640,480);//640*480pxのCanvasインスタンスの生成
        
        Pane pane = new Pane();//Paneインスタンスの生成
        pane.getChildren().add(canvas);//canvasをboardにadd
        CarGameFX game = new CarGameFX(canvas.getGraphicsContext2D());//ゲームインスタンスの生成

        Scene scene = new Scene(pane);//シーンインスタンスの生成
        
        //キーを押した時のイベントを設定
        //GameのInputKeyのKeyPressedメソッドを呼び出す。引数はキーコード。
        scene.setOnKeyPressed( e -> game.getInputKey().keyPressed(e.getCode()) );
        
        //キーを離した時のイベントを設定
        //GameのInputKeyのKeyReleasedメソッドを呼び出す。引数はキーコード。
        scene.setOnKeyReleased( e -> game.getInputKey().keyReleased(e.getCode()) );
        
        //Gameのrunメソッドを終了させる。
        //ウィンドウが閉じられた時のイベントを設定
        stage.setOnCloseRequest( e->game.exitGame() );
        stage.setScene(scene);
        stage.show();
        new Thread(game).start();//ゲームスレッドの開始
    }

    public static void main(String[] args) {
        launch(args);
    }
}
