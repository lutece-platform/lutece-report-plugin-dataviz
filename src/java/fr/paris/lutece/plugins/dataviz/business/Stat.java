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
        private String _name;
        private ReferenceList _list;
        private String _id;
    
        /**
         * Returns the Name
         * @return The Name
         */
        public String getName ( )
        {
            return _name;
        }

        /**
	 * Sets the Name
	 * @param Name The Name
	 */
        public void setName ( String name )
        {
            _name = name;
        }

        /**
         * Returns the Id
         * @return The Id
         */
        public String getId ( )
        {
            return _id;
        }

        /**
	 * Sets the Id
	 * @param Id The Id
	 */
        public void setId ( String id )
        {
            _id = id;
        }
        
        /**
         * Returns the List
         * @return The List
         */
        public ReferenceList getList ( )
        {
            return _list;
        }

        /**
	 * Sets the RequestS=ql
	 * @param List The List
         */
        public void setList ( ReferenceList list )
        {
            _list = list;
        }
}