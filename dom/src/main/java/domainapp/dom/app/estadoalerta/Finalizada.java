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
    
package domainapp.dom.app.estadoalerta;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;

import domainapp.dom.app.alerta.AlertaMatafuego;
import domainapp.dom.app.alerta.AlertaVehiculo;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Finalizada_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@DomainObject(objectType = "FINALIZADA")

@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Finalizada extends EstadoAlerta {

	@Override
	public String toString() {
		return "Finalizada";
	}
	public Finalizada(Timestamp fechaCambio) {
		super(fechaCambio);
	}
	@Override
	public void cambiarAlerta(AlertaMatafuego matafuego) {
	}

	@Override
	public void cambiarAlerta(AlertaVehiculo vehiculo) {
	}

	@Override
	public void aplazarAlertas(AlertaMatafuego matafuego) {
		container.warnUser("El Alerta no se puede aplazar, ya que se encuentra finalizada");
		
	}

	@Override
	public void aplazarAlertas(AlertaVehiculo vehiculo) {
		container.warnUser("El Alerta no se puede aplazar, ya que se encuentra finalizada");
		
	}
	@Override
	public void finalizarAlertas(AlertaMatafuego matafuego) {
		container.warnUser("El Alerta ya se encuentra finalizada");
		
	}

	@Override
	public void finalizarAlertas(AlertaVehiculo vehiculo) {
		container.warnUser("El Alerta ya se encuentra finalizada");
	}

}
