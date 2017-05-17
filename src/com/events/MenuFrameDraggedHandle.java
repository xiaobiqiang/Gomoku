/**
 * @author Xiao Biqiang
 * @brief  这个鼠标处理程序是用于菜单窗口拖动事件的（因为菜单窗口是无修饰的）
 *         使得菜单窗口能够随着鼠标得拖拽而跟着移动
 */
package com.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.frame.MenuFrame;

public class MenuFrameDraggedHandle implements MouseMotionListener{
	private int old_x;                  //上一次鼠标位置的横坐标
	private int old_y;                  //上一次鼠标位置的纵坐标
	private MenuFrame menuFrame;        
	
	public MenuFrameDraggedHandle(MenuFrame f){
		this.menuFrame = f;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		//鼠标拖拽改变的坐标为e.getX()-old_x，在拖拽事件中old_x值是固定的，
		//一个鼠标事件只会让一个鼠标监听器里面的一个事件处理程序执行，不可能让moveMoved()和mouseDragged()同时执行,这个通过一个print语句输出坐标便可验证9
		this.menuFrame.setLocation(this.menuFrame.getLocation().x+e.getX()-old_x, this.menuFrame.getLocation().y+e.getY()-old_y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		old_x = e.getX();               //每次鼠标移动，将会更新old_x值，当鼠标按下准备拖拽后，old_x值固定不变，直到拖拽事件完成
		old_y = e.getY();
	}

}
