<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-touch-fullscreen" content="yes" />
<title>세이브더칠드런</title>
<link rel="stylesheet" type="text/css" href="css/mobile.css">
<link rel="stylesheet" type="text/css" href="css/idangerous.swiper.css">
</head>
<body>
	<div id="left-menu" class="left-menu">
		<div class="d-table">
			<div class="d-row">
				<div class="d-cell1">left sidebar</div>
				<div class="d-cell2" id="bt_lasideClose"><a href="javascript:;" style="color: #fff;">X</a></div>
			</div>
		</div>
	</div>
	<div id="right-menu" class="right-menu">
		<div class="d-table">
			<div class="d-row">
				<div class="d-cell1">right sidebar</div>
				<div class="d-cell2" id="bt_rasideClose"><a href="javascript:;" style="color: #fff;">X</a></div>
			</div>
		</div>
	</div>
	<div class="bg_modal"></div>
	<div class="wrap" id="content-wrapper">
		<div class="header">
			<a href="${pageContext.request.contextPath}/mobile.do" class="BTgotoMain"
				style="position: relative; left: 50%; margin-left: -115px;">매인</a>
			<a href="javascript:;" id="bt_laside" style="float: left;">좌확장영역열기</a>
			<a href="javascript:;" id="bt_raside" style="float: right;">우확장영역열기</a>
			<div class="tabs">
				<a style="display: none;" class="BTgotoMain">Tab 0</a>
				<a href="javascript:;">Tab 1</a>
				<a href="javascript:;" style="margin: 0 17px;">Tab 2</a>
				<a href="javascript:;">Tab 3</a>
			</div>
		</div>
		<div class="container">
			<div class="SlideContent">
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<!-- Main 시작 -->
						<div class="swiper-slide">
							<div class="content-slide">
								<div class="sc-lnb">
									<div class="swiper-wrapper">
										<div class="swiper-slide">
											<div class="title">전체</div>
										</div>
										<div class="swiper-slide">
											<div class="title">세이브더칠드런</div>
										</div>
										<div class="swiper-slide">
											<div class="title">후원하기</div>
										</div>
										<div class="swiper-slide">
											<div class="title">캠페인</div>
										</div>
										<div class="swiper-slide">
											<div class="title">메뉴 5</div>
										</div>
										<div class="swiper-slide">
											<div class="title">메뉴 6</div>
										</div>
										<div class="swiper-slide">
											<div class="title">메뉴 7</div>
										</div>
										<div class="swiper-slide">
											<div class="title">메뉴 8</div>
										</div>
										<div class="swiper-slide">
											<div class="title">메뉴 9</div>
										</div>
										<div class="swiper-slide">
											<div class="title">메뉴 10</div>
										</div>
									</div>
									<a class="sc-lnb-left" style="position: absolute; left: 0; top: 0; background: #aaa; width: 30px; height: 100%; line-height: 45px; z-index: 1000;"></a>
									<a class="sc-lnb-right" style="position: absolute; right: 0; top: 0; background: #aaa; width: 30px; height: 100%; line-height: 45px;">></a>
								</div>
								<div class="sc-lnb-pagination"></div>
								<h2>Main</h2>
								<p>매인</p>
							</div>
						</div>
						<!-- Main 끝 -->
						<!-- Tab 1 시작 -->
						<div class="swiper-slide">
							<div class="content-slide">
								<h2>Tab 1</h2>
								<p>1번째 컨텐트</p>
							</div>
						</div>
						<!-- Tab 1 끝 -->
						<!-- Tab 2 시작 -->
						<div class="swiper-slide">
							<div class="content-slide">
								<h2>Tab 2</h2>
								<p>2번째 컨텐트</p>
							</div>
						</div>
						<!-- Tab 2 끝 -->
						<!-- Tab 3 시작 -->
						<div class="swiper-slide">
							<div class="content-slide" id="newsList">
								<%@ include file="mobileTab3.jsp"%>
							</div>
						</div>
						<!-- Tab 3 끝 -->
					</div>
				</div>
				<div id="content"></div>
			</div>
		</div>
		<div class="footer">
			<a href="${pageContext.request.contextPath}/main.do">PC버전보기</a>
		</div>
	</div>
	<form>
		<input type="hidden" id="targetPage" name="targetPage" value="${targetPage}" />
	</form>
</body>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/idangerous.swiper-2.1.min.js"></script>
<script src="js/jquery.animate-enhanced.min.js"></script>
<script src="js/slidemenu.js"></script>
<script>
var scLNB = new Swiper(".sc-lnb", {
	watchActiveIndex : true,
	slidesPerView : "auto",
	onTouchMove : function() {
		if (scLNB.activeIndex == 0) {
			$(".sc-lnb-left").addClass("hc");
		} else {
			$(".sc-lnb-left").removeClass("hc");
		}
		if (scLNB.activeIndex > scLNB.slides.length / 3) {
			$(".sc-lnb-right").addClass("hc");
		} else {
			$(".sc-lnb-right").removeClass("hc");
		}
	}
});
$(".sc-lnb-left").click(function(e) {
	e.preventDefault();
	scLNB.swipeTo(0);
	$(".sc-lnb-left").addClass("hc");
	$(".sc-lnb-right").removeClass("hc");
});
$(".sc-lnb-right").click(function(e) {
	e.preventDefault();
	scLNB.swipeTo(9);
	$(".sc-lnb-right").addClass("hc");
	$(".sc-lnb-left").removeClass("hc");
});

var tabsSwiper = new Swiper(".swiper-container", {
	speed : 500,
	onSlideChangeStart : function() {
		$(".tabs .active").removeClass("active");
		$(".tabs a").eq(tabsSwiper.activeIndex).addClass("active");
		
		if ($("#newsList").find(":first-child").attr("deptId") != 418) {
			$.ajax({
				url : "mobileTab3.do",
				data : { deptId : 418 },
				type : "post",
				cache : false,
				dataType : "html",
				success : function(data) {
					$("#newsList").html(data);
				}
			});
		}
	}
});
$(".tabs a").on("touchstart mousedown", function(e) {
	e.preventDefault();
	$(".tabs .active").removeClass("active");
	$(this).addClass("active");
	tabsSwiper.swipeTo($(this).index());
});
$(".tabs a").click(function(e) {
	e.preventDefault();
});

$(".BTgotoMain").click(function(e) {
	e.preventDefault();
	tabsSwiper.swipeTo(0);
});

function mobileList(obj) {
	$.ajax({
		url : "mobileList.do",
		data : { deptId : $(obj).attr("deptId") },
		type : "post",
		cache : false,
		dataType : "html",
		success : function(data) {
			$("#newsList").html(data);
		}
	});
}

function goPage(targetPage) {
	$("#targetPage").val(targetPage);
	$.ajax({
		url : "mobileList.do",
		data : $("form").serializeArray(),
		type : "post",
		cache : false,
		dataType : "html",
		success : function(data) {
			$("#newsList").html(data);
		}
	});
}
</script>
</html>
