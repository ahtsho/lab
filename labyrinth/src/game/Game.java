package game;

public class Game {

	boolean started;
	boolean paused;
	boolean levelEnded;

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

	public void end() {
		started = false;
		paused = false;
	}

	public boolean isOn() {
		if (started && !paused)
			return true;
		return false;
	}

	public void nextLevel(){
		levelEnded = true;
	}
}
