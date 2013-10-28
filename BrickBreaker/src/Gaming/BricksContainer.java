package Gaming;
import java.awt.Graphics;
import java.awt.Point;

import Bricks.StoneBrick;

/**
 * 砖块容器类，盛放所有砖块
 * @author anran
 *
 */
public class BricksContainer extends Container implements IDrawable,IUpdatable{
	public int top=20,left=0;
	private BricksMap intmap;
	private Brick[][] bricks;
	/**
	 * 构造函数
	 * @param map 通过砖块地图map来构造该容器及其内部的砖块
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
	 * 获取在第i行第j列的砖块。
	 * @param i 行
	 * @param j 列
	 * @return 若i,j符合要求，则返回该砖块，否则返回null
	 */
	public Brick getBrickByIndex(int i,int j)
	{
		if(i<0||j<0||i>=intmap.Height||j>=intmap.Width)return null;
		return bricks[i][j];
	}
	/**
	 * 返回在thing当前位置的砖块
	 * @param thing 任意可线性移动的对象
	 * @return 若thing的当前位置存在砖块，则返回该砖块；否则返回null
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
