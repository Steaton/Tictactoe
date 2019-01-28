var gameId;
var status;

$(function() {
    $("table").on('click', 'td', function () {
       if (status === "IN_PROGRESS" && !$(this).hasClass("X") && !$(this).hasClass("O")) {
           var x = $(this).attr('id').charAt(0);
           var y = $(this).attr('id').charAt(1);
           var url = "/executeTurn?gameId=" + gameId + "&turn=X&column=" + x + "&row=" + y;
           executeTurn(url, "X");
       }
    });

    startNewGame();
});

function takeTurn() {
    console.log(this);

}

function startNewGame() {
    $.ajax({
        type: 'GET',
        url: "/startNewGame",
        dataType: 'json',
        success: function(response) {
            gameId = response.gameId;
            status = response.gameStatus;
        }
    });
}

function executeTurn(url, turn) {
    $.ajax({
        type: 'POST',
        url: url,
        dataType: 'json',
        success: function(response) {
            updateGame(response.gameStatus);
            updateBoard(turn, response.lastMove);
            if (turn === "X") {
                var computerUrl = "/executeComputerTurn?gameId=" + gameId + "&turn=O";
                executeTurn(computerUrl, "O");
            }
        }
    });
}

function updateGame(gameStatus) {
    if (gameStatus === "IN_PROGRESS") {
        return;
    }  
    if (gameStatus === "X_WON") {
        status = "X_WON";
        $("h2").html("Player Wins!");
    }
    if (gameStatus === "O_WON") {
        status = "O_WON";
        $("h2").html("Computer Wins!");
    }
    if (gameStatus === "DRAW") {
        status = "DRAW";
        $("h2").html("Game is a Draw!");
    }
}

function updateBoard(turn, move) {
    var id = move.x + "" + move.y;
    $('#' + id).addClass(turn);
    $('#' + id).html(turn);
}