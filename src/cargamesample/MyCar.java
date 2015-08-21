/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cargamesample;

import javafx.scene.canvas.GraphicsContext;
import static javafx.scene.input.KeyCode.*;
import javafx.scene.paint.Color;

//自機のクラス
public class MyCar extends Car{
    InputKey inputKey; //キー入力情報
    
    //コンストラクタ
    public MyCar(InputKey inputKey){
        this.inputKey = inputKey;//InputKeyの参照を受け取る
        this.width = 60;
        this.height = 100;
        flag = false;
    }
    
    //自身の状態の計算
    public void calc(){
        if(inputKey.checkStateKey(LEFT) && x > 0){//左が押されていて、且つ左端でなければ
           x -= 6;//左に移動
        }
        if(inputKey.checkStateKey(RIGHT) && x < 640){//右が押されていて、且つ右端でなければ
           x += 6;//右に移動
        }
        if(inputKey.checkStateKey(UP) && y > 0){//上が押されていて、且つ上端でなければ
           y -= 6;//上に移動
        }
        if(inputKey.checkStateKey(DOWN) && y < 480){//下が押されていて、且つ下端でなければ
           y += 6;//下に移動
        }
        count++;
    }
    
    //自身を描画
    @Override
    public void graph(GraphicsContext gc){
        gc.setFill(Color.RED);
        gc.fillRect(x-30, y-50, 60, 100);//車体
        gc.fillRect(x-45, y-40, 10, 20);//車輪左上
        gc.fillRect(x-45, y+20, 10, 20);//車輪左下
        gc.fillRect(x+35, y-40, 10, 20);//車輪右上
        gc.fillRect(x+35, y+20, 10, 20);//車輪右下
    }
}
