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

import domainapp.dom.app.combustible.RepositorioTipoCombustible;
import domainapp.dom.app.combustible.TipoCombustible;

public class TipoCombustibleFixture extends Fixture {

	public TipoCombustibleFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private static String tipo="Nafta,Gas-Oil,Gnc";
	private static String descripcion="Combustible para vehiculos nafteros,Combustible para vehiculos gasoleros,Combustible para vehiculos nafteros equipados con deposito de Gnc";

	private String getTipo(int x){
		return obtenerValor(tipo, x);
	}

	private String getDescripcion(int x){
		return obtenerValor(descripcion, x);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		borrarTabla(executionContext, "TipoCombustible");
		for (int x=0; x<3 ; x++){
			create(getTipo(x),getDescripcion(x),executionContext);
		}
	}

	private TipoCombustible create(final String tipo, final String descriocion, final ExecutionContext executionContext){
		return executionContext.addResult(this, repoTipoCombustible.createTipoCombustible(tipo, descriocion));
	}

	@javax.inject.Inject
	private RepositorioTipoCombustible repoTipoCombustible;
	@javax.inject.Inject
	DomainObjectContainer container;
}
