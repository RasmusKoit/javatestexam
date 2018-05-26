package sample;

public class Tuba {
    String name;
    float pikkus;
    float laius;
    float uks;

    Tuba(String name, float pikkus, float laius, float uks) {
        this.name = name;
        this.pikkus = pikkus;
        this.laius = laius;
        this.uks = uks;
    }

    Tuba() {
        this.name = "undefined";
        this.pikkus = 0;
        this.laius = 0;
        this.uks = 0;
    }


    void give_info() {
        System.out.println("Room is: " + this.name +
                ". With height of: " + this.pikkus +
                " and width of: " + this.laius +
                " door width is " + this.uks);
    }
}
