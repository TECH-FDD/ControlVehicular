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
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Activa_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@DomainObject(objectType = "ACTIVA")

@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Activa extends EstadoAlerta {

	public Activa() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Activo";
	}

	public Activa(Timestamp fechaCambio) {
		super(fechaCambio);
		// TODO Auto-generated constructor stub
	}

	@Override
	@Programmatic
	public void cambiarAlerta(AlertaMatafuego matafuego) {
		EstadoAlerta estado = new AlertaAmarilla(new Timestamp(System.currentTimeMillis()));
		actualizarAlertaMatafuego(matafuego, estado);

	}

	@Override
	@Programmatic
	public void cambiarAlerta(AlertaVehiculo vehiculo) {
		EstadoAlerta estado = new AlertaAmarilla(new Timestamp(System.currentTimeMillis()));
		actualizarAlertaVehiculo(vehiculo, estado);

	}

	@Override
	@Programmatic
	public void aplazarAlertas(AlertaMatafuego matafuego) {
		container.warnUser("El alerta no se puede aplazar, ya que esta esta Activa");
	}

	@Override
	@Programmatic
	public void aplazarAlertas(AlertaVehiculo vehiculo) {
		container.warnUser("El alerta no se puede aplazar, ya que esta esta Activa");
	}

}
