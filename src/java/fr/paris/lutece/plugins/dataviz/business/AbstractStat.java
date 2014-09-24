/*
 * Copyright (c) 2002-2013, Mairie de Paris
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.dataviz.business;


/**
 * This is the business class for the object AbstractStat
 */
public class AbstractStat implements IStat
{
    // Variables declarations 
    private String _strName;
    private String _strSql;
    private Object _Result;
    private String _strId;

    /**
     * Returns the Name
     * @return The Name
     */
    @Override
    public String getName(  )
    {
        return _strName;
    }

    /**
     * Sets the Name
     * @param strName The Name
     */
    @Override
    public void setName( String strName )
    {
        _strName = strName;
    }

    /**
     * Returns the Sql
     * @return The Sql
     */
    @Override
    public String getSql(  )
    {
        return _strSql;
    }

    /**
     * Sets the Sql
     * @param strSql The Sql
     */
    @Override
    public void setSql( String strSql )
    {
        _strSql = strSql;
    }

    /**
     * Returns the Result
     * @return The Result
     */
    @Override
    public Object getResult(  )
    {
        return _Result;
    }

    /**
     * Sets the Result
     * @param Result The Result
     */
    @Override
    public void setResult( Object Result )
    {
        _Result = Result;
    }
    
    /**
     * Returns the Id
     * @return The Id
     */
    @Override
    public String getId(  )
    {
        return _strId;
    }

    /**
     * Sets the Id
     * @param strId The Id
     */
    @Override
    public void setId( String strId )
    {
        _strId = strId;
    }
}
