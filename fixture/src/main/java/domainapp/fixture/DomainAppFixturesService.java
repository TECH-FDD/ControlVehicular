/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package domainapp.fixture;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.RestrictTo;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.fixturescripts.SimpleFixtureScript;

/**
 * Enables fixtures to be installed from the application.
 */
@DomainService
@DomainServiceLayout(
        named="Prototyping",
        menuBar = DomainServiceLayout.MenuBar.SECONDARY,
        menuOrder = "20"
)
public class DomainAppFixturesService extends FixtureScripts {

    public DomainAppFixturesService() {
        super("domainapp", MultipleExecutionStrategy.EXECUTE);
    }

    @Override
    public FixtureScript default0RunFixtureScript() {
        return findFixtureScriptFor(SimpleFixtureScript.class);
    }

    @Override
    public List<FixtureScript> choices0RunFixtureScript() {
        return super.choices0RunFixtureScript();
    }


    // //////////////////////////////////////

    @Action(
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            cssClassFa="fa fa-refresh", named="Actualizar Base de Datos"
    )
	@MemberOrder(sequence = "20")
	public String actualizarBaseDatos() {
		findFixtureScriptFor(AreaFixture.class).run(null);
		findFixtureScriptFor(EmpleadoFixture.class).run(null);
		findFixtureScriptFor(AceiteFixture.class).run(null);
		findFixtureScriptFor(TipoCombustibleFixture.class).run(null);
		findFixtureScriptFor(CombustibleFixture.class).run(null);
		findFixtureScriptFor(GpsFixture.class).run(null);
		findFixtureScriptFor(TallerFixture.class).run(null);
		findFixtureScriptFor(MatafuegoFixture.class).run(null);
		findFixtureScriptFor(VehiculoFixture.class).run(null);
		findFixtureScriptFor(AlertaMatafuegoFixture.class).run(null);
		findFixtureScriptFor(AlertaVehiculoFixture.class).run(null);
		return "La Base de Datos, se ha actualizado con exito!";
	}
}
