package domainapp.dom.app.alerta;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;

import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.vehiculo.Vehiculo;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "AlertaVehiculo_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
		+ "FROM domainapp.dom.app.alerta.AlertaVehiculo") })
@DomainObject(objectType = "ALERTAVEHICULO")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class AlertaVehiculo extends Alerta {
	private Vehiculo vehiculo;
	private int kilometrosAlarma;

	@Persistent
	@MemberOrder(sequence = "6")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public int getKilometrosAlarma() {
		return kilometrosAlarma;
	}

	public void setKilometrosAlarma(int kilometrosAlarma) {
		this.kilometrosAlarma = kilometrosAlarma;
	}

	@Persistent
	@MemberOrder(sequence = "5")
	@javax.jdo.annotations.Column(allowsNull = "Vehiculo")
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public String toString() {
		return "AlertaVehiculo" + getVehiculo().toString();
	}

	public AlertaVehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlertaVehiculo(String nombre, String descripcion,
			Timestamp fechaAlta, Empleado empleado, Vehiculo vehiculo,
			int kilometrosAlarma) {
		super(nombre, descripcion, fechaAlta, empleado);
		this.vehiculo = vehiculo;
		this.kilometrosAlarma = kilometrosAlarma;
	}

}
