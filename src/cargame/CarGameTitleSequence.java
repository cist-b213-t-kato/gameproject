package cargame;

import static javafx.scene.input.KeyCode.*;
import game_ui.GameApp;
import game_ui.InputKey;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import moveobject.MoveObject;
import moveobject.Vehicle;
import sequence.Sequence;

public class CarGameTitleSequence implements Sequence{

	GameApp ga;

	public CarGameTitleSequence(GameApp ga){
		this.ga = ga;
	}

	@Override
	public Sequence update() {

        Shape rect = new Rectangle(320, 240, 50, 100);
        rect.setRotate(20);
        rect.setFill(Color.BLUE);
        MoveObject vehicle = new Vehicle(rect);

        Shape rect2 = new Circle(300, 180, 40);//new Rectangle(340, 240, 50, 100);
        rect2.setRotate(20);
//        Image s = new Image("");

//        System.out.println(rect.intersects(rect2.getBoundsInLocal()));

        Shape text = new Text(200, 200, "I love you");

        Platform.runLater(()->{
	        ga.pane.getChildren().add(vehicle.getShape());
	        ga.pane.getChildren().add(rect2);
	        ga.pane.getChildren().add(text);
        });

    	GraphicsContext gc = GameApp.getGC();
		gc.clearRect(0, 0, 640, 480);
        gc.setFill(Color.BLACK);//塗りつぶしの色を黒に
        gc.setFont(new Font("Meiryo", 42));//フォントをメイリオのサイズ42に
        gc.fillText("PRESS SPACEKEY", 150, 240);//テキストを指定の位置に描画

        if (InputKey.getInstance().checkKeyState(SPACE)) {//スペースキーが押されたら
        	return new CarGameMainSequence();
        }
		return this;
	}

}
