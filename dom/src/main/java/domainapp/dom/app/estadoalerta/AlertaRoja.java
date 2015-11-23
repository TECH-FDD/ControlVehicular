package domainapp.dom.app.estadoalerta;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Programmatic;

import domainapp.dom.app.alerta.AlertaMatafuego;
import domainapp.dom.app.alerta.AlertaVehiculo;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "AlertaRoja_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@DomainObject(objectType = "ALERTAROJA")

@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class AlertaRoja extends EstadoAlerta {

	public AlertaRoja() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlertaRoja(Timestamp fechaCambio) {
		super(fechaCambio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Alerta Roja";
	}

	@Override

	@Programmatic
	public void cambiarAlerta(AlertaMatafuego matafuego) {
		container.warnUser("El alerta no se puede cambiar, ya que esta esta en Alerta Roja");

	}

	@Override
	@Programmatic
	public void cambiarAlerta(AlertaVehiculo vehiculo) {
		container.warnUser("El alerta no se puede cambiar, ya que esta esta en Alerta Roja");

	}

	@Override
	public void aplazarAlertas(AlertaMatafuego matafuego) {
		container.warnUser("El alerta no se puede aplazar, ya que esta esta en Alerta Roja");

	}

	@Override
	public void aplazarAlertas(AlertaVehiculo vehiculo) {
		container.warnUser("El alerta no se puede aplazar, ya que esta en Alerta Roja");

	}
	@Override
	public void finalizarAlertas(AlertaMatafuego matafuego) {
		EstadoAlerta estado = new Finalizada(new Timestamp(System.currentTimeMillis()));
		if(matafuego.getMantenimiento()==null)
			actualizarAlertaMatafuego(matafuego, estado);
		else
			container.warnUser("El Alerta no se puede eliminar, se encuentra asignado a un Mantenimiento");
	}

	@Override
	public void finalizarAlertas(AlertaVehiculo vehiculo) {
		EstadoAlerta estado = new Finalizada(new Timestamp(System.currentTimeMillis()));
		if(vehiculo.getMantenimiento()==null)
			actualizarAlertaVehiculo(vehiculo, estado);
		else
			container.warnUser("El Alerta no se puede eliminar, se encuentra asignado a un Mantenimiento");
	}

}
