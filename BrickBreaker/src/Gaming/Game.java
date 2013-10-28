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
 * 游戏类，用于游戏的逻辑控制
 * @author anran
 *
 */
public final class Game {
	Canvas canvas;
	Image img;
	Graphics g;
	/**
	 * 构造一个游戏，显示在c中。
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
	 * 获取或设置游戏的主体框架
	 */
	public BackContainer gameContainer;
	
	private long timetick;
	private int score;
	private int lives;
	private Integer level;
	/**
	 * 获取当前分数
	 * @return
	 */
	public int getScore()
	{
		return score;
	}
	/**
	 * 添加一定的分数
	 * @param extra 添加的分数
	 * @return 新分数
	 */
	public int AddScore(int extra)
	{
		return score+=extra;
	}
	/**
	 * 设置新的关卡并重置小球（见reset()）
	 * @param newlevel 新的关卡数
	 * @return 返回新的关卡数
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
	 * 减少n条生命；若剩余生命<0则游戏结束
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
	 * 一命呜呼，游戏结束
	 */
	public void LoseGame()
	{
		JOptionPane.showMessageDialog(canvas, "You lose! Your score is "+score+".");
		setStarted(false);
		// TODO Message
	}
	/**
	 * 重置小球位置并停留一小段时间
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
	 * 获取当前的关卡数
	 * @return
	 */
	public int getLevel(){return level;}
	/**
	 * 获取或设置当前的游戏，仅在第一次设置时使用
	 */
	public static Game current;
	/**
	 * 获取当前的游戏
	 * @return
	 */
	public static Game GetCurrentGame()
	{
		return current;
	}
	
	private boolean started;
	public boolean paused;
	/**
	 * 获取游戏是否已开始
	 * @return
	 */
	public boolean isStarted() {
		return started;
	}
	
	/**
	 * 绘制游戏中额外的图形图像，如分数、关卡等
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
	 * 设置游戏开始与否
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
	 * 游戏开始前及结束后显示欢迎界面
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
	 * 以map地图初始化游戏
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
	 * 完成该关卡并进入新的关卡
	 */
	public void FinishLevel()
	{
		setLevel(level+1);
		gameContainer.getBall().AddSpeed(1.1);
		
	}
	private int gamewidth=400,gameheight=600;
	/**
	 * 获取游戏界面的总宽度
	 * @return
	 */
	public int getWidth(){return gamewidth;}
	/**
	 * 获取游戏界面的总高度
	 * @return
	 */
	public int getHeight(){return gameheight;}
	/**
	 * 获取当前的生命数
	 * @return
	 */
	public int getLives() {
		return lives;
	}
	/**
	 * 设置生命数
	 * @param lives
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
}
