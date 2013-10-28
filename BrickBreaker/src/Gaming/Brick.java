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
 * ��ʾһ��ש��ĳ����࣬��Ҫ���̳С�
 * @author anran
 *
 */
public abstract class Brick extends Container implements IDrawable{
	protected PointF LTPosition;
	/**
	 * ��ʾһ��ש��Ŀ�ȣ���λΪpx
	 */
	public static int Width;
	/**
	 * ��ʾһ��ש��ĺ�ȣ���λΪpx
	 */
	public static int Height;
	/**
	 * ��ʾש�����͡�
	 */
	protected int type;
	/**
	 * ��ש��
	 */
	protected int BRICK_BLANK=0;
	/**
	 * ��ͨש��
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
	 * ����type����һ��ש��
	 * @param type ש������
	 * @param x ש���λ�ã���λΪpx
	 * @param y ש���λ�ã���λΪpx
	 * @return ���ظ�ש��
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
	 * ��ȡ��ש�������
	 * @return
	 */
	public int getType()
	{
		return type;
	}
	/**
	 * ���ĸ�ש������ͣ���Ϊ�ᵼ��typeֵ��ö�������Ͳ�������������
	 * @param newtype ������
	 */
	public void AlterType(int newtype){
		type=newtype;
	}
	protected boolean visible=true;
	/**
	 * ��ȡ��ש�����Ͻ����꣬��λΪpx
	 * @return
	 */
	public PointF getLTPosition() {
		return LTPosition;
	}
	/**
	 * ���ø�ש������Ͻ����꣬��λΪpx
	 * @param lTPosition
	 */
	public void setLTPosition(PointF lTPosition) {
		LTPosition = lTPosition;
	}
	/**
	 * ��ȡ��ש���Ƿ������أ�ͨ������Ϊ�ѱ������
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * ���ø�ש���Ƿ����أ�ͨ������Ϊ�ѱ������
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public abstract void Draw(Graphics g,Point offset);
	public abstract boolean Hit();
}
