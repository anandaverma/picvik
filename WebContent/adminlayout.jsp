<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, initial-scale=1.0, maximum-scale=1">
<meta name="description" content="">
<meta name="author" content="Team 404">
<meta name="keywords" content="picture, video, social network, sharing">



<link rel="stylesheet" href="ViewResources/jquery/css/ui-darkness/jquery-ui-1.9.2.custom.css" />
    <script src="ViewResources/jquery/js/jquery-1.8.3.js"></script>
    <script src="ViewResources/jquery/js/jquery-ui-1.9.2.custom.js"></script>
<!-- 2. flowplayer -->
<script src="http://releases.flowplayer.org/5.2.1/flowplayer.min.js"></script>
 
<!-- 3. skin -->
<link rel="stylesheet" type="text/css"
   href="http://releases.flowplayer.org/5.2.1/skin/minimalist.css" />
   
<link rel="stylesheet" href="ViewResources/css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
<script src="ViewResources/javascript/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>  


<link href="ViewResources/css/modern.css" rel="stylesheet">
<!-- <link href="ViewResources/css/modern-responsive.css" rel="stylesheet">  
<link href="ViewResources/css/site.css" rel="stylesheet" type="text/css">
<link href="ViewResources/js/google-code-prettify/prettify.css" rel="stylesheet" type="text/css">
-->
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<script>
    	$(function() {
    		//jQuery.noConflict();
            $( "#datepicker" ).datepicker({
                showButtonPanel: true,
                changeMonth: true,
                changeYear: true,
            });
            $( "#anim" ).change(function() {
                $( "#datepicker" ).datepicker( "option", "showAnim", "fade");
            });
        });
    </script>
<script>
    // increase the default animation speed to exaggerate the effect
    $.fx.speeds._default = 500;
    $(function() {
        $( "#dialog" ).dialog({
            autoOpen: false,
            show: "blind",
        });
 
        $( "#opener" ).click(function() {
            $( "#dialog" ).dialog( "open" );
            return false;
        });
    });
    </script>
<script>
$(function() {
    $(".hiddenimgbox").hide();
});
</script>  
  
</head>
<%@page import = "com.opensymphony.xwork2.ActionContext;" %>

<body class="modern-ui" onload="prettyPrint()">
<div class="page">
			<tiles:insertAttribute name="header" />
</div>

<div class="page secondary">
		<tiles:insertAttribute name="body" />

</div>	

<div class="page">	
	<tiles:insertAttribute name="footer" />
</div>


<!-- Required JavaScripts 
<script type="text/javascript" src="ViewResources/js/assets/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="ViewResources/js/assets/jquery.mousewheel.min.js"></script>
-->
<script src="ViewResources/javascript/accordion.js"></script>
<script src="ViewResources/javascript/buttonset.js"></script>
<script src="ViewResources/javascript/carousel.js"></script>
<script src="ViewResources/javascript/dropdown.js"></script>
<script src="ViewResources/javascript/input-control.js"></script>
<script src="ViewResources/javascript/pagecontrol.js"></script>
<script src="ViewResources/javascript/rating.js"></script>
<script src="ViewResources/javascript/slider.js"></script>
<script src="ViewResources/javascript/tile-slider.js"></script>
<!-- Required JavaScripts
<script type="text/javascript" src="ViewResources/js/assets/github.info.js"></script>
<script type="text/javascript" src="ViewResources/js/assets/google-analytics.js"></script>
<script type="text/javascript" src="ViewResources/js/google-code-prettify/prettify.js"></script>
<script src="ViewResources/js/sharrre/jquery.sharrre-1.3.4.min.js"></script>
-->
<script type="text/javascript" charset="utf-8">

	$(document).ready(function(){
		$("a[rel^='prettyPhoto']").prettyPhoto({
			animation_speed: 'fast', /* fast/slow/normal */
			slideshow: 5000, /* false OR interval time in ms */
			autoplay_slideshow: false, /* true/false */
			opacity: 0.80, /* Value between 0 and 1 */
			show_title: true, /* true/false */
			allow_resize: true, /* Resize the photos bigger than viewport. true/false */
			default_width: 1200,
			default_height: 800,
			counter_separator_label: '/', /* The separator for the gallery counter 1 "of" 2 */
			theme: 'light_square', /* light_rounded / dark_rounded / light_square / dark_square / facebook */
			horizontal_padding: 25, /* The padding on each side of the picture */
			hideflash: false, /* Hides all the flash object on a page, set to TRUE if flash appears over prettyPhoto */
			wmode: 'opaque', /* Set the flash wmode attribute */
			autoplay: true, /* Automatically start videos: True/False */
			modal: false, /* If set to true, only the close button will close the window */
			deeplinking: true, /* Allow prettyPhoto to update the url to enable deeplinking. */
			overlay_gallery: true, /* If set to true, a gallery will overlay the fullscreen image on mouse over */
			keyboard_shortcuts: true, /* Set to false if you open forms inside prettyPhoto */
			changepicturecallback: function(){}, /* Called everytime an item is shown/changed */
			callback: function(){}, /* Called when prettyPhoto is closed */
			ie6_fallback: true,
			
			});
	});
</script>
</body>
</html>