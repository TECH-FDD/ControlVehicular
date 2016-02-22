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
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Aceptado_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DomainObject(objectType = "ACEPTADO")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Aceptado extends EstadoMantenimiento{

	public Aceptado(Timestamp fechaCambio, String motivo) {
		super(fechaCambio, motivo);
	}
	
	public Aceptado() {
		super();
	}
	@Override
	@Programmatic
	public void aceptacion(Mantenimiento mtn,Timestamp fechaCambio,String motivo) {
		
	}

	@Override
	@Programmatic
	public void iniciarProceso(Mantenimiento mtn,Timestamp fechaCambio,String motivo) {
		//EstadoMantenimiento stAnterior = mtn.getEstadoMantenimiento();
		mtn.setEstadoMantenimiento(new EnProceso(fechaCambio,motivo));
		container.persistIfNotAlready(mtn);
		//container.removeIfNotAlready(stAnterior);
		container.informUser("Se a iniciado el proceso de mantenimiento");
	}

	@Override
	@Programmatic
	public void finalizarMantenimiento(Mantenimiento mtn,Timestamp fechaCambio,String motivo) {
	}

	@Override
	@Programmatic
	public void cancelarMantenimiento(Mantenimiento mtn,Timestamp fechaCambio,String motivo) {
	}
	
	@Override
	public String toString(){
		return "Aceptado";
	}
	@javax.inject.Inject
	DomainObjectContainer container;
}
