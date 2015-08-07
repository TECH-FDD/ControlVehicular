package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;

import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.vehiculo.Vehiculo;

@PersistenceCapable
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME)
public abstract class Estado {

	private Timestamp fechaCambio;
	private Motivo motivo;

	public Estado(Timestamp fechaCambio, Motivo motivo) {
		super();
		this.fechaCambio = fechaCambio;
		this.motivo = motivo;
	}

	public Estado() {
		super();
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

	/**********************************
	 * Desactivacion de los elementos.*
	 **********************************/

	public abstract void desactivarGps(Gps gps, Motivo motivo, Timestamp fecha);

	public abstract void desactivarMatafuego(Matafuego matafuego, Motivo motivo, Timestamp fecha);

	public abstract void desactivarVehiculo(Vehiculo vehiculo, Motivo motivo, Timestamp fecha);

	/*********************************
	 * Reactivacion de los elementos.*
	 *********************************/

	public abstract void reactivarGps(Gps gps);

	public abstract void reactivarMatafuego(Matafuego matafuego);

	public abstract void reactivarVehiculo(Vehiculo vehiculo);

	/*******************************
	 * Asignacion de los elementos.*
	 *******************************/

	public abstract void asignarGps(Gps gps);

	public abstract void asignarMatafuego(Matafuego matafuego);

	public abstract void asignarVehiculo(Vehiculo vehiculo);

	@SuppressWarnings("incomplete-switch")
	protected Estado nuevoEstadoInactivo(Timestamp fecha, Motivo motivo){
		Estado e= null;
		switch (motivo) {
		case DESUSO:
			e = new Inactivo(fecha, motivo);
			return e;
		case ROTURA:
			e = new NecesitaReparacion(fecha, motivo);
			return e;
		case INUTILIZBLE:
			e = new Baja(fecha, motivo);
			return e;
		}
		return e;
	}

	protected void actualizarGps(Gps gps, Estado estado){
		//Obtengo el estado anterior del Gps
		Estado old= gps.getEstado();
		//Seteo el nuevo estado.
		gps.setEstado(estado);
		container.persistIfNotAlready(gps);
		container.removeIfNotAlready(old);
	}

	protected void actualizarMatafuego(Matafuego matafuego, Estado estado){
		//Obtengo el estado anterior del Matafuego
		Estado old= matafuego.getEstado();
		//Seteo el nuevo estado.
		matafuego.setEstado(estado);
		container.persistIfNotAlready(matafuego);
		container.removeIfNotAlready(old);
	}

	protected void actualizarVehiculo(Vehiculo vehiculo, Estado estado){
		//Obtengo el estado anterior del Vehiculo
		Estado old= vehiculo.getEstado();
		//Seteo el nuevo estado.
		vehiculo.setEstado(estado);
		container.persistIfNotAlready(vehiculo);
		container.removeIfNotAlready(old);
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}