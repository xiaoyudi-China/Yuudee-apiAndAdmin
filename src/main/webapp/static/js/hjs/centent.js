$(function () {
    var e = window.document,
        t = e.documentElement,
        i = 750,
        d = i / 100
    var n = t.clientWidth || 320;
    n > 750 && (n = 750);
    var a = (n / d)
    var s = document.body.clientHeight / a - (1.64 + 2.34);
    $('.centent').css('height', s + 'rem')
})