package domainapp.dom.app.taller;

import java.io.IOException;
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
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.reporte.Formato;
import domainapp.dom.app.reporte.GenerarReporte;
import domainapp.dom.app.reporte.ReporteTaller;
import net.sf.jasperreports.engine.JRException;

@DomainService(repositoryFor = Taller.class)
@DomainServiceLayout(menuOrder = "100", named = "Taller")
public class RepositorioTaller {

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Taller")
	public Taller createTaller(
			final @ParameterLayout(named = "Nombre Comercial") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombreComercial,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String descripcion,
			final @ParameterLayout(named = "Direccion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String direccion,
			final @ParameterLayout(named = "telefono") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionTel.ADMITIDOS) String telefono,
			final @ParameterLayout(named = "Codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codigo,
			final @ParameterLayout(named = "Email") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionEmail.ADMITIDOS) String email) {

		final Taller taller = container.newTransientInstance(Taller.class);

		taller.setNombreComercial(nombreComercial);
		taller.setTelefono(telefono);
		taller.setDescripcion(descripcion);
		taller.setDireccion(direccion);
		taller.setEmail(email);
		taller.setCodigo(codigo);
		container.persistIfNotAlready(taller);
		return taller;

	}

	// Validar nombre comercial y codigo del taller
	public String validateCreateTaller(String nombreComercial, String telefono,
			String descripcion, String codigo, String direccion, String email) {
		if (!container.allMatches(
				new QueryDefault<Taller>(Taller.class,
						"buscarPorNombreComercial", "nombreComercial",
						nombreComercial.toUpperCase())).isEmpty()) {
			return "El nombre comercial ya existe. Por favor verificar los Datos Ingresados.";
		}
		if (!container.allMatches(
				new QueryDefault<Taller>(Taller.class, "buscarPorCodigo",
						"codigo", codigo.toUpperCase())).isEmpty()) {
			return "El Codigo ya existe. Por favor verificar los Datos Ingresados.";
		}

		return null;
	}
	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<Taller> listAll() {
		List<Taller> lista = this.container
				.allMatches(new QueryDefault<Taller>(Taller.class,
						"ListarTodos"));
		if (lista.isEmpty()) {
			this.container.warnUser("No hay talleres cargados en el sistema");
		}
		return lista;
	}
	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por Nombre Comercial")
	public List<Taller> findByNombreComercial(
			@ParameterLayout(named = "Nombre Comercial") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String nombreComercial) {
		final List<Taller> listaTaller = listAll();
		final List<Taller> lista = new ArrayList<Taller>();
		for (Taller t : listaTaller) {
			if (t.getNombreComercial().toUpperCase().equals(nombreComercial.toUpperCase())) {
				lista.add(t);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Nombre Comercial");
		}
		return lista;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Direccion")
	public List<Taller> findByDireccion(
			@ParameterLayout(named = "Direccion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String direccion) {
		final List<Taller> listaTaller = listAll();
		final List<Taller> lista = new ArrayList<Taller>();
		for (Taller t : listaTaller) {
			if (t.getDireccion().toUpperCase().equals(direccion.toUpperCase())) {
				lista.add(t);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe la Direccion");
		}
		return lista;
	}

	@MemberOrder(sequence = "5")
	@ActionLayout(named = "Buscar por Codigo")
	public List<Taller> findByCodigo(
			@ParameterLayout(named = "Codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String codigo) {
		final List<Taller> listaTaller = listAll();
		final List<Taller> lista = new ArrayList<Taller>();
		for (Taller t : listaTaller) {
			if (t.getCodigo().toUpperCase().equals(codigo.toUpperCase())) {
				lista.add(t);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Codigo buscado");
		}
		return lista;
	}
	@MemberOrder(sequence="6")
	@ActionLayout(named="Exportar Talleres")
	public String elegirFormato(Formato formato) throws JRException, IOException {
		return exportarTodo(formato);
	}

	@Programmatic
	public Formato default0ElegirFormato(final @ParameterLayout(named = "Formato") Formato formato) {
		return Formato.PDF;
	}

	@Programmatic
	public String exportarTodo(Formato formato) throws JRException, IOException {
		List<Object> objectsReport = new ArrayList<Object>();
		for (Taller t : listAll()) {
			ReporteTaller taller= new ReporteTaller();
			taller.setCodigo(t.getCodigo());
			taller.setNombreComercial(t.getNombreComercial());
			taller.setDescripcion(t.getDescripcion());
			taller.setEmail(t.getEmail());
			taller.setTelefono(t.getTelefono());
			taller.setDireccion(t.getDireccion());
			objectsReport.add(taller);
		}
		if (objectsReport.isEmpty() == false) {
			String nombreArchivo = null;
			if (formato == Formato.PDF)
				nombreArchivo = "ReporteTaller/PDF/Talleres "
						+ new Date(System.currentTimeMillis());

			GenerarReporte.generarReporte("Talleres.jrxml", objectsReport, formato, nombreArchivo);
			return "Se ha realizado la exportacion Correctamente";
		} else
			return "No hay elementos para imprimir";
	}


	@javax.inject.Inject
	DomainObjectContainer container;
}
