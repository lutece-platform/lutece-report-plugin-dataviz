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
public interface IStat
{
    /**
     * Returns the Name
     * @return The Name
     */
    String getName(  );

    
    /**
     * Returns the Id
     * @return The Id
     */
    String getId(  );

    /**
     * Returns the GraphType
     * @return The GraphType
     */
    String getGraphType(  );
    
    /**
     * Sets the Name
     * @param strName The Name
     */
    void setName( String strName );
   
    /**
     * Sets the Id
     * @param strId The Id
     */
    void setId( String strId );
    
    /**
     * Sets the GraphType
     * @param strGraphType The GraphType
     */
    void setGraphType( String strGraphType );
}
