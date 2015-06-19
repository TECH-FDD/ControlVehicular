package domainapp.dom.app.aceite;

import java.sql.Timestamp;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

@DomainService(repositoryFor = Aceite.class)
@DomainServiceLayout(menuOrder = "60", named="Aceites")
public class RepositorioAceite {
	@MemberOrder(sequence = "1")
	@ActionLayout(named="Crear nuevo Aceite")
	public Aceite createAceite(
			@ParameterLayout(named="Marca")String marca,
			@ParameterLayout(named="Nombre")String nombre,
			@ParameterLayout(named="Codigo") @Parameter(optionality=Optionality.OPTIONAL)String codigo,
			@ParameterLayout(named="Descripción")@Parameter(optionality=Optionality.OPTIONAL)String descripcion,
			@ParameterLayout(named="Tipo de Aceite")TipoAceite tipoAceite,
			@ParameterLayout(named="Duración (km)")int duracion){
		final Aceite aceite = container.newTransientInstance(Aceite.class);
		aceite.setCodigo(codigo);
		aceite.setDescripcion(descripcion);
		aceite.setDuracion(duracion);
		aceite.setFechaAlta(new Timestamp(System.currentTimeMillis()));
		aceite.setMarca(marca);
		aceite.setNombre(nombre);
		aceite.setTipoAceite(tipoAceite);
		aceite.setActivo(true);
		return aceite;
	}
	@javax.inject.Inject
    DomainObjectContainer container;
}
