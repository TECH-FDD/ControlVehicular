package domainapp.dom.app.taller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import domainapp.dom.app.reporte.ReporteTaller;
import domainapp.dom.app.reporte.TallerDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

@DomainService(repositoryFor = Taller.class)
@DomainServiceLayout(menuOrder = "100", named = "Taller")
public class RepositorioTaller {

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Taller")
	public Taller createTaller(
			final @ParameterLayout(named = "Nombre Comercial") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombreComercial,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String descripcion,
			final @ParameterLayout(named= "Rubro") Rubro rubro,
			final @ParameterLayout(named = "Direccion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String direccion,
			final @ParameterLayout(named = "telefono") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionTel.ADMITIDOS) String telefono,
			final @ParameterLayout(named = "Codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codigo,
			final @ParameterLayout(named = "Email") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionEmail.ADMITIDOS) String email) {

		final Taller taller = container.newTransientInstance(Taller.class);

		taller.setNombreComercial(nombreComercial);
		taller.setTelefono(telefono);
		taller.setDescripcion(descripcion);
		taller.setRubro(rubro);
		taller.setDireccion(direccion);
		taller.setEmail(email);
		taller.setCodigo(codigo);
		taller.setActivo(true);
		container.persistIfNotAlready(taller);
		return taller;

	}

	// Validar nombre comercial y codigo del taller
	public String validateCreateTaller(String nombreComercial,String descripcion,Rubro rubro,
			String direccion,String telefono,String codigo, String email) {
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
	public String downloadAll() throws JRException, IOException {
		TallerDataSource datasource = new TallerDataSource();
		for (Taller t : listAll()) {
			ReporteTaller taller = new ReporteTaller();
			taller.setCodigo(t.getCodigo());
			taller.setNombreComercial(t.getNombreComercial());
			taller.setDescripcion(t.getDescripcion());
			taller.setRubro(t.getRubro().toString());
			taller.setDireccion(t.getDireccion());
			taller.setTelefono(t.getTelefono());
			taller.setEmail(t.getEmail());
			datasource.addParticipante(taller);
		}
		File file = new File("Taller.jrxml");
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
