/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.paris.lutece.plugins.dataviz.business;

import fr.paris.lutece.util.ReferenceList;

/**
 * Override getResult to return result of ListStat
 * @return The list
 */
public class StatList extends AbstractSqlStat implements IStatList
{
    @Override
    public Object getResult(  )
    {
       
        return getList ();
    }

    @Override
    public ReferenceList getList() 
    {
        return (ReferenceList) StatHome.getListResult ( getSql( ) );
    }
}
