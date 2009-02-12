package org.hibersap.conversion;

/*
 * Copyright (C) 2008-2009 akquinet tech@spree GmbH
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

import static org.junit.Assert.assertEquals;

import org.hibersap.HibersapException;
import org.junit.Test;

public class ConverterCacheTest
{
    private ConverterCache cache = new ConverterCache();

    @Test
    public void createsOneInstanceOfEachClass()
    {
        cache.getConverter( CharConverter.class );
        assertEquals( 1, cache.getSize() );

        cache.getConverter( CharConverter.class );
        assertEquals( 1, cache.getSize() );

        cache.getConverter( BooleanConverter.class );
        assertEquals( 2, cache.getSize() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionOnNullArgument()
    {
        cache.getConverter( null );
    }

    @Test(expected = HibersapException.class)
    public void throwsHibersapExceptionIfNotInstantiable()
    {
        cache.getConverter( Converter.class );
    }
}