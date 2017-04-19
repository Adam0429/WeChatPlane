package plane;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet extends Flyer{
	int speed=10;
	static ImageIcon iconbullet = new ImageIcon("C:/Users/wfh/Desktop/飞机/bullet.jpg");
	static Image image= iconbullet.getImage();
	public Bullet(int x1,int y1){						//x1,y1应该是飞机中间最上的坐标
		x=x1;
		y=y1;
	}
	public void step() {
		y -= speed; 
	}


	public boolean OutOfBound() {
		// TODO Auto-generated method stub
		return false;
	}

}
