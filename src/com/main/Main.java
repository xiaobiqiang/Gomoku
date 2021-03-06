package com.main;


import javax.swing.JButton;

import com.button.ButtonFactory;
import com.events.MouseEventOnButtonHandleFactory;
import com.frame.FrameSetterAndGetter;
import com.frame.MenuFrame;
import com.panel.MenuPanel;

public class Main {

	public static void main(String[] args) {
		createAndShowMenuUI();
	}

	public static void createAndShowMenuUI(){
		JButton exitButton = new ButtonFactory(75, 280, 150, 35, "Exit");        //退出程序按钮
		exitButton.addMouseListener(new MouseEventOnButtonHandleFactory("Exit"));
		
		JButton doublePlayerPC_Button = new ButtonFactory(75, 220, 150, 35, "Double PC");
		MouseEventOnButtonHandleFactory doublePlayerPC_EventHandle = new MouseEventOnButtonHandleFactory("Double_Player_PC");
		doublePlayerPC_Button.addMouseListener(doublePlayerPC_EventHandle);
		
		JButton doublePlayerDC_Button = new ButtonFactory(75, 160, 150, 35, "Double DC");
		MouseEventOnButtonHandleFactory doublePlayerDC_EventHandle = new MouseEventOnButtonHandleFactory("Double_Player_DC");
		
		MenuPanel menuPanel = new MenuPanel();
		MenuFrame menuFrame = new MenuFrame();
		
		FrameSetterAndGetter.getInstance().setMenuFrame(menuFrame);
		
		menuPanel.setLayout(null);
		menuPanel.add(exitButton);
		menuPanel.add(doublePlayerPC_Button);
		menuPanel.add(doublePlayerDC_Button);
		
		menuFrame.add(menuPanel);
		menuFrame.setVisible(true);
	}
}
