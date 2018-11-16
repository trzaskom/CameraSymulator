import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by miki on 2018-10-20.
 */
public class Camera {

    private static Camera instance = null;
    private int focalDistance;
    private List<Vector<Point3D>> vectorList3D;
    private List<Wall> wallList = new ArrayList<>();
    private boolean centreOfGravityMethod = true;

    private Camera() {
        focalDistance = 450;
        vectorList3D = CoordinatesOperations.vectorList3D;
        setWallList();
    }

    public static final Camera getInstance() {
        if (instance == null)
            instance = new Camera();

        return instance;
    }

    public void moveForward() {
        for (Vector<Point3D> vector : vectorList3D) {
            double zA = vector.getA().getZ() - CoordinatesOperations.MOVE;
            double zB = vector.getB().getZ() - CoordinatesOperations.MOVE;
            vector.getA().setZ(zA);
            vector.getB().setZ(zB);
        }
        setWallList();
    }

    public void moveBackwards() {
        for (Vector<Point3D> vector : vectorList3D) {
            double zA = vector.getA().getZ() + CoordinatesOperations.MOVE;
            double zB = vector.getB().getZ() + CoordinatesOperations.MOVE;
            vector.getA().setZ(zA);
            vector.getB().setZ(zB);
        }
        setWallList();
    }

    public void moveLeft() {
        for (Vector<Point3D> vector : vectorList3D) {
            double xA = vector.getA().getX() + CoordinatesOperations.MOVE;
            double xB = vector.getB().getX() + CoordinatesOperations.MOVE;
            vector.getA().setX(xA);
            vector.getB().setX(xB);
        }
        setWallList();
    }

    public void moveRight() {
        for (Vector<Point3D> vector : vectorList3D) {
            double xA = vector.getA().getX() - CoordinatesOperations.MOVE;
            double xB = vector.getB().getX() - CoordinatesOperations.MOVE;
            vector.getA().setX(xA);
            vector.getB().setX(xB);
        }
        setWallList();
    }

    public void rotateUp() {
        for (Vector<Point3D> vector : vectorList3D) {
            double yA = (vector.getA().getZ() * Math.sin((-1) * CoordinatesOperations.ROTATE)) + (vector.getA().getY() * Math.cos((-1) * CoordinatesOperations.ROTATE));
            double zA = (vector.getA().getZ() * Math.cos((-1) * CoordinatesOperations.ROTATE)) - (vector.getA().getY() * Math.sin((-1) * CoordinatesOperations.ROTATE));
            double yB = (vector.getB().getZ() * Math.sin((-1) * CoordinatesOperations.ROTATE)) + (vector.getB().getY() * Math.cos((-1) * CoordinatesOperations.ROTATE));
            double zB = (vector.getB().getZ() * Math.cos((-1) * CoordinatesOperations.ROTATE)) - (vector.getB().getY() * Math.sin((-1) * CoordinatesOperations.ROTATE));

            vector.getA().setY(yA);
            vector.getA().setZ(zA);
            vector.getB().setY(yB);
            vector.getB().setZ(zB);
        }
        setWallList();
    }

    public void rotateDown() {
        for (Vector<Point3D> vector : vectorList3D) {
            double yA = (vector.getA().getZ() * Math.sin((1) * CoordinatesOperations.ROTATE)) + (vector.getA().getY() * Math.cos((1) * CoordinatesOperations.ROTATE));
            double zA = (vector.getA().getZ() * Math.cos((1) * CoordinatesOperations.ROTATE)) - (vector.getA().getY() * Math.sin((1) * CoordinatesOperations.ROTATE));
            double yB = (vector.getB().getZ() * Math.sin((1) * CoordinatesOperations.ROTATE)) + (vector.getB().getY() * Math.cos((1) * CoordinatesOperations.ROTATE));
            double zB = (vector.getB().getZ() * Math.cos((1) * CoordinatesOperations.ROTATE)) - (vector.getB().getY() * Math.sin((1) * CoordinatesOperations.ROTATE));

            vector.getA().setY(yA);
            vector.getA().setZ(zA);
            vector.getB().setY(yB);
            vector.getB().setZ(zB);
        }
        setWallList();
    }

