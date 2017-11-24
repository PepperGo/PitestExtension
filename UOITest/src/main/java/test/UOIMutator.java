/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package test;

public class UOIMutator {

    public int myMethod(int a, int b) {
        int c = 1;
         
        int d = 2;
        int e = a + c + b;
        
        int f = d; 
        d++;
        c--;
        
        double g = 12.2;
        double h = g;
        
        return f;
    }
}
