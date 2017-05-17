/**
 * @author Xiao Biqiang
 * @time   2017/5/16
 * @brief  此面板为棋盘面板，绘制棋盘、画棋子，添加游戏功能按钮以及悔棋等操作均在此面板进行
 */
package com.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.button.ButtonFactory;
import com.chess.Chess;
import com.config.ChessStateConfig;
import com.config.ChessboardConfig;
import com.events.KeyEventsHandle;
import com.events.MouseEventOnButtonHandleFactory;
import com.events.MouseEventsHandle;

public class GomokuPanel extends JPanel{
	private int panelWidth = ChessboardConfig.CHESSBOARD_WIDTH;
	private int panelHeight = ChessboardConfig.CHESSBOARD_HEIGHT;
	private Chess chess = new Chess();            //添加棋子对象
	private MouseEventsHandle mEveHandle = new MouseEventsHandle();  //棋盘的鼠标事件处理对象
	private KeyEventsHandle kEveHandle = new KeyEventsHandle(this);  //按键事件处理对象
	private Image white_black_player = Toolkit.getDefaultToolkit().getImage(GomokuPanel.class.getResource("/images/white_black_player.png"));  //棋盘双人对战的图片
	private JButton startButton = new ButtonFactory(700, 600, 150, 50, "Start");      //开始游戏按钮
	private JButton menuButton = new ButtonFactory(700, 540, 150, 50, "Menu");        //返回菜单按钮
	private JButton restartButton = new ButtonFactory(700, 480, 150, 50, "Restart");  //重新开始按钮
	
	public GomokuPanel(){
		super();
//		this.setSize(panelWidth, panelHeight);
		this.setBounds(0, 0, panelWidth, panelHeight);
		this.setBackground(new Color(139, 105, 20));     //设置背景
		this.setLayout(null);          //使用绝对定位
		this.setFocusable(true);            //获取焦点，因为JPanel默认是false，导致检测不到按键事件，只有获取到焦点才能检测到按键
		
		//设置鼠标点击下棋事件
		this.mEveHandle.setGomokuPanel(this);
		this.addMouseListener(this.mEveHandle);
		this.addKeyListener(this.kEveHandle);
		
		//添加开始游戏按钮
		this.add(this.startButton);
		MouseEventOnButtonHandleFactory startButtonHandle = new MouseEventOnButtonHandleFactory("Start");
//		startButtonHandle.setGomokuPanel(this);
		this.startButton.addMouseListener(startButtonHandle);
		
		//添加返回菜单按钮
		this.add(menuButton);
		MouseEventOnButtonHandleFactory menuButtonHandle = new MouseEventOnButtonHandleFactory("Menu");
		this.menuButton.addMouseListener(menuButtonHandle);
		
		//添加重新开始游戏按钮
		this.add(restartButton);
		MouseEventOnButtonHandleFactory restartButtonHandle = new MouseEventOnButtonHandleFactory("Restart");
		this.restartButton.addMouseListener(restartButtonHandle);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(1.0f));
		g2d.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		g2d.drawString("Gomoku", 300, 34);
		
		//画棋盘外围的粗线条
		g2d.setStroke(new BasicStroke(5.0f));
		g2d.drawLine(ChessboardConfig.OUT_CHESSBOARD_POINTS[0].x, ChessboardConfig.OUT_CHESSBOARD_POINTS[0].y,
				     ChessboardConfig.OUT_CHESSBOARD_POINTS[1].x, ChessboardConfig.OUT_CHESSBOARD_POINTS[1].y);
		g2d.drawLine(ChessboardConfig.OUT_CHESSBOARD_POINTS[1].x, ChessboardConfig.OUT_CHESSBOARD_POINTS[1].y,
			         ChessboardConfig.OUT_CHESSBOARD_POINTS[2].x, ChessboardConfig.OUT_CHESSBOARD_POINTS[2].y);
		g2d.drawLine(ChessboardConfig.OUT_CHESSBOARD_POINTS[2].x, ChessboardConfig.OUT_CHESSBOARD_POINTS[2].y,
			         ChessboardConfig.OUT_CHESSBOARD_POINTS[3].x, ChessboardConfig.OUT_CHESSBOARD_POINTS[3].y);
		g2d.drawLine(ChessboardConfig.OUT_CHESSBOARD_POINTS[3].x, ChessboardConfig.OUT_CHESSBOARD_POINTS[3].y,
			         ChessboardConfig.OUT_CHESSBOARD_POINTS[0].x, ChessboardConfig.OUT_CHESSBOARD_POINTS[0].y);
		
		//画棋盘线条
		g2d.setStroke(new BasicStroke(2.0f));
		for(int i=0; i<ChessboardConfig.HORLINES_NUM; i++){
			g2d.drawLine(ChessboardConfig.CHESSBOARD_POINTS[0][i].x, ChessboardConfig.CHESSBOARD_POINTS[0][i].y,
					     ChessboardConfig.CHESSBOARD_POINTS[20][i].x, ChessboardConfig.CHESSBOARD_POINTS[20][i].y);
			g2d.drawLine(ChessboardConfig.CHESSBOARD_POINTS[i][0].x, ChessboardConfig.CHESSBOARD_POINTS[i][0].y,
					     ChessboardConfig.CHESSBOARD_POINTS[i][20].x, ChessboardConfig.CHESSBOARD_POINTS[i][20].y);
		}
		
		//画棋盘内的5个标志点
		g2d.setStroke(new BasicStroke(1.0f));
		for(int i=0; i<ChessboardConfig.FLAG_POINTS.length; i++){
			g2d.fillOval(ChessboardConfig.FLAG_POINTS[i].x-ChessboardConfig.FLAG_POINTS_RADIUS, ChessboardConfig.FLAG_POINTS[i].y-ChessboardConfig.FLAG_POINTS_RADIUS,
					     2*ChessboardConfig.FLAG_POINTS_RADIUS, 2*ChessboardConfig.FLAG_POINTS_RADIUS);
		}
		
		//画双人对战图片和游戏说明
		g2d.drawImage(white_black_player, 680, 50, this);
		g2d.drawString("White", 700, 200);
		g2d.drawString("Player", 700, 240);
		g2d.drawString("Black", 810, 200);
		g2d.drawString("Player", 810, 240);
		g2d.drawString("Key_Describe:", 680, 300);
		g2d.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawString("Key_Esc: Back to menu", 680, 330);
		g2d.drawString("Key_B   : Last step", 680, 370);
		
		//如果下黑棋并且不是悔棋，那么在鼠标点击的行列画黑棋
		if((ChessStateConfig.chessGoType == 1) && (!ChessStateConfig.isGoBack))
		{
			chess.drawBlackChess(
					ChessboardConfig.CHESSBOARD_POINTS[Chess.chessRow][Chess.chessCol].x, 
					ChessboardConfig.CHESSBOARD_POINTS[Chess.chessRow][Chess.chessCol].y, 
					g2d
					);
		}
		else if((ChessStateConfig.chessGoType == 2) && (!ChessStateConfig.isGoBack)){
			chess.drawWhiteChess(
					ChessboardConfig.CHESSBOARD_POINTS[Chess.chessRow][Chess.chessCol].x, 
					ChessboardConfig.CHESSBOARD_POINTS[Chess.chessRow][Chess.chessCol].y, 
					g2d
					);
		}
	}
}
