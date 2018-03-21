package observer;

public class Main {
	public static void main(String[] args) {
		ConcreteGameObject gameObject = new ConcreteGameObject();

		Component component = new ConcreateComponent() {

			@Override
			public void view() {
				System.out.println(gameObject.getNumber());
			}

		};

		Viewer viewer = new Viewer();

		viewer.add(component);

		viewer.view();

	}
}
