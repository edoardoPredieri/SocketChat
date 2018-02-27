package GUI;

import javax.swing.*;
import Listener.Ascoltatore;
import java.awt.*;
import java.io.IOException;


public class Grafica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel prePannelloSopra=new JPanel();
	private JPanel prePannelloSotto=new JPanel();
	private JLabel istruzioni=new JLabel("Inserire: Nome Ip e Porta");
	private JTextField nome=new JTextField(15);
	private JTextField ip=new JTextField(15);
	private JTextField porta=new JTextField(15);
	private JButton b_connetti=new JButton("Connetti");
	private JScrollPane PannelloSopra;
	private JPanel PannelloSotto=new JPanel();
	private JTextField input=new JTextField(40);
	private JTextArea output=new JTextArea(40,30);
	private JButton b_invia=new JButton("invia");
	private Ascoltatore ascoltatore= new Ascoltatore(this);
	private int t=0;
	private String finalMessage="";
	
	
	public Grafica(){
		pre();
	}

	public void pre() {
		prePannelloSopra.setSize(300,200);
		prePannelloSotto.setLayout(new FlowLayout());
		prePannelloSopra.setLayout(new FlowLayout());
		
		nome.setBackground(Color.white);
		nome.setBorder(BorderFactory.createLineBorder(Color.black));
		nome.setEditable(true);
		
		ip.setBackground(Color.white);
		ip.setBorder(BorderFactory.createLineBorder(Color.black));
		ip.setEditable(true);
		
		porta.setBackground(Color.white);
		porta.setBorder(BorderFactory.createLineBorder(Color.black));
		porta.setEditable(true);
		
		b_connetti.addActionListener(ascoltatore);
		b_connetti.setActionCommand(Ascoltatore.CONNETTI);
		
		prePannelloSopra.add(istruzioni);
		prePannelloSotto.add(nome);
		prePannelloSotto.add(ip);
		prePannelloSotto.add(porta);
		prePannelloSotto.add(b_connetti);
		
		this.setTitle("Client_DI_Edoardo");
		this.getContentPane().add(prePannelloSopra,BorderLayout.NORTH);
		this.getContentPane().add(prePannelloSotto,BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public void post() throws IOException{
		output.setBackground(Color.white);
		output.setBorder(BorderFactory.createLineBorder(Color.black));
		output.setEditable(false);
		
		PannelloSopra=new JScrollPane(output,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		PannelloSotto.setLayout(new FlowLayout());
		
		input.setBackground(Color.white);
		input.setBorder(BorderFactory.createLineBorder(Color.black));
		input.setEditable(true);
		
		b_invia.addActionListener(ascoltatore);
		b_invia.setActionCommand(Ascoltatore.INVIA);
		
		PannelloSotto.add(input);
		PannelloSotto.add(b_invia);
		
		
		this.setTitle("SocketChat Client");
		this.remove(prePannelloSopra);
		this.remove(prePannelloSotto);
		this.getContentPane().add(PannelloSopra,BorderLayout.CENTER);
		this.getContentPane().add(PannelloSotto,BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	
	public JTextArea getOutput() {
		return output;
	}

	public void setOutput(String messaggio) {
		if(t<40) {
			if(! finalMessage.equals("")) {
				finalMessage=finalMessage+"\n"+messaggio;
			}
			else {
				finalMessage=messaggio;
			}
			
			this.output.setText(finalMessage);
			t++;
		}
		else {
			finalMessage="";
			t=0;
			this.output.setText(messaggio);
		}
	}

	public String getNome() {
		return nome.getText();
	}

	public String getIp() {
		return ip.getText();
	}

	public int getPorta() {
		int tmp=Integer.parseInt(porta.getText());
		return tmp;
	}

	public String getInput() {
		return input.getText();
	}
}
