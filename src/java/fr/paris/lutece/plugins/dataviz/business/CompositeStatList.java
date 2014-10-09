/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.paris.lutece.plugins.dataviz.business;

import fr.paris.lutece.util.ReferenceList;
import java.util.List;

/**
 *
 * @author evrardmax
 */
public class CompositeStatList extends AbstractStat implements IStatList
{
    private List<StatSingle> _listStatSingle;
    
    public void setStatList ( List<StatSingle> listStatSingle)
    {
        _listStatSingle = listStatSingle;
    }
    
    @Override
    public ReferenceList getList() 
    {
        ReferenceList listSingle = new ReferenceList ( );
        
        for ( StatSingle singleResult : _listStatSingle )
        {
            listSingle.addItem (singleResult.getName(), singleResult.getResult().toString() );
        }
        return listSingle;
    }
}
