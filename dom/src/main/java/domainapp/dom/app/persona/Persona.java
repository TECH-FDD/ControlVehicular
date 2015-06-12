package domainapp.dom.app.persona;

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
	private Documento tipoDocumento;
	private int nroDocumento;
	private Timestamp fechaNacimiento;
	private String domicilio;
	private Provincia provincia;
	private Ciudad ciudad;
	private int codigoPostal;
	private Timestamp fechaAlta;
	private Sexo sexo;
	private String telefono;
	private String email;

	public Persona(String nombre, String apellido, Documento tipoDocumento,
			int nroDocumento, Timestamp fechaNacimiento, String domicilio,
			Provincia provincia, Ciudad ciudad, int codigoPostal,
			Timestamp fechaAlta, Sexo sexo, String telefono, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.fechaAlta = fechaAlta;
		this.sexo = sexo;
		this.telefono = telefono;
		this.email = email;
	}

	public Persona() {
		super();
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
	public Documento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Documento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	@MemberOrder(sequence = "4")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	@MemberOrder(sequence = "8")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	@MemberOrder(sequence = "9")
	@javax.jdo.annotations.Column(allowsNull="false")
	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
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
				+ ", tipoDocumento=" + tipoDocumento + ", nroDocumento="
				+ nroDocumento + ", fechaNacimiento=" + fechaNacimiento
				+ ", domicilio=" + domicilio + ", provincia=" + provincia
				+ ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal
				+ ", fechaAlta=" + fechaAlta + ", sexo=" + sexo + ", telefono="
				+ telefono + ", email=" + email + "]";
	}
}
