package game_ui;

import java.util.HashMap;

import javafx.scene.input.KeyCode;

//キー入力の状態をKeyEventから受け取り保持してくれる
public class InputKey {

	private static InputKey mInstance;

    private HashMap<KeyCode, Integer> map;

	private InputKey(){
		map = new HashMap<KeyCode, Integer>();
	}

	public static InputKey getInstance(){
		if(mInstance==null){
			mInstance = new InputKey();
		}
		return mInstance;
	}

    //キーイベントの内容を自身に格納する(押された)
    public void keyPressed(KeyCode keyCode){
    	//こんなコード書いたらダメです
        map.put(keyCode, 1);
		Game.sleep();
        map.put(keyCode, 0);
    }

    //キーイベントの内容を自身に格納する(離された)
    public void keyReleased(KeyCode keyCode){
        map.put(keyCode, 0);
    }

    //引数のキーの状態を返す
    public boolean checkStateKey(KeyCode keyCode){
        if(map.containsKey(keyCode)){
            return map.get(keyCode)==1;
        }else{
            return false;
        }
    }

}


