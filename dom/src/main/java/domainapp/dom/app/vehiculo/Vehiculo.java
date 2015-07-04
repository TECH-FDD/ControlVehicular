package domainapp.dom.app.vehiculo;

import java.sql.Timestamp;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;

import domainapp.dom.app.estadoelemento.Activo;
import domainapp.dom.app.estadoelemento.Estado;
import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.aceite.Aceite;
import domainapp.dom.app.combustible.Combustible;

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
	private Combustible combustible;
	private Matafuego matafuego;
	private Integer capacTanqueCombustible;
	private Aceite aceite;
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
	@Column(allowsNull = "Combustible")
	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
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
	public Aceite getAceite() {
		return aceite;
	}

	public void setAceite(Aceite aceite) {
		this.aceite = aceite;
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
			Integer polizaSeguro, Gps gps, Combustible combustible,
			Integer capacTanqueCombustible, Aceite aceite,
			String cnsCombustibleRuta, String cnsCombuestibleCiudad,
			String kilometros) {
		super();
		this.marca = marca;
		this.nombre = nombre;
		this.modelo = modelo;
		this.fechaCompra = fechaCompra;
		this.patente = patente;
		this.numeroChasis = numeroChasis;
		this.polizaSeguro = polizaSeguro;
		this.gps = gps;
		this.combustible = combustible;
		this.capacTanqueCombustible = capacTanqueCombustible;
		this.aceite = aceite;
		this.cnsCombustibleRuta = cnsCombustibleRuta;
		this.cnsCombuestibleCiudad = cnsCombuestibleCiudad;
		this.kilometros = kilometros;
		this.estado=new Activo(new Timestamp(System.currentTimeMillis()),null);
	}

	public Vehiculo() {
		super();
	}

}
