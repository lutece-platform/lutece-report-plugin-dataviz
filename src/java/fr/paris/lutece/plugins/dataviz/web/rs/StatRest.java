 /*
 * Copyright (c) 2002-2012, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
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
package fr.paris.lutece.plugins.dataviz.web.rs;

import fr.paris.lutece.plugins.dataviz.business.Stat;
import fr.paris.lutece.plugins.dataviz.business.StatHome;
import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.plugins.rest.util.json.JSONUtil;
import fr.paris.lutece.plugins.rest.util.xml.XMLUtil;
import fr.paris.lutece.util.xml.XmlUtil;
import fr.paris.lutece.portal.service.util.AppLogService;
import java.io.IOException;

import net.sf.json.JSONObject;

import java.util.Collection;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Page resource
 */
 
@Path( RestConstants.BASE_PATH + Constants.PLUGIN_PATH + Constants.STAT_PATH )
public class StatRest
{
//    private static final String KEY_STATS = "stats";
//    private static final String KEY_STAT = "stat";
//    
//    private static final String KEY_ID="id";
//    private static final String KEY_DESCRIPTION="description";
//    private static final String KEY_REQUETE_SQL="requete_sql";
//    
//    @GET
//    @Path( Constants.ALL_PATH )
//    public Response getStats( @HeaderParam(HttpHeaders.ACCEPT) String accept , @QueryParam( Constants.FORMAT_QUERY ) String format ) throws IOException
//    {
//        String entity;
//        String mediaType;
//        
//        if ( (accept != null && accept.contains(MediaType.APPLICATION_JSON)) || (format != null && format.equals(Constants.MEDIA_TYPE_JSON)) )
//        {
//            entity = getStatsJson();
//            mediaType = MediaType.APPLICATION_JSON;
//        }
//        else
//        {
//            entity = getStatsXml();
//            mediaType = MediaType.APPLICATION_XML;
//        }
//        return Response
//            .ok(entity, mediaType)
//            .build();
//    }
//    
//    /**
//     * Gets all resources list in XML format
//     * @return The list
//     */
//    public String getStatsXml( )
//    {
//        StringBuffer sbXML = new StringBuffer( XmlUtil.getXmlHeader() );
//        Collection<Stat> list = StatHome.getStatsList();
//        
//        XmlUtil.beginElement( sbXML , KEY_STATS );
//
//        for ( Stat stat : list )
//        {
//            addStatXml( sbXML, stat );
//        }
//        
//        XmlUtil.endElement( sbXML , KEY_STATS );
//
//        return sbXML.toString(  );
//    }
//    
//    /**
//     * Gets all resources list in JSON format
//     * @return The list
//     */
//    public String getStatsJson( )
//    {
//        JSONObject jsonStat = new JSONObject(  );
//        JSONObject json = new JSONObject(  );
//        
//        Collection<Stat> list = StatHome.getStatsList();
//        
//        for ( Stat stat : list )
//        {
//            addStatJson( jsonStat, stat );
//        }
//        
//        json.accumulate( KEY_STATS, jsonStat );
//        
//        return json.toString( );
//    }
//    
//    @GET
//    @Path( "{" + Constants.ID_PATH + "}" )
//    public Response getStat( @PathParam( Constants.ID_PATH ) String strId, @HeaderParam(HttpHeaders.ACCEPT) String accept , @QueryParam( Constants.FORMAT_QUERY ) String format ) throws IOException
//    {
//        String entity;
//        String mediaType;
//        
//        if ( (accept != null && accept.contains(MediaType.APPLICATION_JSON)) || (format != null && format.equals(Constants.MEDIA_TYPE_JSON)) )
//        {
//            entity = getStatJson( strId );
//            mediaType = MediaType.APPLICATION_JSON;
//        }
//        else
//        {
//            entity = getStatXml( strId );
//            mediaType = MediaType.APPLICATION_XML;
//        }
//        return Response
//            .ok(entity, mediaType)
//            .build();
//    }
//    
//    /**
//     * Gets a resource in XML format
//     * @param strId The resource ID
//     * @return The XML output
//     */
//    public String getStatXml( String strId )
//    {
//        StringBuffer sbXML = new StringBuffer(  );
//        
//        try
//        {
//            int nId = Integer.parseInt( strId );
//            Stat stat = StatHome.findByPrimaryKey( nId );
//
//            if ( stat != null )
//            {
//                sbXML.append( "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" );
//                addStatXml( sbXML, stat );
//            }
//        }
//        catch ( NumberFormatException e )
//        {
//            sbXML.append( XMLUtil.formatError( "Invalid stat number", 3 ) );
//        }
//        catch ( Exception e )
//        {
//            sbXML.append( XMLUtil.formatError( "Stat not found", 1 ) );
//        }
//
//        return sbXML.toString(  );
//    }
//    
//    /**
//     * Gets a resource in JSON format
//     * @param strId The resource ID
//     * @return The JSON output
//     */
//    public String getStatJson( String strId )
//    {
//        JSONObject json = new JSONObject(  );
//        String strJson = "";
//
//        try
//        {
//            int nId = Integer.parseInt( strId );
//            Stat stat = StatHome.findByPrimaryKey( nId );
//
//            if ( stat != null )
//            {
//                addStatJson( json, stat );
//                strJson = json.toString( );
//            }
//        }
//        catch ( NumberFormatException e )
//        {
//            strJson = JSONUtil.formatError( "Invalid stat number", 3 );
//        }
//        catch ( Exception e )
//        {
//            strJson = JSONUtil.formatError( "Stat not found", 1 );
//        }
//
//        return strJson;
//    }
//    
//    @DELETE
//    @Path( "{" + Constants.ID_PATH + "}" )
//    public Response deleteStat( @PathParam( Constants.ID_PATH ) String strId, @HeaderParam(HttpHeaders.ACCEPT) String accept, @QueryParam( Constants.FORMAT_QUERY ) String format ) throws IOException
//    {
//        try
//        {
//            int nId = Integer.parseInt( strId );
//            
//            if ( StatHome.findByPrimaryKey( nId ) != null )
//            {
//                StatHome.remove( nId );
//            }
//        }
//        catch ( NumberFormatException e )
//        {
//            AppLogService.error( "Invalid stat number" );
//        }
//        return getStats(accept, format);
//    }
//    
//    @POST
//    public Response createStat(
//    @FormParam( "id" ) String id, 
//    @FormParam( "description" ) String description, 
//    @FormParam( "requete_sql" ) String requete_sql, 
//    @HeaderParam(HttpHeaders.ACCEPT) String accept, @QueryParam( Constants.FORMAT_QUERY ) String format) throws IOException
//    {
//        if( id != null )
//        {
//            int nId = Integer.parseInt( id);
//
//            Stat stat = StatHome.findByPrimaryKey( nId );
//
//            if ( stat != null )
//            {
//                stat.setDescription( description );
//                stat.setRequeteSql( requete_sql );
//                StatHome.update( stat );
//            }
//        }
//        else
//        {
//            Stat stat = new Stat( );
//            
//            stat.setDescription( description );
//            stat.setRequeteSql( requete_sql );
//            StatHome.create( stat );
//        }
//        return getStats(accept, format);
//    }
//    
//    /**
//     * Write a stat into a buffer
//     * @param sbXML The buffer
//     * @param stat The stat
//     */
//    private void addStatXml( StringBuffer sbXML, Stat stat )
//    {
//        XmlUtil.beginElement( sbXML, KEY_STAT );
//        XmlUtil.addElement( sbXML, KEY_ID , stat.getId ( ) );
//        XmlUtil.addElement( sbXML, KEY_DESCRIPTION , stat.getDescription ( ) );
//        XmlUtil.addElement( sbXML, KEY_REQUETE_SQL , stat.getRequeteSql ( ) );
//        XmlUtil.endElement( sbXML, KEY_STAT );
//    }
//    
//    /**
//     * Write a stat into a JSON Object
//     * @param json The JSON Object
//     * @param stat The stat
//     */
//    private void addStatJson( JSONObject json, Stat stat )
//    {
//        JSONObject jsonStat = new JSONObject(  );
//        jsonStat.accumulate( KEY_ID, stat.getId( ) );
//        jsonStat.accumulate( KEY_DESCRIPTION, stat.getDescription( ) );
//        jsonStat.accumulate( KEY_REQUETE_SQL, stat.getRequeteSql( ) );
//        json.accumulate( KEY_STAT, jsonStat );
//    }
}