package Gaming;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

/**
 * ��ʾ���Ϳ��Ի��Ƴ���
 * @author anran
 *
 */
public interface IDrawable {
	/**
	 * ��ͼ���������������ѯ�ú���������c�л�ͼ��	
	 * @param c һ��Graphics����������ͼ
	 * @param offset ��ʾ��ͼʱ��ƫ�������ڻ�ͼ��λʱһ��Ҫ���ϡ�
	 */
	public void Draw(Graphics c,Point offset);
}
