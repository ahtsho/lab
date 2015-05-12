package core;

public class Game {

	boolean started;
	boolean paused;

	public Game(boolean start, boolean pause) {
		started = start;
		paused = pause;
	}

	public void start() {
		started = true;
		paused = false;
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

}
