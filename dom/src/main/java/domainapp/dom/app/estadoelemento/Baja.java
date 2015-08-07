package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Programmatic;

import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.vehiculo.Vehiculo;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="Baja_ID")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

@DomainObject(
        objectType = "BAJA"
)

@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_CHILD
)
public class Baja extends Estado {

	public Baja() {
		super();
	}

	public Baja(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@Override
	public String toString() {
		return "Baja";
	}

	/**********************************
	 * Desactivacion de los elementos.*
	 **********************************/

	@Override
	@Programmatic
	public void desactivarGps(Gps gps, Motivo motivo, Timestamp fecha) {
		container.warnUser("El Gps seleccionado, se encuentra en estado de Baja,"
				+ " por lo tanto no puede ser desactivado");
	}

	@Override
	@Programmatic
	public void desactivarMatafuego(Matafuego matafuego, Motivo motivo, Timestamp fecha) {
		container.warnUser("El Matafuego seleccionado, se encuentra en estado de Baja,"
				+ " por lo tanto no puede ser desactivado");
	}

	@Override
	@Programmatic
	public void desactivarVehiculo(Vehiculo ehiculo, Motivo motivo, Timestamp fecha) {
		container.warnUser("El Vehiculo seleccionado, se encuentra en estado de Baja,"
				+ " por lo tanto no puede ser desactivado");
	}

	/*********************************
	 * Reactivacion de los elementos.*
	 *********************************/

	@Override
	@Programmatic
	public void reactivarGps(Gps gps) {
		container.warnUser("El Gps seleccionado, ha sido dado de Baja, "
				+ "por lo que no puede ser reactivado.");
	}

	@Override
	@Programmatic
	public void reactivarMatafuego(Matafuego matafuego) {
		container.warnUser("El Matafuego seleccionado, ha sido dado de Baja, "
				+ "por lo que no puede ser reactivado.");
	}

	@Override
	@Programmatic
	public void reactivarVehiculo(Vehiculo vehiculo) {
		container.warnUser("El Vehiculo seleccionado, ha sido dado de Baja, "
				+ "por lo que no puede ser reactivado.");
	}

	/*******************************
	 * Asignacion de los elementos.*
	 *******************************/

	@Override
	@Programmatic
	public void asignarGps(Gps gps) {
		container.warnUser("El Gps fue dado de Baja, por lo que no puede ser asignado.");
	}

	@Override
	@Programmatic
	public void asignarMatafuego(Matafuego matafuego) {
		container.warnUser("El Matafuego fue dado de Baja, por lo que no puede ser asignado.");
	}

	@Override
	@Programmatic
	public void asignarVehiculo(Vehiculo vehiculo) {
		container.warnUser("El Vehiculo fue dado de Baja, por lo que no puede ser asignado.");
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
