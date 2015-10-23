if (typeof samsung == "undefined") {
	function samsung() {
	}
}
if (typeof samsung.neo == "undefined") {
	samsung.neo = function() {
	}
}
if (typeof samsung.neo.commons == "undefined") {
	samsung.neo.commons = function() {
	}
}

samsung.neo.commons.Utils = EP = ep = {
	showPopup : function(mode, fn, companyId, parameter) {
		var defaults = {
			url : "/main.do",
			title : "기본 부서 선택",
			width : 794,
			height : 571,
			param : {
				companyId : companyId,
				parameter : parameter
			},
			onClose : fn
		}
		var options = {
			"SINGLE_DEPT" : {
				title : "단일 부서 선택",
				width : 471,
				height : 571,
				param : {
					frameMode : "singlePanel",
					companyId : companyId,
					useTabs : "orgTab",
					parameter : parameter
				}
			},
			"MULTI_DEPT" : {
				title : "다중 부서 선택",
				width : 794,
				height : 571,
				param : {
					companyId : companyId,
					useTabs : "orgTab",
					parameter : parameter
				}
			}
		}
		if (mode == "SINGLE_DEPT")
			$.modal($.extend(defaults, options.SINGLE_DEPT));
		else if (mode == "MULTI_DEPT")
			$.modal($.extend(defaults, options.MULTI_DEPT));
	}
}
