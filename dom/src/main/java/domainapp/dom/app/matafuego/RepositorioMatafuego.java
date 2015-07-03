package domainapp.dom.app.matafuego;

import java.sql.Timestamp;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

@DomainService(repositoryFor = Matafuego.class)
@DomainServiceLayout(menuOrder = "80", named = "Matafuego")
public class RepositorioMatafuego {

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear matafuego")
	public Matafuego createMatafuego(
			final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombre,
			final @ParameterLayout(named = "codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codigo,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String descripcion,
			final @ParameterLayout(named = "Capacidad") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int capacidad,
			// final @ParameterLayout(named = "Fecha de alta") Timestamp fechaAlta,
			final @ParameterLayout(named = "Fecha de recarga") Timestamp fechaRecarga,
			final @ParameterLayout(named = "Fecha de caducidad") Timestamp fechaCadRecarga) {

		Matafuego matafuego = new Matafuego(nombre, codigo, descripcion,
				capacidad, new Timestamp(System.currentTimeMillis()),
				fechaRecarga, fechaCadRecarga);
		container.persistIfNotAlready(matafuego);
		return matafuego;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<Matafuego> listAll() {
		List<Matafuego> lista = this.container
				.allMatches(new QueryDefault<Matafuego>(Matafuego.class,
						"ListarTodos"));
		if (lista.isEmpty()) {
			this.container.warnUser("No hay areas cargadas en el sistema");
		}
		return lista;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}