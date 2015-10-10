package domainapp.dom.app.reporte;

import org.apache.isis.applib.annotation.MemberOrder;

public class ReporteAlerta {
	private String subTitulo;
	private String nombre;
	private String descripcion;
	private String estadoAlerta;
	private String empleadoInvolucrado;
	private String alerta;
	private String elemento;
	private String fechaAlta;

	@MemberOrder(sequence = "1")
	public String getSubTitulo() {
		return subTitulo;
	}

	public void setsubTitulo(final String subTitulo) {
		this.subTitulo = subTitulo;
	}

	@MemberOrder(sequence = "1")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}
	@MemberOrder(sequence = "1")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@MemberOrder(sequence = "1")
	public String getEstadoAlerta() {
		return estadoAlerta;
	}

	public void setEstadoAlerta(String estadoAlerta) {
		this.estadoAlerta = estadoAlerta;
	}
	@MemberOrder(sequence = "1")
	public String getEmpleadoInvolucrado() {
		return empleadoInvolucrado;
	}

	public void setEmpleadoInvolucrado(String empleadoInvolucrado) {
		this.empleadoInvolucrado = empleadoInvolucrado;
	}
	@MemberOrder(sequence = "1")
	public String getAlerta() {
		return alerta;
	}

	public void setAlerta(String alerta) {
		this.alerta = alerta;
	}
	@MemberOrder(sequence = "1")
	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}
	@MemberOrder(sequence = "1")
	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}
