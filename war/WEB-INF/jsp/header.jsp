<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="com.common.security.EPUser"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>다음정보기술</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css' />" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/header.css' />" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.ui.all.css' />" />
<link rel="stylesheet" type="text/css" href="daumeditor/css/editor.css" />
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.core.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker.js' />"></script>
<script type="text/javascript" src="daumeditor/js/editor_loader.js"></script>
<script type="text/javascript" src="<c:url value='/js/showModalDialog.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.blockUI.js' />"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
$(document).ready(function() {
});

function showNav(lay, parentObj) {
	var parentLeft = parseInt($(parentObj).position().left);
	var adminMenuLeft = parseInt($("#workAreaDiv").position().left) - 10;
	hiddenNav();
	var childMenuUl = $("#snbNav" + lay);
	childMenuUl.show();
	var childMenuWidth = parseInt($("#snbBox" + lay).width());
	//alert('parentLeft = ' + parentLeft + '\nchildMenuWidth = ' + childMenuWidth + '\nchileMenuCnt = ' + childMenuUl.find("a").length);
	childMenuWidth += childMenuUl.find("a").length * 10; // 각 하위 메뉴 아이템별 여백
	if ((parentLeft + childMenuWidth) > adminMenuLeft) {
		var left = parentLeft - ((parentLeft + childMenuWidth) - adminMenuLeft);
		if (left < 0)
			left = 0;
		childMenuUl.css("margin-left", left);
	} else {
		childMenuUl.css("margin-left", parentLeft);
	}
}

function hiddenNav() {
	$(".snbNav").hide();
}

// for IE, Firefox
function fnLogin(pw) {
	var rtnVal = window.showModalDialog("${pageContext.request.contextPath}/login.do", "abcd", "resizable:yes; center:on; dialogwidth:400px; dialogheight:400px;");
	otherParameters[0] = pw;
	if (rtnVal != undefined)
		if (rtnVal.userAuth == "00")
			window.location.reload();
}

