package othello;

import java.util.*;

public class GameState extends Observable{

	int data[][];
	int turn;
	int player;
	int black;
	int white;
	
	public GameState(){
		
		data = new int[8][8];
		data[3][3] = 1;
		data[3][4] = -1;
		data[4][3] = -1;
		data[4][4] = 1;

		turn = 0;
		player = 1;
		black = 2;
		white = 2;
	}
	
	public boolean put(int x, int y){
		//すでに駒があるところには置けない
		if(data[x][y] != 0){
			return false;
		}
		//リバースできないところには置けない
		if(reverse(x,y,true)==false){
			return false;
		}
		
		//駒を置く
		data[x][y] = player;
		player *= -1;
		turn++;
		countDisc();
		
		setChanged();
		notifyObservers();
		
		return true;
	}
	
	public boolean reverse(int x,int y, boolean doReverse ){
		int dir[][] = {
				{-1,-1}, {0,-1}, {1,-1},
				{-1, 0},         {1, 0},
				{-1, 1}, {0, 1}, {1, 1}
		};
		
		boolean reversed = false;
		
		for(int i=0; i<8; i++){
			//隣のマス
			int x0 = x+dir[i][0];
			int y0 = y+dir[i][1];
			if(isOut(x0,y0) == true){
				continue;
			}
			int nextState =data[x0][y0];
			if(nextState == player){
				System.out.println("Next state is player: " +x0 +","+ y0);
				continue;
			}else if(nextState == 0){
				System.out.println("Next state is null: " +x0 +","+ y0);
				continue;
			}else{
				System.out.println("Next state is enemy: " +x0 +","+ y0);
			}
			
			//隣の隣から端まで走査して、自分の色があればリバース
			int j = 2;
			while(true){
			
				int x1 = x + (dir[i][0]*j);
				int y1 = y + (dir[i][1]*j);
				if(isOut(x1,y1) == true){
					break;
				}
				
				//自分の駒があったら、リバース
				if(data[x1][y1]==player){
					System.out.println("Player cell!: " +x1 +","+ y1);
					
					if(doReverse){
						for(int k=1; k<j; k++){
							int x2 = x + (dir[i][0]*k);
							int y2 = y + (dir[i][1]*k);
							data[x2][y2] *= -1;
							System.out.println("reverse: " +x2 +","+ y2);
						}
					}
					reversed = true;
					break;
				}
				
				//空白があったら、終了
				if(data[x1][y1]==0){
					break;
				}
				
				j++;
				
			}
			
		}
		
		return reversed;
	}
	
	
	
	public boolean canReverse(int x, int y){
		return reverse(x, y, false);
	}
	
	public boolean isOut(int x, int y){
		if(x<0 || y<0 || x>=8 || y>=8){
			return true;
		}
		return false;
	}
	
	public boolean checkPass(){
		
		//コピーデータの全升目に対して、リバースできるかチェック
		for(int y=0; y<8; y++){
			for(int x=0; x<8; x++){
				
				//すでに駒があるところはチェックしない
				if(data[x][y] != 0){
					continue;
				}
				
				//リバースできる（した）とき、元に戻してfalseを返す
				if(canReverse(x,y) == true){
					return false;
				}
				
			}
		}
		
		return true;
	}
	
	public void countDisc(){
		
		black = 0;
		white = 0;
		
		for(int y=0; y<8; y++){
			for(int x=0; x<8; x++){
				if(data[x][y] == 1){
					black++;
				}else if(data[x][y] == -1){
					white++;
				}
			}
		}
	}
}
