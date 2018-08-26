$(function () {
    updateView();
});

function updateView() {
    $.get(authorRestURL, function (data) {
            $("#table").empty();
            $.each(data, function (key, value) {
                    $("<tr>").appendTo("#table");
                    $("<td>" + value.id + "</td>").appendTo("#table");
                    $("<td><a href='/authors/" + value.id + "'>" + value.name + "</a></td>").appendTo("#table");
                    $("<td><a  class= 'btn btn-danger' onclick='deleteAuthor(" + value.id + ")'>remove</a></td>").appendTo("#table");
                    $("</tr>").appendTo("#table");
                }
            )
        }
    );
}

function deleteAuthor(id) {
    deleteItem(authorRestURL, id)
}

function addAuthor() {
    addItem(authorRestURL, JSON.stringify({
        id: $("#id").val(),
        name: $("#editName").val()
    }));

}

function cleanDialog(){
    $("#editName").val("");
}