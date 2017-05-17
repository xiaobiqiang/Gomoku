/**
 * @author Xiao Biqiang
 * @time   2017/5/16
 * @brief  ���߳����ڼ����Ϸ�Ƿ�������������������û���ѡ����ĳЩ��־λ����������
 */
package com.gameOver;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.config.ChessStateConfig;
import com.config.GameOverConfig;
import com.frame.FrameSetterAndGetter;
import com.panel.GomokuPanel;

public class GameOver implements Runnable{
	private int mouseRow = 0;
	private int mouseCol = 0;
	private int sameChessNum = 1;
	private GomokuPanel repaintPanel = null;
	
	public GameOver(int row, int col, GomokuPanel repaintPanel){
		this.mouseRow = row;
		this.mouseCol = col;
		this.repaintPanel = repaintPanel;
	}
	
	@Override
	public void run() {
		//�����Ϸ��û��ʤ����
		if(ChessStateConfig.chessWinType == 0)
			this.checkLeftRight();
		if(ChessStateConfig.chessWinType == 0)
			this.checkTopButtom();
		if(ChessStateConfig.chessWinType == 0)
			this.checkDownward();
		if(ChessStateConfig.chessWinType == 0)
			this.checkUpward();
		//�����Ϸ������ʤ����
		if(ChessStateConfig.chessWinType != 0)
			this.showGameOverInfo();
		if((ChessStateConfig.chessWinType != 0) && (GameOverConfig.chooseOption != JOptionPane.YES_OPTION))
		{
			FrameSetterAndGetter.getInstance().getMenuFrame().setVisible(true);  //�ָ��˵����ڲ�����ʾ
			GameOverConfig.restartConfig();          //������Ϸ�����Ա��´����¿�ʼ
			FrameSetterAndGetter.getInstance().getGomokuFrame().dispose();       //�����̴�����ʧ
		}
		else if(GameOverConfig.chooseOption == JOptionPane.YES_OPTION){
			GameOverConfig.restartConfig();
			this.repaintPanel.repaint();
		}
	}
	
