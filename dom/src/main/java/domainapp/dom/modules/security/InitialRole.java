package domainapp.dom.modules.security;

import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.isisaddons.module.security.dom.permission.ApplicationPermissionMode;
import org.isisaddons.module.security.dom.permission.ApplicationPermissionRule;
import org.isisaddons.module.security.seed.scripts.AbstractRoleAndPermissionsFixtureScript;

public class InitialRole extends AbstractRoleAndPermissionsFixtureScript {

	public static final String ROLE_NAME = "Rol-Inicial";

	public InitialRole() {
		super(ROLE_NAME, "Solo Acceso a la Aplicación");
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		newClassPermissions(ApplicationPermissionRule.ALLOW,
				ApplicationPermissionMode.CHANGING, FixtureResult.class);
	}
}
