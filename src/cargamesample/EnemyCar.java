/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cargamesample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//敵機のクラス
public class EnemyCar extends Car{
    private double spd;

    //コンストラクタ
    public EnemyCar(double spd ,double x, double y){
        this.spd = spd;//速度と初期座標を受け取る
        this.setX(x);
        this.setY(y);
        this.setWidth(60);
        this.setHeight(100);
        flag = true;
//        count = 0;
    }

    //自身の状態を計算
    public void calc(){
        y += spd;//速度分だけ自身を下に移動
        if(y > 550){//画面外に出たら
            flag = false;//削除対象に
        }
//        count++;
    }

    //自身を描画
    @Override
    public void graph(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(x-30, y-50, 60, 100);//車体
        gc.fillRect(x-45, y-40, 10, 20);//車輪左上
        gc.fillRect(x-45, y+20, 10, 20);//車輪左下
        gc.fillRect(x+35, y-40, 10, 20);//車輪右上
        gc.fillRect(x+35, y+20, 10, 20);//車輪右下
    }
}