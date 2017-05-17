/**
 * @author Xiao Biqiang
 * @time   2017/5/16
 * @brief  此线程用于检测游戏是否结束，若结束，根据用户的选择标记某些标志位并做出处理
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
		//如果游戏还没有胜利方
		if(ChessStateConfig.chessWinType == 0)
			this.checkLeftRight();
		if(ChessStateConfig.chessWinType == 0)
			this.checkTopButtom();
		if(ChessStateConfig.chessWinType == 0)
			this.checkDownward();
		if(ChessStateConfig.chessWinType == 0)
			this.checkUpward();
		//如果游戏产生了胜利方
		if(ChessStateConfig.chessWinType != 0)
			this.showGameOverInfo();
		if((ChessStateConfig.chessWinType != 0) && (GameOverConfig.chooseOption != JOptionPane.YES_OPTION))
		{
			FrameSetterAndGetter.getInstance().getMenuFrame().setVisible(true);  //恢复菜单窗口并且显示
			GameOverConfig.restartConfig();          //重置游戏数据以便下次重新开始
			FrameSetterAndGetter.getInstance().getGomokuFrame().dispose();       //把棋盘窗口消失
		}
		else if(GameOverConfig.chooseOption == JOptionPane.YES_OPTION){
			GameOverConfig.restartConfig();
			this.repaintPanel.repaint();
		}
	}
	
	//检测水平方向是否达成5颗棋子，
	//方法：已当前棋子为起点，分别检测其左边和右边与该棋子连续相同的数目，若达到5颗，游戏结束
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
