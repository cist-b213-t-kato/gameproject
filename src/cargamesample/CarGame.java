/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cargamesample;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import static javafx.scene.input.KeyCode.SPACE;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class CarGame implements Runnable {

    private InputKey inputKey;//キーの状態を保持するインスタンス
    private boolean loopFlag;//メインループを続けていいかのフラグ
    protected int count;//ゲームカウンタ
    protected MyCar myCar;//自機の参照
    protected List<EnemyCar> enemyCarList;//敵機の参照リスト

    //コンストラクタ
    public CarGame() {
        inputKey = new InputKey();
        loopFlag = true;
        count = 0;
        myCar = new MyCar(inputKey);
        enemyCarList = new ArrayList<>();
    }

    //スレッドが走るメソッド
    @Override
    public void run() {
        //メインループ
        while (loopFlag) {
            if (myCar.isFlag()) {//自機が生きているなら
                calc();//計算
            } else {//自機が死んでいるなら
                if (inputKey.checkStateKey(SPACE)) {//スペースキーが押されたら
                    myCar.setFlag(true);//自機を蘇らせる
                    myCar.setX(320);//位置を初期化
                    myCar.setY(420);
                    count = 0;//カウンタを初期化
                    enemyCarList.clear();//敵機を全て消す
                }
            }
            graph();//描画
            count += 1;
            try {
                Thread.sleep(1000 / 60);//1/60秒待機する＝1秒間に60回処理を行う
            } catch (InterruptedException ex) {

            }
        }
    }

    //ゲームの状態の計算
    private void calc() {
        if (count % 60 == 0) {//1秒置きに
            enemyCarList.add(new EnemyCar(3 + Math.random() * 7, Math.random() * 640, -50));//敵機を生成してリストに追加
        }
        
        //自機の計算
        myCar.calc();

        //敵機の計算
        enemyCarList.stream().forEach(enemy -> enemy.calc());

        hit();//当たり判定を行う
        remove();//不要な敵の削除
    }

    //当たり判定
    private void hit() {
        enemyCarList.forEach(enemyCar -> {
            if (myCar.isHitting(enemyCar)) {
                myCar.setFlag(false);
            }
        }
        );
    }

    //削除対象の削除
    private void remove() {
        for (int i = 0; i < enemyCarList.size(); i++) {
            if (enemyCarList.get(i).isFlag() == false) {
                enemyCarList.remove(i);
            }
        }
    }

    //描画
    abstract protected void graph();

    //ゲームの終了
    public void exitGame() {
        loopFlag = false;//ループを無効にする
    }

    //以下ゲッター///////////////////////
    public InputKey getInputKey() {
        return inputKey;
    }
}
