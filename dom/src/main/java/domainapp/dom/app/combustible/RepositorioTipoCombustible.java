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
@DomainServiceLayout(menuOrder = "50", named = "TipoCombustible")
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
