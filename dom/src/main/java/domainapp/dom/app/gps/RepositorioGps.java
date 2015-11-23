package domainapp.dom.app.gps;

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
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.estadoelemento.Activo;
import domainapp.dom.app.estadoelemento.Asignado;
import domainapp.dom.app.estadoelemento.Baja;
import domainapp.dom.app.estadoelemento.Inactivo;
import domainapp.dom.app.estadoelemento.NecesitaReparacion;
import domainapp.dom.app.estadoelemento.Reparacion;
import domainapp.dom.app.reporte.GpsDataSource;
import domainapp.dom.app.reporte.ReporteGps;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

@DomainService(repositoryFor = Gps.class)
@DomainServiceLayout(menuOrder = "50", named = "Gps")
public class RepositorioGps {
	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear nuevo Gps")
	public Gps createGps(
			final @ParameterLayout(named = "Codigo Identificacion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codIdentificacion,
			final @ParameterLayout(named = "Modelo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String modelo,
			final @ParameterLayout(named = "Marca") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String marca,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String descripcion,
			final @ParameterLayout(named = "Fecha Asignacion Vehiculo") Timestamp fechaAsigVehiculo,
			final @ParameterLayout(named = "observacion Estado Dispositivo ") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String obsEstadoDisp) {

		final Gps gps = new Gps(codIdentificacion, marca, modelo,
				descripcion, new Timestamp(System.currentTimeMillis()),
				fechaAsigVehiculo, obsEstadoDisp);

		container.persistIfNotAlready(gps);
		return gps;
	}

	// Validar atributos Codigo Identificación
	public String validateCreateGps(String codIdentificacion, String modelo,
			String marca, String descripcionString,
			Timestamp fechaAsigVehiculo, String ObsEstadoDispositivo) {
		if (!container.allMatches(
				new QueryDefault<Gps>(Gps.class,
						"buscarPorCodigoIdentificacion", "codIdentificacion",
						codIdentificacion)).isEmpty()) {
			return "El código de Identificación ya existe. Por favor verificar los Datos Ingresados.";
		}
		return null;
	}

	@MemberOrder(sequence = "2")
	public List<Gps> listarTodos() {
		return gpsActivos(container.allInstances(Gps.class));
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por Modelo")
	public List<Gps> findByModelo(
			@ParameterLayout(named = "Modelo Gps") final String modelo) {
		final List<Gps> listaGps = listarTodos();
		final List<Gps> lista = new ArrayList<Gps>();
		for (Gps g : listaGps) {
			if (g.getModelo().toUpperCase().equals(modelo.toUpperCase())) {
				lista.add(g);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Modelo buscado");
		}
		return lista;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Marca")
	public List<Gps> findByMarca(
			@ParameterLayout(named = "Marca Gps") final String marca) {
		final List<Gps> listaGps = listarTodos();
		final List<Gps> lista = new ArrayList<Gps>();
		for (Gps g : listaGps) {
			if (g.getMarca().toUpperCase().equals(marca.toUpperCase())) {
				lista.add(g);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe la Marca buscada");
		}
		return lista;
	}

	@MemberOrder(sequence = "5")
	@ActionLayout(named = "Buscar por Codigo Identificacion")
	public List<Gps> findByCodIdentificacion(
			@ParameterLayout(named = "Codigo Identificacacion")final String codIdentificacion) {
		final List<Gps> listaGps = listarTodos();
		final List<Gps> lista = new ArrayList<Gps>();
		for (Gps g : listaGps) {
			if (g.getCodIdentificacion().toUpperCase().equals(codIdentificacion.toUpperCase())) {
				lista.add(g);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Codigo de Identificación buscado");
		}
		return lista;
	}

	@MemberOrder(sequence = "3", name="Elementos Inactivos")
	@ActionLayout(named = "Gps")
	public List<Gps> listGpsInactivos(){
		List<Gps> lista=container.allInstances(Gps.class);
		List<Gps> inactivos= new ArrayList<Gps>();
		for (Gps gps : lista){
			if ((gps.getEstado() instanceof Inactivo ||
				gps.getEstado() instanceof NecesitaReparacion ||
				gps.getEstado() instanceof Reparacion))
				inactivos.add(gps);
		}
		return inactivos;
	}

	/**
	 * Filtrar lista de Gps, por activos.
	 * @param lista
	 * @return lista de Gps en estado Activo.
	 */
	@Programmatic
	public List<Gps> gpsNoAsignados(final List<Gps> lista){
		List<Gps> noAsignados= new ArrayList<Gps>();
		for (Gps gps : lista){
			if (gps.getEstado() instanceof Activo)
				noAsignados.add(gps);
		}
		return noAsignados;
	}

	/**
	 * Filtrar lista de Gps, por activos y asignados.
	 * @param lista
	 * @return lista de Gps en estado Activo y asignados.
	 */
	private List<Gps> gpsActivos(final List<Gps> lista){
		List<Gps> aa= new ArrayList<Gps>();
		for (Gps gps : lista){
			if (gps.getEstado() instanceof Activo ||
					gps.getEstado() instanceof Asignado)
				aa.add(gps);
		}
		return aa;
	}

	@MemberOrder(sequence = "1", name="Elementos Desestimados")
	@ActionLayout(named = "Gps")
	public List<Gps> listBaja(){
		List<Gps> lista = container.allInstances(Gps.class);
		List<Gps> bajas= new ArrayList<Gps>();
		for (Gps gps : lista){
			if (gps.getEstado() instanceof Baja)
				bajas.add(gps);
		}
		return bajas;
	}
	@ActionLayout(named="Exportar Gps")
	public String downloadAll() throws JRException, IOException {
		GpsDataSource datasource = new GpsDataSource();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		for (Gps g : listarTodos()) {
			ReporteGps gps= new ReporteGps();
			gps.setCodIdentificacion(g.getCodIdentificacion());
			gps.setMarca(g.getMarca());
			gps.setDescripcion(g.getDescripcion());
			gps.setEstado(g.getEstado().toString());
			gps.setFechaAlta(df.format(g.getFechaAlta()));
			gps.setFechaAsigVehiculo(df.format(g.getFechaAsigVehiculo()));
			gps.setModelo(g.getModelo());
			gps.setObsEstadoDispositivo(g.getObsEstadoDispositivo());
			datasource.addParticipante(gps);
		}
		File file = new File("Gps.jrxml");
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
