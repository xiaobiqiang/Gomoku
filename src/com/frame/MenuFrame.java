/**
 * @author Xiao Biqiang
 * @brief  此窗口为菜单窗口：
 *         （1）：取消了默认的修饰
 *         （2）：尺寸固定不可变
 *         （3）：初始位置总是位于屏幕中间
 */
package com.frame;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.config.MenuFrameConfig;
import com.config.ScreenConfig;
import com.events.MenuFrameDraggedHandle;
import com.progressBar.LoadProgressBar;

public class MenuFrame extends JFrame{
	private Image iconImg;
	private LoadProgressBar progressBar;
	
	public MenuFrame(){
		super();
		this.setSize(MenuFrameConfig.MENUFRAME_WIDTH, MenuFrameConfig.MENUFRAME_HEIGHT);
		this.setResizable(false);                      //窗口不可改变尺寸
		this.setUndecorated(true);                     //取消窗口默认的修饰
		this.setLocation((ScreenConfig.SCREEN_WIDTH-MenuFrameConfig.MENUFRAME_WIDTH)/2, (ScreenConfig.SCREEN_HEIGHT-MenuFrameConfig.MENUFRAME_HEIGHT)/2);   //窗口位于屏幕中央
		this.setFrameIcon();                           //设置窗口图标
		this.setLayout(null);                          //使用绝对定位
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);  //设置窗口默认的关闭选项为退出程序
		this.setVisible(true);                         //设置窗口可见
		this.addMouseMotionListener(new MenuFrameDraggedHandle(this));     //设置窗口鼠标拖动事件，因为取消了修饰，所以要设置鼠标拖拽事件来让窗口能被鼠标拖动
	}
	
	public void setFrameIcon(){
		 /**
		  * @brief 从bin文件夹下的images文件夹获取图片资源（因为class文件在bin文件夹）,但是次方式不能通过getWidth和getHeight来获取宽度和高度，
		  *        可以通过（1）：InputStream source = new BufferedInputStream(new FileInputStream(filePath));
		  *                  Image sourceImg = ImageIO.read(source); 
		  *             （2）：File source = new File(filePath);
		  *                  Image sourceImg = ImageIO.read(source);
		  *             （3）：URL source = new URL("www.xxxx.com");
		  *                  Image sourceImg = ImageIO.read(source); 
		  *        来获取图片的宽度和高度。
		  */
		this.iconImg = Toolkit.getDefaultToolkit().getImage(MenuFrame.class.getResource("/images/icon.png"));     
		this.setIconImage(this.iconImg);
	}
	
	public void setLoadProgressBar(LoadProgressBar bar){
		this.progressBar = bar;
	}
}
