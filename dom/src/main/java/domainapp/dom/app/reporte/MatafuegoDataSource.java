package domainapp.dom.app.reporte;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class MatafuegoDataSource  implements JRDataSource{
	private List<ReporteMatafuego> listaMatafuego = new ArrayList<ReporteMatafuego>();
	private int indiceMatafuegoActual = -1;
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("codigo".equals(jrf.getName())) {
			valor = listaMatafuego.get(indiceMatafuegoActual).getCodigo();
		} else if ("marca".equals(jrf.getName())) {
			valor = listaMatafuego.get(indiceMatafuegoActual).getMarca();
		} else if ("descripcion".equals(jrf.getName())) {
			valor = listaMatafuego.get(indiceMatafuegoActual).getDescripcion();
		} else if ("capacidad".equals(jrf.getName())) {
			valor = listaMatafuego.get(indiceMatafuegoActual).getCapacidad();
		} else if ("fechaAlta".equals(jrf.getName())) {
			valor = listaMatafuego.get(indiceMatafuegoActual).getFechaAlta();
		} else if ("fechaRecarga".equals(jrf.getName())) {
		valor = listaMatafuego.get(indiceMatafuegoActual).getFechaRecarga();
		} else if ("fechaCadRecarga".equals(jrf.getName())) {
			valor = listaMatafuego.get(indiceMatafuegoActual).getFechaCadRecarga();
		} else if ("estado".equals(jrf.getName())) {
			valor = listaMatafuego.get(indiceMatafuegoActual).getEstado();
		} 
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceMatafuegoActual< listaMatafuego.size();
	}

	public void addParticipante(ReporteMatafuego matafuego) {
		this.listaMatafuego.add(matafuego);
	}
}
