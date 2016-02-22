/*
 *  SIGAFV is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * SIGAFV is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SIGAFV.  If not, see <http://www.gnu.org/licenses/>.
 */
    
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
