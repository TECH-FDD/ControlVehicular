package domainapp.dom.app.area;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="Area_ID")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@javax.jdo.annotations.Queries({
    @javax.jdo.annotations.Query(
            name = "ListarTodos", language = "JDOQL",
            value = "SELECT "
                    + "FROM domainapp.dom.app.Area"),
@javax.jdo.annotations.Query(
        name = "buscarPorNombre", language = "JDOQL",
        value = "SELECT "
                + "FROM domainapp.dom.app.Area.Area "
                + "WHERE nombre.indexOf(:nombre) >= 0 "),
@javax.jdo.annotations.Query(
        name = "buscarPorCodigo", language = "JDOQL",
        value = "SELECT "
                + "FROM domainapp.dom.app.Area.Area "
                + "WHERE codigo_area.indexOf(:codigo_area) >= 0 ")
})
@DomainObject(
        objectType = "AREA", bounded=true
)

@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_CHILD
)
public class Area {

	private String codigo_area;
	private String nombre;
	private String descripcion;
	private Timestamp fecha_alta;
	
	public Area(String codigo_area, String nombre, String descripcion,
			Timestamp fecha_alta) {
		super();
		this.codigo_area = codigo_area;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_alta = fecha_alta;
	}
	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Persistent
	@MemberOrder(sequence = "10")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getCodigo_area() {
		return codigo_area;
	}
	public void setCodigo_area(String codigo_area) {
		this.codigo_area = codigo_area;
	}
	@Persistent
	@MemberOrder(sequence = "20")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Persistent
	@MemberOrder(sequence = "30")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Persistent
	@MemberOrder(sequence = "40")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public Timestamp getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	
	@Override
	public String toString() {
		return "Area " + nombre;
	} 
		
	
	
}
