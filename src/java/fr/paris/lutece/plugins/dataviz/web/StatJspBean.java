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

import fr.paris.lutece.plugins.dataviz.business.IStatList;
import fr.paris.lutece.plugins.dataviz.business.Stat;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.util.ReferenceList;
import java.util.ArrayList;
import java.util.HashMap;
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

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_STATS = "dataviz.manage_stats.pageTitle";

    // Markers
    public static final String MARK_LIST_LIST = "list_List";
    
    // Views
    private static final String VIEW_MANAGE_STATS = "manageStats";
   
    
    @View( value = VIEW_MANAGE_STATS, defaultView = true )
    public String getManageStats( HttpServletRequest request )
    {
        Map<String, Object> model = new HashMap<String, Object>(  );
                       
        List<IStatList> listBeanStatList = SpringContextService.getBeansOfType( IStatList.class );
        List<Stat> listStatList = new ArrayList ( );
        
        for ( IStatList statResult : listBeanStatList )
        {
            Stat item = new Stat( );
            item.setName( statResult.getName( ));
            item.setId( statResult.getId( ));
            item.setList( (ReferenceList) statResult.getList ( ));
            item.setGraphType( statResult.getGraphType());
            listStatList.add( item );
        }
        model.put (MARK_LIST_LIST, listStatList);

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_STATS, TEMPLATE_MANAGE_STATS, model );
    }
}
