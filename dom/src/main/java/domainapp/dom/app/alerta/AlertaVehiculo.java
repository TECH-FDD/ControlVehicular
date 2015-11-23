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
import domainapp.dom.app.vehiculo.Vehiculo;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "AlertaVehiculo_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.alerta.AlertaVehiculo") })
@DomainObject(objectType = "ALERTAVEHICULO")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class AlertaVehiculo extends Alerta {
	private Vehiculo vehiculo;
	private Integer kilometrosAlarma;

	@Persistent
	@MemberOrder(sequence = "6")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public Integer getKilometrosAlarma() {
		return kilometrosAlarma;
	}

	public void setKilometrosAlarma(Integer kilometrosAlarma) {
		this.kilometrosAlarma = kilometrosAlarma;
	}

	@Persistent
	@MemberOrder(sequence = "5")
	@javax.jdo.annotations.Column(allowsNull = "Vehiculo")
	@Property(editing = Editing.DISABLED)
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public String toString() {
		return getVehiculo().toString();
	}

	public AlertaVehiculo() {
		super();
	}

	public AlertaVehiculo(String nombre, String descripcion, Date fechaAlta, Empleado empleado, Vehiculo vehiculo,
			Integer kilometrosAlarma, EstadoAlerta estadoAlerta, String estadoAnterior) {
		super(nombre, descripcion, fechaAlta, empleado, estadoAlerta, estadoAnterior);
		this.vehiculo = vehiculo;
		this.kilometrosAlarma = kilometrosAlarma;
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Aplazar", position = Position.BELOW)
	public AlertaVehiculo aplazar() {
		getEstadoAlerta().aplazarAlertas(this);
		return this;
	}
	@MemberOrder(sequence = "1", name = "KilometrosAlarma")
	@ActionLayout(named = "Cambiar Kilometro Alarma", position = Position.BELOW)
	public AlertaVehiculo updateKilometrosAlarma(@ParameterLayout(named = "Kilometros Alerta")Integer kilometrosAlerta){
		this.setKilometrosAlarma(kilometrosAlerta);
		this.setEstadoAlerta(repo.asignarAlertaEstado(kilometrosAlerta));
		container.persistIfNotAlready(this);
		return this;
	}
	@ActionLayout(named = "Eliminar alerta")
	public AlertaVehiculo deleteAlertaVehiculo() {
		getEstadoAlerta().finalizarAlertas(this);
		return this;
	}
	@Programmatic
	public boolean hideDeleteAlertaVehiculo() {
		if (this.getEstadoAlerta() instanceof Finalizada)
			return true;
		else
			return false;
	}

	@javax.inject.Inject
	DomainObjectContainer container;

	@javax.inject.Inject
	RepositorioAlertaVehiculo repo;

}
