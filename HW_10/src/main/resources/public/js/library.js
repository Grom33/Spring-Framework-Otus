var bookRestURL = "/api/books/";
var authorRestURL = "/api/authors/";
var genreRestURL = "/api/genres/";

function showDialog() {
    $("#editRow").modal();
}

function hideDialog() {
    $("#editRow").modal("hide");
}

function showAddDialog() {
    cleanDialog();
    showDialog();
}

function showEditDialog() {
    fillDialog();
    showDialog();
}

function deleteItem(rest, id) {
    $.ajax({
        type: "DELETE",
        url: rest + id,
        success: function () {
            updateView();
        },
        error: function(xhr, ajaxOptions, thrownError) {
            alert("Failed to delete! Message:" + xhr.responseText);
        }
    });
}

function addItem(rest, dataItem) {
    $.ajax({
        type: "POST",
        url: rest,
        data: dataItem,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Content-type", "application/json");
        },
        success: function () {
            hideDialog();
            updateView();
        },
        error: function(xhr, ajaxOptions, thrownError) {
            alert("Failed to add! Message:" + thrownError);
        }
    });
}

function updateItem(rest, dataItem) {
    $.ajax({
        type: "PUT",
        url: rest,
        data: dataItem,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Content-type", "application/json");
        },
        success: function () {
            hideDialog();
            updateView();
        }
    });
}
