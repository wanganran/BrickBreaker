package Gaming;
import java.awt.Graphics;
import java.awt.Point;

import Bricks.StoneBrick;

/**
 * ש�������࣬ʢ������ש��
 * @author anran
 *
 */
public class BricksContainer extends Container implements IDrawable,IUpdatable{
	public int top=20,left=0;
	private BricksMap intmap;
	private Brick[][] bricks;
	/**
	 * ���캯��
	 * @param map ͨ��ש���ͼmap����������������ڲ���ש��
	 */
	public BricksContainer(BricksMap map)
	{
		this.name="BricksContainer";
		intmap=map;
		bricks=new Brick[map.Height][map.Width];
		Brick.Width=(Game.current.getWidth()-left*2)/map.Width;
		Brick.Height=(int)(Brick.Width*0.7);
		for(int i=0;i<map.Height;i++)
			for(int j=0;j<map.Width;j++)
				bricks[i][j]=Brick.createBrick(intmap.getBrick(j, i),left+j*Brick.Width,top+i*Brick.Height);
	}
	/**
	 * ��ȡ�ڵ�i�е�j�е�ש�顣
	 * @param i ��
	 * @param j ��
	 * @return ��i,j����Ҫ���򷵻ظ�ש�飬���򷵻�null
	 */
	public Brick getBrickByIndex(int i,int j)
	{
		if(i<0||j<0||i>=intmap.Height||j>=intmap.Width)return null;
		return bricks[i][j];
	}
	/**
	 * ������thing��ǰλ�õ�ש��
	 * @param thing ����������ƶ��Ķ���
	 * @return ��thing�ĵ�ǰλ�ô���ש�飬�򷵻ظ�ש�飻���򷵻�null
	 */
	public Brick getBrickByPos(ILinearMoving thing)
	{
		PointF v1=thing.getPosition();
		if(v1.x<left||v1.y<top||v1.x>=left+BricksMap.Width*Brick.Width||v1.y>=top+BricksMap.Height*Brick.Height)return null;
		return bricks[(int)((v1.y-top)/Brick.Height)][(int)((v1.x-left)/Brick.Width)];
	}
	@Override
	public void Draw(Graphics c, Point offset) {
		Point no=new Point(offset);
		for(int i=0;i<intmap.Height;i++)
			for(int j=0;j<intmap.Width;j++)
			{
				no.x=offset.x+left+Brick.Width*j;
				no.y=offset.y+top+Brick.Height*i;
				bricks[i][j].Draw(c, no);
			}
	}
	@Override
	public void Update(int timespan)
	{
		for(int i=0;i<intmap.Height;i++)
			for(int j=0;j<intmap.Width;j++)
			
				if(bricks[i][j] instanceof IUpdatable)
					((IUpdatable)(bricks[i][j])).Update(timespan);
	}
	public boolean isFinished()
	{
		for(int i=0;i<intmap.Height;i++)
			for(int j=0;j<intmap.Width;j++)
			{
				if(!(bricks[i][j] instanceof StoneBrick)&&bricks[i][j].visible)
					return false;
			}
		return true;
	}
}
