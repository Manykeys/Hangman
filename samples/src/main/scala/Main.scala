import console_reader.Reader
import game_engine.Game
import visualizer.Hangman
import word_choicer.WordSelector

import scala.annotation.tailrec

object Main extends App {
  private val hiddenWord = WordSelector.selectWordFromDictionary()
  private val maxAttempts = 7

  private def startGame(
      hiddenWord: String,
      guessedLetters: Set[Char],
      attempts: Int
  ): Unit = {
    val game = new Game(hiddenWord, maxAttempts)
    @tailrec
    def gameLoop(
        currentAttempts: Int,
        currentGuessedLetters: Set[Char]
    ): Unit = {
      if (
        !game.isGameLost(currentAttempts) && !game.isWordGuessed(
          currentGuessedLetters
        )
      ) {
        val char = Reader().readCharInput()
        val updatedGuessedLetters = currentGuessedLetters + char
        val winAttempt =
          game.resultOfGuess(char, currentGuessedLetters, currentAttempts)

        Hangman().render(currentAttempts, maxAttempts)
        game.displayWord(updatedGuessedLetters)

        val nextAttempts =
          if (winAttempt) currentAttempts else currentAttempts - 1
        gameLoop(nextAttempts, updatedGuessedLetters)
      } else {
        game.endGame(currentGuessedLetters, hiddenWord)
      }
    }

    gameLoop(attempts, guessedLetters)
  }

  startGame(hiddenWord, Set(), maxAttempts)
}
