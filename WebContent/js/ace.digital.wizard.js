(function(a, b) {
	a.fn.ace_digital_wizard = function(prevBtnId, nextBtnId) {
		this.each(function() {
			var h = a(this);
			var d = h.find("li");
			var e = d.length;
			var f = parseInt(100 / e) + "%";
			d.css({
				"min-width" : f,
				"max-width" : f
			});
			h.removeClass("hidden").wizard();
			var g = h.nextAll(".wizard-actions").eq(0);
			var i = h.data("wizard");

			i.$prevBtn = $("#" + prevBtnId).on("click", function() {
				h.wizard("previous");
			});

			i.$nextBtn = $("#" + nextBtnId).on("click", function() {
				h.wizard("next");
			});

			i.nextText = i.$nextBtn.text();
		});
		return this;
	}
})(jQuery);