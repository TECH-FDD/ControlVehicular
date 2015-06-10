package domainapp.dom.app.Pesona;

import java.sql.Timestamp;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.MemberOrder;

@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Persona {
	private String nombre;
	private String apellido;
	private Documento tipo_documento;
	private int nro_documento;
	private Timestamp fecha_nacimiento;
	private String domicilio;
	private Provincia provincia;
	private Ciudad ciudad;
	private int codigo_postal;
	private Timestamp fecha_alta;
	private Sexo sexo;
	private String telefono;
	private String email;

	public Persona(String nombre, String apellido, Documento tipo_documento,
			int nro_documento, Timestamp fecha_nacimiento, String domicilio,
			Provincia provincia, Ciudad ciudad, int codigo_postal,
			Timestamp fecha_alta, Sexo sexo, String telefono, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo_documento = tipo_documento;
		this.nro_documento = nro_documento;
		this.fecha_nacimiento = fecha_nacimiento;
		this.domicilio = domicilio;
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.codigo_postal = codigo_postal;
		this.fecha_alta = fecha_alta;
		this.sexo = sexo;
		this.telefono = telefono;
		this.email = email;
	}
	
	@Persistent
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Persistent
	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	@MemberOrder(sequence = "3")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Documento getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(Documento doc) {
		this.tipo_documento = doc;
	}
	@MemberOrder(sequence = "4")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Timestamp getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Timestamp fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	@MemberOrder(sequence = "5")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	@MemberOrder(sequence = "6")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	@MemberOrder(sequence = "7")
	@javax.jdo.annotations.Column(allowsNull="false")
	public int getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(int codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	@MemberOrder(sequence = "8")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Timestamp getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	@MemberOrder(sequence = "9")
	@javax.jdo.annotations.Column(allowsNull="false")
	public int getNro_documento() {
		return nro_documento;
	}

	public void setNro_documento(int nro_doc) {
		this.nro_documento = nro_doc;
	}

	@MemberOrder(sequence = "10")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	@MemberOrder(sequence = "11")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@MemberOrder(sequence = "12")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@MemberOrder(sequence = "13")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido
				+ ", doc=" + tipo_documento + ", fecha_nacimiento=" + fecha_nacimiento
				+ ", domicilio=" + domicilio + ", ciudad=" + ciudad
				+ ", codigo_postal=" + codigo_postal + ", fecha_alta="
				+ fecha_alta + "]";
	}
}
