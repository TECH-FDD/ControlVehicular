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
