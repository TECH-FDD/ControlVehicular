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
    
package domainapp.dom.app.combustible;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "TipoCombustible_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
		+ "FROM domainapp.dom.app.combustible.TipoCombustible"),
	@javax.jdo.annotations.Query(name = "buscarPorTipo", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.combustible.TipoCombustible "
				+ "WHERE tipo.indexOf(:tipo) >= 0")})
@DomainObject(objectType = "TIPOCOMBUSTIBLE", bounded = true)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class TipoCombustible {

	private String tipo;
	private String descripcion;

	public TipoCombustible(String tipo, String descripcion) {
		super();
		this.tipo = tipo;
		this.descripcion = descripcion;
	}

	public TipoCombustible() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "10")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "20")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return tipo;
	}

}
