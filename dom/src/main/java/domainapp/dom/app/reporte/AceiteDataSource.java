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

public class AceiteDataSource implements JRDataSource{
	private List<ReporteAceite> listaAceite = new ArrayList<ReporteAceite>();
	private int indiceAceiteActual = -1;
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("codigo".equals(jrf.getName())) {
			valor = listaAceite.get(indiceAceiteActual).getCodigo();
		} else if ("nombre".equals(jrf.getName())) {
			valor = listaAceite.get(indiceAceiteActual).getNombre();
		} else if ("marca".equals(jrf.getName())) {
			valor = listaAceite.get(indiceAceiteActual).getMarca();
		} else if ("descripcion".equals(jrf.getName())) {
			valor = listaAceite.get(indiceAceiteActual).getDescripcion();
		} else if ("tipoAceite".equals(jrf.getName())) {
			valor = listaAceite.get(indiceAceiteActual).getTipoAceite();
		} else if ("duracion".equals(jrf.getName())) {
		valor = listaAceite.get(indiceAceiteActual).getDuracion();
		} else if ("fechaAlta".equals(jrf.getName())) {
			valor = listaAceite.get(indiceAceiteActual).getFechaAlta();
		} else if ("activo".equals(jrf.getName())) {
			valor = listaAceite.get(indiceAceiteActual).getActivo();
		} 
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceAceiteActual< listaAceite.size();
	}

	public void addParticipante(ReporteAceite aceite) {
		this.listaAceite.add(aceite);
	}
}
