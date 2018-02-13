$(document).ready(function () {
    var indexPage = new IndexPage();
    indexPage.loadFirstPage();
});

var IndexPage = function (options) {
    var root = this;

    root.vars = {
        prevButton: $(".pagination li:first-child .page-link"),
        pageProgress: $(".pagination li:nth-child(2) .page-link"),
        nextButton: $(".pagination li:last-child .page-link"),
        configurationTable: $("#configuration-table"),
        saveButton: $("#save-button"),
        searchBox: $("#search-box"),
        searchButton: $("#search-button"),
        resetButton: $("#reset-button"),
        searchMode: "DEFAULT",
        currentPage: 0,
        totalPages: 0,
        content: []
    };

    root.construct = function (options) {
        $.extend(root.vars, options);
    };

    root.prevPage = function () {
        if (root.vars.currentPage > 0) {
            root.vars.currentPage--;
            root.loadPage();
        }
    };

    root.nextPage = function () {
        if (root.vars.totalPages > root.vars.currentPage) {
            root.vars.currentPage++;
            root.loadPage();
        }
    };

    root.loadFirstPage = function () {
        root.loadPage(root.notifyStats);
        root.applyBindings();
    };

    root.loadPage = function (callback) {
        var query = "/api/configurations?pageNumber=" + root.vars.currentPage + "&pageSize=10";
        var searchText = $("#search-box").val();

        if (root.vars.searchMode === "APPLICATION_NAME" && searchText) {
            query = "/api/configurations/by-application-name?applicationName=" + searchText + "&pageNumber=" + root.vars.currentPage + "&pageSize=10"
        }

        $.get(query, function (data) {
            root.vars.content = data.content;
            root.vars.totalPages = data.totalPages;

            root.viewPage();

            if (callback)
                callback(data);
        });
    };

    root.viewPage = function () {
        root.updateTable();
        root.updatePagination();
    };

    root.updateTable = function () {
        root.vars.configurationTable.html("");

        root.vars.content.forEach(function (row) {
            var newRow = $(rowTemplate);
            $(newRow).find(".configuration-id").val(row.id);
            $(newRow).find(".configuration-name").val(row.name);
            $(newRow).find(".configuration-type").val(row.type);
            $(newRow).find(".configuration-value").val(row.value);
            $(newRow).find(".configuration-active").val("" + row.active);
            $(newRow).find(".configuration-application-name").val(row.applicationName);
            root.vars.configurationTable.append(newRow);
        });
    };

    root.updatePagination = function () {
        if (root.vars.currentPage > 0)
            root.vars.prevButton.removeClass("disabled");
        else
            root.vars.prevButton.addClass("disabled");

        root.vars.pageProgress.text((root.vars.currentPage + 1) + " / " + root.vars.totalPages);

        if (root.vars.currentPage + 1 < root.vars.totalPages)
            root.vars.nextButton.removeClass("disabled");
        else
            root.vars.nextButton.addClass("disabled");
    };

    root.applyBindings = function () {
        root.vars.prevButton.on("click", function (e) {
            root.prevPage();
        });
        root.vars.nextButton.on("click", function (e) {
            root.nextPage();
        });
        root.vars.searchBox.keypress(function (e) {
            var key = e.which;
            if (key === 13) {
                root.vars.searchButton.click();
            }
        });
        root.vars.searchButton.on("click", function (e) {
            root.vars.searchMode = "APPLICATION_NAME";
            root.vars.currentPage = 0;
            root.loadPage();
        });
        root.vars.resetButton.on("click", function (e) {
            root.vars.searchMode = "DEFAULT";
            root.vars.currentPage = 0;
            root.loadPage();
        });
        root.vars.saveButton.on("click", function (e) {
            var parentForm = $(this).closest("form");
            var row = collectRow(parentForm);
            root.save(row, function () {
                $(parentForm).trigger("reset");
            });
        });

        $("#configuration-table").on("click", "a.edit", function (e) {
            var parent = $(this).closest("tr");
            var editMode = $(this).attr("edit-mode");
            if (editMode && editMode == "true") {
                var row = collectRow(parent);
                root.update(row);

                $(this).text("Edit");
                $(this).attr("edit-mode", false);
                parent.find("input,select").prop('disabled', true);
            } else {
                $(this).text("Update");
                $(this).attr("edit-mode", true);
                parent.find("input,select").prop('disabled', false);
            }
        });
    };

    root.save = function (row, successCallback, errorCallback) {
        delete row["id"];
        $.ajax({
            url: "/api/configurations",
            beforeSend: function (request) {
                request.setRequestHeader("Content-Type", "application/json");
            },
            type: 'POST',
            data: JSON.stringify(row),
            success: function (result) {
                root.notifySuccess("Save completed successfully!");
                successCallback();
            },
            error: function (xhr, status, errorThrown) {
                root.notifyError(xhr);
                errorCallback();
            }
        });
    };

    root.update = function (row) {
        var row_id = row.id;
        delete row["id"];
        $.ajax({
            url: "/api/configurations/" + row_id,
            beforeSend: function (request) {
                request.setRequestHeader("Content-Type", "application/json");
            },
            type: 'PUT',
            data: JSON.stringify(row),
            success: function (result) {
                root.notifySuccess("Update completed successfully!");
            },
            error: function (xhr, status, errorThrown) {
                root.notifyError(xhr);
            }
        });
    };

    root.notifySuccess = function (mesage) {
        new Noty({
            type: 'success',
            timeout: 1000,
            text: mesage
        }).show();
    };

    root.notifyError = function (xhr) {
        var errorData = JSON.parse(xhr.responseText);
        new Noty({
            type: 'error',
            timeout: 1000,
            text: errorData.message
        }).show();
    };

    root.notifyStats = function (data) {
        new Noty({
            timeout: 3000,
            type: 'info',
            text: 'Total pages: ' + data.totalPages + ", Number of records: " + data.totalElements
        }).show();
    };

    root.construct(options);
};

function collectRow(parent) {
    var row = {};
    row.id = $(parent).find(".configuration-id").val();
    row.name = $(parent).find(".configuration-name").val();
    row.type = $(parent).find(".configuration-type").val();
    row.value = $(parent).find(".configuration-value").val();
    row.active = $(parent).find(".configuration-active").val();
    row.applicationName = $(parent).find(".configuration-application-name").val();
    return row;
}

var rowTemplate = "<tr><th scope=\"row\"><input type=\"text\" class=\"form-control configuration-id\" disabled></th>" +
    "<td><input type=\"text\" class=\"form-control configuration-name\" disabled></td>" +
    "<td><select class=\"form-control configuration-type\" disabled><option>STRING</option><option>INTEGER</option><option>DOUBLE</option><option>BOOLEAN</option></select></td>" +
    "<td><input type=\"text\" class=\"form-control configuration-value\" disabled></td>" +
    "<td><select class=\"form-control configuration-active\" disabled><option>true</option><option>false</option></select></td>" +
    "<td><input type=\"text\" class=\"form-control configuration-application-name\" disabled></td>" +
    "<td><a class=\"page-link btn edit\" href=\"#\">Edit</a></td></tr>";