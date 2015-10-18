package moveobject;

import javafx.scene.shape.Shape;

public abstract class MoveObject {
	protected Shape shape;

	public Shape getShape(){
		return shape;
	}

	public boolean isHitting(MoveObject mo){
		return this.getShape().intersects(mo.getShape().getBoundsInLocal());
	}
}
