package daifugo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DaifugoActor {
	private List<Integer> cards = new ArrayList<>();
	public List<Integer> getCards(){
		cards = cards.stream().map((a)->{
			if(a==1){
				return 14;
			}else if(a==2){
				return 15;
			}else{
				return a;
			}
		}).collect(Collectors.toList());
		Collections.sort(cards, (o1,o2)->o1-o2);
		return cards;
	}

	public String getName() {
		return "noName";
	}

	public static List<String> cardsToString(List<Integer> cs) {
		return cs.stream().map((o) -> {
			String s = "";
			if (o < 10) {
				s = o.toString();
			} else if (o.equals(11)) {
				s = "J";
			} else if (o.equals(12)) {
				s = "Q";
			} else if (o.equals(13)) {
				s = "K";
			} else if (o.equals(14)) {
				s = "A";
			} else if (o.equals(15)) {
				s = "2";
			}
			return s;
		}).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		DaifugoActor playerActor = new DaifugoActor();
		playerActor.getCards().add(3);
		playerActor.getCards().add(3);
		playerActor.getCards().add(2);
		playerActor.getCards().add(11);
		playerActor.getCards().add(9);

		for(String s : DaifugoActor.cardsToString(playerActor.getCards())){
			System.out.print(s+" ");
		}
		System.out.println();

		List<Integer> putCards  = new ArrayList<>();

		putCards.add(playerActor.getCards().remove(0));
		putCards.add(playerActor.getCards().remove(0));

		for(String s : DaifugoActor.cardsToString(putCards)){
			System.out.print(s+" ");
		}
		System.out.println();





	}

}
