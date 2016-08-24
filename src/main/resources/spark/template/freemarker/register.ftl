<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <title>KISAN KRISHI BAZAR</title>
    <!-- BOOTSTRAP CORE STYLE  -->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONT AWESOME ICONS  -->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLE  -->
    <link href="assets/css/style.css" rel="stylesheet" />
     <!-- HTML5 Shiv and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <header>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <strong>Email: </strong>hackathon@accenture.com
                    &nbsp;&nbsp;
                    <strong>Support: </strong>+Better-Call-Saul
                </div>

            </div>
        </div>
    </header>
    <!-- HEADER END-->
    <div class="navbar navbar-inverse set-radius-zero">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/retailer/home">

                    <strong>Kisan Krishi Bazar</strong>
                </a>

            </div>

            <div class="left-div">
                <i class="fa fa-user-plus login-icon" ></i>
        </div>
            </div>
        </div>
    <!-- LOGO HEADER END-->
   
    <!-- MENU SECTION END-->
    <div class="content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h4 class="page-head-line">Please fill Registration form  </h4>

                </div>

            </div>
            <div class="row">
                
 				<div class="col-md-12">
                        <div class="panel panel-default">
                        <div class="panel-heading">
                           ENTER USER DETAILS
                        </div>
                        <div  class="panel-body">
                            <form id="retailregister" id= role="form">
                            			 <input name="lat" id="lat" type="hidden" class="form-control"">	
                            			 <input name="longt" id="longt" type="hidden" class="form-control" >	
                            			 <input name="type" id="type" type="hidden" value="R" class="form-control" >	
                                        <div class="form-group has-success">
                                            <label class="control-label" for="success">USERNAME</label>
                                            <input name = "username" type="text" placeholder="Enter UserName" class="form-control" id="success">
                                        </div>
                                        <div class="form-group has-success">
                                            <label class="control-label" for="warning">PASSWORD</label>
                                            <input name= "password" type="password" placeholder="Enter Password" class="form-control" id="warning">
                                        </div>
                                         <div class="form-group has-success">
                                            <label class="control-label" for="warning">COMPANY NAME</label>
                                            <input name= "name" type="text" placeholder="Enter Company Name"  class="form-control" id="warning">
                                        </div>
                                          <div class="form-group has-success">
                                            <label class="control-label" for="warning">COMPANY ADDRESS</label>
                                           <textarea name= "address" class="form-control" rows="3" placeholder="Full Address"></textarea>
                                        </div>
                                        <div class="form-group has-success">
                                            <label class="control-label" for="error">PHONE NUMBER</label>
                                            <input name="phone" type="text"  placeholder="Phone Number" class="form-control" id="error">
                                        </div>
                                         <hr>
                           			<a id="register" href="#" class="btn btn-success btn-sm">register me</a>
                             </form>
                           

                        </div>
                            </div>
                        </div>
               

            </div>
        </div>
    </div>
    <!-- CONTENT-WRAPPER SECTION END-->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    &copy; 2016 MegaHackthon | By : <a href="" target="_blank">MegaHackathon</a>
                </div>

            </div>
        </div>
    </footer>
    <!-- FOOTER SECTION END-->
    <!-- JAVASCRIPT AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
    <!-- CORE JQUERY SCRIPTS -->
    <script src="assets/js/jquery-1.11.1.js"></script>
    <!-- BOOTSTRAP SCRIPTS  -->
    <script src="assets/js/bootstrap.js"></script>
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>    
    <script src="assets/js/registeruser.js"></script>
  
</body>
</html>
