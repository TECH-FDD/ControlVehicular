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
    
package domainapp.dom.app.cargaCombustible;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
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
import org.joda.time.LocalDate;

import domainapp.dom.app.combustible.Combustible;
import domainapp.dom.app.combustible.RepositorioCombustible;
import domainapp.dom.app.combustible.TipoCombustible;
import domainapp.dom.app.reporte.CargaCombustibleDataSource;
import domainapp.dom.app.reporte.ReporteCargaCombustible;
import domainapp.dom.app.vehiculo.Vehiculo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

@DomainService(repositoryFor = CargaCombustible.class)
@DomainServiceLayout(menuOrder = "40", named = "Carga Combustible")
public class RepositorioCargaCombustible {
	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Carga combustible")
	public CargaCombustible createCargaCombustible(final @ParameterLayout(named = "Vehiculo") Vehiculo vehiculo,
			final @ParameterLayout(named= "Tipo Combustible") TipoCombustible tipoCombustible,
			final @ParameterLayout(named = "Combustible") Combustible combustible,
			final @ParameterLayout(named = "Litros Cargados") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) Double litrosCargados) {

		BigDecimal costoTotal = (combustible.getPrecioLitro()).multiply(BigDecimal.valueOf(litrosCargados));

		final CargaCombustible cargaCombustible = new CargaCombustible(vehiculo, new Date(System.currentTimeMillis()),
				combustible, costoTotal, litrosCargados);
		container.persistIfNotAlready(cargaCombustible);
		return cargaCombustible;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<CargaCombustible> listAll() {
		final List<CargaCombustible> listaCargaCombustible = this.container
				.allMatches(new QueryDefault<CargaCombustible>(CargaCombustible.class,
						"ListarTodos"));
		if (listaCargaCombustible.isEmpty()) {
			this.container
					.warnUser("No hay cargas de combustible cargados en el sistema");
		}
		return listaCargaCombustible;
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por vehiculo")
	public List<CargaCombustible> findByVehiculo(
			@ParameterLayout(named = "Vehiculo") final Vehiculo vehiculo) {

		final List<CargaCombustible> listaCargaCombustible = listAll();
		final List<CargaCombustible> lista = new ArrayList<CargaCombustible>();
		for (CargaCombustible cc : listaCargaCombustible) {
			if (cc.getVehiculo().equals(vehiculo)) {
				lista.add(cc);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Vehiculo buscado");
		}
		return lista;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Fecha")
	public List<CargaCombustible> findByFechaCarga(@ParameterLayout(named = "Fecha Carga") final LocalDate fecha) {
		List<CargaCombustible> listaCargaCombustible = container.allInstances(CargaCombustible.class);
		List<CargaCombustible> listaCarga = new ArrayList<CargaCombustible>();
		for (CargaCombustible cargaCombustible: listaCargaCombustible) {
			LocalDate ld = LocalDate.fromDateFields(cargaCombustible.getFechaCarga());
			if (ld.compareTo(fecha) == 0) {
				listaCarga.add(cargaCombustible);
			}
		}
		if (listaCarga.isEmpty()) {
			this.container.warnUser("No existe la fecha buscada");
		}
		return listaCarga;
	}

	/**
	 * Cambia el listado de combustible a mostrar, según la el tipo
	 * seleccionada.
	 *
	 * @return List<Combustible>
	 */
	public List<Combustible> choices2CreateCargaCombustible(Vehiculo vehiculo, TipoCombustible tipoCombustible,
			Combustible combustible, Double litrosCargados) {
		return listarPor(tipoCombustible);
	}

	@Programmatic
	public List<Combustible> listarPor(final TipoCombustible tipoCombustible) {
		List<Combustible> lista = repoCombustible.listAll();
		List<Combustible> listaPor = new ArrayList<Combustible>();
		for (Combustible c : lista) {
			if (c.getTipoCombustible().equals(tipoCombustible)) {
				listaPor.add(c);
			}
		}
		return listaPor;
	}
	@MemberOrder(sequence="7")
	@ActionLayout(named="Exportar Cargas Combustible")
	public String downloadAll() throws JRException, IOException {
		CargaCombustibleDataSource datasource = new CargaCombustibleDataSource();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		for (CargaCombustible cc : listAll()) {
			ReporteCargaCombustible cargaCombustible = new ReporteCargaCombustible();
			cargaCombustible.setCombustible(cc.getCombustible().getNombre());
			cargaCombustible.setVehiculo(cc.getVehiculo().toString());
			cargaCombustible.setCostoTotal(cc.getCostoTotal().toString());
			cargaCombustible.setFechaCarga(df.format(cc.getFechaCarga()));
			cargaCombustible.setLitrosCargados(cc.getLitrosCargados().toString());
			datasource.addParticipante(cargaCombustible);
		}
		File file = new File("CargaCombustible.jrxml");
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
	@javax.inject.Inject
	RepositorioCombustible repoCombustible;
}
