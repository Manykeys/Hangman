package word_choicer

import word_choicer.utils.WordLoader

import scala.util.Random

class WordProvider {
  private val loadedWords = WordLoader().getWordsForHangman("words")

  def getRandomWord(difficulty: String, theme: String): String = {
    val wordsWithOneDifficulty = difficulty.toLowerCase match {
      case "medium" => loadedWords.filter(_.difficulty == "medium")
      case "hard"   => loadedWords.filter(_.difficulty == "hard")
      case _        => loadedWords.filter(_.difficulty == "easy")
    }

    val themeWord = wordsWithOneDifficulty.filter(_.theme == theme)

    if (themeWord.isEmpty) {
      ""
    } else {
      Random.shuffle(themeWord).head.text
    }
  }
}
