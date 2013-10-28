package Gaming;

import java.awt.Graphics;
import java.awt.Point;
/**
 * 滑板类
 * @author anran
 *
 */
public class Board extends Container implements IDrawable{
	private int left;
	/**
	 * 获取或设置滑板的宽度
	 */
	public static int Length=60;
	
	private static int thick=5;
	/**
	 * 获取或设置滑板距离游戏下边框的距离，单位为px
	 */
	public static int bottom=50;
	/**
	 * 获取或设置滑板移动时的速度，单位为px
	 */
	public static int speed=8;
	/**
	 * 获取板最左边距离游戏左边框的距离，单位为px
	 * @return
	 */
	public int getLeft()
	{
		return left;
	}
	/**
	 * 撞击函数.
	 * @param thing 任一可线性运动的对象
	 * @return 若thing的上一帧到该帧的运动轨迹与该滑板相交则返回true，否则返回false
	 */
	public boolean Hit(ILinearMoving thing){
		if(thing.getPosition().y>=Game.GetCurrentGame().getHeight()-bottom)
			return GraphicUtil.cross(thing.getPosition(),thing.getPosition().Plus(thing.getDirection().Multiply(-10000)),new PointF(left,Game.GetCurrentGame().getHeight()-bottom),new PointF(left+Length,Game.GetCurrentGame().getHeight()-bottom));
		return false;
	}
	/**
	 * 移动该滑板
	 * @param left 若为true则向左移动speed，否则向右移动
	 * @return 若移动成功则返回true，否则返回false
	 */
	public boolean Move(boolean left){
		if(left){
			if(this.left>=speed)this.left-=speed;
			else return false;
		}
		else{
			if(this.left+this.Length<=Game.GetCurrentGame().getWidth()-speed)this.left+=speed;
			else return false;
		}
		return true;		
	}
	public boolean Move(int delta)
	{
		this.left+=delta;
		this.left=Math.min(Game.GetCurrentGame().getWidth()-this.Length,this.left);
		this.left=Math.max(0, this.left);
		return true;
	}
	/**
	 * 构造函数
	 */
	public Board()
	{
		this.name="Board";
		this.left=(Game.GetCurrentGame().getWidth()-Length)/2;
	}
	@Override
	public void Draw(Graphics c, Point offset) {
		c.drawImage(Style.getCurrentStyle().getBoard(), left, Game.GetCurrentGame().getHeight()-bottom, Length,thick,null);
	}
	
}
