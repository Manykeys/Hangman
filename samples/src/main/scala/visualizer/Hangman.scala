package visualizer

class Hangman {
  private val gameStage: List[String] = List(
    """
      |—————|
      |/
      |
      |
      |
      |______
      |     |
    """,
    """
      |—————|
      |/    О
      |
      |
      |
      |______
      |     |
    """,
    """
      |—————|
      |/    О
      |     |
      |
      |
      |______
      |     |
    """,
    """
      |—————|
      |/    О
      |    /|
      |
      |
      |______
      |     |
    """,
    """
      |—————|
      |/    О
      |    /|\
      |
      |
      |______
      |     |
    """,
    """
      |—————|
      |/    О
      |    /|\
      |    /
      |
      |______
      |     |
    """,
    """
      |—————|
      |/    О
      |    /|\
      |    / \
      |
      |______
      |     |
    """
  )

  def render(attemptsLeft: Int, maxAttempts: Int): Int = {
    val stage = maxAttempts - attemptsLeft
    println(gameStage(stage))
    stage
  }
}
