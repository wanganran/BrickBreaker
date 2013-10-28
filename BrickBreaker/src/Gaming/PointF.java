package Gaming;
import java.awt.Point;

/**
 * 表示浮点点类
 * @author anran
 *
 */
public class PointF {
	
	public double x,y;
	/**
	 * 获取点的x坐标
	 * @return
	 */
	public double getX() {
		return x;
	}
	/**
	 * 设置点的x坐标
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * 获取点的y坐标
	 * @return
	 */
	public double getY() {
		return y;
	}
	/**
	 * 设置点的y坐标
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * 构造函数
	 * @param x
	 * @param y
	 */
	public PointF(double x,double y)
	{
		this.x=x;
		this.y=y;
		
	}
	public PointF(Point p)
	{
		this.x=p.x;
		this.y=p.y;
	}
	/**
	 * 转换为整数点类，取整数部分
	 * @return
	 */
	public Point toPoint()
	{
		Point res=new Point();
		res.x=(int)x;
		res.y=(int)y;
		return res;
	}
	/**
	 * 矢量加法
	 * @param p 被加数
	 * @return 和
	 */
	public PointF Plus(PointF p)
	{
		return new PointF(x+p.x,y+p.y);
	}
	/**
	 * 矢量数乘
	 * @param d 倍数
	 * @return 积
	 */
	public PointF Multiply(double d)
	{
		return new PointF(x*d,y*d);
	}
}
