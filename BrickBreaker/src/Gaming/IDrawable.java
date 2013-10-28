package Gaming;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

/**
 * 表示类型可以绘制出来
 * @author anran
 *
 */
public interface IDrawable {
	/**
	 * 绘图函数，主程序会轮询该函数用来向c中画图。	
	 * @param c 一个Graphics对象用来画图
	 * @param offset 表示画图时的偏移量，在画图定位时一定要加上。
	 */
	public void Draw(Graphics c,Point offset);
}
