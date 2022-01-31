<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Mantra RD Service Client </title>
    <!-- Bootstrap Core CSS -->
    <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>
    
    <!-- <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
    <link href="${bootstrap}" rel="stylesheet">
    <style>
        .navbar-right h2 {
            color: #fff;
            margin: 20px 20px;
        }

        .mtop {
            margin: 20px 0 0;
        }

        .box-shadow {
            box-shadow: 0 2px 5px rgba(105, 108, 109, 0.7), 0 0 8px 5px rgba(208, 223, 226, 0.4) /*inset*/;
        }

        textarea {
            resize: none;
        }

        .overlay {
            height: 100%;
            width: 0;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0, 0.9);
            overflow-x: hidden;
            transition: 0.5s;
        }

        .overlay-content {
            position: relative;
            top: 25%;
            width: 100%;
            text-align: center;
            margin-top: 30px;
        }

        .overlay a {
            padding: 8px;
            text-decoration: none;
            font-size: 36px;
            color: #818181;
            display: block;
            transition: 0.3s;
        }

            .overlay a:hover, .overlay a:focus {
                color: #f1f1f1;
            }

        .overlay .closebtn {
            position: absolute;
            top: 20px;
            right: 45px;
            font-size: 60px;
        }

        @media (max-width:1200px) {
            .btn-200 {
                width: 150px !important;
                margin-bottom: 10px;
            }
        }


        @media (max-width:768px) {
            .btn-200 {
                width: 100% !important;
                margin-bottom: 10px;
            }
        }

        @media screen and (max-height: 450px) {
            .overlay a {
                font-size: 20px;
            }

            .overlay .closebtn {
                font-size: 40px;
                top: 15px;
                right: 35px;
            }
        }

        .btn-50 {
            width: 50px;
        }

        .btn-100 {
            width: 100px;
        }

        .btn-150 {
            width: 150px;
        }

        .btn-200 {
            width: 205px;
            font-weight: 600;
            font-size: 16px;
        }

        .btn-primary {
            color: #FFF;
            background-color: #428BCA;
            border-color: #357EBD;
        }

            .btn-primary:hover {
                color: #FFF;
                background-color: #357EBD;
                border-color: #428BCA;
            }

        .img {
            min-width: 125px;
            min-height: 155px;
            width: 125px;
            height: 155px;
            border: 1px solid #CCC;
            border-radius: 4px;
            box-shadow: 0px 1px 1px rgba(0, 0, 0, 0.075) inset;
            background-color: #FFFFFF;
        }
    </style>
    <script type="text/javascript">
        document.createElement('header');
        document.createElement('nav');
        document.createElement('section');
        document.createElement('article');
        document.createElement('aside');
        document.createElement('footer');
    </script>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js" type="text/javascript"/>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js" type="text/javascript"/>



	
    <!-- <script type="text/javascript" src="jquery/respond.js"></script>
    <script type="text/javascript" src="jquery/html5shiv.js"></script>
    <script type="text/javascript" src="jquery-1.12.4.js"></script> -->
