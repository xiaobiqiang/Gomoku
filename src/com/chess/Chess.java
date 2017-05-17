package com.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class Chess {
	public static final int CHESS_RADIUS = 10;
//	public static final int CHESS_ARR_LEN = 3;   //棋盘
	public static int chessRow = 0;
	public static int chessCol = 0;
//	public static int previousChessRow = 0;
//	public static int previousChessCol = 0;
	
	/**
	 * 
	 * @param x 棋子横坐标
	 * @param y 棋子纵坐标
	 * @param g 画刷对象的引用
	 */
	public void drawBlackChess(int x, int y, Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		RadialGradientPaint paint = new RadialGradientPaint(x+9, y+9, 20, new float[]{0f,1f}, new Color[]{Color.LIGHT_GRAY,Color.BLACK});
		g2d.setPaint(paint);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
		Ellipse2D e = new Ellipse2D.Float(x-Chess.CHESS_RADIUS, y-Chess.CHESS_RADIUS, 2*Chess.CHESS_RADIUS, 2*Chess.CHESS_RADIUS);
		g2d.fill(e);
	}
	
	public void drawWhiteChess(int x, int y, Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		RadialGradientPaint paint = new RadialGradientPaint(x+9, y+9, 37, new float[]{0.2f,0.9f}, new Color[]{Color.WHITE,Color.BLACK});
		g2d.setPaint(paint);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
		Ellipse2D e = new Ellipse2D.Float(x-Chess.CHESS_RADIUS, y-Chess.CHESS_RADIUS, 2*Chess.CHESS_RADIUS, 2*Chess.CHESS_RADIUS);
		g2d.fill(e);
	}
	
//	public void drawChessArround(int x, int y, Graphics g){
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.setColor(Color.RED);
//		g2d.setStroke(new BasicStroke(1.5f));
//		g2d.drawLine(x-Chess.CHESS_RADIUS, y-Chess.CHESS_RADIUS, x-Chess.CHESS_RADIUS+Chess.CHESS_ARR_LEN, y-Chess.CHESS_RADIUS);
//		g2d.drawLine(x-Chess.CHESS_RADIUS, y-Chess.CHESS_RADIUS, x-Chess.CHESS_RADIUS, y-Chess.CHESS_RADIUS+Chess.CHESS_ARR_LEN);
//		g2d.drawLine(x+Chess.CHESS_RADIUS, y-Chess.CHESS_RADIUS, x+Chess.CHESS_RADIUS-Chess.CHESS_ARR_LEN, y-Chess.CHESS_RADIUS);
//		g2d.drawLine(x+Chess.CHESS_RADIUS, y-Chess.CHESS_RADIUS, x+Chess.CHESS_RADIUS, y-Chess.CHESS_RADIUS+Chess.CHESS_ARR_LEN);
//		g2d.drawLine(x-Chess.CHESS_RADIUS, y+Chess.CHESS_RADIUS, x-Chess.CHESS_RADIUS+Chess.CHESS_ARR_LEN, y+Chess.CHESS_RADIUS);
//		g2d.drawLine(x-Chess.CHESS_RADIUS, y+Chess.CHESS_RADIUS, x-Chess.CHESS_RADIUS, y+Chess.CHESS_RADIUS-Chess.CHESS_ARR_LEN);
//		g2d.drawLine(x+Chess.CHESS_RADIUS, y+Chess.CHESS_RADIUS, x+Chess.CHESS_RADIUS-Chess.CHESS_ARR_LEN, y+Chess.CHESS_RADIUS);
//		g2d.drawLine(x+Chess.CHESS_RADIUS, y+Chess.CHESS_RADIUS, x+Chess.CHESS_RADIUS, y+Chess.CHESS_RADIUS-Chess.CHESS_ARR_LEN);
//	}
}
