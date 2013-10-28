package Gaming;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * �������࣬ʢ����Ϸ��ȫ�����
 * @author anran
 *
 */
public class BackContainer extends Container implements IDrawable,IUpdatable {
	/**
	 * ��ȡ��Ϸ�е���
	 * @return ��Ϸ�е�������Ϸδ��ʼ�򷵻�null.
	 */
	public Ball getBall()
	{
		return (Ball)(this.getChildrenByName("Ball"));
	}
	/**
	 * ��ȡ��Ϸ�е�ש�鼯��
	 * @return ��Ϸ�еķ��鼯�ϣ�����Ϸδ��ʼ�򷵻�null.
	 */
	public  BricksContainer getBricksContainer()
	{
		return (BricksContainer)(this.getChildrenByName("BricksContainer"));
	}
	/**
	 * ��ȡ��Ϸ�еĻ���
	 * @return ��Ϸ�еĻ��壬����Ϸδ��ʼ�򷵻�null.
	 */
	public Board getBoard()
	{
		return (Board)(this.getChildrenByName("Board"));
	}
	/**
	 * ��ȡ��Ϸ�е�ǰ�ĵ��߼���
	 * @param type �������ͣ�-1��ʾ���е�ǰ���ߡ�
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
	 * ����map�еĵ�ͼ������С��
	 * @param map ��ͼ����
	 * @return ���ɹ����򷵻�true.�������쳣�򷵻�false.
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
