package plane;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class picture extends JPanel {
	ImageIcon icon = new ImageIcon("C:/Users/wfh/Desktop/飞机/pic1.jpg");
	Image img= icon.getImage();
	Image image;
	int i;
	HeroPlane heroplane=new HeroPlane();
	Bullet[] b=new Bullet[100];
	public picture(){
		heroplane.x=170;
		heroplane.y=500;
	//	while(b[i]=null){									//用数组管理子弹
		b[1]=new Bullet(0, 0);
		b[1].x=heroplane.x+(heroplane.width-18)/2;				//-18调整一下子弹的位置,对准飞机头
		b[1].y=heroplane.y-50;
	//	System.out.println(heroplane.width);
	}
	public Bullet NewBullet(Bullet b){
		b=new Bullet(heroplane.x+(heroplane.width-18)/2,heroplane.y-50);
		return b;
	}
	public void paintComponent(Graphics g){  		
		heroplane.step();
		g.drawImage(img, 0,0,340,620,this);							//设置大小和位置	
		for(int i=0;i<=b.length;i++){
		//	g.drawImage(b[1].iamge,b[1].x,b[1].y,this);					******子弹的管理*******
		}
		g.drawImage(heroplane.image,heroplane.x,heroplane.y,this);
    }
		
}
