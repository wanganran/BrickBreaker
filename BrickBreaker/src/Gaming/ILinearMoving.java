package Gaming;
/**
 * ��ʾ���Ϳ��������ƶ�
 * @author anran
 *
 */
public interface ILinearMoving {
	/**
	 * ��ȡ�ö����λ��
	 * @return �ö���λ�á�
	 */
	public PointF getDirection();
	/**
	 * ��ȡ�ö�����ж�����
	 * @return �ٶ�ʸ������λΪpx/s
	 */
	public PointF getPosition();
}
