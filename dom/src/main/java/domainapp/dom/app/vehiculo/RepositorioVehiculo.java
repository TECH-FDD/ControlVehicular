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
    
package domainapp.dom.app.vehiculo;

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

import domainapp.dom.app.aceite.TipoAceite;
import domainapp.dom.app.combustible.TipoCombustible;
import domainapp.dom.app.estadoelemento.Activo;
import domainapp.dom.app.estadoelemento.Asignado;
import domainapp.dom.app.estadoelemento.Baja;
import domainapp.dom.app.estadoelemento.Inactivo;
import domainapp.dom.app.estadoelemento.NecesitaReparacion;
import domainapp.dom.app.estadoelemento.Reparacion;
import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.gps.RepositorioGps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.matafuego.RepositorioMatafuego;
import domainapp.dom.app.reporte.ReporteVehiculo;
import domainapp.dom.app.reporte.VehiculoDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

@DomainService(repositoryFor = Vehiculo.class)
@DomainServiceLayout(menuOrder = "80", named = "Vehiculo")
public class RepositorioVehiculo {

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear nuevo Vehiculo")
	public Vehiculo createVehiculo(
			final @ParameterLayout(named = "Marca") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String marca,
			final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String nombre,
			final @ParameterLayout(named = "Modelo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) Integer modelo,
			final @ParameterLayout(named = "Fecha Compra") Timestamp fechaCompra,
			final @ParameterLayout(named = "Patente") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String patente,
			final @ParameterLayout(named = "Numero de Chasis") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String numeroChasis,
			final @ParameterLayout(named = "Poliza/Seguro") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) Integer polizaSeguro,
			final @ParameterLayout(named = "Gps") Gps gps,
			final @ParameterLayout(named = "Tipo de Combustible") TipoCombustible tipoCombustible,
			final @ParameterLayout(named = "Capacidad Tanque de comb.") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS, optionality = Optionality.OPTIONAL) Integer capTanqueCombustible,
			final @ParameterLayout(named = "Tipo de Aceite") TipoAceite tipoAceite,
			final @ParameterLayout(named = "cns Combustible Ruta") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String cnsCombustibleRuta,
			final @ParameterLayout(named = "cns Combustible Ciudad") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String cnsCombustibleCiudad,
			final @ParameterLayout(named = "Matafuego") Matafuego matafuego,
			final @ParameterLayout(named = "Kilometros") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS, optionality = Optionality.OPTIONAL) Integer kilometros) {

		final Vehiculo vehiculo = new Vehiculo(marca, nombre, modelo, fechaCompra, patente, numeroChasis, polizaSeguro,
				gps, tipoCombustible, capTanqueCombustible, tipoAceite, cnsCombustibleRuta, cnsCombustibleCiudad,
				kilometros, matafuego);

		// Pasar el Gps al estado Asignado
		gps.getEstado().asignar(gps);

		// Pasar el Matafuego al estado Asignado
		matafuego.getEstado().asignar(matafuego);

		container.persistIfNotAlready(vehiculo);
		return vehiculo;
	}

	/**
	 * Validar el ingreso de los campos unicos, al dar de alta un Vehiculo.
	 *
	 * @return Mensaje de error.
	 */
	public String validateCreateVehiculo(String marca, String nombre, Integer modelo, Timestamp fechaCompra,
			String patente, String nroChasis, Integer poliza, Gps gps, TipoCombustible tipoCombustible, Integer tanque,
			TipoAceite tipoAceite, String consumoRuta, String consumoCiudad, Matafuego matafuego, Integer kilometro) {

		List<Vehiculo> lista = listAll();
		for (Vehiculo v : lista) {
			if (v.getGps().equals(gps))
				return "El Gps ingresado, ya se encuetra asignado al Vehiculo:" + v.toString();
			if (v.getMatafuego().equals(matafuego))
				return "El Matafuego ingresado, ya se encuentra asignado al Vehiculo:" + v.toString();
			if (v.getNumeroChasis() == nroChasis.toUpperCase())
				return "El Número de Chasis ingresado, pertenece al Vehiculo:" + v.toString();
			if (v.getPatente() == patente.toUpperCase())
				return "La Patente Ingresada, pertenece al Vehiculo:" + v.toString();
		}
		return null;
	}

	/**
	 * Mostrar solo Gps Activos.
	 *
	 * @return List<Gps> activos
	 */
	public List<Gps> choices7CreateVehiculo(String marca, String nombre, Integer modelo, Timestamp fechaCompra,
			String patente, String nroChasis, Integer poliza, Gps gps, TipoCombustible tipoCombustible, Integer tanque,
			TipoAceite tipoAceite, String consumoRuta, String consumoCiudad, Matafuego matafuego, Integer kilometro) {
		List<Gps> lista = repoGps.gpsNoAsignados(container.allInstances(Gps.class));
		return lista;
	}

	/**
	 * Mostrar solo Matafuegos Activos
	 *
	 * @return List<Matafuego> activos.
	 */
	public List<Matafuego> choices13CreateVehiculo(String marca, String nombre, Integer modelo, Timestamp fechaCompra,
			String patente, String nroChasis, Integer poliza, Gps gps, TipoCombustible tipoCombustible, Integer tanque,
			TipoAceite tipoAceite, String consumoRuta, String consumoCiudad, Matafuego matafuego, Integer kilometro) {
		return repoMatafuego.noAsignados(container.allInstances(Matafuego.class));
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar Todos")
	public List<Vehiculo> listAll() {
		return activos(container.allInstances(Vehiculo.class));
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por Marca")
	public List<Vehiculo> findByMarca(
			@ParameterLayout(named = "Marca") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String marca) {
		final List<Vehiculo> listaVehiculo = listAll();
		final List<Vehiculo> lista = new ArrayList<Vehiculo>();
		for (Vehiculo v : listaVehiculo) {
			if (v.getMarca().toUpperCase().equals(marca.toUpperCase())) {
				lista.add(v);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe la Marca");
		}
		return lista;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Nombre")
	public List<Vehiculo> findByNombre(
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) final String nombre) {
		final List<Vehiculo> listaVehiculo = listAll();
		final List<Vehiculo> lista = new ArrayList<Vehiculo>();
		for (Vehiculo v : listaVehiculo) {
			if (v.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
				lista.add(v);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Nombre");
		}
		return lista;
	}

	@MemberOrder(sequence = "5")
	@ActionLayout(named = "Buscar por Modelo")
	public List<Vehiculo> findByModelo(
			@ParameterLayout(named = "Modelo")final Integer modelo) {
		return activos(container.allMatches(new QueryDefault<>(Vehiculo.class, "BuscarModelo", "modelo", modelo)));
	}

	@MemberOrder(sequence = "6")
	@ActionLayout(named = "Buscar por Patente")
	public List<Vehiculo> findByPatente(
			@ParameterLayout(named = "Patente") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String patente) {
		final List<Vehiculo> listaVehiculo = listAll();
		final List<Vehiculo> lista = new ArrayList<Vehiculo>();
		for (Vehiculo v : listaVehiculo) {
			if (v.getPatente().toUpperCase().equals(patente.toUpperCase())) {
				lista.add(v);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe la Patente");
		}
		return lista;
	}

	@MemberOrder(sequence = "7")
	@ActionLayout(named = "Buscar por N° Chasis")
	public List<Vehiculo> findByNumeroChasis(
			@ParameterLayout(named = "Numero Chasis") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String numeroChasis) {
		final List<Vehiculo> listaVehiculo = listAll();
		final List<Vehiculo> lista = new ArrayList<Vehiculo>();
		for (Vehiculo v : listaVehiculo) {
			if (v.getNumeroChasis().toUpperCase().equals(numeroChasis.toUpperCase())) {
				lista.add(v);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Numero de Chasis");
		}
		return lista;
	}

	/**
	 * Filtrar lista de Vehiculo, por estado Activo.
	 *
	 * @param lista
	 * @return lista de Vehiculo Activos.
	 */
	private List<Vehiculo> activos(List<Vehiculo> lista) {
		List<Vehiculo> activos = new ArrayList<Vehiculo>();
		for (Vehiculo v : lista) {
			if (v.getEstado() instanceof Activo || v.getEstado() instanceof Asignado)
				activos.add(v);
		}
		return activos;
	}

	/**
	 * Filtrar lista de Vehiculo, por no Asignados.
	 *
	 * @param lista
	 * @return lista de Vehiculo no Asignados.
	 */
	@Programmatic
	public List<Vehiculo> noAsignados(List<Vehiculo> lista) {
		List<Vehiculo> noAsignados = new ArrayList<Vehiculo>();
		for (Vehiculo v : lista) {
			if (v.getEstado() instanceof Activo)
				noAsignados.add(v);
		}
		return noAsignados;
	}

	@MemberOrder(sequence = "5", name = "Elementos Inactivos")
	@ActionLayout(named = "Vehiculo")
	public List<Vehiculo> listInactivos() {
		List<Vehiculo> lista = container.allInstances(Vehiculo.class);
		List<Vehiculo> inactivos = new ArrayList<Vehiculo>();
		for (Vehiculo vehiculo : lista) {
			if ((vehiculo.getEstado() instanceof Inactivo || vehiculo.getEstado() instanceof NecesitaReparacion
					|| vehiculo.getEstado() instanceof Reparacion))
				inactivos.add(vehiculo);
		}
		return inactivos;
	}

	@MemberOrder(sequence = "3", name = "Elementos Desestimados")
	@ActionLayout(named = "Vehiculo")
	public List<Vehiculo> listBaja() {
		List<Vehiculo> lista = container.allInstances(Vehiculo.class);
		List<Vehiculo> bajas = new ArrayList<Vehiculo>();
		for (Vehiculo vehiculo : lista) {
			if (vehiculo.getEstado() instanceof Baja)
				bajas.add(vehiculo);
		}
		return bajas;
	}

	@MemberOrder(sequence = "8")
	@ActionLayout(named = "Exportar Vehiculo")
	public String downloadAll() throws JRException, IOException {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		VehiculoDataSource datasource = new VehiculoDataSource();
		for (Vehiculo v : listAll()) {
			ReporteVehiculo vehiculo = new ReporteVehiculo();
			String fechaCompra = df.format(v.getFechaCompra());
			vehiculo.setNombre(v.getMarca() + ", " + v.getNombre());
			vehiculo.setModelo(v.getModelo().toString());
			vehiculo.setFechaCompra(fechaCompra);
			vehiculo.setNumeroChasis(v.getNumeroChasis());
			vehiculo.setPatente(v.getPatente());
			vehiculo.setPolizaSeguro(v.getPolizaSeguro().toString());
			vehiculo.setEstado(v.getEstado().toString());
			vehiculo.setCapacTanqueCombustible(v.getCapacTanqueCombustible().toString());
			vehiculo.setCnsCombustibleCiudad(v.getCnsCombuestibleCiudad());
			vehiculo.setCnsCombustibleRuta(v.getCnsCombustibleRuta());
			vehiculo.setGps(v.getGps().toString());
			vehiculo.setMatafuego(v.getMatafuego().toString());
			vehiculo.setKilometros(v.getKilometros().toString());
			vehiculo.setTipoAceite(v.getTipoAceite().toString());
			vehiculo.setTipoCombustible(v.getTipoCombustible().toString());
			datasource.addParticipante(vehiculo);
		}
		File file = new File("Vehiculos.jrxml");
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
	RepositorioGps repoGps;
	@javax.inject.Inject
	RepositorioMatafuego repoMatafuego;
}
