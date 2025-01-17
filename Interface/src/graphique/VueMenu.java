/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VueMenu extends JMenuBar {

	//------------------
	// Attributs
	//------------------
	TestAspirateur laspirateur;
	
	JMenu fichier;
	
	JMenuItem quitter;
	
	//------------------
	// Constructeurs
	//------------------
	public VueMenu(TestAspirateur laspirateur){
		super();
		this.laspirateur = laspirateur;
		
		// Creation des JMenu
		fichier = new JMenu("Fichier");
	
		// Creation des JMenuItem
		quitter = new JMenuItem("Quitter");
		
		// Ajout des JMenuItem
		fichier.add(quitter);
		
		// Ajout des JMenu
		add(fichier);
		
		// Ajout des Actions
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
	}
	
	//------------------
	// Methodes
	//------------------
	
	//--------------
	// Actions
	//--------------
	/*private class ActionLineCount implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
		}
	}*/
	
}
