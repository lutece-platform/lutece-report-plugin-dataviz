/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *	 and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *	 and the following disclaimer in the documentation and/or other materials
 *	 provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *	 contributors may be used to endorse or promote products derived from
 *	 this software without specific prior written permission.
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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.Collection;


/**
 * This class provides Data Access methods for Stat objects
 */

public final class StatDAO implements IStatDAO
{
	
	// Constants
	private static final String SQL_QUERY_NEW_PK = "SELECT max( id ) FROM dataviz_stat";
	private static final String SQL_QUERY_SELECT = "SELECT id, description, requete_sql FROM dataviz_stat WHERE id = ?";
	private static final String SQL_QUERY_INSERT = "INSERT INTO dataviz_stat ( id, description, requete_sql ) VALUES ( ?, ?, ? ) ";
	private static final String SQL_QUERY_DELETE = "DELETE FROM dataviz_stat WHERE id = ? ";
	private static final String SQL_QUERY_UPDATE = "UPDATE dataviz_stat SET id = ?, description = ?, requete_sql = ? WHERE id = ?";
	private static final String SQL_QUERY_SELECTALL = "SELECT id, description, requete_sql FROM dataviz_stat";


	
	/**
	 * Generates a new primary key
	 * @param plugin The Plugin
	 * @return The new primary key
	 */
	public int newPrimaryKey( Plugin plugin)
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK , plugin  );
		daoUtil.executeQuery( );

		int nKey = 1;

		if( daoUtil.next( ) )
		{
			nKey = daoUtil.getInt( 1 ) + 1;
		}

		daoUtil.free();

		return nKey;
	}




	/**
	 * {@inheritDoc }
	 */
	@Override
	public void insert( Stat stat, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );
				
		stat.setId( newPrimaryKey( plugin ) );
				
		daoUtil.setInt( 1, stat.getId( ) );
		daoUtil.setString( 2, stat.getDescription( ) );
		daoUtil.setString( 3, stat.getRequeteSql( ) );

		daoUtil.executeUpdate( );
		daoUtil.free( );
	}


	/**
	 * {@inheritDoc }
	 */
	@Override
	public Stat load( int nKey, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
		daoUtil.setInt( 1 , nKey );
		daoUtil.executeQuery( );

		Stat stat = null;

		if ( daoUtil.next( ) )
		{
			stat = new Stat();
			stat.setId( daoUtil.getInt(  1 ) );
			stat.setDescription( daoUtil.getString(  2 ) );
			stat.setRequeteSql( daoUtil.getString(  3 ) );
		}

		daoUtil.free( );
		return stat;
	}


	/**
	 * {@inheritDoc }
	 */
	@Override
	public void delete( int nStatId, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
		daoUtil.setInt( 1 , nStatId );
		daoUtil.executeUpdate( );
		daoUtil.free( );
	}


	/**
	 * {@inheritDoc }
	 */
	@Override
	public void store( Stat stat, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
				
		daoUtil.setInt( 1, stat.getId( ) );
		daoUtil.setString( 2, stat.getDescription( ) );
		daoUtil.setString( 3, stat.getRequeteSql( ) );
		daoUtil.setInt( 4, stat.getId( ) );
				
		daoUtil.executeUpdate( );
		daoUtil.free( );
	}



	/**
	 * {@inheritDoc }
	 */
	@Override
	public Collection<Stat> selectStatsList( Plugin plugin )
	{
		Collection<Stat> statList = new ArrayList<Stat>(  );
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
		daoUtil.executeQuery(  );

		while ( daoUtil.next(  ) )
		{
				Stat stat = new Stat(  );

					stat.setId( daoUtil.getInt( 1 ) );
					stat.setDescription( daoUtil.getString( 2 ) );
					stat.setRequeteSql( daoUtil.getString( 3 ) );

				statList.add( stat );
		}

		daoUtil.free( );
		return statList;
	}

        @Override
        public int getSingleResult( String sql, Plugin plugin )
        {
            int nResult = 0;
            DAOUtil daoUtil = new DAOUtil( sql, plugin );
            daoUtil.executeQuery(  );

            if ( daoUtil.next(  ) )
            {
                nResult = daoUtil.getInt( 1 );
            }

            daoUtil.free(  );

            return ( nResult );
        }

        @Override
        public Object getListResult ( String sql, Plugin plugin )
        {
            ReferenceList list = new ReferenceList ();
            DAOUtil daoUtil = new DAOUtil( sql, plugin );
            daoUtil.executeQuery(  );

            while ( daoUtil.next(  ) )
            {
                list.addItem( daoUtil.getObject( 1 ).toString(), daoUtil.getObject( 2 ).toString());
            }
            daoUtil.free(  );

            return list;
        }
}
