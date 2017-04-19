package plane;
import java.awt.Image;

public abstract class Flyer {
	int x;									//坐标
	int y;
	int width;
	int height;
	Image image;
	/* 
      	要求所有飞行物必须都能移动 
      	但移动的方式由子类自己实现 
     */  
	public abstract void step();						//实现动画效果，切换图片
	public abstract boolean OutOfBound();				//检查是否飞出窗口
	public static boolean boom(Flyer f1,Flyer f2){		//判断碰撞,和具体对象无关,即不是用对象调用的
		int x1=f1.x+f1.width/2;
		int y1=f1.y+f1.height/2;
		int x2=f2.x+f2.width/2;
		int y2=f2.y+f2.height/2;						//中心点
		boolean H=Math.abs(x1-x2)<(f1.width+f2.width)/2;	//abs取绝对值
		boolean V=Math.abs(y1-y2)<(f1.height+f2.height)/2;	//分别对横竖方向分析
		return H&&V;
	}
}
