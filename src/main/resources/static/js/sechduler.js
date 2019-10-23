var globe_index = 0;
$(function test() {
    
    var $sechdule = $('#sechdules');

    $.ajax({
                        type: "GET",
                        url: 'http://localhost:8080/eflight/api/schedule/' + globe_index,
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        success: function (data) {
                            console.log("success", data);
                           $.each(data, function (i, sched) {

                               $sechdule.append('<div class="card">'
                        +'<h3 class="title">' + "Flight No"+ sched.flight.id + '</h3>'+
                        '<div class="bar">' +
                            '<div class="emptybar"></div>'+
                           '<div class="filledbar"></div>' +
                        '</div>' +
                        '<div class="info_body">'+

                           '<div class = "breaker_">' +
                                '<div class="icon_div">'+
                                    '<span><i class="fa fa-plane"></i></span>'+
                                '</div>'+
                                '<div class="text_div">'+
                                    '<span>'+ sched.flight.airline +'</span><br>'+
                                '</div>'+
                            '</div>'+

                            '<div class = "breaker_">'+
                                '<div class="icon_div">'+
                                    '<span><i class="fa fa-building"></i></span>'+
                                '</div>'+
                                '<div class="text_div">'+
                                    '<span> Iwoa, USA</span><br>'+
                                '</div>'+
                            '</div>' +

                            '<div class = "breaker_">' +
                                '<div class="icon_div">' +
                                    '<span><i class="fa fa-road"></i></span>' +
                                '</div>' +
                                '<div class="text_div">'+
                                    '<span>'+ sched.runway.code +'</span><br>'+
                                '</div>'+
                           '</div>'+

                            '<div class = "breaker_">'+
                                '<div class="icon_div">'+
                                    '<span><i class="fa fa-clock-o"></i></span>'+
                                '</div>'+
                                '<div class="text_div">'+
                                    '<span>' +  moment(sched.time).endOf('day').fromNow()  + '</span><br>' +
                                '</div>'+
                            '</div>'+

                           '<div class = "breaker_">'+
                                '<div class="icon_div">'+
                                    '<span><i class="fa fa-flag"></i></span>'+
                                '</div>'+
                                '<div class="text_div">'+
                                    '<span>' + sched.status + '</span><br>'+
                                '</div>'+
                            '</div>'+
                        '</div>'+
                '</div>'      
                               );

                               globe_index = i;
                            });                            
                        }
                    }); 

                    setsetTimeout(test(), 1000);
  
});




