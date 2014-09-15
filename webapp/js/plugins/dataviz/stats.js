/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $(".stats").slideUp();
    $("#singleStat").click(function(){
        $('div[id*="div_"]').slideUp();
        $("#singleStats").slideToggle();
    });
    $('button[id*="buttons_"]').click(function(){
        var divName = $(this).text();
        if ($("#div_" + divName).is(':visible')){
            $("#div_" + divName).slideToggle();
        }
        else {
            $(".stats").slideUp();
            $("#div_" + divName).slideToggle();
        }
        
    });
    $('table.stat').each(function() {
        var listName = $(this).parent().attr('class');
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
 //           var tabtmp.push()
            data.push([tabCode[i], tabName[i]]);
        }
        var plot1 = jQuery.jqplot("pie_" + listName, [data],
                {
                    seriesDefaults: {
// Make this a pie chart.
                        renderer: jQuery.jqplot.PieRenderer,
                        rendererOptions: {
// Put data labels on the pie slices.
// By default, labels show the percentage of the slice.
                            showDataLabels: true
                        }
                    },
                    legend: {show: true, location: 'e'}
                }
        );
        var s1 = tabName;
// Can specify a custom tick Array.
// Ticks should match up one for each y value (category) in the series.
        var ticks = tabCode;

        var plot1 = $.jqplot("bar_" + listName, [s1], {
// The "seriesDefaults" option is an options object that will
// be applied to all series in the chart.
            seriesDefaults: {
                renderer: $.jqplot.BarRenderer,
                rendererOptions: {fillToZero: true}
            },
// Custom labels for the series are specified with the "label"
// option on the series option.  Here a series option object
// is specified for each series.
            series: [
                {label: $('td.statName')}
            ],
// Show the legend and put it outside the grid, but inside the
// plot container, shrinking the grid to accomodate the legend.
// A value of "outside" would not shrink the grid and allow
// the legend to overflow the container.
            legend: {
                show: true,
                placement: 'outsideGrid'
            },
            axes: {
// Use a category axis on the x axis and use our custom ticks.
                xaxis: {
                    renderer: $.jqplot.CategoryAxisRenderer,
                    ticks: ticks
                },
// Pad the y axis just a little so bars can get close to, but
// not touch, the grid boundaries.  1.2 is the default padding.
                yaxis: {
                    pad: 1.05,
                    tickOptions: {formatString: '%d'}
                }
            }
        });
        $("#pie_" + listName).slideUp();
        $("#button_" + listName).click(function(){
            $('#bar_' + listName).slideToggle();
            $("#pie_" + listName).slideToggle();
        });
    });
});
