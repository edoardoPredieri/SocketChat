package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Client.Client;
import GUI.Grafica;

public class Ascoltatore implements ActionListener{

	public static String CONNETTI="Connetti";
	public static String INVIA="Invia";
	Grafica g;
	private String nome;
	private String ip;
	private int porta;
	public Client client;
	
	
	public Ascoltatore(Grafica g) {
		super();
		this.g=g;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()==CONNETTI){
			
			try {
				g.post();
			} catch (IOException e1) {e1.printStackTrace();}
		
			nome=g.getNome();
			ip=g.getIp();
			porta=g.getPorta();	
			client = new Client(nome,ip,porta,g);
			client.run();
		}
		if(e.getActionCommand()==INVIA){
			client.inviaMessaggio(g.getInput());
		}
	}
	
	public int exit() throws IOException {
		client.stop();
		return 0;
	}
}
