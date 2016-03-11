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
    
package domainapp.dom.app.vehiculo;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.ActionLayout.Position;

import domainapp.dom.app.estadoelemento.Activo;
import domainapp.dom.app.estadoelemento.Asignado;
import domainapp.dom.app.estadoelemento.Inactivo;
import domainapp.dom.app.estadoelemento.Motivo;
import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.gps.RepositorioGps;
import domainapp.dom.app.mantenimiento.ObjetoMantenible;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.matafuego.RepositorioMatafuego;
import domainapp.dom.app.aceite.TipoAceite;
import domainapp.dom.app.combustible.TipoCombustible;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Vehiculo_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.vehiculo "),
		@javax.jdo.annotations.Query(name = "BuscarMarca", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.vehiculo "
				+ "WHERE marca.indexOf(:marca) >= 0"),
		@javax.jdo.annotations.Query(name = "BuscarNombre", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.vehiculo "
				+ "WHERE nombre.indexOf(:nombre) >= 0 "),
		@javax.jdo.annotations.Query(name = "BuscarModelo", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.vehiculo " + "WHERE modelo==:modelo"),
		@javax.jdo.annotations.Query(name = "BuscarPatente", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.vehiculo "
				+ "WHERE patente.indexOf(:patente) >= 0 "),
		@javax.jdo.annotations.Query(name = "BuscarNumeroChasis", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.vehiculo "
				+ "WHERE numeroChasis.indexOf(:numeroChasis) >= 0 ") })
@DomainObject(objectType = "VEHICULO", bounded = true)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
@Inheritance(strategy= InheritanceStrategy.NEW_TABLE)
@MemberGroupLayout(columnSpans = { 4, 4, 4 },
					left = { "General" },
					middle = { "Equipamiento", "Estado" },
					right = { "Detalles Tecnicos" })
public class Vehiculo extends ObjetoMantenible {
	private String marca;
	private String nombre;
	private Integer modelo;
	private Timestamp fechaCompra;
	private String patente;
	private String numeroChasis;
	private Integer polizaSeguro;
	private Gps gps;
	private TipoCombustible TipoCombustible;
	private Matafuego matafuego;
	private Integer capacTanqueCombustible;
	private TipoAceite tipoAceite;
	private String cnsCombustibleRuta;
	private String cnsCombuestibleCiudad;
	private Integer kilometros;

