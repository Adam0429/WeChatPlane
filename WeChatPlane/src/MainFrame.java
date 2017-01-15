import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JPanel{
	public static final int speed=8;
    public static final int WIDTH = 350;  
    public static final int HEIGHT = 660;  				 //��Ϸ����̶���С
    public static Image start; //��ʼͼƬ  
    public static Image pause; //��ͣͼƬ  
    public static Image gameover; //��Ϸ����   
    public static final int START = 0;  
    public static final int RUNNING = 1;  
    public static final int PAUSE = 2;  
    public static final int GAME_OVER = 3;  
    static Flyer[] flyers={};
    static Flyer[] flyers2={};
    public static Bullet[] bullets = {};
    static HeroPlane heroplane=new HeroPlane();
    ImageIcon icon = new ImageIcon("C:/Users/wfh/Desktop/�ɻ�/pic1.jpg");
	Image img= icon.getImage();
	ImageIcon icon2 = new ImageIcon("C:/Users/wfh/Desktop/�ɻ�/pic2.jpg");
	Image img2= icon2.getImage();
	ImageIcon icon3 = new ImageIcon("C:/Users/wfh/Desktop/�ɻ�/����.jpg");
	Image img3= icon3.getImage();
	ImageIcon icon4 = new ImageIcon("C:/Users/wfh/Desktop/�ɻ�/��ͣ.jpg");
	Image img4= icon4.getImage();
	ImageIcon iconp1 = new ImageIcon("C:/Users/wfh/Desktop/�ɻ�/plane1.jpg");
	Image imgp1= iconp1.getImage();
	ImageIcon iconp2 = new ImageIcon("C:/Users/wfh/Desktop/�ɻ�/plane2.jpg");
	Image imgp2= iconp2.getImage();
    //������Ϸ״̬����ǰ״̬������Ĭ��Ϊ��ʼ״̬  
    private static int state;  
    //������Ϸ״̬�ı�ѡ�����  
    static JFrame frame=new JFrame();
    static MainFrame m=new MainFrame();
    static MainFrame.MyListener mk=m.new MyListener();			//Ϊ�˸�addkeylistener����	
    Timer timer = new Timer();  
    static int i = 1;    										//�ӵ���,��Ϊb.length�Ѿ�����
    public static void main(String[] Args){
    	frame.setTitle("΢�Ŵ�ɻ�");
		frame.setSize(WIDTH, HEIGHT);
		frame.getContentPane().add(m);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setLocationRelativeTo(null); 
        frame.addKeyListener(mk);
		frame.setVisible(true);							//����������Զ�ִ��JPanel�������paint����
    }
	public MainFrame(){
		/*д��ε�ʱ�����뵽��constructor ���캯����ֱ�Ӷ���һ�������getcontentpane().add(),������
		��������new��ʱ��ֱ�Ӿ�ִ���˴���.�������������ѭ������Ϊ���ڹ��캯����new��һ��������������
		���캯����һֱnew��һ�����������ѭ�������Ի�������������new��...
		*/
		initial();
		Timer timer = new Timer();     								
		timer.schedule(new MyTask(), 0, 20);							//��timertask���ÿһ��ʱ��repaintһ��
	}
	public void initial(){
		state=START;
		heroplane.x=170;
		heroplane.y=500;
	}
	
	@Override
	 public void paint(Graphics g) {  			//JPanel����࣬��setVisible���Զ�����
		if(state==START){
			g.drawImage(img2, 0,0,340,620,this);					//���ô�С��λ��
		}
		if(state==RUNNING){
			g.drawImage(img, 0,0,340,620,this);					//���ô�С��λ��	
			g.drawImage(heroplane.image,heroplane.x,heroplane.y,this);
			for(int i = 0;i < flyers.length;i++){  
				g.drawImage(flyers[i].image, flyers[i].x, flyers[i].y, null);  
		}  
			for(int i = 0;i < flyers2.length;i++){  
				g.drawImage(flyers2[i].image, flyers2[i].x, flyers2[i].y, null);  
			}  
			for(int i = 0;i < bullets.length;i++){  
				g.drawImage(Bullet.image, bullets[i].x, bullets[i].y, null);  
			}  
		}
		if(state==GAME_OVER){
			g.drawImage(img3, 0,0,340,620,this);
		}
		if(state==PAUSE){
			g.drawImage(img4, 0,0,340,620,this);
		}
	 }
	
	 public class MyListener implements KeyListener{
		 public void keyPressed(KeyEvent e) {  
	        if (e.getKeyCode() == KeyEvent.VK_DOWN) {  
	        	heroplane.y=heroplane.y+speed;
	        } 
	        else if (e.getKeyCode() == KeyEvent.VK_UP) {  
	        	heroplane.y=heroplane.y-speed;
	        } 
	        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {  
	        	heroplane.x=heroplane.x+speed;
	        } 
	        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {  
	        	heroplane.x=heroplane.x-speed;
	        } 	
	        else if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
	        	if(state==RUNNING)
	        		state=PAUSE;
	        	else if(state==PAUSE)
	        		state=RUNNING;
	        	else
	        		state=RUNNING;
	        } 	
	    }  
	 
		 
		public void keyTyped(KeyEvent e) {  
			//JOptionPane.showMessageDialog(null, "�������һ����");  
		}  
		
		public void keyReleased(KeyEvent e) {  
		    //JOptionPane.showMessageDialog(null, "���ɿ��˼�");  
		}  
	 }	
	 
	   public static void shoot(){  //����дӦ�û�Խ����Խ���������СԽ��Խ�󣬿��԰����õ��ӵ�����������Ƴ�
	        Bullet[] newBullets = heroplane.shoot(); //���Ӣ�ۻ����ص����ӵ�����  
	        /*
	         public static int[] copyOf(int[] original, int newLength) {  
        		int[] copy = new int[newLength];  
        		System.arraycopy(original, 0, copy, 0,Math.min(original.length, newLength));  
        		return copy;  
    		}  �������������С������
	         */
	        bullets = Arrays.copyOf(bullets, bullets.length + newBullets.length);   //���ݷ������ӵ��������������ӵ�����  
	        //��newBullets�����п�������Ԫ�ص�bullets����ĩβ  
	        System.arraycopy(newBullets, 0, bullets, bullets.length - newBullets.length, newBullets.length);  
	    }
	   	public static void npc(){
	   		int px=(int) (Math.random()*WIDTH);
	   		Smallplane[] s=new Smallplane[1];
	   		s[0]=new Smallplane(px,0);
	   		Flyer[] newFlyer=s;
		    flyers= Arrays.copyOf(flyers, flyers .length + newFlyer.length);
		    System.arraycopy(newFlyer, 0, flyers, flyers.length-newFlyer.length, newFlyer.length);
	   	}
		public static void npc2(){
	   		int px=(int) (Math.random()*WIDTH);
	   		Bigplane[] s=new Bigplane[1];
	   		s[0]=new Bigplane(px,0);
	   		Flyer[] newFlyer2=s;
		    flyers2= Arrays.copyOf(flyers2, flyers2 .length + 1);
		    System.arraycopy(newFlyer2, 0, flyers2, flyers2.length-newFlyer2.length, newFlyer2.length);
	   	}
		public static void boom(){
			 for(int i = 0;i < bullets.length;i++){  
		            for(int j = 0;j < flyers.length;j++){  
		                if(Flyer.boom(bullets[i], flyers[j])){  
		                    heroplane.score++;
		                    //�ӵ���������ɾ�������еĵл�  
		                    //step1�� ʹ�õ����������һ��Ԫ���滻�����еĵл�  
		                    flyers[j] = flyers[flyers.length - 1];  
		                    //step2: ѹ������  
		                    flyers = Arrays.copyOf(flyers, flyers.length - 1);  
		                    //���ӵ�������ɾ�����ел����ӵ�  
		                    bullets[i] = bullets[bullets.length - 1];  
		                    bullets = Arrays.copyOf(bullets, bullets.length -1);  
		                    System.out.println("��ǰ�÷�"+heroplane.score);
		                    i--; //�ڷ���һ����ײ���ӵ���Ҫ��һ��Ԫ�أ����¼�⵱ǰλ��  
		                    break; //ֻҪ������ײ���˳���ǰ���������ѭ��  
		                }  
		            }  
		      for(int i1 = 0;i1 < bullets.length;i1++){  
			        for(int j = 0;j < flyers2.length;j++){  
			            if(Flyer.boom(bullets[i1], flyers2[j])){  
			            	heroplane.score=heroplane.score+2;
			            	//�ӵ���������ɾ�������еĵл�  
			                //step1�� ʹ�õ����������һ��Ԫ���滻�����еĵл�  
			                flyers2[j] = flyers2[flyers2.length - 1];  
			                //step2: ѹ������  
			                flyers2 = Arrays.copyOf(flyers2, flyers2.length - 1);  
			                //���ӵ�������ɾ�����ел����ӵ�  
			                bullets[i1] = bullets[bullets.length - 1];  
			                bullets = Arrays.copyOf(bullets, bullets.length -1); 
			                System.out.println("��ǰ�÷�"+heroplane.score);
			                i1--; //�ڷ���һ����ײ���ӵ���Ҫ��һ��Ԫ�أ����¼�⵱ǰλ��  
			                break; //ֻҪ������ײ���˳���ǰ���������ѭ��  
			            }  
			        }  
		      	}
			 }
		}
		public static void hit(){
			for(int i=1;i<flyers.length;i++){
				if(Flyer.boom(heroplane,flyers[i])){
					flyers[i] = flyers[flyers.length - 1];  
					flyers = Arrays.copyOf(flyers, flyers.length - 1);  
					heroplane.life--;
					heroplane.score++;
					if(heroplane.life==0)
						state=GAME_OVER;
					System.out.println("��ǰ�÷�"+heroplane.score);
					System.out.println("��ǰ����"+heroplane.life);
				}
			}
			
			for(int i=0;i<flyers2.length;i++){
				if(Flyer.boom(heroplane,flyers2[i])){
					flyers2[i] = flyers2[flyers2.length - 1];  
					flyers2 = Arrays.copyOf(flyers2, flyers2.length - 1);  
					heroplane.life--;
					heroplane.score++;
					if(heroplane.life==0)
						state=GAME_OVER;
					System.out.println("��ǰ�÷�"+heroplane.score);
					System.out.println("��ǰ����"+heroplane.life);
				}
			}
		}
		static class MyTask extends TimerTask{		//��ʱ�������Ҫ�̳�timertask	timertask����ֻ��runһ�ַ���
			private int runTimes = 0;  								//	
			public void run(){
                    //ÿִ��һ��run������runTimes��+1  
                    runTimes++;  
                    if(runTimes % 10 == 0){ 
                    	shoot();			//ÿ�ζ�����һ��heroplane.shoot
                    }  
                    if(runTimes % 30 == 0){
                    	npc();
                    	npc2();
                    }													//�����ƶ�
                    for(int i = 0;i < flyers.length;i++){  
                        flyers[i].step(); 
                    }  
                    for(int i = 0;i < flyers2.length;i++){  
                    	flyers2[i].step(); 
                    }  
                    for(int i = 0;i < bullets.length;i++){  
                        bullets[i].step();   
                    }  
                    boom();
                    hit();
                    heroplane.step();
                    m.repaint();					//����ÿ�붼�ڱ䣬����Ҳ�ø���
	        }     
		}    
}