    public void rotateLeft() {
        for (Vector<Point3D> vector : vectorList3D) {
            double xA = (vector.getA().getX() * Math.cos((-1) * CoordinatesOperations.ROTATE)) - (vector.getA().getZ() * Math.sin((-1) * CoordinatesOperations.ROTATE));
            double zA = (vector.getA().getX() * Math.sin((-1) * CoordinatesOperations.ROTATE)) + (vector.getA().getZ() * Math.cos((-1) * CoordinatesOperations.ROTATE));
            double xB = (vector.getB().getX() * Math.cos((-1) * CoordinatesOperations.ROTATE)) - (vector.getB().getZ() * Math.sin((-1) * CoordinatesOperations.ROTATE));
            double zB = (vector.getB().getX() * Math.sin((-1) * CoordinatesOperations.ROTATE)) + (vector.getB().getZ() * Math.cos((-1) * CoordinatesOperations.ROTATE));

            vector.getA().setX(xA);
            vector.getA().setZ(zA);
            vector.getB().setX(xB);
            vector.getB().setZ(zB);
        }
        setWallList();
    }

    public void rotateRight() {
        for (Vector<Point3D> vector : vectorList3D) {
            double xA = (vector.getA().getX() * Math.cos((1) * CoordinatesOperations.ROTATE)) - (vector.getA().getZ() * Math.sin((1) * CoordinatesOperations.ROTATE));
            double zA = (vector.getA().getX() * Math.sin((1) * CoordinatesOperations.ROTATE)) + (vector.getA().getZ() * Math.cos((1) * CoordinatesOperations.ROTATE));
            double xB = (vector.getB().getX() * Math.cos((1) * CoordinatesOperations.ROTATE)) - (vector.getB().getZ() * Math.sin((1) * CoordinatesOperations.ROTATE));
            double zB = (vector.getB().getX() * Math.sin((1) * CoordinatesOperations.ROTATE)) + (vector.getB().getZ() * Math.cos((1) * CoordinatesOperations.ROTATE));

            vector.getA().setX(xA);
            vector.getA().setZ(zA);
            vector.getB().setX(xB);
            vector.getB().setZ(zB);
        }
        setWallList();
    }

    public void zoomIn() {
        focalDistance += CoordinatesOperations.ZOOM;
    }

    public void zoomOut() {
        focalDistance -= CoordinatesOperations.ZOOM;
    }

    public void loadInitial() {
        setVectorList3D(CoordinatesOperations.readCoordinates());
        setFocalDistance(450);
        setWallList();
    }

    public List<Vector<Point3D>> getVectorList3D() {
        return vectorList3D;
    }

    public void setVectorList3D(List<Vector<Point3D>> vectorList3D) {
        this.vectorList3D = vectorList3D;
    }

    public int getFocalDistance() {
        return focalDistance;
    }

    public void setFocalDistance(int focalDistance) {
        this.focalDistance = focalDistance;
    }

    public void setWallList() {
        wallList.clear();
        for (int i = 0; i < vectorList3D.size(); i += 4) {
            wallList.add(new Wall(vectorList3D.get(i).getA(), vectorList3D.get(i).getB(), vectorList3D.get(i + 2).getA(), vectorList3D.get(i + 2).getB()));
        }
    }

    public List<Wall> getWallList() {
        List<Wall> sortedWallList = sort();
        return sortedWallList;
    }

    public List<Wall> sort(){
        if (centreOfGravityMethod) {
            Collections.sort(wallList, Collections.reverseOrder());
            return wallList;
        }else {
            List<Wall> sortedWallList = new ArrayList<>();
            sortedWallList.add(wallList.get(0));
            for (Wall wallToAdd : wallList.subList(1,wallList.size())){
                for (int i = 0; i < sortedWallList.size(); i++){
                    if (sortedWallList.get(i).getzMax() < wallToAdd.getzMin()) {
                        sortedWallList.add(i, wallToAdd);
                        break;
                    }
                    else{
                        if (i == sortedWallList.size()-1) {
                            sortedWallList.add(wallToAdd);
                            break;
                        }
                        else
                            continue;
                    }
                }
            }
            return sortedWallList;
        }
    }

    public void setCentreOfGravityMethod(boolean centreOfGravityMethod) {
        this.centreOfGravityMethod = centreOfGravityMethod;
    }
}
