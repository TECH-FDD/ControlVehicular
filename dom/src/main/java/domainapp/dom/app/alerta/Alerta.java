package domainapp.dom.app.alerta;

import java.util.Date;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;

import domainapp.dom.app.empleado.Empleado;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Alerta {

	private String nombre;
	private String descripcion;
	private Date fechaAlta;
	private Empleado empleado;

	@Persistent
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Persistent
	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull = "true")
	@Property(editing = Editing.DISABLED)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Persistent
	@MemberOrder(sequence = "3")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Persistent
	@MemberOrder(sequence = "5")
	@javax.jdo.annotations.Column(allowsNull = "Empleado")
	@Property(editing = Editing.DISABLED)
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Alerta(String nombre, String descripcion, Date fechaAlta,
			Empleado empleado) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.empleado = empleado;
	}

	public Alerta() {
		super();
	}

	@Override
	public String toString() {
		return "Alerta [nombre=" + nombre + ", descripcion=" + descripcion
				+ ", fechaAlta=" + fechaAlta + ", empleado=" + empleado;
	}
}