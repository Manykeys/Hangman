package game_engine

class Game(word: String, maxAttempts: Int) {
  private val wordChars: String = word.toLowerCase

  def isGameLost(attempts: Int): Boolean = attempts == 0

  def displayWord(openedChars: Set[Char]): String = {
    val result = wordChars
      .map { letter =>
        if (openedChars.contains(letter)) letter else '_'
      }
      .mkString(" ")
    println(result)
    result
  }

  def resultOfGuess(
      letter: Char,
      openedChars: Set[Char],
      attemptsLeft: Int
  ): Boolean = {
    (openedChars.contains(letter), wordChars.contains(letter)) match {
      case (true, _) =>
        println(s"Вы уже использовали букву '$letter'")
        true
      case (false, true) =>
        println(s"Верно, есть буква '$letter', откройте!")
        true
      case (false, false) =>
        println(
          s"Неверно, буквы '$letter' нет в слове. Осталось попыток: ${attemptsLeft - 1}"
        )
        false
    }
  }

  def endGame(guessedLetters: Set[Char], hiddenWord: String): Unit = {
    if (isWordGuessed(guessedLetters)) {
      println("Вы победили!!!")
    } else {
      println(s"Вы проиграли. Загаданное слово было: $hiddenWord")
    }
  }

  def isWordGuessed(guessedLetters: Set[Char]): Boolean =
    wordChars.forall(guessedLetters.contains)
}
