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

import org.apache.isis.applib.fixturescripts.FixtureScript;

public abstract class Fixture extends FixtureScript {

	@Override
	protected void execute(ExecutionContext executionContext) {
		// TODO Auto-generated method stub

	}

	public static String obtenerValor(final String valor, final int x){
		String[] valores=valor.split(",");
		return valores[x];
	}

	public void borrarTabla(final ExecutionContext executionContext,final String tabla){
		executionContext.executeChild(this, new VaciarTablaFixture(tabla));
	}
}
