package domainapp.dom.app.taller;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

@DomainService(repositoryFor = Taller.class)
@DomainServiceLayout(menuOrder = "100", named = "Taller")
public class RepositorioTaller {

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Taller")
	public Taller createTaller(
			final @ParameterLayout(named = "Nombre Comercial") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombreComercial,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String descripcion,
			final @ParameterLayout(named = "Direccion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String direccion,
			final @ParameterLayout(named = "telefono") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionTel.ADMITIDOS) String telefono,
			final @ParameterLayout(named = "Codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codigo,
			final @ParameterLayout(named = "Email") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionEmail.ADMITIDOS) String email) {

		final Taller taller = container.newTransientInstance(Taller.class);

		taller.setNombreComercial(nombreComercial.toUpperCase());
		taller.setTelefono(telefono);
		taller.setDescripcion(descripcion.toUpperCase());
		taller.setDireccion(direccion.toUpperCase());
		taller.setEmail(email);
		taller.setCodigo(codigo);
		container.persistIfNotAlready(taller);
		return taller;

	}

	// Validar nombre comercial y codigo del taller
	public String validateCreateTaller(String nombreComercial, String telefono,
			String descripcion, String codigo, String direccion, String email) {
		if (!container.allMatches(
				new QueryDefault<Taller>(Taller.class,
						"buscarPorNombreComercial", "nombreComercial",
						nombreComercial.toUpperCase())).isEmpty()) {
			return "El nombre comercial ya existe. Por favor verificar los Datos Ingresados.";
		}
		if (!container.allMatches(
				new QueryDefault<Taller>(Taller.class, "buscarPorCodigo",
						"codigo", codigo.toUpperCase())).isEmpty()) {
			return "El Codigo ya existe. Por favor verificar los Datos Ingresados.";
		}

		return null;
	}
	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<Taller> listAll() {
		List<Taller> lista = this.container
				.allMatches(new QueryDefault<Taller>(Taller.class,
						"ListarTodos"));
		if (lista.isEmpty()) {
			this.container.warnUser("No hay talleres cargados en el sistema");
		}
		return lista;
	}
	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por Nombre Comercial")
	public List<Taller> findByNombreComercial(
			@ParameterLayout(named = "Nombre Comercial") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String nombreComercial) {
		return container.allMatches(new QueryDefault<>(Taller.class,
				"buscarPorNombreComercial", "nombreComercial", nombreComercial));
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Direccion")
	public List<Taller> findByDireccion(
			@ParameterLayout(named = "Direccion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String direccion) {
		return container.allMatches(new QueryDefault<>(Taller.class,
				"buscarPorDireccion", "direccion", direccion));
	}

	@MemberOrder(sequence = "5")
	@ActionLayout(named = "Buscar por Codigo")
	public List<Taller> findByCodigo(
			@ParameterLayout(named = "Codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String codigo) {
		return container.allMatches(new QueryDefault<>(Taller.class,
				"buscarPorCodigo", "codigo",
				codigo));
	}


	@javax.inject.Inject
	DomainObjectContainer container;
}
