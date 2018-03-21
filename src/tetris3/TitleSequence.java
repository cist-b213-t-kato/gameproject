package tetris3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sequence3.Sequence;

public class TitleSequence extends Sequence {

	@Override
	public void update() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		changeSequence(new MainSequence());
	}

	@Override
	public void draw() {

	}

}
