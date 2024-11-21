package word_choicer

import console_reader.Reader
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class WordSelectorSpec extends AnyFunSuite with Matchers {

  class MockReader(difficultyInput: String, categoryInput: String)
      extends Reader {
    override def getDifficulty: String = difficultyInput
    override def getCategory(difficulty: String): String = categoryInput
  }

  class MockWordProvider(
      wordForDifficultyAndTheme: Map[(String, String), String]
  ) extends WordProvider {
    override def getRandomWord(difficulty: String, theme: String): String =
      wordForDifficultyAndTheme.getOrElse((difficulty, theme), "")
  }

  test(
    "selectWordFromDictionary should return a word from the specified difficulty and category"
  ) {
    val mockReader = new MockReader("easy", "animals")
    val mockWordProvider =
      new MockWordProvider(Map(("easy", "animals") -> "cat"))
    val selectedWord = WordSelector.selectWordFromDictionary(mockReader)
    List("cat", "bird").contains(selectedWord) shouldBe true
  }
}
