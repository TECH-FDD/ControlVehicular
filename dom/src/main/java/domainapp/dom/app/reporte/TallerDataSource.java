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

public class TallerDataSource implements JRDataSource{
	private List<ReporteTaller> listaTaller = new ArrayList<ReporteTaller>();
	private int indiceTallerActual = -1;
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;

		if ("nombreComercial".equals(jrf.getName())) {
			valor = listaTaller.get(indiceTallerActual).getNombreComercial();
		} else if ("descripcion".equals(jrf.getName())) {
			valor = listaTaller.get(indiceTallerActual).getDescripcion();
		} else if ("direccion".equals(jrf.getName())) {
			valor = listaTaller.get(indiceTallerActual).getDireccion();
		} else if ("telefono".equals(jrf.getName())) {
			valor = listaTaller.get(indiceTallerActual).getTelefono();
		} else if ("codigo".equals(jrf.getName())) {
			valor = listaTaller.get(indiceTallerActual).getCodigo();
		} else if ("email".equals(jrf.getName())) {
			valor = listaTaller.get(indiceTallerActual).getEmail();
		}  else if ("rubro".equals(jrf.getName())) {
			valor = listaTaller.get(indiceTallerActual).getRubro();
		} 
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceTallerActual < listaTaller.size();
	}

	public void addParticipante(ReporteTaller taller) {
		this.listaTaller.add(taller);
	}
}
