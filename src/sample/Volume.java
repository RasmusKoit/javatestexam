package sample;

public class Volume extends Shape {
    double volume;

    Volume(String name, float radius, float height) {
        this.name = name;
        this.radius = radius;
        this.height = height;
    }

    Volume() {
        name = "undefined";
        radius = 0;
        height = 0;
    }
    public double CilinderVolume() {
        return Math.PI*Math.pow(this.radius,2.0)* this.height;
    }

    public double ConeVolume() {
        return 1/3*Math.PI*Math.pow(this.radius,2.0)* this.height;
    }

    public double getVolume() {
        if (this.name.equals("Silinder")) {
            return CilinderVolume();
        }else {
            return ConeVolume();
        }

    }
}
