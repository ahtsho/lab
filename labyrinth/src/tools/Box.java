package tools;

import java.util.ArrayList;

import utils.Utils;

public class Box implements Tool {

	private ArrayList<Tool> objects;

	public Box(ArrayList<Tool> objs) {
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
