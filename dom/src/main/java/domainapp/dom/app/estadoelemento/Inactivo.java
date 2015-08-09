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
         column="Inactivo_ID")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

@DomainObject(
        objectType = "INACTIVO"
)

@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_CHILD
)
public class Inactivo extends Estado {

	public Inactivo(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	public Inactivo() {
		super();
	}

	@Override
	public String toString() {
		return "Inactivo";
	}

	/**********************************
	 * Desactivacion de los elementos.*
	 **********************************/

	@Override
	@Programmatic
	public void desactivarGps(Gps gps, Motivo motivo, Timestamp fecha){
		container.warnUser("El Gps seleccionado, ya se encuentra en estado Inactivo.");
	}

	@Override
	@Programmatic
	public void desactivarMatafuego(Matafuego matafuego, Motivo motivo, Timestamp fecha){
		container.warnUser("El Matafuego seleccionado, ya se encuentra en estado Inactivo.");
	}

	@Override
	@Programmatic
	public void desactivarVehiculo(Vehiculo vehiculo, Motivo motivo, Timestamp fecha){
		container.warnUser("El Vehiculo seleccionado, ya se encuentra en estado Inactivo.");
	}

	/*********************************
	 * Reactivacion de los elementos.*
	 *********************************/

	@Override
	@Programmatic
	public void reactivarGps(Gps gps) {
		Estado estado = new Activo(new Timestamp(System.currentTimeMillis()), Motivo.REACTIVADO);
		actualizarGps(gps, estado);
		container.informUser("El Gps seleccionado, ha sido reacivado exitosamente.");
	}

	@Override
	@Programmatic
	public void reactivarMatafuego(Matafuego matafuego) {
		Estado estado = new Activo(new Timestamp(System.currentTimeMillis()), Motivo.REACTIVADO);
		actualizarMatafuego(matafuego, estado);
		container.informUser("El Matafuego seleccionado, ha sido reacivado exitosamente.");
	}

	@Override
	@Programmatic
	public void reactivarVehiculo(Vehiculo vehiculo) {
		Estado estado = new Activo(new Timestamp(System.currentTimeMillis()), Motivo.REACTIVADO);
		actualizarVehiculo(vehiculo, estado);
		container.informUser("El Vehiculo seleccionado, ha sido reacivado exitosamente.");
	}

	/*******************************
	 * Asignacion de los elementos.*
	 *******************************/

	@Override
	@Programmatic
	public void asignarGps(Gps gps) {
		container.warnUser("Para ser asignado, previamente, el Gps debe ser Reactivado");
	}

	@Override
	@Programmatic
	public void asignarMatafuego(Matafuego matafuego) {
		container.warnUser("Para ser asignado, previamente, el Matafuego debe ser Reactivado");
	}

	@Override
	@Programmatic
	public void asignarVehiculo(Vehiculo vehiculo) {
		container.warnUser("Para ser asignado, previamente, el Vehiculo debe ser Reactivado");
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