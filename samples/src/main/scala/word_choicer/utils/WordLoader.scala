package word_choicer.utils

import word_choicer.utils.WordLoader.Word
// мог решить через csv, захотел директории
import java.io.File
import scala.io.Source
import scala.jdk.CollectionConverters.*
import scala.util.Using

class WordLoader {
  private val difficulties = List("easy", "medium", "hard")

  def getWordsForHangman(baseDir: String): List[Word] = {
    (
      for {
      difficulty <- difficulties
      theme <- getThemesForDifficulty(baseDir, difficulty)
      resourcePath = s"/$baseDir/$difficulty/$theme.txt"
      } yield readWordsFromDirectories(resourcePath, theme, difficulty)
    )
    .flatten
  }

  def readWordsFromDirectories(
      filePath: String,
      theme: String,
      difficulty: String
  ): List[Word] = {
    val url = getClass.getResource(filePath)
    Using(Source.fromFile(url.toString.substring(5))) { source =>
      source
        .getLines()
        .map { line =>
          Word(line.trim, theme, difficulty)
        }
        .toList
    }.getOrElse(List.empty)
  }

  def getThemesForDifficulty(
      baseDir: String,
      difficulty: String
  ): List[String] = {
    val directory = new File(
      getClass.getResource(s"/$baseDir/$difficulty").getPath
    )
    if (directory.exists() && directory.isDirectory) {
      directory.listFiles
        .filter(file => file.isFile && file.getName.endsWith(".txt"))
        .map(file => file.getName.stripSuffix(".txt"))
        .toList
    } else {
      List.empty
    }
  }
}

object WordLoader {
  case class Word(text: String, theme: String, difficulty: String)
}
