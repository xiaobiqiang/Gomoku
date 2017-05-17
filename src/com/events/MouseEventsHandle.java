/**
 * @author Xiao Biqiang
 * @time   2017/5/16
 * @brief  ������¼����ڴ���������̣���Ҫ���ж������Ƿ�淶��������һ�����ӵȹ���
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
		//�����Ϸ��ʼ�����������
		if(ChessStateConfig.isGameStart && (e.getButton() == MouseEvent.BUTTON1)){
			//�ж��Ƿ��ǻ���
			if(ChessStateConfig.isGoBack == true){
				ChessStateConfig.isGoBack = false;
				ChessStateConfig.isWhiteDown = !ChessStateConfig.isWhiteDown;  //����������ж��º��廹�ǰ��壬�������Ҫ�����ȡ�����ñ������º����°��壬���°����º���
			}
			//���������λ��ת��Ϊ���̵�����
			this.transXYtoRowCol(e.getX(), e.getY());
			//�������µ���һ�С����һ�С���һ�к����һ�У���������ط���û���¹����ӣ�==0��
			if((this.mouseRow>0) && (this.mouseRow<20) && (this.mouseCol>0) && (this.mouseCol<20) && (ChessStateConfig.downChessType[this.mouseRow][this.mouseCol] == 0)){
				if(ChessStateConfig.isWhiteDown){
					ChessStateConfig.chessGoType = 1;    //1�������
					ChessStateConfig.downChessType[this.mouseRow][this.mouseCol] = 1;  //�����λ�ñ��Ϊ���º���״̬
					ChessStateConfig.isWhiteDown = false;
				}else{
					ChessStateConfig.chessGoType = 2;    //2�������
					ChessStateConfig.downChessType[this.mouseRow][this.mouseCol] = 2;  //�����λ�ñ��Ϊ���°���״̬
					ChessStateConfig.isWhiteDown = true;
				}
				//�����˵�ǰ���ӵ�λ��
				Chess.chessRow = this.mouseRow;
				Chess.chessCol = this.mouseCol;
				//�ػ潫Ҫ���������
				this.gomokuPanel.repaint(
						ChessboardConfig.CHESSBOARD_POINTS[this.mouseRow][this.mouseCol].x-Chess.CHESS_RADIUS,
						ChessboardConfig.CHESSBOARD_POINTS[this.mouseRow][this.mouseCol].y-Chess.CHESS_RADIUS,
						2*Chess.CHESS_RADIUS,
						2*Chess.CHESS_RADIUS
						);
				//�����Ϸ�Ƿ����
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
