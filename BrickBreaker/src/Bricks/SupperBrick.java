package Bricks;
//iL
import java.awt.Graphics;
import java.awt.Point;

import Gaming.Brick;
import Gaming.Game;
import Gaming.PointF;
import Gaming.Style;

public class SupperBrick extends Brick {

	@Override
	public void Draw(Graphics c, Point offset) {
		if(visible)
			c.drawImage(Style.getCurrentStyle().getBrickImage(Brick.BRICK_SUPER), offset.x, offset.y, Brick.Width, Brick.Height, null);
	}

	@Override
	public boolean Hit() {
		this.visible=false;
		Gaming.Game.GetCurrentGame().FinishLevel();
		// TODO Add Extras
		return true;
	}

}
