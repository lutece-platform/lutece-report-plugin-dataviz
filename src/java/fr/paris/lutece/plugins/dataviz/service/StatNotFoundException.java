/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.dataviz.service;


/**
 *
 * @author evrardmax
 */
public class StatNotFoundException extends Exception
{
    public StatNotFoundException( String statName )
    {
        super( "Statistique '" + statName + "' not found !" );
    }
}
