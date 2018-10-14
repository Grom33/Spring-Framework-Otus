$(function () {
    updateView();
});

function updateView() {
    $("#table").empty();
    $.get(genreRestURL, function (data) {
        $.each(data, function (key, value) {
                $("<tr>").appendTo("#table");
                $("<td>" + value.id + "</td>").appendTo("#table");
                $("<td><a href='/genres/" + value.id + "'>" + value.name + "</a></td>").appendTo("#table");
                $("<td><a  class= 'btn btn-danger' onclick='deleteGenre(" + value.id + ")'>remove</a></td>").appendTo("#table");
                $("</tr>").appendTo("#table");
            }
        )
    });
}

function deleteGenre(id) {
    deleteItem(genreRestURL, id);
}

function addGenre() {
    addItem(genreRestURL,
        JSON.stringify({
            id: $("#id").val(),
            name: $("#editName").val()
        }));
}

function cleanDialog(){
    $("#editName").val("");
}