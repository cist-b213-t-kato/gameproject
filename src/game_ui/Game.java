package game_ui;

import java.util.HashMap;

import javafx.scene.input.KeyCode;
import sequence.Sequence;

public abstract class Game implements Runnable {

	public abstract Sequence getPrimarySequence();

	@Override
	public void run() {

		Sequence seq = getPrimarySequence();
		while (true) {
			System.out.println(seq);
			seq = seq.update();
			Game.loopEnd();
		}

	}

	private static int count;

	public static void loopEnd(){
		++count;
		try {
			Thread.sleep(1000/60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int getCount() {
		return count;
	}


	public static class InputKey {

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
	    public boolean checkKeyState(KeyCode keyCode){
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

	    public void resetKeyState(){
			map = new HashMap<KeyCode, Integer>();
	    }

	}


}

