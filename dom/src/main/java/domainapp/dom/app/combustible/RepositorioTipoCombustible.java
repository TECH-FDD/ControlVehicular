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
    
package domainapp.dom.app.combustible;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.combustible.TipoCombustible;

@DomainService(repositoryFor = TipoCombustible.class)
@DomainServiceLayout(menuOrder = "40", named = "Combustible")
public class RepositorioTipoCombustible {

	@MemberOrder(sequence = "1")
	@ActionLayout(named="Crear tipo combustible")
	public TipoCombustible createTipoCombustible(
			final @ParameterLayout(named = "Tipo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 20) String tipo,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 100) String descripcion) {

		TipoCombustible tipoCombustible = container
				.newTransientInstance(TipoCombustible.class);
		tipoCombustible.setTipo(tipo);
		tipoCombustible.setDescripcion(descripcion);
		container.persistIfNotAlready(tipoCombustible);
		return tipoCombustible;
	}
	// Validacion de tipo de combustible
	public String validateCreateTipoCombustible(String tipo,String descripcion) {
		if (!container.allMatches(new QueryDefault<TipoCombustible>(TipoCombustible.class,"buscarPorTipo", "tipo", tipo.toUpperCase())).isEmpty()) {
				return "El tipo de combustible ingresado ya existe. Por favor verifique datos ingresados.";
			}
		return null;
		}

	@MemberOrder(sequence = "2")
	@ActionLayout(named="Lista todos")
	public List<TipoCombustible> listAll() {
		return container.allInstances(TipoCombustible.class);
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
