package cargame;

import game_ui.Game;
import game_ui.GameApp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sequence.Sequence;

public class CarGameMainSequence implements Sequence {

	private int count;// ゲームカウンタ
	protected MyCar myCar;// 自機の参照
	protected List<EnemyCar> enemyCarList;// 敵機の参照リスト

	// コンストラクタ
	public CarGameMainSequence() {
		enemyCarList = new ArrayList<>();
		count = 0;
		myCar = new MyCar();
		myCar.setFlag(true);// 自機を蘇らせる
		myCar.setX(320);// 位置を初期化
		myCar.setY(420);
	}

	@Override
	public Sequence update() {
		while(Game.loopFlag){
			count += 1;
			if (count % 10 == 0) {// 1秒置きに
				enemyCarList.add(new EnemyCar(3 + Math.random() * 7,
						Math.random() * 640, -50));// 敵機を生成してリストに追加
			}

			// 自機の計算
			myCar.calc();

			// 敵機の計算
			enemyCarList.stream().forEach(enemy -> enemy.calc());

			// 当たり判定を行う
			enemyCarList.forEach(enemyCar -> {
				if (myCar.isHitting(enemyCar)) {
					myCar.setFlag(false);
				}
			});

			// 不要な敵の除去
			enemyCarList = enemyCarList.stream()
					.parallel()
					.filter(e->e.isFlag() == true)
					.collect(Collectors.toList());

			// 描画
			Platform.runLater(()->{
				GraphicsContext gc = GameApp.getGC();
				GameApp.getGC().clearRect(0, 0, 640, 480);
				myCar.graph(gc);// 自機の描画
				enemyCarList.forEach(enemy -> enemy.graph(gc));// 敵機の描画
				gc.setFill(Color.BLACK);// 塗りつぶしの色を黒に
				gc.setFont(new Font("Meiryo", 22));// フォントをメイリオのサイズ22に
				gc.fillText("カウント" + count, 0, 20);// テキストを指定の位置に描画
				gc.fillText("" + enemyCarList.size(), 0, 68);// テキストを指定の位置に描画
			});
			Game.sleep();

			if (!myCar.isFlag()) {// 自機が死んでいるなら
				return new CarGameResultSequence(count);
			}else{
//				break;
			}

		}
		return this;

	}

}
