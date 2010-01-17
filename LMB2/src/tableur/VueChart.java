/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package tableur;

import java.awt.BorderLayout;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class VueChart extends JPanel {

	//------------------
	// Attributs
	//------------------
	private Hashtable<String, Integer> dataMotsComplet;
	
	//------------------
	// Constructeurs
	//------------------
	public VueChart(Hashtable<String, Integer> dataMotsComplet){
		this.dataMotsComplet = dataMotsComplet;

		setLayout(new BorderLayout());
		TitledBorder bfact = BorderFactory.createTitledBorder("Graphique");
		bfact.setTitleJustification(TitledBorder.CENTER);
		setBorder(bfact);
		
	    DefaultPieDataset pieDataset = new DefaultPieDataset();
		Enumeration<String> e = dataMotsComplet.keys();
		String cle = e.nextElement();
		while (e.hasMoreElements()){
			System.err.println(dataMotsComplet.get(cle));
			pieDataset.setValue(cle, dataMotsComplet.get(cle));
			cle = e.nextElement();
		}

	    JFreeChart pieChart = ChartFactory.createPieChart3D("Test camembert", 
	      pieDataset, true, true, true); 
	    ChartPanel cPanel = new ChartPanel(pieChart); 
	    add(cPanel); 

		
		/**
		 * UTILISER LA JFREE CHART
		 * METTRE DES BOUTONS
		 * ETC.
		 */
	}//cons-1
}