</head>
<body>
<jsp:include page="../fragments/header.jsp" />
    <div id="wrapper">
        <div id="myNav" class="overlay">
            <div class="overlay-content">
                <a href="#">Please wait while discovering port from 11100 to 11120.This will take some time.</a>
            </div>
        </div>
        <!-- Navigation -->
        <div class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <a class="navbar-brand"><img src="logo.png" alt="Mantra logo"></a>
            </div>
            <!-- /.navbar-header -->

            <div class="nav navbar-top-links navbar-right">
                <h2>Mantra RD Service Call</h2>
            </div>
        </div>
		
		
		<div class="col-md-4">

                                            <div class="form-group">
                                                <label style="color:red"> Custom SSL Certificate Domain Name  Ex:(rd.myservice.com) </label>
                                                <input type="text" class="form-control" id="txtSSLDomName" placeholder="127.0.0.1">
                                            </div>
											</div>
                                    </div>
									<div class="col-md-8">

                                            <div class="form-group">
                                                <label style="color:red"><b>[ After bind custom certificate add your domain name in hosts file  (C:\Windows\System32\drivers\etc\hosts)</b></label>
                                                <label style="color:red"><b>Ex: 127.0.0.1   rd.myservice.com ]</b></label>
                                            </div>
											</div>
                                    </div>
		
        <div class="container-fluid">
            <!-- /.row -->
            <div class="row ">
                <div class="col-lg-12 mtop ">
                    <div>

                        <h4 class="col-md-2">Initialized Framework</h4>
                        <div class="form-group">
                            <button type="button" onclick="discoverAvdm();" type="button" value="Discover AVDM" class="btn btn-200 btn-primary">Discover AVDM</button>

                            <button type="button" value="Device Info" onclick="deviceInfoAvdm();" class="btn btn-200 btn-primary">Device Info</button>

                            <button type="button" value="Capture" onclick="CaptureAvdm();" class="btn btn-200 btn-primary">Capture</button>

                            <button type="button" onclick="reset();" class="btn btn-200 btn-primary" value="Reset">Reset</button>
                            &nbsp;&nbsp;
							
                            <!-- <input style="visibility:hidden"  name="ChkRD" id="chkHttpsPort" type="checkbox">Custome Port For HTTPS</input> -->
							<input style="visibility:hidden"  name="ChkRD" id="chkHttpsPort" type="checkbox"></input>
							
                        </div>



                    </div>
                </div>
                <div class="col-lg-12 ">
                    <div class="panel panel-default box-shadow">
                        <div class="panel-heading">
                            Select Option to Capture
                        </div>
                        <div class="panel-body ">
                            <div class="row">
                                <div class="col-md-5">
                                    <div class="form-group">
                                        <label>AVDM</label>
                                        <select id="ddlAVDM" class="form-control">
                                            <option></option>
                                        </select>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Timeout</label>
                                                <select id="Timeout" class="form-control">
                                                    <option>10000</option>
                                                    <option>10000</option>
                                                    <option>20000</option>
                                                    <option>30000</option>
                                                    <option>40000</option>
                                                    <option>50000</option>
                                                    <option>60000</option>
                                                    <option>70000</option>
                                                    <option>80000</option>
                                                    <option>90000</option>
                                                    <option>100000</option>
                                                    <option>0</option>
                                                </select>
                                            </div>


                                        </div>


                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>PidVer</label>
                                                <select id="Pidver" class="form-control">
                                                    <option>2.0</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Env</label>
                                                <select id="Env" class="form-control">
                                                    <option>S</option>
                                                    <option selected="true">PP</option>
                                                    <option>P</option>
                                                </select>
                                            </div>
                                        </div>

                                    </div>


                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>PTimeout</label>
                                                <select id="pTimeout" class="form-control">
                                                    <option>10000</option>
                                                    <option selected="selected">20000</option>
                                                    <option>30000</option>
                                                    <option>40000</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>PGCount</label>
                                                <select id="pgCount" class="form-control">
                                                    <option>1</option>
                                                    <option selected="selected">2</option>
                                                </select>
                                            </div>
                                        </div>


                                    </div>




                                </div>
                                <div class="col-md-1">

                                    <div class="form-group">
                                        <label>DataType</label>
                                        <select id="Dtype" class="form-control">
                                            <option value="0">X</option>
                                            <option value="1">P</option>

                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label>Client Key</label>
                                        <input id="txtCK" class="form-control" type="text" placeholder="Enter text">
                                    </div>

                                    <div class="form-group">
                                        <label>OTP</label>
                                        <input id="txtotp" class="form-control" type="text" placeholder="Enter text">
                                    </div>

                                </div>

                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label>Wadh</label>
                                        <textarea id="txtWadh" rows="3" class="form-control"></textarea>
                                    </div>

                                </div>
                                <div class="col-md-4">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Finger Count</label>
                                                <select id="Fcount" class="form-control">
                                                    <option>0</option>
                                                    <option selected="selected">1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                    <option>6</option>
                                                    <option>7</option>
                                                    <option>8</option>
                                                    <option>9</option>
                                                    <option>10</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>Iris Count</label>
                                                <select id="Icount" class="form-control">
                                                    <option>0</option>
                                                    <option>1</option>
                                                    <option>2</option>
                                                </select>
                                            </div>

                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Face Count</label>
                                                <select id="Pcount" class="form-control">
                                                    <option>0</option>
                                                    <option>1</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>Finger Type</label>
                                                <select id="Ftype" class="form-control">
                                                    <option value="0">FMR</option>
                                                    <option value="1">FIR</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">

                                            <div class="form-group">
                                                <label>Iris Type </label>
                                                <select id="Itype" class="form-control">
                                                    <option>SELECT</option>
                                                    <option>ISO</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>Face Type</label>
                                                <select id="Ptype" class="form-control">
                                                    <option>SELECT</option>
                                                </select>
                                            </div>
                                        </div>
 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 ">
                    <div class="panel panel-default box-shadow">
                        <div class="panel-heading">
                            AVDM / Device Info
                        </div>
                        <div class="panel-body ">

                            <div class="form-group">
                                <textarea rows="5" id="txtDeviceInfo" class="form-control"></textarea>
                            </div>


                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <div class="col-lg-4 ">
                    <div class="panel panel-default box-shadow">
                        <div class="panel-heading">
                            Pid Options
                        </div>
                        <div class="panel-body ">

                            <div class="form-group">
                                <textarea id="txtPidOptions" rows="5" class="form-control"></textarea>
                            </div>


                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <div class="col-lg-12 ">
                    <div class="panel panel-default box-shadow">
                        <div class="panel-heading">
                            Pid Data
                        </div>
                        <div class="panel-body ">

                            <div class="form-group">
                                <textarea id="txtPidData" rows="7" class="form-control"></textarea>
                            </div>


                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <div class="col-lg-6  ">
                    <div class="panel panel-default box-shadow">
                        <div class="panel-heading">
                            PERSONAL IDENTITY(PI)
                        </div>
                        <div class="panel-body ">
                            <div class="row">

                                <div class="col-md-5">
                                    <div class="form-horizontal">

                                        <div class="form-group">
                                            <label class="control-label col-xs-5">Name</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtName" placeholder="Enter Your Name">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">Local Name:</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtLocalNamePI" placeholder="Local Name">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">Gender</label>
                                            <div class="col-xs-7">
                                                <select id="drpGender" class="form-control">
                                                    <option value="0">Select</option>
                                                    <option>MALE</option>
                                                    <option>FEMALE</option>
                                                    <option>TRANSGENDER</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">DOB</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtDOB" placeholder="DOB">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">Phone</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtPhone" placeholder="Phone">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">DOB Type:</label>
                                            <div class="col-xs-7">
                                                <select id="drpDOBType" class="form-control">
                                                    <option value="0">select</option>
                                                    <option>V</option>
                                                    <option>D</option>
                                                    <option>A</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-7">
                                    <div role="form" class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-xs-4" style="font-size:13px;">Match Strategy</label>
                                            <div class="col-xs-8">

                                                <label class="radio-inline">
                                                    <input type="radio" name="RDPI" id="rdExactPI" checked="true">Exact
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="RDPI" id="rdPartialPI"> Partial
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="RDPI" id="rdFuzzyPI"> Fuzzy
                                                </label>

                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-4">Match Value:</label>
                                            <div class="col-xs-8">
                                                <select id="drpMatchValuePI" class="form-control"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-4">Age</label>
                                            <div class="col-xs-8">
                                                <input type="number" class="form-control" id="txtAge" placeholder="Age">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-4">LocalMatchValue:</label>
                                            <div class="col-xs-8">
                                                <select class="form-control" id="drpLocalMatchValuePI"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-4">Email</label>
                                            <div class="col-xs-8">
                                                <input type="email" class="form-control" id="txtEmail" placeholder="Email">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6  ">
                    <div class="panel panel-default box-shadow">
                        <div class="panel-heading">
                            PERSONAL ADDRESS(PA)
                        </div>
                        <div class="panel-body ">
                            <div class="row">
                                <div class="col-md-6">
                                    <form role="form" class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">Care Of:</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtCareOf" placeholder="Care Of:">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">Landmark:</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtLandMark" placeholder="Landmark">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">Locality:</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtLocality" placeholder="Locality">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">City:</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtCity" placeholder="Email">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">District:</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtDist" placeholder="Email">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">PinCode:</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtPinCode" placeholder="PinCode">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-6">
                                    <form role="form" class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">Building: 	</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtBuilding" placeholder="Building">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">Street:</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtStreet" placeholder="Street">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">PO Name: </label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtPOName" placeholder="PO Name">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">Sub Dist:</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtSubDist" placeholder="Sub Dist">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">State:</label>
                                            <div class="col-xs-7">
                                                <input type="text" class="form-control" id="txtState" placeholder="State">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-xs-5">Match Strategy:</label>
                                            <div class="col-xs-7">
                                                <label class="radio-inline">
                                                    <input type="radio" name="optionsRadiosInline" id="rdMatchStrategyPA" checked="true" value="option1">Exact
                                                </label>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12 ">
                    <div class="panel panel-default box-shadow">
                        <div class="panel-heading">
                            PERSONAL FULL ADDRESS(PFA)
                        </div>
                        <div class="panel-body ">
                            <div class="row">
                                <div class="col-lg-4">
                                    <form role="form">
                                        <div class="form-group">
                                            <label>Email </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="RD" id="rdExactPFA" checked="true">Exact
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="RD" id="rdPartialPFA"> Partial
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="RD" id="rdFuzzyPFA"> Fuzzy
                                            </label>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Match Value:</label>
                                                    <select class="form-control" id="drpMatchValuePFA"></select>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Local Match Value:</label>
                                                    <select class="form-control" id="drpLocalMatchValue"></select>
                                                </div>
                                            </div>

                                        </div>

                                    </form>
                                </div>
                                <div class="col-lg-4">
                                    <form role="form">
                                        <div class="form-group">
                                            <label>Address Value:</label>
                                            <textarea rows="2" id="txtAddressValue" class="form-control"></textarea>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-lg-4">
                                    <form role="form">
                                        <div class="form-group">
                                            <label>Local Address:</label>
                                            <textarea rows="2" id="txtLocalAddress" class="form-control"></textarea>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-md-12">
                    <label id="lblstatus">
                    </label>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <script language="javascript" type="text/javascript">

		var GetCustomDomName='127.0.0.1';
		var GetPIString='';
		var GetPAString='';
		var GetPFAString='';
		var DemoFinalString='';
		var select = '';
		select += '<option val=0>Select</option>';
		for (i=1;i<=100;i++){
			select += '<option val=' + i + '>' + i + '</option>';
		}
		$('#drpMatchValuePI').html(select);
		$('#drpMatchValuePFA').html(select);
		$('#drpLocalMatchValue').html(select);
		$('#drpLocalMatchValuePI').html(select);

		var finalUrl="";
		var MethodInfo="";
		var MethodCapture="";
		var OldPort=false;






		function test()
		{
			alert("I am calling..");
		}

		function reset()
		{
		  
		
	
		
			$('#txtWadh').val('');
		    $('#txtDeviceInfo').val('');
			$('#txtPidOptions').val('');
			$('#txtPidData').val('');
		    $("select#ddlAVDM").prop('selectedIndex', 0);
		    $("select#Timeout").prop('selectedIndex', 0);
			$("select#Icount").prop('selectedIndex', 0);
			$("select#Fcount").prop('selectedIndex', 0);
			$("select#Icount").prop('selectedIndex', 0);
			$("select#Itype").prop('selectedIndex', 0);
			$("select#Ptype").prop('selectedIndex', 0);
			$("select#Ftype").prop('selectedIndex', 0);
			$("select#Dtype").prop('selectedIndex', 0);

			$('#txtotp').val('');
			$("select#pTimeout").prop('selectedIndex', 1);
			$("select#pgCount").prop('selectedIndex', 1);
			$('#txtSSLDomName').val('');
		}
		// All New Function

		function Demo()
		{


		var GetPIStringstr='';
		var GetPAStringstr='';
		var GetPFAStringstr='';

			if(GetPI()==true)
			{
				GetPIStringstr ='<Pi '+GetPIString+' />';

			}
			else
			{
				GetPIString='';
			}

			if(GetPA()==true)
			{
				GetPAStringstr ='<Pa '+GetPAString+' />';
				//alert(GetPAStringstr);
			}
			else
			{
				GetPAString='';
			}

			if(GetPFA()==true)
			{
				GetPFAStringstr ='<Pfa '+GetPFAString+' />';
				//alert(GetPFAStringstr);
			}
			else
			{
				GetPFAString='';
			}

			if(GetPI()==false && GetPA()==false && GetPFA()==false)
			{
				//alert("Fill Data!");
				DemoFinalString='';
			}
			else
			{
				DemoFinalString = '<Demo>'+ GetPIStringstr +' ' + GetPAStringstr + ' ' + GetPFAStringstr + ' </Demo>';
				//alert(DemoFinalString)
			}


		}

		function GetPI()
		{
			var Flag=false;
			GetPIString='';

			 if ($("#txtName").val().length > 0)
            {
                Flag = true;
                GetPIString += "name="+ "\""+$("#txtName").val()+"\"";
            }

            if ($("#drpMatchValuePI").val() > 0 && Flag)
            {
                Flag = true;
				GetPIString += " mv="+ "\""+$("#drpMatchValuePI").val()+"\"";
            }

			if ($('#rdExactPI').is(':checked') && Flag)
            {
                Flag = true;
                GetPIString += " ms="+ "\"E\"";
            }
            else if ($('#rdPartialPI').is(':checked') && Flag)
            {
                Flag = true;
               GetPIString += " ms="+ "\"P\"";
            }
            else if ($('#rdFuzzyPI').is(':checked') && Flag)
            {
                Flag = true;
                GetPIString += " ms="+ "\"F\"";
            }
			if ($("#txtLocalNamePI").val().length > 0)
            {
				Flag = true;
                GetPIString += " lname="+ "\""+$("#txtLocalNamePI").val()+"\"";
            }

			if ($("#txtLocalNamePI").val().length > 0 && $("#drpLocalMatchValuePI").val() > 0)
            {
				Flag = true;
				GetPIString += " lmv="+ "\""+$("#drpLocalMatchValuePI").val()+"\"";
            }



                if ($("#drpGender").val() == "MALE")
                {
                    Flag = true;
					 GetPIString += " gender="+ "\"M\"";
                }
                else if ($("#drpGender").val() == "FEMALE")
                {
                    Flag = true;
                     GetPIString += " gender="+ "\"F\"";
                }
                else if ($("#drpGender").val() == "TRANSGENDER")
                {
                    Flag = true;
                   GetPIString += " gender="+ "\"T\"";
                }
            //}
			    if ($("#txtDOB").val().length > 0 )
				{
					Flag = true;
					GetPIString += " dob="+ "\""+$("#txtDOB").val()+"\"";
				}

				if ($("#drpDOBType").val() != "0")
				{
					Flag = true;
					GetPIString += " dobt="+ "\""+$("#drpDOBType").val()+"\"";
				}

				if ($("#txtAge").val().length)
				{
					Flag = true;
					GetPIString += " age="+ "\""+$("#txtAge").val()+"\"";
				}

				if ($("#txtPhone").val().length > 0 || $("#txtEmail").val().length > 0)
				{
					Flag = true;
					GetPIString += " phone="+ "\""+$("#txtPhone").val()+"\"";
				}
				if ($("#txtEmail").val().length > 0)
				{
					Flag = true;
					GetPIString += " email="+ "\""+$("#txtEmail").val()+"\"";
				}

			//alert(GetPIString);
			return Flag;
		}


		function GetPA()
		{
			var Flag=false;
			GetPAString='';

			if ($("#txtCareOf").val().length > 0)
            {
				Flag = true;
                GetPAString += "co="+ "\""+$("#txtCareOf").val()+"\"";
            }
            if ($("#txtLandMark").val().length > 0 )
            {
                Flag = true;
                GetPAString += " lm="+ "\""+$("#txtLandMark").val()+"\"";
            }
            if ($("#txtLocality").val().length > 0 )
            {
               Flag = true;
                GetPAString += " loc="+ "\""+$("#txtLocality").val()+"\"";
            }
            if ($("#txtCity").val().length > 0 )
            {
                Flag = true;
                GetPAString += " vtc="+ "\""+$("#txtCity").val()+"\"";
            }
            if ($("#txtDist").val().length > 0 )
            {
                Flag = true;
                GetPAString += " dist="+ "\""+$("#txtDist").val()+"\"";
            }
            if ($("#txtPinCode").val().length > 0 )
            {
                Flag = true;
                GetPAString += " pc="+ "\""+$("#txtPinCode").val()+"\"";
            }
            if ($("#txtBuilding").val().length > 0 )
            {
                 Flag = true;
                GetPAString += " house="+ "\""+$("#txtBuilding").val()+"\"";
            }
            if ($("#txtStreet").val().length > 0 )
            {
                 Flag = true;
                GetPAString += " street="+ "\""+$("#txtStreet").val()+"\"";
            }
            if ($("#txtPOName").val().length > 0 )
            {
                 Flag = true;
                GetPAString += " po="+ "\""+$("#txtPOName").val()+"\"";
            }
            if ($("#txtSubDist").val().length > 0 )
            {
                  Flag = true;
                GetPAString += " subdist="+ "\""+$("#txtSubDist").val()+"\"";
            }
            if ($("#txtState").val().length > 0)
            {
                 Flag = true;
                GetPAString += " state="+ "\""+$("#txtState").val()+"\"";
            }
            if ( $('#rdMatchStrategyPA').is(':checked') && Flag)
            {
                Flag = true;
                GetPAString += " ms="+ "\"E\"";
            }
			//alert(GetPIString);
			return Flag;
		}



		function GetPFA()
		{
			var Flag=false;
			GetPFAString='';

			if ($("#txtAddressValue").val().length > 0)
            {
				Flag = true;
                GetPFAString += "av="+ "\""+$("#txtAddressValue").val()+"\"";
            }

			if ($("#drpMatchValuePFA").val() > 0 && $("#txtAddressValue").val().length > 0)
            {
                Flag = true;
				GetPFAString += " mv="+ "\""+$("#drpMatchValuePFA").val()+"\"";
            }

			if ($('#rdExactPFA').is(':checked') && Flag)
            {
                Flag = true;
                GetPFAString += " ms="+ "\"E\"";
            }
            else if ($('#rdPartialPFA').is(':checked') && Flag)
            {
                Flag = true;
               GetPFAString += " ms="+ "\"P\"";
            }
            else if ($('#rdFuzzyPFA').is(':checked') && Flag)
            {
                Flag = true;
                GetPFAString += " ms="+ "\"F\"";
            }

			if ($("#txtLocalAddress").val().length > 0)
            {
				Flag = true;
                GetPFAString += " lav="+ "\""+$("#txtLocalAddress").val()+"\"";
            }

			if ($("#drpLocalMatchValue").val() > 0 && $("#txtLocalAddress").val().length > 0)
            {
                Flag = true;
				GetPFAString += " lmv="+ "\""+$("#drpLocalMatchValue").val()+"\"";
            }
			//alert(GetPIString);
			return Flag;
		}

		$( "#ddlAVDM" ).change(function() {
		//alert($("#ddlAVDM").val());
		discoverAvdmFirstNode($("#ddlAVDM").val());
		});


		$( "#chkHttpsPort" ).change(function() {
		    if($("#chkHttpsPort").prop('checked')==true)
		    {
		        OldPort=true;
		    }
		    else
		    {
		        OldPort=false;
		    }

		});

		function discoverAvdmFirstNode(PortNo)
		{

			$('#txtWadh').val('');
		    $('#txtDeviceInfo').val('');
			$('#txtPidOptions').val('');
			$('#txtPidData').val('');

		//alert(PortNo);

		var primaryUrl = "http://"+GetCustomDomName+":";
            url = "";
					 var verb = "RDSERVICE";
                        var err = "";
						var res;
						$.support.cors = true;
						var httpStaus = false;
						var jsonstr="";
						 var data = new Object();
						 var obj = new Object();

							$.ajax({
							type: "RDSERVICE",
							async: false,
							crossDomain: true,
							url: primaryUrl + PortNo,
							contentType: "text/xml; charset=utf-8",
							processData: false,
							cache: false,
							async:false,
							crossDomain:true,
							success: function (data) {
								httpStaus = true;
								res = { httpStaus: httpStaus, data: data };
							    //alert(data);

								//debugger;

								 $("#txtDeviceInfo").val(data);

								var $doc = $.parseXML(data);

								//alert($($doc).find('Interface').eq(1).attr('path'));


								if($($doc).find('Interface').eq(0).attr('path')=="/rd/capture")

								{
								  MethodCapture=$($doc).find('Interface').eq(0).attr('path');
								}
								if($($doc).find('Interface').eq(1).attr('path')=="/rd/capture")

								{
								  MethodCapture=$($doc).find('Interface').eq(1).attr('path');
								}

								if($($doc).find('Interface').eq(0).attr('path')=="/rd/info")

								{
								  MethodInfo=$($doc).find('Interface').eq(0).attr('path');
								}
								if($($doc).find('Interface').eq(1).attr('path')=="/rd/info")

								{
								  MethodInfo=$($doc).find('Interface').eq(1).attr('path');
								}

								

								 alert("RDSERVICE Discover Successfully");
							},
							error: function (jqXHR, ajaxOptions, thrownError) {
							$('#txtDeviceInfo').val("");
							//alert(thrownError);
								res = { httpStaus: httpStaus, err: getHttpError(jqXHR) };
							},
						});

						return res;
		}








		function discoverAvdm()
		{
		   
            
			// New
			
			GetCustomDomName =  "127.0.0.1";
			if ($("#txtSSLDomName").val().trim().length > 0)
            {
				GetCustomDomName = $("#txtSSLDomName").val().trim();
            }
			
			
			
			
			
			
			
			

			openNav();


			$('#txtWadh').val('');
		    $('#txtDeviceInfo').val('');
			$('#txtPidOptions').val('');
			$('#txtPidData').val('');

			//


			var SuccessFlag=0;
            var primaryUrl = "http://"+GetCustomDomName+":";

						 try {
							 var protocol = window.location.href;
							 if (protocol.indexOf("https") >= 0) {
								primaryUrl = "https://"+GetCustomDomName+":";
							}
						 } catch (e)
						{ }


            url = "";
			 $("#ddlAVDM").empty();
			//alert("Please wait while discovering port from 11100 to 11120.\nThis will take some time.");
			SuccessFlag=0;
			    for (var i = 11100; i <= 11105; i++)
                {
					if(primaryUrl=="https://"+GetCustomDomName+":" && OldPort==true)
					{
					   i="8005";
					}
				    $("#lblStatus1").text("Discovering RD service on port : " + i.toString());

						var verb = "RDSERVICE";
                        var err = "";

						var res;
						$.support.cors = true;
						var httpStaus = false;
						var jsonstr="";
						 var data = new Object();
						 var obj = new Object();



							$.ajax({

							type: "RDSERVICE",
							async: false,
							crossDomain: true,
							url: primaryUrl + i.toString(),
							contentType: "text/xml; charset=utf-8",
							processData: false,
							cache: false,
							crossDomain:true,

							success: function (data) {

								httpStaus = true;
								res = { httpStaus: httpStaus, data: data };
							    //alert(data);
								$("#txtDeviceInfo").val(data);
								finalUrl = primaryUrl + i.toString();
								var $doc = $.parseXML(data);
								var CmbData1 =  $($doc).find('RDService').attr('status');
								var CmbData2 =  $($doc).find('RDService').attr('info');
								if(RegExp('\\b'+ 'Mantra' +'\\b').test(CmbData2)==true)
								{
								    closeNav();
									$("#txtDeviceInfo").val(data);

									if($($doc).find('Interface').eq(0).attr('path')=="/rd/capture")
									{
									  MethodCapture=$($doc).find('Interface').eq(0).attr('path');
									}
									if($($doc).find('Interface').eq(1).attr('path')=="/rd/capture")
									{
									  MethodCapture=$($doc).find('Interface').eq(1).attr('path');
									}
									if($($doc).find('Interface').eq(0).attr('path')=="/rd/info")
									{
									  MethodInfo=$($doc).find('Interface').eq(0).attr('path');
									}
									if($($doc).find('Interface').eq(1).attr('path')=="/rd/info")
									{
									  MethodInfo=$($doc).find('Interface').eq(1).attr('path');
									}

									$("#ddlAVDM").append('<option value='+i.toString()+'>(' + CmbData1 +'-' + i.toString()+')'+CmbData2+'</option>')
									SuccessFlag=1;
									//alert("RDSERVICE Discover Successfully");
									//return;

								}

								//alert(CmbData1);
								//alert(CmbData2);

							},
							error: function (jqXHR, ajaxOptions, thrownError) {
							if(i=="8005" && OldPort==true)
							{
								OldPort=false;
								i="11099";
							}
							//$('#txtDeviceInfo').val("");
							//alert(thrownError);

								//res = { httpStaus: httpStaus, err: getHttpError(jqXHR) };
							},

						});



						if(SuccessFlag==1)
						{
						  //break;
						}

						//$("#ddlAVDM").val("0");

                }

				if(SuccessFlag==0)
				{
				 alert("Connection failed Please try again.");
				}
				else{
				alert("RDSERVICE Discover Successfully");
				}

				$("select#ddlAVDM").prop('selectedIndex', 0);

				
				closeNav();
				return res;
		}


		function openNav() {
			document.getElementById("myNav").style.width = "100%";
		}

		function closeNav() {
			document.getElementById("myNav").style.width = "0%";
		}

		function deviceInfoAvdm()
		{
			//alert($("#ddlAVDM").val());
         






            url = "";

					
                    // $("#lblStatus").text("Discovering RD Service on Port : " + i.toString());
					//Dynamic URL

						finalUrl = "http://"+GetCustomDomName+":" + $("#ddlAVDM").val();

						try {
							var protocol = window.location.href;
							if (protocol.indexOf("https") >= 0) {
								finalUrl = "https://"+GetCustomDomName+":" + $("#ddlAVDM").val();
							}
						} catch (e)
						{ }

					//
					 var verb = "DEVICEINFO";
                      //alert(finalUrl);

                        var err = "";

						var res;
						$.support.cors = true;
						var httpStaus = false;
						var jsonstr="";
						;
							$.ajax({

							type: "DEVICEINFO",
							async: false,
							crossDomain: true,
							url: finalUrl+MethodInfo,
							contentType: "text/xml; charset=utf-8",
							processData: false,
							success: function (data) {
							//alert(data);
								httpStaus = true;
								res = { httpStaus: httpStaus, data: data };

								$('#txtDeviceInfo').val(data);
							},
							error: function (jqXHR, ajaxOptions, thrownError) {
							alert(thrownError);
								res = { httpStaus: httpStaus, err: getHttpError(jqXHR) };
							},
						});

						return res;

		}



		function CaptureAvdm()
		{



			var strWadh="";
		    var strOtp="";
	       Demo();

	       if($("#txtWadh").val()!="")
	       {
	            strWadh=" wadh=\""+$("#txtWadh").val()+'"';
	       }
	       if($("#txtotp").val()!="")
	       {
	            strOtp=" otp=\""+$("#txtotp").val()+'"';
	       }
	       var XML='<?xml version="1.0"?> <PidOptions ver="1.0"> <Opts fCount="'+$("#Fcount").val()+'" fType="'+$("#Ftype").val()+'" iCount="'+$("#Icount").val()+'" pCount="'+$("#Pcount").val()+'" pgCount="'+$("#pgCount").val()+'"'+strOtp+' format="'+$("#Dtype").val()+'"   pidVer="'+$("#Pidver").val()+'" timeout="'+$("#Timeout").val()+'" pTimeout="'+$("#pTimeout").val()+'"'+strWadh+' posh="UNKNOWN" env="'+$("#Env").val()+'" /> '+DemoFinalString+'<CustOpts><Param name="mantrakey" value="'+$("#txtCK").val()+'" /></CustOpts> </PidOptions>';



             



						finalUrl = "http://"+GetCustomDomName+":" + $("#ddlAVDM").val();

						try {
							var protocol = window.location.href;
							if (protocol.indexOf("https") >= 0) {
								finalUrl = "https://"+GetCustomDomName+":" + $("#ddlAVDM").val();
							}
						} catch (e)
						{ }



					 var verb = "CAPTURE";


                        var err = "";

						var res;
						$.support.cors = true;
						var httpStaus = false;
						var jsonstr="";
						;
							$.ajax({

							type: "CAPTURE",
							async: false,
							crossDomain: true,
							url: finalUrl+MethodCapture,
							data:XML,
							contentType: "text/xml; charset=utf-8",
							processData: false,
							success: function (data) {
							//alert(data);
								httpStaus = true;
								res = { httpStaus: httpStaus, data: data };

								$('#txtPidData').val(data);
								$('#txtPidOptions').val(XML);

								var $doc = $.parseXML(data);
								var Message =  $($doc).find('Resp').attr('errInfo');

								alert(Message);

							},
							error: function (jqXHR, ajaxOptions, thrownError) {
							//$('#txtPidOptions').val(XML);
							alert(thrownError);
								res = { httpStaus: httpStaus, err: getHttpError(jqXHR) };
							},
						});

						return res;
		}
		function getHttpError(jqXHR) {
		    var err = "Unhandled Exception";
		    if (jqXHR.status === 0) {
		        err = 'Service Unavailable';
		    } else if (jqXHR.status == 404) {
		        err = 'Requested page not found';
		    } else if (jqXHR.status == 500) {
		        err = 'Internal Server Error';
		    } else if (thrownError === 'parsererror') {
		        err = 'Requested JSON parse failed';
		    } else if (thrownError === 'timeout') {
		        err = 'Time out error';
		    } else if (thrownError === 'abort') {
		        err = 'Ajax request aborted';
		    } else {
		        err = 'Unhandled Error';
		    }
		    return err;
		}

    </script>
    <!-- jQuery -->
    <!-- <script src="jquery/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
 --></body>
</html>
