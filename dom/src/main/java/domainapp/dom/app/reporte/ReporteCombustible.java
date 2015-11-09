package domainapp.dom.app.reporte;

import org.apache.isis.applib.annotation.MemberOrder;

public class ReporteCombustible {
	private String nombre;
	private String empresa;
	private String codigo;
	private String descripcion;
	private String categoria;
	private String precioLitro;
	private String precioAnterior;
	private String porcentajeAumento;
	private String octanaje;
	private String tipoCombustible;
	private String activo;
	
	@MemberOrder(sequence = "1")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@MemberOrder(sequence = "1")
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	@MemberOrder(sequence = "1")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@MemberOrder(sequence = "1")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@MemberOrder(sequence = "1")
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@MemberOrder(sequence = "1")
	public String getPrecioLitro() {
		return precioLitro;
	}
	public void setPrecioLitro(String precioLitro) {
		this.precioLitro = precioLitro;
	}
	@MemberOrder(sequence = "1")
	public String getPrecioAnterior() {
		return precioAnterior;
	}
	public void setPrecioAnterior(String precioAnterior) {
		this.precioAnterior = precioAnterior;
	}
	@MemberOrder(sequence = "1")
	public String getPorcentajeAumento() {
		return porcentajeAumento;
	}
	public void setPorcentajeAumento(String porcentajeAumento) {
		this.porcentajeAumento = porcentajeAumento;
	}
	@MemberOrder(sequence = "1")
	public String getOctanaje() {
		return octanaje;
	}
	public void setOctanaje(String octanaje) {
		this.octanaje = octanaje;
	}
	@MemberOrder(sequence = "1")
	public String getTipoCombustible() {
		return tipoCombustible;
	}
	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}
	@MemberOrder(sequence = "1")
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	
}
