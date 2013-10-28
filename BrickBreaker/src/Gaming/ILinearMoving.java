package Gaming;
/**
 * 表示类型可以线性移动
 * @author anran
 *
 */
public interface ILinearMoving {
	/**
	 * 获取该对象的位置
	 * @return 该对象位置。
	 */
	public PointF getDirection();
	/**
	 * 获取该对象的行动方向
	 * @return 速度矢量，单位为px/s
	 */
	public PointF getPosition();
}
