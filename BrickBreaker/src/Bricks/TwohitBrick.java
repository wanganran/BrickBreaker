package Bricks;
//iL
import java.awt.Graphics;
import java.awt.Point;

import Gaming.Brick;
import Gaming.PointF;
import Gaming.Style;

public class TwohitBrick extends Brick {

	public int i=2;
	public Graphics g;
	public Point offse;
	//i=remained number of hit
	@Override
	public void Draw(Graphics c, Point offset) {
		g=c;
		offse=offset;
		if(visible&&i==2)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_NORMAL2), offset.x, offset.y, this.Width, this.Height, null);
		else if(visible&&i==1)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_NORMAL), offset.x, offset.y, this.Width, this.Height, null);

	}

	@Override
	public boolean Hit(){
		i=i-1;
		if(i==0)
			visible=false;
		else
			Draw(g,offse);
		return true;
		
	}
}