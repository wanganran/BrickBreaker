package Gaming;
import java.awt.Point;

/**
 * ��ʾ�������
 * @author anran
 *
 */
public class PointF {
	
	public double x,y;
	/**
	 * ��ȡ���x����
	 * @return
	 */
	public double getX() {
		return x;
	}
	/**
	 * ���õ��x����
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * ��ȡ���y����
	 * @return
	 */
	public double getY() {
		return y;
	}
	/**
	 * ���õ��y����
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * ���캯��
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
	 * ת��Ϊ�������࣬ȡ��������
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
	 * ʸ���ӷ�
	 * @param p ������
	 * @return ��
	 */
	public PointF Plus(PointF p)
	{
		return new PointF(x+p.x,y+p.y);
	}
	/**
	 * ʸ������
	 * @param d ����
	 * @return ��
	 */
	public PointF Multiply(double d)
	{
		return new PointF(x*d,y*d);
	}
}
