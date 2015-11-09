package domainapp.dom.app.reporte;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class EmpleadoDataSource implements JRDataSource {
	private List<ReporteEmpleado> listaEmpleado = new ArrayList<ReporteEmpleado>();
	private int indiceEmpleadoActual = -1;

	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;

		if ("legajo".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getLegajo();
		} else if ("nombre".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getNombre();
		} else if ("tipoDocumento".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getTipoDocumento();
		} else if ("nroDocumento".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getNroDocumento();
		} else if ("fechaNacimiento".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getFechaNacimiento();
		} else if ("domicilio".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getDomicilio();
		} else if ("provincia".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getProvincia();
		} else if ("ciudad".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getCiudad();
		} else if ("codigoPostal".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getCodigoPostal();
		} else if ("fechaAlta".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getFechaAlta();
		} else if ("sexo".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getSexo();
		} else if ("telefono".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getTelefono();
		} else if ("email".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getLegajo();
		} else if ("area".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getArea();
		} else if ("activo".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getActivo();
		} else if ("vehiculo".equals(jrf.getName())) {
			valor = listaEmpleado.get(indiceEmpleadoActual).getVehiculo();
		}
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceEmpleadoActual < listaEmpleado.size();
	}

	public void addParticipante(ReporteEmpleado empleado) {
		this.listaEmpleado.add(empleado);
	}
}