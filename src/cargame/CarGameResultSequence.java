package cargame;

import game_ui.Game.InputKey;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sequence.Sequence;

public class CarGameResultSequence implements Sequence{

	private GraphicsContext gc;
	private int score;

	public CarGameResultSequence(GraphicsContext gc, int score){
		this.gc = gc;
		this.score = score;
	}

	@Override
	public Sequence update() {

        gc.setFill(Color.BLACK);//塗りつぶしの色を黒に
        gc.setFont(new Font("Meiryo", 24));//フォントをメイリオのサイズ42に
        gc.fillText("衝突…", 0, 140);//テキストを指定の位置に描画
        gc.fillText("score:"+score, 0, 180);//テキストを指定の位置に描画
        gc.fillText("SPACEキーでもういちど", 0, 220);//テキストを指定の位置に描画

        if(InputKey.getInstance().isPushed(KeyCode.SPACE)){
        	return new CarGameMainSequence(gc);
        }

		return this;
	}

}
