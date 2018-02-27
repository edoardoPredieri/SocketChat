package IO;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class avviaServer implements Runnable {
	
	private ServerSocket serversocket;
	public ArrayList<PrintWriter> tubi=new ArrayList<PrintWriter>();
	public ArrayList<ClientThread> clienti = new ArrayList<ClientThread>();
	private int porta;
	
	public avviaServer(int porta) {
		this.porta=porta;
	}

	public void run(){
		try { 
			serversocket=new ServerSocket(porta);
			while(true){
					Socket socket=serversocket.accept();
					System.out.println("Connesso");
					PrintWriter in =new PrintWriter(socket.getOutputStream());
					tubi.add(in);		
					ClientThread cliente =new ClientThread(socket,in,tubi);
					clienti.add(cliente);
					Thread t=new Thread(cliente);
					t.start();
			}
			
		}catch (IOException e){ System.err.println(e.getMessage());}
	}
	
	public void stopServer() throws IOException{
		serversocket.close();
		
		java.util.Iterator<ClientThread> i= clienti.iterator();
		while(i.hasNext()){
			ClientThread tmp=i.next();
			tmp.close();
		}
	}
}
