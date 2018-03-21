package tetris2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sequence.Sequence;

public class MainSequence implements Sequence {

	@Override
	public Sequence update() {
		draw();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new EndSequence();
	}

	public void draw() {
		System.out.println("MainSequence");
	}

}
