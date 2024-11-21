package visualizer

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class HangmanSpec extends AnyFunSuite with Matchers {

  test("render should display the correct stage based on attemptsLeft") {
    val hangman = new Hangman
    hangman.render(5, 6) shouldBe 1
    hangman.render(3, 6) shouldBe 3
    hangman.render(0, 6) shouldBe 6
  }
}
