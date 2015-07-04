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
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Baja extends Estado {

	public Baja(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@Override
	public void desactivar(Object elemento,Timestamp fecha, Motivo motivo) {
		this.container.warnUser("El elemento ha sido dado de baja, a causa de encontrarse en un estado inutilizable.");
	}

	@javax.inject.Inject
	DomainObjectContainer container;

}