var rcms = (function() {
	return {
		setFocusFirstElement : function() {
			$(":input:visible:enabled:first").focus();
		},
		applyNiceScroll : function(elements) {
			elements.forEach(function(element) {
				element.niceScroll();
			});
		},
		applyAnimation : function(elements, option) {
			var cleanAnimation = function(element) {
				$(element).removeClass('animated');
				((option != null) ? $(element).removeClass(option) : $(element)
						.removeClass($(element).attr("data-animation")));
			};
			elements.forEach(function(element) {
				$(element).on(
						'mouseenter',
						function() {
							cleanAnimation(element);
							$(this).addClass('animated');
							((option != null) ? $(this).addClass(option) : $(
									this).addClass(
									$(this).attr("data-animation")));
						});
				$(element).on('mouseleave', function() {
					setTimeout(function() {
						cleanAnimation(element);
					}, 1000);
				});
			});
		},
		applyTagCanvas : function(elements) {
			elements.forEach(function(element) {
				if (!element.container.tagcanvas({
					textColour : '#ffffff',
					outlineThickness : 1,
					maxSpeed : 0.1,
					depth : 0.75
				})) {
					element.canvas.hide();
				}
			});
		}
	};
})();