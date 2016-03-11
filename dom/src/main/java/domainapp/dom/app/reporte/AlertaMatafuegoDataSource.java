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

public class AlertaMatafuegoDataSource implements JRDataSource {
	private List<ReporteAlerta> listaAlerta = new ArrayList<ReporteAlerta>();
	private int indiceAlertaActual = -1;

	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("nombre".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getNombre();
		} else if ("descripcion".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getDescripcion();
		} else if ("estadoAlerta".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getEstadoAlerta();
		} else if ("alerta".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getAlerta();
		} else if ("elemento".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getElemento();
		} else if ("fechaAlta".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getFechaAlta();
		} else if ("subTitulo".equals(jrf.getName())) {
			valor = listaAlerta.get(indiceAlertaActual).getSubTitulo();
		}
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceAlertaActual < listaAlerta.size();
	}

	public void addParticipante(ReporteAlerta alerta) {
		this.listaAlerta.add(alerta);
	}
}
