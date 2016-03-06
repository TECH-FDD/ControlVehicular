/*
 *  SIGAFV is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * SIGAFV is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SIGAFV.  If not, see <http://www.gnu.org/licenses/>.
 */
    
package domainapp.dom.app.alerta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
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

import domainapp.dom.app.estadoalerta.Activa;
import domainapp.dom.app.estadoalerta.AlertaAmarilla;
import domainapp.dom.app.estadoalerta.AlertaNaranja;
import domainapp.dom.app.estadoalerta.AlertaRoja;
import domainapp.dom.app.estadoalerta.Aplazado;
import domainapp.dom.app.estadoalerta.EstadoAlerta;
import domainapp.dom.app.estadoalerta.Finalizada;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.reporte.AlertaMatafuegoDataSource;
import domainapp.dom.app.reporte.ReporteAlerta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

@DomainService(repositoryFor = AlertaMatafuego.class)
@DomainServiceLayout(menuOrder = "120", named = "Alerta")

public class RepositorioAlertaMatafuego {
	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Nueva Alerta Matafuego")
	public AlertaMatafuego createAlertaMatafuego(

	final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombre,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String descripcion,
			final @ParameterLayout(named = "Matafuego") Matafuego matafuego,
			final @ParameterLayout(named = "Fecha Alerta") Date contadorAlerta) {

		final AlertaMatafuego alertaMatafuego = new AlertaMatafuego(nombre, descripcion,
				new Date(System.currentTimeMillis()), matafuego, contadorAlerta,
				asignarAlertaEstado(contadorAlerta), null);
		container.persistIfNotAlready(alertaMatafuego);
		return alertaMatafuego;
	}

	// Validar contadorAlerta
	public String validateCreateAlertaMatafuego(String nombre, String detalle, Matafuego matafuego,
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
			if (!(alerta.getEstadoAlerta() instanceof Aplazado || alerta.getEstadoAlerta() instanceof Activa || alerta.getEstadoAlerta() instanceof Finalizada)) {
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

	@Programmatic
	public String downloadAll() throws JRException, IOException {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		AlertaMatafuegoDataSource datasource = new AlertaMatafuegoDataSource();
		for (AlertaMatafuego a : listAll()) {
			String fechaAlerta = df.format(a.getFechaAlerta());
			String fechaAlta = df.format(a.getFechaAlta());
			ReporteAlerta alerta = new ReporteAlerta();
			alerta.setNombre(a.getNombre());
			alerta.setDescripcion(a.getDescripcion());
			alerta.setEstadoAlerta(a.getEstadoAlerta().toString());
			alerta.setAlerta(fechaAlerta);
			alerta.setFechaAlta(fechaAlta);
			alerta.setElemento(a.getMatafuego().toString());
			alerta.setsubTitulo(" ");
			datasource.addParticipante(alerta);
		}
		File file = new File("AlertaMatafuego.jrxml");
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		JasperDesign jd = JRXmlLoader.load(input);
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
		JasperViewer.viewReport(jasperPrint, false);
		return "Reporte Generado";
	}

	@Programmatic
	public String exportarPorPeriodo(Date desde, Date hasta) throws JRException, IOException {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		AlertaMatafuegoDataSource datasource = new AlertaMatafuegoDataSource();
		for (AlertaMatafuego a : listAll()) {
			String fechaAlerta = df.format(a.getFechaAlerta());
			String fechaAlta = df.format(a.getFechaAlta());
			ReporteAlerta alerta = new ReporteAlerta();
			alerta.setNombre(a.getNombre());
			alerta.setDescripcion(a.getDescripcion());
			alerta.setEstadoAlerta(a.getEstadoAlerta().toString());
			alerta.setAlerta(fechaAlerta);
			alerta.setFechaAlta(fechaAlta);
			alerta.setElemento(a.getMatafuego().toString());
			alerta.setsubTitulo("Desde: " + df.format(desde) + ", Hasta: " + df.format(hasta));
			datasource.addParticipante(alerta);
		}
		File file = new File("AlertaMatafuego.jrxml");
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		JasperDesign jd = JRXmlLoader.load(input);
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
		JasperViewer.viewReport(jasperPrint, false );
		return "Reporte Generado";
	}
	@javax.inject.Inject
	DomainObjectContainer container;
}
