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
     * Returns the Result
     * @return The Result
     */
    Object getResult(  );

    /**
     * Returns the Sql
     * @return The Sql
     */
    String getSql(  );
    
    /**
     * Returns the Id
     * @return The Id
     */
    String getId(  );

    /**
     * Sets the Name
     * @param strName The Name
     */
    void setName( String strName );

    /**
     * Sets the Result
     * @param Result The Result
     */
    void setResult( Object Result );

    /**
     * Sets the Sql
     * @param strSql The Sql
     */
    void setSql( String strSql );
    
    /**
     * Sets the Id
     * @param strId The Id
     */
    void setId( String strId );
}
