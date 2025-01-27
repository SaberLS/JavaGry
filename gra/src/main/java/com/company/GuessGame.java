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

  public void setChances(int nb_chances) {
    this.nb_chances = nb_chances;
  }

  public int getChances() {
    return nb_chances;
  }

  public void setAnswer(T answer) {
    this.answer = answer;
  }

  public T getAnswer() {
    return answer;
  }

  public int triesLeft() {
    return nb_chances - tries;
  }

  public void attempt(Scanner scr) {
    System.out.println(this.attemptMessage());

    S input = this.getUserInput(scr);

    this.guess(input);
  }

  public String gameStats() {
    return this.quit() + "\n"
        + "Poprawna odpowiedź:\t"
        + this.getAnswer()
        + "\nPozostałe Próby:\t"
        + this.triesLeft() + "/" + this.getChances()
        + "\nPoprawne:\t"
        + Arrays.toString(this.correctQuesses)
        + "\nNie poprawne:\t"
        + Arrays.toString(this.wrongQuesses);
  }

  public void play() {
    this.start();

    Scanner scr = new Scanner(System.in);
    while (this.state == State.PLAYING) {
      this.attempt(scr);
    }
    scr.close();

    System.out.println(this.gameStats());
  }
}
