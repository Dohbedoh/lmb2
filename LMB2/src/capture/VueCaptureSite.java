/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package capture;

import Aspirateur.*;
import graphique.VueMetaDonnees;
import graphique.VueOnglets;
import graphique.VueProgressBar;
import graphique.VueSauvegarde;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VueCaptureSite extends JPanel implements Observer{

	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	public VueProgressBar vueProgressBar;
	private Thread t;
	public VueSauvegarde vueSauvegarde;
	
	// CAPTURE
	public JLabel afficheURL;
	public JTextField url;
	
	public JLabel afficheNom;
	public JTextField nom;
	
	public JLabel affichePath;
	public JTextField path;
	
	public JButton capturer;
	public JButton stop;
	public JButton pause;
	public JButton reprendre;
	public JButton parcourir;
	
	// CONTRAINTES
	public JLabel afficheProfondeur;
	public JLabel afficheThreadP;
	public JLabel afficheThreadR;
	public JLabel afficheVolume;
	public JLabel afficheVolumePage;
	public JLabel afficheVolumeRessource;
	
	public VueContraintes contraintesProfondeur;
	public VueContraintes contraintesThreadP;
	public VueContraintes contraintesThreadR;
	public JTextField contraintesVolume;
	public JTextField contraintesVolumePage;
	public JTextField contraintesVolumeRessources;
	public JButton optionsAvancées;
	
	public VueFiltres vueFiltres;
	public VueMetaDonnees vueMeta;
	public VueOnglets vueOnglets;
	
	//------------------
	// Constructeurs
	//------------------
	public VueCaptureSite(Aspirateur laspirateur, VueProgressBar vueProgressBar, VueOnglets vueOnglets){
		this.laspirateur = laspirateur;
		this.vueProgressBar = vueProgressBar;
		this.vueOnglets = vueOnglets;
		
		optionsAvancées = new JButton("Authentification...");
		optionsAvancées.addActionListener(new ActionOptionsAvancees());
		contraintesProfondeur = new VueContraintes(laspirateur, -1, 15);
		//contraintesProfondeur.setMinimumSize(new Dimension(80,20));
		contraintesThreadP = new VueContraintes(laspirateur, 1, 5);
		contraintesThreadP.setValue(1);
		//contraintesThreadP.setMinimumSize(new Dimension(80,20));
		contraintesThreadR = new VueContraintes(laspirateur, 1, 5);
		//contraintesThreadR.setMinimumSize(new Dimension(80,20));
		contraintesThreadR.setValue(2);
		contraintesVolume = new JTextField("Infini");
		contraintesVolume.setPreferredSize(new Dimension(80,20));
		contraintesVolumePage = new JTextField("Infini");
		contraintesVolumePage.setPreferredSize(new Dimension(80,20));
		contraintesVolumeRessources = new JTextField("Infini");
		contraintesVolumeRessources.setPreferredSize(new Dimension(80,20));
		vueFiltres = new VueFiltres(laspirateur);
		vueMeta = new VueMetaDonnees(laspirateur);
		

		
		laspirateur.addObserver(this);
		this.setLayout(new BorderLayout());
		
		// Creation des elements du panneau
		afficheURL = new JLabel("URL du site : ", SwingConstants.RIGHT);
		afficheNom = new JLabel("Nom du site : ", SwingConstants.RIGHT);
		affichePath = new JLabel("Workspace  : ", SwingConstants.RIGHT);
		
		url = new JTextField("http://www.renaudmathieu.fr/lmb2/Site01/");
		nom = new JTextField(laspirateur.getName());
		path = new JTextField(laspirateur.getPath());
		
		capturer = new JButton("Capturer");
		stop = new JButton("Stop");
		pause = new JButton("Pause");
		reprendre = new JButton("Reprendre");
		
		parcourir = new JButton("...");
		parcourir.setToolTipText("Changer de workspace");
		
		capturer.setToolTipText("Capturer le site");
		capturer.setForeground(Color.red);
		stop.setForeground(Color.red);
		pause.setForeground(Color.blue);
		reprendre.setForeground(Color.green);
		
		
		// Ajout des elements du panneau
		JPanel capture = new JPanel();
		Container capGauche = new Container();
		capture.setLayout(new BorderLayout());
		
		Container butCont = new Container();
		butCont.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		butCont.add(capturer);
		butCont.add(stop);
		butCont.add(reprendre);
		butCont.add(pause);
		butCont.add(parcourir);
		
		GroupLayout layout = new GroupLayout(capGauche);
		capGauche.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(afficheURL)
	                    	.addComponent(afficheNom)
	                    	.addComponent(affichePath)
		                )
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(url)
	                    	.addComponent(nom)
	                    	.addComponent(path)
		                )
	                )
	            	.addGroup(layout.createSequentialGroup()
	            			.addGap(5)
	            			.addComponent(butCont)
			        )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGap(5)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheURL)
	                    	.addComponent(url)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheNom)
	                    	.addComponent(nom)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(affichePath)
	                    	.addComponent(path)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                		.addComponent(butCont)
	                )
	          )
	          .addGap(5)
	    );
	    
		TitledBorder afact = BorderFactory.createTitledBorder("Données");
		afact.setTitleJustification(TitledBorder.CENTER);
		capture.setBorder(afact);
		
		// Ajout des actions
		capturer.addActionListener(new ActionCapturerSite());
		stop.addActionListener(new ActionStopAsiprateur());
		pause.addActionListener(new ActionPauseAsiprateur());
		reprendre.addActionListener(new ActionResumeAsiprateur());
		nom.addActionListener(new ActionMAJName());
		path.addActionListener(new ActionMAJPath());
		parcourir.addActionListener(new ActionChangerPath());
		
		pause.setEnabled(false);
		reprendre.setEnabled(false);
		
		//--------------------------------
		// Contraintes
		JPanel contrainte = new JPanel();
		
		TitledBorder bfact = BorderFactory.createTitledBorder("Contraintes de capture");
		bfact.setTitleJustification(TitledBorder.CENTER);
		contrainte.setBorder(bfact);
		contrainte.setLayout(new BorderLayout());
		
		JPanel chaut = new JPanel();
		
		afficheProfondeur = new JLabel("Profondeur à parcourir en nombre de pages");
		afficheThreadP = new JLabel("Nb de Thread pour les Pages");
		afficheThreadR = new JLabel("Nb de thread pour les Ressources");
		afficheVolume = new JLabel("Taille maximum du site (en Ko)");
		afficheVolumePage = new JLabel("Taille maximum des pages (en Ko)");
		afficheVolumeRessource = new JLabel("Taille maximum des ressources (en Ko)");
		afficheVolume = new JLabel("Taille maximale du site (en Ko) :");
		afficheVolumePage = new JLabel("Taille maximale des pages (en Ko) :");
		afficheVolumeRessource = new JLabel("Taille maximale des ressources (en Ko) :");

	
		afficheProfondeur.setToolTipText("No : Profondeur illimitée");
		afficheThreadP.setToolTipText("Minimum = 0 ; Maximum = 5");
		afficheThreadR.setToolTipText("Minimum = 0 ; Maximum = 5");
		afficheVolume.setToolTipText("Infini : Taille illimitée");
		afficheVolumePage.setToolTipText("Infini : Taille illimitée");
		afficheVolumeRessource.setToolTipText("Infini : Taille illimitée");
	    
		Container optCont = new Container();
		optCont.setLayout(new BorderLayout(10,10));
		
		GroupLayout layoutContraintes = new GroupLayout(chaut);
		chaut.setLayout(layoutContraintes);
		layoutContraintes.setHorizontalGroup(layoutContraintes.createParallelGroup()
				.addGroup(layoutContraintes.createParallelGroup()
	            	.addGroup(layoutContraintes.createSequentialGroup()
	    	            .addGap(5)
	                    .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(afficheProfondeur)
	                    	.addComponent(afficheThreadP)
	                    	.addComponent(afficheThreadR)
	                    	.addComponent(afficheVolume)	                    	
	                    	.addComponent(afficheVolumePage)
	                    	.addComponent(afficheVolumeRessource)
		                )
	    	            .addGap(10)
	                    .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(contraintesProfondeur)
	                    	.addComponent(contraintesThreadP)
	                    	.addComponent(contraintesThreadR)
	                    	.addComponent(contraintesVolume)
	                    	.addComponent(contraintesVolumePage)
	                    	.addComponent(contraintesVolumeRessources)
		                )
	                )
		            .addGroup(layoutContraintes.createSequentialGroup()
			    	       .addGap(10)
			               .addComponent(optionsAvancées)
			        )
	            )
	    );
	    
	    layoutContraintes.setVerticalGroup(layoutContraintes.createSequentialGroup()
	    		.addGap(5)
	            .addGroup(layoutContraintes.createSequentialGroup()
	                .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheProfondeur)
	                    	.addComponent(contraintesProfondeur)
	                )
	                .addGap(10)
	                .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheThreadP)
	                    	.addComponent(contraintesThreadP)
	                )
	                .addGap(10)
	                .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheThreadR)
	                    	.addComponent(contraintesThreadR)
	                )
	                .addGap(10)
	                .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheVolume)
	                    	.addComponent(contraintesVolume)
	                )
	                .addGap(10)
	                .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheVolumePage)
	                    	.addComponent(contraintesVolumePage)
	                )
	                .addGap(10)
	                .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheVolumeRessource)
	                    	.addComponent(contraintesVolumeRessources)
	                )
	             )
	            .addGap(10)
	            .addGroup(layoutContraintes.createParallelGroup()
	            		.addComponent(optionsAvancées)
	            )
	    );
	    
		TitledBorder chautB = BorderFactory.createTitledBorder("Options");
		chautB.setTitleJustification(TitledBorder.CENTER);
		chaut.setBorder(chautB);
	    
		optCont.add(chaut, BorderLayout.CENTER);
		
		Container north = new Container();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		north.setLayout(gbl);
		
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5 , 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        north.add(capGauche, gbc);

        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5 , 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        north.add(vueMeta, gbc);
		
		contrainte.add(optCont,BorderLayout.EAST);
		contrainte.add(vueFiltres,BorderLayout.CENTER);
		capture.add(north, BorderLayout.CENTER);
		
		add(capture,BorderLayout.NORTH);
		add(contrainte,BorderLayout.CENTER);
	}//cons-1
	
	
	public void setEnabled(boolean b){
		pause.setEnabled(b);
		reprendre.setEnabled(b);
		stop.setEnabled(b);
		capturer.setEnabled(b);
		parcourir.setEnabled(b);
		optionsAvancées.setEnabled(b);
		contraintesProfondeur.setEnabled(b);
		contraintesThreadP.setEnabled(b);
		contraintesThreadR.setEnabled(b);
		contraintesVolume.setEnabled(b);
		contraintesVolumePage.setEnabled(b);
		contraintesVolumeRessources.setEnabled(b);
	}
	
	//------------------
	// Methodes
	//------------------
	public void update(Observable o, Object arg) {
		nom.setText(laspirateur.getName());
		path.setText(laspirateur.getPath());
	}

	public VueSauvegarde getVueSauvegarde() {
		return vueSauvegarde;
	}

	public void setVueSauvegarde(VueSauvegarde vueSauvegarde) {
		this.vueSauvegarde = vueSauvegarde;
	}
	
	//------------------
	// Actions
	//------------------
	/**
	 * Cette action permet de donner au modele le nom specifie pour la sauvegarde du site
	 */
	private class ActionMAJName implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			laspirateur.setName(nom.getText());
			update(null,null);
		}
	}
	
	/**
	 * Cette action permet de donner au modele le chemin du workspace
	 */
	private class ActionMAJPath implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			laspirateur.setPath(path.getText());
			update(null,null);
		}
	}
	
	/**
	 * Cette action permet de changer le workspace du modele
	 */
	private class ActionChangerPath implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
        	JFileChooser chooser = new JFileChooser();
        	chooser.setApproveButtonText("Choisir ce repertoire comme workspace...");
        	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	int status = chooser.showOpenDialog(null);

            if (status == JFileChooser.APPROVE_OPTION) {
            	path.setText(chooser.getSelectedFile().getAbsolutePath());
            	laspirateur.setPath(chooser.getSelectedFile().getAbsolutePath());
            	System.out.println("Votre workspace : "+path.getText());
            	vueSauvegarde.refresh();
            }
        	
			update(null,null);
		}
	}
	
	/**
	 * Cette action permet de capturer un site
	 */
	private class ActionCapturerSite implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			//laspirateur.reinitialise();
			int profondeur = contraintesProfondeur.getValue();
			int tailleSite = -1;
			int taillePage = -1;
			int tailleRessources = -1;
			
			if(contraintesVolume.getText().matches("[0-9]*")){
				tailleSite = Integer.parseInt(contraintesVolume.getText())*1024;
			}else{
				if(!contraintesVolume.getText().equals("Infini")){
					tailleSite = -2;
				}
			}
			
			if(contraintesVolumePage.getText().matches("[0-9]*")){
				taillePage = Integer.parseInt(contraintesVolumePage.getText())*1024;
			}else{
				if(!contraintesVolumePage.getText().equals("Infini")){
					taillePage = -2;
				}
			}
			

			if(contraintesVolumeRessources.getText().matches("[0-9]*")){
				tailleRessources = Integer.parseInt(contraintesVolumeRessources.getText())*1024;
			}else{
				if(!contraintesVolumeRessources.getText().equals("Infini")){
					tailleRessources = -2;
				}
			}
			
			if(tailleSite!=-2 && taillePage!=-2 && tailleRessources!=-2 && url.getText().length() != 0){
				if(url.getText().startsWith("http://") || url.getText().startsWith("https://")){
					setEnabled(false);
					capturer.setEnabled(false);
					stop.setEnabled(true);
					pause.setEnabled(true);
					reprendre.setEnabled(false);
					vueFiltres.setEnabled(false);
					vueMeta.setEnabled(false);
					vueOnglets.getVueStatistiques().setEnabled(false);
					
					// Recuperation des informations sur la capture
					laspirateur.setName(nom.getText());
					laspirateur.setPath(path.getText());
					laspirateur.makeURLLocal();
					laspirateur.setMeta(vueMeta.getValeur().getText());
					laspirateur.setNbPagesThread(contraintesThreadP.getValue());
					laspirateur.setNbRessourcesThread(contraintesThreadR.getValue());
					laspirateur.setProfondeur(profondeur);
					laspirateur.setTailleSiteMax(tailleSite);
					laspirateur.setTaillePagesMax(taillePage);
					laspirateur.setTailleRessourcesMax(tailleRessources);
					
					// Methode pour faire les filtres
					
					ArrayList<String> lesFiltres = vueFiltres.getListeFiltres();
					laspirateur.setFiltres(lesFiltres);
					
					
					// Nouveau processus pour lancer le process
					t = new Thread(new Runnable(){
		
						public void run() {
							laspirateur.launchProcess(url.getText());
							setEnabled(true);
							vueMeta.setEnabled(true);
							vueOnglets.setEnabled(true);
							vueFiltres.setEnabled(true);
							vueSauvegarde.refresh();
							vueProgressBar.setValue(0);
						}
						
					});
					
					t.start();
				}else{
					JOptionPane.showMessageDialog(null,"L'url doit commencer par \"http://\" ou \"https://\" !","Attention",JOptionPane.WARNING_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null,"Vous avez mal rempli un champ !","Attention",JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}

	/**
	 * Cette action permet stopper le parser
	 */
	private class ActionStopAsiprateur implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			if(!laspirateur.isStopped()){
				laspirateur.stop();
			}
			pause.setEnabled(false);
			reprendre.setEnabled(false);
		}
	}
	
	/**
	 * Cette action permet de mettre en pause le parser
	 */
	private class ActionPauseAsiprateur implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			laspirateur.suspend();
			reprendre.setEnabled(true);
			pause.setEnabled(false);
		}
	}
	
	/**
	 * Cette action permet de reprendre le parser mis en pause
	 */
	private class ActionResumeAsiprateur implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			laspirateur.reprendre();
			pause.setEnabled(true);
			reprendre.setEnabled(false);
		}
	}
	
	/**
	 * Action effectué lorsque l'on appuie sur "Options Avancées..."
	 */
	private class ActionOptionsAvancees implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			VueOptionsAvancées vueOptionsAvancées = new VueOptionsAvancées(laspirateur);
			vueOptionsAvancées.setVisible(true);
		}
		
	}
}
