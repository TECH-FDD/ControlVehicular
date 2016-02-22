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

public class AreaDataSource implements JRDataSource{
	private List<ReporteArea> listaArea = new ArrayList<ReporteArea>();
	private int indiceAreaActual = -1;
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		
		if ("codigoArea".equals(jrf.getName())) {
			valor = listaArea.get(indiceAreaActual).getCodigoArea();
		} else if ("nombre".equals(jrf.getName())) {
			valor = listaArea.get(indiceAreaActual).getNombre();
		} else if ("descripcion".equals(jrf.getName())) {
			valor = listaArea.get(indiceAreaActual).getDescripcion();
		} else if ("fechaAlta".equals(jrf.getName())) {
			valor = listaArea.get(indiceAreaActual).getFechaAlta();
		} else if ("activo".equals(jrf.getName())) {
			valor = listaArea.get(indiceAreaActual).getActivo();
		} 
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceAreaActual< listaArea.size();
	}

	public void addParticipante(ReporteArea area) {
		this.listaArea.add(area);
	}
}
