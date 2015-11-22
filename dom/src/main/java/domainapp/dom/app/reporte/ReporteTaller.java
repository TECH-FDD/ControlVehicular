package domainapp.dom.app.reporte;

import org.apache.isis.applib.annotation.MemberOrder;

public class ReporteTaller {
	private String nombreComercial;
	private String descripcion;
	private String rubro;
	private String direccion;
	private String telefono;
	private String codigo;
	private String email;

	@MemberOrder(sequence = "1")
	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	@MemberOrder(sequence = "1")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@MemberOrder(sequence = "1")
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@MemberOrder(sequence = "1")
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@MemberOrder(sequence = "1")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@MemberOrder(sequence = "1")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@MemberOrder(sequence = "1")
	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

}
