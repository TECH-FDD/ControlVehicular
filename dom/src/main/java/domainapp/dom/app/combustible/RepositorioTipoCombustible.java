package domainapp.dom.app.combustible;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

import domainapp.dom.app.combustible.TipoCombustible;

@DomainService(repositoryFor = TipoCombustible.class)
@DomainServiceLayout(menuOrder = "50", named = "TipoCombustible")
public class RepositorioTipoCombustible {

	@MemberOrder(sequence = "1")
	public TipoCombustible crearTipoCombustible(
			final @ParameterLayout(named = "Tipo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 20) String tipo,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 100) String descripcion) {

		TipoCombustible tipoCombustible = container
				.newTransientInstance(TipoCombustible.class);
		tipoCombustible.setTipo(tipo);
		tipoCombustible.setDescripcion(descripcion);
		container.persistIfNotAlready(tipoCombustible);
		return tipoCombustible;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
