package game_engine

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class GameSpec extends AnyFunSuite with Matchers {

  test("isWordGuessed should return true if all letters are guessed") {
    val game = new Game("hello", 5)
    val guessedLetters = Set('h', 'e', 'l', 'o')
    game.isWordGuessed(guessedLetters) shouldBe true
  }

  test("isWordGuessed should return false if not all letters are guessed") {
    val game = new Game("hello", 5)
    val guessedLetters = Set('h', 'e', 'l')
    game.isWordGuessed(guessedLetters) shouldBe false
  }

  test("isGameLost should return true if attempts are 0") {
    val game = new Game("hello", 0)
    game.isGameLost(0) shouldBe true
  }

  test("isGameLost should return false if attempts are greater than 0") {
    val game = new Game("hello", 5)
    game.isGameLost(3) shouldBe false
  }

  test(
    "displayWord should display word with underscores for unguessed letters"
  ) {
    val game = new Game("hello", 5)
    val openedChars = Set('h', 'l')
    game.displayWord(openedChars) shouldBe "h _ l l _"
  }

  test("resultOfGuess should return true if letter is already opened") {
    val game = new Game("hello", 5)
    val openedChars = Set('h', 'l')
    val attemptsLeft = 5
    game.resultOfGuess('h', openedChars, attemptsLeft) shouldBe true
  }

  test("resultOfGuess should return true if letter is correct and not opened") {
    val game = new Game("hello", 5)
    val openedChars = Set('h', 'l')
    val attemptsLeft = 5
    game.resultOfGuess('e', openedChars, attemptsLeft) shouldBe true
  }

  test("resultOfGuess should return false if letter is incorrect") {
    val game = new Game("hello", 5)
    val openedChars = Set('h', 'l')
    val attemptsLeft = 5
    game.resultOfGuess('a', openedChars, attemptsLeft) shouldBe false
  }
}
