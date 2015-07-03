package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.vehiculo.Vehiculo;

public abstract class Estado implements IEstado {
	private Timestamp fechaCambio;
	private String motivo;
	protected Gps gps;
	protected Matafuego matafuego;
	protected Vehiculo vehiculo;
	
	public Estado(Timestamp fechaCambio, String motivo) {
		super();
		this.fechaCambio = fechaCambio;
		this.motivo = motivo;
	}

	public Timestamp getFechaCambio() {
		return fechaCambio;
	}

	public void setFechaCambio(Timestamp fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}