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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;

import domainapp.dom.app.alerta.AlertaMatafuego;
import domainapp.dom.app.alerta.RepositorioAlertaMatafuego;
import domainapp.dom.app.matafuego.Matafuego;

public class AlertaMatafuegoFixture extends Fixture {
	private static String nombre= "Recarga";
	private static String descripcion = "Vencimiento";
	
	public AlertaMatafuegoFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		borrarTabla(executionContext, "AlertaMatafuego");
		List<Matafuego> matafuegos=container.allInstances(Matafuego.class);
		List<AlertaMatafuego> alertaMatafuegos= container.allInstances(AlertaMatafuego.class);
		if (alertaMatafuegos!=null){
			borrarTabla(executionContext,"AlertaMatafuego");
		}
			Date fecha=new Date(System.currentTimeMillis());
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(fecha);
			for (int x = 0; x < 3; x++) {
				calendar.add(Calendar.DAY_OF_YEAR,(100) );
				create(nombre, descripcion, new Date(System.currentTimeMillis()),matafuegos.get(x+1),calendar.getTime(),null,executionContext);
			}
		
	}

	private AlertaMatafuego create(String nombre, String descripcion,Date fechaAlta, Matafuego matafuego,Date contadorAlerta,String estadoAnterior,
			ExecutionContext executionContext) {
		return executionContext.addResult(this,
				repoAlerta.createAlertaMatafuego(nombre, descripcion, matafuego, contadorAlerta));
	}

	@javax.inject.Inject
	private RepositorioAlertaMatafuego repoAlerta;
	@javax.inject.Inject
	DomainObjectContainer container;
}
