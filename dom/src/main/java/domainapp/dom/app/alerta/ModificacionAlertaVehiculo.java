package domainapp.dom.app.alerta;

import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;

import domainapp.dom.app.empleado.Empleado;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "ModificacionAlertaVehiculo_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
			+ "FROM domainapp.dom.app.alerta.ModificacionAlertaVehiculo")})
	
@DomainObject(objectType = "MODIFICACIONALERTAVEHICULO")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class ModificacionAlertaVehiculo {
	private Empleado modificacionEmpleado;
	private Date fechaModificacion;
	private AlertaVehiculo alertaVehiculo;
	private String datosModificados;
	
	@Persistent
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull = "Empleado")
	public Empleado getModificacionEmpleado() {
		return modificacionEmpleado;
	}
	public void setModificacionEmpleado(Empleado modificacionEmpleado) {
		this.modificacionEmpleado = modificacionEmpleado;
	}
	@Persistent
	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	@Persistent
	@MemberOrder(sequence = "3")
	@javax.jdo.annotations.Column(allowsNull = "Alerta")
	public AlertaVehiculo getAlertaModificacion() {
		return alertaVehiculo;
	}
	public void setAlertaModificacion(AlertaVehiculo alertaVehiculo) {
		this.alertaVehiculo = alertaVehiculo;
	}
	@Override
	public String toString() {
		return "Empleado: "+ modificacionEmpleado + " ,vehiculo:  "+ alertaVehiculo;
	}
	@Persistent
	@MemberOrder(sequence = "4")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getDatosModificados() {
		return datosModificados;
	}
	public void setDatosModificados(String datosModificados) {
		this.datosModificados = datosModificados;
	}
	public ModificacionAlertaVehiculo(Empleado modificacionEmpleado,
			Date fechaModificacion, AlertaVehiculo alertaVehiculo,
			String datosModificados) {
		super();
		this.modificacionEmpleado = modificacionEmpleado;
		this.fechaModificacion = fechaModificacion;
		this.alertaVehiculo = alertaVehiculo;
		this.datosModificados = datosModificados;
	}
	public ModificacionAlertaVehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
