package com.company;

public abstract class GuessGame<T> extends GAME {
  private int nb_chances;
  private int tries;
  private T answer;

  public GuessGame(int nb_chances, T answer, String name, String description) {
    super(name, description);
    this.nb_chances = nb_chances;
    this.answer = answer;
  }

  public GuessGame(int nb_chances, T answer) {
    super("Guess Game", "Guess Something");
    this.nb_chances = nb_chances;
    this.answer = answer;
  }

  public void setChances(int nb_chances) {
    this.nb_chances = nb_chances;
  }

  public int getChances() {
    return nb_chances;
  }

  public void setAnswer(T answer) {
    this.answer = answer;

    this.description = "Guess " + answer.getClass().getName();
  }

  public T getAnswer() {
    return answer;
  }

  public void guess(T guessed) {
    this.tries++;

    if (guessed.equals(this.answer)) {
      this.state = State.WIN;
    } else if (this.triesLeft() <= 0) {
      this.state = State.LOSE;
    }
  }

  public int triesLeft() {
    return nb_chances - tries;
  }
}