// for Chrome
function showModalDialogCallback(rtnVal) {
	alert(otherParameters[0]);
	if (rtnVal != undefined)
		if (rtnVal.userAuth == "00")
			window.location.reload();
}
</script>
</head>
<body>
	<div class="header">
		<div class="gnb">
			<div style="text-align: center; padding-top: 15px;">
				<a href="${pageContext.request.contextPath}/main.do"><img style="cursor: pointer;" src="/DaumHome/images/logo_eagle_office.gif" /></a>
			</div>
			<ul class="iconMenu">
				<li class="l"></li>
				<li class="c pre">
					<a class="opt01" onclick="selectMenu('icon_0001', 'URL', '/neo/modules/mail/main.mvc');"></a>
				</li>
				<li class="c ">
					<a class="opt02" onclick="selectMenu('icon_0002', 'URL', '/neo/modules/bbs/main.mvc?boardMode=COMPANY');"></a>
				</li>
				<li class="c ">
					<a class="opt03" onclick="selectMenu('icon_0003', 'URL', '/neo/modules/approval/worklist/t1/main.mvc?menuType=1');"></a>
				</li>
				<li class="c ">
					<a class="opt04" onclick="selectMenu('icon_0004', 'URL', '/neo/modules/org/main.mvc');"></a>
				</li>
				<li class="c ">
					<a class="opt05" onclick="selectMenu('icon_0005', 'SCRIPT', 'goConfigIconMenu()');"></a>
				</li>
				<li class="c ">
					<a class="opt06" onclick="selectMenu('icon_0006', 'URL', '/neo/modules/activity/main.mvc');"></a>
				</li>
				<li class="r"></li>
			</ul>
			<div class="userInfo">
				<table cellSpacing="0" cellPadding="0">
					<tbody>
						<tr>
						<c:if test="${empty sessionCheck.userId}">
							<td style="padding-top: 1px; white-space: nowrap;">
								<a title="마지막 접속 시간 : 2016-02-04 14:50:32"></a><a
								class="myInfo"
								onclick="fnLogin(1234);"
								href="javascript:void(0);">로그인</a>
							</td>
						</c:if>
						<c:if test="${not empty sessionCheck.userId}">
							<td style="padding-top: 1px; white-space: nowrap;">
								<%
									EPUser user = (EPUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
									String username = user.getUsername();
								%>
								<a title="마지막 접속 시간 : 2016-02-04 14:50:32"><%=username%>님</a>(SharedService팀(EP))<a
								class="myInfo"
								href="${pageContext.request.contextPath}/logout.do">내정보(로그아웃)</a>
							</td>
						</c:if>
							<td style="padding-left: 3px;">
								<a class="logout" href="j_spring_security_logout"></a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="topGuide">
				<div class="csCenter">
					<a href="${pageContext.request.contextPath}/mobile.do"><img src="/DaumHome/images/btn_mobile.gif" /></a>
				</div>
				<div style="padding-top: 4px; padding-bottom: 7px;">
					<label style="cursor: pointer; color: #666; font-size: 11px;">
						<input name="searchOrgAllChk" type="checkbox"
						value="on">전사
					</label>
					<span>
						<select name="searchOption"
						style="width: 85px; font-size: 12px;"
						onchange="changeSearchOption(this.value);">
							<option value="SEARCH_ORG">사원검색</option>
							<option value="SEARCH_ALL">통합검색</option>
						</select>
					</span><input name="keyword" class="schipt"
						style="width: 110px; ime-mode: active;"
						onkeyup="enterSearchForTopPane(event);" type="text" /><img
						style="cursor: pointer;" onclick="searchByKeyword();"
						src="/DaumHome/images/btn_search.gif" />
				</div>
			</div>
		</div>
		<div class="lnb">
			<div style="float: left;">
				<ul style="position: relative;">
					<li>
						<a class="rollover" onmouseover="javascript:showNav('bar_0100', this);" onclick="selectMenu('bar_0100', 'URL', '/neo/ep4/Workplace/view/wp/WP0000102559');">
						<img src="/DaumHome/images/lnb_menu01.gif" /><img class="over" src="/DaumHome/images/lnb_menu01_ov.gif" /></a>
					</li>
					<li>
						<a class="rollover" onmouseover="javascript:showNav('bar_0200', this);" onclick="selectMenu('bar_0200', 'URL', '/neo/modules/mail/main.mvc');">
						<img src="/DaumHome/images/lnb_menu02.gif" /><img class="over" src="/DaumHome/images/lnb_menu02_ov.gif" /></a>
					</li>
					<li>
						<a class="rollover" onmouseover="javascript:showNav('bar_0300', this);" onclick="selectMenu('bar_0300', 'URL', '/neo/modules/bbs/main.mvc?boardMode=COMPANY');">
						<img src="/DaumHome/images/lnb_menu03.gif" /><img class="over" src="/DaumHome/images/lnb_menu03_ov.gif" /></a>
					</li>
					<li>
						<a class="rollover" onmouseover="javascript:showNav('bar_0400', this);" onclick="selectMenu('bar_0400', 'URL', '/neo/modules/approval/worklist/t1/main.mvc?menuType=1');">
						<img src="/DaumHome/images/lnb_menu04.gif" /><img class="over" src="/DaumHome/images/lnb_menu04_ov.gif" /></a>
					</li>
				</ul>
			</div>
			<div style="float: right; white-space: nowrap;">
				<img src="/DaumHome/images/lnb_quick_left.gif" /><img style="cursor: pointer;"
					onclick="selectMenu('menu_0700', 'URL', '/neo/modules/blog/main.mvc');"
					src="/DaumHome/images/lnb_quick_blog.gif" /><img style="cursor: pointer;"
					onclick="selectMenu('menu_0800', 'URL', '/neo/modules/wp/main.mvc');"
					src="/DaumHome/images/lnb_quick_wp.gif" />
			</div>
		</div>
		<div class="snb">
			<ul class="snbNav" id="snbNavbar_0100" style="margin-left: 0px; display: none;">
				<li class="sm_l"></li>
				<li class="sm_c" id="snbBoxbar_0100">
					<a class="barNo" onclick="selectMenu('WP0000102559', 'URL', '/neo/ep4/Workplace/view/wp/WP0000102559');">한화S&amp;C</a>
					<a onclick="selectMenu('WP0000170694', 'URL', '/neo/ep4/Workplace/view/wp/WP0000170694');">MyPage</a>
				</li>
				<li class="sm_r"></li>
			</ul>
			<ul class="snbNav" id="snbNavbar_0200" style="margin-left: 70px; display: none;">
				<li class="sm_l"></li>
				<li class="sm_c" id="snbBoxbar_0200">
					<a class="barNo" onclick="selectMenu('bar_0201', 'URL', '/neo/modules/mail/main.mvc');">메일</a>
					<a onclick="selectMenu('menu_0645', 'URL', '/neo/modules/mail/config/mailConfigFolder.mvc');">메일백업</a>
					<a onclick="selectMenu('bar_0204', 'URL', '/neo/modules/schedule/main.mvc');">일정</a>
					<a onclick="selectMenu('bar_0203', 'URL', '/neo/modules/address/main.mvc');">주소록</a>
					<a onclick="selectMenu('menu_0367', 'URL', '/neo/modules/activity/main.mvc');">활동정보</a>
					<a onclick="selectMenu('menu_0454', 'URL', '/neo/modules/misc/sms/main.mvc');">SMS/FAX</a>
				</li>
				<li class="sm_r"></li>
			</ul>
			<ul class="snbNav" id="snbNavbar_0300" style="margin-left: 147px; display: none;">
				<li class="sm_l"></li>
				<li class="sm_c" id="snbBoxbar_0300">
					<a class="barNo" onclick="selectMenu('bar_0302', 'URL', '/neo/modules/bbs/main.mvc?boardMode=COMPANY');">회사게시판</a>
					<a onclick="selectMenu('bar_0301', 'URL', '/neo/modules/bbs/main.mvc?boardMode=GROUP&&selectedBoardId=19570');">그룹게시판</a>
					<a onclick="selectMenu('menu_0512', 'URL', '/neo/modules/bbs/bbsAffiliateMain.mvc?boardMode=AFFILIATE');">관계사게시판</a>
					<a onclick="selectMenu('menu_0673', 'URL', '/neo/modules/bbs/main.mvc?selectedWpId=14870&selectedBoardId=115714');">통번역게시판</a>
					<a onclick="selectMenu('menu_0481', 'URL', '/neo/modules/bbs/main.mvc?selectedWpId=11668&global=true&selectedBoardId=24123');">글로벌게시판</a>
					<a onclick="selectMenu('bar_0207', 'URL', '/neo/modules/org/main.mvc');">조직정보</a>
					<a onclick="selectMenu('menu_0366', 'SCRIPT', 'joinsPeople()');">인물정보</a>
				</li>
				<li class="sm_r"></li>
			</ul>
			<ul class="snbNav" id="snbNavbar_0400" style="margin-left: 226px; display: none;">
				<li class="sm_l"></li>
				<li class="sm_c" id="snbBoxbar_0400">
					<a class="barNo" href="${pageContext.request.contextPath}/boardList.do">게시판</a>
					<a href="${pageContext.request.contextPath}/code.do">코드관리</a>
					<a href="${pageContext.request.contextPath}/iBatis.do">아이바티스 연습</a>
					<a href="${pageContext.request.contextPath}/controlMng.do">제어관리</a>
					<a href="<c:out value='${pageContext.request.contextPath}'/>/bbs.do">다음에디터</a>
					<a href="${pageContext.request.contextPath}/nonHtml.do">비 HTML</a>
					<a href="${pageContext.request.contextPath}/module/DI.do">Dependency Injection</a>
					<a href="${pageContext.request.contextPath}/chat.do">채팅</a>
				</li>
				<li class="sm_r"></li>
			</ul>
		</div>
		<div class="work" id="workAreaDiv">
			<select style="margin-top: 1px; vertical-align: top;" onchange="location.href='<c:out value='${pageContext.request.contextPath}'/>/admin/main.do'">
				<option value="-1">관리자 메뉴▼</option>
				<option value="/neo/admin/modules/groupware/main.mvc?menuId=admin_030000">시스템관리</option>
				<option value="/neo/admin/modules/groupware/main.mvc?menuId=admin_010000">그룹웨어관리</option>
				<option value="/neo/admin/modules/groupware/main.mvc?menuId=admin_020000">전자결재관리</option>
				<option value="/neo/admin/modules/groupware/main.mvc?menuId=menu_0127">블로그관리</option>
				<option value="/neo/admin/modules/groupware/main.mvc?menuId=menu_0233">워크플레이스관리</option>
				<option value="/neo/ep4/Admin">포탈관리</option>
				<option value="/neo/admin/modules/groupware/main.mvc?menuId=menu_0356">DRM관리</option>
			</select>
			<img style="cursor: pointer;" onclick="goQuickMenu(this.parentNode);" src="/DaumHome/images/btn_work_system.gif" />
		</div>
	</div>