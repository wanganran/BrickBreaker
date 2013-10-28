package Gaming;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 砖块地图类
 * @author anran
 *
 */
public class BricksMap {
	private int data[][];
	/**
	 * 地图横向的砖块数
	 */
	public static int Width=10;
	/**
	 * 地图纵向的砖块数
	 */
	public static int Height=10;
	public int Level;
	private BricksMap(){data=new int[Height][Width];}
	/**
	 * 根据path路径所指向的文件内容创建砖块地图
	 * @param path 文件路径。文件内容必须为10*10的字符矩阵，以回车来分隔相邻的两行。
	 * @return 返回解析后得到的砖块地图
	 * @throws IOException 若文件不存在或文件内容有错误，则引发该异常
	 */
	public static BricksMap LoadMap(String path,int level) throws IOException
	{
		java.io.InputStreamReader sr=new InputStreamReader(new FileInputStream(path));
		int tmp;
		BricksMap res=new BricksMap();
		for(int i=0;i<Height;i++)
			for(int j=0;j<Width;j++){
				while((tmp=sr.read())!=-1&&(tmp==13||tmp==10));
				res.data[i][j]=(tmp==' '?0:(tmp-'a'+1));
			}
		res.Level=level;
		return res;
	}
	/**
	 * 根据砖块的位置获取砖块类型
	 * @param x 砖块的横向位置（最左列为0）
	 * @param y 砖块的纵向位置（最上行为0）
	 * @return
	 */
	public int getBrick(int x,int y)
	{
		return data[y][x];
	}
}
