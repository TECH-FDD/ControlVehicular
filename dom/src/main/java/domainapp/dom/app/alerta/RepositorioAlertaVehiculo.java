package domainapp.dom.app.alerta;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.estadoalerta.Activa;
import domainapp.dom.app.estadoalerta.AlertaAmarilla;
import domainapp.dom.app.estadoalerta.AlertaNaranja;
import domainapp.dom.app.estadoalerta.AlertaRoja;
import domainapp.dom.app.estadoalerta.Aplazado;
import domainapp.dom.app.estadoalerta.EstadoAlerta;
import domainapp.dom.app.vehiculo.Vehiculo;

@DomainService(repositoryFor = AlertaVehiculo.class)
@DomainServiceLayout(menuOrder = "120", named = "Alerta")
public class RepositorioAlertaVehiculo {
	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Nueva Alerta Vehiculo")
	public AlertaVehiculo createAlertaVehiculo(

	final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombre,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String descripcion,
			final @ParameterLayout(named = "Creado Por: ") Empleado empleado,
			final @ParameterLayout(named = "Vehiculo") Vehiculo vehiculo,
			final @ParameterLayout(named = "kilometros Alarma") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int kilometrosAlarma) {
		EstadoAlerta estado = asignarAlertaEstado(kilometrosAlarma);
		final AlertaVehiculo alertaVehiculo = new AlertaVehiculo(nombre, descripcion,
				new Date(System.currentTimeMillis()), empleado, vehiculo, kilometrosAlarma, estado, null);

		container.persistIfNotAlready(alertaVehiculo);
		return alertaVehiculo;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<AlertaVehiculo> listAll() {
		List<AlertaVehiculo> lista = this.container
				.allMatches(new QueryDefault<AlertaVehiculo>(AlertaVehiculo.class, "ListarTodos"));
		if (lista.isEmpty()) {
			this.container.warnUser("No hay alertas de vehiculos cargadas en el sistema");
		}
		return lista;
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por nombre")
	public List<AlertaVehiculo> findByNombre(@ParameterLayout(named = "Nombre") final String nombre) {
		final List<AlertaVehiculo> listaAlarmaVehiculo = listAll();
		final List<AlertaVehiculo> lista = new ArrayList<AlertaVehiculo>();
		for (AlertaVehiculo aV : listaAlarmaVehiculo) {
			if (aV.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				lista.add(aV);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe la alarma buscada");
		}
		return lista;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Fecha Alta")
	public List<AlertaVehiculo> findByFechaAlta(@ParameterLayout(named = "Fecha Alta") final LocalDate fechaAlta) {

		List<AlertaVehiculo> listaAlarmaVehiculo = container.allInstances(AlertaVehiculo.class);
		List<AlertaVehiculo> listaAlarma = new ArrayList<AlertaVehiculo>();
		for (AlertaVehiculo alerta : listaAlarmaVehiculo) {
			LocalDate ld = LocalDate.fromDateFields(alerta.getFechaAlta());
			if (ld.compareTo(fechaAlta) == 0) {
				listaAlarma.add(alerta);
			}
		}
		if (listaAlarma.isEmpty()) {
			this.container.warnUser("No existe la alarma buscada");
		}
		return listaAlarma;
	}

	@Programmatic
	public EstadoAlerta asignarAlertaEstado(int kilometros) {
		EstadoAlerta estado;
		if (kilometros > 40000 && kilometros <= 80000)
			estado = new AlertaAmarilla(new Timestamp(System.currentTimeMillis()));
		else if (kilometros > 20000 && kilometros <= 40000)
			estado = new AlertaNaranja(new Timestamp(System.currentTimeMillis()));
		else if (kilometros > 0 && kilometros <= 20000)
			estado = new AlertaRoja(new Timestamp(System.currentTimeMillis()));
		else {
			estado = new Activa(new Timestamp(System.currentTimeMillis()));
		}
		return estado;
	}

	@Programmatic
	// @Collection(editing=Editing.DISABLED)
	// @CollectionLayout(render=RenderType.EAGERLY,cssClass="AlertaVehiculo")
	public List<AlertaVehiculo> listAllAlertaRojaVehiculo() {
		List<AlertaVehiculo> lista = listAll();
		List<AlertaVehiculo> listaAlerta = new ArrayList<AlertaVehiculo>();
		for (AlertaVehiculo alerta : lista) {
			if (alerta.getEstadoAlerta() instanceof AlertaRoja) {
				listaAlerta.add(alerta);
			}
		}
		return listaAlerta;
	}

	@Programmatic
	// @Collection(editing=Editing.DISABLED)
	// @CollectionLayout(render=RenderType.EAGERLY,cssClass="AlertaVehiculo")
	public List<AlertaVehiculo> listAllAlertaNaranjaVehiculo() {
		List<AlertaVehiculo> lista = listAll();
		List<AlertaVehiculo> listaAlerta = new ArrayList<AlertaVehiculo>();
		for (AlertaVehiculo alerta : lista) {
			if (alerta.getEstadoAlerta() instanceof AlertaNaranja) {
				listaAlerta.add(alerta);
			}
		}
		return listaAlerta;
	}

	@Programmatic
	// @Collection(editing=Editing.DISABLED)
	// @CollectionLayout(render=RenderType.EAGERLY,cssClass="AlertaVehiculo")
	public List<AlertaVehiculo> listAllAlertaAmarillaVehiculo() {
		List<AlertaVehiculo> lista = listAll();
		List<AlertaVehiculo> listaAlerta = new ArrayList<AlertaVehiculo>();
		for (AlertaVehiculo alerta : lista) {
			if (alerta.getEstadoAlerta() instanceof AlertaAmarilla) {
				listaAlerta.add(alerta);
			}
		}

		return listaAlerta;
	}

	@Programmatic
	public List<AlertaVehiculo> listAllVehiculo() {
		List<AlertaVehiculo> lista = listAll();
		List<AlertaVehiculo> listaAlerta = new ArrayList<AlertaVehiculo>();
		for (AlertaVehiculo alerta : lista) {
			if (!(alerta.getEstadoAlerta() instanceof Aplazado || alerta.getEstadoAlerta() instanceof Activa)) {
				listaAlerta.add(alerta);
			}
		}
		Collections.sort(listaAlerta, new OrdenaCod());
		return listaAlerta;
	}

	@SuppressWarnings("rawtypes")
	class OrdenaCod implements Comparator {
		public int compare(Object o1, Object o2) {
			AlertaVehiculo alerta1 = (AlertaVehiculo) o1;
			AlertaVehiculo alerta2 = (AlertaVehiculo) o2;
			return (alerta1.getKilometrosAlarma()).compareTo(alerta2.getKilometrosAlarma());
		}
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
