/**
 * @author Xiao Biqiang 
 * @brief  ����һ����ť�����������ʵ��Ĳ���������λ�á���С�����Ƶȿ��Բ���һ����ͬ��۵İ�ť
 */
package com.button;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class ButtonFactory extends JButton{
	private int width = 0;
	private int height = 0;
	private int locx = 0;
	private int locy = 0;
	private boolean hover = false;
	/**
	 * 
	 * @param x ��ť�ڰ�����������ϵĺ����꣬ǰ���ǰ�����������Ĳ���Ϊ���Զ�λ������������
	 * @param y ��ť�ڰ�����������ϵ������꣬ǰ���ǰ�����������Ĳ���Ϊ���Զ�λ������������
	 * @param w ��ť�Ŀ��
	 * @param h ��ť�ĸ߶�
	 * @param buttonName ��ť����ʾ������
	 */
	public ButtonFactory(int x, int y, int w, int h, String buttonName){
		super();
		
		this.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		this.setText(buttonName);
		
		this.locx = x;
		this.locy = y;
		this.width = w;
		this.height = h;
		this.setBounds(x, y, w, h);
		
		this.setBorderPainted(false);             //������Ĭ�ϵı߽�
		this.setContentAreaFilled(false);         //�����ư�ť���ݣ��߿�֮�ڵĿռ䣩
		this.setFocusPainted(false);              //�����ư�ť�Ľ��㣨��ť�����ڵ�С�߿�
		
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				hover = true;
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hover = false;
				repaint();
			}		
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		float tran = 0.7f;
		if(!hover)
			tran = 0.3f;
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, tran));              //����͸����
		GradientPaint p1;
		GradientPaint p2;
		if(this.getModel().isPressed()) {        //�����ť����
			p1 = new GradientPaint(0, 0, new Color(0,0,0), 0, this.height-1, new Color(100,100,100));
			p2 = new GradientPaint(0, 1, new Color(0,0,0,50), 0, this.height-3, new Color(255,255,255,100));
		}else{
			p1 = new GradientPaint(0, 0, new Color(100,100,100), 0, this.height-1, new Color(0,0,0));
			p2 = new GradientPaint(0, 1, new Color(255,255,255,100), 0, this.height-3, new Color(0,0,0,50));
		}
		GradientPaint gp = new GradientPaint(0, 0, new Color(205,255,205), 0, this.getHeight(), new Color(51,154,47), true);
		g2d.setPaint(gp);
		RoundRectangle2D r2d = new RoundRectangle2D.Float(0.0f, 0.0f, this.width-1, this.height-1, 20, 20);
		Shape clip = g2d.getClip();
		g2d.clip(r2d);
		g2d.fillRect(0, 0, width, height);
		g2d.setClip(clip);
		g2d.setPaint(p1);
		g2d.drawRoundRect(0, 0, width-1, height-1, 20, 20);
		g2d.setPaint(p2);
		g2d.drawRoundRect(1, 1, width-3, height-3, 18, 18);
	}
}
