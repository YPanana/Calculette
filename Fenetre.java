import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame implements Observateur {
	
	/*
	 * Attributs
	 */
	
	//-- Elements de l'interface graphique --
	
	//Espace d'affichage
	private JLabel label = new JLabel();
	
	//Boutons de chiffres
	private JButton bouton0 = new JButton("0");
	private JButton bouton1 = new JButton("1");
	private JButton bouton2 = new JButton("2");
	private JButton bouton3 = new JButton("3");
	private JButton bouton4 = new JButton("4");
	private JButton bouton5 = new JButton("5");
	private JButton bouton6 = new JButton("6");
	private JButton bouton7 = new JButton("7");
	private JButton bouton8 = new JButton("8");
	private JButton bouton9 = new JButton("9");
	
	//Boutons d'operations
	private JButton boutonPlus    = new JButton("+");
	private JButton boutonMoins   = new JButton("-");
	private JButton boutonFois    = new JButton("*");
	private JButton boutonDiviser = new JButton("/");
	private JButton boutonEgal    = new JButton("=");
	
	//Autres boutons
	private JButton boutonVirgule = new JButton(".");
	private JButton boutonEffacer = new JButton("C");
	
	//Conteneurs
	private JPanel containerBoutonsChiffres   = new JPanel();
	private JPanel containerBoutonsOperations = new JPanel();
	
	//-- Elements du programme --
	//Indicateurs booleens
	private boolean operateurSelectionne = false;
	private boolean operande1Selectionne = false;
	private boolean pointSelectionne     = false;
	private boolean effacer              = false;
	
	//Notre classe Calculette qui effectue les calculs
	private Calculette calc;
	
	/*
	 * Constructeurs
	 */
	
	public Fenetre() {
		
		//Initialisation de la fenetre
		this.setTitle("Calculette"); 				         //Titre
		this.setSize(200, 250);      						 //Dimensions
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Comportement(le programme s'arrete lorsque l'on ferme la fenetre)
		this.setLocationRelativeTo(null);                    //Fenetre centree
		
		this.calc = new Calculette();
		calc.addObservateur(this);
		
		//Choix du Layout Manager Border Layout pour le Content Pane de la fenetre
		this.getContentPane().setLayout(new BorderLayout());
		
		//Espace d'affichage
		//Parametrage du JLabel
		Font police = new Font("DS-digital", Font.TYPE1_FONT, 20);    //Choix de la police
		label.setFont(police);
		label.setHorizontalAlignment(JLabel.RIGHT); 			      //Texte aligne a droite
		label.setText("0");                                           //Le chiffre 0 est affiche au debut du programme sur l'ecran de la calculette
		label.setBorder(BorderFactory.createLineBorder(Color.black)); //On trace la bordure de l'ecran
		this.getContentPane().add(label,BorderLayout.NORTH);          //On place l'ecran dans la partie nord de la fenetre
		
		//Boutons de chiffres, de la virgule et du resultat (bouton egal)
		//Preparation du conteneur qui va contenir ces boutons
		containerBoutonsChiffres.setLayout(new GridLayout(4,3)); //On choisit le layout GridLayout, une grille avec 4 lignes et 3 colonnes
		
		//Les boutons sont ecoutes par une instance de notre classe interne BoutonsListener
		bouton1.addActionListener(new BoutonsListener());
		bouton2.addActionListener(new BoutonsListener());
		bouton3.addActionListener(new BoutonsListener());
		bouton4.addActionListener(new BoutonsListener());
		bouton5.addActionListener(new BoutonsListener());
		bouton6.addActionListener(new BoutonsListener());
		bouton7.addActionListener(new BoutonsListener());
		bouton8.addActionListener(new BoutonsListener());
		bouton9.addActionListener(new BoutonsListener());
		bouton0.addActionListener(new BoutonsListener());
		boutonVirgule.addActionListener(new BoutonsListener());
		boutonEgal.addActionListener(new BoutonsListener());
		
		//On ajoute les boutons dans le conteneur
		containerBoutonsChiffres.add(bouton1);
		containerBoutonsChiffres.add(bouton2);
		containerBoutonsChiffres.add(bouton3);
		containerBoutonsChiffres.add(bouton4);
		containerBoutonsChiffres.add(bouton5);
		containerBoutonsChiffres.add(bouton6);
		containerBoutonsChiffres.add(bouton7);
		containerBoutonsChiffres.add(bouton8);
		containerBoutonsChiffres.add(bouton9);
		containerBoutonsChiffres.add(bouton0);
		containerBoutonsChiffres.add(boutonVirgule);
		containerBoutonsChiffres.add(boutonEgal);
		this.getContentPane().add(containerBoutonsChiffres,BorderLayout.CENTER); //On place le conteneur avec les boutons dans la partie centrale de la fenetre
		
		//Boutons d'operations
		//Preparation du conteneur
		containerBoutonsOperations.setLayout(new GridLayout(5,1)); //On choisit ici aussi une grille de 5 lignes et 1 colonne
		
		//Les boutons sont ecoutes par une instance de notre classe interne BoutonsListener
		boutonEffacer.addActionListener(new BoutonsListener());
		boutonPlus.addActionListener(new BoutonsListener());
		boutonMoins.addActionListener(new BoutonsListener());
		boutonFois.addActionListener(new BoutonsListener());
		boutonDiviser.addActionListener(new BoutonsListener());
		
		//On ajoute les boutons
		containerBoutonsOperations.add(boutonEffacer);
		containerBoutonsOperations.add(boutonPlus);
		containerBoutonsOperations.add(boutonMoins);
		containerBoutonsOperations.add(boutonFois);
		containerBoutonsOperations.add(boutonDiviser);
		this.getContentPane().add(containerBoutonsOperations,BorderLayout.EAST); //On place le conteneur dans la partie est de la fenetre
		
		//On affiche la fenetre
		this.setVisible(true);
	}
	
	/*
	 * Methodes
	 */
	@Override
	public void update(String s) {
		// TODO Auto-generated method stub
		if (s.endsWith(".0")) {
			s = s.replace(".0", "");
		}
		if (effacer) {
			this.label.setText(s);
		}
		else {
			String texte = this.label.getText();
			texte += s;
			this.label.setText(texte);
		}
	}
	
	/*
	 * Classes internes
	 */
	
	//Classe qui va ecouter tous les boutons et mettre a jour le contenu de l'ecran
	//en fonction des clics qui ont ete faits
	class BoutonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			String op1,op2;
			double temp;
			JButton boutonClique = (JButton)event.getSource();
			
			String valeur = boutonClique.getText();
			
			if (valeur.equals("0") || valeur.equals("1") || valeur.equals("2") || valeur.equals("3") ||
				valeur.equals("4") || valeur.equals("5") || valeur.equals("6") || valeur.equals("7") ||
				valeur.equals("8") || valeur.equals("9"))
			{
				if (operateurSelectionne) {
					effacer = true;
					operateurSelectionne = false;
				}
				else {
					if (label.getText().equals("0")) {
						effacer = true;
					}
					else {
						effacer = false;
					}
				}
				update(valeur);
			}
			else if (valeur.equals("+") || valeur.equals("-") || valeur.equals("/") || valeur.equals("*")) {
				operateurSelectionne = true;
				
				if (!operande1Selectionne) {
					op1 = label.getText();
					calc.setOperande1(Double.parseDouble(op1));
					calc.setOperateur(valeur);
					operande1Selectionne = true;
				}
				else {
					op1 = label.getText();
					temp = calc.getOperande1() + Double.parseDouble(op1);
					update(Double.toString(temp));
					calc.setOperande1(temp);
				}
			}
			else if (valeur.equals("=")) {
				operateurSelectionne = true;
				operande1Selectionne = false;
				
				op2 = label.getText();
				calc.setOperande2(Double.parseDouble(op2));
				calc.effectuerCalcul();
			}
			else if (valeur.equals(".")) {
				if (!pointSelectionne) {
					effacer = false;
					update(valeur);
					pointSelectionne = true;
				}
			}
			else if (valeur.equals("C")) {
				effacer = true;
				update("0");
				calc = new Calculette();
			}	
		}
	}
}
