/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cargamesample;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author b2130480
 */
public class CarGameFX extends CarGame{
    private GraphicsContext gc;//描画メソッドを持つインスタンス

    //コンストラクタ
    //描画対象のCanvasが持つGraphicContextの参照を受け取る
    public CarGameFX(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    protected void graph() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gc.setFill(Color.WHITE);//塗りつぶしの色を白に
                gc.fillRect(0, 0, 640, 480);//画面の描画内容を全て消す
                if (myCar.isFlag()) {//自機が生きているなら
                    myCar.graph(gc);//自機の描画
                    enemyCarList.forEach(enemy -> enemy.graph(gc));
                    gc.setFill(Color.BLACK);//塗りつぶしの色を黒に
                    gc.setFont(new Font("Meiryo", 22));//フォントをメイリオのサイズ22に
                    gc.fillText("カウント" + count, 0, 20);//テキストを指定の位置に描画
                    gc.fillText("" + enemyCarList.size(), 0, 68);//テキストを指定の位置に描画
                } else {//自機が死んでいたら
                    gc.setFill(Color.BLACK);//塗りつぶしの色を黒に
                    gc.setFont(new Font("Meiryo", 42));//フォントをメイリオのサイズ42に
                    gc.fillText("PRESS SPACEKEY", 150, 240);//テキストを指定の位置に描画
                }
            }
        });
    }
    
}
