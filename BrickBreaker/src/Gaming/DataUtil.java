package Gaming;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * �������ļ�IO���в����ľ�̬��
 * @author anran
 *
 */
public class DataUtil {
	/**
	 * ��ȡlevel�ؿ��ĵ�ͼ����
	 * @param level
	 * @return
	 * @throws IOException ��level����Ӧ�ĵ�ͼ�ļ������ڻ��ļ������������������쳣
	 */
	public static BricksMap getMapByLevel(int level) throws IOException
	{
		level=(level-1)%9+1;
		return BricksMap.LoadMap("src\\Maps\\"+level+".txt",level);
	}
	/**
	 * ����prefix+"/"+name+".png"��һ�ļ�·����ȡͼƬ
	 * @param prefix
	 * @param name
	 * @return
	 * @throws IOException ���ļ������ڻ�ͼƬ�����������쳣
	 */
	public static Image getImage(String prefix,String name) throws IOException
	{
		return ImageIO.read(new File("src/"+prefix+"/"+name+".png"));
	}
}
