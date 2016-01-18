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
	},
	ajax : function(p) {
		var blockDefaults = {
			turnOff : false, // true : 사용안함, false : 사용
			type : 'message', // message, image, none
			message : "처리중입니다.",
			timeout : 60 * 1000, // 1분으로 timeout 세팅
			forceIframe : false,
			showOverlay : true
		}

		var blockParams = $.extend(blockDefaults, p.block);

		var progressHTML;
		if (!blockParams.turnOff) {
			progressHTML = '<div id="blockHTML" style="display:none;position:relative;padding:21px;">';
			progressHTML += '	<table><colgroup><col width="100%"/><col width="50"/></colgroup><tr>'
			progressHTML += '	<td><span id="blockHTML_MSG" style="font-size: 9pt;">' + blockParams.message + '</span></td>';
			progressHTML += '	<td><img align="absmiddle" src="/DaumHome/images/viewLoading.gif" /></td>';
			progressHTML += '	</tr></table>'
			progressHTML += '</div>';
		}

		var block4Message = {
			message : $(progressHTML),
			css : {
				width : '275px',
				border : '3px solid #78A9ED',
				top : '40%',
				left : '40%',
				textAlign : 'left',
				cursor : 'default'
			},
			overlayCSS : {
				opacity : 0,
				cursor : 'default'
			},
			timeout : 60 * 1000
		}
		var block4Image = {
			message : '<img align="absmiddle" src="/DaumHome/images/viewLoading.gif" />',
			css : {
				width : '50px',
				border : 'none',
				top : '40%',
				left : '47%',
				textAlign : 'center',
				cursor : 'default',
				background : 'none'
			},
			overlayCSS : {
				opacity : 0,
				cursor : 'default'
			},
			timeout : 60 * 1000
		}
		var block4UserDefined = {
			message : null,
			overlayCSS : {
				opacity : 0,
				cursor : 'default'
			}
		}

		var _showBlockUI = null;
		var _hideBlockUI = null;

		if (blockParams.turnOff) {
			blockParams = null;
			_showBlockUI = function() {
			}
			_hideBlockUI = function() {
			}
		} else {
			if (blockParams.type == 'message') {
				blockParams = $.extend(blockParams, block4Message);
			} else if (blockParams.type == 'image') {
				blockParams = $.extend(blockParams, block4Image);
			} else if (blockParams.type == 'none') {
				blockParams = $.extend(blockParams, block4UserDefined);
			} else {
				blockParams = $.extend(blockParams, block4UserDefined);
			}

			if (p.block && p.block.opacity != null && p.block.opacity !== undefined) {
				blockParams.overlayCSS.opacity = p.block.opacity;
			}

			if (blockParams.type == 'message' || blockParams.type == 'image' || blockParams.type == 'none') {
				if (blockParams.target && blockParams.target !== undefined) {
					_showBlockUI = function() {
						blockParams.target.block(blockParams);
					}
					_hideBlockUI = function() {
						var full = true;
						var els;
						if (full)
							els = $('body').children().filter('.blockUI').add('body > .blockUI');
						else
							els = $('.blockUI', el);
						els.hide();

						blockParams.target.unblock();
					}
				} else {
					_showBlockUI = function() {
						$.blockUI(blockParams);
					}
					_hideBlockUI = function() {
						var full = true;
						var els;
						if (full)
							els = $('body').children().filter('.blockUI').add('body > .blockUI');
						else
							els = $('.blockUI', el);
						els.hide();

						$.unblockUI();
					}
				}
			} else {
				if (blockParams.target && blockParams.target !== undefined) {
					_showBlockUI = function() {
						p.block.message.show();
						blockParams.target.block(blockParams);
					}
					_hideBlockUI = function() {
						p.block.message.hide();

						var full = true;
						var els;
						if (full)
							els = $('body').children().filter('.blockUI').add('body > .blockUI');
						else
							els = $('.blockUI', el);
						els.hide();

						blockParams.target.unblock();
					}
				} else {
					_showBlockUI = function() {
						p.block.message.show();
						$.blockUI(blockParams);
					}
					_hideBlockUI = function() {
						p.block.message.hide();

						var full = true;
						var els;
						if (full)
							els = $('body').children().filter('.blockUI').add('body > .blockUI');
						else
							els = $('.blockUI', el);
						els.hide();

						$.unblockUI();
					}
				}
			}
		}

		var _handleSuccess = function(data) {
			p.callback(data);
		}

		var _handleError = function() {
			try {
				p.error();
			} catch (e) {
			}
		}

		var ajaxDefaults = {
			async : true,
			type : "POST",
			timeout : 60 * 1000,
			dataType : 'html',
			data : null,
			beforeSend : function() {
				_showBlockUI();
			},
			success : function(data) {
				_hideBlockUI();
				_handleSuccess(data);
			},
			error : function(xhr, s, e) {
				_hideBlockUI();
				_handleError();
				if (s == "timeout") {
					alert("처리가 지연되고 있습니다.\r\n\r\n잠시 후 다시 시도해 주시기 바랍니다.");
				} else if (s == "error") {
					var xhrStatus = 0;
					try {
						xhrStatus = xhr.status;
					} catch (e) {
					}
					if (xhrStatus == 401) { // 인증에러
						$.modal({
							url : "/DaumHome/login.do",
							title : "로그인",
							width : 468,
							height : 354,
							scroll : "no",
							onClose : function cb(val) {
								if (val == "LOGIN_SUCCESS") {
									ep.ajax(p); // 로그인 후 다시 요청 재귀호출
								}
							}
						});
					} else {
						var startIndex = $.trim(xhr.responseText).indexOf("<alertErrorMsg>");
						if (xhrStatus == 500 && startIndex != -1) {
							var endIndex = $.trim(xhr.responseText).indexOf("</alertErrorMsg>");
							var errorMsg = $.trim(xhr.responseText).substring(startIndex + 15, endIndex);
							alert(errorMsg);
						}
					}
				} else if (s == "parsererror") {
					alert("서버로부터 받은 데이터를 처리하던 중 오류가 발생하였습니다. : " + xhr.responseText);
				} else {
					alert("서버와의 통신 중 알 수 없는 오류가 발생하였습니다. : " + "[status:" + s + (e != null && e !== undefined) ? (",error:" + e.message) : "" + "]");
				}
			}
		}

		var ajaxParams = $.extend(ajaxDefaults, p);
		$.ajax(ajaxParams);
	}
}
