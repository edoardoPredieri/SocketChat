package Client;

import java.net.Socket;
import java.util.Scanner;
import java.io.*;

import GUI.Grafica;

public class Client implements Runnable {
	
	private String nome;
	private String ip;
	private int porta;
	public Socket socket;
	public PrintWriter in;
	public Scanner scanner;
	public Grafica g;
	
	
	public Client(String nome, String ip, int porta,Grafica g) {
		super();
		this.nome=nome;
		this.ip=ip;
		this.porta=porta;
		this.g=g;
	}

	public void run() {
		try {
			socket=new Socket(ip,porta);
			InputStreamReader lettore=new InputStreamReader(socket.getInputStream());
			scanner=new Scanner(lettore);
			in= new PrintWriter(socket.getOutputStream());
		} catch (Exception e) {System.err.println("Errore di connessione"); }
		TreadAscolto();
	}
	
	public void TreadAscolto(){
		Thread t=new Thread(new ascolto());
		t.start();
	}
	
	public void inviaMessaggio(String messaggio){
		in.println("["+nome+"]"+" "+messaggio);
		in.flush();
	}
	
	
	
	public class ascolto implements Runnable{

		public void run() {
			String messaggio;
			while((messaggio=scanner.nextLine())!=null){
				System.out.println("[RICEVUTO] "+messaggio);
				g.setOutput(messaggio);
			}
		}
		
	}

	public void stop() throws IOException{
		in.close();
		scanner.close();
		socket.close();
	}

	public Socket getSocket() {
		return socket;
	}

	public PrintWriter getIn() {
		return in;
	}

	public Scanner getScanner() {
		return scanner;
	}
}
