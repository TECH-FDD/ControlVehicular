package domainapp.dom.app.alerta;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import domainapp.dom.app.matafuego.Matafuego;

@DomainService(repositoryFor = AlertaMatafuego.class)
@DomainServiceLayout(menuOrder = "120", named = "Alerta")

public class RepositorioAlertaMatafuego {
	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Nueva Alerta Matafuego")
	public AlertaMatafuego createAlertaMatafuego(

	final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombre,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String descripcion,
			final @ParameterLayout(named = "Creado Por: ") Empleado empleado,
			final @ParameterLayout(named = "Matafuego") Matafuego matafuego,
			final @ParameterLayout(named = "Fecha Alerta") Date contadorAlerta) {

		final AlertaMatafuego alertaMatafuego = new AlertaMatafuego(nombre, descripcion,
				new Date(System.currentTimeMillis()), empleado, matafuego, contadorAlerta,
				asignarAlertaEstado(contadorAlerta), null);
		container.persistIfNotAlready(alertaMatafuego);
		return alertaMatafuego;
	}

	// Validar contadorAlerta
	public String validateCreateAlertaMatafuego(String nombre, String detalle, Empleado empleado, Matafuego matafuego,
			Date contadorAlerta) {

		if (contadorAlerta.before(new Date(System.currentTimeMillis())))
			return "La fecha de la Alerta ingresado, debe ser posterior a la fecha actual";

		return null;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<AlertaMatafuego> listAll() {
		List<AlertaMatafuego> lista = this.container
				.allMatches(new QueryDefault<AlertaMatafuego>(AlertaMatafuego.class, "ListarTodos"));

		final List<AlertaMatafuego> listaMatafuego = new ArrayList<AlertaMatafuego>();
		for (AlertaMatafuego alerta : lista) {
			AlertaMatafuego alertaMatafuego = modificarAlertaEstado(alerta);
			listaMatafuego.add(alertaMatafuego);
		}
		if (lista.isEmpty()) {
			this.container.warnUser("No hay alertas de matafuegos cargadas en el sistema");
		}
		Collections.sort(listaMatafuego, new OrdenaCod());
		return listaMatafuego;
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por nombre")
	public List<AlertaMatafuego> findByNombre(@ParameterLayout(named = "Nombre") final String nombre) {
		final List<AlertaMatafuego> listaAlarma = listAll();
		final List<AlertaMatafuego> lista = new ArrayList<AlertaMatafuego>();
		for (AlertaMatafuego aM : listaAlarma) {
			if (aM.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				lista.add(aM);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe la alarma buscada");
		}
		return lista;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Fecha Alta")
	public List<AlertaMatafuego> findByFechaAlta(@ParameterLayout(named = "Fecha Alta") final LocalDate fechaAlta) {
		List<AlertaMatafuego> listaAlertaMatafuego = container.allInstances(AlertaMatafuego.class);
		List<AlertaMatafuego> listaAlarma = new ArrayList<AlertaMatafuego>();
		for (AlertaMatafuego alerta : listaAlertaMatafuego) {
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
	public AlertaMatafuego modificarAlertaEstado(AlertaMatafuego alerta) {
		long dia = diferenciaFecha(alerta.getFechaAlerta());
		if (dia > 60 && dia <= 90 && alerta.getEstadoAlerta() instanceof Activa) {
			alerta.getEstadoAlerta().cambiarAlerta(alerta);
		} else if (dia > 30 && dia <= 60 && alerta.getEstadoAnterior() == "Alerta Amarilla"
				&& alerta.getEstadoAlerta() instanceof Aplazado) {
			alerta.getEstadoAlerta().cambiarAlerta(alerta);
		} else if (dia > 0 && dia <= 30 && alerta.getEstadoAnterior() == "Alerta Amarilla"
				&& alerta.getEstadoAlerta() instanceof Aplazado) {
			alerta.getEstadoAlerta().cambiarAlerta(alerta);
		}
		return alerta;
	}

	@Programmatic
	public long diferenciaFecha(Date fecha) {
		Calendar fechaAlerta = Calendar.getInstance();
		// Se crea un objeto con la fecha actual
		Calendar fechaActual = Calendar.getInstance();
		// Se asigna la fecha recibida a la fecha de nacimiento.
		fechaAlerta.setTime(fecha);
		// Se restan la fecha actual y la fecha de nacimiento
		long milisec = fechaAlerta.getTimeInMillis() - fechaActual.getTimeInMillis();
		long days = milisec / 1000 / 60 / 60 / 24;
		System.out.println("Days : " + days);
		// int año =fechaAlerta.get(Calendar.)-fechaActual.get(Calendar.DATE);
		return days;
	}

	@Programmatic
	public EstadoAlerta asignarAlertaEstado(Date contador) {
		long dia = diferenciaFecha(contador);
		EstadoAlerta estado;
		if (dia > 60 && dia <= 90)
			estado = new AlertaAmarilla(new Timestamp(System.currentTimeMillis()));
		else if (dia > 30 && dia <= 60)
			estado = new AlertaNaranja(new Timestamp(System.currentTimeMillis()));
		else if (dia > 0 && dia <= 30)
			estado = new AlertaRoja(new Timestamp(System.currentTimeMillis()));
		else {
			estado = new Activa(new Timestamp(System.currentTimeMillis()));
			container.warnUser("dia" + dia);
		}
		return estado;
	}

	@Programmatic
	public List<AlertaMatafuego> listAllAlertaRojaMatafuego() {
		List<AlertaMatafuego> lista = listAll();
		List<AlertaMatafuego> listaAlerta = new ArrayList<AlertaMatafuego>();
		for (AlertaMatafuego alerta : lista) {
			if (alerta.getEstadoAlerta() instanceof AlertaRoja) {
				listaAlerta.add(alerta);
			}
		}
		return listaAlerta;
	}

	@Programmatic
	public List<AlertaMatafuego> listAllAlertaNaranjaMatafuego() {
		List<AlertaMatafuego> lista = listAll();
		List<AlertaMatafuego> listaAlerta = new ArrayList<AlertaMatafuego>();
		for (AlertaMatafuego alerta : lista) {
			if (alerta.getEstadoAlerta() instanceof AlertaNaranja) {
				listaAlerta.add(alerta);
			}
		}
		return listaAlerta;
	}

	@Programmatic
	public List<AlertaMatafuego> listAllAlertaAmarillaMatafuego() {
		List<AlertaMatafuego> lista = listAll();
		List<AlertaMatafuego> listaAlerta = new ArrayList<AlertaMatafuego>();
		for (AlertaMatafuego alerta : lista) {
			if (alerta.getEstadoAlerta() instanceof AlertaAmarilla) {
				listaAlerta.add(alerta);
			}
		}
		return listaAlerta;
	}

	@Programmatic
	public List<AlertaMatafuego> listAllMatafuego() {
		List<AlertaMatafuego> lista = listAll();
		List<AlertaMatafuego> listaAlerta = new ArrayList<AlertaMatafuego>();
		for (AlertaMatafuego alerta : lista) {
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
			AlertaMatafuego alertaMatafuego1 = (AlertaMatafuego) o1;
			AlertaMatafuego alertaMatafuego2 = (AlertaMatafuego) o2;
			return (alertaMatafuego1.getFechaAlerta().before(alertaMatafuego2.getFechaAlerta())) ? -1
					: (alertaMatafuego1.getFechaAlerta().after(alertaMatafuego2.getFechaAlerta()) ? 1 : 0);
		}
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
