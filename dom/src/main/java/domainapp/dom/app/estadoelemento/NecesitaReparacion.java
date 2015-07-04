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
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class NecesitaReparacion extends Estado {

	public NecesitaReparacion(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desactivar(Object elemento,Timestamp fecha, Motivo motivo) {
		container.warnUser("El elemento necesita ser reparado para poder ser Reactivado.");
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}