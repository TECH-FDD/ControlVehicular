package domainapp.dom.app.reporte;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class AreaDataSource implements JRDataSource{
	private List<ReporteArea> listaArea = new ArrayList<ReporteArea>();
	private int indiceAreaActual = -1;
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		
		if ("codigoArea".equals(jrf.getName())) {
			valor = listaArea.get(indiceAreaActual).getCodigoArea();
		} else if ("nombre".equals(jrf.getName())) {
			valor = listaArea.get(indiceAreaActual).getNombre();
		} else if ("descripcion".equals(jrf.getName())) {
			valor = listaArea.get(indiceAreaActual).getDescripcion();
		} else if ("fechaAlta".equals(jrf.getName())) {
			valor = listaArea.get(indiceAreaActual).getFechaAlta();
		} else if ("activo".equals(jrf.getName())) {
			valor = listaArea.get(indiceAreaActual).getActivo();
		} 
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceAreaActual< listaArea.size();
	}

	public void addParticipante(ReporteArea area) {
		this.listaArea.add(area);
	}
}
