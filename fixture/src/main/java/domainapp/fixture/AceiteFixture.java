package domainapp.fixture;

import org.apache.isis.applib.DomainObjectContainer;

import domainapp.dom.app.aceite.Aceite;
import domainapp.dom.app.aceite.RepositorioAceite;
import domainapp.dom.app.aceite.TipoAceite;

public class AceiteFixture extends Fixture {

	private String nombre="Elaion F10,Helix F10,Lubrax F10";
	private String marca="Ypf,Shell,Petrobras";
	private String codigo="YESS,HSSS,PLSS";
	private String descripcion="Aceite Semi-Sintetico de alta performance.";
	private int duracion=10000;

	public AceiteFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private String getNombre(int x) {
		return obtenerValor(nombre, x);
	}

	private String getMarca(int x) {
		return obtenerValor(marca, x);
	}

	private String getCodigo(int x) {
		return obtenerValor(codigo, x);
	}

	private int getDuracion() {
		return duracion;
	}

	private String getDescripcion() {
		return descripcion;
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		borrarTabla(executionContext, "Aceite");
		for (int x=0; x<3;x++){
			create(getMarca(x),getNombre(x),getCodigo(x),getDescripcion(),TipoAceite.SemiSintetico,getDuracion(),executionContext);
		}
	}

	private Aceite create(final String marca, final String nombre, final String codigo,
						final String descripcion, final TipoAceite tipoAceite,
						final int duracion, ExecutionContext executionContext){
		return executionContext.addResult(this, repoAceite.createAceite(marca, nombre, codigo, descripcion, tipoAceite, duracion));
	}

	@javax.inject.Inject
	private RepositorioAceite repoAceite;
	@javax.inject.Inject
	DomainObjectContainer container;
}
