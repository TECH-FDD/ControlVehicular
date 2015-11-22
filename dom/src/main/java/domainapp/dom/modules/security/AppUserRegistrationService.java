package domainapp.dom.modules.security;

import java.util.Set;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.isisaddons.module.security.dom.role.ApplicationRole;
import org.isisaddons.module.security.dom.role.ApplicationRoles;
import org.isisaddons.module.security.userreg.SecurityModuleAppUserRegistrationServiceAbstract;

@DomainService
public class AppUserRegistrationService extends SecurityModuleAppUserRegistrationServiceAbstract {

	protected ApplicationRole getInitialRole() {
		return findRole(InitialRole.ROLE_NAME);
	}

	private ApplicationRole findRole(final String roleName) {
		return applicationRoles.findRoleByName(roleName);
	}

	@Inject
	private ApplicationRoles applicationRoles;

	@Override
	protected Set<ApplicationRole> getAdditionalInitialRoles() {
		return null;
	}
}
