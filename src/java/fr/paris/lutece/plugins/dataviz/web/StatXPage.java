/*
 * Copyright (c) 2002-2013, Mairie de Paris
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
package fr.paris.lutece.plugins.dataviz.web;
 
import fr.paris.lutece.plugins.dataviz.business.Stat;
import fr.paris.lutece.plugins.dataviz.business.StatHome;
import fr.paris.lutece.plugins.dataviz.business.StatList;
import fr.paris.lutece.plugins.dataviz.business.StatSingle;
import fr.paris.lutece.portal.service.message.SiteMessage;
import fr.paris.lutece.portal.service.message.SiteMessageException;
import fr.paris.lutece.portal.service.message.SiteMessageService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.url.UrlItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 
import java.util.Map; 
import javax.servlet.http.HttpServletRequest;

/**
 * This class provides the user interface to manage Stat xpages ( manage, create, modify, remove )
 */
 
@Controller( xpageName = "stat" , pageTitleI18nKey = "dataviz.xpage.stat.pageTitle" , pagePathI18nKey = "dataviz.xpage.stat.pagePathLabel" )
public class StatXPage extends MVCApplication
{
    // Templates
    private static final String TEMPLATE_MANAGE_STATS="/skin/plugins/dataviz/manage_stats.html";
    private static final String TEMPLATE_CREATE_STAT="/skin/plugins/dataviz/create_stat.html";
    private static final String TEMPLATE_MODIFY_STAT="/skin/plugins/dataviz/modify_stat.html";
    private static final String TEMPLATE_STAT = "/skin/plugins/dataviz/dataviz.html";
    
    // JSP
    private static final String JSP_PAGE_PORTAL = "jsp/site/Portal.jsp";
    
    // Parameters
    private static final String PARAMETER_ID_STAT="id";
    
    private static final String PARAM_ACTION = "action";
    private static final String PARAM_PAGE = "page";
    
    // Markers
    private static final String MARK_STAT_LIST = "stat_list";
    private static final String MARK_STAT = "stat";
    public static final String MARK_LIST_SINGLE = "list_Single";
    public static final String MARK_LIST_LIST = "list_List";
    
    // Message
    private static final String MESSAGE_CONFIRM_REMOVE_STAT = "dataviz.message.confirmRemoveStat";
    
    // Views
    private static final String VIEW_HOME = "home";
    private static final String VIEW_MANAGE_STATS = "manageStats";
    private static final String VIEW_CREATE_STAT = "createStat";
    private static final String VIEW_MODIFY_STAT = "modifyStat";

    // Actions
    private static final String ACTION_CREATE_STAT = "createStat";
    private static final String ACTION_MODIFY_STAT= "modifyStat";
    private static final String ACTION_REMOVE_STAT = "removeStat";
    private static final String ACTION_CONFIRM_REMOVE_STAT = "confirmRemoveStat";

    // Infos
    private static final String INFO_STAT_CREATED = "dataviz.info.stat.created";
    private static final String INFO_STAT_UPDATED = "dataviz.info.stat.updated";
    private static final String INFO_STAT_REMOVED = "dataviz.info.stat.removed";
    
    // Session variable to store working values
    private Stat _stat;
    
    /**
     * Returns the form of main page
     *
     * @param request The Http request
     * @return the html code of the main page
     */
    @View( value = VIEW_HOME , defaultView = true )
    public XPage viewHome( HttpServletRequest request )
    {
        Map<String, Object> model = new HashMap<String, Object>(  );
        
        List<StatSingle> listSimpleStat = SpringContextService.getBeansOfType( StatSingle.class );
        ReferenceList listSingle = new ReferenceList ( );
        
        for ( StatSingle singleResult : listSimpleStat )
        {
            listSingle.addItem (singleResult.getName(), singleResult.getResult().toString() );
        }
        model.put( MARK_LIST_SINGLE, listSingle );
        
        List<StatList> listBeanStatList = SpringContextService.getBeansOfType( StatList.class );
        List<Stat> listStatList = new ArrayList ( );
        
        for ( StatList statResult : listBeanStatList )
        {
            Stat item = new Stat( );
            item.setName( statResult.getName( ));
            item.setList( (ReferenceList) statResult.getResult ( ));
            listStatList.add( item );
        }
        model.put (MARK_LIST_LIST, listStatList);

        return getXPage( TEMPLATE_STAT, request.getLocale(  ), model );
    }
    
    @View( value = VIEW_MANAGE_STATS)
    public XPage getManageStats( HttpServletRequest request )
    {
        _stat = null;
        Map<String, Object> model = getModel(  );
        model.put( MARK_STAT_LIST, StatHome.getStatsList(  ) );

        return getXPage( TEMPLATE_MANAGE_STATS, request.getLocale(  ), model );
    }

    /**
     * Returns the form to create a stat
     *
     * @param request The Http request
     * @return the html code of the stat form
     */
    @View( VIEW_CREATE_STAT )
    public XPage getCreateStat( HttpServletRequest request )
    {
        _stat = ( _stat != null ) ? _stat : new Stat(  );

        Map<String, Object> model = getModel(  );
        model.put( MARK_STAT, _stat );
           
        return getXPage( TEMPLATE_CREATE_STAT, request.getLocale(  ), model );
    }

    /**
     * Process the data capture form of a new stat
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_STAT )
    public XPage doCreateStat( HttpServletRequest request )
    {
        populate( _stat, request );

        // Check constraints
        if ( !validateBean( _stat, getLocale( request ) ) )
        {
            return redirectView( request, VIEW_CREATE_STAT );
        }

        StatHome.create( _stat );
        addInfo( INFO_STAT_CREATED, getLocale( request ) );

        return redirectView( request, VIEW_MANAGE_STATS );
    }

    /**
     * Manages the removal form of a stat whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_STAT )
    public XPage getConfirmRemoveStat( HttpServletRequest request ) throws SiteMessageException
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_STAT ) );
        UrlItem url = new UrlItem( JSP_PAGE_PORTAL );
        url.addParameter( PARAM_PAGE, MARK_STAT );
        url.addParameter( PARAM_ACTION, ACTION_REMOVE_STAT );
        url.addParameter( PARAMETER_ID_STAT, nId );
        
        SiteMessageService.setMessage(request, MESSAGE_CONFIRM_REMOVE_STAT, SiteMessage.TYPE_CONFIRMATION, url.getUrl(  ));
        return null;
    }

    /**
     * Handles the removal form of a stat
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage stats
     */
    @Action( ACTION_REMOVE_STAT )
    public XPage doRemoveStat( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_STAT ) );
        StatHome.remove( nId );
        addInfo( INFO_STAT_REMOVED, getLocale( request ) );

        return redirectView( request, VIEW_MANAGE_STATS );
    }

    /**
     * Returns the form to update info about a stat
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_STAT )
    public XPage getModifyStat( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_STAT ) );

        if ( _stat == null  || ( _stat.getId(  ) != nId ))
        {
            _stat = StatHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_STAT, _stat );
        
        return getXPage( TEMPLATE_MODIFY_STAT, request.getLocale(  ), model );
    }

    /**
     * Process the change form of a stat
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_STAT )
    public XPage doModifyStat( HttpServletRequest request )
    {
        populate( _stat, request );

        // Check constraints
        if ( !validateBean( _stat, getLocale( request ) ) )
        {
            return redirect( request, VIEW_MODIFY_STAT, PARAMETER_ID_STAT, _stat.getId(  ) );
        }

        StatHome.update( _stat );
        addInfo( INFO_STAT_UPDATED, getLocale( request ) );

        return redirectView( request, VIEW_MANAGE_STATS );
    }
}
