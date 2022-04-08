package presentacion.reportes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.conexion.Conexion;
import dto.PersonaDTO;

public class ReporteMusicaPreferida
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	private Logger log = LogManager.getLogger(Conexion.class);
	//Recibe la lista de personas para armar el reporte
    public ReporteMusicaPreferida(List<PersonaDTO> personas, String orden)
    {
    	Collections.sort(personas, new Comparator<PersonaDTO>() {
     	      @Override
     	      public int compare(final PersonaDTO object1, final PersonaDTO object2) {
     	    		  return object1.getMusica().compareTo(object2.getMusica());
     	      }
     	  	});
     	 
      	if(orden == "desc") {
      		 personas.sort(Comparator.comparing(PersonaDTO::getMusica, Comparator.reverseOrder()));
      	}     	
    	//Hardcodeado
		Map<String, Object> parametersMap = new HashMap<String, Object>();	
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));	
		//parametersMap.put("SORT_FIELDS", personas.stream().map(PersonaDTO::getMusica).collect(Collectors.toList()));
    	try		{
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteMusicaPreferida.jasper" );
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap, 
					new JRBeanCollectionDataSource(personas));
    		log.info("Se cargó correctamente el reporte");
		}
		catch( JRException ex ) 
		{
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteMusicaPreferida.Jasper", ex);
		}
    }       
    
    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}	