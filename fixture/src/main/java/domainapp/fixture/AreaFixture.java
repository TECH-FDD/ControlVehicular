package domainapp.fixture;

import org.apache.isis.applib.DomainObjectContainer;

import domainapp.dom.app.area.Area;
import domainapp.dom.app.area.RepositorioArea;

public class AreaFixture extends Fixture {

	private static String codigo = "MT,AC,RRPP";
	private static String nombre = "Mantenimiento,Atención al Cliente,Relaciones Publicas";
	private static String descripcion = "Mantenimintos de lineas y equipos externos de la empresa,Atención domiciliaria a clientes,Relaciones publicas con empresas externas interesadas en contratar nestro servicio";

	public AreaFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private static String getCodigo(int x) {
		return obtenerValor(codigo, x);
	}

	private static String getNombre(int x) {
		return obtenerValor(nombre, x);
	}

	private static String getDescripcion(int x) {
		return obtenerValor(descripcion, x);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		borrarTabla(executionContext, "Area");
		for (int x = 0; x < 3; x++) {
			create(getCodigo(x), getNombre(x), getDescripcion(x),
					executionContext);
		}
	}

	private Area create(String codigo, String nombre, String descripcion,
			ExecutionContext executionContext) {
		return executionContext.addResult(this,
				repoArea.createArea(codigo, nombre, descripcion));
	}

	@javax.inject.Inject
	private RepositorioArea repoArea;
	@javax.inject.Inject
	DomainObjectContainer container;
}
