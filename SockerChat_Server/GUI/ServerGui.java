package GUI;

import javax.swing.*;
import Listener.Ascoltatore;

import java.awt.*;

public class ServerGui extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel pannello=new JPanel();
	private JButton b_avvia=new JButton("Avvia");
	private JButton b_stop=new JButton("Stop");
	private JPanel pannelloSopra=new JPanel();
	private JPanel pannelloSopraSup=new JPanel();
	private JPanel pannelloSopraInf=new JPanel();
	private JPanel pannelloSotto=new JPanel();
	private JLabel testo=new JLabel("SocketChat Server");
	private JLabel textPorta=new JLabel("Porta:");
	private JTextField getPorta=new JTextField(3);
	private Ascoltatore ascoltatore=new Ascoltatore(this);
	
	
	public ServerGui(){
	
	this.getContentPane().setLayout(new BorderLayout());	
	this.add(pannello,BorderLayout.CENTER);
	this.setSize(200,200);
	

	pannelloSopra.setLayout(new BorderLayout());
	
	pannelloSopraSup.add(testo);
	pannelloSopraInf.add(textPorta);
	pannelloSopraInf.add(getPorta);
	
	pannelloSopra.add(pannelloSopraSup,BorderLayout.NORTH);
	pannelloSopra.add(pannelloSopraInf, BorderLayout.SOUTH);
	
	pannelloSotto.add(b_avvia);
	pannelloSotto.add(b_stop);
	
	b_avvia.addActionListener(ascoltatore);
	b_avvia.setActionCommand(ascoltatore.AVVIA);
	b_stop.addActionListener(ascoltatore);
	b_stop.setActionCommand(ascoltatore.STOP);
	
	
	pannello.add(pannelloSopra,BorderLayout.NORTH);
	pannello.add(pannelloSotto, BorderLayout.SOUTH);
	
	this.setTitle("SocketChat Server");
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	public int getPorta() {
		String tmp=getPorta.getText();
		return Integer.parseInt(tmp);
	}
}
