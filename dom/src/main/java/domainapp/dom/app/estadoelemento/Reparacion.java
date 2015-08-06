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
public class Reparacion extends Estado {

	public Reparacion() {
		super();
	}

	public Reparacion(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@Override
	public String toString() {
		return "En Reparación";
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
		container.warnUser("El Vehiculo seleccionado, ya se encuentra en estado Inactivo.");
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}