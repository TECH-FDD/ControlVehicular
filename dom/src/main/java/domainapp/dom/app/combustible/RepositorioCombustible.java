package domainapp.dom.app.combustible;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.DomainServiceLayout.MenuBar;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.combustible.Combustible;
import domainapp.dom.app.combustible.TipoCombustible;
import domainapp.dom.app.reporte.CombustibleDataSource;
import domainapp.dom.app.reporte.ReporteCombustible;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

@DomainService(repositoryFor = Combustible.class)
@DomainServiceLayout(menuOrder = "40.1", named = "Combustible", menuBar = MenuBar.PRIMARY)
public class RepositorioCombustible {

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear combustible")
	public Combustible createCombustible(

			final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) String nombre,
			final @ParameterLayout(named = "Empresa") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 30) String empresa,
			final @ParameterLayout(named = "Inicial") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 20) String codigo,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 100) String descripcion,
			final @ParameterLayout(named = "Categoria") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 100) String categoria,
			final @ParameterLayout(named = "Precio/litro") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) BigDecimal precioLitro,
			final @ParameterLayout(named = "Precio anterior") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) BigDecimal precioAnterior,
			final @ParameterLayout(named = "Porcentaje de aumento") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) BigDecimal porceAumento,
			final @ParameterLayout(named = "Octanaje") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) int octanaje,
			final @ParameterLayout(named = "Tipo de combustible") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) TipoCombustible tipoCombustible) {

		final Combustible combustible = container
				.newTransientInstance(Combustible.class);

		combustible.setNombre(nombre);
		combustible.setEmpresa(empresa);
		combustible.setCodigo(codigo);
		combustible.setDescripcion(descripcion);
		combustible.setCategoria(categoria);
		combustible.setPrecioLitro(precioLitro);
		combustible.setPrecioAnterior(precioAnterior);
		combustible.setPorcentajeAumento(porceAumento);
		combustible.setOctanaje(octanaje);
		combustible.setTipoCombustible(tipoCombustible);
		combustible.setActivo(true);

		container.persistIfNotAlready(combustible);
		return combustible;
	}

	// Validacion de nombre de combustible y codigo
	public String validateCreateCombustible(String nombre, String empresa,
			String codigo, String descripcion, String categoria,
			BigDecimal precio_litro, BigDecimal precio_anterior,
			BigDecimal porce_aumento, int octanaje,
			TipoCombustible tipo_combustible) {
		if (!container.allMatches(
				new QueryDefault<Combustible>(Combustible.class,
						"buscarPorNombre", "nombre", nombre.toUpperCase()))
				.isEmpty()) {

			return "El nombre ya existe. Por favor verifique datos ingresados.";
		}
		if (!container.allMatches(
				new QueryDefault<Combustible>(Combustible.class,
						"buscarPorCodigo", "codigo", codigo.toUpperCase()))
				.isEmpty()) {
			return "El codigo ingresado ya exite! Por favor verifique los datos ingresados.";
		}
		return null;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<Combustible> listAll() {
		final List<Combustible> listaCombustible = this.container
				.allMatches(new QueryDefault<Combustible>(Combustible.class,
						"ListarTodos"));
		if (listaCombustible.isEmpty()) {
			this.container
					.warnUser("No hay combustibles cargados en el sistema");
		}
		return listaCombustible;
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por nombre")
	public List<Combustible> findByNombre(
			@ParameterLayout(named = "Nombre") final String nombre) {

		final List<Combustible> listaCombustible = listAll();
		final List<Combustible> lista = new ArrayList<Combustible>();
		for (Combustible c : listaCombustible) {
			if (c.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				lista.add(c);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Nombre buscado");
		}
		return lista;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por codigo")
	public List<Combustible> findByCodigo(
			@ParameterLayout(named = "Codigo") final String codigo) {
		final List<Combustible> listaCombustible = listAll();
		final List<Combustible> lista = new ArrayList<Combustible>();
		for (Combustible c : listaCombustible) {
			if (c.getCodigo().toUpperCase().equals(codigo.toUpperCase())) {
				lista.add(c);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el codigo buscado");
		}
		return lista;
	}

	@MemberOrder(sequence = "2", name = "Elementos Inactivos")
	@ActionLayout(named = "Combustibles")
	public List<Combustible> listAllCombustibles() {
		List<Combustible> lista = this.container
				.allMatches(new QueryDefault<Combustible>(Combustible.class,
						"ListarInactivos"));
		return lista;
	}
	@MemberOrder(sequence="5")
	@ActionLayout(named="Exportar Combustibles")
	public String downloadAll() throws JRException, IOException {
		CombustibleDataSource datasource = new CombustibleDataSource();
		for (Combustible c : listAll()) {
			ReporteCombustible combustible= new ReporteCombustible();
			combustible.setCodigo(c.getCodigo());
			combustible.setNombre(c.getNombre());
			combustible.setDescripcion(c.getDescripcion());
			combustible.setCategoria(c.getCategoria());
			combustible.setEmpresa(c.getEmpresa());
			combustible.setOctanaje(Integer.toString(c.getOctanaje()));
			combustible.setPrecioLitro(c.getPrecioLitro().toString());
			combustible.setPrecioAnterior(c.getPrecioAnterior().toString());
			combustible.setPorcentajeAumento(c.getPorcentajeAumento().toString());
			combustible.setTipoCombustible(c.getTipoCombustible().toString());
			if(c.isActivo())
				combustible.setActivo("Activo");
			else
				combustible.setActivo("Inactivo");
			datasource.addParticipante(combustible);
		}
		File file = new File("Combustible.jrxml");
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		JasperDesign jd = JRXmlLoader.load(input);
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
		JasperViewer.viewReport(jasperPrint, true);
		return "Reporte Generado";
	}
	@javax.inject.Inject
	DomainObjectContainer container;
}