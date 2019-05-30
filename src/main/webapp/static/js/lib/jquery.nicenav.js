/* 代码整理：大头网 www.datouwang.com */
; (function ($) {
    $.fn.extend({
        'nicenav': function (con, curIndex) {
            con = typeof con === 'number' ? con : 400;
            var $lis = $(this).children('li'), $h = $(this).children('.buoy');
            curIndex = curIndex || 0;

            $lis.each(function() {
                if ($(this).hasClass('active')) {
                    curIndex = $(this).index();
                }
            })

            $lis.hover(function () {
                if ($h.is(":visible")) {
                    $h.stop().animate({
                        'left': $(this)[0].offsetLeft,
                        'width': $(this).width()
                    }, con);
                }
            }, function () {
                if ($h.is(":visible")) {
                    $h.stop().animate({
                        'left': $lis.eq(curIndex)[0].offsetLeft,
                        'width': $lis.eq(curIndex).width()
                    }, con);
                }
            }).mousedown(function() {
                curIndex = $(this).index();
                $(this).addClass('active').siblings().removeClass('active');
            });

            $h.stop().animate({
                'left': $lis.eq(curIndex)[0].offsetLeft,
                'width': $lis.eq(curIndex).width()
            });

            $(window).resize(resize);
            resize();

            function resize() {
                if ($lis.eq(0).css("float") == "left") {
                    $h.show();
                    $lis.eq(curIndex).trigger("mouseleave");
                } else {
                    $h.hide();
                }
            }
        }
    });
}(jQuery));