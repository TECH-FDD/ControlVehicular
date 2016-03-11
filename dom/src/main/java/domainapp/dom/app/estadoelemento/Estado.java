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

import domainapp.dom.app.mantenimiento.ObjetoMantenible;

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

	public abstract void desactivar(final ObjetoMantenible objeto, final Motivo motivo, final Timestamp fecha);

	/*********************************
	 * Reactivacion de los elementos.*
	 *********************************/

	public abstract void reactivar(final ObjetoMantenible objeto);

	/*******************************
	 * Asignacion de los elementos.*
	 *******************************/

	public abstract void asignar(final ObjetoMantenible objeto);

	/**********************************
	 * Desasignacion de los elementos.*
	 **********************************/

	public abstract void desasignar(final ObjetoMantenible objeto);

	/***************************************
	 * Operaciones privadas de los estados.*
	 ***************************************/

	/**
	 * Obtener nuevo estado, según el motivo ingresado.
	 * @param fecha
	 * @param motivo
	 * @return Nuevo estado.
	 */
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

	/**
	 * Actualizar el ObjetoMantenible con el nuevo estado y eliminar el estado anterior de la BD.
	 * @param objeto
	 * @param estado
	 */
	protected void actualizarElemento(final ObjetoMantenible objeto, final Estado estado) {
		//Obtengo el estado anterior del Elemento
		Estado old= objeto.getEstado();
		//Seteo el nuevo estado.
		objeto.setEstado(estado);
		container.persistIfNotAlready(objeto);
		container.removeIfNotAlready(old);
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}