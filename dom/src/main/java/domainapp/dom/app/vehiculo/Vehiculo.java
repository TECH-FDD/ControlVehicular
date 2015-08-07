package domainapp.dom.app.vehiculo;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
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
import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.matafuego.Matafuego;
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
public class Vehiculo {
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
	private String kilometros;
	private Estado estado;

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
	@MemberOrder(sequence = "8")
	@Column(allowsNull = "Gps")
	public Gps getGps() {
		return gps;
	}

	public void setGps(Gps gps) {
		this.gps = gps;
	}

	@Persistent
	@MemberOrder(sequence = "9")
	@Column(allowsNull = "Tipo Combustible")
	public TipoCombustible getTipoCombustible() {
		return TipoCombustible;
	}

	public void setTipoCombustible(TipoCombustible tipoCombustible) {
		this.TipoCombustible = tipoCombustible;
	}

	@Persistent
	@MemberOrder(sequence = "10")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "true")
	public Integer getCapacTanqueCombustible() {
		return capacTanqueCombustible;
	}

	public void setCapacTanqueCombustible(Integer capacTanqueCombustible) {
		this.capacTanqueCombustible = capacTanqueCombustible;
	}

	@Persistent
	@MemberOrder(sequence = "11")
	@Column(allowsNull = "Aceite")
	public TipoAceite getTipoAceite() {
		return tipoAceite;
	}

	public void setTipoAceite(TipoAceite tipoAceite) {
		this.tipoAceite = tipoAceite;
	}

	@Persistent
	@MemberOrder(sequence = "12")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "true")
	public String getCnsCombustibleRuta() {
		return cnsCombustibleRuta;
	}

	public void setCnsCombustibleRuta(String cnsCombustibleRuta) {
		this.cnsCombustibleRuta = cnsCombustibleRuta;
	}

	@Persistent
	@MemberOrder(sequence = "13")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "true")
	public String getCnsCombuestibleCiudad() {
		return cnsCombuestibleCiudad;
	}

	public void setCnsCombuestibleCiudad(String cnsCombuestibleCiudad) {
		this.cnsCombuestibleCiudad = cnsCombuestibleCiudad;
	}

	@Persistent
	@MemberOrder(sequence = "14")
	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "true")
	public String getKilometros() {
		return kilometros;
	}

	public void setKilometros(String kilometros) {
		this.kilometros = kilometros;
	}

	@Persistent
	@MemberOrder(sequence = "15")
	@Column(allowsNull = "true")
	public Matafuego getMatafuego() {
		return matafuego;
	}

	public void setMatafuego(Matafuego matafuego) {
		this.matafuego = matafuego;
	}

	@Persistent
	@MemberOrder(sequence = "16")
	@Column(allowsNull = "false")
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return marca + ", " + modelo;
	}

	public Vehiculo(String marca, String nombre, Integer modelo,
			Timestamp fechaCompra, String patente, String numeroChasis,
			Integer polizaSeguro, Gps gps, TipoCombustible tipoCombustible,
			Integer capacTanqueCombustible, TipoAceite tipoAceite,
			String cnsCombustibleRuta, String cnsCombuestibleCiudad,
			String kilometros, Matafuego matafuego) {
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
		this.estado=new Activo(new Timestamp(System.currentTimeMillis()),Motivo.ALTA);
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
	public Vehiculo desactivar(@ParameterLayout(named="Motivo") Motivo motivo){
		this.getEstado().desactivarVehiculo(this, motivo, new Timestamp(System.currentTimeMillis()));
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
	public Vehiculo reactivar(){
		this.getEstado().reactivarVehiculo(this);
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
