import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miki on 2018-10-20.
 */
public class CoordinatesOperations {

    public static List<Vector<Point3D>> vectorList3D = readCoordinates();
    public static final int MOVE = 25;
    public static final double ROTATE = 4 * Math.PI/180;
    public static final int ZOOM = 15;


    public static List<Vector<Point3D>> readCoordinates() {
        List<Vector<Point3D>> vectorList3D = new ArrayList<>();
        File file = new File("C:\\Users\\miki\\.IdeaIC15\\IntelliJ\\Camera\\src\\map.txt");

        if (!file.exists()) {
            System.out.println("File doesn't exist");
            return vectorList3D;
        }

        BufferedReader br;
        FileReader fr;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.length() == 0 || currentLine.startsWith("#"))
                    continue;

                String tab[] = currentLine.split(";");

                for (int i = 0; i < tab.length; i++)
                    tab[i] = tab[i].trim();

                Point3D a = new Point3D(Double.parseDouble(tab[0]), Double.parseDouble(tab[1]), Double.parseDouble(tab[2]));
                Point3D b = new Point3D(Double.parseDouble(tab[3]), Double.parseDouble(tab[4]), Double.parseDouble(tab[5]));

                vectorList3D.add(new Vector(a, b));
            }

            return vectorList3D;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>(0);
    }

    public static List<Vector<Point2D>> convertList(List<Vector<Point3D>> vectorList3D) {
        List<Vector<Point2D>> vectorList2D = new ArrayList<>();
        for (Vector<Point3D> vector3D : vectorList3D) {
            if(vector3D.getA().getZ() <= 0 || vector3D.getB().getZ() <= 0)
                continue;
            vectorList2D.add(new Vector<>(convertPoint(vector3D.getA()), convertPoint(vector3D.getB())));
        }
        return vectorList2D;
    }


    public static Point2D convertPoint(Point3D point3D) {
        Camera camera = Camera.getInstance();
        int x = (int) (point3D.getX() * (camera.getFocalDistance() / point3D.getZ()) + 1000 / 2);
        int y = (int) (-point3D.getY() * (camera.getFocalDistance() / point3D.getZ()) + 600 / 2);
        return new Point2D(x, y);
    }



}
