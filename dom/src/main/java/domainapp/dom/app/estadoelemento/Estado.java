package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;

import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.vehiculo.Vehiculo;

@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
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

	@Persistent
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull="true")
	@Property(editing=Editing.DISABLED)
	public Timestamp getFechaCambio() {
		return fechaCambio;
	}

	public void setFechaCambio(Timestamp fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	@Persistent
	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull="true")
	@Property(editing=Editing.DISABLED)
	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

}