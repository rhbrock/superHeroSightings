<%-- 
    Document   : login
    Created on : Nov 25, 2018, 8:20:31 AM
    Author     : Roger Brock
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Super Hero Sightings</title>

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/superHeroSightings.css" rel="stylesheet">

    </head>

    <body>

        <header>
            <h1 style='border-top:thick black solid;' >Super Hero Sightings</h1>
            <div id="head"> 
                <nav class="navbar navbar-expand-md navbar-dark bg-dark box-shadow">
                    <button class="navbar-toggler mr-2" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" 
                            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/">Home</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/supers">Supers</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/orgs">Organizations</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/locs">Locations</a>
                            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/sightings">Sightings</a>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="nav-item nav-link text-danger" href="${pageContext.request.contextPath}/displayUserList">
                                    User Admin
                                </a>                       
                            </sec:authorize>
                        </div>
                    </div>	

                    <sec:authorize access="hasRole('ROLE_SIDEKICK')">
                        <div class="d-inline-flex p-2 mr-auto" style="width: 200px;">
                            <button type='button' class='btn btn-primary btn-lg' data-toggle="modal" data-target="#newSighting">New Sighting</button>
                        </div>
                    </sec:authorize>
                </nav>
            </div>
        </header>

        <div class="container">
            <c:if test="${param.login_error == 1}">
                <h3>Wrong id or password!</h3>
            </c:if>
            <form class="form-horizontal" 
                  role="form" 
                  method="post" 
                  action="j_spring_security_check">
                <div class="form-group">
      
                    <div class="col-md-8">
                        <input type="text" 
                               class="form-control" 
                               name="j_username" 
                               placeholder="Username"/>
                    </div>
                </div>
                <div class="form-group">

                    <div class="col-md-8">
                        <input type="text" 
                               class="form-control" 
                               name="j_password" 
                               placeholder="Password"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" 
                               class="btn btn-default" 
                               id="search-button" 
                               value="Sign In"/>
                    </div>
                </div>
            </form>    
        </div>


        <!--New Sighting Window-->     
        <div class="modal fade" id="newSighting" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">New Sighting</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form" 
                              action="newSighting" method="POST">

                            <div class="form-group">
                                <label for="super" class="col-md-4 control-label">Super:</label>
                                <div class="col-md-8">
                                    <select class="form-control" id="super"
                                            name="super" >
                                        <c:forEach var="l" items="${superList}">
                                            <option value="${l.superId}"><c:out value="${l.name}"/>
                                            </c:forEach>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="locName" class="col-md-4 control-label">Location Name</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="LocationName"
                                           name="locName" placeholder="LocationName">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="locDesc" class="col-md-4 control-label">Description:</label>                          
                                <div class="col-md-8">
                                    <textarea type="text" class="form-control" id="locDesc"
                                              name="locDesc" placeholder="Location Description"/></textarea>
                                </div>

                            </div>

                            <div class="form-group">
                                <label for="streetAddress" class="col-md-4 control-label">Street Address</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="streetAddress"
                                           name="streetAddress" placeholder="Street Address">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="zip" class="col-md-4 control-label">Zip Code:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="zip"
                                           name="zip" placeholder="Zip">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="lat" class="col-md-4 control-label">Lat:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="lat"
                                           name="lat" placeholder="Latitude">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="lon" class="col-md-4 control-label">Lon:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="lon"
                                           name="lon" placeholder="Longitude">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="dateTime" class="col-md-4 control-label">Date and Time</label>
                                <div class="col-md-8">
                                    Leave Blank if Sighting is Now!</br>
                                    We'll set it for ya!
                                    <input type="datetime-local" class="form-control" id="dateTime"
                                           name="dateTime" placeholder="Auto-generate on submit"/></a>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-success" value="Submit"/>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </form> 
                    </div>
                </div>
            </div>
        </div>


    </div>

    <footer class="text-muted">

        <p style="text-align:center;">
            <a href="#">Back to top</a>
        </p>

    </footer>


    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCIrN6BGMXAwTrzuTPuz1Rv3UGppyGw8R8&callback=initMap" async defer></script>
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/superHeroSightings.js"></script>

</body>
</html>
