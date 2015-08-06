package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;

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
	public void desactivarGps(Gps gps, Motivo motivo, Timestamp fecha) {

	}

	@Override
	public void desactivarMatafuego(Matafuego matafuego, Motivo motivo, Timestamp fecha) {

	}

	@Override
	public void desactivarVehiculo(Vehiculo vehiculo, Motivo motivo, Timestamp fecha) {

	}
}
