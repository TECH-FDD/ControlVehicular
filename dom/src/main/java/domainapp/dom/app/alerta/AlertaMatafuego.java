package domainapp.dom.app.alerta;

import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.ActionLayout.Position;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Property;

import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.estadoalerta.EstadoAlerta;
import domainapp.dom.app.estadoalerta.Finalizada;
import domainapp.dom.app.mantenimiento.Mantenimiento;
import domainapp.dom.app.matafuego.Matafuego;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "AlertaMatafuego_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.alerta.AlertaMatafuego"),
		@javax.jdo.annotations.Query(name = "Buscar_matafuego", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.alerta.AlertaMatafuego " + "WHERE matafuego.indexOf(:matafuego)>= 0") })

@DomainObject(objectType = "Alerta Matafuego")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class AlertaMatafuego extends Alerta {

	private Matafuego matafuego;
	private Date fechaAlerta;

	@Persistent
	@MemberOrder(sequence = "21")
	@javax.jdo.annotations.Column(allowsNull = "Matafuego")
	@Property(editing = Editing.DISABLED)
	public Matafuego getMatafuego() {
		return matafuego;
	}

	public void setMatafuego(Matafuego matafuego) {
		this.matafuego = matafuego;
	}

	@Persistent
	@MemberOrder(sequence = "22")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public Date getFechaAlerta() {
		return fechaAlerta;
	}

	public void setFechaAlerta(Date fechaAlerta) {
		this.fechaAlerta = fechaAlerta;
	}

	@Override
	public String toString() {
		return "AlertaMatafuego: " + getMatafuego().toString();
	}

	public AlertaMatafuego() {
		super();
	}

	public AlertaMatafuego(String nombre, String descripcion, Date fechaAlta, Empleado empleado, Matafuego matafuego,
			Date fechaAlerta, EstadoAlerta estado, String estadoAnterior) {
		super(nombre, descripcion, fechaAlta, empleado, estado, estadoAnterior);
		this.matafuego = matafuego;
		this.fechaAlerta = fechaAlerta;
	}
	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Aplazar", position = Position.BELOW)
	public AlertaMatafuego Aplazar() {
		getEstadoAlerta().aplazarAlertas(this);
		return this;
	}
	@MemberOrder(sequence = "1", name = "FechaAlerta")
	@ActionLayout(named = "Cambiar Fecha Alerta", position = Position.BELOW)
	public AlertaMatafuego updateFechaAlerta(final @ParameterLayout(named = "Fecha Alerta")Date fechaAlerta){
		this.setFechaAlerta(fechaAlerta);
		this.setEstadoAlerta(repo.asignarAlertaEstado(fechaAlerta));
		return this;
	}
	@ActionLayout(named = "Eliminar alerta")
	public AlertaMatafuego deleteAlertaMatafuego() {
		getEstadoAlerta().finalizarAlertas(this);
		return this;
	}
	@Programmatic
	public boolean hideDeleteAlertaMatafuego() {
		if (this.getEstadoAlerta()instanceof Finalizada)
			return true;
		else
			return false;
	}
	@javax.inject.Inject
	DomainObjectContainer container;

	@javax.inject.Inject
	RepositorioAlertaMatafuego repo;
}
