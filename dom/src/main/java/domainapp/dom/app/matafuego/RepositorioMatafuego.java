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
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.estadoelemento.Activo;
import domainapp.dom.app.estadoelemento.Asignado;
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
			final @ParameterLayout(named = "Codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codigo,
			final @ParameterLayout(named = "Descripción") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String descripcion,
			final @ParameterLayout(named = "Capacidad") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int capacidad,
			// final @ParameterLayout(named = "Fecha de alta") Timestamp fechaAlta,
			final @ParameterLayout(named = "Fecha de última Recarga") Timestamp fechaRecarga,
			final @ParameterLayout(named = "Fecha de caducidad") Timestamp fechaCadRecarga) {

		Matafuego matafuego = new Matafuego(Marca, codigo, descripcion,
				capacidad, new Timestamp(System.currentTimeMillis()),
				fechaRecarga, fechaCadRecarga);
		container.persistIfNotAlready(matafuego);
		return matafuego;
	}

	/**
	 * Validar campos al cargar un nuevo Matafuego.
	 *
	 * @return mensaje de error.
	 */
	public String validateCreateMatafuego(String marca, String codigo, String Descripcion,
										int capacidad, Timestamp fechaRecarga, Timestamp fechaCaducidad){

		if (fechaRecarga.after(new Timestamp(System.currentTimeMillis())))
			return "La Fecha de Ultima Recarga no puede ser posterior al dia de hoy.";

		if (fechaCaducidad.before(new Timestamp(System.currentTimeMillis())))
			return "La Fecha de Caducidad de Carga, no puede ser anterior al dia hoy.";
		return null;
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

		final List<Matafuego> listaMatafuego = listAll();
		final List<Matafuego> lista = new ArrayList<Matafuego>();
		for (Matafuego m : listaMatafuego) {
			if (m.getMarca().toUpperCase().equals(marca.toUpperCase())) {
				lista.add(m);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe la Marca buscada");
		}
		return lista;
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
		return inactivos;
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
			if (m.getEstado() instanceof Activo ||
					m.getEstado() instanceof Asignado)
				activos.add(m);
		}
		return activos;
	}

	/**
	 * Filtrar lista de Matafuegos, por No asignados.
	 * @param lista
	 * @return lista de Matafuegos No Asignados.
	 */
	@Programmatic
	public List<Matafuego> noAsignados(List<Matafuego> lista){
		List<Matafuego> noAsignados = new ArrayList<Matafuego>();
		for (Matafuego m : lista){
			if (m.getEstado() instanceof Activo)
				noAsignados.add(m);
		}
		return noAsignados;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}