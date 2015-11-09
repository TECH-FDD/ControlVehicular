package domainapp.dom.app.reporte;

import org.apache.isis.applib.annotation.MemberOrder;

public class ReporteArea {
	private String codigoArea;
	private String nombre;
	private String descripcion;
	private String fechaAlta;
	private String activo;
	
	@MemberOrder(sequence="1")
	public String getCodigoArea() {
		return codigoArea;
	}
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}
	@MemberOrder(sequence="1")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@MemberOrder(sequence="1")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@MemberOrder(sequence="1")
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	@MemberOrder(sequence="1")
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	
}
