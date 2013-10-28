package Gaming;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.omg.CORBA.portable.ApplicationException;

import java.awt.Canvas;
import java.awt.BorderLayout;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * 主窗口，用来显示游戏
 * @author anran
 *
 */
public class Main {

	private JFrame frmBricksbreaker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmBricksbreaker.setVisible(true);
					window.beginGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * 布置游戏容器并准备初始地图
	 * @throws IOException
	 */
	public void beginGame() throws IOException
	{
		Game.current=new Game(canvas);
		Game.current.Init(BricksMap.LoadMap("src/Maps/1.txt",1));
		//Game.GetCurrentGame().setStarted(true);
	}
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}
	private Canvas canvas;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBricksbreaker = new JFrame();
		
		
		MouseMotionListener mml=new MouseMotionListener(){
			private int last=-1;
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				if(!Game.GetCurrentGame().isStarted())return;
				if(last==-1){
					last=arg0.getX();
					Board b=Game.current.gameContainer.getBoard();
					b.Move(arg0.getX()-b.getLeft()-b.Length/2);
				}
				else
				{
					Game.current.gameContainer.getBoard().Move(arg0.getX()-last);
					last=arg0.getX();
				}
			}
			
		};
		frmBricksbreaker.addMouseMotionListener(mml);
		
		frmBricksbreaker.setTitle("BricksBreaker");
		frmBricksbreaker.setBounds(100, 100, 400, 600);
		frmBricksbreaker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		canvas = new Canvas();
		
		canvas.addMouseMotionListener(mml);
		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if(!Game.current.isStarted())return;
				if(arg0.getKeyCode()==KeyEvent.VK_RIGHT)
					Game.current.gameContainer.getBoard().Move(false);
				else if(arg0.getKeyCode()==KeyEvent.VK_LEFT)
					Game.current.gameContainer.getBoard().Move(true);
				else if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE)
					Game.current.paused=!Game.current.paused;
			}
		});
		frmBricksbreaker.getContentPane().add(canvas, BorderLayout.CENTER);
		
		
		
	}

}
