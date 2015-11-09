package domainapp.dom.app.reporte;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class AlertaMatafuegoDataSource implements JRDataSource {
	private List<ReporteAlerta> listaAlerta = new ArrayList<ReporteAlerta>();
	private int indiceAlertaActual = -1;

	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("nombre".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getNombre();
		} else if ("descripcion".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getDescripcion();
		} else if ("estadoAlerta".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getEstadoAlerta();
		} else if ("empleadoInvolucrado".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getEmpleadoInvolucrado();
		} else if ("alerta".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getAlerta();
		} else if ("elemento".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getElemento();
		} else if ("fechaAlta".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getFechaAlta();
		} else if ("subTitulo".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getSubTitulo();
		}
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceAlertaActual < listaAlerta.size();
	}

	public void addParticipante(ReporteAlerta alerta) {
		this.listaAlerta.add(alerta);
	}
}
