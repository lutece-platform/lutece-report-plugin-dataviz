/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.dataviz.web;

import fr.paris.lutece.plugins.dataviz.service.StatNotFoundException;
import fr.paris.lutece.plugins.dataviz.service.StatsService;
import fr.paris.lutece.util.json.ErrorJsonResponse;
import fr.paris.lutece.util.json.JsonUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author evrardmax
 */
public class StatServlet extends HttpServlet
{
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String PARAMETER_NAME = "name";

    @Override
    protected void service( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        String strName = req.getParameter( PARAMETER_NAME );

        String strOutput;

        try
        {
            strOutput = StatsService.instance(  ).getStat( strName );
        }
        catch ( StatNotFoundException ex )
        {
            ErrorJsonResponse error = new ErrorJsonResponse( "1", ex.getMessage(  ) );
            strOutput = JsonUtil.buildJsonResponse( error );
        }

        ServletOutputStream out = resp.getOutputStream(  );

        resp.setContentType( CONTENT_TYPE_JSON );
        out.print( strOutput );
        out.flush(  );
        out.close(  );
    }
}
