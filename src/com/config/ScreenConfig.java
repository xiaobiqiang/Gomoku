package com.config;

import java.awt.Toolkit;

public class ScreenConfig {
	//获取屏幕的宽度和高度
	public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int SCREEN_HEIGHT =Toolkit.getDefaultToolkit().getScreenSize().height;
}
