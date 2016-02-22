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

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Finalizado_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DomainObject(objectType = "FINALIZADO")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Finalizado extends EstadoMantenimiento{

	public Finalizado(Timestamp fechaCambio, String motivo) {
		super(fechaCambio, motivo);
	}
	
	public Finalizado() {
		super();
	}
	@Override
	public void iniciarProceso(Mantenimiento m,Timestamp fechaCambio, String motivo) {
	}

	@Override
	public void aceptacion(Mantenimiento m,Timestamp fechaCambio, String motivo) {
	}

	@Override
	public void finalizarMantenimiento(Mantenimiento m,Timestamp fechaCambio, String motivo) {
	}

	@Override
	public void cancelarMantenimiento(Mantenimiento m,Timestamp fechaCambio, String motivo) {
	}
	
	@Override
	public String toString(){
		return "Finalizado";
	}

}
