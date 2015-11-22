package domainapp.dom.app.estadoalerta;

import java.sql.Timestamp;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;

import domainapp.dom.app.alerta.AlertaMatafuego;
import domainapp.dom.app.alerta.AlertaVehiculo;

@PersistenceCapable
@Discriminator(strategy = DiscriminatorStrategy.CLASS_NAME)
public abstract class EstadoAlerta {
	private Timestamp fechaCambio;

	public EstadoAlerta(Timestamp fechaCambio) {
		super();
		this.fechaCambio = fechaCambio;
	}

	public EstadoAlerta() {
		super();
	}

	@Persistent
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull = "true")
	@Property(editing = Editing.DISABLED)
	public Timestamp getFechaCambio() {
		return fechaCambio;
	}

	public void setFechaCambio(Timestamp fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	@Override
	public String toString() {
		return "";
	}

	/*********************************
	 * Cambiar alerta.*
	 *********************************/
	public abstract void cambiarAlerta(AlertaMatafuego matafuego);

	public abstract void cambiarAlerta(AlertaVehiculo vehiculo);

	/**********************************
	 * Postergar Alertas*
	 **********************************/

	public abstract void aplazarAlertas(AlertaMatafuego matafuego);

	public abstract void aplazarAlertas(AlertaVehiculo vehiculo);

	/**
	 * Actualizar la alerta matafuergo con el nuevo estado y eliminar el estado
	 * anterior de la BD.
	 * 
	 * @param alertaMatafuego
	 * @param estadoAlerta
	 */
	protected void actualizarAlertaMatafuego(AlertaMatafuego alerta, EstadoAlerta estado) {
		// Obtengo el estado anterior del Gps
		EstadoAlerta old = alerta.getEstadoAlerta();
		// Seteo el nuevo estado.
		alerta.setEstadoAnterior(old.toString());
		alerta.setEstadoAlerta(estado);
		container.persistIfNotAlready(alerta);
		container.removeIfNotAlready(old);
	}

	/**
	 * Actualizar la alerta vehiculo con el nuevo estado y eliminar el estado
	 * anterior de la BD.
	 * 
	 * @param alertaVehiculo
	 * @param estadoAlerta
	 */
	protected void actualizarAlertaVehiculo(AlertaVehiculo alerta, EstadoAlerta estado) {
		// Obtengo el estado anterior del Gps
		EstadoAlerta old = alerta.getEstadoAlerta();
		// Seteo el nuevo estado.
		alerta.setEstadoAlerta(estado);
		alerta.setEstadoAnterior(old.toString());
		container.persistIfNotAlready(alerta);
		container.removeIfNotAlready(old);

	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
