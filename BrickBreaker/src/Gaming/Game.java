package Gaming;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Handler;

import javax.swing.JOptionPane;

/**
 * ��Ϸ�࣬������Ϸ���߼�����
 * @author anran
 *
 */
public final class Game {
	Canvas canvas;
	Image img;
	Graphics g;
	/**
	 * ����һ����Ϸ����ʾ��c�С�
	 * @param c
	 */
	public Game(Canvas c)
	{
		canvas=c;
		img=c.createImage(c.getWidth(),c.getHeight());
		g=img.getGraphics();
		gamewidth=c.getWidth();
		gameheight=c.getHeight();
		this.setStarted(false);
	}
	/**
	 * ��ȡ��������Ϸ��������
	 */
	public BackContainer gameContainer;
	
	private long timetick;
	private int score;
	private int lives;
	private Integer level;
	/**
	 * ��ȡ��ǰ����
	 * @return
	 */
	public int getScore()
	{
		return score;
	}
	/**
	 * ���һ���ķ���
	 * @param extra ��ӵķ���
	 * @return �·���
	 */
	public int AddScore(int extra)
	{
		return score+=extra;
	}
	/**
	 * �����µĹؿ�������С�򣨼�reset()��
	 * @param newlevel �µĹؿ���
	 * @return �����µĹؿ���
	 */
	public int setLevel(int newlevel)
	{
		level=newlevel;
		synchronized(gameContainer){
		gameContainer.RemoveChild(gameContainer.getBricksContainer());
		try {
			gameContainer.AddChild(new BricksContainer(DataUtil.getMapByLevel(level)));
			reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		return level;
	}
	/**
	 * ����n����������ʣ������<0����Ϸ����
	 * @param n
	 */
	public void LoseLife(int n)
	{
		lives-=n;
		if(lives<=0){
			lives=0;
			LoseGame();
		}
		else
			reset();
	}
	/**
	 * һ���غ�����Ϸ����
	 */
	public void LoseGame()
	{
		JOptionPane.showMessageDialog(canvas, "You lose! Your score is "+score+".");
		setStarted(false);
		// TODO Message
	}
	/**
	 * ����С��λ�ò�ͣ��һС��ʱ��
	 */
	public void reset()
	{
		gameContainer.getBall().Reset();
		gameContainer.getBall().Pause();
		Thread th=new Thread(){
			@Override
			public void run()
			{
				synchronized(level){
					try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(gameContainer.getBall().getDirection().y>0)gameContainer.getBall().getDirection().y=-gameContainer.getBall().getDirection().y;
					gameContainer.getBall().Resume();
				}
			}
		};
		th.start();
	}
	/**
	 * ��ȡ��ǰ�Ĺؿ���
	 * @return
	 */
	public int getLevel(){return level;}
	/**
	 * ��ȡ�����õ�ǰ����Ϸ�����ڵ�һ������ʱʹ��
	 */
	public static Game current;
	/**
	 * ��ȡ��ǰ����Ϸ
	 * @return
	 */
	public static Game GetCurrentGame()
	{
		return current;
	}
	
	private boolean started;
	public boolean paused;
	/**
	 * ��ȡ��Ϸ�Ƿ��ѿ�ʼ
	 * @return
	 */
	public boolean isStarted() {
		return started;
	}
	
	/**
	 * ������Ϸ�ж����ͼ��ͼ����������ؿ���
	 * @param g
	 */
	public void Draw(Graphics g)
	{
		g.setColor(Color.lightGray);
		Font ft=g.getFont();
		ft.deriveFont(ft.BOLD, 15);
		g.drawString("Score: "+score, 5, 15);
		g.drawString("Level: "+level, gamewidth-60, 15);
		g.drawString("Lives: "+this.lives, 170, 15);
	}
	/**
	 * ������Ϸ��ʼ���
	 * @param started
	 */
	public void setStarted(boolean started) {
		//if(started==this.started)return;
		this.started = started;
		if(started)
		{
			score=0;
			lives=5;
			new Thread(){
				@Override
				public void run()
				{
					long last=System.currentTimeMillis();
					timetick=0;
					while(Game.this.started)
					{
						/*try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}*/
						
						gameContainer.Draw(g, new Point(0,0));
						Game.this.Draw(g);
						long newl=System.currentTimeMillis();
						if(!paused)gameContainer.Update((int)(newl-last));
						last=newl;
						timetick+=newl;
						if(paused)
						{
							Font of=g.getFont();
							g.setFont(g.getFont().deriveFont(80.0f));
							g.setColor(Color.yellow);
							g.drawString("Paused", 50,200);
							g.setColor(Color.LIGHT_GRAY);
							g.setFont(of);
						}

						canvas.getGraphics().drawImage(img, 0,0,canvas);
					}
				}
			}.start();
		}
		else
		{
			ShowWelcome();
		}
		
	}
	/**
	 * ��Ϸ��ʼǰ����������ʾ��ӭ����
	 */
	private void ShowWelcome() {
		g.drawImage(Style.getCurrentStyle().getBackground(),0, 0, 400, 600,null);
		try {
			g.drawImage(DataUtil.getImage("Images", "caption"),0, 100, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.setFont(g.getFont().deriveFont(Font.BOLD, 15));
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Click this window to start game!", 80, 300);
		Thread th=new Thread(){
			@Override
			public void run(){
				while(!isStarted())

					canvas.getGraphics().drawImage(img, 0,0,canvas);
			}
		};
		th.start();
		final MouseListener ml=new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Game.this.setStarted(true);
				
				Game.this.canvas.removeMouseListener(this);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				
			}
			
		};
		canvas.addMouseListener(ml);
	}
	/**
	 * ��map��ͼ��ʼ����Ϸ
	 * @param map
	 */
	public void Init(BricksMap map)
	{
		gameContainer=new BackContainer();
		
		gameContainer.AddChild(new BricksContainer(map));
		
		gameContainer.AddChild(new Board());
		gameContainer.AddChild(new Ball(200));
		
		level=map.Level;
	}
	/**
	 * ��ɸùؿ��������µĹؿ�
	 */
	public void FinishLevel()
	{
		setLevel(level+1);
		gameContainer.getBall().AddSpeed(1.1);
		
	}
	private int gamewidth=400,gameheight=600;
	/**
	 * ��ȡ��Ϸ������ܿ��
	 * @return
	 */
	public int getWidth(){return gamewidth;}
	/**
	 * ��ȡ��Ϸ������ܸ߶�
	 * @return
	 */
	public int getHeight(){return gameheight;}
	/**
	 * ��ȡ��ǰ��������
	 * @return
	 */
	public int getLives() {
		return lives;
	}
	/**
	 * ����������
	 * @param lives
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
}
