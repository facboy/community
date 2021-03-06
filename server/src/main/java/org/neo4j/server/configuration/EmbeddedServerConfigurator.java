/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.server.configuration;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.neo4j.kernel.AbstractGraphDatabase;
import org.neo4j.server.WrappingNeoServerBootstrapper;

/**
 * Used by the {@link WrappingNeoServerBootstrapper}, passing the minimum amount
 * of required configuration on to the neo4j server.
 * <p>
 * If you want to change configuration for your
 * {@link WrappingNeoServerBootstrapper}, create an instance of this class, and
 * add configuration like so:
 * 
 * <pre>
 * {
 *     &#064;code EmbeddedServerConfigurator conf = new EmbeddedServerConfigurator( myDb );
 *     conf.configuration()
 *             .addProperty( WEBSERVER_PORT_PROPERTY_KEY, 8080 );
 * }
 * </pre>
 * 
 * See the neo4j manual for information about what configuration directives the
 * server takes, or take a look at the static strings in {@link Configurator}.
 */
public class EmbeddedServerConfigurator implements Configurator
{

    private MapBasedConfiguration config = new MapBasedConfiguration();
    private Set<ThirdPartyJaxRsPackage> jaxRsPackages = new HashSet<ThirdPartyJaxRsPackage>();

    public EmbeddedServerConfigurator( AbstractGraphDatabase db )
    {
        config.addProperty( DATABASE_LOCATION_PROPERTY_KEY, db.getStoreDir() );
    }

    @Override
    public Configuration configuration()
    {
        return config;
    }

    @Override
    public Map<String, String> getDatabaseTuningProperties()
    {
        return null;
    }

    @Override
    public Set<ThirdPartyJaxRsPackage> getThirdpartyJaxRsClasses()
    {
        return jaxRsPackages;
    }

}
