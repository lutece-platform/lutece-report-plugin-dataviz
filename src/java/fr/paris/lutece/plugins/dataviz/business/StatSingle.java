/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.paris.lutece.plugins.dataviz.business;

/**
 * Override getResult to return result of SingleStat
 * @return The nResult
 */
public class StatSingle extends AbstractStat
{
    @Override
    public Object getResult(  )
    {
        int nResult = StatHome.getSingleResult( getSql() );

        return nResult;
    }
}
