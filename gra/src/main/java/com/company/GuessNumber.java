package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class GuessNumber extends GuessGame<Integer, Integer> {
  int min;
  int max;

  public GuessNumber(Scanner scanner, int nb_chances, int answer, String name, String description) {
    super(scanner, nb_chances, answer, name, description);

    this.max = 100;
    this.min = 0;
  }

  public GuessNumber(Scanner scanner, int nb_chances, int answer, int max, int min) {
    super(scanner, nb_chances, answer, "Number Game", "Guess number");

    this.max = max;
    this.min = min;
    this.wrongQuesses = new Integer[nb_chances];
    this.correctQuesses = new Integer[1];

    Arrays.fill(this.correctQuesses, null);
    Arrays.fill(this.wrongQuesses, null);
  }

  public GuessNumber(int nb_chances, int answer) {
    super(nb_chances, answer, "Number Game", "Guess number");

    this.wrongQuesses = new Integer[nb_chances];
    this.correctQuesses = new Integer[1];

    Arrays.fill(this.correctQuesses, null);
    Arrays.fill(this.wrongQuesses, null);

    this.max = 100;
    this.min = 0;
  }

  public void init() {
    this.setAnswer((int) (Math.random() * (this.max - this.min + 1)) + this.min);
    this.state = State.INIT;
    this.tries = 0;
    Arrays.fill(this.correctQuesses, null);
    Arrays.fill(this.wrongQuesses, null);
  }

  public String nextStep(String param) {
    String s = "<div>";
    s += "<p>" + "Nazwa: " + this.name + "</p>" + "<br>";
    s += "<p>" + "Szukana wartosc: " + this.getAnswer() + "</p>" + "<br></div>";
    s += "<p>" + "Ograniczenie dolne: " + this.min + "</p>" + "<br>";
    s += "<p>" + "Ograniczenie gorne: " + this.max + "</p>" + "<br>";
    // TODO nextStep
    if (!this.isPlaying()) {
      this.init();
    } else if (param != "") {
      this.guess(Integer.parseInt(param));
    }
    s += "<p>" + "Liczba iteracji: " + this.triesLeft() + "/" + this.getChances() + "</p>" + "<br>";
    s += "<p>" + "Poprzednie proby: " + Arrays.toString(this.wrongQuesses) +
        "</p>" + "<br>";
    s += "<div class=\"w3-panel w3-blue\">";
    s += "<p>" + this.stateMessage() + "</p>";
    if (this.state == State.INIT) {
      this.state = State.PLAYING;
    }
    s += "</div>";
    return s;
  }

  public Integer getUserInput() {
    Integer input = this.scanner.nextInt();
    return input;
  }

  public Boolean checkAnswer(Integer guessed) {
    return (this.getAnswer().equals(guessed));
  }
}
