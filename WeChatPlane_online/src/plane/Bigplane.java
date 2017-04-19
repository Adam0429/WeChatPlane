package plane;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Bigplane extends Flyer{
	int speed=5;
	ImageIcon icon = new ImageIcon("C:/Users/wfh/Desktop/·É»ú/plane2.jpg"); 
		public Bigplane (int x1,int y1){
		x=x1;
		y=y1;
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("C:/Users/wfh/Desktop/·É»ú/plane2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		image= icon.getImage();						
		width=bufferedImage.getHeight();
		height=bufferedImage.getWidth();
	}
	@Override
	public void step() {
		y+=speed;
		
	}
	@Override
	public boolean OutOfBound() {
		// TODO Auto-generated method stub
		return false;
	}

}