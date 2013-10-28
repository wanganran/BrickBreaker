package Gaming;
import java.awt.Image;

/**
 * 表示界面图形图像的风格，抽象类，需要呗继承
 * @author anran
 *
 */
public abstract class Style {
	/** 
	 * 获取某砖块类型的图像
	 * @param type 砖块类型
	 * @return 返回该砖块类型所对应的砖块图片
	 */
	public abstract Image getBrickImage(int type);
	/**
	 * 获取背景图片
	 * @return 返回背景图片
	 */
	public abstract Image getBackground();
	/**
	 * 获取球的图片
	 * @return 返回球的图片
	 */
	public abstract Image getBall();
	/**
	 * 获取滑板的图片
	 * @return 返回滑板的图片
	 */
	public abstract Image getBoard();
	private static Style cstyle=new ClassicStyle();
	/**
	 * 获取当前的界面风格
	 * @return 返回当前的界面风格
	 */
	public static Style getCurrentStyle()
	{
		return cstyle;
	}
}
