import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class HeroPlane extends Flyer{
	ImageIcon iconhero1 = new ImageIcon("C:/Users/wfh/Desktop/�ɻ�/hero1.jpg");
	ImageIcon iconhero2 = new ImageIcon("C:/Users/wfh/Desktop/�ɻ�/hero2.jpg");
	Image imghero1= iconhero1.getImage();
	Image imghero2= iconhero2.getImage();
	boolean doubleFire; 			//˫�������ӵ�������������
	int life;
	int score;
	int height;									//bufferedImage.getHeight();��֪��ʲô���⣬�ֶ������
    int width;	
	//Image image;
	public HeroPlane() {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("C:/Users/wfh/Desktop/�ɻ�/hero1.jpg"));
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
	public boolean OutOfBound() {							//����
		// TODO Auto-generated method stub
		return false;
	}
	 /** 
     * Ӣ�ۻ�������ƶ��ķ��� 
     * Ҫ������굱ǰ��λ�� 
     * @param x ���λ�õ�x���� 
     * @param y ���λ�õ�y���� 
     */  
	public void move(int x,int y){
		//�����x��y����������  
        //move����������Ӣ�ۻ�������λ�ú����λ��һ��  
		this.x=x-width/2;
		this.y=y-height/2;
	}
	 /** 
     * Ӣ�ۻ���÷��������ķ��� 
     * @param f ��һ�������︸�෽��������ָ��л����ߴ�ɻ� 
     */ 
	public void getScore_Award(Flyer f){
		//���жϵ��˶��������  
		//if(f instanceof Airplane){						//�л�
		//��õл������еķ������ӵ����ַ�����  
        //score =score+((Airplane)f).getScore()    
        		//������Ͻ���
		}
	}

