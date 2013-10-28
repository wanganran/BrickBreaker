package Gaming;
/**
 * 表示类型可以根据时间更新其内部逻辑
 * @author anran
 *
 */
public interface IUpdatable {
	/**
	 * 逻辑更新函数
	 * @param timespan 此帧距离上一帧的时间差，单位为ms。
	 */
	public void Update(int timespan);
}
