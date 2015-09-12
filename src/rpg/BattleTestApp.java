package rpg;

import java.util.ArrayList;
import java.util.List;

import rpg.event.Event;

public class BattleTestApp {

	BattleTestApp(){
        //Unitを作る
        List<Unit> list = new ArrayList<>();
        list.add(new Unit("秋吉", 300, 100, 25));
//        list.add(new Unit("魔法使い", 20, 30));
        list.add(new Unit("野獣先輩", 220, 34, 35){
            public void acceptEvent(Event e) {
            	if(e instanceof AttackCommandEvent){
                    if(hitPoint.getValue()<=0){
                        System.out.println(name+"「お前のことが好きだったんだよ…（遺言）」");
                        System.out.println(name+"は生命活動を停止…死んだのだ");
                    }else if(hitPoint.getValue()<=hitPoint.getMaxValue()/9){
                        System.out.println(name+"「ｱｰｲｷｿ」");
                    }else{
                        System.out.println(name+"「やりますねえ！（賞賛）」");
                    }
            	}
            }
        });

        System.out.println();

        //コマンド入力
        list.get(0).setCommand(new CounterAttackCommandEvent(list.get(1)));
        list.get(1).setCommand(new AttackCommandEvent(list.get(0)));

        for(int i=1; i<=3; i++){
	        //すばやさ順にソートし全てコマンドさせる
	        List<Unit> battleMember = new ArrayList<>(list);
	        battleMember.sort((u1,u2)->(u2.agility.getValue()-u1.agility.getValue()));
	        battleMember.forEach(u->{
	        	u.execute();
	    		System.out.println();
	        });
        }
	}

    public static void main(String[] args){
    	new BattleTestApp();

    }
}
