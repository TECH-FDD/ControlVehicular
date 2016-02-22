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

public class ReporteMatafuego {
	private String marca;
	private String codigo;
	private String descripcion;
	private String capacidad;
	private String fechaAlta;
	private String fechaRecarga;
	private String fechaCadRecarga;
	private String estado;
	
	@MemberOrder(sequence = "1")
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	@MemberOrder(sequence = "1")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@MemberOrder(sequence = "1")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@MemberOrder(sequence = "1")
	public String getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	@MemberOrder(sequence = "1")
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	@MemberOrder(sequence = "1")
	public String getFechaRecarga() {
		return fechaRecarga;
	}
	public void setFechaRecarga(String fechaRecarga) {
		this.fechaRecarga = fechaRecarga;
	}
	@MemberOrder(sequence = "1")
	public String getFechaCadRecarga() {
		return fechaCadRecarga;
	}
	public void setFechaCadRecarga(String fechaCadRecarga) {
		this.fechaCadRecarga = fechaCadRecarga;
	}
	@MemberOrder(sequence = "1")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
