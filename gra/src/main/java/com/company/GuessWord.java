package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class GuessWord extends GuessGame<String, Character> {
  public GuessWord(Scanner scanner, int nb_chances, String answer, String name, String description) {
    super(scanner, nb_chances, answer.replace(' ', '-'), name, description);
  }

  public GuessWord(Scanner scanner, int nb_chances, String answer) {
    super(scanner, nb_chances, answer.replace(' ', '-'), "Word Game", "Guess Word");

    int answerLength = answer.length();

    this.correctQuesses = new Character[answerLength];
    Arrays.fill(this.correctQuesses, null);

    this.wrongQuesses = new Character[nb_chances];
    Arrays.fill(this.wrongQuesses, null);
  }

  public void correctAnswer(Character guessed) {
    String answer = this.getAnswer();
    int index = answer.indexOf(guessed);

    while (index != -1) {
      this.correctQuesses[index] = guessed;
      index = answer.indexOf(guessed, index + 1);
    }

    StringBuilder sb = new StringBuilder();
    for (Character c : this.correctQuesses) {
      sb.append(c);
    }
    String corectAnsStr = sb.toString();

    if (answer.equals(corectAnsStr)) {
      this.state = State.WIN;
    }
  }

  @Override
  public Boolean checkAnswer(Character guessed) {
    int index = this.getAnswer().indexOf(guessed);

    Boolean repeated = false;

    for (Character character : this.correctQuesses) {
      if (character == guessed) {
        repeated = true;
        break;
      }
    }

    return (index != -1 && !repeated);
  }

  public Character getUserInput() {
    Character input = this.scanner.next().charAt(0);
    return input;
  }
}
