/**
 * @author Xiao Biqiang
 * @brief  �������̴��ڣ����������������壬��Ҫ���ڹ涨���̵Ĵ�С��ͼ��ȳ�������
 */
package com.frame;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.config.ChessboardConfig;
import com.events.GomokuFrameDraggedHandle;
import com.panel.GomokuPanel;

public class GomokuFrame extends JFrame{
	private int frameWidth = ChessboardConfig.CHESSBOARD_WIDTH;
	private int frameHeight = ChessboardConfig.CHESSBOARD_HEIGHT;
	private Image iconImg;
	private GomokuPanel gomokuPanel;
	
	public GomokuFrame(){
		super("Gomoku");
		this.setSize(frameWidth, frameHeight);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setFrameIconImg();
		this.setLayout(null);                   //ʹ�þ��Զ�λ
		this.gomokuPanel = new GomokuPanel();
		this.add(gomokuPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.gomokuPanel.addMouseMotionListener(new GomokuFrameDraggedHandle(this));
	}
	public void setFrameIconImg(){
		this.iconImg = Toolkit.getDefaultToolkit().getImage(GomokuFrame.class.getResource("/images/icon.png"));
		this.setIconImage(iconImg);
	}
	
	public int getFrameWidth(){
		return this.frameWidth;
	}
	
	public GomokuPanel getGomokuPanel(){
		return this.gomokuPanel;
	}
}
