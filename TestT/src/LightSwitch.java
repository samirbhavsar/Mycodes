import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
 
public class LightSwitch extends Application {
	int[] array = new int[25];
	Circle[] light = new Circle[25];
 
	@Override
	public void start(Stage stage) {
		init(stage);
		stage.show();
	}
 
	public void init(Stage stage) {
		Group root = new Group();
		Scene scene = new Scene(root, 700, 500, Color.WHITE);
		for (int i = 0; i < 25; i++) {
			circleChange(i, root);
		}
		root.getChildren().addAll(light);
		stage.setTitle("Lights Problem");
		stage.setScene(scene);
	}
 
	public void circleChange(final int i, final Group root) {
		light[i] = new Circle(i * 25 + 25, 100, 10);
		light[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if (array[i] == 1)
					array[i] = 0;
				else
					array[i] = 1;
 
				int counter = 0;
				int place = -1;
				boolean x = false;
				for (int a = 0; a < 25; a++) {
					if (array[a] == 1) {
						if (x == false) {
							place = a;
							x = true;
						}
						counter++;
					} else {
						if (counter >= 4 && x == true)
							a = 26;
						else {
							x = false;
							counter = 0;
							place = -1;
						}
					}
				}
 
				if (counter >= 4 && x == true)
					for (int b = 0; b < counter; b++)
						array[b + place] = 0;
 
				paint();
				me.consume();
			}
		});
	}
 
	public void paint() {
		for (int a = 0; a < 25; a++) {
			if (array[a] == 0)
				light[a].setFill(Color.BLACK);
			else
				light[a].setFill(Color.RED);
		}
	}
 
	public static void main(String args[]) {
		Application.launch(args);
	}
}