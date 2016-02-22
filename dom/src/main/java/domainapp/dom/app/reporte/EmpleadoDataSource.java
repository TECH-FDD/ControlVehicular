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

public class EmpleadoDataSource implements JRDataSource {
	private List<ReporteEmpleado> listaEmpleado = new ArrayList<ReporteEmpleado>();
	private int indiceEmpleadoActual = -1;

	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;

		if ("legajo".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getLegajo();
		} else if ("nombre".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getNombre();
		} else if ("tipoDocumento".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getTipoDocumento();
		} else if ("nroDocumento".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getNroDocumento();
		} else if ("fechaNacimiento".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getFechaNacimiento();
		} else if ("domicilio".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getDomicilio();
		} else if ("provincia".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getProvincia();
		} else if ("ciudad".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getCiudad();
		} else if ("codigoPostal".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getCodigoPostal();
		} else if ("fechaAlta".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getFechaAlta();
		} else if ("sexo".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getSexo();
		} else if ("telefono".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getTelefono();
		} else if ("email".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getLegajo();
		} else if ("area".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getArea();
		} else if ("activo".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getActivo();
		} else if ("vehiculo".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getVehiculo();
		}
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceEmpleadoActual < listaEmpleado.size();
	}

	public void addParticipante(ReporteEmpleado empleado) {
		this.listaEmpleado.add(empleado);
	}
}