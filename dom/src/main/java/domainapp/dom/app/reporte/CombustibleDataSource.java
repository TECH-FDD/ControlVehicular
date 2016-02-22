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

public class CombustibleDataSource implements JRDataSource{
	private List<ReporteCombustible> listaCombustible = new ArrayList<ReporteCombustible>();
	private int indiceCombustibleActual = -1;
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("codigo".equals(jrf.getName())) {
			valor = listaCombustible.get(indiceCombustibleActual).getCodigo();
		} else if ("nombre".equals(jrf.getName())) {
			valor = listaCombustible.get(indiceCombustibleActual).getNombre();
		} else if ("descripcion".equals(jrf.getName())) {
			valor = listaCombustible.get(indiceCombustibleActual).getDescripcion();
		} else if ("empresa".equals(jrf.getName())) {
			valor = listaCombustible.get(indiceCombustibleActual).getEmpresa();
		} else if ("activo".equals(jrf.getName())) {
			valor = listaCombustible.get(indiceCombustibleActual).getActivo();
		} else if ("categoria".equals(jrf.getName())) {
			valor = listaCombustible.get(indiceCombustibleActual).getCategoria();
		} else if ("precioLitro".equals(jrf.getName())) {
			valor = listaCombustible.get(indiceCombustibleActual).getPrecioLitro();
		} else if ("precioAnterior".equals(jrf.getName())) {
			valor = listaCombustible.get(indiceCombustibleActual).getPrecioAnterior();
		} else if ("porcentajeAumento".equals(jrf.getName())) {
			valor = listaCombustible.get(indiceCombustibleActual).getPorcentajeAumento();
		} else if ("octanaje".equals(jrf.getName())) {
			valor = listaCombustible.get(indiceCombustibleActual).getOctanaje();
		} else if ("tipoCombustible".equals(jrf.getName())) {
			valor = listaCombustible.get(indiceCombustibleActual).getTipoCombustible();
		} 
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceCombustibleActual< listaCombustible.size();
	}

	public void addParticipante(ReporteCombustible combustible) {
		this.listaCombustible.add(combustible);
	}
}

