/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.paris.lutece.plugins.dataviz.business;

import fr.paris.lutece.util.ReferenceList;

/**
 *
 * @author evrardmax
 */
public class StatList extends AbstractStat
{
    @Override
    public Object getResult(  )
    {
        ReferenceList list = (ReferenceList) StatHome.getListResult ( getSql( ) );
        
        return list;
    }
}
