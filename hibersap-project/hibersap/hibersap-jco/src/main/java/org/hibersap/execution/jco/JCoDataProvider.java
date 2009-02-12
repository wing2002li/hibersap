package org.hibersap.execution.jco;

/*
 * Copyright (C) 2008 akquinet tech@spree GmbH
 * 
 * This file is part of Hibersap.
 * 
 * Hibersap is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 * 
 * Hibersap is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with Hibersap. If
 * not, see <http://www.gnu.org/licenses/>.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

/**
 * The Hibersap implementation of the JCo DestinationDataProvider. JCo destinations can be added and
 * removed at runtime.
 * 
 * @author Carsten Erker
 */
public class JCoDataProvider
    implements DestinationDataProvider
{
    Map<String, Properties> propertiesForDestinationName = new HashMap<String, Properties>();

    private DestinationDataEventListener eventListener;

    public void addDestination( String destinationName, Properties properties )
    {
        // if ( wasAdded( destinationName ) )
        // {
        // throw new HibersapException( "A JCo destination named '" + destinationName +
        // "' was already registered" );
        // }
        propertiesForDestinationName.put( destinationName, properties );
        eventListener.updated( destinationName );
    }

    public void removeDestination( String destinationName )
    {
        propertiesForDestinationName.remove( destinationName );
        eventListener.deleted( destinationName );
    }

    /**
     * {@inheritDoc}
     */
    public Properties getDestinationProperties( String destinationName )
    {
        if ( wasAdded( destinationName ) )
        {
            return propertiesForDestinationName.get( destinationName );
        }
        else
        {
            throw new RuntimeException( "No JCo destination with name " + destinationName + " found" );
        }
    }

    public boolean wasAdded( String destinationName )
    {
        return propertiesForDestinationName.containsKey( destinationName );
    }

    /**
     * {@inheritDoc}
     */
    public void setDestinationDataEventListener( DestinationDataEventListener eventListener )
    {
        this.eventListener = eventListener;
    }

    /**
     * {@inheritDoc}
     */
    public boolean supportsEvents()
    {
        return true;
    }
}