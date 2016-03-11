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
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Programmatic;

import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.mantenimiento.ObjetoMantenible;
import domainapp.dom.app.vehiculo.Vehiculo;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="Asignado_ID")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

@DomainObject(
        objectType = "ASIGNADO"
)

@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_CHILD
)
public class Asignado extends Estado {

	public Asignado() {
		super();
	}

	public Asignado(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@Override
	public String toString() {
		return "Asignado";
	}

	/**********************************
	 * Desactivacion de los elementos.*
	 **********************************/

	@Override
	@Programmatic
	public void desactivar(final ObjetoMantenible objeto, final Motivo motivo, final Timestamp fecha) {

		//Desasigno el ObjetoMantenible de su contenedor.
		desasignar(objeto);

		Estado e= nuevoEstadoInactivo(fecha, motivo);
		actualizarElemento(objeto, e);
		container.informUser("Se desactivo el Elemento de manera exitosa.");
	}

	/**********************************
	 * Desasignacion de los elementos.*
	 **********************************/

	@Override
	@Programmatic
	public void desasignar(final ObjetoMantenible objeto) {
		// Busco y actualizo su contedor
		if (objeto instanceof Vehiculo) {
			final List<Empleado> le = container.allInstances(Empleado.class);
			for (Empleado e : le) {
				if (e.getVehiculo().equals(objeto)) {
					e.setVehiculo(null);
					container.persistIfNotAlready(e);
					container.warnUser("El Empleado " + e.toString() + " se encuentra sin Vehiculo asignado.");
					break;
				}
			}
		}
		else {
			final List<Vehiculo> lv = container.allInstances(Vehiculo.class);
			for (Vehiculo v : lv) {
				if (v.getGps().equals(objeto) || v.getMatafuego().equals(objeto)) {
					if (objeto instanceof Gps) {
						v.setGps(null);
						container.warnUser("El Vehiculo " + v.toString()
								+ " se encuentra sin Gps asignado.");
					}
					else {
						v.setMatafuego(null);
						container.warnUser("El Vehiculo " + v.toString()
								+ " se encuentra sin Gps asignado.");
					}
					container.persistIfNotAlready(v);
					break;
				}
			}
		}
		actualizarElemento(objeto, new Activo(objeto.getEstado().getFechaCambio(),objeto.getEstado().getMotivo()));
	}

	/*********************************
	 * Reactivacion de los elementos.*
	 *********************************/

	@Override
	@Programmatic
	public void reactivar(final ObjetoMantenible objeto) {
		container.warnUser("El Elemento seleccionado, se encuentra en estado Asignado y Activo.");
	}

	/*******************************
	 * Asignacion de los elementos.*
	 *******************************/

	@Override
	@Programmatic
	public void asignar(final ObjetoMantenible objeto) {
		container.warnUser("El Elemento seleccionado ya se encuentra asignado.");
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
