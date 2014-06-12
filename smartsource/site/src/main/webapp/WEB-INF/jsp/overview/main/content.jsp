<%@ include file="/WEB-INF/jspf/htmlTags.jspf" %>
<%--@elvariable id="crPage" type="java.lang.Integer"--%>
<%--@elvariable id="info" type="com.newsamerica.componentsinfo.GeneralListInfo"--%>
<%--@elvariable id="pages" type="java.util.Collection<java.lang.Integer>"--%>
<%--@elvariable id="query" type="java.lang.String"--%>
<%--@elvariable id="result" type="org.hippoecm.hst.content.beans.query.HstQueryResult"--%>
 <%--@elvariable id="ids" type="java.lang.String"--%>
 
bbbbbbbbbbbbbbbbbbbbb  <c:out value="${ids}"/>
<c:choose>
  <c:when test="${empty info}">
    <tag:pagenotfound/>
  </c:when>
  <c:otherwise>
    <c:if test="${not empty info.title}">
      <hst:element var="headTitle" name="title">
        <c:out value="${info.title}"/>
      </hst:element>
      <hst:headContribution keyHint="headTitle" element="${headTitle}"/>
    </c:if>
    
    <h2>
      ${info.title}
      <c:if test="${not empty result.totalSize}"> Total results ${result.totalSize}</c:if>
    </h2>
    <ul>
      <c:forEach var="item" items="${result.hippoBeans}">
        <hst:link var="link" hippobean="${item}"/>
        <li class="overview-item">
          <hst:cmseditlink hippobean="${item}"/>
          <a href="${link}">${item.title}</a>
          <div>
            <c:if test="${hst:isReadable(item, 'date.time')}">
              <p><fmt:formatDate value="${item.date.time}" type="Date" pattern="MMMM d, yyyy h:mm a"/></p>
            </c:if>
            <p>${item.summary}</p>
			 <tr>
						<td align="left" width="25px">
				
										</td><td align="left" width="20px">
						<input type="checkbox" class="checkbox" style="vertical-align:top;" name="chkoffer27269" value="27269" onclick="chkboxClick(27269,1,3,2),deSelectAll('2')"></td><td align="left" valign="top" style="padding-left:px;padding-top:8px;">
							<label style=""> 
							<span style="width:110%; height: 100%; font-family:Arial;font-size:9px;color:000000">
							
							SELECT THIS COUPON
							
							</span>
							</label>
							</td>
						
						
							<td align="right" style="font-weight: bold; padding-left:10px;font-family:Arial;font-size:10px;color:000000">Share:</td>
							<td align="right" width="20px" style="padding-left:9px;">
							
							<img src="static_content/sc/images/smartsource/facebook_logo.png" style="cursor:pointer;" height="15px" width="16px" align="center" alt="Share this coupon on Facebook" title="Share this coupon on Facebook" onclick="fbPopUp('27269', '$1 off 2 single packs of Tic Tacs (ss) ')">
								
							 <!-- <fb:like href="http://smartsource.com:80/smartsource/mailfriend/emailpopup.jsp?offerId=27429&Link=MKRU3JAAR6CCY&offerValue=4&categoryId=15&flag=false&version=0&childOffer=&MID=&localeId=1&defaultLocaleId=1&secondaryLocaleId=&defaultLocaleCode=en_US&secondaryLocaleCode=&zipCode=&_a=5&trackerId=UA-2972760-2&sm=true&check=27269&Fi=27269" layout="button_count" send="false" width="450" show_faces="true" border="0"></fb:like> --> 
							<!-- <iframe src="//www.facebook.com/plugins/like.php?href=http%3A%2F%2Fpsdssdc.newsamerica.com%2Fsmartsource%2Findex.jsp%3FLink%3DMKRU3JAAR6CCY%26sm%3Dtrue%26check%3D27269%26Fi%3D27269&amp;send=false&amp;layout=button_count&amp;width=70&amp;show_faces=true&amp;action=like&amp;colorscheme=light&amp;font&amp;height=25&amp;appId=484065148281914" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:70px; height:25px;" allowTransparency="true"></iframe> -->
						</td>
						
						<td align="right" width="15px" style="padding-left:9px;">
						
						<a onclick="twitterFeed('27269','Save $1.00', 'MKRU3JAAR6CCY', 'Tic Tac','','$1 off 2 single packs of Tic Tacs (ss) ');" id="shareLink27269" href="#" alt="Share this coupon on Twitter" title="Share this coupon on Twitter" _target="blank"><img src="static_content/sc/images/smartsource/twitter-bird-white-on-blue.png" height="15" width="16" border="0" align="center"></a>
							
							
				
									
							</td><td align="right" width="23px" height="15px" valign="top" style="padding-left:9px;padding-top:6px;">												
						
						
							
									<img src="static_content/sc/images/smartsource/icon_email_afriend.gif" style="cursor:pointer;padding-bottom:3px;" align="top" height="15px" width="15px" alt="Email Coupons to Friend" title="Email Coupons to Friend" onclick="convertHtmlNumericCode('$1 off 2 single packs of Tic Tacs (ss) ');wopen('http://smartsource.com:80/smartsource/mailfriend/emailpopup.jsp?offerId=27269&amp;Link=MKRU3JAAR6CCY&amp;offerValue=1&amp;categoryId=3&amp;flag=false&amp;version=0&amp;childOffer=&amp;MID=&amp;localeId=1&amp;defaultLocaleId=1&amp;secondaryLocaleId=&amp;defaultLocaleCode=en_US&amp;secondaryLocaleCode=&amp;zipCode=&amp;_a=5&amp;trackerId=UA-2972760-2', 'popup',560, 470); return false;">&nbsp;&nbsp;							
									 
						</td></tr>
          </div>
        </li>
      </c:forEach>
    </ul>
    
    <!--if there are pages on the request, they will be printed by the tag:pages -->
    <tag:pages pages="${pages}" page="${page}"/>
    <form name="input" id="input" action="" method="get">
 <input type="hidden" name="ids" id="selectedCoupons" >
<input type="button" onclick="getCheckedBoxes()" value="Print Coupons">
</form>
  </c:otherwise>
</c:choose>