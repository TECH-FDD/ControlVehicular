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
    
package domainapp.dom.app.reporte;

import org.apache.isis.applib.annotation.MemberOrder;

public class ReporteGps {
	private String codIdentificacion;
	private String marca;
	private String modelo;
	private String descripcion;
	private String fechaAlta;
	private String fechaAsigVehiculo;
	private String obsEstadoDispositivo;
	private String estado;
	
	@MemberOrder(sequence = "1")
	public String getCodIdentificacion() {
		return codIdentificacion;
	}
	public void setCodIdentificacion(String codIdentificacion) {
		this.codIdentificacion = codIdentificacion;
	}
	@MemberOrder(sequence = "1")
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	@MemberOrder(sequence = "1")
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	@MemberOrder(sequence = "1")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@MemberOrder(sequence = "1")
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	@MemberOrder(sequence = "1")
	public String getFechaAsigVehiculo() {
		return fechaAsigVehiculo;
	}
	public void setFechaAsigVehiculo(String fechaAsigVehiculo) {
		this.fechaAsigVehiculo = fechaAsigVehiculo;
	}
	@MemberOrder(sequence = "1")
	public String getObsEstadoDispositivo() {
		return obsEstadoDispositivo;
	}
	public void setObsEstadoDispositivo(String obsEstadoDispositivo) {
		this.obsEstadoDispositivo = obsEstadoDispositivo;
	}
	@MemberOrder(sequence = "1")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
