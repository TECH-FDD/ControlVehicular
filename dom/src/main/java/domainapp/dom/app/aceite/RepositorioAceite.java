package domainapp.dom.app.aceite;

import java.sql.Timestamp;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

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

	@MemberOrder(sequence ="2")
	@ActionLayout(named="Listar Todos")
	public List<Aceite> listAll(){
		return container.allMatches(new QueryDefault<Aceite>(Aceite.class, "ListarTodos"));
	}

	@ActionLayout(named="Buscar por Marca")
	@MemberOrder(sequence ="3")
	public List<Aceite> findByMarca(
			@ParameterLayout(named="Marca") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS)String marca){
		return container.allMatches(new QueryDefault<Aceite>(Aceite.class, "Buscar_Marca", "marca",marca));
	}

	@ActionLayout(named="Buscar por Nombre")
	@MemberOrder(sequence="4")
	public List<Aceite> findByNombre(
			@ParameterLayout(named="Nombre") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String nombre){
		return container.allMatches(new QueryDefault<Aceite>(Aceite.class, "Buscar_Nombre","nombre",nombre));
	}

	@ActionLayout(named="Buscar por Codigo")
	@MemberOrder(sequence="5")
	public List<Aceite> findByCodigo(
			@ParameterLayout(named="Codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codigo){
		return container.allMatches(new QueryDefault<Aceite>(Aceite.class,"Buscar_Codigo","codigo",codigo));
	}

	@ActionLayout(named="Listar por Tipo de Aceite")
	@MemberOrder(sequence="6")
	public List<Aceite> listTipoAceite(
			@ParameterLayout(named="Tipo de Aceite") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) TipoAceite tipoAceite){
		return container.allMatches(new QueryDefault<Aceite>(Aceite.class,"Buscar_Tipo","tipoAceite",tipoAceite));
	}
	@javax.inject.Inject
    DomainObjectContainer container;
}
