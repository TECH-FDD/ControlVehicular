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
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "ModificacionAlertaMatafuego_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
			+ "FROM domainapp.dom.app.alerta.ModificacionAlertaMatafuego")})
	
@DomainObject(objectType = "MODIFICACIONALERTAMATAFUEGO")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class ModificacionAlertaMatafuego  {
	private Empleado modificacionEmpleado;
	private Date fechaModificacion;
	private AlertaMatafuego alertaMatafuego;
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
	public AlertaMatafuego getAlertaModificacion() {
		return alertaMatafuego;
	}
	public void setAlertaModificacion(AlertaMatafuego alertaMatafuego) {
		this.alertaMatafuego = alertaMatafuego;
	}
	@Override
	public String toString() {
		return modificacionEmpleado + " ,"+ alertaMatafuego.getMatafuego() ;
	}
	public ModificacionAlertaMatafuego(Empleado modificacionEmpleado,
			Date fechaModificacion, AlertaMatafuego alerta,String datosModificados) {
		super();
		this.modificacionEmpleado = modificacionEmpleado;
		this.fechaModificacion = fechaModificacion;
		this.alertaMatafuego = alerta;
		this.datosModificados=datosModificados;
	}
	public ModificacionAlertaMatafuego() {
		super();
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
	
	
	
}
