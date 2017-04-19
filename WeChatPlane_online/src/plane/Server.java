package plane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.text.AbstractDocument.BranchElement;

public class Server{
	public static void main(String[] args) {
		new Server();
	}
	public Server(){
		go();
	}
	public void go(){
		try {
			ServerSocket ss=new ServerSocket(6666);
			Socket client1=ss.accept();
			Socket client2=ss.accept();
			Thread thread=new Thread(new HandleSession(client1, client2));
			System.out.println("联机成功");
			thread.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public class HandleSession implements Runnable{
		Socket client1;
		Socket client2;
		DataOutputStream d1;
		DataInputStream i1;
		DataOutputStream d2;
		DataInputStream i2;
		public HandleSession(Socket c,Socket c2){
			client1=c;
			client2=c2;
			try {
				d1=new DataOutputStream(client1.getOutputStream());
				i1=new DataInputStream(client1.getInputStream());
				d2=new DataOutputStream(client2.getOutputStream());
				i2=new DataInputStream(client2.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		public void run() {
			try{
			while(true){
				d1.writeInt(i2.readInt());
				d1.writeInt(i2.readInt());
				d2.writeInt(i1.readInt());
				d2.writeInt(i1.readInt());
				Thread.sleep(30);
			}
				}
			catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
	}
}