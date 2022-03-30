$(document).ready(function() {
    $("#products").click(function() {
        $("#main_left_div").show();
        $("#main_right_div").show();
        $("#products").prop('disabled', false);
    });
 });

$(document).ready(function() {
    $("#add_group").click(function() {
        // $("#top_div").children().prop('disabled', true);
        $("#main_left_div").hide();
        $("#main_right_div2").hide();
        $("#add_group").prop('disabled', true);
        $("#edit_group").prop('disabled', true);
        $("#delete_group").prop('disabled', true);
        $("#add_group_window").show();
    });
});

 // $( document ).ready(function() {
 //    $("#fridge").click(function() {
 //        $("#main_left_div").hide();
 //        $("#main_right_div").hide();
 //    });
 // });

 // $( document ).ready(function() {
 //    $("#product_choice").click(function() {
 //        $("#test").hide();
 //       $("#testProduct").show();
 //    });
 // });


 // $( document ).ready(function() {
 //     $("#editGroupButton").click(function() {
 //        $("#main_left_div").hide();
 //        $("#main_right_div").children().prop('disabled', true);
 //     });
 // } );

$(document).ready(function () {
    var apiRoot = 'http://localhost:8080/api/';
    var datatableRowTemplate = $('[data-datatable-row-template]').children()[0];
    var groupContainer = $('[data-group-container]');

    // init
    getAllGroups();

    function createElement(data) {
        var element = $(datatableRowTemplate).clone();

        element.attr('data-group-id', data.id);
        element.find('[data-group-name-section] [data-group-name-paragraph]').text(data.title);
        element.find('[data-group-name-section] [data-group-name-input]').val(data.title);

        element.find('[data-group-content-section] [data-group-content-paragraph]').text(data.content);
        element.find('[data-group-content-section] [data-group-content-input]').val(data.content);

        return element;
    }

    function handleDatatableRender(data) {
        groupContainer.empty();
        data.forEach(function(group) {
            createElement(group).appendTo(groupContainer);
        });
    }

    function getAllGroups() {
        var requestUrl = apiRoot + 'group/all';

        $.ajax({
            url: requestUrl,
            method: 'GET',
            success: handleDatatableRender
        });
    }

    function handleGroupUpdateRequest() {
        var parentEl = $(this).parent().parent();
        var groupId = parentEl.attr('data-group-id');
        var groupTitle = parentEl.find('[data-group-name-input]').val();
        var groupContent = parentEl.find('[data-group-content-input]').val();
        var requestUrl = apiRoot + 'group';

        $.ajax({
            url: requestUrl,
            method: "PUT",
            processData: false,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify({
                "id": groupId,
                "title": groupTitle,
                "content": groupContent
            }),
            success: function(data) {
                parentEl.attr('data-group-id', data.id).toggleClass('datatable__row--editing');
                parentEl.find('[data-group-name-paragraph]').text(groupTitle);
                parentEl.find('[data-group-content-paragraph]').text(groupContent);
            }
        });
    }

    function handleGroupDeleteRequest() {
        var parentEl = $(this).parent().parent();
        var groupId = parentEl.attr('data-group-id');
        var requestUrl = apiRoot + 'group';

        $.ajax({
            url: requestUrl + '?' + $.param({
                "id": groupId
            }),
            method: 'DELETE',
            success: function() {
                parentEl.slideUp(400, function() { parentEl.remove(); });
            }
        })
    }

    function handleGroupSubmitRequest(event) {
        event.preventDefault();

        var groupTitle = $("#titleGroup").val();
        var requestUrl = apiRoot + 'group';

        $.ajax({
            url: requestUrl,
            method: 'POST',
            processData: false,
            contentType: "application/json; charset=utf-8",
            dataType: 'JSON',
            data: JSON.stringify({
                "name": groupTitle,
            }),
            complete: function(data) {
                if(data.status === 200) {
                    // getAllGroups();
                    console.log("STATUS: " + data.status);
                }
            }
        });
    }

    function toggleEditingState() {
        var parentEl = $(this).parent().parent();
        parentEl.toggleClass('datatable__row--editing');

        var groupTitle = parentEl.find('[data-group-name-paragraph]').text();
        var groupContent = parentEl.find('[data-group-content-paragraph]').text();

        parentEl.find('[data-group-name-input]').val(groupTitle);
        parentEl.find('[data-group-content-input]').val(groupContent);
    }

    $('[data-group-add-form]').on('submit', handleGroupSubmitRequest);

    groupContainer.on('click','[data-group-edit-button]', toggleEditingState);
    groupContainer.on('click','[data-group-edit-abort-button]', toggleEditingState);
    groupContainer.on('click','[data-group-submit-update-button]', handleGroupUpdateRequest);
    groupContainer.on('click','[data-group-delete-button]', handleGroupDeleteRequest);
});

