package com.company;

import java.util.Arrays;
import java.util.Scanner;

public abstract class GuessGame<T, S> extends GAME implements GuessInterface<S> {
  private int nb_chances;
  public int tries;
  private T answer;
  S[] correctQuesses;
  S[] wrongQuesses;

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

  protected void correctAnswer(S guessed) {
    this.state = State.WIN;
    this.correctQuesses[0] = guessed;
  }

  protected void wrongAnswer(S guessed) {
    this.tries++;

    int index = 0;
    for (S element : this.wrongQuesses) {
      if (element == null)
        break; // Znalazłem element
      index++;
    }
    this.wrongQuesses[index] = guessed;

    if (this.triesLeft() == 0) {
      this.state = State.LOSE;
    }
  }

  public Boolean checkAnswer(S guessed) {
    return (guessed == answer);
  };

  private void guess(S guessed) {
    if (this.checkAnswer(guessed)) {
      this.correctAnswer(guessed);
    } else {
      this.wrongAnswer(guessed);
    }
  }

  public void play() {
    System.out.println(this.start());

    Scanner scr = new Scanner(System.in);
    while (this.state == State.PLAYING) {
      System.out.print("-----------------------------------------------------------------");
      System.out.println(this.gameStats());

      S input = this.getUserInput(scr);
      this.guess(input);
    }

    System.out.println("-----------------------------------------------------------------");
    System.out.println("\t" + this.quitMessage());
    System.out.println(this.gameStats());
  }
}
