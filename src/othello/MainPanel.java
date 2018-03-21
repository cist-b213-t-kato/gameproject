package othello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainPanel extends JPanel implements MouseListener, Observer{

	static final int SIZE = 50;
	static final int W = SIZE * 10;
	static final int H = SIZE * 8;
	
	GameState state = new GameState();
	CPU2 cpu = new CPU2();
	
	public MainPanel(){
		setPreferredSize(new Dimension(W,H));
		addMouseListener(this);
		
		state.addObserver(this);
	}
	
	public void paintComponent(Graphics g){
		
		//背景
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, W, H);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(400, 0, W, H);
		//線
		g.setColor(Color.BLACK);
		for(int i=0; i<8; i++){
			g.drawLine(0, i*SIZE, 400, i*SIZE);
			g.drawLine(i*SIZE, 0, i*SIZE, H);
		}
		g.setColor(Color.DARK_GRAY);
		//g.drawRect(SIZE*2, SIZE*2, SIZE*4, SIZE*4);
		
		//駒
		for(int y=0; y<8; y++){
			for(int x=0; x<8; x++){
				if(state.data[x][y] == 1){
					g.setColor(Color.BLACK);
					g.fillOval(x*SIZE, y*SIZE, SIZE, SIZE);
				}else if(state.data[x][y] == -1){
					g.setColor(Color.WHITE);
					g.fillOval(x*SIZE, y*SIZE, SIZE, SIZE);
				}
			}
		}
		
		//データ表示
		g.setColor(Color.BLACK);
		g.drawString("TURN = "+state.turn, 410, 30);
		g.drawString("PLAYER = "+state.player, 410, 50);
		g.drawString("DISC = "+state.black+" : " +state.white, 410, 70);
		
	
		
	}
	
	public void update(Observable o, Object arg){
		repaint();
	}
	
	public void mousePressed(MouseEvent e){

		int x = e.getX();
		int y = e.getY();
		
		x /= SIZE;
		y /= SIZE;
		System.out.println("(x,y)=("+x+","+y+")");
		
		//駒を置く＆置けない場合はメッセージ
		if( state.put(x,y) == false ){
			JOptionPane.showMessageDialog(this, "Can't put this area!");
		}
		
		//盤面が埋まったら終了
		if(state.turn == 60){
			JOptionPane.showMessageDialog(this, "End!");
//			MainPanel panel = new MainPanel();
//			panel.removeAll();
//			panel.paintComponent(g);
		}
		//パスチェック
		else if( state.checkPass() == true ){
			state.player *= -1;
			JOptionPane.showMessageDialog(this, "Pass! Next turn is "+state.player);
		}
		
		//CPUのターン
		if(state.player == cpu.color && state.turn != 60){
			
			int action[] = cpu.decide(state);
			if(action[0] != -1)
				state.put(action[0], action[1]);
			
			//盤面が埋まったら終了
			if(state.turn == 60){
				JOptionPane.showMessageDialog(this, "End!");
			}
			//パスチェック
			else if( state.checkPass() == true ){
				JOptionPane.showMessageDialog(this, "Pass! Next turn is "+state.turn);
			}
		}
	}
	
	public void mouseClicked(MouseEvent e){

	}
	public void mouseReleased(MouseEvent e){

	}
	public void mouseEntered(MouseEvent e){

	}
	public void mouseExited(MouseEvent e){

	}
}
