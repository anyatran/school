import tester.Tester;
import javalib.colors.Blue;
import javalib.colors.Green;
import javalib.colors.IColor;
import javalib.colors.Red;

interface IMobile {
    int totalWeight();

    int totalHeight();

    boolean isBalanced();
}

class Simple implements IMobile {
    int length;
    int weight;
    IColor color;

    Simple(int length, int weight, IColor color) {
        this.length = length;
        this.weight = weight;
        this.color = color;
    }

    public int totalWeight() {
        return this.weight;
    }

    public int totalHeight() {
        return this.length + this.weight;
    }

    public boolean isBalanced() {
        return true;
    }
}

class Complex implements IMobile {
    int length;
    int leftside;
    int rightside;
    IMobile left;
    IMobile right;

    Complex(int length, int leftside, int rightside, IMobile left, IMobile right) {
        this.length = length;
        this.leftside = leftside;
        this.rightside = rightside;
        this.left = left;
        this.right = right;
    }

    public int totalWeight() {
        return this.left.totalWeight() + this.right.totalWeight();
    }

    public int totalHeight() {
        return Math.max(this.left.totalHeight(), this.right.totalHeight())
                + this.length;
    }

    public boolean isBalanced() {
        return this.left.isBalanced()
                && this.right.isBalanced()
                && ((this.left.totalWeight() * this.rightside) == (this.right
                        .totalWeight() * this.leftside));
    }
}

class ExampleMobiles {
    IMobile simple1 = new Simple(1, 10, new Red());
    IMobile simple2 = new Simple(2, 10, new Blue());

    IMobile complex1 = new Complex(3, 12, 5, new Complex(1, 6, 6, simple1,
            simple2), new Simple(3, 40, new Green()));

    boolean testTotalWeight(Tester t) {
        return t.checkExpect(this.simple1.totalWeight(), 10)
                && t.checkExpect(this.simple2.totalWeight(), 10)
                && t.checkExpect(this.complex1.totalWeight(), 60);
    }

    boolean testTotalHeight(Tester t) {
        return t.checkExpect(this.simple1.totalHeight(), 11)
                && t.checkExpect(this.simple2.totalHeight(), 12)
                && t.checkExpect(this.complex1.totalHeight(), 46);
    }
    
    boolean testIsBalanced(Tester t) {
        return t.checkExpect(this.simple1.isBalanced(), true) &&
                t.checkExpect(this.complex1.isBalanced(), false);
    }
}
