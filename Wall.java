import java.util.*;

/**
 * Created by miki on 2018-11-10.
 */
public class Wall implements Comparable<Wall>{
    private Point3D a;
    private Point3D b;
    private Point3D c;
    private Point3D d;
    private double centerOfGravity;
    private double zMax;
    private double zMin;

    public Wall(Point3D a, Point3D b, Point3D c, Point3D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.centerOfGravity = Math.sqrt(
                Math.pow((a.getX() + b.getX() + c.getX() + d.getX())/4, 2) +
                Math.pow((a.getY() + b.getY() + c.getY() + d.getY())/4, 2) +
                Math.pow((a.getZ() + b.getZ() + c.getZ() + d.getZ())/4, 2));
        this.zMax = Collections.max(new ArrayList<Double>() {{
            add(a.getZ());
            add(b.getZ());
            add(c.getZ());
            add(d.getZ());
        }});
        this.zMin = Collections.min(new ArrayList<Double>() {{
            add(a.getZ());
            add(b.getZ());
            add(c.getZ());
            add(d.getZ());
        }});
    }

    public Point3D getA() {
        return a;
    }

    public void setA(Point3D a) {
        this.a = a;
    }

    public Point3D getB() {
        return b;
    }

    public void setB(Point3D b) {
        this.b = b;
    }

    public Point3D getC() {
        return c;
    }

    public void setC(Point3D c) {
        this.c = c;
    }

    public Point3D getD() {
        return d;
    }

    public void setD(Point3D d) {
        this.d = d;
    }

    public double getCenterOfGravity() {
        return centerOfGravity;
    }

    public void setCenterOfGravity(double centerOfGravity) {
        this.centerOfGravity = centerOfGravity;
    }

    public double getzMax() {
        return zMax;
    }

    public void setzMax(double zMax) {
        this.zMax = zMax;
    }

    public double getzMin() {
        return zMin;
    }

    public void setzMin(double zMin) {
        this.zMin = zMin;
    }

    @Override
    public int compareTo(Wall o) {
        return Double.compare(this.centerOfGravity, o.centerOfGravity);
    }
}
