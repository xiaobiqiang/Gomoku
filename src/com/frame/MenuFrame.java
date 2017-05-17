/**
 * @author Xiao Biqiang
 * @brief  �˴���Ϊ�˵����ڣ�
 *         ��1����ȡ����Ĭ�ϵ�����
 *         ��2�����ߴ�̶����ɱ�
 *         ��3������ʼλ������λ����Ļ�м�
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
		this.setResizable(false);                      //���ڲ��ɸı�ߴ�
		this.setUndecorated(true);                     //ȡ������Ĭ�ϵ�����
		this.setLocation((ScreenConfig.SCREEN_WIDTH-MenuFrameConfig.MENUFRAME_WIDTH)/2, (ScreenConfig.SCREEN_HEIGHT-MenuFrameConfig.MENUFRAME_HEIGHT)/2);   //����λ����Ļ����
		this.setFrameIcon();                           //���ô���ͼ��
		this.setLayout(null);                          //ʹ�þ��Զ�λ
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);  //���ô���Ĭ�ϵĹر�ѡ��Ϊ�˳�����
		this.setVisible(true);                         //���ô��ڿɼ�
		this.addMouseMotionListener(new MenuFrameDraggedHandle(this));     //���ô�������϶��¼�����Ϊȡ�������Σ�����Ҫ���������ק�¼����ô����ܱ�����϶�
	}
	
	public void setFrameIcon(){
		 /**
		  * @brief ��bin�ļ����µ�images�ļ��л�ȡͼƬ��Դ����Ϊclass�ļ���bin�ļ��У�,���Ǵη�ʽ����ͨ��getWidth��getHeight����ȡ��Ⱥ͸߶ȣ�
		  *        ����ͨ����1����InputStream source = new BufferedInputStream(new FileInputStream(filePath));
		  *                  Image sourceImg = ImageIO.read(source); 
		  *             ��2����File source = new File(filePath);
		  *                  Image sourceImg = ImageIO.read(source);
		  *             ��3����URL source = new URL("www.xxxx.com");
		  *                  Image sourceImg = ImageIO.read(source); 
		  *        ����ȡͼƬ�Ŀ�Ⱥ͸߶ȡ�
		  */
		this.iconImg = Toolkit.getDefaultToolkit().getImage(MenuFrame.class.getResource("/images/icon.png"));     
		this.setIconImage(this.iconImg);
	}
	
	public void setLoadProgressBar(LoadProgressBar bar){
		this.progressBar = bar;
	}
}
