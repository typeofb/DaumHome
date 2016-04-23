<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	$(".lnbArea .ct01 a").click(function(){
		$(this).closest("div").next("ul").toggle();
	});
});
</script>
	<div class="lnbArea">
		<div class="ct01">
			<a href="javascript:void(0);" onfocus="this.blur();"><img src="images/btn_sm01.gif" class="vam2 magR3" />게시판</a>
		</div>
		<ul class="ct02" id="bbsToggle">
			<li><a id="bbs01" href="javascript:void(0);" onfocus="this.blur();" onclick="javascript:selectMenuItem('bbs01', this);">게시글</a></li>
			<li><a id="bbs02" href="javascript:void(0);" onfocus="this.blur();" onclick="javascript:selectMenuItem('bbs02', this);">댓글</a></li>
			<li><a id="bbs03" href="javascript:void(0);" onfocus="this.blur();" onclick="javascript:selectMenuItem('bbs03', this);">모아보기</a></li>
		</ul>
		<div class="ct01">
			<a href="javascript:void(0);" onfocus="this.blur();"><img src="images/btn_sm01.gif" class="vam2 magR3" />블로그</a>
		</div>
		<ul class="ct02" id="blogToggle">
			<li><a id="blog01" href="javascript:void(0);" onfocus="this.blur();" onclick="javascript:selectMenuItem('blog01', this);">포스트</a></li>
			<li><a id="blog02" href="javascript:void(0);" onfocus="this.blur();" onclick="javascript:selectMenuItem('blog02', this);">댓글</a></li>
			<li><a id="blog03" href="javascript:void(0);" onfocus="this.blur();" onclick="javascript:selectMenuItem('blog03', this);">방명록</a></li>
			<li><a id="blog04" href="javascript:void(0);" onfocus="this.blur();" onclick="javascript:selectMenuItem('blog04', this);">Q&amp;A</a></li>
		</ul>
		<div class="ct01">
			<a href="javascript:void(0);" onfocus="this.blur();"><img src="images/btn_sm01.gif" class="vam2 magR3" />워크플레이스</a>
		</div>
		<ul class="ct02" id="wpToggle">
			<li><a id="wp01" href="javascript:void(0);" onfocus="this.blur();" onclick="javascript:selectMenuItem('wp01', this);">게시글</a></li>
			<li><a id="wp02" href="javascript:void(0);" onfocus="this.blur();" onclick="javascript:selectMenuItem('wp02', this);">댓글</a></li>
		</ul>
		<div class="ct01">
			<a href="javaScript:selectMenuItem('menu_21');"><img src="images/btn_sm01.gif" class="vam2 magR3" />진행중 설문</a>
		</div>
		<div class="ct01">
			<a href="javaScript:selectMenuItem('menu_22');"><img src="images/btn_sm01.gif" class="vam2 magR3" />지난 설문</a>
		</div>
		<div class="ct01">
			<a href="javaScript:selectMenuItem('menu_23');"><img src="images/btn_sm01.gif" class="vam2 magR3" />설문 요청</a>
		</div>
	</div>