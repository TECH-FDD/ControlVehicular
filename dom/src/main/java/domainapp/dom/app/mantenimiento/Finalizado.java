package domainapp.dom.app.mantenimiento;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Finalizado_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DomainObject(objectType = "FINALIZADO")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Finalizado extends EstadoMantenimiento{

	public Finalizado(Timestamp fechaCambio, String motivo) {
		super(fechaCambio, motivo);
	}
	
	public Finalizado() {
		super();
	}
	@Override
	public void iniciarProceso(Mantenimiento m,Timestamp fechaCambio, String motivo) {
	}

	@Override
	public void aceptacion(Mantenimiento m,Timestamp fechaCambio, String motivo) {
	}

	@Override
	public void finalizarMantenimiento(Mantenimiento m,Timestamp fechaCambio, String motivo) {
	}

	@Override
	public void cancelarMantenimiento(Mantenimiento m,Timestamp fechaCambio, String motivo) {
	}
	
	@Override
	public String toString(){
		return "Finalizado";
	}

}
