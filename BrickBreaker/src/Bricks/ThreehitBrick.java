package Bricks;
// iL
import java.awt.Graphics;
import java.awt.Point;

import Gaming.Brick;
import Gaming.PointF;
import Gaming.Style;

public class ThreehitBrick extends Brick {

	int i=3;
	//i=remained number of hit
	public Graphics g;
	public Point offse;
	@Override
	
	public void Draw(Graphics c, Point offset) {
		g=c;
		offse=offset;
		if(visible&&i==3)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_NORMAL3), offset.x, offset.y, this.Width, this.Height, null);
		else if(visible&&i==2)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_NORMAL2), offset.x, offset.y, this.Width, this.Height, null);
		else if(visible&&i==1)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_NORMAL), offset.x, offset.y, this.Width, this.Height, null);
			
	    
	}

	@Override
	public boolean Hit() {
		i=i-1;
		if(i==0)
		{
			this.visible=false;
			// TODO Add Extras
		}
		else
		{
			Draw(g,offse);
		}
			return true;
	}

}