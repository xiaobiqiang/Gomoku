/**
 * @author Xiao Biqiang
 * @brief  �����ΪĿ¼��塣������MenuFrame��
 *         �˵�ѡ�ť�Լ������ȶ����ڴ����
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
		g2d.setStroke(new BasicStroke(2.5f));      //���û��ߵ�������ȣ�BasicStroke�����������������
		g2d.setColor(Color.GREEN);
		g2d.setFont(new Font("���Ʋ���", Font.BOLD, 52));       //����������ʽ��Font�������������塢��С�Լ��Ӵֵ�
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);      //������ͼ��Ŀ����״��Ե
		g2d.drawImage(bkImg, 0, 0, this);                //��ͼ�������Ǹ����������ڵ���������꣨����˵�����壩
		g2d.drawString("Gomoku", 45, 105);
	}
}
