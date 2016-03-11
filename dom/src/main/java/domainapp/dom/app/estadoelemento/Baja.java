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
    
package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Programmatic;

import domainapp.dom.app.mantenimiento.ObjetoMantenible;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="Baja_ID")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

@DomainObject(
        objectType = "BAJA"
)

@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_CHILD
)
public class Baja extends Estado {

	public Baja() {
		super();
	}

	public Baja(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@Override
	public String toString() {
		return "Baja";
	}

	/**********************************
	 * Desactivacion de los elementos.*
	 **********************************/

	@Override
	@Programmatic
	public void desactivar(final ObjetoMantenible objeto, final Motivo motivo, final Timestamp fecha) {
		container.warnUser("El Elemento seleccionado, se encuentra en estado de Baja,"
				+ " por lo tanto no puede ser desactivado");
	}

	/*********************************
	 * Reactivacion de los elementos.*
	 *********************************/

	@Override
	@Programmatic
	public void reactivar(final ObjetoMantenible objeto) {
		container.warnUser("El Elemento seleccionado, ha sido dado de Baja, "
				+ "por lo que no puede ser reactivado.");
	}

	/*******************************
	 * Asignacion de los elementos.*
	 *******************************/

	@Override
	@Programmatic
	public void asignar(final ObjetoMantenible objeto) {
		container.warnUser("El Elemento seleccionado fue dado de Baja, por lo que no puede ser asignado.");
	}

	/**********************************
	 * Desasignacion de los elementos.*
	 **********************************/

	@Override
	public void desasignar(final ObjetoMantenible objeto) {
		container.warnUser("El Elemento seleccionado, debe encontrarse en estado Asignado para poder ser Desasignado.");
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
