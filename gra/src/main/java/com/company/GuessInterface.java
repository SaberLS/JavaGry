package com.company;

public interface GuessInterface<T> extends GameInterface {
  public Boolean checkAnswer(T guessed);

  public void correctAnswer(T guessed);

  public void wrongAnswer(T guessed);

  public T getUserInput();

  public String gameStats();

  default void step() {
    System.out.println(this.gameStats());
    T input = this.getUserInput();
    this.guess(input);
  }

  default void guess(T guessed) {
    if (this.checkAnswer(guessed)) {
      this.correctAnswer(guessed);
    } else {
      this.wrongAnswer(guessed);
    }
  }
}
