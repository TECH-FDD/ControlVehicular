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

import java.math.BigDecimal;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;

import domainapp.dom.app.combustible.Combustible;
import domainapp.dom.app.combustible.RepositorioCombustible;
import domainapp.dom.app.combustible.TipoCombustible;

public class CombustibleFixture extends Fixture {

	private static String nombre="V-Power,Super,Infinia,Super,Premium,Super";
	private static String empresa="Shell,Shell,Ypf,Ypf,Petrobras,Petrobras";
	private static String descripcion="Combustible de exelente calidad.";
	private static String codigo="VPS,SS,IY,SY,PP,SP";
	private static String categoria="Premium,Super,Premium,Super,Premium,Super";
	private static String precioLitro="13.5,12.5,13.45,12.45,13.55,12.65";
	private static String precioAnterior="12.95,11.95,12.90,11.90,12.00,11.00";
	private static String porcentajeAumento="1.5,0.5,1.2,0.6,0.9,1.3";
	private static String octanaje="105,98,102,95,103,96";

	private String getNombre(int x){
		return obtenerValor(nombre, x);
	}

	private String getEmpresa(int x){
		return obtenerValor(empresa,x);
	}

	private String getCodigo(int x){
		return obtenerValor(codigo, x);
	}

	private String getCategoria(int x){
		return obtenerValor(categoria, x);
	}

	private BigDecimal getPrecioLitro(int x){
		BigDecimal bd= new BigDecimal(obtenerValor(precioLitro, x));
		return bd;
	}

	private BigDecimal getPrecioAterior(int x){
		BigDecimal bd= new BigDecimal(obtenerValor(precioAnterior, x));
		return bd;
	}

	private BigDecimal getPorcentajeAumento(int x){
		BigDecimal bd= new BigDecimal(obtenerValor(porcentajeAumento, x));
		return bd;
	}

	private int getOctanaje(int x){
		return Integer.parseInt(obtenerValor(octanaje, x));
	}

	public CombustibleFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		borrarTabla(executionContext, "Combustible");
		List<TipoCombustible> tiposCombustible= container.allInstances(TipoCombustible.class);
		for (int x=0; x<6; x++){
			create(getNombre(x),getEmpresa(x),getCodigo(x),getCategoria(x),getPrecioLitro(x),getPrecioAterior(x),getPorcentajeAumento(x),getOctanaje(x),tiposCombustible.get(0),executionContext);
		}
	}

	private Combustible create(String nombre,String empresa,String codigo,String categoria,BigDecimal precioLitro,BigDecimal precioAnterior,BigDecimal porcentajeAumento, int octanaje,TipoCombustible tipoCombustible,ExecutionContext executionContext ){
		return executionContext.addResult(this, repoCombustible.createCombustible(nombre, empresa, codigo, descripcion, categoria, precioLitro, precioAnterior, porcentajeAumento, octanaje, tipoCombustible));
	}

	@javax.inject.Inject
	private RepositorioCombustible repoCombustible;
	@javax.inject.Inject
	DomainObjectContainer container;
}
