(function () {
    "use strict";

    function hideExtraTickets() {
        for (let i = 6; i <= $(".container").children().length; i++) {
            $(".container").children().eq(i).hide();
        }
    }

    $(document).ready(function () {
        if ($(".container").children().length <= 6) {
            $('#showMore').hide();
        } else {
            hideExtraTickets();
        }
        let n = 6;
        $("#showMore").on("click", function () {
            for (let i = n; i < $(".container").children().length; i++) {
                $(".container").children().eq(i).show();
                n++;
            }
            if (n === $(".container").children().length) {
                $('#showMore').hide();
            }
        });
    });

})();