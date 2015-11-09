package domainapp.dom.app.reporte;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class GpsDataSource implements JRDataSource {
	private List<ReporteGps> listaGps = new ArrayList<ReporteGps>();
	private int indiceGpsActual = -1;

	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("codIdentificacion".equals(jrf.getName())) {
			valor = listaGps.get(indiceGpsActual).getCodIdentificacion();
		} else if ("marca".equals(jrf.getName())) {
			valor = listaGps.get(indiceGpsActual).getMarca();
		} else if ("modelo".equals(jrf.getName())) {
			valor = listaGps.get(indiceGpsActual).getModelo();
		} else if ("descripcion".equals(jrf.getName())) {
			valor = listaGps.get(indiceGpsActual).getDescripcion();
		} else if ("fechaAlta".equals(jrf.getName())) {
			valor = listaGps.get(indiceGpsActual).getFechaAlta();
		} else if ("fechaAsigVehiculo".equals(jrf.getName())) {
			valor = listaGps.get(indiceGpsActual).getFechaAsigVehiculo();
		} else if ("obsEstadoDispositivo".equals(jrf.getName())) {
			valor = listaGps.get(indiceGpsActual).getObsEstadoDispositivo();
		} else if ("estado".equals(jrf.getName())) {
			valor = listaGps.get(indiceGpsActual).getEstado();
		}
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceGpsActual < listaGps.size();
	}

	public void addParticipante(ReporteGps gps) {
		this.listaGps.add(gps);
	}
}
