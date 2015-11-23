package domainapp.dom.app.mantenimiento;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.gps.RepositorioGps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.matafuego.RepositorioMatafuego;
import domainapp.dom.app.taller.Taller;
import domainapp.dom.app.vehiculo.RepositorioVehiculo;
import domainapp.dom.app.vehiculo.Vehiculo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

@DomainService(repositoryFor = Mantenimiento.class)
@DomainServiceLayout(menuOrder = "120", named = "Mantenimiento")
public class RepositorioMantenimiento {

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear mantenimiento")
	public Mantenimiento createMantenimiento(

			final @ParameterLayout(named = "Titulo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 50) String titulo,
			final @ParameterLayout(named = "Detalle") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL, maxLength = 50) String detalle,
			final @ParameterLayout(named = "Codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL, maxLength = 50) String codigo,
			final @ParameterLayout(named = "Fecha de realizacion") Timestamp fechaRealizacion,
			final @ParameterLayout(named = "Tipo elemento") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 30) TipoElemento tipoElemento,
			final @ParameterLayout(named = "Elemento") ObjetoMantenible elemento,
			final @ParameterLayout(named = "Taller") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 30) Taller taller,
			final @ParameterLayout(named = "Costo de respuesto") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 30) BigDecimal costoRespuesto,
			final @ParameterLayout(named = "Costo de mano de obra") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 30) BigDecimal costoManoObra,
			final @ParameterLayout(named = "Costo total") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 30) BigDecimal costoTotal,
			final @ParameterLayout(named = "De rutina") @Parameter(optionality = Optionality.OPTIONAL) Boolean deRutina) {

		Mantenimiento unMantenimiento = new Mantenimiento(titulo, detalle,
				codigo, new Timestamp(System.currentTimeMillis()),
				fechaRealizacion, tipoElemento, elemento, taller, costoRespuesto,
				costoManoObra, costoTotal, deRutina);
		container.persistIfNotAlready(unMantenimiento);
		return unMantenimiento;

	}


	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<Mantenimiento> listAll() {
		final List<Mantenimiento> listaMantenimiento = this.container
				.allMatches(new QueryDefault<Mantenimiento>(
						Mantenimiento.class, "ListarTodos"));
		if (listaMantenimiento.isEmpty()) {
			this.container
					.warnUser("No hay combustibles cargados en el sistema");
		}
		return listaMantenimiento;
	}
	
	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por titulo")
	public List<Mantenimiento> findByTitulo(
			@ParameterLayout(named = "Titulo") final String titulo) {

		final List<Mantenimiento> listaMantenimiento = this.container
				.allMatches(new QueryDefault<Mantenimiento>(
						Mantenimiento.class, "buscarPorTitulo", "titulo",
						titulo));

		if (listaMantenimiento.isEmpty()) {
			this.container.warnUser("No existe el mantenimiento buscado");
		}
		return listaMantenimiento;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por codigo")
	public List<Mantenimiento> findByCodigo(
			@ParameterLayout(named = "Codigo") final String codigo) {
		final List<Mantenimiento> listaMantenimiento = this.container
				.allMatches(new QueryDefault<Mantenimiento>(
						Mantenimiento.class, "buscarPorCodigo", "codigo",
						codigo));
		if (listaMantenimiento.isEmpty()) {
			this.container.warnUser("No existe el mantenimiento buscado");
		}
		return listaMantenimiento;
	}

	@MemberOrder(sequence = "7", name="Elementos Inactivos")
	@ActionLayout(named = "Mantenimiento")
	public List<Mantenimiento> listInactivos(){
		List<Mantenimiento> lista=container.allInstances(Mantenimiento.class);
		List<Mantenimiento> inactivos= new ArrayList<Mantenimiento>();
		for (Mantenimiento mantenimiento : lista){
			if ((mantenimiento.getEstadoMantenimiento() instanceof Aceptado ||
					mantenimiento.getEstadoMantenimiento() instanceof Cancelado ))
				inactivos.add(mantenimiento);
		}
		return inactivos;
	}

	@MemberOrder(sequence = "5", name="Elementos Desestimados")
	@ActionLayout(named = "Mantenimiento")
	public List<Mantenimiento> listBaja(){
		List<Mantenimiento> lista = container.allInstances(Mantenimiento.class);
		List<Mantenimiento> bajas= new ArrayList<Mantenimiento>();
		for (Mantenimiento mantenimiento : lista){
			if (mantenimiento.getEstadoMantenimiento() instanceof Cancelado)
				bajas.add(mantenimiento);
		}
		return bajas;
	}

	public List<ObjetoMantenible> choices5CreateMantenimiento(final String titulo, final String detalle, final String codigo, final Timestamp fechaRealizacion,
															  final TipoElemento tipoElemento, final ObjetoMantenible elemento, final Taller taller, 
															  final BigDecimal costoRespuesto, final BigDecimal costoManoObra, final BigDecimal costoTotal, 
															  final Boolean deRutina) {
		List<ObjetoMantenible> lista = new ArrayList<ObjetoMantenible>();
		if (tipoElemento == TipoElemento.GPS)
		{
			List<Gps> listaGps = repoGps.listarTodos();
			for (Gps gps : listaGps)
			{
				lista.add(gps);
			}
			return lista;
		}
		else if(tipoElemento == TipoElemento.MATAFUEGO) 
		{
			List<Matafuego> listaMatafuego = repoMatafuego.listAll();
			for (Matafuego matafuego : listaMatafuego)
			{
				lista.add(matafuego);
			}
			return lista;
		}
		else
		{
			List<Vehiculo> listaVehiculo = repoVehiculo.listAll();
			for (Vehiculo vehiculo : listaVehiculo)
			{
				lista.add(vehiculo);
			}
			return lista;
		}
	}

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	RepositorioGps repoGps;
	@javax.inject.Inject
	RepositorioMatafuego repoMatafuego;
	@javax.inject.Inject
	RepositorioVehiculo repoVehiculo;
}