package Gaming;
import java.util.ArrayList;

/**
 * 表示容器的抽象类，需要被继承
 * @author anran
 *
 */
public abstract class Container {
	/**
	 * 表示容器的名称，需要保证任意两容器的名称不同
	 */
	public String name;
	
	protected ArrayList<Container> children=new ArrayList<Container>();
	/**
	 * 根据容器名称查找容器
	 * @param name
	 * @return
	 */
	public Container getChildrenByName(String name){
		for(Container c:children)
		{
			if(c.name==name)
				return c;
		}
		return null;
	}
	/**
	 * 在容器内添加一个容器
	 * @param c
	 * @return
	 */
	public Container AddChild(Container c)
	{
		children.add(c);
		return c;
	}
	/**
	 * 在容器内删除一个容器
	 * @param c
	 * @return
	 */
	public Container RemoveChild(Container c)
	{
		children.remove(c);
		return c;
	}
	/**
	 * 获取容器内的子容器数量
	 * @return
	 */
	public int getChildrenCount()
	{
		return children.size();
	}
}
