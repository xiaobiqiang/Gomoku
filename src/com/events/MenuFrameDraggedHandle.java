/**
 * @author Xiao Biqiang
 * @brief  �����괦����������ڲ˵������϶��¼��ģ���Ϊ�˵������������εģ�
 *         ʹ�ò˵������ܹ�����������ק�������ƶ�
 */
package com.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.frame.MenuFrame;

public class MenuFrameDraggedHandle implements MouseMotionListener{
	private int old_x;                  //��һ�����λ�õĺ�����
	private int old_y;                  //��һ�����λ�õ�������
	private MenuFrame menuFrame;        
	
	public MenuFrameDraggedHandle(MenuFrame f){
		this.menuFrame = f;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		//�����ק�ı������Ϊe.getX()-old_x������ק�¼���old_xֵ�ǹ̶��ģ�
		//һ������¼�ֻ����һ���������������һ���¼��������ִ�У���������moveMoved()��mouseDragged()ͬʱִ��,���ͨ��һ��print��������������֤9
		this.menuFrame.setLocation(this.menuFrame.getLocation().x+e.getX()-old_x, this.menuFrame.getLocation().y+e.getY()-old_y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		old_x = e.getX();               //ÿ������ƶ����������old_xֵ������갴��׼����ק��old_xֵ�̶����䣬ֱ����ק�¼����
		old_y = e.getY();
	}

}
