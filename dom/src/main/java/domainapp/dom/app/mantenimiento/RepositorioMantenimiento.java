package domainapp.dom.app.mantenimiento;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

import domainapp.dom.app.taller.Taller;

import java.sql.Timestamp;
import java.math.BigDecimal;

@DomainService(repositoryFor = Mantenimiento.class)
@DomainServiceLayout(menuOrder = "120", named = "Mantenimiento")
public class RepositorioMantenimiento {

	public RepositorioMantenimiento() {
		// TODO Auto-generated constructor stub
	}
	
	@MemberOrder(sequence="1")
	@ActionLayout(named ="Crear mantenimiento")
	public Mantenimiento createMantenimiento(
			
		final @ParameterLayout(named="Titulo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) String titulo,
		final @ParameterLayout(named="Detalle") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS,optionality = Optionality.OPTIONAL, maxLength = 50) String detalle,
		final @ParameterLayout(named = "Codigo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS,optionality = Optionality.OPTIONAL, maxLength = 50) String codigo,
		//final @ParameterLayout(named = "Fecha de creación") Timestamp fechaCreacion,
		final @ParameterLayout(named="Fecha de realizacion") Timestamp fechaRealizacion,
		final @ParameterLayout(named = "Elemento") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) String elemento,
		final @ParameterLayout(named = "Taller") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) Taller taller,
		final @ParameterLayout(named = "Costo de respuesto") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) BigDecimal costoRespuesto,	
		final @ParameterLayout(named = "Costo de mano de obra")	@Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) BigDecimal costoManoObra,
		final @ParameterLayout(named = "Costo total") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, maxLength = 10) BigDecimal costoTotal,
		final @ParameterLayout(named="De rutina") Boolean deRutina){
		
		Mantenimiento unMantenimiento = new Mantenimiento(titulo,detalle,codigo, new Timestamp(System.currentTimeMillis()),
				fechaRealizacion,elemento, taller,costoRespuesto,costoManoObra,costoTotal,deRutina);
		container.persistIfNotAlready(unMantenimiento);
		return unMantenimiento;
		
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
}
