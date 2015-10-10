package domainapp.dom.app.reporte;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@DomainService()
@DomainServiceLayout(named = "Reportes")
public class GenerarReporte {
	public static void generarReporte(String jrxml, List<Object> parametros,Formato formato, String nombreArchivo)
			throws JRException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();

		File file = new File(jrxml);
		JRBeanArrayDataSource jArray = new JRBeanArrayDataSource(parametros.toArray());
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		JasperDesign jd = JRXmlLoader.load(input);
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JasperPrint print = JasperFillManager.fillReport(reporte, map, jArray);

		// Lo muestra con el jasperviewer
		// JasperViewer.viewReport(print, false);
		if(formato==Formato.PDF)
			JasperExportManager.exportReportToPdfFile(print, nombreArchivo + ".pdf");
		else
			if(formato==Formato.XLS){
				JRXlsExporter exporterXLS = new JRXlsExporter();

				exporterXLS.setExporterInput(new SimpleExporterInput(print));
				exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(nombreArchivo + ".xls"));

				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setOnePagePerSheet(true);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);

				exporterXLS.setConfiguration(configuration);
				exporterXLS.exportReport();
			}
			else{
				JRDocxExporter export = new JRDocxExporter();
				export.setExporterInput(new SimpleExporterInput(print));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(nombreArchivo + ".docx"));
				export.exportReport();
			}
		// Muestra el reporte en otra ventana
		// JasperExportManager.exportReportToHtmlFile(print, "nuevo.html");
	}

	@javax.inject.Inject
	public ReportContext reportContext;

	@javax.inject.Inject
	DomainObjectContainer container;
}
