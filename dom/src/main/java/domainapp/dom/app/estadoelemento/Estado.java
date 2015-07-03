package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.vehiculo.Vehiculo;

public abstract class Estado implements IEstado {
	private Timestamp fechaCambio;
	private Motivo motivo;
	protected Gps gps;
	protected Matafuego matafuego;
	protected Vehiculo vehiculo;
	
	public Estado(Timestamp fechaCambio, Motivo motivo) {
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

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

}