package domainapp.dom.app.gps;

import java.sql.Timestamp;

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
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Where;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Gps_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.gps.Gps"),
		@javax.jdo.annotations.Query(name = "buscarPorMarca", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.gps.Gps "
				+ "WHERE marca.indexOf(:marca)>= 0"),
		@javax.jdo.annotations.Query(name = "buscarPorModelo", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.gps.Gps "
				+ "WHERE modelo.indexOf(:modelo)>= 0"),
		@javax.jdo.annotations.Query(name = "buscarPorCodigoIdentificacion", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.gps.Gps "
				+ "WHERE codIdentificacion.indexOf(:codIdentificacion)>= 0") })
@DomainObject(objectType = "GPS")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Gps {

	private String codIdentificacion;
	private String marca;
	private String modelo;
	private String descripcion;
	private Timestamp fechaAlta;
	private Timestamp fechaAsigVehiculo;
	private String obsEstadoDispositivo;
	private boolean activo;

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getCodIdentificacion() {
		return codIdentificacion;
	}

	public void setCodIdentificacion(String codIdentificacion) {
		this.codIdentificacion = codIdentificacion;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Persistent
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Persistent
	@MemberOrder(sequence = "6")
	@Column(allowsNull = "true")
	public Timestamp getFechaAsigVehiculo() {
		return fechaAsigVehiculo;
	}

	public void setFechaAsigVehiculo(Timestamp fechaAsigVehiculo) {
		this.fechaAsigVehiculo = fechaAsigVehiculo;
	}

	@Persistent
	@MemberOrder(sequence = "7")
	@Column(allowsNull = "true")
	public String getObsEstadoDispositivo() {
		return obsEstadoDispositivo;
	}

	public void setObsEstadoDispositivo(String obsEstadoDispositivo) {
		this.obsEstadoDispositivo = obsEstadoDispositivo;
	}

	@Property(hidden = Where.EVERYWHERE)
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Gps marca=" + marca + ", modelo=" + modelo;
	}

	public Gps(String codIdentificacion, String marca, String modelo,
			String descripcion, Timestamp fechaAlta,
			Timestamp fechaAsigVehiculo, String obsEstadoDispositivo,
			boolean activo) {
		super();
		this.codIdentificacion = codIdentificacion;
		this.marca = marca;
		this.modelo = modelo;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaAsigVehiculo = fechaAsigVehiculo;
		this.obsEstadoDispositivo = obsEstadoDispositivo;
		this.activo = activo;
	}

	public Gps() {
		super();
	}

	/**
	 * Desactivar un Gps, para que el mismo no pueda usarse en un vehiculo.
	 * 
	 * @return mensaje de confirmacion.
	 */

	public String eliminarGps(
			final @ParameterLayout(named = "Razon baja") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String razonBaja) {

		BajaGps baja = new BajaGps();
		this.setActivo(false);
		baja.setFechaBaja(new Timestamp(System.currentTimeMillis()));
		baja.setRazonBaja(razonBaja);
		baja.setGps(this);

		container.persistIfNotAlready(baja);
		return "El Gps ha sido eliminado de manera exitosa!";
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
