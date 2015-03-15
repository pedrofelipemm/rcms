$(document).ready(function() {
	if (!$('#myCanvas').tagcanvas({
		textColour : '#ffffff',
		outlineThickness : 1,
		maxSpeed : 0.1,
		depth : 0.75
	})) {
		$('#myCanvasContainer').hide();
	}
});