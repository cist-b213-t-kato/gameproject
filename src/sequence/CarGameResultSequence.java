package sequence;

import game_ui.GameApp;
import game_ui.InputKey;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CarGameResultSequence implements Sequence{

	private int score;

	public CarGameResultSequence(int score){
		this.score = score;
	}

	@Override
	public Sequence update() {

    	GraphicsContext gc = GameApp.getGC();

        gc.setFill(Color.BLACK);//塗りつぶしの色を黒に
        gc.setFont(new Font("Meiryo", 24));//フォントをメイリオのサイズ42に
        gc.fillText("衝突…", 0, 140);//テキストを指定の位置に描画
        gc.fillText("score:"+score, 0, 180);//テキストを指定の位置に描画
        gc.fillText("SPACEキーでもういちど", 0, 220);//テキストを指定の位置に描画

        if(InputKey.getInstance().checkStateKey(KeyCode.SPACE)){
        	return new CarGameMainSequence();
        }

		return this;
	}

}
