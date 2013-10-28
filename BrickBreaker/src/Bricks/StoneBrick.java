package Bricks;

import java.awt.Graphics;
import java.awt.Point;

import Gaming.Brick;
import Gaming.PointF;
import Gaming.Style;

public class StoneBrick extends Brick {
	
	public void Draw(Graphics c, Point offset) {
		if(visible)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_STONE), offset.x, offset.y, this.Width, this.Height, null);
	}
	
	public boolean Hit() {
		this.visible=true;
		return true;
	}
}
