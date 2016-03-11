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

import domainapp.dom.app.reporte.AlertaMatafuegoDataSource;
import domainapp.dom.app.reporte.ReporteAlerta;
import domainapp.dom.app.estadoalerta.Activa;
import domainapp.dom.app.estadoalerta.AlertaAmarilla;
import domainapp.dom.app.estadoalerta.AlertaNaranja;
import domainapp.dom.app.estadoalerta.AlertaRoja;
import domainapp.dom.app.estadoalerta.Aplazado;
import domainapp.dom.app.estadoalerta.EstadoAlerta;
import domainapp.dom.app.estadoalerta.Finalizada;
import domainapp.dom.app.vehiculo.Vehiculo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

@DomainService(repositoryFor = AlertaVehiculo.class)
@DomainServiceLayout(menuOrder = "120", named = "Alerta")
public class RepositorioAlertaVehiculo {
	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Nueva Alerta Vehiculo")
	public AlertaVehiculo createAlertaVehiculo(

	final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombre,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String descripcion,
			final @ParameterLayout(named = "Vehiculo") Vehiculo vehiculo,
			final @ParameterLayout(named = "kilometros Alarma") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int kilometrosAlarma) {
		int kilometrosVehiculo= vehiculo.getKilometros();
		EstadoAlerta estado = asignarAlertaEstado(kilometrosAlarma, kilometrosVehiculo);
		final AlertaVehiculo alertaVehiculo = new AlertaVehiculo(nombre, descripcion,
				new Date(System.currentTimeMillis()), vehiculo, kilometrosAlarma, estado, null);

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
	public EstadoAlerta asignarAlertaEstado(int kilometrosAlerta, int kilometrosVehiculo) {
		EstadoAlerta estado;
		int kmRestantes=kilometrosVehiculo-kilometrosAlerta;
		if (kmRestantes > 2000 && kmRestantes<= 3000)
			estado = new AlertaAmarilla(new Timestamp(System.currentTimeMillis()));
		else if (kmRestantes> 1000 && kmRestantes <= 2000)
			estado = new AlertaNaranja(new Timestamp(System.currentTimeMillis()));
		else if (kmRestantes > 0 && kmRestantes <= 1000)
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
			AlertaVehiculo alerta1 = (AlertaVehiculo) o1;
			AlertaVehiculo alerta2 = (AlertaVehiculo) o2;
			return (alerta1.getKilometrosAlarma()).compareTo(alerta2.getKilometrosAlarma());
		}
	}

	@Programmatic
	public String downloadAll() throws JRException, IOException {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		AlertaMatafuegoDataSource datasource = new AlertaMatafuegoDataSource();
		for (AlertaVehiculo a : listAll()) {
			String fechaAlta = df.format(a.getFechaAlta());
			ReporteAlerta alerta = new ReporteAlerta();
			alerta.setNombre(a.getNombre());
			alerta.setDescripcion(a.getDescripcion());
			alerta.setEstadoAlerta(a.getEstadoAlerta().toString());
			alerta.setAlerta(a.getKilometrosAlarma().toString());
			alerta.setFechaAlta(fechaAlta);
			alerta.setElemento(a.getVehiculo().toString());
			alerta.setsubTitulo(" ");
			datasource.addParticipante(alerta);
		}
		File file = new File("AlertaVehiculo.jrxml");
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
		for (AlertaVehiculo a : listAll()) {
			String fechaAlta = df.format(a.getFechaAlta());
			ReporteAlerta alerta = new ReporteAlerta();
			alerta.setNombre(a.getNombre());
			alerta.setDescripcion(a.getDescripcion());
			alerta.setEstadoAlerta(a.getEstadoAlerta().toString());
			alerta.setAlerta(a.getKilometrosAlarma().toString());
			alerta.setFechaAlta(fechaAlta);
			alerta.setElemento(a.getVehiculo().toString());
			alerta.setsubTitulo("Desde: " + df.format(desde) + ", Hasta: " + df.format(hasta));
			datasource.addParticipante(alerta);
		}
		File file = new File("AlertaVehiculo.jrxml");
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

	@javax.inject.Inject
	DomainObjectContainer container;
}
