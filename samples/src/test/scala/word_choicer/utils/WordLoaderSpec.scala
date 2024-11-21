package word_choicer.utils

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import word_choicer.utils.WordLoader.Word

class WordLoaderSpec extends AnyFunSuite with Matchers {

  test(
    "getWordsForHangman should return a list of words for all difficulties and themes"
  ) {
    val wordLoader = new WordLoader
    val words = wordLoader.getWordsForHangman("words")
    words.nonEmpty shouldBe true
    words.foreach { word =>
      word.text should not be empty
      word.theme should not be empty
      word.difficulty should not be empty
    }
  }

  test(
    "getThemesForDifficulty should return a list of themes for a given difficulty"
  ) {
    val wordLoader = new WordLoader
    val themes = wordLoader.getThemesForDifficulty("words", "easy")
    themes should contain allOf ("animals", "countries")
  }

  test("readWordsFromDirectories should read words from a file") {
    val wordLoader = new WordLoader
    val words = wordLoader.readWordsFromDirectories(
      "/words/easy/animals.txt",
      "animals",
      "easy"
    )
    words should contain allOf (
      Word("cat", "animals", "easy"),
      Word("bird", "animals", "easy")
    )
  }

}
