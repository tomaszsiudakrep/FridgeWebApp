$( document ).ready(function() {
    $("#products").click(function() {
        $("#main_left_div").show();
        $("#main_right_div").show();
        $("#products").prop('disabled', false);
    });
 });

$( document ).ready(function() {
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
    var groupContainer = $('[data-tasks-container]');

    // init
    getAllTasks();

    function createElement(data) {
        var element = $(datatableRowTemplate).clone();

        element.attr('data-task-id', data.id);
        element.find('[data-task-name-section] [data-task-name-paragraph]').text(data.title);
        element.find('[data-task-name-section] [data-task-name-input]').val(data.title);

        element.find('[data-task-content-section] [data-task-content-paragraph]').text(data.content);
        element.find('[data-task-content-section] [data-task-content-input]').val(data.content);

        return element;
    }

    function handleDatatableRender(data) {
        groupContainer.empty();
        data.forEach(function(group) {
            createElement(group).appendTo(groupContainer);
        });
    }

    function getAllTasks() {
        var requestUrl = apiRoot + 'group/all';

        $.ajax({
            url: requestUrl,
            method: 'GET',
            success: handleDatatableRender
        });
    }

    function handleTaskUpdateRequest() {
        var parentEl = $(this).parent().parent();
        var groupId = parentEl.attr('data-task-id');
        var groupTitle = parentEl.find('[data-task-name-input]').val();
        var groupContent = parentEl.find('[data-task-content-input]').val();
        var requestUrl = apiRoot + 'updateTask';

        $.ajax({
            url: requestUrl,
            method: "PUT",
            processData: false,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify({
                id: groupId,
                title: groupTitle,
                content: groupContent
            }),
            success: function(data) {
                parentEl.attr('data-task-id', data.id).toggleClass('datatable__row--editing');
                parentEl.find('[data-task-name-paragraph]').text(groupTitle);
                parentEl.find('[data-task-content-paragraph]').text(groupContent);
            }
        });
    }

    function handleTaskDeleteRequest() {
        var parentEl = $(this).parent().parent();
        var groupId = parentEl.attr('data-task-id');
        var requestUrl = apiRoot + 'deleteTask';

        $.ajax({
            url: requestUrl + '/?' + $.param({
                groupId: groupId
            }),
            method: 'DELETE',
            success: function() {
                parentEl.slideUp(400, function() { parentEl.remove(); });
            }
        })
    }

    function handleTaskSubmitRequest(event) {
        event.preventDefault();

        // var groupTitle = $(this).find("titleGroup").val();
        var groupTitle = $("#titleGroup").val();
        // var groupContent = $(this).find('[name="content"]').val();
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
                    // getAllTasks();
                    console.log("STATUS: " + data.status);
                }
            }
        });
    }

    function toggleEditingState() {
        var parentEl = $(this).parent().parent();
        parentEl.toggleClass('datatable__row--editing');

        var groupTitle = parentEl.find('[data-task-name-paragraph]').text();
        var groupContent = parentEl.find('[data-task-content-paragraph]').text();

        parentEl.find('[data-task-name-input]').val(groupTitle);
        parentEl.find('[data-task-content-input]').val(groupContent);
    }

    $('[data-task-add-form]').on('submit', handleTaskSubmitRequest);

    groupContainer.on('click','[data-task-edit-button]', toggleEditingState);
    groupContainer.on('click','[data-task-edit-abort-button]', toggleEditingState);
    groupContainer.on('click','[data-task-submit-update-button]', handleTaskUpdateRequest);
    groupContainer.on('click','[data-task-delete-button]', handleTaskDeleteRequest);
});

