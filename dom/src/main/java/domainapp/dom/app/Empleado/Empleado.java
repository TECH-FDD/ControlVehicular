package domainapp.dom.app.Empleado;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;

import domainapp.dom.app.Pesona.Documento;
import domainapp.dom.app.Pesona.Persona;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="Empleado_ID")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@javax.jdo.annotations.Queries({
    @javax.jdo.annotations.Query(
            name = "ListarTodos", language = "JDOQL",
            value = "SELECT "
                    + "FROM domainapp.dom.app.Empleado")
})

@DomainObject(
        objectType = "EMPLEADO"
)

@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_CHILD
)
public class Empleado extends Persona{
	private String legajo;

	

	public Empleado(String nombre, String apellido, Documento doc, int nro_doc,
			Timestamp fecha_nacimiento, String domicilio, String ciudad,
			int codigo_postal, Timestamp fecha_alta, String legajo) {
		super(nombre, apellido, doc, nro_doc, fecha_nacimiento, domicilio,
				ciudad, codigo_postal, fecha_alta);
		this.legajo = legajo;
	}
	public Empleado() {
		super();
	}
	@Persistent
	@MemberOrder(sequence = "10")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	@Override
	public String toString() {
		return "Empleado [legajo=" + legajo + "]";
	}
	
	
}
