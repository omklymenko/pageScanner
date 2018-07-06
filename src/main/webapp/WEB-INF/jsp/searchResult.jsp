<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Search results</title>
    <h1 id="searchStatus">Searching...</h1>
    <button onclick="stopSearch()">Stop search</button>
    <button onclick="startNewSearch()">Start new search</button>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<h2>Search results:</h2>
<table id="results" data-toggle="table">
    <thead>
    <tr>
        <th data-field="index">#</th>
        <th data-field="link">URL</th>
        <th data-field="status">Status</th>
        <th data-field="comment">Comment</th>
    </tr>
    </thead>
</table>

<script type="text/javascript">
    var resultsTable = $('#results');
    var updateInterval = 500;

    function updateRow(item, index) {
        resultsTable.bootstrapTable('updateRow', {index: index, row: item});
    }

    function refreshResults() {
        $.ajax({
            type: 'GET',
            url: '/updateResults',
            success: function (data) {
                //add indexes, just for UI
                data.pageList.forEach(function (item, index) {
                    item.index = index + 1;
                });
                currentSize = resultsTable.bootstrapTable('getData').length;
                //update existing rows
                rowsToUpdate = data.pageList.slice(0, currentSize);
                rowsToUpdate.forEach(updateRow);

                //add other new rows
                resultsTable.bootstrapTable('append', data.pageList.slice(rowsToUpdate.length));

                //repeat while searchFinished = false, with interval 500ms
                setTimeout(function () {
                    if (!data.searchFinished) {
                        refreshResults();
                    }
                }, updateInterval);
            }
        });
    }

    function stopSearch() {
        $.ajax({
            type: 'GET',
            url: '/stopSearch',
            success: function (data) {
                $('#searchStatus').html("Search stopped")
            }
        });
    }
    refreshResults();

    function startNewSearch() {
//        $('#results tbody tr').remove();
        window.location = '/';
//        var table = document.getElementById("results");
//        Table.innerHTML = "";
    }

</script>
</body>
</html>