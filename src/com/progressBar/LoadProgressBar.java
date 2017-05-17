/**
 * @author Xiao Biqiang
 * @time   2017/5/16
 * @brief  �˽������Ǳ�ʾ��Ϸ���صĽ��ȣ�����ڲ˵�����
 */
package com.progressBar;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JProgressBar;

import com.frame.FrameSetterAndGetter;
import com.frame.GomokuFrame;

public class LoadProgressBar extends JProgressBar implements Runnable{
	private int min = 0;
	private int max = 0;
	private boolean isFirst = true;
	
	/**
	 * 
	 * @param min ��������ʾ����Сֵ
	 * @param max ��������ʾ�����ֵ
	 */
	public LoadProgressBar(int min, int max){
		super(min, max);
		this.min = min;
		this.max = max;
		
		this.setBorderPainted(false);
		this.setFont(new Font("����", Font.PLAIN, 24));
		this.setStringPainted(true);      //�ý������ܹ���ӡ������
		this.setForeground(new Color(0,210,40));
		this.setBackground(new Color(188, 190, 194));
		this.setBounds(7, 390, 286, 30);
	}
	
	@Override
	public void run() {
		int i = this.min-1;
		
		while(i < this.max){
			i++;
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				//���룺����������������ֵ֮��Ż���ʾ����Ȼ�ǻ����صģ����߳�ִ������������������������һ�μ��ؽ�������û������ֵ�����Ի����أ����߳̿������ִ�min��ʼ��ʾ���أ���������
				this.setValue(i);
			}
		}
		
		if(isFirst)
		{
			FrameSetterAndGetter.getInstance().setGomokuFrame(new GomokuFrame());   //��һ�μ��زų�ʼ�����̴��ڣ����治�����³�ʼ������ֻ֤��һ�����̴���
			isFirst = false;
		}
		else
			FrameSetterAndGetter.getInstance().getGomokuFrame().setVisible(true);   //������ǵ�һ�μ��أ���ô��disposed�����̴��ڻָ�������ʾ
		
		FrameSetterAndGetter.getInstance().getMenuFrame().dispose();                //�ò˵�������ʧ���ͷ���Ļ��Դ
	}
}