	//���ˮƽ�����Ƿ���5�����ӣ�
	//�������ѵ�ǰ����Ϊ��㣬�ֱ�������ߺ��ұ��������������ͬ����Ŀ�����ﵽ5�ţ���Ϸ����
	public void checkLeftRight(){	
		int i = 1;
		while((ChessStateConfig.chessWinType == 0) && 
			  ((this.mouseCol-i) > 0) &&
			  (ChessStateConfig.downChessType[this.mouseRow][this.mouseCol-i] == ChessStateConfig.downChessType[this.mouseRow][this.mouseCol-i+1])){
			i++;
			this.sameChessNum++;
		}
		i = 1;
		while((ChessStateConfig.chessWinType == 0) && 
			  ((this.mouseCol+i) < 20) &&
			  (ChessStateConfig.downChessType[this.mouseRow][this.mouseCol+i] == ChessStateConfig.downChessType[this.mouseRow][this.mouseCol+i-1])){
			i++;
			this.sameChessNum++;
		}
		if(this.sameChessNum < 5)
			this.sameChessNum = 1;
		else
			ChessStateConfig.chessWinType = ChessStateConfig.downChessType[this.mouseRow][this.mouseCol];
	}
	public void checkTopButtom(){
		int i = 1;
		while((ChessStateConfig.chessWinType == 0) && 
			  ((this.mouseRow-i) > 0) && 
			  (ChessStateConfig.downChessType[this.mouseRow-i][this.mouseCol] == ChessStateConfig.downChessType[this.mouseRow-i+1][this.mouseCol])){
			i++;
			this.sameChessNum++;
		}
		i = 1;
		while((ChessStateConfig.chessWinType == 0) && 
			  ((this.mouseRow+i) < 20) && 
			  (ChessStateConfig.downChessType[this.mouseRow+i][this.mouseCol] == ChessStateConfig.downChessType[this.mouseRow+i-1][this.mouseCol])){
			i++;
			this.sameChessNum++;
		}
		if(this.sameChessNum < 5)
			this.sameChessNum = 1;
		else
			ChessStateConfig.chessWinType = ChessStateConfig.downChessType[this.mouseRow][this.mouseCol];
	}
	public void checkUpward(){
		int i = 1;
		while((ChessStateConfig.chessWinType == 0) && 
			  ((this.mouseRow+i) < 20) && ((this.mouseCol-i) > 0) &&
			  (ChessStateConfig.downChessType[this.mouseRow+i][this.mouseCol-i] == ChessStateConfig.downChessType[this.mouseRow+i-1][this.mouseCol-i+1])){
			i++;
			this.sameChessNum++;
		}
		i = 1;
		while((ChessStateConfig.chessWinType == 0) && 
			  ((this.mouseRow-i) > 0) && ((this.mouseCol+i) < 20) &&
			  (ChessStateConfig.downChessType[this.mouseRow-i][this.mouseCol+i] == ChessStateConfig.downChessType[this.mouseRow-i+1][this.mouseCol+i-1])){
			i++;
			this.sameChessNum++;
		}
		if(this.sameChessNum < 5)
			this.sameChessNum = 1;
		else
			ChessStateConfig.chessWinType = ChessStateConfig.downChessType[this.mouseRow][this.mouseCol];
	}
	public void checkDownward(){
		int i = 1;
		while((ChessStateConfig.chessWinType == 0) && 
			  ((this.mouseRow-i) > 0) && ((this.mouseCol-i) > 0) &&
			  (ChessStateConfig.downChessType[this.mouseRow-i][this.mouseCol-i] == ChessStateConfig.downChessType[this.mouseRow-i+1][this.mouseCol-i+1])){
			i++;
			this.sameChessNum++;
		}
		i = 1;
		while((ChessStateConfig.chessWinType == 0) && 
			  ((this.mouseRow+i) < 20) && ((this.mouseCol+i) < 20) &&
			  (ChessStateConfig.downChessType[this.mouseRow+i][this.mouseCol+i] == ChessStateConfig.downChessType[this.mouseRow+i-1][this.mouseCol+i-1])){
			i++;
			this.sameChessNum++;
		}
		if(this.sameChessNum < 5)
			this.sameChessNum = 1;
		else
			ChessStateConfig.chessWinType = ChessStateConfig.downChessType[this.mouseRow][this.mouseCol];
	}
	
	public void showGameOverInfo(){
		Image imgOver = Toolkit.getDefaultToolkit().getImage(GameOver.class.getResource("/images/Over.png"));
		ImageIcon iconOver = new ImageIcon(imgOver);
		Object[] options = {"Again", "Menu"};
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} finally { 
			if(ChessStateConfig.chessWinType == 1){
				GameOverConfig.chooseOption = JOptionPane.showOptionDialog(null, "Black Chess Win!", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, iconOver, options, options[0]);
			}
			if(ChessStateConfig.chessWinType == 2){
				GameOverConfig.chooseOption = JOptionPane.showOptionDialog(null, "White Chess Win!", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, iconOver, options, options[0]);
			}
		}
	}
//	public static void restartConfig(){
//		ChessStateConfig.chessWinType = 0;
//		ChessStateConfig.chessGoType = 0;
//		ChessStateConfig.isWhiteDown = true;
//		for(int i=0; i<ChessStateConfig.downChessType.length; i++){
//			for(int j=0; j<ChessStateConfig.downChessType[i].length; j++)
//			{
//				ChessStateConfig.downChessType[i][j] = 0;
//			}
//		}
//		GameOverConfig.chooseOption = -1;
//		GoBackConfig.chooseOption = -1;
//		GoBackConfig.countBlackBack = 1;
//		GoBackConfig.countWhiteBack = 1;
//	}
}
