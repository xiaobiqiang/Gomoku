package com.events;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.chess.Chess;
import com.config.ChessStateConfig;
import com.config.ChessboardConfig;
import com.config.GameOverConfig;
import com.config.GoBackConfig;
import com.frame.FrameSetterAndGetter;
import com.gameOver.GameOver;
import com.panel.GomokuPanel;

public class KeyEventsHandle implements KeyListener{
	private GomokuPanel gomokuPanel = null;
	private Image imgQuestion = Toolkit.getDefaultToolkit().getImage(KeyEventsHandle.class.getResource("/images/question_icon.png"));
	private ImageIcon iconQuestion = new ImageIcon(imgQuestion);
	private Image imgError = Toolkit.getDefaultToolkit().getImage(KeyEventsHandle.class.getResource("/images/error_icon.png"));
	private ImageIcon iconError = new ImageIcon(imgError);
	
	public KeyEventsHandle(GomokuPanel gomokuPanel){
		this.gomokuPanel = gomokuPanel;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if((e.getKeyChar() == 'B') || (e.getKeyChar() == 'b')){
			if(ChessStateConfig.chessGoType != 0){
				this.printAndSetBackMsg();
				if(GoBackConfig.chooseOption == JOptionPane.YES_OPTION){
					if(ChessStateConfig.chessGoType == 1)
						GoBackConfig.countBlackBack++;
					if(ChessStateConfig.chessGoType == 2)
						GoBackConfig.countWhiteBack++;
					ChessStateConfig.isGoBack = true;
					this.gomokuPanel.repaint(
							ChessboardConfig.CHESSBOARD_POINTS[Chess.chessRow][Chess.chessCol].x-Chess.CHESS_RADIUS,
							ChessboardConfig.CHESSBOARD_POINTS[Chess.chessRow][Chess.chessCol].y-Chess.CHESS_RADIUS,
							2*Chess.CHESS_RADIUS,
							2*Chess.CHESS_RADIUS
							);
					ChessStateConfig.downChessType[Chess.chessRow][Chess.chessCol] = 0;
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 27){
			FrameSetterAndGetter.getInstance().getMenuFrame().setVisible(true);
			FrameSetterAndGetter.getInstance().getGomokuFrame().dispose();
			GameOverConfig.restartConfig();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	public void printAndSetBackMsg(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if((ChessStateConfig.chessGoType == 1) && (GoBackConfig.countBlackBack > GoBackConfig.MAX_BACK_COUNT)){
				JOptionPane.showMessageDialog(null, "You have done twice!", "Error", JOptionPane.ERROR_MESSAGE, this.iconError);
				GoBackConfig.chooseOption = -1;
			}
			if((ChessStateConfig.chessGoType == 2) && (GoBackConfig.countWhiteBack > GoBackConfig.MAX_BACK_COUNT)){
				JOptionPane.showMessageDialog(null, "You have done twice!", "Error", JOptionPane.ERROR_MESSAGE, this.iconError);
				GoBackConfig.chooseOption = -1;
			}
			if((ChessStateConfig.chessGoType == 1) && (GoBackConfig.countBlackBack <= GoBackConfig.MAX_BACK_COUNT))
				GoBackConfig.chooseOption = JOptionPane.showConfirmDialog(null, "Are you sure to go back?", "Go Back", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, this.iconQuestion);
			if((ChessStateConfig.chessGoType == 2) && (GoBackConfig.countWhiteBack <= GoBackConfig.MAX_BACK_COUNT))
				GoBackConfig.chooseOption = JOptionPane.showConfirmDialog(null, "Are you sure to go back?", "Go Back", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, this.iconQuestion);
		}
	}
}
