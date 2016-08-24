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
    <title>Kisan Krishi Bazaaar</title>
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
                    <strong>Email: </strong>info@yourdomain.com &nbsp;&nbsp;
                    <strong>Support: </strong>+90-897-678-44
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
            </div>
            <div class="left-div">
                <div class="user-settings-wrapper">
                    <ul class="nav">
                        <li class="dropdown">
                            <div class="dropdown-menu dropdown-settings">
                                <div class="media">
                                    <a class="media-left" href="#">
                                        <img src="assets/img/64-64.jpg" alt="" class="img-rounded" />
                                    </a>
                                    <div class="media-body">
                                        <h4 class="media-heading">Jhon Deo Alex </h4>
                                        <h5>Developer & Designer</h5>
                                    </div>
                                </div>
                                <hr />
                                <h5><strong>Personal Bio : </strong></h5> Anim pariatur cliche reprehen derit.
                                <hr />
                                <a href="#" class="btn btn-info btn-sm">Full Profile</a>&nbsp; <a href="login.html" class="btn btn-danger btn-sm">Logout</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- LOGO HEADER END-->
    <section class="menu-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar-collapse collapse ">
                        <ul id="menu-top" class="nav navbar-nav navbar-right">
                            <li><a class="menu-top-active" href="/retailer/dashboard">Dashboard</a>
                            </li>
                            <li><a href="/retailer/orderhistory">Order History</a>
                            </li>
                            <li><a href="/retailer/home">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- MENU SECTION END-->
    <div class="content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h4 class="page-head-line">DASHBOARD</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            What Products do you want to buy?
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    <label>Products</label>
                                      <select class="form-control" id="products">
                                      </select>
                                </div>
                                <div class="alert alert-info priceDiv hide">
                                    <strong>Todays Estimated Price in MCX</strong><span> Rs</span><span id="qtyPrice"></span><span> per kg</span>
                                </div>
                                <div class="form-group quantityDiv hide">
                                    <label>Quantity(kg)</label>
                       
                                      <select class="form-control" id="quantity"></select>                          
                                </div>
                            </form>
                            <hr>
                            <a id="search" href="#" class="btn btn-success btn-sm hide">Search	</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="table-responsive orderTable hide">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Farmer Name</th>
                                    <th>Quantity Available(kg)</th>
                                    <th>Farmer Price</th>                                    
                                    <th>Mark Intrested</th>
                                    <th>Negotiate</th>
                                    <th>View Details</th>
                                </tr>
                            </thead>
                            <tbody class="userDetails">
                               
                              
                            </tbody>
                        </table>
                    </div>
                    <div>
                    <div id="mymodal"></div>
                    </div>

                </div>
            </div>
            <!-- CONTENT-WRAPPER SECTION END-->
            <footer>
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            &copy; 2015 YourCompany | By : <a href="http://www.designbootstrap.com/" target="_blank">DesignBootstrap</a>
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
            <script src="assets/js/dashboard.js"></script>
</body>

</html>