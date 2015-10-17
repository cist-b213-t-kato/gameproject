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
    	if(!map.containsKey(keyCode) || map.get(keyCode)==0){
    		map.put(keyCode, Game.getCount());
    	}
    }

    //キーイベントの内容を自身に格納する(離された)
    public void keyReleased(KeyCode keyCode){
        map.put(keyCode, 0);
    }

    //引数のキーの状態を返す
    public boolean checkStateKey(KeyCode keyCode){
    	int cnt = getKeyPressedCount(keyCode);
    	return cnt==1 || cnt>=30 && cnt%2==1;
    }

    //キーが叩かれて何カウント経つか
    public int getKeyPressedCount(KeyCode keyCode){
        if(map.containsKey(keyCode)){
        	if(map.get(keyCode)==0){
        		return 0;
        	}
        	return Game.getCount() - map.get(keyCode);
        }else{
            return 0;
        }
    }

}


