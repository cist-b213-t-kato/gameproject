package sequence;

import static javafx.scene.input.KeyCode.*;
import game_ui.GameApp;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import cargamesample.InputKey;

public class CarGameTitleSequence implements Sequence{

	@Override
	public Sequence update() {
    	GraphicsContext gc = GameApp.getGC();

		gc.clearRect(0, 0, 640, 480);
        gc.setFill(Color.BLACK);//塗りつぶしの色を黒に
        gc.setFont(new Font("Meiryo", 42));//フォントをメイリオのサイズ42に
        gc.fillText("PRESS SPACEKEY", 150, 240);//テキストを指定の位置に描画

        if (InputKey.getInstance().checkStateKey(SPACE)) {//スペースキーが押されたら
        	return new CarGameMainSequence();
        }
		return this;
	}

}
