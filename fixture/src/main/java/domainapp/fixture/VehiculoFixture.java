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

import java.sql.Timestamp;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;

import domainapp.dom.app.aceite.TipoAceite;
import domainapp.dom.app.combustible.TipoCombustible;
import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.vehiculo.RepositorioVehiculo;
import domainapp.dom.app.vehiculo.Vehiculo;

public class VehiculoFixture extends Fixture {

	private static String marca="Ford,Isuzu,Iveco,Iveco,Hyundai";
	private static String nombre= "Cargo 815,FTR,Cursor,Trakker,HD78";
	private static int modelo=2015;
	private static String patente="SER 123,HBD 789,HUG 675,IBM 255,KIJ 567";
	private static String numeroChasis="JGASDYUGSUY5468FG,KSUDS327DSF44RFDE,HDJSY2653GHETDBSH78,JFUERJ463548EHDGJFU80K,JUDH6DHSYS987SHYC7";
	private static String polizaSeguro="12545687,45875658,5287469,542134569,457821456";
	private static int capTanqueCombustible=1000;
	private static String cnsCombustibleRuta="1850";
	private static String cnsCombustibleCiudad="900";
	private static int kilometros=3000;

	private static String getMarca(int x) {
		return obtenerValor(marca, x);
	}
	
	private static String getNombre(int x) {
		return obtenerValor(nombre, x);
	}
	
	private static String getPatente(int x) {
		return obtenerValor(patente, x);
	}

	private static String getNumeroChasis(int x) {
		return obtenerValor(numeroChasis, x);
	}

	private static int getPolizaSeguro(int x) {
		return Integer.parseInt(obtenerValor(polizaSeguro, x));
	}

	public VehiculoFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {

		borrarTabla(executionContext,"Vehiculo");
		List<Gps> gps= container.allInstances(Gps.class);
		List<TipoCombustible> tipoCombustibles= container.allInstances(TipoCombustible.class);
//		List<Aceite> aceites= container.allInstances(Aceite.class);
		List<Matafuego> matafuegos= container.allInstances(Matafuego.class);

		for (int x=0; x<5; x++){
			create(getMarca(x),getNombre(x),new Timestamp(System.currentTimeMillis()),getPatente(x),
					getNumeroChasis(x),getPolizaSeguro(x),gps.get(x),tipoCombustibles.get(0),
					matafuegos.get(x),TipoAceite.SemiSintetico,executionContext);
		}
	}

	private Vehiculo create(String marca,String nombre, Timestamp fechaCompra,
			String patente, String numeroChasis, Integer polizaSeguro,
			Gps gps, TipoCombustible tipoCombustible, Matafuego matafuego,
			TipoAceite tipoAceite, ExecutionContext executionContext) {
		return executionContext.addResult(this, repoVehiculo.createVehiculo(marca, nombre, modelo, fechaCompra, patente, numeroChasis, polizaSeguro, gps, tipoCombustible, capTanqueCombustible, tipoAceite, cnsCombustibleRuta, cnsCombustibleCiudad, matafuego, kilometros));
	}

	@javax.inject.Inject
	private RepositorioVehiculo repoVehiculo;
	@javax.inject.Inject
	DomainObjectContainer container;
}
