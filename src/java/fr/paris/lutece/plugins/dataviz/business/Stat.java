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

import fr.paris.lutece.util.ReferenceList;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;


/**
 * This is the business class for the object Stat
 */ 
public class Stat
{
	// Variables declarations 
        
	private int _nId;
        // @NotEmpty( message = "#i18n{dataviz.validation.stat.Description.notEmpty}" )
        @NotEmpty( message = "#i18n{portal.validation.message.notEmpty}" )
        
	private String _strDescription;
        // @NotEmpty( message = "#i18n{dataviz.validation.stat.RequeteSql.notEmpty}" )
        @NotEmpty( message = "#i18n{portal.validation.message.notEmpty}" )
        // @Size( max = 255 , message = "#i18n{dataviz.validation.stat.RequeteSql.size}" ) 
        @Size( max = 255 , message = "#i18n{portal.validation.message.sizeMax}" ) 
        
	private String _strRequeteSql;        
        private String _name;
        private String _type;
        private ReferenceList _list;
	/**
	 * Returns the Id
	 * @return The Id
	 */
	public int getId()
	{
		return _nId;
	}

	/**
	 * Sets the Id
	 * @param nId The Id
	 */ 
	public void setId( int nId )
	{
		_nId = nId;
	}
	/**
	 * Returns the Description
	 * @return The Description
	 */
	public String getDescription()
	{
		return _strDescription;
	}

	/**
	 * Sets the Description
	 * @param strDescription The Description
	 */ 
	public void setDescription( String strDescription )
	{
		_strDescription = strDescription;
	}
	/**
	 * Returns the RequeteSql
	 * @return The RequeteSql
	 */
	public String getRequeteSql()
	{
		return _strRequeteSql;
	}

	/**
	 * Sets the RequeteSql
	 * @param strRequeteSql The RequeteSql
	 */ 
	public void setRequeteSql( String strRequeteSql )
	{
		_strRequeteSql = strRequeteSql;
	}
        
        public Object getListResult(  )
        {
            ReferenceList list = (ReferenceList) StatHome.getListResult ( getRequeteSql() );

            return list;
        }
        
        public Object getResult(  )
        {
            int nResult = StatHome.getSingleResult( getRequeteSql(  ) );

            return nResult;
        }
    
        public String getName ( )
        {
            return _name;
        }

        public void setName ( String name )
        {
            _name = name;
        }

        public ReferenceList getList ( )
        {
            return _list;
        }

        public void setList ( ReferenceList list )
        {
            _list = list;
        }
        
        public String getType ( )
        {
            return _type;
        }

        public void setType ( String type )
        {
            _type = type;
        }
}