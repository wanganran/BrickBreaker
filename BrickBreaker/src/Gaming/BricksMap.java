package Gaming;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ש���ͼ��
 * @author anran
 *
 */
public class BricksMap {
	private int data[][];
	/**
	 * ��ͼ�����ש����
	 */
	public static int Width=10;
	/**
	 * ��ͼ�����ש����
	 */
	public static int Height=10;
	public int Level;
	private BricksMap(){data=new int[Height][Width];}
	/**
	 * ����path·����ָ����ļ����ݴ���ש���ͼ
	 * @param path �ļ�·�����ļ����ݱ���Ϊ10*10���ַ������Իس����ָ����ڵ����С�
	 * @return ���ؽ�����õ���ש���ͼ
	 * @throws IOException ���ļ������ڻ��ļ������д������������쳣
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
	 * ����ש���λ�û�ȡש������
	 * @param x ש��ĺ���λ�ã�������Ϊ0��
	 * @param y ש�������λ�ã�������Ϊ0��
	 * @return
	 */
	public int getBrick(int x,int y)
	{
		return data[y][x];
	}
}
