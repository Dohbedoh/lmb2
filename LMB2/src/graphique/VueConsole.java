/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Aspirateur.*;

public class VueConsole extends JPanel  implements Observer{

	//------------------
	// Attributs
	//------------------
	private JTextArea laconsole;
	private JLabel currentPage;
	private JLabel currentPageLab;
	private JLabel currentRess;
	private JLabel currentRessLab;
	public Aspirateur laspirateur;
	public JScrollPane scroll;
	//------------------
	// Constructeur
	//------------------
	public VueConsole(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		this.setLayout(new BorderLayout(5,5));
		//this.setPreferredSize(new Dimension(200,150));
		
		laspirateur.addObserver(this);
		
		currentPageLab = new JLabel("Page actuelle : ");
		currentPage = new JLabel("");		
		currentRessLab = new JLabel("Ress actuelle : ");
		currentRess = new JLabel("");
		

		currentPage.setFont(new Font(null,1,11));
		currentPageLab.setFont(new Font(null,1,11));
		currentRess.setFont(new Font(null,1,11));
		currentRessLab.setFont(new Font(null,1,11));

		currentPage.setForeground(new Color(51,204,0));
		currentRess.setForeground(new Color(51,204,0));
		
		// Creation des elements graphiques
		laconsole = new JTextArea();
		laconsole.setRows(6);
		PrintStream out = new PrintStream( new JTextAreaAdapter(laconsole));
		
		// Ajout des elements graphiques
		scroll = new JScrollPane(laconsole,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		Container cont = new Container();
		/*cont.setLayout(new BorderLayout());
		cont.add(currentPageLab, BorderLayout.WEST);
		cont.add(currentPage, BorderLayout.CENTER);*/
		
		GroupLayout layout = new GroupLayout(cont);
		cont.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	                    // Le groupe des labels
	    	            .addGap(2)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(currentPageLab)
	                    	.addComponent(currentRessLab)
	                    )
	                    //le groupe des nbs
	    	            .addGap(2)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)

	                        .addComponent(currentPage)
		                    .addComponent(currentRess)
		                )
	                )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGroup(layout.createSequentialGroup()
		            .addGap(2)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(currentPageLab)
	                		.addComponent(currentPage)
	                )
	                .addGap(2)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(currentRessLab)
	                		.addComponent(currentRess)
	                )
	          )
              .addGap(2)
	    );
		
		this.add(cont, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		
		// Redirection des sorties standard et d'erreur
		System.setOut(out);
		//System.setErr(out);

		// Options des elements graphiques
		laconsole.setEditable(false);
		laconsole.setFont(new Font("TimesRoman",Font.PLAIN,12));
		
		TitledBorder afact = BorderFactory.createTitledBorder("Console");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
	}//cons-1
	
	public void reset(){
		laconsole.setText("");
	}

	//------------------
	// Methodes
	//------------------
	public void update(Observable o, Object arg) {
		if(arg == null){
			JScrollBar jsb = scroll.getVerticalScrollBar() ;
			if(jsb!=null){
				jsb.setValue( jsb.getMaximum() );
			}
		}
		currentPage.setText(laspirateur.getCurrentPage());
		currentRess.setText(laspirateur.getCurrentRess());
	}
}
