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
public interface ISqlStat extends IStat
{

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
     * Sets the Result
     * @param Result The Result
     */
    void setResult( Object Result );

    /**
     * Sets the Sql
     * @param strSql The Sql
     */
    void setSql( String strSql );
}
