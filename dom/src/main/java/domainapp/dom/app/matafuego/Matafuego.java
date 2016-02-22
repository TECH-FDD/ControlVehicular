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
    
package domainapp.dom.app.matafuego;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;

import domainapp.dom.app.estadoelemento.Activo;
import domainapp.dom.app.estadoelemento.Asignado;
import domainapp.dom.app.estadoelemento.Estado;
import domainapp.dom.app.estadoelemento.Inactivo;
import domainapp.dom.app.estadoelemento.Motivo;
import domainapp.dom.app.mantenimiento.ObjetoMantenible;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Matafuego_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
		+ "FROM domainapp.dom.app.matafuego.Matafuego "),
		@javax.jdo.annotations.Query(name = "buscarPorMarca", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.matafuego.Matafuego "
				+ "WHERE marca.indexOf(:marca) >= 0 ") })
@DomainObject(objectType = "MATAFUEGO", bounded = true)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
@Inheritance(strategy= InheritanceStrategy.NEW_TABLE)
public class Matafuego extends ObjetoMantenible {
	private String marca;
	private String codigo;
	private String descripcion;
	private int capacidad;
	private Timestamp fechaAlta;
	private Timestamp fechaRecarga;
	private Timestamp fechaCadRecarga;
	private Estado estado;

	public Matafuego(String marca, String codigo, String descripcion,
			int capacidad, Timestamp fechaAlta, Timestamp fechaRecarga,
			Timestamp fechaCadRecarga) {
		super();
		this.marca = marca;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
		this.fechaAlta = fechaAlta;
		this.fechaRecarga = fechaRecarga;
		this.fechaCadRecarga = fechaCadRecarga;
		this.estado=new Activo(new Timestamp(System.currentTimeMillis()), Motivo.ALTA);
	}

	public Matafuego() {
		super();
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "10")
	@Column(allowsNull = "false")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "20")
	@Column(allowsNull = "false")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "30")
	@Column(allowsNull = "false")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "40")
	@Column(allowsNull = "false")
	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "50")
	@Column(allowsNull = "false")
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Persistent
	@MemberOrder(sequence = "60")
	@Column(allowsNull = "false")
	public Timestamp getFechaRecarga() {
		return fechaRecarga;
	}

	public void setFechaRecarga(Timestamp fechaRecarga) {
		this.fechaRecarga = fechaRecarga;
	}

	@Persistent
	@MemberOrder(sequence = "70")
	@Column(allowsNull = "false")
	public Timestamp getFechaCadRecarga() {
		return fechaCadRecarga;
	}

	public void setFechaCadRecarga(Timestamp fechaCadRecarga) {
		this.fechaCadRecarga = fechaCadRecarga;
	}

	@Persistent
	@MemberOrder(sequence = "90")
	@Column(allowsNull = "false")
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Matafuego " + marca;
	}

	/**
	 * Desactivar un Matafuego, para que el mismo no pueda usarse en un vehiculo.
	 *
	 * @return Matafuego con estado Actualizado.
	 */
	public Matafuego desactivar(@ParameterLayout(named="Motivo") Motivo motivo){
		this.getEstado().desactivarMatafuego(this, motivo, new Timestamp(System.currentTimeMillis()));
		return this;
	}

	/**
	 * Validar la lista de motivos a mostrar al momento de desactivar un Matafuego.
	 * @param motivo
	 * @return lista de motivos.
	 */
	public List<Motivo> choices0Desactivar(Motivo motivo){
		return Motivo.listar("desactivar");
	}

	/**
	 * Verificar si se debe mostrar el boton Desactivar.
	 *
	 * @return Confirmacion
	 */
	public boolean hideDesactivar(){
		if(this.getEstado() instanceof Activo ||
				this.getEstado() instanceof Asignado)
			return false;
		else
			return true;
	}

	/**
	 * Reactivar un Matafuego para poder ser utilizado en el sistema.
	 *
	 * @return this
	 */
	public Matafuego reactivar(){
		this.getEstado().reactivarMatafuego(this);
		return this;
	}

	/**
	 * Verificar si se debe mosrar el boton.
	 *
	 * @return Confirmacion de si se debe mostrar el Boton.
	 */
	public boolean hideReactivar(){
		if (this.getEstado() instanceof Inactivo)
			return false;
		else
			return true;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
