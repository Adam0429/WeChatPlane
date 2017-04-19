package plane;
import java.awt.Image;

public abstract class Flyer {
	int x;									//����
	int y;
	int width;
	int height;
	Image image;
	/* 
      	Ҫ�����з�������붼���ƶ� 
      	���ƶ��ķ�ʽ�������Լ�ʵ�� 
     */  
	public abstract void step();						//ʵ�ֶ���Ч�����л�ͼƬ
	public abstract boolean OutOfBound();				//����Ƿ�ɳ�����
	public static boolean boom(Flyer f1,Flyer f2){		//�ж���ײ,�;�������޹�,�������ö�����õ�
		int x1=f1.x+f1.width/2;
		int y1=f1.y+f1.height/2;
		int x2=f2.x+f2.width/2;
		int y2=f2.y+f2.height/2;						//���ĵ�
		boolean H=Math.abs(x1-x2)<(f1.width+f2.width)/2;	//absȡ����ֵ
		boolean V=Math.abs(y1-y2)<(f1.height+f2.height)/2;	//�ֱ�Ժ����������
		return H&&V;
	}
}
