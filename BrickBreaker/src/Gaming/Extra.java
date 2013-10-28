package Gaming;
import java.awt.Canvas;

/**
 * 表示道具的抽象类，需要被继承
 * @author anran
 * 
 */
// TODO 需要完善
public abstract class Extra extends Container implements IDrawable,IUpdatable,ILinearMoving {
	private int extraType;
	public int getType()
	{
		return extraType;
	}
	private boolean enabled;
	public abstract void DrawDrop(Canvas c);
	public abstract void Hit();
	public abstract void DrawEffect(Canvas c,Game g);
	public void  Unregister()
	{
		Game.GetCurrentGame().gameContainer.RemoveChild(this);
	}
	
	public abstract void UpdateEffect(int t);
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if(enabled){
			dir=new PointF(0,0);
			Hit();
		}
	}
	public void Draw(Canvas c)
	{
		if(Game.GetCurrentGame()!=null&&Game.GetCurrentGame().isStarted()){
			if(!isEnabled())DrawDrop(c);
			else
				DrawEffect(c,Game.GetCurrentGame());
		}
	}
	public void Update(int t)
	{
		// TODO finish extras
	}
	public abstract PointF getPosition();
	private PointF dir=new PointF(0,5);
	public PointF getDirection(){
		return dir;
	}
}
