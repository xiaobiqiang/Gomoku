/**
 * @author Xiao Biqiang
 * @time   2017/5/16
 * @brief  此窗口容器用于存放和获取当前工作的菜单窗口和棋盘窗口，考虑到有多处要使用这两个窗口，因而通过一个容器专门存放和获取
 */
package com.frame;

public class FrameSetterAndGetter {
	private GomokuFrame gomokuFrame;
	private MenuFrame menuFrame;
	private static FrameSetterAndGetter frameSetterAndGetter = new FrameSetterAndGetter();
	private FrameSetterAndGetter(){ };
	
	public void setGomokuFrame(GomokuFrame f){
		this.gomokuFrame = f;
	}
	
	public void setMenuFrame(MenuFrame f){
		this.menuFrame = f;
	}
	
	public GomokuFrame getGomokuFrame(){
		return this.gomokuFrame;
	}
	
	public MenuFrame getMenuFrame(){
		return this.menuFrame;
	}
	
	public static FrameSetterAndGetter getInstance(){
		return frameSetterAndGetter;
	}
}
