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
            tabCode.push(parseInt($(this).html()));
        })
        $(this).find('td.nameValue').each(function() {
            tabName.push(parseInt($(this).html()));
        })
        var data = [];
        for (i = 0; i < tabCode.length; i++)
        {
            data.push([tabCode[i], tabName[i]]);
        }
        if ( $(this).attr('class') == "stat True")
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
            var s1 = tabName;
            var ticks = tabCode;

            var plot1 = $.jqplot("bar_" + listName, [s1], {
                seriesDefaults: {
                    renderer: $.jqplot.BarRenderer,
                    rendererOptions: {fillToZero: true}
                },
                series: [
                    {label: $('td.statName')}
                ],
                legend: {
                    show: false
                },
                axes: {
                    xaxis: {
                        renderer: $.jqplot.CategoryAxisRenderer,
                        ticks: ticks
                    },
                    yaxis: {
                        pad: 1.05,
                        tickOptions: {formatString: '%d'}
                    }
                }
            });
        }
        $("#button_" + listName).click(function(){
            $('#div_bar_' + listName).slideToggle(0);
            $("#div_pie_" + listName).slideToggle(0);
        });
    });
    $('button[id*="buttons_"]').click(function(){
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