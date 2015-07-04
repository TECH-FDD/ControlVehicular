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
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Inactivo extends Estado {

	public Inactivo(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@javax.inject.Inject
	DomainObjectContainer container;

	@Override
	public void desactivar(Object elemento, Timestamp fecha, Motivo motivo) {
		container.warnUser("El elemento ya se encuentra Inactivo.");
	}
}