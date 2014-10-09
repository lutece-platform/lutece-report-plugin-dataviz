/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.dataviz.service;

import fr.paris.lutece.plugins.dataviz.business.ISqlStat;
import fr.paris.lutece.plugins.dataviz.business.IStat;
import fr.paris.lutece.portal.service.cache.AbstractCacheableService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.json.JsonResponse;
import fr.paris.lutece.util.json.JsonUtil;
import java.util.List;


/**
 *
 */
public class StatsService extends AbstractCacheableService
{
    private static final String CACHE_SERVICE_NAME = "Dataviz Stats Cache";
    private static StatsService _singleton;
    private static List<ISqlStat> _listStat;

    private StatsService(  )
    {
    }
/**
 * @return The singleton
 */
    public static synchronized StatsService instance(  )
    {
        if ( _singleton == null )
        {
            _singleton = new StatsService(  );
            _singleton.init(  );
        }

        return _singleton;
    }

    /**
     *  Init the Cache
     */
    private void init(  )
    {
        initCache();
        
        _listStat = SpringContextService.getBeansOfType( ISqlStat.class );
    }

    /**
     * Retrieves the stat whose name is pass in parameter
     * @param strStatName
     * @return strResult
     * @throws StatNotFoundException 
     */
    public String getStat( String strStatName ) throws StatNotFoundException
    {
        _listStat = SpringContextService.getBeansOfType( ISqlStat.class );
        String strResult = (String) getFromCache( strStatName.toLowerCase(  ) );

        if ( strResult == null )
        {
            boolean bFound = false;

            for ( ISqlStat stat : _listStat )
            {
                if ( strStatName.equalsIgnoreCase( stat.getName(  ) ) )
                {
                    JsonResponse json = new JsonResponse( stat.getResult(  ) );
                    strResult = JsonUtil.buildJsonResponse( json );
                    putInCache( strStatName, strResult );
                    bFound = true;

                    break;
                }
            }

            if ( !bFound )
            {
                throw new StatNotFoundException( strStatName );
            }
        }

        return strResult;
    }

    @Override
    public String getName(  )
    {
        return CACHE_SERVICE_NAME;
    }
}
