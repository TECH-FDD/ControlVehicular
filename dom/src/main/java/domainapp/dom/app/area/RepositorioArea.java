package domainapp.dom.app.area;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
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
	@ActionLayout(named = "Crear area")
	public Area createArea(
			final @ParameterLayout(named = "Codigo de Area") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) String codigoArea,
			final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 30) String nombre,
			final @ParameterLayout(named = "Descripcion") String descripcion){

		final Area area = container.newTransientInstance(Area.class);

		Date date = new Date();
		Timestamp fecha = new Timestamp(date.getTime());

		area.setCodigoArea(codigoArea);
		area.setNombre(nombre);
		area.setDescripcion(descripcion);
		area.setFechaAlta(fecha);
		area.setActivo(true);
		container.persistIfNotAlready(area);
		return area;

	}

	// Validar Codigo de Area y Nombre
		public String validateCreateArea(String codigoArea, String nombre,String descripcion) {
			if (!container.allMatches(
					new QueryDefault<Area>(Area.class, "buscarPorNombre",
							"nombre", nombre.toUpperCase())).isEmpty()) {
				return "El nombre ya Existe. Por favor vericar los datos ingresados.";
			}
			if (!container.allMatches(
					new QueryDefault<Area>(Area.class, "buscarPorCodigo",
							"codigoArea", codigoArea.toUpperCase())).isEmpty()) {
				return "El Codigo de Area ya Existe. Por favor vericar los datos ingresados.";
			}
			return null;
		}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<Area> listAll() {
		final List<Area> listaArea = this.container
				.allMatches(new QueryDefault<Area>(Area.class, "ListarTodos"));
		if (listaArea.isEmpty()) {
			this.container.warnUser("No hay areas cargadas en el sistema");
		}
		return listaArea;
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por nombre")
	public List<Area> findByNombre(
			@ParameterLayout(named = "Nombre") final String nombre) {
		final List<Area> listaArea = listAll();
		final List<Area> lista = new ArrayList<Area>();
		for (Area a : listaArea) {
			if (a.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				lista.add(a);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Nombre buscado");
		}
		return lista;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por codigo")
	public List<Area> findByCodigo(
			@ParameterLayout(named = "Codigo de area") final String codigoArea) {
		final List<Area> listaArea = listAll();
		final List<Area> lista = new ArrayList<Area>();
		for (Area a : listaArea) {
			if (a.getCodigoArea().toUpperCase().equals(codigoArea.toUpperCase())) {
				lista.add(a);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Codigo buscado");
		}
		return lista;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}