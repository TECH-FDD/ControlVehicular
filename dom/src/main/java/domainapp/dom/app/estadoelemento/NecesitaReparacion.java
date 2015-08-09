package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Programmatic;

import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.vehiculo.Vehiculo;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="NecesitaReparacion_ID")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

@DomainObject(
        objectType = "NECESITAREPARACION"
)

@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_CHILD
)
public class NecesitaReparacion extends Estado {

	public NecesitaReparacion() {
		super();
	}

	public NecesitaReparacion(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@Override
	public String toString() {
		return "Necesita Reparacion";
	}

	/**********************************
	 * Desactivacion de los elementos.*
	 **********************************/

	@Override
	@Programmatic
	public void desactivarGps(Gps gps, Motivo motivo, Timestamp fecha) {
		container.warnUser("El Gps seleccionado, ya se encuentra en estado Inactivo.");
	}

	@Override
	@Programmatic
	public void desactivarMatafuego(Matafuego matafuego, Motivo motivo, Timestamp fecha) {
		container.warnUser("El Matafuego seleccionado, ya se encuentra en estado Inactivo.");
	}

	@Override
	@Programmatic
	public void desactivarVehiculo(Vehiculo vehiculo, Motivo motivo, Timestamp fecha) {
		container.warnUser("El Matafuego seleccionado, ya se encuentra en estado Inactivo.");
	}

	/*********************************
	 * Reactivacion de los elementos.*
	 *********************************/

	@Override
	@Programmatic
	public void reactivarGps(Gps gps) {
		container.warnUser("Para ser reactivado, se necesita confirmación de que el Gps fue reparado.");
	}

	@Override
	@Programmatic
	public void reactivarMatafuego(Matafuego matafuego) {
		container.warnUser("Para ser reactivado, se necesita confirmación de que el Matafuego fue reparado.");
	}

	@Override
	@Programmatic
	public void reactivarVehiculo(Vehiculo vehiculo) {
		container.warnUser("Para ser reactivado, se necesita confirmación de que el Vehiculo fue reparado.");
	}

	/*******************************
	 * Asignacion de los elementos.*
	 *******************************/

	@Override
	@Programmatic
	public void asignarGps(Gps gps) {
		container.warnUser("El Gps no puede ser asignado al vehiculo, porque necesita reparación.");
	}

	@Override
	@Programmatic
	public void asignarMatafuego(Matafuego matafuego) {
		container.warnUser("El Matafuego no puede ser asignado al vehiculo, porque necesita reparación.");
	}

	@Override
	@Programmatic
	public void asignarVehiculo(Vehiculo vehiculo) {
		container.warnUser("El Vehiculo no puede ser asignado, porque necesita reparación.");
	}

	/**********************************
	 * Desasignacion de los elementos.*
	 **********************************/

	@Override
	public void desasignarGps(Vehiculo vehiculo) {
		container.warnUser("El Gps, debe encontrarse en estado Asignado para poder ser Desasignado.");
	}

	@Override
	public void desasignarMatafuego(Vehiculo vehiculo) {
		container.warnUser("El Matafuego, debe encontrarse en estado Asignado para poder ser Desasignado.");
	}

	@Override
	public void desasignarVehiculo(Empleado empleado) {
		container.warnUser("El Vehiculo, debe encontrarse en estado Asignado para poder ser Desasignado.");
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}