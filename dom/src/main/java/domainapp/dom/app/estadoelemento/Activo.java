package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;

import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.vehiculo.Vehiculo;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="Activo_ID")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

@DomainObject(
        objectType = "ACTIVO"
)

@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_CHILD
)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Activo extends Estado {

	public Activo(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@javax.inject.Inject
	DomainObjectContainer container;

	@Override
	public void desactivar(Object elemento, Timestamp fecha, Motivo motivo) {
		if (elemento instanceof Matafuego){
			matafuego=(Matafuego) elemento;
			switch (motivo) {
				case DESUSO:
					matafuego.setEstado(new Inactivo(fecha,motivo));
					container.informUser("El Matafuego ha pasado a estado Inactivo.");
					break;
				case INUTILIZBLE:
					matafuego.setEstado(new Baja(fecha,motivo));
					container.informUser("El Matafuego ha pasado a estado dado de Baja.");
					break;
				case RUPTURA:
					matafuego.setEstado(new NecesitaReparacion(fecha, motivo));
					container.informUser("El Matafuego ha pasado a estado Necesita Reparación.");
					break;
			}
		}
		if (elemento instanceof Vehiculo){
			vehiculo=(Vehiculo) elemento;
			switch (motivo) {
				case DESUSO:
					vehiculo.setEstado(new Inactivo(fecha,motivo));
					container.informUser("El Vehiculo ha pasado a estado Inactivo.");
					break;
				case INUTILIZBLE:
					vehiculo.setEstado(new Baja(fecha,motivo));
					container.informUser("El Vehiculo ha pasado a estado dado de Baja.");
					break;
				case RUPTURA:
					vehiculo.setEstado(new NecesitaReparacion(fecha, motivo));
					container.informUser("El Vehiculo ha pasado a estado Necesita Reparación.");
					break;
			}
		}
		if (elemento instanceof Gps){
			gps=(Gps) elemento;
			switch (motivo) {
				case DESUSO:
					gps.setEstado(new Inactivo(fecha,motivo));
					container.informUser("El Gps ha pasado a estado Inactivo.");
					break;
				case INUTILIZBLE:
					gps.setEstado(new Baja(fecha,motivo));
					container.informUser("El Gps ha pasado a estado dado de Baja.");
					break;
				case RUPTURA:
					gps.setEstado(new NecesitaReparacion(fecha, motivo));
					container.informUser("El Gps ha pasado a estado Necesita Reparación.");
					break;
			}
		}
	}

	@Override
	public String toString() {
		return "Activo";
	}
}