$(function () {
    updateView();
});

function updateView() {
    $("#table").empty();
    $.get(bookRestURL, function (data) {
        $.each(data, function (key, value) {
                $("<tr>").appendTo("#table");
                $("<td>" + value.id + "</td>").appendTo("#table");
                $("<td><a href='/books/" + value.id + "'>" + value.name + "</a></td>").appendTo("#table");
                $("<td>" + onlyName(value.genres) + "</td>").appendTo("#table");
                $("<td><a  class= 'btn btn-danger' onclick='deleteBook(" + value.id + ")'>remove</a></td>").appendTo("#table");
                $("</tr>").appendTo("#table");
            }
        )
    });
}

function onlyName(data) {
    var result = "";
    $.each(data, function (key, value) {
            result = result + " " + value.name + ",";
        }
    );
    return result.slice(0, -1);
}

function cleanDialog() {
    $("#editName").val("");
    $("#editDescription").val("");
    loadAuthors();
    loadGenres();
}

function loadAuthors() {
    $("#authors").empty();
    $.get(authorRestURL, function (data) {
        $.each(data, function (key, value) {
            $("<option value='" + value.id + "' id='authorOption_" + value.id + "'>" + value.name + "</option>").appendTo("#authors");
        })
    })
}

function loadGenres() {
    $("#genres").empty();
    $.get(genreRestURL, function (data) {
        $.each(data, function (key, value) {
            $("<option value='" + value.id + "' id='genreOption_" + value.id + "'>" + value.name + "</option>").appendTo("#genres");
        })
    })
}

function addBook() {
    addItem(bookRestURL,
        JSON.stringify({
            id: $("#id").val(),
            name: $("#editName").val(),
            description: $("#editDescription").val(),
            genres: getGenres(),
            authors: getAuthors()
        }));
}

function getAuthors() {
    var result = [];
    $.each($("#authors").val(), function (key, value) {
        var author = {};
        author.id = value;
        author.name = $("#authorOption_" + value).text();
        result.push(author);
    });
    return result;
}

function getGenres() {
    var result = [];
    $.each($("#genres").val(), function (key, value) {
        var genre = {};
        genre.id = value;
        genre.name = $("#genreOption_" + value).text();
        result.push(genre);
    });
    return result;
}

function deleteBook(id) {
    deleteItem(bookRestURL, id);
}
