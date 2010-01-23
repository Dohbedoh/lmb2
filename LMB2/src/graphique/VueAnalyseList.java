/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import statistiques.Statistiques;
import visualiser.BareBonesBrowserLaunch;

public class VueAnalyseList extends JPanel implements Observer{
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	private JList jlist;
	private JButton imagesBut, addrBut, cssBut, jsBut, tousBut;
	
	private JPopupMenu menu;
	private JMenuItem visualiserSelection;
	private File selected;
	
	private JScrollPane scroll;
	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseList(Statistiques stats){
		this.stats = stats;
		this.setLayout(new BorderLayout(5,5));
		
		TitledBorder afact = BorderFactory.createTitledBorder("Listes de Fichiers");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
		
		jlist = new JList();
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.setCellRenderer(new ListFormateur());
		add(new JScrollPane(jlist),BorderLayout.CENTER);
		
		Container cont = new Container();
		GroupLayout layout = new GroupLayout(cont);
		cont.setLayout(layout);

		tousBut = new JButton("Voir Tout");
		imagesBut = new JButton("Voir Images");
		addrBut = new JButton("Voir Adresses");
		cssBut = new JButton("Voir CSS");
		jsBut = new JButton("Voir JS");
		
		menu = new JPopupMenu();
		visualiserSelection = new JMenuItem("Visualiser");
		visualiserSelection.addActionListener(new ActionVisualiserSelection());
		menu.add(visualiserSelection);
		
		layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    		.addComponent(imagesBut)
	                    )
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		                        .addComponent(addrBut)
		                )
	    	            .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        		.addComponent(cssBut)
                        )
        	    	    .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        		.addComponent(jsBut)
                        )
        	    	    .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        		.addComponent(tousBut)
                        )
	                )
	            )
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGroup(layout.createSequentialGroup()
		            .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                		.addComponent(imagesBut)
	                		.addComponent(addrBut)
	                		.addComponent(cssBut)
	                		.addComponent(jsBut)
	                		.addComponent(tousBut)
	                )
	                .addGap(10)
	          )
		);
		
		add(cont,BorderLayout.SOUTH);
		
		
		// Actions
		imagesBut.addActionListener(new ActionLoadImages());
		addrBut.addActionListener(new ActionLoadAdress());
		cssBut.addActionListener(new ActionLoadCSS());
		jsBut.addActionListener(new ActionLoadJS());
		tousBut.addActionListener(new ActionLoadTout());
		
		jlist.addMouseListener(new ActionClickDroit());
		
	}

	public void setEnabled(boolean b){
		imagesBut.setEnabled(b);
		addrBut.setEnabled(b);
		cssBut.setEnabled(b);
		jsBut.setEnabled(b);
		tousBut.setEnabled(b);
	}
	
	public void setStatistiques(Statistiques stats){
		this.stats = stats;
	}
	
	public Statistiques getStatistiques(){
		return this.stats;
	}
	
	//------------------
	// M�thodes
	//------------------
	public void update(Observable o, Object arg) {
		
		// Par d�faut on chargera toutes les pages Enregistrees
		jlist.setModel(new ListAdapter(stats.getLesFichiersEnregistres()));

	}
	
	//------------------
	// Actions
	//------------------
	private class ActionLoadImages implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setModel(new ListAdapter(stats.getDataImages()));
		}
	}
	
	private class ActionLoadAdress implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setListData(stats.getMetaData().getMailTosList().toArray());
		}
	}
	
	private class ActionLoadCSS implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setModel(new ListAdapter(stats.getDataCSS()));
		}
	}
	
	private class ActionLoadJS implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setModel(new ListAdapter(stats.getDataJS()));
		}
	}
	
	private class ActionLoadTout implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setModel(new ListAdapter(stats.getLesFichiersEnregistres()));
		}
	}
	
	/**
	 * Action lanc�e lorsque l'on clique droit sur le JTree
	 */
	private class ActionClickDroit extends MouseAdapter{

		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				if(!jlist.isSelectionEmpty()){
					if(jlist.getSelectedValue() instanceof File){
						selected = (File)jlist.getSelectedValue();
						visualiserSelection.setText("Visualiser");
						menu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			}
			if (e.getClickCount() == 2) {
				if(jlist.getSelectedValue() instanceof File){
					selected = (File)jlist.getSelectedValue();
					URL url;
					try {
						url = selected.toURL();
						BareBonesBrowserLaunch.openURL(url.toString());
					} catch (MalformedURLException er) {er.printStackTrace();}
				}
				
			}
		}
		
	}
	
	private class ActionVisualiserSelection implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(jlist.getSelectedValue() instanceof File){
				selected = (File)jlist.getSelectedValue();
				URL url;
				try {
					url = selected.toURL();
					BareBonesBrowserLaunch.openURL(url.toString());
				} catch (MalformedURLException e) {e.printStackTrace();}
			}
			
		}
	}
	
}
