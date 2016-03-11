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
    
package domainapp.dom.app.mantenimiento;

import java.sql.Timestamp;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;

import domainapp.dom.app.estadoelemento.Activo;
import domainapp.dom.app.estadoelemento.Estado;
import domainapp.dom.app.estadoelemento.Motivo;

@PersistenceCapable
@DomainObject(objectType = "OBJETOMANTENIBLE", bounded = true)
@Inheritance(strategy= InheritanceStrategy.NEW_TABLE)

public abstract class ObjetoMantenible {

	public ObjetoMantenible() {
		super();
		this.estado = new Activo(new Timestamp(System.currentTimeMillis()), Motivo.ALTA);
	}

	protected Estado estado;
	protected String publico;

	@Persistent
	@MemberOrder(sequence = "100")
	@Column(allowsNull = "false")
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return publico;
	}
}
