package plane;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.security.spec.ECPrivateKeySpec;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLException;
import javax.swing.ImageIcon;

public class HeroPlane extends Flyer implements Runnable{
	public void run() {
		while(true){
			System.out.println(y);
			if(left){
				try {					//add these code to give some time to react,or the plane move too fast
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				x=x-1;
			}
			else if(right){
				try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				x=x+1;
			}
			else if(up){
				try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				y=y-1;
			}
			else if(down){
				try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				y=y+1;
			}
		}
	}
	ImageIcon iconhero1 = new ImageIcon("C:/Users/wfh/Desktop/�ɻ�/hero1.jpg");
	ImageIcon iconhero2 = new ImageIcon("C:/Users/wfh/Desktop/�ɻ�/hero2.jpg");
	Image imghero1= iconhero1.getImage();
	Image imghero2= iconhero2.getImage();
	public boolean left=false,right=false,down=false,up=false; 
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

