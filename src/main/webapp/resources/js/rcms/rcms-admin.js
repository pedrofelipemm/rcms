$(document).ready(function() {
	
	this.applyNiceScroll = function(elements) {
		elements.forEach(function(element) {
			element.niceScroll();
		});
	}
	
	this.applyNiceScroll([$('html'), $('#admin-menu-left')]);
	
	$(":input:visible:enabled:first").focus();
});