package model.game;

public class Game {

	static boolean started;
	static boolean paused;
	static boolean levelEnded;

	public Game(boolean start, boolean pause, boolean level) {
		started = start;
		paused = pause;
		levelEnded = level;
	}

	public void start() {
		started = true;
		paused = false;
		levelEnded = false;
	}

	public void pause() {
		started = true;
		paused = true;
	}

	public void endGame() {
		started = false;
		paused = false;	
	}

	public boolean isOn() {
		if (started && !paused)
			return true;
		return false;
	}

	public static void levelStarted(){
		levelEnded = false;
	}
	public static void nextLevel(){
		levelEnded = true;
	}
}
