/**
 * @author Xiao Biqiang
 * @time   2017/5/16
 * @brief  此鼠标事件用于处理下棋过程，主要是判断下棋是否规范，该下哪一种棋子等工作
 */
package com.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.chess.Chess;
import com.config.ChessStateConfig;
import com.config.ChessboardConfig;
import com.gameOver.GameOver;
import com.panel.GomokuPanel;

public final class MouseEventsHandle implements MouseListener{
	private int mouseRow = 0;
	private int mouseCol = 0;
	private GomokuPanel gomokuPanel = null;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//如果游戏开始并且左键按下
		if(ChessStateConfig.isGameStart && (e.getButton() == MouseEvent.BUTTON1)){
			//判断是否是悔棋
			if(ChessStateConfig.isGoBack == true){
				ChessStateConfig.isGoBack = false;
				ChessStateConfig.isWhiteDown = !ChessStateConfig.isWhiteDown;  //根据这个来判断下黑棋还是白棋，因而悔棋要把这个取反，让本来该下黑棋下白棋，该下白棋下黑棋
			}
			//把鼠标点击的位置转换为棋盘的行列
			this.transXYtoRowCol(e.getX(), e.getY());
			//不允许下到第一行、最后一行、第一列和最后一列，并且这个地方还没有下过棋子（==0）
			if((this.mouseRow>0) && (this.mouseRow<20) && (this.mouseCol>0) && (this.mouseCol<20) && (ChessStateConfig.downChessType[this.mouseRow][this.mouseCol] == 0)){
				if(ChessStateConfig.isWhiteDown){
					ChessStateConfig.chessGoType = 1;    //1代表黑棋
					ChessStateConfig.downChessType[this.mouseRow][this.mouseCol] = 1;  //把这个位置标记为已下黑棋状态
					ChessStateConfig.isWhiteDown = false;
				}else{
					ChessStateConfig.chessGoType = 2;    //2代表白棋
					ChessStateConfig.downChessType[this.mouseRow][this.mouseCol] = 2;  //把这个位置标记为已下白棋状态
					ChessStateConfig.isWhiteDown = true;
				}
				//保存了当前棋子的位置
				Chess.chessRow = this.mouseRow;
				Chess.chessCol = this.mouseCol;
				//重绘将要下棋的区域
				this.gomokuPanel.repaint(
						ChessboardConfig.CHESSBOARD_POINTS[this.mouseRow][this.mouseCol].x-Chess.CHESS_RADIUS,
						ChessboardConfig.CHESSBOARD_POINTS[this.mouseRow][this.mouseCol].y-Chess.CHESS_RADIUS,
						2*Chess.CHESS_RADIUS,
						2*Chess.CHESS_RADIUS
						);
				//检测游戏是否结束
				GameOver gameOver = new GameOver(this.mouseRow, this.mouseCol, this.gomokuPanel);
				Thread gameOverThread = new Thread(gameOver);
				gameOverThread.start();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
	
	private void transXYtoRowCol(int x, int y){
		float ff1 = (x-ChessboardConfig.LTX)/(float)ChessboardConfig.INTERVAL;
		int nff1 = (int)ff1;
		float fff1 = ff1-nff1;
		this.mouseCol = nff1 + (fff1>0.5 ? 1:0);
		ff1 = (y-ChessboardConfig.LTY)/(float)ChessboardConfig.INTERVAL;
		nff1 = (int)ff1;
		fff1 = ff1-nff1;
		this.mouseRow = nff1 + (fff1>0.5?1:0);
	}
	
	public void setGomokuPanel(GomokuPanel panel){
		this.gomokuPanel = panel;
	}
}
