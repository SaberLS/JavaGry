package com.company;

import java.util.Arrays;
import java.util.Scanner;

public abstract class GuessGame<T, S> extends GAME implements GuessInterface<S> {
  private int nb_chances;
  public int tries;
  private T answer;
  S[] correctQuesses;
  S[] wrongQuesses;

  public GuessGame(Scanner scanner, int nb_chances, T answer, String name, String description) {
    super(scanner, name, description);
    this.nb_chances = nb_chances;
    this.answer = answer;
  }

  public GuessGame(Scanner scanner, int nb_chances, T answer) {
    super(scanner, "Guess Game", "Guess Something");
    this.nb_chances = nb_chances;
    this.answer = answer;
  }

  public GuessGame(int nb_chances, T answer, String name, String description) {
    super(name, description);
    this.nb_chances = nb_chances;
    this.answer = answer;
  }

  private void setChances(int nb_chances) {
    this.nb_chances = nb_chances;
  }

  public int getChances() {
    return nb_chances;
  }

  protected void setAnswer(T answer) {
    this.answer = answer;
  }

  protected T getAnswer() {
    return answer;
  }

  public int triesLeft() {
    return nb_chances - tries;
  }

  public String gameStats() {
    return "\n"
        + "Poprawna odpowiedź:\t"
        + this.getAnswer()
        + "\nPozostałe Próby:\t"
        + this.triesLeft() + "/" + this.getChances()
        + "\nPoprawne:\t"
        + Arrays.toString(this.correctQuesses)
        + "\nNie poprawne:\t"
        + Arrays.toString(this.wrongQuesses);
  }

  public void correctAnswer(S guessed) {
    this.state = State.WIN;
    this.correctQuesses[0] = guessed;
  }

  public void wrongAnswer(S guessed) {
    this.tries++;

    int index = 0;
    for (S element : this.wrongQuesses) {
      if (element == null)
        break; // Znalazłem element
      index++;
    }
    this.wrongQuesses[index] = guessed;

    if (this.triesLeft() == 0) {
      this.lose();
    }
  }

  // public void play() {
  // while (this.isPlaying()) {
  // System.out.print("-----------------------------------------------------------------");
  // System.out.println(this.gameStats());

  // S input = this.getUserInput();
  // this.guess(input);
  // }

  // System.out.println("-----------------------------------------------------------------");
  // System.out.println("\t" + this.quitMessage());
  // System.out.println(this.gameStats());
  // }
}
