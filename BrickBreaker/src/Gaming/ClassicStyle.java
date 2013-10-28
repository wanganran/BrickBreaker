package Gaming;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * ¾­µä·ç¸ñ
 * @author anran
 *
 */
public class ClassicStyle extends Style {
	Image[] bricklist;
	Image back,ball,board;
	public ClassicStyle()
	{
		bricklist=new Image[100];
	}
	@Override
	public Image getBrickImage(int type) {
		if(bricklist[type]==null){
			try {
				return bricklist[type]=ImageIO.read(new File("src/Images/"+Character.toString((char)('a'+type-1))+".png"));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		else return bricklist[type];
	}

	@Override
	public Image getBackground() {
		if(back==null){
			try {
				return back=ImageIO.read(new File("src/Images/back.png"));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		else return back;
		
	}

	@Override
	public Image getBall() {
		if(ball==null){
			try {
				return ball=ImageIO.read(new File("src/Images/ball.png"));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		else return ball;
	}

	@Override
	public Image getBoard() {
		if(board==null){
			try {
				return board=ImageIO.read(new File("src/Images/board.png"));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		else return board;
	}

}
