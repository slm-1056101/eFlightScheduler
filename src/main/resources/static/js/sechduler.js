$( document ).ready(function() {
  function ajaxGet(){
    $.ajax({
      type : "GET",
      url : "localhost:8080/eflight/api/schedule/4",
      success: function(result){
        if(result.status == "Done"){
          var sechduleList = "";
          $.each(result.data, function(i, schedule){
            var schedule = "Id = " + i + ", flightId = " + schedule.flight.id + ", flightName = " + schedule.flight.airline + "<br>";
            $('#getResultDiv .list-group').append(schedule)
              });
          console.log("Success: ", result);
        }
      },
    });  
  }
})