/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('table.stat').each(function() {
        var listName = $(this).parent().attr('id');
        var tabCode = [];
        var tabName = [];
        $(this).find('td.codeValue').each(function() {
            tabCode.push($(this).html());
        })
        $(this).find('td.nameValue').each(function() {
            tabName.push(parseInt($(this).html()));
        })
        var data = [];
        for (i = 0; i < tabCode.length; i++)
        {
            data.push([tabCode[i], tabName[i]]);
        }
        if ( $(this).attr('class') != "stat False")
        {
            var plot1 = jQuery.jqplot("pie_" + listName, [data],
                    {
                        seriesDefaults: {
                            renderer: jQuery.jqplot.PieRenderer,
                            rendererOptions: {
                                showDataLabels: true
                            }
                        },
                        legend: {show: true, location: 'e', rendererOptions: {numberColumns: 2}}
                    }
            );
            var ticks = tabCode;
            var plot1 = $.jqplot("bar_" + listName, [data], {
                seriesDefaults: {
                    renderer: $.jqplot.BarRenderer,
                    rendererOptions: {fillToZero: true}
                },
                legend: { show: false },
                axesDefaults: {
                    tickRenderer: $.jqplot.CanvasAxisTickRenderer ,
                     tickOptions: {
                        fontSize: '8pt'
                    }
                },
                axes: {
                    xaxis: {
                        renderer: $.jqplot.CategoryAxisRenderer,
                        tickOptions: {
                             angle: 30,
                            formatString: '%b&nbsp;%#d'   // format string to use with the axis tick formatter
                        }
                    },
                    yaxis: {
                        pad: 1.05,
                        tickOptions: {formatString: '%d'}
                    }
                }
            });
        
        /*
        * graph type curl source data bean VotePerDay
        * @type @exp;jQuery@call;jqplot|@exp;$@call;jqplot|@exp;$@call;jqplot
        *
        * For next graph we need a date for frist field 
        * date format : dd-jan-yy
        * 
        */
        var reg=new RegExp("^[0-9]{2}[-]{1}[a-zA-Z]{3}[-]{1}[0-9]{2}$","g");
        if (reg.test(data[0][0]))
        {
            var plot1 = jQuery.jqplot("curl_" + listName, [data],
            {
                axes:{
                    xaxis:{
                        renderer:$.jqplot.DateAxisRenderer,
                        tickOptions:{ formatString:'%b&nbsp;%#d' }
                    },
                    yaxis:{
                        tickOptions:{ formatString:'%d' }
                    }
                },
                highlighter: {
                    show: true,
                    sizeAdjust: 7.5
                },
                cursor: { show: false }
            });
        }
    }
      
    });
    $('[id*="buttons_"]').click(function(){
        var divName = $(this).attr("name");
        if ($(".div_" + divName).is(':visible')){
            $(".div_" + divName).slideUp(200);
        }
        else {
            $('.toggle').slideUp(0);
            $(".div_" + divName).slideToggle(200);
        }
    });
    $('.toggle').slideUp(0);
});