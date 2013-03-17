<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
   <title>Hello</title>
</head>
   <script src="resources/dhtmlxscheduler.js" type="text/javascript" charset="utf-8"></script>
   <link rel="stylesheet" href="resources/dhtmlxscheduler.css" type="text/css" media="screen" title="no title" charset="utf-8">
	
	<script src="resources/ext/dhtmlxscheduler_readonly.js" type="text/javascript" charset="utf-8"></script>
   
<style type="text/css" media="screen">
   html, body{
      margin:0px;
      padding:0px;
      height:100%;
      overflow:hidden;
   }   
</style>

<script type="text/javascript" charset="utf-8">
	function init() {		
		scheduler.config.xml_date="%Y-%m-%d %H:%i";
		scheduler.init('scheduler_here',new Date(2010,0,10),"month");
		scheduler.config.readonly_form = true;		

		//block all modifications
		scheduler.attachEvent("onBeforeDrag",function(){return false;})
		scheduler.attachEvent("onClick",function(){return false;})
		scheduler.config.details_on_dblclick = true;
		scheduler.config.dblclick_create = false;
		
		scheduler.load("resources/data/events.xml",function(){
			scheduler.showLightbox("1261150511");
		});
	}	
</script>
</head>
<body onload="init()")>
	<h3>Message : ${message}</h3>	
	<h3>Username : ${username}</h3>	
	<h2>Foo : ${foo}</h2>		
	
	 <div id="scheduler_here" class="dhx_cal_container" style='width:70%; height:70%; left:15%' >
    	<div class="dhx_cal_navline">
			<div class="dhx_cal_prev_button">&nbsp;</div>
			<div class="dhx_cal_next_button">&nbsp;</div>
			<div class="dhx_cal_today_button"></div>
			<div class="dhx_cal_date"></div>
			<div class="dhx_cal_tab" name="day_tab" style="right:204px;"></div>
			<div class="dhx_cal_tab" name="week_tab" style="right:140px;"></div>
			<div class="dhx_cal_tab" name="month_tab" style="right:76px;"></div>
		</div>
		<div class="dhx_cal_header">
		</div>
		<div class="dhx_cal_data">
		</div>		
	</div>	
		
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	
</body>
</html>