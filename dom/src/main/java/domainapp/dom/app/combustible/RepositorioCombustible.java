package domainapp.dom.app.combustible;

import java.math.BigDecimal;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.DomainServiceLayout.MenuBar;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.combustible.Combustible;
import domainapp.dom.app.combustible.TipoCombustible;

@DomainService(repositoryFor = Combustible.class)
@DomainServiceLayout(menuOrder = "40", named = "Combustible",menuBar=MenuBar.PRIMARY)
public class RepositorioCombustible {

	@MemberOrder(sequence = "1")
	public Combustible crearCombustible(

			final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) String nombre,
			final @ParameterLayout(named = "Empresa") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 30) String empresa,
			final @ParameterLayout(named = "Inicial") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 20) String codigo,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 100) String descripcion,
			final @ParameterLayout(named = "Categoria") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 100) String categoria,
			final @ParameterLayout(named = "Precio/litro") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) BigDecimal precioLitro,
			final @ParameterLayout(named = "Precio anterior") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) BigDecimal precioAnterior,
			final @ParameterLayout(named = "Porcentaje de aumento") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) BigDecimal porceAumento,
			final @ParameterLayout(named = "Octanaje") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) int octanaje,
			final @ParameterLayout(named = "Tipo de combustible") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) TipoCombustible tipoCombustible) {

		final Combustible combustible = container
				.newTransientInstance(Combustible.class);

		combustible.setNombre(nombre);
		combustible.setEmpresa(empresa);
		combustible.setCodigo(codigo);
		combustible.setDescripcion(descripcion);
		combustible.setCategoria(categoria);
		combustible.setPrecioLitro(precioLitro);
		combustible.setPrecioAnterior(precioAnterior);
		combustible.setPorcentajeAumento(porceAumento);
		combustible.setOctanaje(octanaje);
		combustible.setTipoCombustible(tipoCombustible);
		combustible.setActivo(true);

		container.persistIfNotAlready(combustible);
		return combustible;
	}

	// Validacion de nombre de combustible y codigo
	public String validateCrearCombustible(String nombre, String empresa,
			String codigo, String descripcion, String categoria,
			BigDecimal precio_litro, BigDecimal precio_anterior,
			BigDecimal porce_aumento, int octanaje,
			TipoCombustible tipo_combustible) {
		if (!container.allMatches(
				new QueryDefault<Combustible>(Combustible.class,
						"buscarPorNombre", "nombre", nombre)).isEmpty()) {
			this.container
					.warnUser("El nombre ya existe. Por favor verifique datos ingresados.");
		}
		if (!container.allMatches(
				new QueryDefault<Combustible>(Combustible.class,
						"buscarPorCodigo", "codigo", codigo)).isEmpty()) {
			this.container
					.warnUser("El codigo ingresado ya exite! Por favor verifique los datos ingresados.");
		}
		return null;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named="Listar todos")
	public List<Combustible> listAll() {
		final List<Combustible> listaCombustible = this.container
				.allMatches(new QueryDefault<Combustible>(Combustible.class,
						"ListarTodos"));
		if (listaCombustible.isEmpty()) {
			this.container
					.warnUser("No hay combustibles cargados en el sistema");
		}
		return listaCombustible;
	}


	@MemberOrder(sequence = "3")
	@ActionLayout(named="Buscar por nombre")
	public List<Combustible> findByNombre(
			@ParameterLayout(named = "Nombre") final String nombre) {

		final List<Combustible> listaCombustible = this.container
				.allMatches(new QueryDefault<Combustible>(Combustible.class,
						"buscarPorNombre", "nombre", nombre));
		if (listaCombustible.isEmpty()) {
			this.container.warnUser("No existe el combustible buscado");
		}
		return listaCombustible;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named="Buscar por codigo")
	public List<Combustible> findByCodigo(
			@ParameterLayout(named = "Codigo") final String codigo) {
		final List<Combustible> listaCombustible = this.container
				.allMatches(new QueryDefault<Combustible>(Combustible.class,
						"buscarPorCodigo", "codigo", codigo));
		if (listaCombustible.isEmpty()) {
			this.container.warnUser("No existe el combustible buscado");
		}
		return listaCombustible;
	}
	
	

	@javax.inject.Inject
	DomainObjectContainer container;
}