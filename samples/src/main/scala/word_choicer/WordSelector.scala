package word_choicer

import console_reader.Reader

import scala.annotation.tailrec

object WordSelector {
  @tailrec
  final def selectWordFromDictionary(reader: Reader = Reader()): String = {
    val difficulty = reader.getDifficulty
    val category = reader.getCategory(difficulty)

    val hiddenWord = WordProvider().getRandomWord(difficulty, category)

    if (hiddenWord == "") {
      println("Не удалось получить слово. Попробуйте снова.")
      selectWordFromDictionary()
    } else {
      hiddenWord
    }
  }
}
