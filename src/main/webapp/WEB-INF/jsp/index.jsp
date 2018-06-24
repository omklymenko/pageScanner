<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<h3>Welcome</h3>
<form:form method="POST" action="/searchResult" modelAttribute="searchForm">
    <table>
        <tr>
            <td><form:label path="url">URL: </form:label></td>
            <td><form:input path="url"/></td>
        </tr>
        <tr>
            <td><form:label path="textToSearch">Text to search: </form:label></td>
            <td><form:input path="textToSearch"/></td>
        </tr>
        <tr>
            <td><form:label path="numOfThreads">Number of threads: </form:label></td>
            <td><form:input path="numOfThreads"/></td>
        </tr>
        <tr>
            <td><form:label path="maxNumberOfUrl">Max number of URL: </form:label></td>
            <td><form:input path="maxNumberOfUrl"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>