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
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.url.UrlItem;

import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;


/**
 * This class provides the user interface to manage Stat features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageStats.jsp", controllerPath = "jsp/admin/plugins/dataviz/", right = "DATAVIZ_MANAGEMENT_STAT" )
public class StatJspBean extends ManageStatJspBean
{

    ////////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_STATS="/admin/plugins/dataviz/manage_stats.html";
    private static final String TEMPLATE_CREATE_STAT="/admin/plugins/dataviz/create_stat.html";
    private static final String TEMPLATE_MODIFY_STAT="/admin/plugins/dataviz/modify_stat.html";


    // Parameters
    private static final String PARAMETER_ID_STAT="id";


    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_STATS = "dataviz.manage_stats.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_STAT = "dataviz.modify_stat.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_STAT = "dataviz.create_stat.pageTitle";

    // Markers
    private static final String MARK_STAT_LIST = "stat_list";
    private static final String MARK_STAT = "stat";

    private static final String JSP_MANAGE_STATS = "jsp/admin/plugins/dataviz/ManageStats.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_STAT = "dataviz.message.confirmRemoveStat";
    private static final String PROPERTY_DEFAULT_LIST_STAT_PER_PAGE = "dataviz.listStats.itemsPerPage";
 
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "dataviz.model.entity.stat.attribute.";

    // Views
    private static final String VIEW_MANAGE_STATS = "manageStats";
    private static final String VIEW_CREATE_STAT = "createStat";
    private static final String VIEW_MODIFY_STAT = "modifyStat";

    // Actions
    private static final String ACTION_CREATE_STAT = "createStat";
    private static final String ACTION_MODIFY_STAT = "modifyStat";
    private static final String ACTION_REMOVE_STAT = "removeStat";
    private static final String ACTION_CONFIRM_REMOVE_STAT = "confirmRemoveStat";

    // Infos
    private static final String INFO_STAT_CREATED = "dataviz.info.stat.created";
    private static final String INFO_STAT_UPDATED = "dataviz.info.stat.updated";
    private static final String INFO_STAT_REMOVED = "dataviz.info.stat.removed";
    
    // Session variable to store working values
    private Stat _stat;
    
    
    @View( value = VIEW_MANAGE_STATS, defaultView = true )
    public String getManageStats( HttpServletRequest request )
    {
        _stat = null;
        _strCurrentPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX, _strCurrentPageIndex );
        _nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( PROPERTY_DEFAULT_LIST_STAT_PER_PAGE, 50 );
        _nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage,
                _nDefaultItemsPerPage );

        UrlItem url = new UrlItem( JSP_MANAGE_STATS );
        String strUrl = url.getUrl(  );
        List<Stat> listStats = (List<Stat>) StatHome.getStatsList(  );

        // PAGINATOR
        LocalizedPaginator paginator = new LocalizedPaginator( listStats, _nItemsPerPage, strUrl,
                PARAMETER_PAGE_INDEX, _strCurrentPageIndex, getLocale(  ) );

        Map<String, Object> model = getModel(  );

        model.put( MARK_NB_ITEMS_PER_PAGE, "" + _nItemsPerPage );
        model.put( MARK_PAGINATOR, paginator );
        model.put( MARK_STAT_LIST, paginator.getPageItems(  ) );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_STATS, TEMPLATE_MANAGE_STATS, model );
    }

    /**
     * Returns the form to create a stat
     *
     * @param request The Http request
     * @return the html code of the stat form
     */
    @View( VIEW_CREATE_STAT )
    public String getCreateStat( HttpServletRequest request )
    {
        _stat = ( _stat != null ) ? _stat : new Stat(  );

        Map<String, Object> model = getModel(  );
        model.put( MARK_STAT, _stat );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_STAT, TEMPLATE_CREATE_STAT, model );
    }

    /**
     * Process the data capture form of a new stat
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_STAT )
    public String doCreateStat( HttpServletRequest request )
    {
        populate( _stat, request );

        // Check constraints
        if ( !validateBean( _stat, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_STAT );
        }

        StatHome.create( _stat );
        addInfo( INFO_STAT_CREATED, getLocale(  ) );

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
    public String getConfirmRemoveStat( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_STAT ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_STAT ) );
        url.addParameter( PARAMETER_ID_STAT, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_STAT,
                url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a stat
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage stats
     */
    @Action( ACTION_REMOVE_STAT )
    public String doRemoveStat( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_STAT ) );
        StatHome.remove( nId );
        addInfo( INFO_STAT_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_STATS );
    }

    /**
     * Returns the form to update info about a stat
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_STAT )
    public String getModifyStat( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_STAT ) );

        if ( _stat == null || ( _stat.getId(  ) != nId ))
        {
            _stat = StatHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_STAT, _stat );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_STAT, TEMPLATE_MODIFY_STAT, model );
    }

    /**
     * Process the change form of a stat
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_STAT )
    public String doModifyStat( HttpServletRequest request )
    {
        populate( _stat, request );

        // Check constraints
        if ( !validateBean( _stat, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_STAT, PARAMETER_ID_STAT, _stat.getId(  ) );
        }

        StatHome.update( _stat );
        addInfo( INFO_STAT_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_STATS );
    }
}
