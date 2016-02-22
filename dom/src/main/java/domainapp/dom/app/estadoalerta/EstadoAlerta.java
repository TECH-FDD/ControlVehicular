/*
 *  SIGAFV is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * SIGAFV is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SIGAFV.  If not, see <http://www.gnu.org/licenses/>.
 */
    
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

	/**********************************
	 * Finalizar Alertas*
	 **********************************/

	public abstract void finalizarAlertas(AlertaMatafuego matafuego);

	public abstract void finalizarAlertas(AlertaVehiculo vehiculo);

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
