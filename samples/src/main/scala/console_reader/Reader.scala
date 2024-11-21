package console_reader

import word_choicer.utils.WordLoader

import scala.annotation.tailrec

class Reader {
  def getDifficulty: String = {
    println(
      "Выберите уровень сложности: easy, medium, hard. По умолчанию: easy"
    )
    readLine() match {
      case "medium" => "medium"
      case "hard"   => "hard"
      case _        => "easy"
    }
  }

  def getCategory(difficulty: String): String = {
    println(s"Выберите категорию слова: ${WordLoader()
        .getThemesForDifficulty("words", difficulty)
        .mkString(", ")}")
    val category = readLine()
    category
  }

  def readLine(): String = scala.io.StdIn.readLine().trim.toLowerCase

  @tailrec
  final def readCharInput(attempt: Option[Char] = None): Char = {
    println("Введите одну букву:")
    readLine().headOption  match {
      case Some(char) if char.isLetter => char
      case Some(_) =>
        println("Ошибка: введен символ, но это не буква. Попробуйте снова")
        readCharInput()
      case None =>
        println("Ошибка: введен пустой ввод. Попробуйте снова")
        readCharInput()
    }
  }
}
