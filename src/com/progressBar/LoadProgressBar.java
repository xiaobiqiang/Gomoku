/**
 * @author Xiao Biqiang
 * @time   2017/5/16
 * @brief  此进度条是表示游戏加载的进度，添加于菜单窗口
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
	 * @param min 进度条显示的最小值
	 * @param max 进度条显示的最大值
	 */
	public LoadProgressBar(int min, int max){
		super(min, max);
		this.min = min;
		this.max = max;
		
		this.setBorderPainted(false);
		this.setFont(new Font("楷体", Font.PLAIN, 24));
		this.setStringPainted(true);      //让进度条能够打印出进度
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
				//猜想：进度条可能在设置值之后才会显示，不然是会隐藏的，而线程执行完后进行了清理工作，导致下一次加载进度条并没有设置值，所以会隐藏，当线程开启后又从min开始显示加载？？？？？
				this.setValue(i);
			}
		}
		
		if(isFirst)
		{
			FrameSetterAndGetter.getInstance().setGomokuFrame(new GomokuFrame());   //第一次加载才初始化棋盘窗口，后面不会重新初始化，保证只有一个棋盘窗口
			isFirst = false;
		}
		else
			FrameSetterAndGetter.getInstance().getGomokuFrame().setVisible(true);   //如果不是第一次加载，那么让disposed的棋盘窗口恢复并且显示
		
		FrameSetterAndGetter.getInstance().getMenuFrame().dispose();                //让菜单窗口消失并释放屏幕资源
	}
}
