$(function () {
    updateView();
});

function updateView() {
    $.get(authorRestURL + $("#id").val(), function (data) {
            $("#name").text(data.name);
            $("#editName").val(data.name);
        }
    )
}

function saveAuthor() {
    updateItem(authorRestURL,
        JSON.stringify({
            id: $("#id").val(),
            name: $("#editName").val()
        }));
}