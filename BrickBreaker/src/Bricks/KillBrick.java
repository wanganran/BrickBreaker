package Bricks;
//iL
import java.awt.Graphics;
import java.awt.Point;

import Gaming.Brick;
import Gaming.PointF;
import Gaming.Style;

public class KillBrick extends Brick {

	@Override
	public void Draw(Graphics c, Point offset) {
		if(visible)
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_KILL), offset.x, offset.y, this.Width, this.Height, null);
	}

	@Override
	public boolean Hit() {
		this.visible=false;
		Gaming.Game.GetCurrentGame().LoseLife(1);
		// TODO Add Extras
		return true;
	}

}
