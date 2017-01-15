import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class HeroPlane extends Flyer{
	ImageIcon iconhero1 = new ImageIcon("C:/Users/wfh/Desktop/飞机/hero1.jpg");
	ImageIcon iconhero2 = new ImageIcon("C:/Users/wfh/Desktop/飞机/hero2.jpg");
	Image imghero1= iconhero1.getImage();
	Image imghero2= iconhero2.getImage();
	boolean doubleFire; 			//双倍火力子弹数，看不出来
	int life;
	int score;
	int height;									//bufferedImage.getHeight();不知道什么问题，手动输入吧
    int width;	
	//Image image;
	public HeroPlane() {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("C:/Users/wfh/Desktop/飞机/hero1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   								
		width=bufferedImage.getHeight();
		height=bufferedImage.getWidth();
	    doubleFire=false;
	    life=3;
	    score=0;
	}
	public Bullet[] shoot(){  
        Bullet[] bullets = null;  
        bullets = new Bullet[1];  
        bullets[0] = new Bullet(x + width/2 - 20/2,y - 20);  
        return bullets;  
    }  
	
	public int getLife(){
		return life;
	}
	public int getScore(){
		return score;
	}
	public void step() {
		Random r=new Random();
		if(r.nextInt(2)==0){
			image=imghero1;
		}
		else 
			image=imghero2;
	}

	@Override
	public boolean OutOfBound() {							//不会
		// TODO Auto-generated method stub
		return false;
	}
	 /** 
     * 英雄机随鼠标移动的方法 
     * 要求传入鼠标当前的位置 
     * @param x 鼠标位置的x坐标 
     * @param y 鼠标位置的y坐标 
     */  
	public void move(int x,int y){
		//传入的x，y是鼠标的坐标  
        //move的作用是让英雄机的中心位置和鼠标位置一致  
		this.x=x-width/2;
		this.y=y-height/2;
	}
	 /** 
     * 英雄机获得分数或奖励的方法 
     * @param f 是一个飞行物父类方法，可以指向敌机或者大飞机 
     */ 
	public void getScore_Award(Flyer f){
		//先判断敌人对象的类型  
		//if(f instanceof Airplane){						//敌机
		//获得敌机对象中的分数，加到当现分数上  
        //score =score+((Airplane)f).getScore()    
        		//下面加上奖励
		}
	}

