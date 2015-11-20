package domainapp.dom.app.cargaCombustible;

import java.math.BigDecimal;
import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;

import domainapp.dom.app.combustible.Combustible;
import domainapp.dom.app.vehiculo.Vehiculo;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "CargaCombustible_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DomainObject(objectType = "Carga Combustible")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class CargaCombustible {
	private Vehiculo vehiculo;
	private Date fechaCarga;
	private Combustible combustible;
	private BigDecimal costoTotal;
	private BigDecimal litrosCargados;

	public CargaCombustible(Vehiculo vehiculo, Date fechaCarga, Combustible combustible, BigDecimal costoTotal,
			BigDecimal litrosCargados) {
		super();
		this.vehiculo = vehiculo;
		this.fechaCarga = fechaCarga;
		this.combustible = combustible;
		this.costoTotal = costoTotal;
		this.litrosCargados = litrosCargados;
	}

	@Persistent
	@MemberOrder(sequence = "30")
	@javax.jdo.annotations.Column(allowsNull = "Vehiculo")
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Persistent
	@MemberOrder(sequence = "31")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	@Persistent
	@MemberOrder(sequence = "32")
	@javax.jdo.annotations.Column(allowsNull = "Combustible")
	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}

	@Persistent
	@MemberOrder(sequence = "33")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public BigDecimal getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}

	@Persistent
	@MemberOrder(sequence = "34")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public BigDecimal getLitrosCargados() {
		return litrosCargados;
	}

	public void setLitrosCargados(BigDecimal litrosCargados) {
		this.litrosCargados = litrosCargados;
	}

	@Override
	public String toString() {
		return vehiculo + ". Costo Carga: " + costoTotal;
	}

}
