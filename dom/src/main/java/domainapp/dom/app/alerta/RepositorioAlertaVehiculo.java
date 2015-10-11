package domainapp.dom.app.alerta;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
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

import domainapp.dom.app.Estadoalerta.Activa;
import domainapp.dom.app.Estadoalerta.AlertaAmarilla;
import domainapp.dom.app.Estadoalerta.AlertaNaranja;
import domainapp.dom.app.Estadoalerta.AlertaRoja;
import domainapp.dom.app.Estadoalerta.Aplazado;
import domainapp.dom.app.Estadoalerta.EstadoAlerta;
import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.reporte.Formato;
import domainapp.dom.app.reporte.GenerarReporte;
import domainapp.dom.app.reporte.ReporteAlerta;
import domainapp.dom.app.vehiculo.Vehiculo;
import net.sf.jasperreports.engine.JRException;

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

	@Programmatic
	public String elegirFormato(Formato formato) throws JRException, IOException {
		return exportarTodo(formato);
	}

	@Programmatic
	public String elegirFormato(Formato formato, Date desde, Date hasta) throws JRException, IOException {
		return exportarPorPeriodo(desde, hasta, formato);
	}

	@Programmatic
	public Formato default0ElegirFormato(final @ParameterLayout(named = "Formato") Formato formato) {
		return Formato.PDF;
	}

	@Programmatic
	public String exportarTodo(Formato formato) throws JRException, IOException {
		List<Object> objectsReport = new ArrayList<Object>();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		for (AlertaVehiculo a : listAll()) {
			String fechaAlta = df.format(a.getFechaAlta());
			ReporteAlerta alerta = new ReporteAlerta();
			alerta.setNombre(a.getNombre());
			alerta.setDescripcion(a.getDescripcion());
			alerta.setEstadoAlerta(a.getEstadoAlerta().toString());
			alerta.setAlerta(a.getKilometrosAlarma().toString());
			alerta.setFechaAlta(fechaAlta);
			alerta.setElemento(a.getVehiculo().toString());
			alerta.setEmpleadoInvolucrado(a.getEmpleado().getNombre() + " " + a.getEmpleado().getApellido());
			alerta.setsubTitulo(" ");
			objectsReport.add(alerta);
		}
		if (objectsReport.isEmpty() == false) {
			String nombreArchivo = null;
			if (formato == Formato.PDF)
				nombreArchivo = "ReporteAlerta/AlertasVehiculo/PDF/AlertaVehiculo "
						+ new Date(System.currentTimeMillis());
			else if (formato == Formato.XLS)
				nombreArchivo = "ReporteAlerta/AlertasVehiculo/XLS/AlertaVehiculo "
						+ new Date(System.currentTimeMillis());
			else
				nombreArchivo = "ReporteAlerta/AlertasVehiculo/DOC/AlertaVehiculo "
						+ new Date(System.currentTimeMillis());

			GenerarReporte.generarReporte("AlertasVehiculo.jrxml", objectsReport, formato, nombreArchivo);
			return "Se ha realizado la exportacion Correctamente";
		} else
			return "No hay elementos para imprimir";
	}

	@Programmatic
	public String exportarPorPeriodo(Date desde, Date hasta, Formato formato) throws JRException, IOException {
		List<Object> objectsReport = new ArrayList<Object>();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		for (AlertaVehiculo a : listAll()) {
			if (a.getFechaAlta().after(desde) && a.getFechaAlta().before(hasta)) {
				String fechaAlta = df.format(a.getFechaAlta());
				ReporteAlerta alerta = new ReporteAlerta();
				alerta.setNombre(a.getNombre());
				alerta.setDescripcion(a.getDescripcion());
				alerta.setEstadoAlerta(a.getEstadoAlerta().toString());
				alerta.setAlerta(a.getKilometrosAlarma().toString());
				alerta.setFechaAlta(fechaAlta);
				alerta.setElemento(a.getVehiculo().toString());
				alerta.setEmpleadoInvolucrado(a.getEmpleado().getNombre() + " " + a.getEmpleado().getApellido());
				alerta.setsubTitulo("Desde: " + df.format(desde) + ", Hasta: " + df.format(hasta));
				objectsReport.add(alerta);
			}
		}
		if (objectsReport.isEmpty() == false) {
			String nombreArchivo = null;
			if (formato == Formato.PDF)
				nombreArchivo = "ReporteAlerta/AlertasVehiculo/PDF/AlertaVehiculo "
						+ new Date(System.currentTimeMillis());
			else if (formato == Formato.DOCX)
				nombreArchivo = "ReporteAlerta/AlertasVehiculo/DOC/AlertaVehiculo "
						+ new Date(System.currentTimeMillis());

			GenerarReporte.generarReporte("AlertasVehiculo.jrxml", objectsReport, formato, nombreArchivo);
			return "Se ha realizado la exportacion Correctamente";
		} else
			return "No hay elementos para imprimir";

	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
