package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;
import java.util.List;

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
         column="Asignado_ID")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

@DomainObject(
        objectType = "ASIGNADO"
)

@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_CHILD
)
public class Asignado extends Estado {

	public Asignado() {
		super();
	}

	public Asignado(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@Override
	public String toString() {
		return "Asignado";
	}

	/**********************************
	 * Desactivacion de los elementos.*
	 **********************************/

	@Override
	@Programmatic
	public void desactivarGps(Gps gps, Motivo motivo, Timestamp fecha) {
		//Busco el vehiculo que tiene asignado este Gps.
		List<Vehiculo> lv = container.allInstances(Vehiculo.class);
		Vehiculo vehiculo = new Vehiculo();
		for (Vehiculo v : lv){
			if (gps.equals(v.getGps()))
				vehiculo = v;
		}

		//Desasigno el Gps y actualizo el vechiculo.
		desasignarGps(vehiculo);

		Estado e= nuevoEstadoInactivo(fecha, motivo);
		actualizarGps(gps, e);
		container.informUser("Se desactivo el Gps de manera exitosa.");
		container.warnUser("El Vehiculo " + vehiculo.toString() + " se encuentra sin Gps asignado.");
	}

	@Override
	@Programmatic
	public void desactivarMatafuego(Matafuego matafuego, Motivo motivo, Timestamp fecha) {
		// Busco el vehiculo que tiene asignado este Matafuego.
		List<Vehiculo> lv = container.allInstances(Vehiculo.class);
		Vehiculo vehiculo = new Vehiculo();
		for (Vehiculo v : lv) {
			if (matafuego.equals(v.getGps()))
				vehiculo = v;
		}

		// Desasigno el Matafuego y actualizo el vechiculo.
		desasignarMatafuego(vehiculo);

		Estado e = nuevoEstadoInactivo(fecha, motivo);
		actualizarMatafuego(matafuego, e);
		container.informUser("Se desactivo el Matafuego de manera exitosa.");
		container.warnUser("El Vehiculo " + vehiculo.toString() + " se encuentra sin Matafuego asignado.");
	}

	@Override
	@Programmatic
	public void desactivarVehiculo(Vehiculo vehiculo, Motivo motivo, Timestamp fecha) {
		// Busco el empleado que tiene asignado este Vechiculo.
		List<Empleado> lv = container.allInstances(Empleado.class);
		Empleado empleado = new Empleado();
		for (Empleado em : lv) {
			if (vehiculo.equals(em.getVehiculo()))
				empleado = em;
		}

		// Desasigno el Vehiculo y actualizo el empleado.
		desasignarVehiculo(empleado);

		Estado e = nuevoEstadoInactivo(fecha, motivo);
		actualizarVehiculo(vehiculo, e);
		container.informUser("Se desactivo el Vehiculo de manera exitosa.");
	}

	/**********************************
	 * Desasignacion de los elementos.*
	 **********************************/

	@Programmatic
	public void desasignarGps(Vehiculo vehiculo){
		//Actualizo el Gps.
		Gps gps=vehiculo.getGps();
		actualizarGps(gps, new Activo(gps.getEstado().getFechaCambio(),gps.getEstado().getMotivo()));

		//Guardo el vehiculo sin Gps Asignado.
		vehiculo.setGps(null);
		container.persistIfNotAlready(vehiculo);
	}

	@Programmatic
	public void desasignarMatafuego(Vehiculo vehiculo){
		//Actualizo el Matafuego.
		Matafuego matafuego=vehiculo.getMatafuego();
		actualizarMatafuego(matafuego, new Activo(matafuego.getEstado().getFechaCambio(),matafuego.getEstado().getMotivo()));

		//Guardo el vehiculo sin Matafuego Asignado.
		vehiculo.setGps(null);
		container.persistIfNotAlready(vehiculo);
	}

	@Programmatic
	public void desasignarVehiculo(Empleado empleado){
		Vehiculo vehiculo = empleado.getVehiculo();
		actualizarVehiculo(vehiculo, new Activo(vehiculo.getEstado().getFechaCambio(), vehiculo.getEstado().getMotivo()));

		empleado.setVehiculo(null);
		container.persistIfNotAlready(empleado);
	}

	/*********************************
	 * Reactivacion de los elementos.*
	 *********************************/

	@Override
	@Programmatic
	public void reactivarGps(Gps gps) {
		container.warnUser("El Gps seleccionado, se encuentra en estado Asignado y Activo.");
	}

	@Override
	@Programmatic
	public void reactivarMatafuego(Matafuego matafuego) {
		container.warnUser("El Matafuego seleccionado, se encuentra en estado Asignado y Activo.");
	}

	@Override
	@Programmatic
	public void reactivarVehiculo(Vehiculo vehiculo) {
		container.warnUser("El Vehiculo seleccionado, se encuentra en estado Asignado y Activo.");
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
