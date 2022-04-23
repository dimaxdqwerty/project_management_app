let $content = $("div.card");

$('#sort').on("click", function () {

    let type = $('#type').find(":selected").text();
    let order = $('#order').find(":selected").text();

    $("#showMore").click();

    switch (type) {
        case 'Everything':
            showAllTickets();
            break;
        case 'Tasks':
            showSpecialTickets("tasks");
            break;
        case 'Subtasks':
            showSpecialTickets("subtasks");
            break;
        case 'Bugs':
            showSpecialTickets("bugs");
            break;
    }

    let typeSort;
    switch (order) {
        case 'Descending':
            typeSort = $content.sort(function (a, b) {
                return ($(a).find("div").find("div").find("h2").attr("data-title").toLowerCase()) >
                ($(b).find("div").find("div").find("h2").attr("data-title").toLowerCase())
                    ? 1 : -1;
            });
            break;
        case 'Ascending':
            typeSort = $content.sort(function (a, b) {
                return ($(a).find("div").find("div").find("h2").attr("data-title").toLowerCase()) <
                ($(b).find("div").find("div").find("h2").attr("data-title").toLowerCase())
                    ? 1 : -1;
            });
            break;
    }

    $(".container").html(typeSort);
});

function showAllTickets() {
    for (let i = 0; i <= $(".container").children().length; i++) {
        $(".container").children().eq(i).show();
    }
}

function showSpecialTickets(ticketType) {
    hideAllTickets();
    for (let i = 0; i <= $(".container").children().length; i++) {
        let ticket = $(".container").children().eq(i).find("div").find("h3").attr("data-type");
        if (ticket && (ticket.toLowerCase() + 's' === ticketType)) {
            $(".container").children().eq(i).show();
        }
    }
}

function hideAllTickets() {
    for (let i = 0; i <= $(".container").children().length; i++) {
        $(".container").children().eq(i).hide();
    }
}
