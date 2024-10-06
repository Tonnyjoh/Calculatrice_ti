import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculatrice extends JFrame {
	private boolean selectedOperator = false;
	private boolean misyChiffre = false;
	private boolean calculTermine = false;
	private final JPanel conteneur = new JPanel();
	private final JPanel conteneur2 = new JPanel(new GridLayout(5, 4, 5, 5));  // Modifier ici pour un meilleur agencement
	private final JLabel ecranLabel = new JLabel(" ");
	private final JTextArea historiqueArea = new JTextArea(8, 20);
	private final BorderLayout borderLayout = new BorderLayout();
	private final String[] tabTouche = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/", "+/-"};
	private final JButton[] tabBouton = new JButton[tabTouche.length];
	private double chiffre = 0;
	private String str = " ";
	private String operateur = "";

	public void init() {
		// Configuration de l'écran principal (cadre)
		ecranLabel.setFont(new Font("Courier", Font.BOLD, 20));
		ecranLabel.setHorizontalAlignment(JLabel.RIGHT);
		ecranLabel.setPreferredSize(new Dimension(50, 50));
		ecranLabel.setOpaque(true);
		ecranLabel.setBackground(Color.LIGHT_GRAY);
		ecranLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		historiqueArea.setFont(new Font("Courier", Font.PLAIN, 16));
		historiqueArea.setEditable(false);
		historiqueArea.setBackground(Color.WHITE);
		historiqueArea.setBorder(BorderFactory.createTitledBorder("Historique"));
		JScrollPane scrollPane = new JScrollPane(historiqueArea);
		scrollPane.setPreferredSize(new Dimension(200, 150));  // Fixer une taille maximale

		conteneur.setLayout(borderLayout);
		conteneur2.setPreferredSize(new Dimension(300, 300));

		// Ajout des composants
		conteneur.add(ecranLabel, BorderLayout.NORTH);
		conteneur.add(scrollPane, BorderLayout.WEST); // Zone d'historique
		conteneur.add(conteneur2, BorderLayout.CENTER);
		conteneur.setBorder(BorderFactory.createLineBorder(Color.black));

		for (int i = 0; i < tabTouche.length; i++) {
			tabBouton[i] = new JButton(tabTouche[i]);
			tabBouton[i].setPreferredSize(new Dimension(50, 50));
			conteneur2.add(tabBouton[i]);
			tabBouton[i].addActionListener(new chiffreListener());

			// Associer les boutons avec les ActionListener correspondants
			switch (i) {
				case 11: // =
					tabBouton[i].addActionListener(new egalListener());
					break;
				case 12: // C
					tabBouton[i].setForeground(Color.red);
					tabBouton[i].addActionListener(new clearListener());
					break;
				case 13: // +
					tabBouton[i].addActionListener(new plusListener());
					break;
				case 14: // -
					tabBouton[i].addActionListener(new moinsListener());
					break;
				case 15: // *
					tabBouton[i].addActionListener(new foisListener());
					break;
				case 16: // /
					tabBouton[i].addActionListener(new divisionListener());
					break;
				case 17: // +/-
					tabBouton[i].addActionListener(new changeSignListener());
					break;
			}
		}
	}

	public Calculatrice() {
		init();
		this.setSize(450, 460);
		this.setTitle("Calculette");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(conteneur);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void calcul() {
		double input = Double.parseDouble(ecranLabel.getText());
		historiqueArea.append(chiffre + " " + operateur + " " + input + " = ");

		switch (operateur) {
			case "+":
				chiffre += input;
				break;
			case "-":
				chiffre -= input;
				break;
			case "*":
				chiffre *= input;
				break;
			case "/":
				if (input != 0) {
					chiffre /= input;
				} else {
					ecranLabel.setText("Erreur");
					historiqueArea.append("Erreur\n");
					return;
				}
				break;
		}
		ecranLabel.setText(String.valueOf(chiffre));
		historiqueArea.append(chiffre + "\n");
		selectedOperator = false;
		misyChiffre = false;
		calculTermine = true;
	}
	public void mametrakaValeur() {
		if (selectedOperator && !misyChiffre && !str.equals(" ")) {
			chiffre = Double.parseDouble(ecranLabel.getText());
			str = " ";
			ecranLabel.setText(str);
			misyChiffre = true;
		}
		if (selectedOperator && misyChiffre) {
			str = " ";
			ecranLabel.setText(str);
		}
	}

	// ActionListener pour les chiffres
	class chiffreListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (calculTermine) {
				str = "";
				calculTermine = false;
			}

			for (int i = 0; i < 11; i++) {
				if (e.getSource() == tabBouton[i]) {
					str += tabTouche[i];
					ecranLabel.setText(str);
				}
			}
		}
	}

	// ActionListeners pour les opérations
	class plusListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (selectedOperator) {
				calcul();
			}
			selectedOperator = true;
			mametrakaValeur();
			operateur = "+";
		}
	}

	class moinsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (selectedOperator) {
				calcul();
			}
			selectedOperator = true;
			mametrakaValeur();
			operateur = "-";
		}
	}

	class foisListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (selectedOperator) {
				calcul();
			}
			selectedOperator = true;
			mametrakaValeur();
			operateur = "*";
		}
	}

	class divisionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (selectedOperator) {
				calcul();
			}
			selectedOperator = true;
			mametrakaValeur();
			operateur = "/";
		}
	}

	// Listener pour le bouton = (égal)
	class egalListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (misyChiffre) {
				calcul();
			}
		}
	}

	// Listener pour le bouton C (clear)
	class clearListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			chiffre = 0;
			str = " ";
			operateur = "";
			misyChiffre = false;
			selectedOperator = false;
			calculTermine = false;
			ecranLabel.setText(" ");
		}
	}

	// Listener pour le changement de signe (+/-)
	class changeSignListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			double valeur = Double.parseDouble(ecranLabel.getText());
			valeur = -valeur;
			ecranLabel.setText(String.valueOf(valeur));
		}
	}

	public static void main(String[] args) {
		new Calculatrice();
	}
}
