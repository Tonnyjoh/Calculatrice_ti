import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculatrice extends JFrame{
	private boolean selectedOperator=false;
	private boolean misyChiffre=false;
	private	JPanel conteneur= new JPanel();
	private	JPanel conteneur2= new JPanel();
	private	JPanel conteneur3= new JPanel();
	private JLabel ecranLabel= new JLabel(" ");
	private BorderLayout borderLayout =new BorderLayout();
	private	String[] tabTouche= {"1","2","3","4","5","6","7","8","9","0",".","=","C","+","-","*","/"};
	private	JButton[] tabBouton= new JButton[tabTouche.length];
	private double chiffre;
	private String str=" ";
	private String operateur="";
	public void init() {
		
		ecranLabel.setFont(new Font("courier",Font.BOLD,20));
		ecranLabel.setHorizontalAlignment(JLabel.RIGHT);
		ecranLabel.setPreferredSize(new Dimension(50,50));
		conteneur.setLayout(borderLayout);
		conteneur2.setPreferredSize(new Dimension(40,50));
		conteneur3.setPreferredSize(new Dimension(70,50));
		conteneur.add(ecranLabel,BorderLayout.NORTH);
		conteneur.add(conteneur2,BorderLayout.CENTER);
		conteneur.add(conteneur3,BorderLayout.EAST);
		conteneur.setBorder(BorderFactory.createLineBorder(Color.black));
		for(int i=0;i<tabTouche.length;i++) {
			tabBouton[i]= new JButton(""+tabTouche[i]+"");
		//	tabBouton[i].setFont(new Font("Arial",Font.BOLD,20));
			tabBouton[i].setPreferredSize(new Dimension(65,55));
			conteneur2.add(tabBouton[i]);
			tabBouton[i].addActionListener(new chiffreListener());
			switch (i){
				case 11:
						conteneur2.add(tabBouton[i]);
						tabBouton[i].addActionListener(new egalListener());			
						break;
				case 12:
						conteneur3.add(tabBouton[i]);
						tabBouton[i].setForeground(Color.red);
						tabBouton[i].addActionListener(new clearListener());
						break;
				case 13:
						conteneur3.add(tabBouton[i]);
						tabBouton[i].addActionListener(new plusListener());
						break;
				case 14:
						conteneur3.add(tabBouton[i]);
						tabBouton[i].addActionListener(new moinsListener());
						break;	
				case 15:
						conteneur3.add(tabBouton[i]);
						tabBouton[i].addActionListener(new foisListener());
						break;
				case 16:
						conteneur3.add(tabBouton[i]);
						tabBouton[i].addActionListener(new divisionListener());
						break;	
			}
		}

		
	}
	public Calculatrice(){
		init();
		this.setSize(300,400);
		this.setTitle("Calculette");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(conteneur);
		this.setVisible(true);
	}
	public void calcul() {
		double chiffre2=chiffre;
			if(operateur=="+") {

				chiffre += Double.parseDouble(ecranLabel.getText()) ;
				System.out.println(""+chiffre2+" + "+ecranLabel.getText()+"= "+chiffre);
				ecranLabel.setText(""+chiffre);
				}
			if(operateur=="-") {

				chiffre -= Double.parseDouble(ecranLabel.getText());
				System.out.println(""+chiffre2+" - "+ecranLabel.getText()+"= "+chiffre);
				ecranLabel.setText(""+chiffre);
			}
			if(operateur=="*") {

				chiffre *= Double.parseDouble(ecranLabel.getText());
				System.out.println(""+chiffre2+" * "+ecranLabel.getText()+"= "+chiffre);
				ecranLabel.setText(""+chiffre);
			
		}
			if(operateur=="/") {
				try {
					chiffre /= Double.parseDouble(ecranLabel.getText()) ;
					System.out.println(""+chiffre2+" / "+ecranLabel.getText()+"= "+chiffre);
					ecranLabel.setText(""+chiffre);
				}
				catch(ArithmeticException e) {
					ecranLabel.setText("0");
				}
			}
			
	}
	public void mametrakaValeur() {
		if(selectedOperator && !misyChiffre && str!= " " ) {
			chiffre= Double.parseDouble(ecranLabel.getText());
			str=" ";
			ecranLabel.setText(str);
			misyChiffre=true;
		}
		if(selectedOperator && misyChiffre) {
			str=" ";
			ecranLabel.setText(str);
			
		}
	}
	class chiffreListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i=0;i<11;i++) {
					str= ecranLabel.getText();
					if(e.getSource()==tabBouton[i]) {
							str += tabTouche[i];
							ecranLabel.setText(str);
						}
					
				 }
			}
			
	}
		
	
	
	class plusListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedOperator=true;
			mametrakaValeur();
			operateur= "+";
			
		}
		
	
	}
	class moinsListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedOperator=true;
			mametrakaValeur();
			operateur= "-";
			
		}
		
	}class foisListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedOperator=true;
			mametrakaValeur();
			operateur= "*";
			
		}
		
	}class divisionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedOperator=true;
			mametrakaValeur();
			operateur= "/";
		
		}
		
	}
	class clearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
				misyChiffre=false;
				ecranLabel.setText(" ");
		}
		
	}

	class egalListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(misyChiffre) {
				calcul();
			}
		
		}
			
	}
		
	
	public static void main(String[] args) {
		Calculatrice calculatrice= new Calculatrice();

	}

}
