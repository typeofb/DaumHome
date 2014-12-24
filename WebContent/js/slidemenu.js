/* TriggerClick 0:close, 1:leftopen, 2:rightopen */
TriggerClick = 0;

$(document).ready(function() {
	// sidemenu left
	$('a#bt_laside').click(function() {
		if (TriggerClick == 0) {
			$('body').addClass('open-menu');
			$('.bg_modal').show();
			$('.bg_modal').animate({
				opacity : 0.25
			}, 'slow', function() {
				// Animation complete.
				TriggerClick = 1;
			});
			//$('#left-menu').show();
			$('#left-menu').animate({
				left : "0"
			}, 'slow');
		}
		return false;
	});

	// sidemenu right
	$('a#bt_raside').click(function() {
		if (TriggerClick == 0) {
			var rp = $('body').innerWidth() - $('#right-menu').width();
			$('body').addClass('open-menu');
			$('.bg_modal').show();
			$('.bg_modal').animate({
				opacity : 0.25
			}, 'slow', function() {
				// Animation complete.
				TriggerClick = 2;
			});
			//$('#right-menu').show();
			$('#right-menu').animate({
				left : rp
			}, 'slow');
		}
		return false;
	});

	// sidemenu folding
	$('.bg_modal').click(function() {
		if (TriggerClick == 1) {
			$('body').removeClass();
			$('#left-menu').animate({
				left : -$('#left-menu').width()
			}, 'slow');
			$(".bg_modal").animate({
				opacity : 0
			}, 'slow', function() {
				// Animation complete.
				$(".bg_modal").hide();
				//$('#left-menu').hide();
				TriggerClick = 0;
			});
		} else if (TriggerClick == 2) {
			$('body').removeClass();
			$('#right-menu').animate({
				left : $('body').innerWidth()
			}, 'slow');
			$(".bg_modal").animate({
				opacity : 0
			}, 'slow', function() {
				// Animation complete.
				$(".bg_modal").hide();
				//$('#right-menu').hide();
				TriggerClick = 0;
			});
		}
	});

	// sidemenu reset
	$(window).resize(function() {
		$('#left-menu').height($(document).height());
		$('#right-menu').height($(document).height());

		if (TriggerClick == 2) {
			$('#right-menu').css({
				left : ($('body').innerWidth() - $('#right-menu').width())
			});
		} else {
			$('#right-menu').css({
				left : $('body').innerWidth()
			});
		}
	});
	$(window).trigger('resize');

	// folding left
	$('#bt_lasideClose').click(function() {
		if (TriggerClick == 1) {
			$('body').removeClass();
			$('#left-menu').animate({
				left : -$('#left-menu').width()
			}, 'slow');
			$(".bg_modal").animate({
				opacity : 0
			}, 'slow', function() {
				// Animation complete.
				$(".bg_modal").hide();
				//$('#left-menu').hide();
				TriggerClick = 0;
			});
		} else if (TriggerClick == 2) {
			$('body').removeClass();
			$('#right-menu').animate({
				left : $('body').innerWidth()
			}, 'slow');
			$(".bg_modal").animate({
				opacity : 0
			}, 'slow', function() {
				// Animation complete.
				$(".bg_modal").hide();
				//$('#right-menu').hide();
				TriggerClick = 0;
			});
		}
	});

	// folding right
	$('#bt_rasideClose').click(function() {
		if (TriggerClick == 1) {
			$('body').removeClass();
			$('#left-menu').animate({
				left : -$('#left-menu').width()
			}, 'slow');
			$(".bg_modal").animate({
				opacity : 0
			}, 'slow', function() {
				// Animation complete.
				$(".bg_modal").hide();
				//$('#left-menu').hide();
				TriggerClick = 0;
			});
		} else if (TriggerClick == 2) {
			$('body').removeClass();
			$('#right-menu').animate({
				left : $('body').innerWidth()
			}, 'slow');
			$(".bg_modal").animate({
				opacity : 0
			}, 'slow', function() {
				// Animation complete.
				$(".bg_modal").hide();
				//$('#right-menu').hide();
				TriggerClick = 0;
			});
		}
	});
});