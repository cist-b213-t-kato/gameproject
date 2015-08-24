package sequence;

import game_ui.GameApp;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import cargamesample.EnemyCar;
import cargamesample.MyCar;

public class CarGameMainSequence implements Sequence{

    private int count;//ゲームカウンタ
    protected MyCar myCar;//自機の参照
    protected List<EnemyCar> enemyCarList;//敵機の参照リスト

    //コンストラクタ
    public CarGameMainSequence() {
        enemyCarList = new ArrayList<>();
        count = 0;
        myCar = new MyCar();
        myCar.setFlag(true);//自機を蘇らせる
        myCar.setX(320);//位置を初期化
        myCar.setY(420);
    }

	@Override
	public Sequence update() {
        if (myCar.isFlag()) {//自機が生きているなら
            calc();//計算
        } else {//自機が死んでいるなら
        	return new CarGameResultSequence(count);
        }
        graph();//描画
        count += 1;

		return this;
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
    protected void graph(){
    	GraphicsContext gc = GameApp.getGC();
		GameApp.getGC().clearRect(0, 0, 640, 480);
		myCar.graph(gc);//自機の描画
        enemyCarList.forEach(enemy -> enemy.graph(gc));
        gc.setFill(Color.BLACK);//塗りつぶしの色を黒に
        gc.setFont(new Font("Meiryo", 22));//フォントをメイリオのサイズ22に
        gc.fillText("カウント" + count, 0, 20);//テキストを指定の位置に描画
        gc.fillText("" + enemyCarList.size(), 0, 68);//テキストを指定の位置に描画

    }

}
