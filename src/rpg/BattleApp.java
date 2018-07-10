package rpg;

import java.util.ArrayList;
import java.util.List;

public class BattleApp {

	public BattleApp(){
        List<Unit> partyList = new ArrayList<>();
        List<Unit> enemyList = new ArrayList<>();

        enemyList.add(new Unit("遠野", 300, 100, 25));
        partyList.add(new Unit("野獣先輩", 220, 34, 35){
        	@Override
            public void causeDamage() {
                if(hitPoint.getValue()<=0){
                    System.out.println(name+"「お前のことが好きだったんだよ…（遺言）」");
                    System.out.println(name+"は生命活動を停止…死んだのだ");
                }else if(hitPoint.getValue()<=hitPoint.getMaxValue()/9){
                    System.out.println(name+"「ｱｰｲｷｿ」");
                }else{
                    System.out.println(name+"「やりますねえ！（賞賛）」");
                }
            }
        });

        //コマンド入力
        enemyList.get(0).setCommand(new CounterAttackCommandEvent(partyList.get(0)));
        partyList.get(0).setCommand(new AttackCommandEvent(partyList.get(0)));

        List<Unit> battleMember = new ArrayList<>();
        battleMember.addAll(partyList);
        battleMember.addAll(enemyList);
        for(int i=1; i<=5; i++){
	        //すばやさ順にソートし全てコマンドさせる
	        battleMember.sort((u1,u2)->(u2.agility.getValue()-u1.agility.getValue()));
	        battleMember.forEach(u->{
	        	u.execute();
	    		System.out.println();
	        });
	        if ( !partyList.stream().anyMatch(u->u.hitPoint.getValue()>0) ) {
	        	System.out.println("全滅した");
	        	break;
	        } else if ( !enemyList.stream().anyMatch(u->u.hitPoint.getValue()>0) ) {
	        	System.out.println("勝利した");
	        	break;
	        }
        }
	}

    public static void main(String[] args){
    	new BattleApp();

    }
}
