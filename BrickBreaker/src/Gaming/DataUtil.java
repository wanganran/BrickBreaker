package Gaming;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 用来对文件IO进行操作的静态类
 * @author anran
 *
 */
public class DataUtil {
	/**
	 * 获取level关卡的地图数据
	 * @param level
	 * @return
	 * @throws IOException 若level所对应的地图文件不存在或文件内容有误则引发该异常
	 */
	public static BricksMap getMapByLevel(int level) throws IOException
	{
		level=(level-1)%9+1;
		return BricksMap.LoadMap("src\\Maps\\"+level+".txt",level);
	}
	/**
	 * 根据prefix+"/"+name+".png"这一文件路径获取图片
	 * @param prefix
	 * @param name
	 * @return
	 * @throws IOException 若文件不存在或图片损坏则引发该异常
	 */
	public static Image getImage(String prefix,String name) throws IOException
	{
		return ImageIO.read(new File("src/"+prefix+"/"+name+".png"));
	}
}
