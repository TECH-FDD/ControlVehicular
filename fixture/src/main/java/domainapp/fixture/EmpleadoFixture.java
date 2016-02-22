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

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.joda.time.LocalDate;

import domainapp.dom.app.area.Area;
import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.empleado.RepositorioEmpleado;
import domainapp.dom.app.persona.Ciudad;
import domainapp.dom.app.persona.Documento;
import domainapp.dom.app.persona.Provincia;
import domainapp.dom.app.persona.Sexo;
import domainapp.dom.app.vehiculo.Vehiculo;

public class EmpleadoFixture extends Fixture{

	private static String nombre="Pedro,Jose,Pepe,Delfort,Franco,Marcos,Luis,Emiliano,Lautaro,Anibal";
	private static String apellido="Chiappano,Villegas,Burgardt,Gonzales,Zuñiga,Pereyra,Garcia,Alfonzo,Gomez,Medina";
	private static String calle="Formosa,Chaco,Mendoza,Alsina,Uruguay,Artigas,Neuquen,Rio Negro,Mitre,Tucuman";
	private static String numero="1045,1036,58,2444,1457,2056,425,586,10,47";
	private static String nroDocumento="32145784,54215456,10245648,37359193,10381721,14381778,44585612,43589874,24565142,26545621";
	private static String email="@gmail.com";
	private static String codArea="0298-15";
	private static String telefono="456585,785465,874554,654578,124575,432154,756891,789541,789432,789412";

	private static String getTelefono(int x){
		String tel=codArea+obtenerValor(telefono,x);
		return tel;
	}

	private static String getNombre(int x) {
		return obtenerValor(nombre,x);
	}

	private static String getApellido(int x) {
		return obtenerValor(apellido,x);
	}

	private static int getNroDocumento(int x) {
		return Integer.parseInt(obtenerValor(nroDocumento, x));
	}

	private static String getDireccion(int x){
		String direccion=obtenerValor(calle,x)+" "+obtenerValor(numero,x);
		return direccion;
	}

	private static String getEmail(int x) {
		String nombre=getNombre(x);
		String apellido=getApellido(x);
		String newEmail=nombre.substring(0)+apellido+email;
		return newEmail;
	}

	private static String getLegajo(int x) {
		int num=100*(x+1);
		return "10-"+String.valueOf(num);
	}

	public EmpleadoFixture (){
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		List<Area> areas=container.allInstances(Area.class);
		if (areas!=null){
			borrarTabla(executionContext,"Empleado");
			for (int x = 0; x < 10; x++) {
				create(getNombre(x), getApellido(x), Documento.DNI,
						getNroDocumento(x), LocalDate.now(), Sexo.Masculino,
						Provincia.RioNegro, Ciudad.General_Roca, 8332,
						getDireccion(x), getTelefono(x), getEmail(x),
						getLegajo(x), null, areas.get(0), executionContext);
			}
		}
	}

	private Empleado create(String nombre, String apellido,
			Documento tipoDocumento, int numeroDocumento,
			LocalDate fechaNacimiento, Sexo sexo, Provincia provincia,
			Ciudad ciudad, int codigoPostal, String domicilio, String telefono,
			String email, String legajo, Vehiculo vehiculo, Area area,
			ExecutionContext executionContext) {
		return executionContext.addResult(this, repoEmpleado.createEmpleado(
				nombre, apellido, tipoDocumento, numeroDocumento,
				fechaNacimiento, sexo, provincia, ciudad, codigoPostal,
				domicilio, telefono, email, legajo, vehiculo, area));
	}

	@javax.inject.Inject
	private RepositorioEmpleado repoEmpleado;
	@javax.inject.Inject
	DomainObjectContainer container;
}
