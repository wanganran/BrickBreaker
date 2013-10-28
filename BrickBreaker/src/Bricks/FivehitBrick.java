package Bricks;

import java.awt.Graphics;
import java.awt.Point;

import Gaming.Brick;
import Gaming.PointF;
import Gaming.Style;

public class FivehitBrick extends Brick {
	
	private int count=5;
	private Graphics g;
	private Point offse; 
	
	@Override
	public void Draw(Graphics c, Point offset) {
		this.g=c;
		this.offse=offset;
		if(visible&&count==5)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_NORMAL5), offset.x, offset.y, this.Width, this.Height, null);
		else if(visible&&count==4)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_NORMAL4), offset.x, offset.y, this.Width, this.Height, null);
		else if(visible&&count==3)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_NORMAL3), offset.x, offset.y, this.Width, this.Height, null);
		else if(visible&&count==2)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_NORMAL2), offset.x, offset.y, this.Width, this.Height, null);
		else if(visible&&count==1)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_NORMAL), offset.x, offset.y, this.Width, this.Height, null);
			
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean Hit() {
		count--;
		if(count==0)
			this.visible=false;
		// TODO Auto-generated method stub
		else
			this.Draw(this.g,this.offse);
		return true;
	}
	

}