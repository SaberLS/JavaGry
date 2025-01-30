package com.company;

public interface GameInterface {
  enum State {
    INIT,
    PLAYING,
    LOSE,
    WIN,
  };

  public void win();

  public void lose();

  public Boolean isPlaying();

  public void startGame();

  public void endGame();

  public void step();

  public String nextStep(String param);

  default void play() {
    this.startGame();

    while (this.isPlaying()) {
      System.out.print("-----------------------------------------------------------------");
      this.step();
    }
    System.out.print("-----------------------------------------------------------------");

    this.endGame();
  };
}
