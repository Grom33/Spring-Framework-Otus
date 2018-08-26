$(function () {
    updateView();
    getComments();
});

function updateView() {
    $.get(bookRestURL + $("#id").val(), function (data) {
            $("#bookTitle").text(data.name);
            $("#bookDescription").text(data.description);
            fillAuthorField(data.authors);
            fillGenreField(data.genres);

            $("#editName").val(data.name);
        }
    )
}

function fillAuthorField(data) {
    $("#authorsList").empty();
    $.each(data, function (key, value) {
            $("<li>" + value.name + "</li>").appendTo("#authorsList");
        }
    )
}

function fillGenreField(data) {
    $("#genresList").empty();
    $.each(data, function (key, value) {
            $("<li>" + value.name + "</li>").appendTo("#genresList");
        }
    )
}

function fillDialog() {
    $.get(bookRestURL + $("#id").val(), function (data) {
        $("#editName").val(data.name);
        $("#editDescription").val(data.description);
        loadAuthors(data);
        loadGenres(data);
    });
}

function loadAuthors(book) {
    var authors = book.authors;
    $("#authors").empty();
    $.get(authorRestURL, function (data) {
        $.each(data, function (key, value) {
            $("<option value='" + value.id +
                "' id='authorOption_" + value.id +
                "'" + selected(authors, value.id) + ">" +
                value.name + "</option>").appendTo("#authors");
        })
    })
}

function loadGenres(book) {
    var genres = book.genres;
    $("#genres").empty();
    $.get(genreRestURL, function (data) {
        $.each(data, function (key, value) {
            $("<option value='" + value.id +
                "' id='genreOption_" + value.id + "' " +
                selected(genres, value.id) +
                ">" + value.name + "</option>").appendTo("#genres");
        })
    })
}

function selected(array, id) {
    var result = false;
    array.forEach(function (element) {
        if (element.id === id) {
            result = true;
        }
    });

    if (result) {
        return "selected = true";
    } else {
        return "";
    }
}

function saveBook() {

    updateItem(bookRestURL,
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

function getComments() {
    $("#commentsCard").empty();
    var commentRestURL = "/api/books/" + $("#id").val() + "/comments/";
    $.get(commentRestURL, function (data) {
        $.each(data, function (key, value) {
            $("<div class='card-body'>").appendTo("#commentsCard");
            $("<p class='card-text'>" + value.review + "</p>").appendTo("#commentsCard");
            $("</div>").appendTo("#commentsCard");
        })
    })
}

function addComment() {
    var commentRestURL = "/api/books/" + $("#id").val() + "/comments/";
    $.ajax({
        type: "POST",
        url: commentRestURL,
        data: JSON.stringify({
            id: $("#id").val(),
            review: $("#commentEdit").val()
        }),
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Content-type", "application/json");
        },
        success: function () {
            hideDialog()
            getComments();
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("Failed to add! Message:" + thrownError);
        }
    });
    $("#commentEdit").empty();
}