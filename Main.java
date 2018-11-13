import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by miki on 2018-10-16.
 */
public class Main extends Application {

    Camera camera = null;
    GraphicsContext gc = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1000, 600);
        Group root = new Group();
        gc = canvas.getGraphicsContext2D();
        CoordinatesOperations.readCoordinates();
        camera = Camera.getInstance();
        drawImage();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    camera.moveForward();
                    drawImage();
                    break;
                case S:
                    camera.moveBackwards();
                    drawImage();
                    break;
                case A:
                    camera.moveLeft();
                    drawImage();
                    break;
                case D:
                    camera.moveRight();
                    drawImage();
                    break;
                case UP:
                    camera.rotateUp();
                    drawImage();
                    break;
                case DOWN:
                    camera.rotateDown();
                    drawImage();
                    break;
                case LEFT:
                    camera.rotateLeft();
                    drawImage();
                    break;
                case RIGHT:
                    camera.rotateRight();
                    drawImage();
                    break;
                case EQUALS:
                    camera.zoomIn();
                    drawImage();
                    break;
                case MINUS:
                    camera.zoomOut();
                    drawImage();
                    break;
                case R:
                    camera.loadInitial();
                    drawImage();
                    break;
                default:
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

/*    public void drawImage() {
        gc.clearRect(0,0,1000,600);
        List<Vector<Point2D>> vectorList = CoordinatesOperations.convertList(camera.getVectorList3D());
        gc.setStroke(Color.RED);
        for (Vector<Point2D> vector : vectorList) {
            gc.strokeLine(vector.getA().getX(), vector.getA().getY(), vector.getB().getX(), vector.getB().getY());
        }
    }*/

    public void drawImage() {
        Point2D a , b, c, d;
        double[] x;
        double[] y;

        gc.clearRect(0,0,1000,600);

        for (Wall wall : camera.getWallList()){

            if (wall.getA().getZ() <= 0 || wall.getB().getZ() <= 0 || wall.getC().getZ() <= 0 || wall.getD().getZ() <= 0)
                continue;

            a = CoordinatesOperations.convertPoint(wall.getA());
            b = CoordinatesOperations.convertPoint(wall.getB());
            c = CoordinatesOperations.convertPoint(wall.getC());
            d = CoordinatesOperations.convertPoint(wall.getD());

            x = new double[] {a.getX(), b.getX(), c.getX(), d.getX()};
            y = new double[] {a.getY(),b.getY(), c.getY(), d.getY()};
            gc.setFill(Color.LIGHTGRAY);
            gc.fillPolygon(x, y , 4);

            gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
            gc.strokeLine(b.getX(), b.getY(), c.getX(), c.getY());
            gc.strokeLine(c.getX(), c.getY(), d.getX(), d.getY());
            gc.strokeLine(d.getX(), d.getY(), a.getX(), a.getY());
        }

    }
}
