package domainapp.dom.app.reporte;

import org.apache.isis.applib.annotation.MemberOrder;

public class ReporteCargaCombustible {
	private String vehiculo;
	private String fechaCarga;
	private String combustible;
	private String costoTotal;
	private String litrosCargados;
	
	@MemberOrder(sequence="1")
	public String getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}
	@MemberOrder(sequence="1")
	public String getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	@MemberOrder(sequence="1")
	public String getCombustible() {
		return combustible;
	}
	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}
	@MemberOrder(sequence="1")
	public String getCostoTotal() {
		return costoTotal;
	}
	public void setCostoTotal(String costoTotal) {
		this.costoTotal = costoTotal;
	}
	@MemberOrder(sequence="1")
	public String getLitrosCargados() {
		return litrosCargados;
	}
	public void setLitrosCargados(String litrosCargados) {
		this.litrosCargados = litrosCargados;
	}
}
