/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VueCaptureSite extends JPanel{

	//------------------
	// Attributs
	//------------------
	public TestAspirateur laspirateur;
	
	public JLabel afficheURL;
	public JTextField url;
	
	public JLabel afficheCible;
	public JTextField cible;
	
	public JLabel vide;
	
	public JButton capturer;
	//------------------
	// Constructeurs
	//------------------
	public VueCaptureSite(TestAspirateur laAspirateur){
		this.laspirateur = laAspirateur;
		
		this.setLayout(new GridLayout(3,2));
		
		// Creation des elements du panneau
		afficheURL = new JLabel("URL :", SwingConstants.CENTER);
		afficheCible = new JLabel("Cible :", SwingConstants.CENTER);
		
		url = new JTextField(20);
		cible = new JTextField(20);
		
		vide = new JLabel();
		
		capturer = new JButton("Capturer");
		
		// Ajout des elements du panneau
		add(afficheURL);
		add(url);
		
		add(afficheCible);
		add(cible);
		
		add(vide);
		
		add(capturer);
		
		// Ajout des actions
		capturer.addActionListener(new ActionCapturerSite());
		
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	private class ActionCapturerSite implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			String lurl = url.getText();
			String lacible = cible.getText();
			
			laspirateur.setName(lacible);
			laspirateur.launchProcess(lurl);
			laspirateur.setLocal("C:/LMB2/Test/");
			laspirateur.launchCopy();
		}
	}
}
