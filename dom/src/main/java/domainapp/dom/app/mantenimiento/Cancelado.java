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

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Programmatic;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Cancelado_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DomainObject(objectType = "CANCELADO")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Cancelado extends EstadoMantenimiento {
	
	
	public Cancelado(Timestamp fechaCambio, String motivo) {
		super(fechaCambio, motivo);
	}
	
	public Cancelado() {
		super();
	}	
	
	@Override
	@Programmatic
	public void iniciarProceso(Mantenimiento m,Timestamp fechaCambio, String motivo) {
	}

	@Override
	@Programmatic
	public void aceptacion(Mantenimiento m,Timestamp fechaCambio, String motivo) {
		if(m.getEstadoMantenimiento() instanceof Cancelado){
			m.setEstadoMantenimiento(new Aceptado(fechaCambio, motivo));
			container.persistIfNotAlready(m);
			container.informUser("El mantenimiento ha sido aceptado, para ser iniciado");
		}else{
			container.informUser("Intenta aceptar un mantenimiento que ya se enceuntra en proceso");
		}
	}

	@Override
	@Programmatic
	public void finalizarMantenimiento(Mantenimiento m,Timestamp fechaCambio, String motivo) {
	}

	@Override
	@Programmatic
	public void cancelarMantenimiento(Mantenimiento m,Timestamp fechaCambio, String motivo) {
	}
	
	@Override
	public String toString(){
		return "Cancelado";
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
}
