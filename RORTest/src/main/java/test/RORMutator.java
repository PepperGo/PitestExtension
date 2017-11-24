package test;

public class RORMutator {

    public int myMethod(int a, int b) {
        if (a > 0)
            return a;
        else if (a < 0 && b > 0)
            return b;
        else
            return a + b;
    }

}
