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
