package daifugo;

import java.util.ArrayList;
import java.util.List;

class Actor {
	public String name;
	public List<Integer> cards = new ArrayList<>();
	public boolean isWinFlag;

	public Actor(String nm) {
		name = nm;
	}

	public void run() {
		if (!cards.isEmpty()) {
			cpumind();
			DaifugoFSS.field(DaifugoFSS.p);
		}
		if (cards.isEmpty()) {
			cpuvicordef();
		}
	}

	public void cpumind() {
		List<Integer> a = cards;
		// CPUの思考について
		// 現在は大きいものを持っているとただ出すだけ
		System.out.print(name + "　　");

		for (int i = 0; i < a.size(); i++) {// CPUは場札よりも１でも大きいものを出す
			if (DaifugoFSS.p < a.get(i)) {
				DaifugoFSS.p = a.get(i);
				switch (DaifugoFSS.p) {
				case 14:
					System.out.println("1");
					break;
				case 15:
					System.out.println("2");
					break;
				default:
					System.out.println(DaifugoFSS.p);
					break;
				}

				DaifugoFSS.pass = 0;
				a.remove(i);
				break;
			} else if (i + 1 == a.size()) {// もしもっていないならPASS
				DaifugoFSS.pass();
			}
		}
	}

	public void cpuvicordef() {
		// CPUの勝敗判定について
		if (isWinFlag == false) {
			isWinFlag = true;
			System.out.println(name + " is No." + DaifugoFSS.number);
			DaifugoFSS.number++;
			DaifugoFSS.member--;
		}
	}

	public void showCards() {
		for (Integer card : cards) {
			// プレイヤーの手札表示
			switch (card) {
			case 14:
				System.out.print(1 + "  ");
				break;
			case 15:
				System.out.print(2 + "  ");
				break;
			default:
				System.out.print(card + "  ");
				break;
			}
		}
		System.out.println();

	}

	public void putcard(int i) {
		// カードを場に出す
		DaifugoFSS.p = cards.get(i);
		DaifugoFSS.pass = 0;
		cards.remove(i);
	}

}