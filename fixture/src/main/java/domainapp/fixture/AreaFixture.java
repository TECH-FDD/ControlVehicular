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

import org.apache.isis.applib.DomainObjectContainer;

import domainapp.dom.app.area.Area;
import domainapp.dom.app.area.RepositorioArea;

public class AreaFixture extends Fixture {

	private static String codigo = "MT,AC,RRPP";
	private static String nombre = "Mantenimiento,Atención al Cliente,Relaciones Publicas";
	private static String descripcion = "Mantenimintos de lineas y equipos externos de la empresa,Atención domiciliaria a clientes,Relaciones publicas con empresas externas interesadas en contratar nestro servicio";

	public AreaFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private static String getCodigo(int x) {
		return obtenerValor(codigo, x);
	}

	private static String getNombre(int x) {
		return obtenerValor(nombre, x);
	}

	private static String getDescripcion(int x) {
		return obtenerValor(descripcion, x);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		borrarTabla(executionContext, "Area");
		for (int x = 0; x < 3; x++) {
			create(getCodigo(x), getNombre(x), getDescripcion(x),
					executionContext);
		}
	}

	private Area create(String codigo, String nombre, String descripcion,
			ExecutionContext executionContext) {
		return executionContext.addResult(this,
				repoArea.createArea(codigo, nombre, descripcion));
	}

	@javax.inject.Inject
	private RepositorioArea repoArea;
	@javax.inject.Inject
	DomainObjectContainer container;
}
