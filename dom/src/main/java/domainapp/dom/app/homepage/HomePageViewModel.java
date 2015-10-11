/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package domainapp.dom.app.homepage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.ViewModel;

import domainapp.dom.app.alerta.AlertaMatafuego;
import domainapp.dom.app.alerta.AlertaVehiculo;
import domainapp.dom.app.alerta.RepositorioAlertaMatafuego;
import domainapp.dom.app.alerta.RepositorioAlertaVehiculo;
import domainapp.dom.app.reporte.Formato;
import net.sf.jasperreports.engine.JRException;

@ViewModel
public class HomePageViewModel {

	// region > title
	public String title() {
		return (getAlertasMatafuego().size() + getAlertasVehiculo().size()) + " Alertas";
	}
	// endregion

	// region > object (collection)
	@HomePage
	public List<AlertaMatafuego> getAlertasMatafuego() {
		return repositorioAlertaMatafuego.listAllMatafuego();
	}
	@HomePage
	public List<AlertaVehiculo> getAlertasVehiculo() {
		return repositorioAlertaVehiculo.listAllVehiculo();
	}

	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(describedAs = "El documento se almacenara en ReporteAlertas/AlertasMatafuego")
	public String exportarTodo(@ParameterLayout(named = "Formato") Formato formato) throws JRException, IOException {
		if (getAlertasMatafuego().isEmpty())
			return "No hay elementos para Exportar";
		else {
			repositorioAlertaMatafuego.elegirFormato(formato);
			return "Se ha realizado la Exportación correctamente";
		}
	}

	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(describedAs = "El documento se almacenara en ReporteAlertas/AlertasMatafuego")
	public String exportarPorPeriodo(@ParameterLayout(named = "Formato") Formato formato,
			@ParameterLayout(named = "Desde") Date desde, @ParameterLayout(named = "Hasta") Date hasta)
					throws JRException, IOException {
		if (getAlertasMatafuego().isEmpty())
			return "No hay elementos para Exportar";
		else {
			repositorioAlertaMatafuego.elegirFormato(desde, hasta, formato);
			return "Se ha realizado la Exportación correctamente";
		}
	}
	@javax.inject.Inject
	RepositorioAlertaMatafuego repositorioAlertaMatafuego;
	@javax.inject.Inject
	RepositorioAlertaVehiculo repositorioAlertaVehiculo;

	// endregion
}
