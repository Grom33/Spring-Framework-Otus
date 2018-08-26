$(function () {
    updateView();
});

function updateView() {
    $.get(genreRestURL + $("#id").val(), function (data) {
            $("#name").text(data.name);
            $("#editName").val(data.name);
        }
    )
}

function saveGenre() {
    updateItem(genreRestURL, JSON.stringify({
        id: $("#id").val(),
        name: $("#editName").val()
    }));
    $("#editName").val("");
}