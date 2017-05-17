/**
 * @author Xiao Biqiang
 * @time   2017/5/16
 * @brief  �˴����������ڴ�źͻ�ȡ��ǰ�����Ĳ˵����ں����̴��ڣ����ǵ��жദҪʹ�����������ڣ����ͨ��һ������ר�Ŵ�źͻ�ȡ
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
