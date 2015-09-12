package daifugo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaifugoFSS {

	static int p = 0;
	static int pass = 0;// pass数のカウント
	static int number = 1, member = 4;// 順位と残っている人数

	Actor you = new Actor("you");
	Actor cpu1 = new Actor("cpu1");
	Actor cpu2 = new Actor("cpu2");
	Actor cpu3 = new Actor("cpu3");

	public DaifugoFSS() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 全員に手札を配る
		List<Integer> list = new ArrayList<>();// 山札
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j <= 15; j++) {
				// list.add(i * 100 + j);//こっちはスートを使う場合（スペード、ダイヤ、ハート、クローバー）
				list.add(j);// 山札にカードを入れる（3-15×4）
				// なんで3-15なのかはソートが楽だから
				// 一応操作は14や15でも1や2でも出来るようにしてる
			}
		}
		Collections.shuffle(list);// 山札シャッフル
		while (!list.isEmpty()) {
			you.cards.add(list.remove(0));
			cpu1.cards.add(list.remove(0));
			cpu2.cards.add(list.remove(0));
			cpu3.cards.add(list.remove(0));
		}

		// 手札を並び替える
		you.cards.sort((i1, i2) -> (i1 - i2));
		cpu1.cards.sort((i1, i2) -> (i1 - i2));
		cpu2.cards.sort((i1, i2) -> (i1 - i2));
		cpu3.cards.sort((i1, i2) -> (i1 - i2));

		// ここからpを場札として扱うので初期化
		p = 0;
		String str1 = "";
		Integer number = 0;

		while(true){
			you.showCards();
			System.out.println("何を出しますか？( pass と打つとパスします )");
			str1 = br.readLine();// scanf

			if(str1.equals("pass")){
				pass();
				cpu1.run();
				cpu2.run();
				cpu3.run();
				continue;
			}

			try{
				number = Integer.parseInt(str1);
			}catch(NumberFormatException e){
				System.out.println("不適切な値が入力されました");
				System.out.println("もう一度入力してください");
				continue;
			}

			if(str1.equals("1")){
				number = 14;
			}else if(str1.equals("2")){
				number = 15;
			}

			if (number <= p) {
				System.out.println("だせません");// 大富豪のルール的に場札より小さい数字は出せません
				System.out.println("もう一度入力してください");
				continue;
			}

			if(you.cards.contains(number)){//ありますねえ！（歓喜）
				you.putcard(you.cards.indexOf(number));
			}else{//ないです（食い気味）
				System.out.println("だせません");// 大富豪のルール的に場札より小さい数字は出せません
				System.out.println("もう一度入力してください");
				continue;
			}

			cpu1.run();
			cpu2.run();
			cpu3.run();

		}

	}

	public static void main(String[] args) throws IOException {// mainの開始
		new DaifugoFSS();

	}

	public static void field(int p) {
		// 場札の表示
		switch (p) {
		case 0:
			System.out.println("場札なし");
			break;
		case 14:
			System.out.println("場札" + 1);
			break;
		case 15:
			System.out.println("場札" + 2);
			break;
		default:
			System.out.println("場札" + p);
			break;
		}
	}

	public static void pass() {
		// パスしたときの動作
		System.out.println("PASS");
		pass++;
		if (pass >= member - 1) {
			pass = 0;
			p = 0;
		}
	}

}
