package com.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.frame.GomokuFrame;

public class GomokuFrameDraggedHandle implements MouseMotionListener{
	int old_x;
	int old_y;
	private GomokuFrame gomokuFrame;
	public GomokuFrameDraggedHandle(GomokuFrame f){
		super();
		this.gomokuFrame = f;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		this.gomokuFrame.setLocation(this.gomokuFrame.getLocation().x+e.getX()-old_x, this.gomokuFrame.getLocation().y+e.getY()-old_y);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.old_x = e.getX();
		this.old_y = e.getY();
	}
	
}
