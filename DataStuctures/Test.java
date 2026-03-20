public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        BoxOwner b = new BoxOwner("Shamit");
        Box b1 = new Box(2, 2, 2, "red", b);
        Box b2 = new Box(b1);

        b2.height = 44;
        b2.boxOwner.name = "Amit";

        System.out.println(b1.height); // 2 (unchanged)
        System.out.println(b1.boxOwner.name);
    }
}

class Box implements Cloneable {
    int height, breadth, length;
    String colour;
    BoxOwner boxOwner;

    public Box(int height, int breadth, int length, String colour, BoxOwner boxOwner) {
        this.height = height;
        this.breadth = breadth;
        this.length = length;
        this.colour = colour;
        this.boxOwner = boxOwner;
    }


    public Box(Box other) {
        this.height = other.height;
        this.breadth = other.breadth;
        this.length = other.length;
        this.colour = other.colour;
        this.boxOwner = new BoxOwner(other.boxOwner);
    }
}

class BoxOwner{
    String name;

    public BoxOwner(String name){
        this.name = name;
    }

    public BoxOwner(BoxOwner other){
        this.name = other.name;
    }
}


