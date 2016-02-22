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
    
package domainapp.dom.app.mantenimiento;

import java.sql.Timestamp;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;

@PersistenceCapable
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME)
public abstract class EstadoMantenimiento {

	private Timestamp fechaCambio;
	private String motivo;

	public EstadoMantenimiento(Timestamp fechaCambio, String motivo) {
		super();
		this.fechaCambio = fechaCambio;
		this.motivo = motivo;
	}

	public EstadoMantenimiento() {
		super();
	}
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Timestamp getFechaCambio() {
		return fechaCambio;
	}
	public void setFechaCambio(Timestamp fechaCambio) {
		this.fechaCambio = fechaCambio;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
	public abstract void iniciarProceso(Mantenimiento m,Timestamp fechaCambio,String motivo);
	public abstract void aceptacion(Mantenimiento m,Timestamp fechaCambio,String motivo);
	public abstract void finalizarMantenimiento(Mantenimiento m,Timestamp fechaCambio,String motivo);
	public abstract void cancelarMantenimiento(Mantenimiento m,Timestamp fechaCambio,String motivo);
	

	
}
