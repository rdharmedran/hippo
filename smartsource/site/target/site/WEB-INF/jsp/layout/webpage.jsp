<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/WEB-INF/jspf/htmlTags.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	 <script >

// Pass the checkbox name to the function
function getCheckedBoxes() {
  var checkboxes = document.getElementsByClassName('checkbox');
  var checkboxesChecked = '';
  // loop over them all
  for (var i=0; i<checkboxes.length; i++) {
     // And stick the checked ones onto an array...
     if (checkboxes[i].checked) {
        checkboxesChecked+=','+checkboxes[i].value;
     }
  }

  // Return the array if it is non-empty, or null
  if( checkboxesChecked.length > 0){
  
  document.getElementById('selectedCoupons').value=checkboxesChecked;
document.getElementById('input').submit();
} 
}

// Call as
var checkedBoxes = getCheckedBoxes("mycheckboxes");
  </script>
    <hst:link var="link" path="/css/style.css"/>
    <link rel="stylesheet" href="${link}" type="text/css"/>
  </head>
  <body>
    <hst:include ref="header"/>
    <hst:include ref="main"/>
    <hst:headContributions categoryIncludes="scripts"/>
  </body>
</html>
