(function($) {
	$.modal = function(options) {
		var defaults = {
			url : "",
			title : "",
			scroll : "auto",
			width : 350,
			height : 100,
			top : "0",
			left : "0",
			param : "",
			context : "/DaumHome",
			pos : "center",
			modal : "Y",
			resizable : "no",
			win : (window.dialogArguments) ? window.dialogArguments.win : window,
			onClose : function cb(val) {
			}
		};
		var param = $.extend(defaults, options);
		return jQuery.modal.util.showDialog(param);
	};
	$.modeless = function(options) {
		var defaults = {
			modal : "N"
		};
		var options = $.extend(defaults, options);
		return jQuery.modal(options);
	};
	$.modal.util = {
		showDialog : function(options) {
			var rtnVal;
			options.height += 100;
			var features = "dialogHeight:" + options.height + "px; dialogWidth:" + options.width + "px;";
			if (options.pos == "relative") {
				var x = event.x;
				var y = event.y;
				var oW, oH, cW, cH;
				oW = document.body.offsetWidth;
				oH = document.body.offsetHeight;
				cW = x + parseInt(options.width, 10) + 20;
				cH = y + parseInt(options.height, 10) + 5;
				if (cW > oW)
					x = x - (cW - oW);
				if (cH > oH)
					y = y - (cH - oH);
				x += window.screenLeft;
				y += window.screenTop;
				features += "dialogLeft:" + x + "px; dialogTop:" + y + "px;";
			} else if (options.pos == "absolute")
				features += "dialogLeft:" + options.left + "px; dialogTop:" + options.top + "px;";
			else
				features += "center:on;";
			features += "help:no; resizable:" + options.resizable + "; status:no; scroll:no; unadorned:yes;";
			
			var title = encodeURIComponent(options.title);
			
			// https://developer.mozilla.org/en/DOM/window.showModalDialog
			if (options.modal == "Y")
				rtnVal = window.showModalDialog(options.context + "/modal.jsp?title=" + title + "&scroll=" + options.scroll, options, features);
			else
				rtnVal = window.showModelessDialog(options.context + "/modal.jsp?title=" + title + "&scroll=" + options.scroll, options, features);
			if (jQuery.isFunction(options.onClose))
				options.onClose(rtnVal);
			
			return rtnVal;
		}
	}
})(jQuery);
