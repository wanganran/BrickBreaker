package Gaming;

import java.awt.Graphics;
import java.awt.Point;
/**
 * ������
 * @author anran
 *
 */
public class Board extends Container implements IDrawable{
	private int left;
	/**
	 * ��ȡ�����û���Ŀ��
	 */
	public static int Length=60;
	
	private static int thick=5;
	/**
	 * ��ȡ�����û��������Ϸ�±߿�ľ��룬��λΪpx
	 */
	public static int bottom=50;
	/**
	 * ��ȡ�����û����ƶ�ʱ���ٶȣ���λΪpx
	 */
	public static int speed=8;
	/**
	 * ��ȡ������߾�����Ϸ��߿�ľ��룬��λΪpx
	 * @return
	 */
	public int getLeft()
	{
		return left;
	}
	/**
	 * ײ������.
	 * @param thing ��һ�������˶��Ķ���
	 * @return ��thing����һ֡����֡���˶��켣��û����ཻ�򷵻�true�����򷵻�false
	 */
	public boolean Hit(ILinearMoving thing){
		if(thing.getPosition().y>=Game.GetCurrentGame().getHeight()-bottom)
			return GraphicUtil.cross(thing.getPosition(),thing.getPosition().Plus(thing.getDirection().Multiply(-10000)),new PointF(left,Game.GetCurrentGame().getHeight()-bottom),new PointF(left+Length,Game.GetCurrentGame().getHeight()-bottom));
		return false;
	}
	/**
	 * �ƶ��û���
	 * @param left ��Ϊtrue�������ƶ�speed�����������ƶ�
	 * @return ���ƶ��ɹ��򷵻�true�����򷵻�false
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
	 * ���캯��
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
