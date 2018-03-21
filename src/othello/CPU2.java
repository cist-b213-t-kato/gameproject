package othello;

import java.util.*;

public class CPU2 {
	

	int color;	//BLACK or WHITE

	public CPU2(){
		color = -1;
	}

	int[] decide(GameState state){

		ArrayList<int[]> array = new ArrayList<int[]>();
	
		//盤面の空マスを置けるかチェック
		for(int y=0; y<8; y++){
			for(int x=0; x<8; x++){

				//すでに駒があるときはパス
				if(state.data[x][y] != 0)
					continue;

				//置けるマスのとき、候補として記憶
				if(state.canReverse(x, y) == true){
					int pos[] = {x,y};
					array.add(pos);
				}

			}
		}
	
		
			int value[][] = {
					{20,   1, 10, 7, 7, 10,   1, 20},
					{ 1, -12,  2, 2, 2,  2, -12,  1},
					{10,   2,  6, 3, 3,  6,   2, 10},
					{ 7,   2,  3, 5, 5,  3,   2,  7},
					{ 7,   2,  3, 5, 5,  3,   2,  7},
					{10,   2,  6, 3, 3,  6,   2, 10},
					{ 1, -12,  2, 2, 2,  2, -12,  1},
					{20,   1, 10, 7, 7, 10,   1, 20}
			};
			//int b;
			ArrayList<Integer> array2 = new ArrayList<Integer>();
		   	int s = value[array.get(0)[0]][array.get(0)[1]];
		   	array2.add(0);
		   	for(int k = 1;k < array.size();k++){
		  int d =  value[array.get(k)[0]][array.get(k)[1]];
			if(s < d){
				s = d;
				array2.clear();
				array2.add(k); 
			}else if(s == d){
				array2.add(k);
			}
		   	}
		
		   	
		   	
			
				if(array2.size() > 1){
					Random rnd = new Random();

				        int ran = rnd.nextInt(array2.size());

					return array.get(array2.get(ran));
				}
					return array.get(array2.get(0));
				
				
				
			
			
	}
}

		
			//int point = 0;
			
			//for (int i = 0; i < 8; i++) {

				//for (int j = 0; j < 8; j++) {

				//point += value[i][j] * a.data[i][j];

				
				
		//}
	//}
//}

	



		//	if(array.size() <= 0){
			//		int pos[] = {-1, -1};
			//		return pos;
			//	}
		//	int index = nextInt(array.size());
		//	
		//return array.get(index);
	//}
//}





