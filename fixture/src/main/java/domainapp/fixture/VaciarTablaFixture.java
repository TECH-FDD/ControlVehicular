package domainapp.fixture;

import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class VaciarTablaFixture extends Fixture{

	private String tabla="";

	public VaciarTablaFixture(final String tabla){
		withDiscoverability(Discoverability.DISCOVERABLE);
		this.tabla=tabla;
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		if (tabla!="")
			isisJdoSupport.executeUpdate("TRUNCATE \""+tabla+"\"CASCADE");
	}

	@javax.inject.Inject
	private IsisJdoSupport isisJdoSupport;
}
