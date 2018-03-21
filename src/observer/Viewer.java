package observer;

import java.util.ArrayList;
import java.util.List;

public class Viewer {

	List<Component> componentList;

	public Viewer() {
		componentList = new ArrayList<>();
	}

	public void add(Component component) {
		this.componentList.add(component);
	}

	public void view() {
		for (Component c : componentList) {
			c.view();
		}
	}

}
