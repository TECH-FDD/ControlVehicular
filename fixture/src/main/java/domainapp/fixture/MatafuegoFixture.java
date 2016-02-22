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
    
package domainapp.fixture;

import java.sql.Timestamp;
import java.util.Calendar;

import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.matafuego.RepositorioMatafuego;

public class MatafuegoFixture extends Fixture {

	private String marca="Yukon,Georgia,Drago,3M,TGB";
	private String codigo="YK,GG,DG,3M,TGB";
	private String descripcion="Extintor manual de Polvo Comprimido.";
	private String capacidad= "3,3,5,10,15";
	private Timestamp fechaCarga = new Timestamp(System.currentTimeMillis());
	private Timestamp fechaRecarga;

	public MatafuegoFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	public String getMarca(int x) {
		return obtenerValor(marca, x);
	}

	public String getCodigo(int x) {
		return obtenerValor(codigo, x);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCapacidad(int x) {
		return obtenerValor(capacidad, x);
	}

	public Timestamp getFechaCarga() {
		return fechaCarga;
	}

	public Timestamp getFechaRecarga() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Timestamp(System.currentTimeMillis()).getTime());
		cal.add(Calendar.YEAR, 1);
		fechaRecarga = new Timestamp(cal.getTimeInMillis());
		return fechaRecarga;
	}

	public void setFechaRecarga(Timestamp fechaRecarga) {
		this.fechaRecarga = fechaRecarga;
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		borrarTabla(executionContext, "Matafuego");
		for (int x=0; x<5; x++){
			create(getMarca(x), getCodigo(x), Integer.parseInt(getCapacidad(x)), getFechaCarga(), getFechaRecarga(), executionContext);
		}
	}

	private Matafuego create(String marca, String codigo, int capacidad, Timestamp fechaRecarga, Timestamp fechaCadRecarga, ExecutionContext executionContext){
		return executionContext.addResult(this, repoMatafuego.createMatafuego(marca, codigo, descripcion, capacidad, fechaRecarga, fechaCadRecarga));
	}

	@javax.inject.Inject
	private RepositorioMatafuego repoMatafuego;
}
