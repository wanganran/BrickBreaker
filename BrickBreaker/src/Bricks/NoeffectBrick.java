package Bricks;

import java.awt.Graphics;
import java.awt.Point;

import Gaming.Brick;
import Gaming.PointF;
import Gaming.Style;

public class NoeffectBrick extends Brick {
	
	@Override
	public void Draw(Graphics c, Point offset) {
		if(visible)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_NOEFFECT), offset.x, offset.y, this.Width, this.Height, null);
	}

	@Override
	public boolean Hit() {
		this.visible=false;
		return false;
	}
}
