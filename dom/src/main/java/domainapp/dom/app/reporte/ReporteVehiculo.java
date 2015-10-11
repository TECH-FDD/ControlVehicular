package domainapp.dom.app.reporte;

import org.apache.isis.applib.annotation.MemberOrder;

public class ReporteVehiculo {
	private String nombre;
	private String modelo;
	private String fechaCompra;
	private String patente;
	private String numeroChasis;
	private String polizaSeguro;
	private String gps;
	private String TipoCombustible;
	private String matafuego;
	private String capacTanqueCombustible;
	private String tipoAceite;
	private String cnsCombustibleRuta;
	private String cnsCombustibleCiudad;
	private String kilometros;
	private String estado;

	@MemberOrder(sequence = "1")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@MemberOrder(sequence = "1")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@MemberOrder(sequence = "1")
	public String getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	@MemberOrder(sequence = "1")
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	@MemberOrder(sequence = "1")
	public String getNumeroChasis() {
		return numeroChasis;
	}

	public void setNumeroChasis(String numeroChasis) {
		this.numeroChasis = numeroChasis;
	}

	@MemberOrder(sequence = "1")
	public String getPolizaSeguro() {
		return polizaSeguro;
	}

	public void setPolizaSeguro(String polizaSeguro) {
		this.polizaSeguro = polizaSeguro;
	}

	@MemberOrder(sequence = "1")
	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	@MemberOrder(sequence = "1")
	public String getTipoCombustible() {
		return TipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		TipoCombustible = tipoCombustible;
	}

	@MemberOrder(sequence = "1")
	public String getMatafuego() {
		return matafuego;
	}

	public void setMatafuego(String matafuego) {
		this.matafuego = matafuego;
	}

	@MemberOrder(sequence = "1")
	public String getCapacTanqueCombustible() {
		return capacTanqueCombustible;
	}

	public void setCapacTanqueCombustible(String capacTanqueCombustible) {
		this.capacTanqueCombustible = capacTanqueCombustible;
	}

	@MemberOrder(sequence = "1")
	public String getTipoAceite() {
		return tipoAceite;
	}

	public void setTipoAceite(String tipoAceite) {
		this.tipoAceite = tipoAceite;
	}

	@MemberOrder(sequence = "1")
	public String getCnsCombustibleRuta() {
		return cnsCombustibleRuta;
	}

	public void setCnsCombustibleRuta(String cnsCombustibleRuta) {
		this.cnsCombustibleRuta = cnsCombustibleRuta;
	}

	@MemberOrder(sequence = "1")
	public String getCnsCombustibleCiudad() {
		return cnsCombustibleCiudad;
	}

	public void setCnsCombustibleCiudad(String cnsCombustibleCiudad) {
		this.cnsCombustibleCiudad = cnsCombustibleCiudad;
	}

	@MemberOrder(sequence = "1")
	public String getKilometros() {
		return kilometros;
	}

	public void setKilometros(String kilometros) {
		this.kilometros = kilometros;
	}

	@MemberOrder(sequence = "1")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
