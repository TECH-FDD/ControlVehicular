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
    
package domainapp.fixture;

import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;

import domainapp.dom.app.alerta.AlertaVehiculo;
import domainapp.dom.app.alerta.RepositorioAlertaVehiculo;
import domainapp.dom.app.vehiculo.Vehiculo;

public class AlertaVehiculoFixture extends Fixture{
	private static String nombre= "Cambio de Aceite,Cambio de filtro de Aceite,Cambio de filtro de Aire, Cambio de Correa";
	private static String descripcion = "";
	private static String contadorAlerta="10000,20000,10000,20000";
	
	public AlertaVehiculoFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	private static String getNombre(int x) {
		return obtenerValor(nombre, x);
	}
	private static Integer getContadorAlerta(int x){
		return Integer.parseInt(obtenerValor(contadorAlerta, x));
	}
	@Override
	protected void execute(ExecutionContext executionContext) {
		List<Vehiculo> vehiculos=container.allInstances(Vehiculo.class);
		List<AlertaVehiculo> alertavehiculos= container.allInstances(AlertaVehiculo.class);
		if (alertavehiculos!=null){
			borrarTabla(executionContext,"AlertaVehiculo");
		}
			
			for (int x = 0; x < 4; x++) {
				create(getNombre(x), descripcion, new Date(System.currentTimeMillis()),vehiculos.get(x+1),getContadorAlerta(x), executionContext);
			}
		
	}

	private AlertaVehiculo create(String nombre, String descripcion,Date fechaAlta, Vehiculo vehiculo,int contadorAlerta,
			ExecutionContext executionContext) {
		return executionContext.addResult(this,
				repoAlerta.createAlertaVehiculo(nombre, descripcion, vehiculo, contadorAlerta));
	}

	@javax.inject.Inject
	private RepositorioAlertaVehiculo repoAlerta;
	@javax.inject.Inject
	DomainObjectContainer container;
}
