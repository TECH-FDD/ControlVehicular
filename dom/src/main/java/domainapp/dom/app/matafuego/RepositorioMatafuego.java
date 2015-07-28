package domainapp.dom.app.matafuego;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.estadoelemento.Activo;
import domainapp.dom.app.estadoelemento.Baja;
import domainapp.dom.app.estadoelemento.Inactivo;
import domainapp.dom.app.estadoelemento.NecesitaReparacion;
import domainapp.dom.app.estadoelemento.Reparacion;

@DomainService(repositoryFor = Matafuego.class)
@DomainServiceLayout(menuOrder = "80", named = "Matafuego")
public class RepositorioMatafuego {

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear matafuego")
	public Matafuego createMatafuego(
			final @ParameterLayout(named = "Marca") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String Marca,
			final @ParameterLayout(named = "codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codigo,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String descripcion,
			final @ParameterLayout(named = "Capacidad") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int capacidad,
			// final @ParameterLayout(named = "Fecha de alta") Timestamp fechaAlta,
			final @ParameterLayout(named = "Fecha de recarga") Timestamp fechaRecarga,
			final @ParameterLayout(named = "Fecha de caducidad") Timestamp fechaCadRecarga) {

		Matafuego matafuego = new Matafuego(Marca, codigo, descripcion,
				capacidad, new Timestamp(System.currentTimeMillis()),
				fechaRecarga, fechaCadRecarga);
		container.persistIfNotAlready(matafuego);
		return matafuego;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<Matafuego> listAll() {
		List<Matafuego> lista = activos(this.container
				.allMatches(new QueryDefault<Matafuego>(Matafuego.class,
						"ListarTodos")));
		if (lista.isEmpty()) {
			this.container.warnUser("No hay Matafuegos cargados en el sistema");
		}
		return lista;
	}
	
	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por Marca")
	public List<Matafuego> findByMarca(
			@ParameterLayout(named = "Marca") final String marca) {

		final List<Matafuego> listaMatafuego = activos (this.container
				.allMatches(new QueryDefault<Matafuego>(Matafuego.class,
						"buscarPorMarca", "marca", marca)));
		if (listaMatafuego.isEmpty()) {
			this.container.warnUser("No existe el matafuego buscado");
		}
		return listaMatafuego;
	}

	@MemberOrder(sequence = "4", name="Elementos Inactivos")
	@ActionLayout(named = "Matafuego")
	public List<Matafuego> listInactivos(){
		List<Matafuego> lista=container.allInstances(Matafuego.class);
		List<Matafuego> inactivos= new ArrayList<Matafuego>();
		for (Matafuego Matafuego : lista){
			if ((Matafuego.getEstado() instanceof Inactivo ||
				Matafuego.getEstado() instanceof NecesitaReparacion ||
				Matafuego.getEstado() instanceof Reparacion))
				inactivos.add(Matafuego);
		}
		return lista;
	}

	@MemberOrder(sequence = "2", name="Elementos Desestimados")
	@ActionLayout(named = "Matafuego")
	public List<Matafuego> listBaja(){
		List<Matafuego> lista = container.allInstances(Matafuego.class);
		List<Matafuego> bajas= new ArrayList<Matafuego>();
		for (Matafuego matafuego : lista){
			if (matafuego.getEstado() instanceof Baja)
				bajas.add(matafuego);
		}
		return bajas;
	}

	/**
	 * Filtrar lista de Matafuegos, por estado Activo.
	 * @param lista
	 * @return lista de Matafuegos Activos.
	 */
	private List<Matafuego> activos(List<Matafuego> lista){
		List<Matafuego> activos = new ArrayList<Matafuego>();
		for (Matafuego m : lista){
			if (m.getEstado() instanceof Activo)
				activos.add(m);
		}
		return activos;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}