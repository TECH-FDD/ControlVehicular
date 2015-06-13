package domainapp.dom.app.area;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.area.Area;

@DomainService(repositoryFor = Area.class)
@DomainServiceLayout(menuOrder = "30", named = "Area")
public class RepositorioArea {

	@MemberOrder(sequence = "1")
	public Area crearArea(
			final @ParameterLayout(named = "Codigo de Area") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) String codigoArea,
			final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 30) String nombre,
			final @ParameterLayout(named = "Descripcion") String descripcion)
	/* final @ParameterLayout(named="Fecha de alta") Timestamp fecha_alta) */{

		final Area area = container.newTransientInstance(Area.class);

		Date date = new Date();
		Timestamp fecha = new Timestamp(date.getTime());

		area.setCodigoArea(codigoArea);
		area.setNombre(nombre);
		area.setDescripcion(descripcion);
		area.setFechaAlta(fecha);
		container.persistIfNotAlready(area);
		return area;

	}

	@MemberOrder(sequence = "2")
	public List<Area> ListarTodos() {
		return container.allInstances(Area.class);
	}

	@MemberOrder(sequence = "3")
	public List<Area> buscarPorNombre(
			@ParameterLayout(named = "Nombre") final String nombre) {
		return container.allMatches(new QueryDefault<>(Area.class,
				"buscarPorNombre", "nombre", nombre));
	}

	@MemberOrder(sequence = "4")
	public List<Area> buscarPorCodigo(
			@ParameterLayout(named = "Codigo de area") final String codigoArea) {
		return container.allMatches(new QueryDefault<>(Area.class,
				"buscarPorCodigo", "codigoArea", codigoArea));
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}