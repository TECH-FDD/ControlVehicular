package domainapp.fixture;

import domainapp.dom.app.taller.RepositorioTaller;
import domainapp.dom.app.taller.Taller;

public class TallerFixture extends Fixture{
	private static String nombreComercial="El Basco,Gatty,Taller Alberto,Cofre Neu";
	private static String descripcion="S/Descripcion";
	private static String direccion="Alcorta 450, San Martin 285, Avenida del Trabajador 2564";
	private static String telefono="456585,785465,874554";
	private static String codigo= "HDY685,JUS585,ANS852";
	private static String email="@gmail.com";
	
	private static String getnombreComercial(int x) {
		return obtenerValor(nombreComercial,x);
	}
	private static String getDireccion(int x) {
		return obtenerValor(direccion,x);
	}
	private static String getTelefono(int x) {
		return obtenerValor(telefono,x);
	}
	private static String getCodigo(int x) {
		return obtenerValor(codigo,x);
	}
	
	private static String getEmail(int x) {
		String nombreComercial = getnombreComercial(x);
		nombreComercial = nombreComercial.replace(' ', '_');
		String newEmail = nombreComercial + email;
		return newEmail;
	}
	public TallerFixture (){
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	@Override
	protected void execute(ExecutionContext executionContext) {
			borrarTabla(executionContext,"Taller");
			for (int x = 0; x < 3; x++) {
				create(getnombreComercial(x),getDireccion(x),getCodigo(x),getTelefono(x),getEmail(x),true, executionContext);
			}
		}
private Taller create(String nombreComercial, String direccion,String codigo, String telefono,
		String email,boolean activo, ExecutionContext executionContext) {
	return executionContext.addResult(this, repoTaller.createTaller(nombreComercial, descripcion, direccion, telefono, codigo, email));
}

@javax.inject.Inject
private RepositorioTaller repoTaller;

}