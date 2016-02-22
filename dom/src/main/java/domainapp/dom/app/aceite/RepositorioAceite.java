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
    
package domainapp.dom.app.aceite;

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

import domainapp.dom.app.reporte.AceiteDataSource;
import domainapp.dom.app.reporte.ReporteAceite;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


@DomainService(repositoryFor = Aceite.class)
@DomainServiceLayout(menuOrder = "60", named="Aceites")
public class RepositorioAceite {
	@MemberOrder(sequence = "1")
	@ActionLayout(named="Crear nuevo Aceite")
	public Aceite createAceite(
			@ParameterLayout(named="Marca") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS)String marca,
			@ParameterLayout(named="Nombre")@Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS)String nombre,
			@ParameterLayout(named="Codigo")@Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS,optionality=Optionality.OPTIONAL)String codigo,
			@ParameterLayout(named="Descripción")@Parameter(optionality=Optionality.OPTIONAL)String descripcion,
			@ParameterLayout(named="Tipo de Aceite")TipoAceite tipoAceite,
			@ParameterLayout(named="Duración (km)")@Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS)int duracion){
		final Aceite aceite = container.newTransientInstance(Aceite.class);
		aceite.setCodigo(codigo);
		aceite.setDescripcion(descripcion);
		aceite.setDuracion(duracion);
		aceite.setFechaAlta(new Timestamp(System.currentTimeMillis()));
		aceite.setMarca(marca);
		aceite.setNombre(nombre);
		aceite.setTipoAceite(tipoAceite);
		aceite.setActivo(true);
		container.persistIfNotAlready(aceite);
		return aceite;
	}

	public String validateCreateAceite(final String marca, final String nombre, final String codigo,
										final String descripcion, final TipoAceite tipoAceite,
										final int duracion){
		List<Aceite> listaAceite=container.allInstances(Aceite.class);
		for (Aceite aceite : listaAceite){
			//Paso el nombre y la marca a mayusculas y lo comparo con los parametros ingresados tambien convertidos a mayusculas.
			String aceiteNombre=aceite.getNombre().toUpperCase();
			String aceiteMarca=aceite.getMarca().toUpperCase();
			if(aceiteNombre.equals(nombre.toUpperCase()) && aceiteMarca.equals(marca.toUpperCase()))
				return "El aceite ingresado ya existe. De no poder visualizarlo, verifique si se encuentra inactivo alctualmente y activelo.";
			if(codigo!=null)
				if(aceite.getCodigo()!=null){
					//Solo si el codigo ingresado, y el codigo de este aceite son distintos de null, los comparo transformados en mayusculas.
					String aceiteCodigo= aceite.getCodigo().toUpperCase();
					if (aceiteCodigo.equals(codigo.toUpperCase()))
						return "El Codigo ingresado, ya ha sido asignado a otro Aceite. Por favor, ingresar un codigo diferente.";
				}
		}
		return null;
	}

	@MemberOrder(sequence ="2")
	@ActionLayout(named="Listar Todos")
	public List<Aceite> listAll(){
		return container.allMatches(new QueryDefault<Aceite>(Aceite.class, "ListarTodos"));
	}

	@ActionLayout(named="Buscar por Marca")
	@MemberOrder(sequence ="3")
	public List<Aceite> findByMarca(
			@ParameterLayout(named="Marca") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS)String marca){
		final List<Aceite> listaAceite = listAll();
		final List<Aceite> lista = new ArrayList<Aceite>();
		for (Aceite a : listaAceite) {
			if (a.getMarca().toUpperCase().equals(marca.toUpperCase())) {
				lista.add(a);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe la Marca buscada");
		}
		return lista;
	}


	@ActionLayout(named="Buscar por Nombre")
	@MemberOrder(sequence="4")
	public List<Aceite> findByNombre(
			@ParameterLayout(named="Nombre") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombre){
		final List<Aceite> listaAceite = listAll();
		final List<Aceite> lista = new ArrayList<Aceite>();
		for (Aceite a : listaAceite) {
			if (a.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				lista.add(a);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Nombre buscado");
		}
		return lista;
	}

	@ActionLayout(named="Buscar por Codigo")
	@MemberOrder(sequence="5")
	public List<Aceite> findByCodigo(
			@ParameterLayout(named="Codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codigo){
		final List<Aceite> listaAceite = listAll();
		final List<Aceite> lista = new ArrayList<Aceite>();
		for (Aceite a : listaAceite) {
			if (a.getCodigo().toUpperCase().equals(codigo.toUpperCase())) {
				lista.add(a);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el codigo buscado");
		}
		return lista;
	}

	@ActionLayout(named = "Listar por Tipo de Aceite")
	@MemberOrder(sequence = "6")
	public List<Aceite> listTipoAceite(
			@ParameterLayout(named = "Tipo de Aceite") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) TipoAceite tipoAceite) {
		final List<Aceite> listaAceite = listAll();
		final List<Aceite> lista = new ArrayList<Aceite>();
		for (Aceite a : listaAceite) {
			if (a.getTipoAceite().equals(tipoAceite)) {
				lista.add(a);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Tipo de Aceite buscado");
		}
		return lista;
	}
	@MemberOrder(sequence="7")
	@ActionLayout(named="Exportar Aceites")
	public String downloadAll() throws JRException, IOException {
		AceiteDataSource datasource = new AceiteDataSource();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		for (Aceite a : listAll()) {
			ReporteAceite aceite = new ReporteAceite();
			aceite.setCodigo(a.getCodigo());
			aceite.setNombre(a.getNombre());
			aceite.setDescripcion(a.getDescripcion());
			aceite.setDuracion(Integer.toString(a.getDuracion()));
			aceite.setActivo(Boolean.toString(a.isActivo()));
			aceite.setFechaAlta(df.format(a.getFechaAlta()));
			aceite.setTipoAceite(a.getTipoAceite().toString());
			aceite.setMarca(a.getMarca());
			datasource.addParticipante(aceite);
		}
		File file = new File("Aceite.jrxml");
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
