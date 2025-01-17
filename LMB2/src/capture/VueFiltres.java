/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package capture;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Aspirateur.*;

public class VueFiltres extends JPanel{

	
	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	
	//public JLabel afficheFiltre;
	
	ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	ArrayList<String> listeFiltres = new ArrayList<String>();
	
	private JCheckBox selection;
	private JButton ajouterFiltre;
	
	//Les cases � cocher
	//Images
	private JCheckBox check1 = new JCheckBox(".gif");
    private JCheckBox check2 = new JCheckBox(".ico");
    private JCheckBox check3 = new JCheckBox(".jpeg");
    private JCheckBox check4 = new JCheckBox(".jpg");
    private JCheckBox check5 = new JCheckBox(".png");
    //Audio / Video
    private JCheckBox check6 = new JCheckBox(".aac");    
    private JCheckBox check7 = new JCheckBox(".avi");
    private JCheckBox check8 = new JCheckBox(".mkv");
    private JCheckBox check9 = new JCheckBox(".mp3");
    private JCheckBox check10 = new JCheckBox(".wav");
    //Programmation
    private JCheckBox check11 = new JCheckBox(".css");    
    private JCheckBox check12 = new JCheckBox(".java");
    private JCheckBox check13 = new JCheckBox(".xml");
    private JCheckBox check14 = new JCheckBox(".js");
    private JCheckBox check15 = new JCheckBox(".c");
    //Autres
    private JCheckBox check16 = new JCheckBox(".pdf");
    private JCheckBox check17 = new JCheckBox(".doc");
    private JCheckBox check18 = new JCheckBox(".exe");
    private JCheckBox check19 = new JCheckBox(".zip");
    private JCheckBox check20 = new JCheckBox(".txt");
    
    private JPanel filtres4;
	
