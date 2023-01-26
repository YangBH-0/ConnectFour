package connectfour

fun main() {
    val game = Four()
    game.start()
}

val rowRange = (5..9)
val columnRange = (5..9)

class Four {
    private val MAX_PLAYER = 2
    private val players = MutableList<String>(MAX_PLAYER) { "unknown" }
    private val score = MutableList<Int>(MAX_PLAYER) { 0 }
    private var board = MutableList(6) { MutableList(7) { -1 } }
    private val boardRegex = Regex("""[\d]+[\s]*[xX][\s]*[\d]+""")
    private val intRegex = Regex("[0-9]+")
    private val boardSign = listOf("o", "*")
    private val endSign = "end"
    private var count = 0
    private var flag = false
    private var endFlag = false
    private var gamesNum = 1
    fun start() {
        welcome()
        joinPlayer()
        setBoard()
        selectGames()
        play()
    }

    private fun welcome() {
        println("Connect Four")
    }

    private fun joinPlayer() {
        val sequence = listOf("First", "Second")
        for (i in 0 until MAX_PLAYER) {
            println("${sequence[i]} player's name:")
            players[i] = readln()
        }
    }

    private fun setBoard() {
        var row = 6
        var column = 7
        do {
            println("Set the board dimensions (Rows x Columns)")
            println("Press Enter for default (6 x 7)")
            val input = readln().trim()
            if (input.isEmpty()) break
            if (!boardRegex.matches(input)) {
                println("Invalid input")
                continue
            }
            val splitChar = if (input.contains("x")) 'x' else 'X'
            row = input.split(splitChar)[0].trim().toInt()
            if (row !in rowRange) {
                println("Board rows should be from 5 to 9")
                continue
            }
            column = input.split(splitChar)[1].trim().toInt()
            if (column !in columnRange) {
                println("Board columns should be from 5 to 9")
                continue
            }
            break
        } while (true)
        createArray(row, column)
    }

    private fun createArray(a: Int, b: Int) {
        board = MutableList(a) { MutableList(b) { -1 } }
    }

    private fun selectGames() {
        do {
            println("""
                Do you want to play single or multiple games?
                For a single game, input 1 or press Enter
                Input a number of games:
                """.trimIndent())
            val input = readln().trim()
            if (input.isEmpty()) break
            if (!intRegex.matches(input)) {
                println("Invalid input")
                continue
            }
            if (Regex("0").matches(input)) {
                println("Invalid input")
                continue
            }
            gamesNum = input.toInt()
            break
        } while (true)

    }

    private fun play() {
        var gameCount = 0
        initGame()
        while (gameCount < gamesNum) {
            if (gamesNum != 1) println("Game #${gameCount + 1}")
            drawBoard()
            while (flag) {
                round(gameCount)
            }
            if(endFlag) break
            println("""
                Score
                ${players[0]}: ${score[0]} ${players[1]}: ${score[1]}
            """.trimIndent())
            gameCount++
            flag = true
            initBoard()
        }
        end()
    }

    private fun initBoard(){
        createArray(board.size,board[0].size)
        count = 0
    }

    private fun initGame() {
        println("${players[0]} VS ${players[1]}")
        println("${board.size} X ${board[0].size} board")
        if (gamesNum != 1) println("Total $gamesNum games") else println("Single game")
        flag = true
    }

    private fun drawBoard() {
        var boardCanvas = ""
        for (i in board[0].indices) {
            print(" ${i + 1}")
        }
        println()
        for (element in board) {
            for (j in 0 until element.size) {
                print("║")
                print(if (element[j] == -1) " " else boardSign[element[j]])
            }
            print("║")
            println()
        }
        boardCanvas += "╚═"
        for (i in 1 until board.last().size) {
            boardCanvas += "╩═"
        }
        boardCanvas += "╝\n"
        print(boardCanvas)
    }

    private fun round(c: Int) {
        if(c%2 ==0) {
            for (i in players.indices) {
                if (!flag) return
                turn(i)
            }
        }else{
            for(i in players.size - 1 downTo 0){
                if (!flag) return
                turn(i)
            }
        }
    }

    private fun turn(index: Int) {
        val wrongReply = "Incorrect column number"
        var oRow = 0
        var oColumn = 0
        loop@ while (true) {
            println("${players[index]}'s turn:")
            val input = readln().trim()
            if (input == endSign) {
                flag = false
                endFlag = true
                return
            }
            if (!intRegex.matches(input)) {
                println(wrongReply)
                continue
            }
            val column = input.toInt() - 1
            if (column !in 0 until board[0].size) {
                println("The column number is out of range (1 - ${board[0].size})")
                continue
            }
            for (i in board.indices.reversed()) {
                if (board[i][column] == -1) {
                    board[i][column] = index
                    count++
                    oRow = i
                    oColumn = column
                    break@loop
                }
            }
            println("Column ${column + 1} is full")
        }
        drawBoard()
        if (checkWinCondition(oRow, oColumn)) {
            score[index]+=2
            println("Player ${players[index]} won")
        }
    }

    private fun checkWinCondition(x: Int, y: Int): Boolean {
        // check win condition
        var count = 0
        val right: Boolean = y + 3 < board[0].size
        val left: Boolean = y - 3 >= 0
        val down: Boolean = x + 3 < board.size
        val upper: Boolean = x - 3 >= 0
        // horizon (column, right)
        if (right) {
            for (i in 0..3) {
                if (board[x][y] != board[x][y + i]) break
                count++
            }
            if (count == 4) {
                flag = false
                return true
            }
        }
        // horizon (column, left)
        if (left) {
            count = 0
            for (i in 0..3) {
                if (board[x][y] != board[x][y - i]) break
                count++
            }
            if (count == 4) {
                flag = false
                return true
            }
        }
        // vertical (down)
        if (down) {
            count = 0
            for (i in 0..3) {
                if (board[x][y] != board[x + i][y]) break
                count++
            }
            if (count == 4) {
                flag = false
                return true
            }
        }
        // diagonal (right, down)
        if (right && down) {
            count = 0
            for (i in 0..3) {
                if (board[x][y] != board[x + i][y + i]) break
                count++
            }
            if (count == 4) {
                flag = false
                return true
            }
        }

        // diagonal (left, down)
        if (left && down) {
            count = 0
            for (i in 0..3) {
                if (board[x][y] != board[x + i][y - i]) break
                count++
            }
            if (count == 4) {
                flag = false
                return true
            }
        }
        // diagonal (right, upper)
        if (right && upper) {
            count = 0
            for (i in 0..3) {
                if (board[x][y] != board[x - i][y + i]) break
                count++
            }
            if (count == 4) {
                flag = false
                return true
            }
        }
        // diagonal (left, upper)
        if (left && upper) {
            count = 0
            for (i in 0..3) {
                if (board[x][y] != board[x - i][y - i]) break
                count++
            }
            if (count == 4) {
                flag = false
                return true
            }
        }
        //draw
        if (this.count == board.size * board[0].size) {
            println("It is a draw")
            score[0]++
            score[1]++
            flag = false
        }
        return false;
    }

    private fun end() {
        println("Game over!")
    }
}