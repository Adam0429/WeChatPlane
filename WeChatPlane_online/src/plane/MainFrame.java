package plane;



import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.security.KeyStore.TrustedCertificateEntry;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.x500.X500Principal;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JPanel{
	public static final int speed=8;
    public static final int WIDTH = 350;  
    public static final int HEIGHT = 660;  				 //游戏界面固定大小
    public static Image start; //开始图片  
    public static Image pause; //暂停图片  
    public static Image gameover; //游戏结束   
    public static final int START = 0;  
    public static final int RUNNING = 1;  
    public static final int PAUSE = 2;  
    public static final int GAME_OVER = 3;  
    static Flyer[] flyers={};
    static Flyer[] flyers2={};
    public static Bullet[] bullets = {};
    static HeroPlane heroplane=new HeroPlane();
    ImageIcon icon = new ImageIcon("C:/Users/wfh/Desktop/飞机/pic1.jpg");
	Image img= icon.getImage();
	ImageIcon icon2 = new ImageIcon("C:/Users/wfh/Desktop/飞机/pic2.jpg");
	Image img2= icon2.getImage();
	ImageIcon icon3 = new ImageIcon("C:/Users/wfh/Desktop/飞机/结束.jpg");
	Image img3= icon3.getImage();
	ImageIcon icon4 = new ImageIcon("C:/Users/wfh/Desktop/飞机/暂停.jpg");
	Image img4= icon4.getImage();
	ImageIcon iconp1 = new ImageIcon("C:/Users/wfh/Desktop/飞机/plane1.jpg");
	Image imgp1= iconp1.getImage();
	ImageIcon iconp2 = new ImageIcon("C:/Users/wfh/Desktop/飞机/plane2.jpg");
	Image imgp2= iconp2.getImage();
    //定义游戏状态：当前状态变量：默认为开始状态  
    private static int state;  
    //定义游戏状态的备选项常量：  
    static JFrame frame=new JFrame();
    static MainFrame m=new MainFrame();
    static MainFrame.MyListener mk=m.new MyListener();			//为了给addkeylistener传参	
    Timer timer = new Timer();  
    static int i = 1;    										//子弹数,因为b.length已经给定
    public static void main(String[] Args){
    	Thread thread=new Thread(heroplane);
    	thread.start();
    	frame.setTitle("微信打飞机");
		frame.setSize(WIDTH, HEIGHT);
		frame.getContentPane().add(m);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setLocationRelativeTo(null); 
        frame.addKeyListener(mk);
		frame.setVisible(true);							//这个方法会自动执行JPanel子类里的paint方法
    }
	public MainFrame(){
		/*写这段的时候，我想到用constructor 构造函数来直接定义一个主类给getcontentpane().add(),这样在
		主函数里new的时候，直接就执行了代码.但这样会造成死循环。因为我在构造函数里new了一个对象，这个对象的
		构造函数会一直new下一个对象，造成死循环，所以还是在主函数里new吧...
		*/
		initial();
		Timer timer = new Timer();     								
		timer.schedule(new MyTask(), 0, 20);							//用timertask解决每一段时间repaint一次
	}
	public void initial(){
		state=START;
		heroplane.x=170;
		heroplane.y=500;
	}
	
	@Override
	 public void paint(Graphics g) {  			//JPanel里的类，用setVisible会自动调用
		if(state==START){
			g.drawImage(img2, 0,0,340,620,this);					//设置大小和位置
		}
		if(state==RUNNING){
			g.drawImage(img, 0,0,340,620,this);					//设置大小和位置	
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
	 public void outline(HeroPlane h,String d) throws Exception{
		 switch(d){
		 case"a":
			 if(h.x<=0){
				 throw new Exception();
			 }
		 case"d":
			 if(h.x>=330){
				 throw new Exception();
			 }
		 }
		 
	 }
	 public class MyListener implements KeyListener{
		
		 public void keyPressed(KeyEvent e) {  
			 try{
		     if (e.getKeyCode() == KeyEvent.VK_DOWN) {  
	        	//outline(heroplane);
	        	heroplane.down=true;
	        } 
	        else if (e.getKeyCode() == KeyEvent.VK_UP) {  
	        	//outline(heroplane);
	        	heroplane.up=true;
	        } 
	        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {  
	        	//outline(heroplane,"d");
	        	heroplane.right=true;
	        } 
	        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {  
	        	//outline(heroplane,"a");
	        	heroplane.left=true;
	        } 	
	        else if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
	        	if(state==RUNNING)
	        		state=PAUSE;
	        	else if(state==PAUSE)
	        		state=RUNNING;
	        	else
	        		state=RUNNING;
	        }} 
	        catch (Exception exception){
	        	//exception.printStackTrace();
	        	}
	      
			
	        
	
	    }  
	 
		 
		public void keyTyped(KeyEvent e) {  
			//JOptionPane.showMessageDialog(null, "你键入了一个键");  
		}  
		
		public void keyReleased(KeyEvent e) {  
			     if (e.getKeyCode() == KeyEvent.VK_DOWN) {  
		        	//outline(heroplane);
		        	heroplane.down=false;
		        } 
		        else if (e.getKeyCode() == KeyEvent.VK_UP) {  
		        	//outline(heroplane);
		        	heroplane.up=false;
		        } 
		        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {  
		        	//outline(heroplane,"d");
		        	heroplane.right=false;
		        } 
		        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {  
		        	//outline(heroplane,"a");
		        	heroplane.left=false;
		        } 	
		    //JOptionPane.showMessageDialog(null, "你松开了键");  
		}  
	 }	
	 
	   public static void shoot(){  //这样写应该会越运行越卡，数组大小越来越大，可以把无用的子弹对象从数组移除
	        Bullet[] newBullets = heroplane.shoot(); //获得英雄机返回的新子弹数组  
	        /*
	         public static int[] copyOf(int[] original, int newLength) {  
        		int[] copy = new int[newLength];  
        		System.arraycopy(original, 0, copy, 0,Math.min(original.length, newLength));  
        		return copy;  
    		}  起到了扩充数组大小的作用
	         */
	        bullets = Arrays.copyOf(bullets, bullets.length + newBullets.length);   //根据返回新子弹的数量，扩容子弹数组  
	        //从newBullets数组中拷贝所有元素到bullets数组末尾  
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
		                    //从敌人数组中删除被击中的敌机  
		                    //step1： 使用敌人数组最后一个元素替换被击中的敌机  
		                    flyers[j] = flyers[flyers.length - 1];  
		                    //step2: 压缩数组  
		                    flyers = Arrays.copyOf(flyers, flyers.length - 1);  
		                    //从子弹数组中删除击中敌机的子弹  
		                    bullets[i] = bullets[bullets.length - 1];  
		                    bullets = Arrays.copyOf(bullets, bullets.length -1);  
		                    System.out.println("当前得分"+heroplane.score);
		                    i--; //第发现一次碰撞，子弹就要退一个元素，重新检测当前位置  
		                    break; //只要发现碰撞就退出当前敌人数组的循环  
		                }  
		            }  
		      for(int i1 = 0;i1 < bullets.length;i1++){  
			        for(int j = 0;j < flyers2.length;j++){  
			            if(Flyer.boom(bullets[i1], flyers2[j])){  
			            	heroplane.score=heroplane.score+2;
			            	//从敌人数组中删除被击中的敌机  
			                //step1： 使用敌人数组最后一个元素替换被击中的敌机  
			                flyers2[j] = flyers2[flyers2.length - 1];  
			                //step2: 压缩数组  
			                flyers2 = Arrays.copyOf(flyers2, flyers2.length - 1);  
			                //从子弹数组中删除击中敌机的子弹  
			                bullets[i1] = bullets[bullets.length - 1];  
			                bullets = Arrays.copyOf(bullets, bullets.length -1); 
			                System.out.println("当前得分"+heroplane.score);
			                i1--; //第发现一次碰撞，子弹就要退一个元素，重新检测当前位置  
			                break; //只要发现碰撞就退出当前敌人数组的循环  
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
					System.out.println("当前得分"+heroplane.score);
					System.out.println("当前生命"+heroplane.life);
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
					System.out.println("当前得分"+heroplane.score);
					System.out.println("当前生命"+heroplane.life);
				}
			}
		}
		static class MyTask extends TimerTask{		//计时任务必须要继承timertask	timertask里面只有run一种方法
			private int runTimes = 0;  								//	
			public void run(){
                    //每执行一次run方法，runTimes就+1  
                    runTimes++;  
                    if(runTimes % 10 == 0){ 
                    	shoot();			//每次都调用一下heroplane.shoot
                    }  
                    if(runTimes % 30 == 0){
                    	npc();
                    	npc2();
                    }													//物体移动
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
                    m.repaint();					//变量每秒都在变，画面也得跟上
	        }     
		}    
}
