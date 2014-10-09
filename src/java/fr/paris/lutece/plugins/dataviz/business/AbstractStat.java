/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.paris.lutece.plugins.dataviz.business;

/**
 *
 * @author evrardmax
 */
public class AbstractStat implements IStat
{
        private String _strId;
        private String _strName;
        private String _strGraphType;

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

    /**
     * Returns the GraphType
     * @return The GraphType
     */
    @Override
    public String getGraphType() 
    {
        return _strGraphType;
    }

    /**
     * Sets the GraphType
     * @param strGraphType The GraphType
     */
    @Override
    public void setGraphType(String strGraphType) 
    {
        _strGraphType = strGraphType;
    }
}
