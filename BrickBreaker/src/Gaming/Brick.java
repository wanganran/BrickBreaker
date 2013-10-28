package Gaming;
import java.awt.Graphics;
import java.awt.Point;

import Bricks.Brickbomb;
import Bricks.Brickbomb2;
import Bricks.FivehitBrick;
import Bricks.FourhitBrick;
import Bricks.KillBrick;
import Bricks.NormalBrick;
import Bricks.SupperBrick;
import Bricks.ThreehitBrick;
import Bricks.TwohitBrick;
import Bricks.StoneBrick;
import Bricks.NoeffectBrick;

/**
 * 表示一个砖块的抽象类，需要被继承。
 * @author anran
 *
 */
public abstract class Brick extends Container implements IDrawable{
	protected PointF LTPosition;
	/**
	 * 表示一个砖块的宽度，单位为px
	 */
	public static int Width;
	/**
	 * 表示一个砖块的厚度，单位为px
	 */
	public static int Height;
	/**
	 * 表示砖块类型。
	 */
	protected int type;
	/**
	 * 空砖块
	 */
	protected int BRICK_BLANK=0;
	/**
	 * 普通砖块
	 */
	protected static int BRICK_NORMAL=1;
	protected static int BRICK_NORMAL2=2;
	protected static int BRICK_NORMAL3=3;
	protected static int BRICK_NORMAL4=4;
	protected static int BRICK_NORMAL5=5;
	protected static int BRICK_STONE=6;
	protected static int BRICK_BOMB=7;
	protected static int BRICK_BOMB2=8;
	protected static int BRICK_NOEFFECT=9;
	protected static int BRICK_SUPER=10;
	protected static int BRICK_KILL=11;
	
	/**
	 * 根据type创建一个砖块
	 * @param type 砖块类型
	 * @param x 砖块的位置，单位为px
	 * @param y 砖块的位置，单位为px
	 * @return 返回该砖块
	 */
	public static Brick createBrick(int type,int x,int y)
	{
		if(type==BRICK_BOMB)
		{
			Brick b= new Brickbomb();
			b.type=b.BRICK_BOMB;
			b.LTPosition=new PointF(x,y);
			return b;
		}
		else if(type==BRICK_BOMB2)
		{
			Brick b= new Brickbomb2();
			b.type=b.BRICK_BOMB2;
			b.LTPosition=new PointF(x,y);
			return b;
		}
		else if(type==BRICK_NORMAL4)
		{
			Brick b= new FourhitBrick();
			b.type=b.BRICK_NORMAL4;
			b.LTPosition=new PointF(x,y);
			return b;
		}
		else if(type==BRICK_NORMAL5)
		{
			Brick b= new FivehitBrick();
			b.type=b.BRICK_NORMAL5;
			b.LTPosition=new PointF(x,y);
			return b;
		}
		else if(type==BRICK_STONE)
		{
			Brick b= new StoneBrick();
			b.type=b.BRICK_STONE;
			b.LTPosition=new PointF(x,y);
			return b;
		}
		else if(type==BRICK_NOEFFECT)
		{
			Brick b= new NoeffectBrick();
			b.type=b.BRICK_NOEFFECT;
			b.LTPosition=new PointF(x,y);
			return b;
		}
		else if(type==BRICK_KILL)
		{
			Brick b= new KillBrick();
			b.type=b.BRICK_KILL;
			b.LTPosition=new PointF(x,y);
			return b;
		}
		else if(type==BRICK_SUPER)
		{
			Brick b= new SupperBrick();
			b.type=b.BRICK_SUPER;
			b.LTPosition=new PointF(x,y);
			return b;
		}
		else if(type==BRICK_NORMAL2)
		{
			Brick b= new TwohitBrick();
			b.type=b.BRICK_NORMAL2;
			b.LTPosition=new PointF(x,y);
			return b;
		}
		else if(type==BRICK_NORMAL3)
		{
			Brick b= new ThreehitBrick();
			b.type=b.BRICK_NORMAL3;
			b.LTPosition=new PointF(x,y);
			return b;
		}
		else if(type==BRICK_NORMAL)
		{
			Brick b= new NormalBrick();
			b.type=b.BRICK_NORMAL;
			b.LTPosition=new PointF(x,y);
			return b;
		}	
		else
		{
			Brick b= new NormalBrick();
			b.type=b.BRICK_NORMAL;
			b.visible=false;
			b.LTPosition=new PointF(x,y);
			return b;
		}
	}
	/**
	 * 获取该砖块的类型
	 * @return
	 */
	public int getType()
	{
		return type;
	}
	/**
	 * 更改该砖块的类型，因为会导致type值与该对象的类型不符，所以慎用
	 * @param newtype 新类型
	 */
	public void AlterType(int newtype){
		type=newtype;
	}
	protected boolean visible=true;
	/**
	 * 获取该砖块左上角坐标，单位为px
	 * @return
	 */
	public PointF getLTPosition() {
		return LTPosition;
	}
	/**
	 * 设置该砖块的左上角坐标，单位为px
	 * @param lTPosition
	 */
	public void setLTPosition(PointF lTPosition) {
		LTPosition = lTPosition;
	}
	/**
	 * 获取该砖块是否已隐藏（通常被视为已被打掉）
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * 设置该砖块是否隐藏（通常被视为已被打掉）
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public abstract void Draw(Graphics g,Point offset);
	public abstract boolean Hit();
}
