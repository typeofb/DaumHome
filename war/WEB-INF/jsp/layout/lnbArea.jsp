<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	$(".lnbArea .ct01 a").click(function(){
		$(this).closest("div").next("ul").toggle();
	});
});
function selectMenuItem(id, attr) {
	switch (id) {
		case "bbs01":
			location.href = "<c:url value='boardList.do' />";
			break;
		case "bbs02":
			location.href = "<c:url value='code.do' />";
			break;
		case "bbs03":
			location.href = "<c:url value='iBatis.do' />";
			break;
		case "blog01":
			location.href = "<c:url value='controlMng.do' />";
			break;
		case "blog02":
			location.href = "<c:url value='bbs.do' />";
			break;
		case "blog03":
			location.href = "<c:url value='nonHtml.do' />";
			break;
		case "blog04":
			location.href = "<c:url value='DI.do' />";
			break;
		case "wp01":
			location.href = "<c:url value='chat.do' />";
			break;
		case "wp02":
			location.href = "<c:url value='boardList.do' />";
			break;
		default:
			location.href = "<c:url value='boardList.do' />";
			break;
	}
}
</script>
	<div class="lnbArea">
		<div class="ct01">
			<a href="javaScript:void(0);" onfocus="this.blur();"><img src="<c:url value='images/btn_sm01.gif' />" class="vam2 magR3" />게시판</a>
		</div>
		<ul class="ct02" id="bbsToggle">
			<li><a id="bbs01" href="javascript:selectMenuItem('bbs01', this);" onfocus="this.blur();">게시판</a></li>
			<li><a id="bbs02" href="javascript:selectMenuItem('bbs02', this);" onfocus="this.blur();">코드관리</a></li>
			<li><a id="bbs03" href="javascript:selectMenuItem('bbs03', this);" onfocus="this.blur();">아이바티스 연습</a></li>
		</ul>
		<div class="ct01">
			<a href="javaScript:void(0);" onfocus="this.blur();"><img src="<c:url value='images/btn_sm01.gif' />" class="vam2 magR3" />블로그</a>
		</div>
		<ul class="ct02" id="blogToggle">
			<li><a id="blog01" href="javascript:selectMenuItem('blog01', this);" onfocus="this.blur();">제어관리</a></li>
			<li><a id="blog02" href="javascript:selectMenuItem('blog02', this);" onfocus="this.blur();">다음에디터</a></li>
			<li><a id="blog03" href="javascript:selectMenuItem('blog03', this);" onfocus="this.blur();">비 HTML</a></li>
			<li><a id="blog04" href="javascript:selectMenuItem('blog04', this);" onfocus="this.blur();">Dependency Injection</a></li>
		</ul>
		<div class="ct01">
			<a href="javaScript:void(0);" onfocus="this.blur();"><img src="<c:url value='images/btn_sm01.gif' />" class="vam2 magR3" />워크플레이스</a>
		</div>
		<ul class="ct02" id="wpToggle">
			<li><a id="wp01" href="javascript:selectMenuItem('wp01', this);" onfocus="this.blur();">채팅</a></li>
			<li><a id="wp02" href="javascript:selectMenuItem('wp02', this);" onfocus="this.blur();">게시판</a></li>
		</ul>
		<div class="ct01">
			<a href="javaScript:void(0);" onfocus="this.blur();"><img src="<c:url value='images/btn_sm01.gif' />" class="vam2 magR3" />진행중 설문</a>
		</div>
		<div class="ct01">
			<a href="javaScript:void(0);" onfocus="this.blur();"><img src="<c:url value='images/btn_sm01.gif' />" class="vam2 magR3" />지난 설문</a>
		</div>
		<div class="ct01">
			<a href="javaScript:void(0);" onfocus="this.blur();"><img src="<c:url value='images/btn_sm01.gif' />" class="vam2 magR3" />설문 요청</a>
		</div>
	</div>