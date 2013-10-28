package Gaming;
/**
 * 计算几何函数库
 * @author anran
 *
 */
public class GraphicUtil {
	private static double xmult(PointF p1,PointF p2,PointF p0){
		return (p1.x-p0.x)*(p2.y-p0.y)-(p2.x-p0.x)*(p1.y-p0.y);
	}

	private static double xmult(double x1,double y1,double x2,double y2,double x0,double y0){
		return (x1-x0)*(y2-y0)-(x2-x0)*(y1-y0);
	}
	private static double eps=1e-6;
	private static boolean zero(double x)
	{
		return ((x)>0?(x):-(x))<eps;
	}
	private static boolean dots_inline(double x1,double y1,double x2,double y2,double x3,double y3){
		return zero(xmult(x1,y1,x2,y2,x3,y3));
	}
	private static boolean dots_inline(PointF p1,PointF p2,PointF p3){
		return zero(xmult(p1,p2,p3));
	}

	private static boolean same_side(PointF p1,PointF p2,PointF l1,PointF l2){
		return xmult(l1,p1,l2)*xmult(l1,p2,l2)>eps;
	}
	private static boolean dot_online_in(PointF p,PointF l1,PointF l2){
		return zero(xmult(p,l1,l2))&&(l1.x-p.x)*(l2.x-p.x)<eps&&(l1.y-p.y)*(l2.y-p.y)<eps;
	}
	/**
	 * 判断u1到u2的线段与v1到v2的线段是否相交
	 * @param u1
	 * @param u2
	 * @param v1
	 * @param v2
	 * @return 若相交，则返回true；否则返回false
	 */
	public static boolean cross(PointF u1,PointF u2,PointF v1,PointF v2)
	{
		if (!dots_inline(u1,u2,v1)||!dots_inline(u1,u2,v2))
			return !same_side(u1,u2,v1,v2)&&!same_side(v1,v2,u1,u2);
		return dot_online_in(u1,v1,v2)||dot_online_in(u2,v1,v2)||dot_online_in(v1,u1,u2)||dot_online_in(v2,u1,u2);

	}
}
