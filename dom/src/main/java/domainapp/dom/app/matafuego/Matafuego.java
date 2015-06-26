package domainapp.dom.app.matafuego;

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

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Matafuego_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.matafuego.Matafuego " )})
@DomainObject(objectType = "MATAFUEGO", bounded = true)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Matafuego {
	private String nombre;
	private String codigo;
	private String descripcion;
	private int capacidad;
	private Timestamp fechaAlta;
	private Timestamp fechaRecarga;
	private Timestamp fechaCadRecarga;
	private String vehiculo;
	private String mantenimiento;
	private String reparacion;
	
	
	public Matafuego(String nombre, String codigo, String descripcion,
			int capacidad, Timestamp fechaAlta, Timestamp fechaRecarga,
			Timestamp fechaCadRecarga, String vehiculo, String mantenimiento,
			String reparacion) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
		this.fechaAlta = fechaAlta;
		this.fechaRecarga = fechaRecarga;
		this.fechaCadRecarga = fechaCadRecarga;
		this.vehiculo = vehiculo;
		this.mantenimiento = mantenimiento;
		this.reparacion = reparacion;
	}
	public Matafuego() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence="10")
	@Column(allowsNull="false")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence="20")
	@Column(allowsNull="false")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence="30")
	@Column(allowsNull="false")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Persistent
	@Property(editing = Editing.DISABLED )
	@MemberOrder(sequence="40")
	@Column(allowsNull="false")
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence="50")
	@Column(allowsNull="false")
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	@Persistent
	@MemberOrder(sequence="60")
	@Column(allowsNull="false")
	public Timestamp getFechaRecarga() {
		return fechaRecarga;
	}
	public void setFechaRecarga(Timestamp fechaRecarga) {
		this.fechaRecarga = fechaRecarga;
	}
	@Persistent
	@MemberOrder(sequence="70")
	@Column(allowsNull="false")
	public Timestamp getFechaCadRecarga() {
		return fechaCadRecarga;
	}
	public void setFechaCadRecarga(Timestamp fechaCadRecarga) {
		this.fechaCadRecarga = fechaCadRecarga;
	}
	@Persistent
	@MemberOrder(sequence="80")
	@Column(allowsNull="false")
	public String getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence="90")
	@Column(allowsNull="false")
	public String getMantenimiento() {
		return mantenimiento;
	}
	public void setMantenimiento(String mantenimiento) {
		this.mantenimiento = mantenimiento;
	}
	@Persistent
	@Property(editing = Editing.DISABLED )
	@MemberOrder(sequence="100")
	@Column(allowsNull="false")
	public String getReparacion() {
		return reparacion;
	}
	public void setReparacion(String reparacion) {
		this.reparacion = reparacion;
	}
	@Override
	public String toString() {
		return "Matafuego " + nombre;
	}
	
	
	
}
