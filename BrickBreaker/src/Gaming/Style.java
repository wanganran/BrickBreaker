package Gaming;
import java.awt.Image;

/**
 * ��ʾ����ͼ��ͼ��ķ�񣬳����࣬��Ҫ�¼̳�
 * @author anran
 *
 */
public abstract class Style {
	/** 
	 * ��ȡĳש�����͵�ͼ��
	 * @param type ש������
	 * @return ���ظ�ש����������Ӧ��ש��ͼƬ
	 */
	public abstract Image getBrickImage(int type);
	/**
	 * ��ȡ����ͼƬ
	 * @return ���ر���ͼƬ
	 */
	public abstract Image getBackground();
	/**
	 * ��ȡ���ͼƬ
	 * @return �������ͼƬ
	 */
	public abstract Image getBall();
	/**
	 * ��ȡ�����ͼƬ
	 * @return ���ػ����ͼƬ
	 */
	public abstract Image getBoard();
	private static Style cstyle=new ClassicStyle();
	/**
	 * ��ȡ��ǰ�Ľ�����
	 * @return ���ص�ǰ�Ľ�����
	 */
	public static Style getCurrentStyle()
	{
		return cstyle;
	}
}
