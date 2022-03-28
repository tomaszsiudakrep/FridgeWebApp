$( document ).ready(function() {
    $("#products").click(function() {
        $("#main_left_div").show();
        $("#main_right_div").show();
    });
 });

 $( document ).ready(function() {
    $("#fridge").click(function() {
        $("#main_left_div").hide();
        $("#main_right_div").hide();
    });
 });

 $( document ).ready(function() {
    $("#product_choice").click(function() {
        $("#test").hide();
       $("#testProduct").show();
    });
 });


 $( document ).ready(function() {
     $("#editGroupButton").click(function() {
        $("#main_left_div").hide();
        $("#main_right_div").children().prop('disabled', true);
     });
 } );