	//------------------
	// Contructeur
	//------------------
	public VueFiltres(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		this.setLayout(new BorderLayout());
		GridLayout gridL = new GridLayout(1,4);
		gridL.setHgap(10);
		gridL.setVgap(10);
		
		TitledBorder afact = BorderFactory.createTitledBorder("Filtres");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
		
		// Creation des elements graphiques de VueFiltres
		//afficheFiltre = new JLabel("Filtres", SwingUtilities.CENTER);
		
		// Ajout des elements dans le conteneur VueFiltres
		//add(afficheFiltre,BorderLayout.NORTH);
		
		
		//1er panneau : pour les filtres d'images
		JPanel filtres1 = new JPanel();
		filtres1.setLayout(new GridLayout(5,1));
		//filtres1.add(new JLabel("Images", SwingUtilities.CENTER));
		filtres1.add(check1); checkBoxes.add(check1);
		filtres1.add(check2); checkBoxes.add(check2);
		filtres1.add(check3); checkBoxes.add(check3);
		filtres1.add(check4); checkBoxes.add(check4);
		filtres1.add(check5); checkBoxes.add(check5);
		JPanel c1 = new JPanel();
		c1.setLayout(new BorderLayout(5,5));
		c1.setBackground(new Color(150,200,250));
		c1.add(new JLabel("Images", SwingUtilities.CENTER),BorderLayout.NORTH);
		c1.add(filtres1,BorderLayout.CENTER);
		
		//2er panneau : pour les filtres d'audios / videos
		JPanel filtres2 = new JPanel();
		filtres2.setLayout(new GridLayout(5,1));
		filtres2.add(check6); checkBoxes.add(check6);
		filtres2.add(check7); checkBoxes.add(check7);
		filtres2.add(check8); checkBoxes.add(check8);
		filtres2.add(check9); checkBoxes.add(check9);
		filtres2.add(check10); checkBoxes.add(check10);
		JPanel c2 = new JPanel();
		c2.setLayout(new BorderLayout(5,5));
		c2.setBackground(new Color(150,200,250));
		c2.add(new JLabel("Audio / Video", SwingUtilities.CENTER),BorderLayout.NORTH);
		c2.add(filtres2,BorderLayout.CENTER);
		
		//3eme panneau : pour les autres filtres
		JPanel filtres3 = new JPanel();
		filtres3.setLayout(new GridLayout(5,1));
		filtres3.add(check11); checkBoxes.add(check11);
		filtres3.add(check12); checkBoxes.add(check12);
		filtres3.add(check13); checkBoxes.add(check13);
		filtres3.add(check14); checkBoxes.add(check14);
		filtres3.add(check15); checkBoxes.add(check15);
		JPanel c3 = new JPanel();
		c3.setLayout(new BorderLayout(5,5));
		c3.setBackground(new Color(150,200,250));
		c3.add(new JLabel("Programmation", SwingUtilities.CENTER),BorderLayout.NORTH);
		c3.add(filtres3,BorderLayout.CENTER);

		//3eme panneau : pour les autres filtres
		filtres4 = new JPanel();
		filtres4.setLayout(new GridLayout(5,1));
		filtres4.add(check16); checkBoxes.add(check16);
		filtres4.add(check17); checkBoxes.add(check17);
		filtres4.add(check18); checkBoxes.add(check18);
		filtres4.add(check19); checkBoxes.add(check19);
		filtres4.add(check20); checkBoxes.add(check20);
		JPanel c4 = new JPanel();
		c4.setLayout(new BorderLayout(5,5));
		c4.setBackground(new Color(150,200,250));
		c4.add(new JLabel("Autres", SwingUtilities.CENTER),BorderLayout.NORTH);
		c4.add(filtres4,BorderLayout.CENTER);
		
		//-------------------------------
		// Options
		
		// Couleurs
		filtres1.setBackground(new Color(150,200,250));
		filtres2.setBackground(new Color(150,200,250));
		filtres3.setBackground(new Color(150,200,250));
		filtres4.setBackground(new Color(150,200,250));
		
		
		// Divers
		//afficheFiltre.setFont(new Font("", Font.BOLD,12));
		
		//-------------------------------
		// Ajout des actions
		for(int i=0; i<checkBoxes.size(); i++){
			checkBoxes.get(i).addActionListener(new ActionCheck());
		}
		
		selection = new JCheckBox("Tout cocher");
		selection.addActionListener(new ActionCheckAll());
		
		ajouterFiltre = new JButton("Ajouter un filtre");
		ajouterFiltre.addActionListener(new ActionAjouterFiltre());
		

		Container south = new Container();
		south.setLayout(new FlowLayout());
		south.add(ajouterFiltre,SwingUtilities.CENTER);
		south.add(selection,SwingUtilities.CENTER);

		Container center = new Container();
		GroupLayout layout = new GroupLayout(center);
		center.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
	            	.addGroup(layout.createSequentialGroup()
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(c1)
		                )
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(c2)
		                )
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(c3)
		                )
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(c4)
		                )
	                )
	            )
	    );
		
		layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGap(5)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(c1)
	                    	.addComponent(c2)
	                    	.addComponent(c3)
	                    	.addComponent(c4)
	                )
	             )
	    );
		
		
		add(center,BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		
		
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	public boolean containsCB(String value){
		for(int i=0; i<checkBoxes.size(); i++){
			if(checkBoxes.get(i).getText().equals(value)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> getListeFiltres(){
		return this.listeFiltres;
	}
	
	public void setEnabled(boolean b){
		selection.setEnabled(b);
		ajouterFiltre.setEnabled(b);
		for(int i=0; i<checkBoxes.size(); i++){
			checkBoxes.get(i).setEnabled(b);
		}
	}
	
	//Action des cases � cocher :
	private class ActionCheck implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				// Recuperation de la source de l'action
				JCheckBox current = (JCheckBox)e.getSource();
				
				if(current.isSelected())
					listeFiltres.add(current.getText());
				else
					listeFiltres.remove(current.getText());
				
				// Visualiser le contenu de la liste des Filtres
				/*for (int i = 0; i < listeFiltres.size(); i++){
					System.out.println(listeFiltres.get(i));
				}*/
				
			}
	}
	
	private class ActionCheckAll implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(selection.isSelected()){
				for(int i=0; i<checkBoxes.size(); i++){
					checkBoxes.get(i).setSelected(true);
					listeFiltres.add(checkBoxes.get(i).getText());
				}
			}else{
				for(int i=0; i<checkBoxes.size(); i++){
					checkBoxes.get(i).setSelected(false);
					listeFiltres.remove(checkBoxes.get(i).getText());
				}
			}
		}
		
	}
	
	private class ActionAjouterFiltre implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String value = JOptionPane.showInputDialog("Entrez un filtre (ex : \"zip\")");
			if(value!=null){
				if(!value.contains(".") && !value.matches("[0-9]*") && !containsCB("."+value) && value.matches("[a-z]*[0-9]*[a-z]*")){
					JCheckBox newCheck = new JCheckBox("."+value);
					checkBoxes.add(newCheck);
					if((filtres4.getComponentCount()+1)/5>((GridLayout)filtres4.getLayout()).getColumns()*5){
						((GridLayout)filtres4.getLayout()).setColumns(((GridLayout)filtres4.getLayout()).getColumns()+1);
						filtres4.revalidate();
					}
					filtres4.add(newCheck);
					if(selection.isSelected()){
						newCheck.setSelected(true);
						listeFiltres.add(newCheck.getText());
					}
					JOptionPane.showMessageDialog(null, "Le filtre a �t� ajout�");
				}else{
					JOptionPane.showMessageDialog(null, "Le filtre saisi n'est pas valide");
				}
			}
		}
		
	}
	
}
