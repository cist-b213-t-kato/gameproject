package cargamesample;

import java.util.HashMap;

import javafx.scene.input.KeyCode;

//キー入力の状態をKeyEventから受け取り保持してくれる
public class InputKey {

	private static InputKey mInstance;

	public static InputKey getInstance(){
		if(mInstance==null){
			mInstance = new InputKey();
		}
		return mInstance;
	}

	private InputKey(){}

    private HashMap<KeyCode, Boolean> map = new HashMap<KeyCode, Boolean>();

    //キーイベントの内容を自身に格納する(押された)
    public void keyPressed(KeyCode keyCode){
        map.put(keyCode, true);
    }

    //キーイベントの内容を自身に格納する(離された)
    public void keyReleased(KeyCode keyCode){
        map.put(keyCode, false);
    }

    //引数のキーの状態を返す
    public boolean checkStateKey(KeyCode keyCode){
        if(map.containsKey(keyCode)){
            return map.get(keyCode);
        }else{
            return false;
        }
    }
}


