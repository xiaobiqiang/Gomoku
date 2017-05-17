/**
 * @author Xiao Biqiang
 * @brief  此面板为目录面板。包含在MenuFrame中
 *         菜单选项按钮以及背景等都画在此面板
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

import javax.swing.JPanel;

import com.config.MenuFrameConfig;

public class MenuPanel extends JPanel{
	private int panelWidth = MenuFrameConfig.MENUFRAME_WIDTH;
	private int panelHeight = MenuFrameConfig.MENUFRAME_HEIGHT;
	private Image bkImg = Toolkit.getDefaultToolkit().getImage(MenuPanel.class.getResource("/images/menukg.png"));
	
	public MenuPanel(){
		super();
		this.setSize(this.panelWidth, this.panelHeight);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2.5f));      //设置画线的线条宽度，BasicStroke类用于设置线条宽度
		g2d.setColor(Color.GREEN);
		g2d.setFont(new Font("华云彩体", Font.BOLD, 52));       //设置字体样式，Font类用于设置字体、大小以及加粗等
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);      //消除画图象的抗锯齿状边缘
		g2d.drawImage(bkImg, 0, 0, this);                //画图的坐标是根据自身所在的组件的坐标（比如说这个面板）
		g2d.drawString("Gomoku", 45, 105);
	}
}
