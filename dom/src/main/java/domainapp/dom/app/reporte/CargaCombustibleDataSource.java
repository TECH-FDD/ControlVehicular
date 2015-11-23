package domainapp.dom.app.reporte;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class CargaCombustibleDataSource implements JRDataSource{
	private List<ReporteCargaCombustible> listaCargaCombustible = new ArrayList<ReporteCargaCombustible>();
	private int indiceCargaCombustibleActual = -1;
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("vehiculo".equals(jrf.getName())) {
			valor = listaCargaCombustible.get(indiceCargaCombustibleActual).getVehiculo();
		} else if ("combustible".equals(jrf.getName())) {
			valor = listaCargaCombustible.get(indiceCargaCombustibleActual).getCombustible();
		} else if ("fechaCarga".equals(jrf.getName())) {
			valor = listaCargaCombustible.get(indiceCargaCombustibleActual).getFechaCarga();
		} else if ("litrosCargados".equals(jrf.getName())) {
			valor = listaCargaCombustible.get(indiceCargaCombustibleActual).getLitrosCargados();
		} else if ("costoTotal".equals(jrf.getName())) {
			valor = listaCargaCombustible.get(indiceCargaCombustibleActual).getCostoTotal();
		}
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceCargaCombustibleActual< listaCargaCombustible.size();
	}

	public void addParticipante(ReporteCargaCombustible cargaCombustible) {
		this.listaCargaCombustible.add(cargaCombustible);
	}
}
