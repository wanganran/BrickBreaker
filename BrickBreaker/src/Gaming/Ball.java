package Gaming;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;

/**
 * �����ͣ�ʵ����IDrawable,IUpdatable��ILinearMoving
 * @author anran
 *
 */
public class Ball extends Container implements IDrawable,IUpdatable,ILinearMoving {
	/**
	 * ��ʼ��һ��С��
	 * @param speed ��С��ĳ�ʼ�ٶȣ���λΪpx/s;��ʼ����Ϊ���ϡ�
	 */
	public Ball(double speed)
	{
		this.name="Ball";
		this.direction=new PointF(0,-speed);
		Reset();
	}
	
	private PointF position;
	private PointF direction;
	public PointF getPosition() {
		return position;
	}
	/**
	 * ����С���λ��
	 * @param position ��λ��
	 */
	public void setPosition(PointF position) {
		this.position = position;
	}
	public PointF getDirection() {
		return direction;
	}
	public void AddSpeed(double ratio)
	{
		this.direction.x*=ratio;
		this.direction.y*=ratio;
	}
	/**
	 * ����С��ķ���
	 * @param direction ���ٶ�ʸ������λΪpx/s
	 */
	public void setDirection(PointF direction) {
		this.direction = direction;
	}
	/**
	 * С��뾶
	 */
	public static double Radius=6;
	@Override
	public void Update(int timespan) {
		if(!pause){
			if(this.position.y>Game.GetCurrentGame().getHeight()-Game.GetCurrentGame().gameContainer.getBoard().bottom)
			{
				Game.GetCurrentGame().LoseLife(1);
				return;
			}
			this.position=this.position.Plus(direction.Multiply(timespan/1000.0));
		}
		if(Game.GetCurrentGame().gameContainer.getBoard().Hit(this))
		{
			this.position.y=(Game.GetCurrentGame().getHeight()-Board.bottom)*2-this.position.y;
			double r=Math.min(1,Math.max(0.0,this.position.x-Game.GetCurrentGame().gameContainer.getBoard().getLeft())/Board.Length);
			double rad=(r-0.5)/1.2*Math.PI;
			double sp=Math.sqrt(this.direction.x*direction.x+direction.y*direction.y);
			direction.x=Math.sin(rad)*sp;
			direction.y=-Math.cos(rad)*sp;
		}
		Brick b;
		while((b=Game.GetCurrentGame().gameContainer.getBricksContainer().getBrickByPos(this))!=null&&b.visible)
		{
			if(b.Hit()){
			PointF oldpos= position.Plus(direction.Multiply(-1000));
				boolean le=GraphicUtil.cross(b.getLTPosition(), b.getLTPosition().Plus(new PointF(0,Brick.Height)), position,oldpos);
				boolean re=GraphicUtil.cross(b.getLTPosition().Plus(new PointF(Brick.Width,0)), b.getLTPosition().Plus(new PointF(Brick.Width,Brick.Height)), position, oldpos);
				boolean te=GraphicUtil.cross(b.getLTPosition(), b.getLTPosition().Plus(new PointF(Brick.Width,0)), position, oldpos);
				boolean be=GraphicUtil.cross(b.getLTPosition().Plus(new PointF(0,Brick.Height)), b.getLTPosition().Plus(new PointF(Brick.Width,Brick.Height)), position, oldpos);
			
				if(le||re)direction.x=-direction.x;
				if(be||te)direction.y=-direction.y;
				
				if(le)
					position.x=b.getLTPosition().x*2-position.x;
				if(re)
					position.x=(b.getLTPosition().x+Brick.Width)*2-position.x;

				if(te)
					position.y=b.getLTPosition().y*2-position.y;
				if(be)
					position.y=(b.getLTPosition().y+Brick.Height)*2-position.y;
			}
			if(!b.visible)Game.GetCurrentGame().AddScore(1);
			if(Game.GetCurrentGame().gameContainer.getBricksContainer().isFinished())Game.GetCurrentGame().FinishLevel();
		}
		if(this.position.x<0)
		{
			this.position.x=-position.x;
			this.direction.x=-this.direction.x;
		}
		else if(this.position.x>Game.GetCurrentGame().getWidth())
		{
			this.position.x=2*Game.GetCurrentGame().getWidth()-position.x;
			this.direction.x=-direction.x;
		}
		else if(this.position.y<0)
		{
			this.position.y=-position.y;
			this.direction.y=-this.direction.y;
		}
		else if(this.position.y>=Game.GetCurrentGame().getHeight())
			Game.GetCurrentGame().LoseLife(1);
	}
	@Override
	public void Draw(Graphics c, Point offset) {
		c.drawImage(Style.getCurrentStyle().getBall(), offset.x+(int)(position.x-Radius), offset.y+(int)(position.y-Radius), (int)(2*Radius), (int)(2*Radius), null);
	}
	/**
	 * ����С��λ�á�
	 */
	public void Reset()
	{
		Board b=Game.GetCurrentGame().gameContainer.getBoard();
		this.position=new PointF(b.getLeft()+b.Length/2,Game.current.getHeight()-Board.bottom-this.Radius-10);
	}
	private boolean pause;
	/**
	 * ��ͣС���˶�
	 */
	public void Pause()
	{
		pause=true;
		
	}
	/**
	 * ����С���˶�
	 */
	public void Resume()
	{
		pause=false;
	}
}
