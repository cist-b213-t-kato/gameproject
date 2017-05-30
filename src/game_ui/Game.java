package game_ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

	private static MediaPlayer player;

	public static void mediaplay(String p){
		Path path = Paths.get(p);
		String movieUri = path.toUri().toString();
		Media media = new Media(movieUri);

		if(player!=null){
			player.stop();
		}

		player = new MediaPlayer(media);
		GameApp.view.setMediaPlayer(player);
		player.setCycleCount(Integer.MAX_VALUE);
		player.play();
	}

	public static void mediaStop(){
		player.stop();
	}

	private static int count = 0;

	public static int getCount(){
		return count;
	}

	public static void loopEnd(){
		++count;
		try {
			Thread.sleep(1000/60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	    	if(map.containsKey(keyCode)==false || map.get(keyCode)==0){
	    		map.put(keyCode, Game.getCount());
	    	}
	    }

	    //キーイベントの内容を自身に格納する(離された)
	    public void keyReleased(KeyCode keyCode){
	        map.put(keyCode, 0);
	    }

	    //引数のキーの状態を返す
	    public boolean checkStateKey(KeyCode keyCode){
	    	if(map.containsKey(keyCode)==false || map.get(keyCode)==0){
	    		return false;
	    	}else{
		    	int cnt = Game.getCount() - keyPressedCount(keyCode);
		    	return cnt==1 || cnt>=30 && cnt%2==0;
	    	}
	    }

	    public int keyPressedCount(KeyCode keyCode){
    		return map.getOrDefault(keyCode, 0);
	    }

	}


}

