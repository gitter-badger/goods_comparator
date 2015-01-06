$(function(){ 
    $(".search_ico").click(function(){ 
        $(".search_bar").toggleClass('search_open'); 
        var keys = $("#search").val(); 
        if(keys.length>2){ 
            $("#search").val(''); 
            $("#myform").submit(); 
        }else{ 
            return false; 
        } 
    }); 
}); 