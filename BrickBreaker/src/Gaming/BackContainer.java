package Gaming;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * 总容器类，盛放游戏的全部组件
 * @author anran
 *
 */
public class BackContainer extends Container implements IDrawable,IUpdatable {
	/**
	 * 获取游戏中的球
	 * @return 游戏中的球，若游戏未开始则返回null.
	 */
	public Ball getBall()
	{
		return (Ball)(this.getChildrenByName("Ball"));
	}
	/**
	 * 获取游戏中的砖块集合
	 * @return 游戏中的方块集合，若游戏未开始则返回null.
	 */
	public  BricksContainer getBricksContainer()
	{
		return (BricksContainer)(this.getChildrenByName("BricksContainer"));
	}
	/**
	 * 获取游戏中的滑板
	 * @return 游戏中的滑板，若游戏未开始则返回null.
	 */
	public Board getBoard()
	{
		return (Board)(this.getChildrenByName("Board"));
	}
	/**
	 * 获取游戏中当前的道具集合
	 * @param type 道具类型，-1表示所有当前道具。
	 * @return
	 */
	public List<Extra> getCurrentExtrasByType(int type)
	{
		// TODO Finish Extras
		List<Extra> res=new ArrayList<Extra>();
		for(Container e:children)
		{
			if(e instanceof Extra&&(type==-1||((Extra)e).getType()==type))
			{
				res.add((Extra)e);
			}
		}
		return res;
	}
	/**
	 * 载入map中的地图并重置小球。
	 * @param map 地图对象
	 * @return 若成功，则返回true.若出现异常则返回false.
	 */
	public boolean Load(BricksMap map)
	{
		this.children.remove(getBricksContainer());
		BricksContainer newc=new BricksContainer(map);
		this.children.add(newc);
		Game.GetCurrentGame().setLevel(map.Level);
		return true;
	}
	@Override
	public void Update(int timespan) {
		try{
		for(Container c :children)
			if(c instanceof IUpdatable)
				((IUpdatable)c).Update(timespan);
		}
		catch(java.util.ConcurrentModificationException ex){}
	}
	@Override
	public void Draw(Graphics c, Point offset) {
		c.drawImage(Style.getCurrentStyle().getBackground(), 0, 0, Game.GetCurrentGame().getWidth(), Game.GetCurrentGame().getHeight(),null);
		for(Container g:children)
		{
			if(g instanceof IDrawable)
				((IDrawable)g).Draw(c, offset);
		}
	}
	
}
