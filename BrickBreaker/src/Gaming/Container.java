package Gaming;
import java.util.ArrayList;

/**
 * ��ʾ�����ĳ����࣬��Ҫ���̳�
 * @author anran
 *
 */
public abstract class Container {
	/**
	 * ��ʾ���������ƣ���Ҫ��֤���������������Ʋ�ͬ
	 */
	public String name;
	
	protected ArrayList<Container> children=new ArrayList<Container>();
	/**
	 * �����������Ʋ�������
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
	 * �����������һ������
	 * @param c
	 * @return
	 */
	public Container AddChild(Container c)
	{
		children.add(c);
		return c;
	}
	/**
	 * ��������ɾ��һ������
	 * @param c
	 * @return
	 */
	public Container RemoveChild(Container c)
	{
		children.remove(c);
		return c;
	}
	/**
	 * ��ȡ�����ڵ�����������
	 * @return
	 */
	public int getChildrenCount()
	{
		return children.size();
	}
}
