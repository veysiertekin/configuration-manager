<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Reboot.css is for to make page's view almost same at all browsers!-->
    <link rel="stylesheet" href="/css/reboot.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/noty.css">
    <link rel="stylesheet" href="/css/index.css">

    <title>Configuration Manager</title>
</head>
<body>
<br/>
<h3 class="table-header">Configuration Manager</h3>
<br/>
<nav>
    <div class="form-group justify-content-center row">
        <span class="col-3">
            <input type="text" class="form-control" id="search-box" placeholder="search by application name">
        </span>
        <span class="col-1">
            <a class="page-link btn" href="#" id="search-button">Search</a>
        </span>
        <span class="col-1">
            <a class="page-link btn" href="#" id="reset-button">Reset</a>
        </span>
    </div>
</nav>
<div class="form-group justify-content-center row">
    <form>
        <table class="table">
            <tbody>
            <tr>
                <td><input type="text" class="form-control configuration-name" placeholder="Property name"></td>
                <td style="padding-top: 20px">Type</td>
                <td><select class="form-control configuration-type">
                    <option>STRING</option>
                    <option>INTEGER</option>
                    <option>DOUBLE</option>
                    <option>BOOLEAN</option>
                </select></td>
                <td><input type="text" class="form-control configuration-value" placeholder="Property value"></td>
                <td style="padding-top: 20px">Active</td>
                <td><select class="form-control configuration-active">
                    <option>true</option>
                    <option>false</option>
                </select></td>
                <td><input type="text" class="form-control configuration-application-name"
                           placeholder="Application name"></td>
                <td>
                    <button type="button" class="btn btn-dark" href="#" id="save-button">Add New</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
<nav>
    <ul class="pagination justify-content-center">
        <li class="page-item"><a class="page-link btn" href="#">Previous</a></li>
        <li class="page-item"><a class="page-link btn disabled" href="#"></a></li>
        <li class="page-item"><a class="page-link btn" href="#">Next</a></li>
    </ul>
</nav>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Type</th>
        <th scope="col">Value</th>
        <th scope="col">Is Active</th>
        <th scope="col">Application Name</th>
        <th scope="col">Action</th>
        <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody id="configuration-table">
    </tbody>
</table>
<nav>
    <ul class="pagination justify-content-center">
        <li class="page-item"><a class="page-link btn" href="#">Previous</a></li>
        <li class="page-item"><a class="page-link btn disabled" href="#"></a></li>
        <li class="page-item"><a class="page-link btn" href="#">Next</a></li>
    </ul>
</nav>

<script src="/js/jquery.min.js"></script>
<script src="/js/noty.min.js"></script>
<script src="/js/index.js"></script>
</body>
</html>