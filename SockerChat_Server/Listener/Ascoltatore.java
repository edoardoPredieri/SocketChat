package Listener;

import IO.avviaServer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import GUI.ServerGui;


public class Ascoltatore implements ActionListener {

	public String AVVIA=("avvia");
	public String STOP=("stop");
	private avviaServer server;
	ServerGui g;
	private int porta;
	
	public Ascoltatore(ServerGui g) {
		this.g=g;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()==AVVIA){
			porta=g.getPorta();
			System.out.println("AvvioIlserver");
			server=new avviaServer(porta);
			Thread t1=new Thread(server);
			t1.start();
			System.out.println("Server Avviato sulla porta: "+porta);
			
		}
		if(e.getActionCommand()==STOP){
			try {
				server.stopServer();
				System.out.println("Server chiuso con successo");
			} catch (IOException e1) {System.out.println("Errore di chiusura server");}
		}
	}
}