	@Persistent
	@MemberOrder(sequence = "1")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "false")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Persistent
	@MemberOrder(sequence = "2")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "false")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Persistent
	@MemberOrder(sequence = "3")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "false")
	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	@Persistent
	@MemberOrder(sequence = "4")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "false")
	public Timestamp getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Timestamp fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	@Persistent
	@MemberOrder(sequence = "5")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "false")
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	@Persistent
	@MemberOrder(sequence = "6")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "false")
	public String getNumeroChasis() {
		return numeroChasis;
	}

	public void setNumeroChasis(String numeroChasis) {
		this.numeroChasis = numeroChasis;
	}

	@Persistent
	@MemberOrder(sequence = "7")
	@Column(allowsNull = "false")
	public Integer getPolizaSeguro() {
		return polizaSeguro;
	}

	public void setPolizaSeguro(Integer polizaSeguro) {
		this.polizaSeguro = polizaSeguro;
	}

	@Persistent
	@MemberOrder(sequence = "8", name = "Equipamiento")
	@Column(allowsNull = "true")
	public Gps getGps() {
		return gps;
	}

	public void setGps(Gps gps) {
		this.gps = gps;
	}

	@Persistent
	@MemberOrder(sequence = "9", name = "Detalles Tecnicos")
	@Column(allowsNull = "Tipo Combustible")
	public TipoCombustible getTipoCombustible() {
		return TipoCombustible;
	}

	public void setTipoCombustible(TipoCombustible tipoCombustible) {
		this.TipoCombustible = tipoCombustible;
	}

	@Persistent
	@MemberOrder(sequence = "10", name = "Detalles Tecnicos")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "true")
	public Integer getCapacTanqueCombustible() {
		return capacTanqueCombustible;
	}

	public void setCapacTanqueCombustible(Integer capacTanqueCombustible) {
		this.capacTanqueCombustible = capacTanqueCombustible;
	}

	@Persistent
	@MemberOrder(sequence = "11", name = "Detalles Tecnicos")
	@Column(allowsNull = "Aceite")
	public TipoAceite getTipoAceite() {
		return tipoAceite;
	}

	public void setTipoAceite(TipoAceite tipoAceite) {
		this.tipoAceite = tipoAceite;
	}

	@Persistent
	@MemberOrder(sequence = "12", name = "Detalles Tecnicos")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "true")
	public String getCnsCombustibleRuta() {
		return cnsCombustibleRuta;
	}

	public void setCnsCombustibleRuta(String cnsCombustibleRuta) {
		this.cnsCombustibleRuta = cnsCombustibleRuta;
	}

	@Persistent
	@MemberOrder(sequence = "13", name = "Detalles Tecnicos")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "true")
	public String getCnsCombuestibleCiudad() {
		return cnsCombuestibleCiudad;
	}

	public void setCnsCombuestibleCiudad(String cnsCombuestibleCiudad) {
		this.cnsCombuestibleCiudad = cnsCombuestibleCiudad;
	}

	@Persistent
	@MemberOrder(sequence = "14", name = "Estado")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "true")
	public Integer getKilometros() {
		return kilometros;
	}

	public void setKilometros(Integer kilometros) {
		this.kilometros = kilometros;
	}

	@Persistent
	@MemberOrder(sequence = "15", name = "Equipamiento")
	@Column(allowsNull = "true")
	public Matafuego getMatafuego() {
		return matafuego;
	}

	public void setMatafuego(Matafuego matafuego) {
		this.matafuego = matafuego;
	}

	@Override
	public String toString() {
		return marca + ", " + nombre;
	}

	public Vehiculo(String marca, String nombre, Integer modelo,
			Timestamp fechaCompra, String patente, String numeroChasis,
			Integer polizaSeguro, Gps gps, TipoCombustible tipoCombustible,
			Integer capacTanqueCombustible, TipoAceite tipoAceite,
			String cnsCombustibleRuta, String cnsCombuestibleCiudad,
			Integer kilometros, Matafuego matafuego) {
		super();
		this.marca = marca;
		this.nombre = nombre;
		this.modelo = modelo;
		this.fechaCompra = fechaCompra;
		this.patente = patente;
		this.numeroChasis = numeroChasis;
		this.polizaSeguro = polizaSeguro;
		this.gps = gps;
		this.TipoCombustible = tipoCombustible;
		this.capacTanqueCombustible = capacTanqueCombustible;
		this.tipoAceite = tipoAceite;
		this.cnsCombustibleRuta = cnsCombustibleRuta;
		this.cnsCombuestibleCiudad = cnsCombuestibleCiudad;
		this.kilometros = kilometros;
		this.matafuego = matafuego;
	}

	public Vehiculo() {
		super();
	}

	/**
	 * Desactivar un Vehiculo, para que el mismo no pueda usarse en un vehiculo.
	 *
	 * @return Matafuego con estado Actualizado.
	 */
	@MemberOrder(sequence = "16", name = "Estado")
	@ActionLayout(named = "Deseactivar", position = Position.PANEL)
	public Vehiculo desactivar(@ParameterLayout(named="Motivo") Motivo motivo){
		this.getEstado().desactivar(this, motivo, new Timestamp(System.currentTimeMillis()));
		return this;
	}

	/**
	 * Validar la lista de motivos a mostrar al momento de desactivar un Vehiculo.
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
		if (this.getEstado() instanceof Activo ||
				this.getEstado() instanceof Asignado)
			return false;
		else
			return true;
	}

	/**
	 * Reactivar un Vehiculo para poder ser utilizado en el sistema.
	 *
	 * @return this
	 */
	@MemberOrder(sequence = "16", name = "Estado")
	@ActionLayout(named = "Reactivar", position = Position.PANEL)
	public Vehiculo reactivar(){
		this.getEstado().reactivar(this);
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

	/**
	 * Cambiar el Gps del Vehiculo seleccionado.
	 * @param gps
	 * @return Vehiculo seleccionado.
	 */
	@MemberOrder(sequence = "1", name = "Gps")
	@ActionLayout(named = "Cambiar Gps", position = Position.PANEL)
	public Vehiculo updateGps(@ParameterLayout(named = "Gps") Gps gps){
		//Desasigno el Gps anterior.
		if(this.getGps() != null)
			this.getGps().getEstado().desasignar(this.getGps());
		//Cambio el estado del nuevo Gps a Asginado
		gps.getEstado().asignar(gps);
		//Actualizo el vehiculo con el nuevo Gps
		this.setGps(gps);
		container.persistIfNotAlready(this);
		return this;
	}

	/**
	 * Mostrar lista de Gps
	 * @return Lista de Gps disponibles.
	 */
	public List<Gps> choices0UpdateGps(){
		return repoGps.gpsNoAsignados(container.allInstances(Gps.class));
	}

	/**
	 * Cambiar el Matafuego del Vehiculo seleccionado.
	 * @param gps
	 * @return Vehiculo seleccionado.
	 */
	@MemberOrder(sequence = "1", name = "Matafuego")
	@ActionLayout(named = "Cambiar Matafuego", position = Position.PANEL)
	public Vehiculo updateMatafuego(@ParameterLayout(named = "Matafuego") Matafuego matafuego){
		if (this.getMatafuego() != null)
			this.getMatafuego().getEstado().desasignar(this.getMatafuego());
		matafuego.getEstado().asignar(matafuego);
		this.setMatafuego(matafuego);
		container.persistIfNotAlready(this);
		return this;
	}

	/**
	 * Mostrar lista de Matafuegos
	 * @return Lista de Matafuegos disponibles.
	 */
	public List<Matafuego> choices0UpdateMatafuego(){
		return repoMatafuego.noAsignados(container.allInstances(Matafuego.class));
	}

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	RepositorioGps repoGps;
	@javax.inject.Inject
	RepositorioMatafuego repoMatafuego;
}
