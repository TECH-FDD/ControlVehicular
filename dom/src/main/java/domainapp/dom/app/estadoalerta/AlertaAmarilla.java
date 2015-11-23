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
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "AlertaAmarilla_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@DomainObject(objectType = "ALERTAAMARILLA")

@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class AlertaAmarilla extends EstadoAlerta {

	public AlertaAmarilla() {
		super();
	}

	public AlertaAmarilla(Timestamp fechaCambio) {
		super(fechaCambio);
	}

	@Override
	public String toString() {
		return "Alerta Amarilla";
	}

	@Override
	@Programmatic
	public void cambiarAlerta(AlertaMatafuego matafuego) {
		EstadoAlerta estado = new AlertaNaranja(new Timestamp(System.currentTimeMillis()));
		actualizarAlertaMatafuego(matafuego, estado);
	}

	@Override
	@Programmatic
	public void cambiarAlerta(AlertaVehiculo vehiculo) {
		EstadoAlerta estado = new AlertaNaranja(new Timestamp(System.currentTimeMillis()));
		actualizarAlertaVehiculo(vehiculo, estado);

	}

	@Override
	@Programmatic
	public void aplazarAlertas(AlertaMatafuego matafuego) {
		EstadoAlerta estado = new Aplazado(new Timestamp(System.currentTimeMillis()));
		actualizarAlertaMatafuego(matafuego, estado);
		container.informUser("Se aplazo Exitosamente.");

	}

	@Override
	@Programmatic
	public void aplazarAlertas(AlertaVehiculo vehiculo) {
		EstadoAlerta estado = new Aplazado(new Timestamp(System.currentTimeMillis()));
		actualizarAlertaVehiculo(vehiculo, estado);
		container.informUser("Se aplazo Exitosamente.");

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
