/**
 * @author Xiao Biqiang
 * @time   2017/5/16
 * @brief  此事件处理程序专门用于处理按钮事件，与按钮工厂对应
 */
package com.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.config.ChessStateConfig;
import com.config.GameOverConfig;
import com.frame.FrameSetterAndGetter;
import com.progressBar.LoadProgressBar;

public class MouseEventOnButtonHandleFactory implements MouseListener{
	private String buttonName;
	private boolean isPCFirst = true;    //用于判断是否是第一次开始单机双人对战
//	private MenuFrame menuFrame;
//	private GomokuFrame gomokuFrame;
//	private GomokuPanel gomokupanel;
	private LoadProgressBar progressBar;
	
	public MouseEventOnButtonHandleFactory(String buttonName){
		super();
		this.buttonName = buttonName;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			if("Exit".equalsIgnoreCase(this.buttonName)){
				System.exit(0);
			}
			if("Double_Player_PC".equalsIgnoreCase(this.buttonName)){
				if(isPCFirst){
					progressBar = new LoadProgressBar(0, 100);
					FrameSetterAndGetter.getInstance().getMenuFrame().add(progressBar);
					isPCFirst = false;
				}
//				LoadProgressBar progressBar = new LoadProgressBar(0, 100);
//				FrameSetterAndGetter.getInstance().getMenuFrame().add(progressBar);
				new Thread(progressBar).start();
//				FrameSetterAndGetter.getInstance().getMenuFrame().dispose();
//				FrameSetterAndGetter.getInstance().setGomokuFrame(new GomokuFrame());
			}
			if("Double_Player_DC".equalsIgnoreCase(this.buttonName)){
				FrameSetterAndGetter.getInstance().getMenuFrame().dispose();
			}
			if("Start".equalsIgnoreCase(this.buttonName)){
				ChessStateConfig.isGameStart = true;
				FrameSetterAndGetter.getInstance().getGomokuFrame().getGomokuPanel().requestFocus();  //开始游戏之后进入棋盘面板，由于JPanel默认是不可获取焦点，因而只能请求到焦点后才能检测到按键
			}
			if("Menu".equalsIgnoreCase(buttonName)){
				FrameSetterAndGetter.getInstance().getGomokuFrame().dispose();
				FrameSetterAndGetter.getInstance().getMenuFrame().setVisible(true);
				GameOverConfig.restartConfig();
			}
			if("Restart".equalsIgnoreCase(buttonName)){
				GameOverConfig.restartConfig();
				FrameSetterAndGetter.getInstance().getGomokuFrame().getGomokuPanel().repaint();
			}
//			if("Back_Step".equalsIgnoreCase(buttonName)){
//				if(ChessStateConfig.chessGoType == 1)
//					GoBackConfig.countBlackBack++;
//				if(ChessStateConfig.chessGoType == 2)
//					GoBackConfig.countWhiteBack++;
//				if(((ChessStateConfig.chessGoType == 1)&&(GoBackConfig.countBlackBack<=GoBackConfig.MAX_BACK_COUNT)) || 
//				   ((ChessStateConfig.chessGoType == 2)&&(GoBackConfig.countWhiteBack<=GoBackConfig.MAX_BACK_COUNT))){
//					ChessStateConfig.isGoBack = true;
//					this.gomokupanel.repaint(
//							ChessboardConfig.CHESSBOARD_POINTS[Chess.chessRow][Chess.chessCol].x-Chess.CHESS_RADIUS,
//							ChessboardConfig.CHESSBOARD_POINTS[Chess.chessRow][Chess.chessCol].y-Chess.CHESS_RADIUS,
//							2*Chess.CHESS_RADIUS,
//							2*Chess.CHESS_RADIUS
//							);
//					ChessStateConfig.downChessType[Chess.chessRow][Chess.chessCol] = 0;
//				}
//			}
		}
	}
	
//	public void setGomokuPanel(GomokuPanel pane){
//		this.gomokupanel = pane;
//	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
