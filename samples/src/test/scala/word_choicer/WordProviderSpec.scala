package word_choicer

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import word_choicer.utils.WordLoader

class WordProviderSpec extends AnyFunSuite with Matchers {
  class MockWordLoader extends WordLoader {
    override def getWordsForHangman(baseDir: String): List[WordLoader.Word] = {
      List(
        WordLoader.Word("bird", "animals", "easy"),
        WordLoader.Word("cat", "animals", "easy")
      )
    }
  }

  test(
    "getRandomWord should return a random word from the specified difficulty and theme"
  ) {
    val wordProvider = new WordProvider {
      private val loadedWords = new MockWordLoader().getWordsForHangman("words")
    }

    val easyAnimalsWord = wordProvider.getRandomWord("easy", "animals")
    Set("cat", "bird").contains(easyAnimalsWord) shouldBe true

  }
}
