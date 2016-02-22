/*
 *  SIGAFV is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * SIGAFV is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SIGAFV.  If not, see <http://www.gnu.org/licenses/>.
 */
    
package domainapp.dom.app.persona;

import java.sql.Timestamp;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.joda.time.LocalDate;

@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Persona {
	private String nombre;
	private String apellido;
	private Documento tipoDocumento;
	private int nroDocumento;
	private LocalDate fechaNacimiento;
	private String domicilio;
	private Provincia provincia;
	private Ciudad ciudad;
	private int codigoPostal;
	private Timestamp fechaAlta;
	private Sexo sexo;
	private String telefono;
	private String email;

	public Persona(String nombre, String apellido, Documento tipoDocumento,
			int nroDocumento, LocalDate fechaNacimiento, String domicilio,
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
	@Property(editing=Editing.DISABLED)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Persistent
	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	@Persistent
	@MemberOrder(sequence = "3")
	@javax.jdo.annotations.Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
	public Documento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Documento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	@MemberOrder(sequence = "5")
	@javax.jdo.annotations.Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	@MemberOrder(sequence = "10")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	@MemberOrder(sequence = "8")
	@javax.jdo.annotations.Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@MemberOrder(sequence = "9")
	@javax.jdo.annotations.Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	@MemberOrder(sequence = "13")
	@javax.jdo.annotations.Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	@MemberOrder(sequence = "4")
	@javax.jdo.annotations.Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	@MemberOrder(sequence = "6")
	@javax.jdo.annotations.Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
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
	
	@MemberOrder(sequence = "7")
	@javax.jdo.annotations.Column(allowsNull="false")
	@Property(editing=Editing.DISABLED)
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
