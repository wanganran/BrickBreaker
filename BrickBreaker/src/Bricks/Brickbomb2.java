package Bricks;

import java.awt.Graphics;
import java.awt.Point;

import Gaming.Brick;
import Gaming.Game;
import Gaming.IUpdatable;
import Gaming.PointF;
import Gaming.Style;

import java.io.File;
import java.io.IOException;
import java.awt.Image;

import javax.imageio.ImageIO;

public class Brickbomb2 extends Brick implements IUpdatable{

	private Graphics g;
	private Point offse; 
	private int i;
	private int j;
	private int count=2;
	
	private int step;
	private Image[] bombimage;
	
	public Brickbomb2()
	{
		bombimage=new Image[8];
		step=0;
		try {
			bombimage[0]=ImageIO.read(new File("src/Images/1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bombimage[1]=ImageIO.read(new File("src/Images/2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bombimage[2]=ImageIO.read(new File("src/Images/3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bombimage[3]=ImageIO.read(new File("src/Images/4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bombimage[4]=ImageIO.read(new File("src/Images/5.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bombimage[5]=ImageIO.read(new File("src/Images/6.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bombimage[6]=ImageIO.read(new File("src/Images/7.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bombimage[7]=ImageIO.read(new File("src/Images/8.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int getcount()
	{
		return count;
	}
	@Override
	public void Draw(Graphics c, Point offset) {
		// TODO Auto-generated method stub
		this.g=c;
		this.offse=offset;
		if(visible&&count==2)
		{
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_BOMB2), offset.x, offset.y, this.Width, this.Height, null);
		}
		if(visible&&count==1)
		{
			c.drawImage(Style.getCurrentStyle().getBrickImage(this.BRICK_BOMB), offset.x, offset.y, this.Width, this.Height, null);
		}
		if(visible==false)
		{
			int x;
			x=(int)step/5;
			if(x<=7)
				c.drawImage(bombimage[x],offset.x-this.Width,offset.y-this.Height,this.Width*3,this.Height*4,null);
		}
			
	}

	@Override
	public boolean Hit() {
		this.i=((int) (this.getLTPosition().getY()-Game.GetCurrentGame().gameContainer.getBricksContainer().top))/this.Height;
		this.j=((int) (this.getLTPosition().getX()-Game.GetCurrentGame().gameContainer.getBricksContainer().left))/this.Width;
		if(count==0)
			return true;
		if(count==2)
		{
			count--;return true;
		}
		count--;	
		this.visible=false;
		for(int m=this.i-1;m<=this.i+1;m++)
		{
			for(int n=this.j-1;n<=this.j+1;n++)
			{
				if(Game.GetCurrentGame().gameContainer.getBricksContainer().getBrickByIndex(m, n)!=null)
				{
					if(Game.GetCurrentGame().gameContainer.getBricksContainer().getBrickByIndex(m, n).getType()==this.BRICK_STONE)
					{		
						Game.GetCurrentGame().gameContainer.getBricksContainer().getBrickByIndex(m, n).setVisible(true);
					}
					else if(Game.GetCurrentGame().gameContainer.getBricksContainer().getBrickByIndex(m, n).getType()==this.BRICK_BOMB)
					{
						Game.GetCurrentGame().gameContainer.getBricksContainer().getBrickByIndex(m, n).Hit();
					}
					else if(Game.GetCurrentGame().gameContainer.getBricksContainer().getBrickByIndex(m, n).getType()==this.BRICK_BOMB2)
					{
						Game.GetCurrentGame().gameContainer.getBricksContainer().getBrickByIndex(m, n).Hit();
						Game.GetCurrentGame().gameContainer.getBricksContainer().getBrickByIndex(m, n).Hit();
					}
					else
						Game.GetCurrentGame().gameContainer.getBricksContainer().getBrickByIndex(m, n).setVisible(false);
				}
			}
		}
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void Update(int timespan) {
		// TODO Auto-generated method stub
		if(step!=50)
		{
			if(this.visible==false)
			{				
				step++;				
			}
		}
	}
	

}
