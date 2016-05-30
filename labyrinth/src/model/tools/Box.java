package model.tools;

import java.util.concurrent.CopyOnWriteArrayList;

import utils.Utils;

public class Box implements Tool {

	private CopyOnWriteArrayList<Tool> objects;

	public Box(CopyOnWriteArrayList<Tool> objs) {
		if (!objs.isEmpty()) {
			objects = objs;
		}
	}

	public Tool extractObject() {
		return objects.get(Utils.generateRandomNumber(objects.size()));
	}

	@Override
	public String getName() {
		return "?";
	}

	public void removeObject(Tool tool) {
		objects.remove(tool);
	}

}
