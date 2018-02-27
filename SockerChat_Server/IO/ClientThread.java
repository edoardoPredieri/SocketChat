package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ClientThread  implements Runnable {
	public BufferedReader reader;
	public PrintWriter in;
	public Socket socket;
	private  ArrayList<PrintWriter> tubi2;
	
	public ClientThread(Socket s,PrintWriter user, ArrayList<PrintWriter> tubi){
		in=user;
		tubi2=tubi;
		try{
			socket=s;
			InputStreamReader lettura=new InputStreamReader(socket.getInputStream());
			reader=new BufferedReader(lettura);
		}catch(Exception e){System.err.println("Errore Sconosciuto, stronzo.");}
	}
	
	public void run() {
		String messaggio;
		try{
			while((messaggio=reader.readLine()) != null){
				inviaMessaggio(messaggio);
			}
		}catch(Exception e){System.err.println("Errore di Connessione");}	
	}
	
	
	public void inviaMessaggio(String Messaggio){
		Iterator<PrintWriter> i =tubi2.iterator();
		
		while (i.hasNext()){
			try{
				PrintWriter writer = (PrintWriter) i.next();
				writer.println(Messaggio);
				writer.flush();
				System.out.println("Ho inviato: "+Messaggio);
				
			}catch(Exception e){System.err.println("Errore nell'invio del messsaggio");}
		}
	}
	
	public void close() throws IOException{
		reader.close();
		in.close();
		socket.close();
		tubi2=new ArrayList<PrintWriter>();
	}
}
