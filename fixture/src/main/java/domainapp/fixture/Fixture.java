package domainapp.fixture;

import org.apache.isis.applib.fixturescripts.FixtureScript;

public abstract class Fixture extends FixtureScript {

	@Override
	protected void execute(ExecutionContext executionContext) {
		// TODO Auto-generated method stub

	}

	public static String obtenerValor(final String valor, final int x){
		String[] valores=valor.split(",");
		return valores[x];
	}

	public void borrarTabla(final ExecutionContext executionContext,final String tabla){
		executionContext.executeChild(this, new VaciarTablaFixture(tabla));
	}
}
