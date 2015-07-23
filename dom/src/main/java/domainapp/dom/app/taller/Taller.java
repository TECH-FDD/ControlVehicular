package domainapp.dom.app.taller;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Taller_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@DomainObject(objectType = "TALLER")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Taller {
	private String nombreComercial;
	private String descripcion;
	private String direccion;
	private String telefono;
	private String codigo;
	private String email;
	

	public Taller(String nombreComercial, String descripcion, String direccion,
			String telefono, String codigo, String email) {
		super();
		this.nombreComercial = nombreComercial;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo = codigo;
		this.email = email;
	}
	
	
	public Taller() {
		super();
	}




	@Persistent
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	
	@Persistent
	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull = "true")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Persistent
	@MemberOrder(sequence = "3")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Persistent
	@MemberOrder(sequence = "4")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Persistent
	@MemberOrder(sequence = "5")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Persistent
	@MemberOrder(sequence = "6")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "nombreComercial:" + nombreComercial ;
	}
	
}
