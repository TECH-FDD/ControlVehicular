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
    
package domainapp.dom.app.empleado;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import domainapp.dom.app.area.Area;
import domainapp.dom.app.persona.Ciudad;
import domainapp.dom.app.persona.Documento;
import domainapp.dom.app.persona.Provincia;
import domainapp.dom.app.persona.Sexo;
import domainapp.dom.app.reporte.EmpleadoDataSource;
import domainapp.dom.app.reporte.ReporteEmpleado;
import domainapp.dom.app.vehiculo.Vehiculo;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

@DomainService(repositoryFor = Empleado.class)
@DomainServiceLayout(menuOrder = "20", named = "Empleado")
public class RepositorioEmpleado {
	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear nuevo Empleado")
	public Empleado createEmpleado(
			final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String nombre,
			final @ParameterLayout(named = "Apellido") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String apellido,
			final @ParameterLayout(named = "Tipo de Documento") Documento tipoDocumento,
			final @ParameterLayout(named = "N° de Documento") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int nro_documento,
			final @ParameterLayout(named = "Fecha de Nacimiento") LocalDate fechaNacimiento,
			final @ParameterLayout(named = "Sexo") Sexo sexo,
			final @ParameterLayout(named = "Provincia") Provincia provincia,
			final @ParameterLayout(named = "Ciudad") Ciudad ciudad,
			final @ParameterLayout(named = "Codigo Postal") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int codigoPostal,
			final @ParameterLayout(named = "Domicilio") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String domicilio,
			final @ParameterLayout(named = "Telefono") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionTel.ADMITIDOS) String telefono,
			final @ParameterLayout(named = "E-mail") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionEmail.ADMITIDOS) String email,
			final @ParameterLayout(named = "Nro de Legajo") String legajo,
			final @ParameterLayout(named = "Vehiculo") @Parameter(optionality=Optionality.OPTIONAL) Vehiculo vehiculo,
			final @ParameterLayout(named = "Area") Area area) {

		final Empleado empleado = container
				.newTransientInstance(Empleado.class);
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setTipoDocumento(tipoDocumento);
		empleado.setNroDocumento(nro_documento);
		empleado.setFechaNacimiento(fechaNacimiento);
		empleado.setDomicilio(domicilio);
		empleado.setCiudad(ciudad);
		empleado.setCodigoPostal(codigoPostal);
		empleado.setFechaAlta(new Timestamp(System.currentTimeMillis()));
		empleado.setLegajo(legajo);
		empleado.setSexo(sexo);
		empleado.setArea(area);
		empleado.setTelefono(telefono);
		empleado.setEmail(email);
		empleado.setProvincia(provincia);
		empleado.setActivo(true);
		empleado.setVehiculo(vehiculo);
		container.persistIfNotAlready(empleado);

		//Cambio el estado del vehiculo seleccionado a Asignado.
		if(vehiculo != null){
			vehiculo.getEstado().asignarVehiculo(vehiculo);
		}

		return empleado;
	}

	// Validar atributos N° Documento y Fecha Nacimiento
	public String validateCreateEmpleado(String nombre, String apellido,
			Documento tipoDocumento, int nroDocumento,
			LocalDate fechaNacimiento, Sexo sexo, Provincia provincia,
			Ciudad ciudad, int codigoPostal, String domicilio, String telefono,
			String email, String legajo, Vehiculo vehiculo, Area area) {
		if (!container.allMatches(
				new QueryDefault<Empleado>(Empleado.class, "Buscar_Documento",
						"nroDocumento", nroDocumento)).isEmpty()) {
			return "El número de Documento ya existe. Por favor vericar los datos ingresados.";
		}
		if (fechaNacimiento.isAfter(LocalDate.now())) {
			return "La fecha de nacimiento debe ser menor al día actual.";
		}
		if (!container.allMatches(
				new QueryDefault<Empleado>(Empleado.class, "Buscar_Legajo",
						"legajo", legajo)).isEmpty()) {
			return "El número de legajo ingresado ya existe. Por favor verificar los datos ingresados.";
		}
		if (area.isActivo() == false)
			return "El area ingresada se encuentra inactiva. Por favor ingrese otra area.";
		return null;
	}

	@MemberOrder(sequence = "5")
	@ActionLayout(named = "Buscar por N° de Documento")
	public List<Empleado> findByDocumento(
			@ParameterLayout(named = "N° de Documento") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) final int nroDocumento) {
		return filtrarPorActivos(container.allMatches(new QueryDefault<>(
				Empleado.class, "Buscar_Documento", "nroDocumento",
				nroDocumento)));
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por Nombre")
	public List<Empleado> findByNombre(
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) final String nombre) {
		final List<Empleado> listaEmpleado = listarTodos();
		final List<Empleado> lista = new ArrayList<Empleado>();
		for (Empleado e : listaEmpleado) {
			if (e.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				lista.add(e);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Nombre buscado");
		}
		return lista;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Apellido")
	public List<Empleado> findByApellido(
			@ParameterLayout(named = "Apellido") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) final String apellido) {
		final List<Empleado> listaEmpleado = listarTodos();
		final List<Empleado> lista = new ArrayList<Empleado>();
		for (Empleado e : listaEmpleado) {
			if (e.getApellido().toUpperCase().equals(apellido.toUpperCase())) {
				lista.add(e);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Apellido buscado");
		}
		return lista;
	}

	@MemberOrder(sequence = "2")
	public List<Empleado> listarTodos() {
		return filtrarPorActivos(container.allInstances(Empleado.class));
	}

	@MemberOrder(sequence = "6")
	@ActionLayout(named = "Buscar por Legajo")
	public List<Empleado> findByLegajo(
			@ParameterLayout(named = "N° de Legajo") final String legajo) {
		final List<Empleado> listaEmpleado = listarTodos();
		final List<Empleado> lista = new ArrayList<Empleado>();
		for (Empleado e : listaEmpleado) {
			if (e.getLegajo().toUpperCase().equals(legajo.toUpperCase())) {
				lista.add(e);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Legajo buscado");
		}
		return lista;
	}

	/**
	 * Cambia el listado de Ciudad a mostrar, según la Provincia seleccionada.
	 *
	 * @return List<Ciudad>
	 */
	public List<Ciudad> choices7CreateEmpleado(String nombre, String apellido,
			Documento tipoDocumento, int nroDocumento,
			LocalDate fechaNacimiento, Sexo sexo, Provincia provincia,
			Ciudad ciudad, int codigoPostal, String domicilio, String telefono,
			String email, String legajo, Vehiculo vehiculo, Area area) {
		return Ciudad.listarPor(provincia);
	}

	@MemberOrder(sequence = "1", name = "Elementos Inactivos")
	@ActionLayout(named="Empleados")
	public List<Empleado> listAllEmpleados() {
		List<Empleado> lista = container.allInstances(Empleado.class);
		List<Empleado> inactivos = new ArrayList<Empleado>();
		for (Empleado empleado : lista) {
			if (!empleado.isActivo())
				inactivos.add(empleado);
		}
		return inactivos;
	}

	/**
	 * Filtrar lista por Empleados activos.
	 *
	 * @param listaEmpleados
	 * @return Lista de Empleados activos.
	 */
	private List<Empleado> filtrarPorActivos(final List<Empleado> lista) {
		List<Empleado> activos = new ArrayList<Empleado>();
		for (Empleado empleado : lista) {
			if (empleado.isActivo())
				activos.add(empleado);
		}
		return activos;
	}

	@MemberOrder(sequence = "7")
	@ActionLayout(named = "Exportar Empleado")
	public String downloadAll() throws JRException, IOException {
		EmpleadoDataSource datasource = new EmpleadoDataSource();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		List<Empleado> listaEmpleado = container.allInstances(Empleado.class);
		for (Empleado e : listaEmpleado) {
			ReporteEmpleado empleado = new ReporteEmpleado();
			empleado.setLegajo(e.getLegajo().toString());
			empleado.setNombre(e.getNombre() + " " + e.getApellido());
			empleado.setTipoDocumento(e.getTipoDocumento().toString());
			empleado.setNroDocumento(Integer.toString(e.getNroDocumento()));
			empleado.setFechaNacimiento(e.getFechaNacimiento().toString());
			empleado.setProvincia(e.getProvincia().toString());
			empleado.setCiudad(e.getCiudad().toString());
			empleado.setDomicilio(e.getDomicilio());
			empleado.setCodigoPostal(Integer.toString(e.getCodigoPostal()));
			empleado.setFechaAlta(df.format(e.getFechaAlta()));
			empleado.setSexo(e.getSexo().toString());
			empleado.setTelefono(e.getTelefono());
			empleado.setEmail(e.getEmail());
			if (e.isActivo())
				empleado.setActivo("Activo");
			else
				empleado.setActivo("Inactivo");
			if (e.getVehiculo() != null)
				empleado.setVehiculo(e.getVehiculo().toString());
			else
				empleado.setVehiculo("No Asignado");
			empleado.setArea(e.getArea().toString());
			datasource.addParticipante(empleado);
		}
		File file = new File("Empleado.jrxml");
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
