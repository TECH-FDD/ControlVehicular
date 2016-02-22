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

public class VehiculoDataSource implements JRDataSource {
	private List<ReporteVehiculo> listaVehiculo = new ArrayList<ReporteVehiculo>();
	private int indiceVehiculoActual = -1;

	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;

		if ("nombre".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getNombre();
		} else if ("modelo".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getModelo();
		} else if ("fechaCompra".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getFechaCompra();
		} else if ("patente".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getPatente();
		} else if ("numeroChasis".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getNumeroChasis();
		} else if ("polizaSeguro".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getPolizaSeguro();
		} else if ("gps".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getGps();
		} else if ("tipoCombustible".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getTipoCombustible();
		} else if ("matafuego".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getMatafuego();
		} else if ("capacTanqueCombustible".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getCapacTanqueCombustible();
		} else if ("tipoAceite".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getTipoAceite();
		} else if ("cnsCombustibleCiudad".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getCnsCombustibleCiudad();
		} else if ("cnsCombustibleRuta".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getCnsCombustibleRuta();
		} else if ("kilometros".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getKilometros();
		} else if ("estado".equals(jrf.getName())) {
			valor = listaVehiculo.get(indiceVehiculoActual).getEstado();
		}
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceVehiculoActual < listaVehiculo.size();
	}

	public void addParticipante(ReporteVehiculo vehiculo) {
		this.listaVehiculo.add(vehiculo);
	}
}
