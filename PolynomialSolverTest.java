package eg.edu.alexu.csd.datastructure.linkedList;

import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomialSolverTest {

    @Test
    public void test1() {
        PolynomialSolver list = new PolynomialSolver();
        int [][] arr = {{5,2},{7,3},{-1,-1},{8,2},{1,0}};
        list.setPolynomial('a',arr);
            //program will sort it depend on the expo
            //and simplify terms with the same expo
            //polynomial of A : 5x^2 + 7x^3 -1x^-1 + 8x^2 + 1X^0
            //function will simplify this as : 7X^3 +13X^2 + 1 -1X^-1
            //terms(5X^2 will sum with 8X^2 = 13X^2) (9X^0 = 1)
        assertEquals(list.print('a'),"7x^3 +13x^2 +1 -1x^-1");

        int [][] arr1 = {{5,2},{7,2},{-1,2},{8,3},{9,3}};
        list.setPolynomial('b',arr1);
            //polynomial of B : 5x^2 + 7x^2 -1x^2 + 8x^3 + 9X^3
            //function will simplify this as : 17x^3 +11x^2
            //terms(5X^2  &  7X^2  &  -1x^2 = 17X^2) (8X^3  & 9x^3 = 11x^3)
        assertEquals(list.print('b'),"17x^3 +11x^2");


        //so  A :  7x^3 +13x^2 +1 -1x^-1     b : 17x^3 +11x^2
        list.add('a','b');
            //so R :  7x^3 +13x^2 +1 -1x^-1 + 17x^3 +11x^2
            //function will simplify this as : 24x^3 +24x^2 +1 -1x^-1
            //terms(7X^3  &  17X^3  = 24X^3) (13X^2 & 11x^2 = 24x^2)
        assertEquals(list.print('r'),"24x^3 +24x^2 +1 -1x^-1");

        list.subtract('a','b');
            //so R : ( 7x^3 +13x^2 +1 -1x^-1 )-( 17x^3 +11x^2)
            //function will simplify this as : 24x^3 +24x^2 +1 -1x^-1
        assertEquals(list.print('r'),"-10x^3 +2x^2 +1 -1x^-1");

        list.multiply('a','b');
            //so R : ( 7x^3 +13x^2 +1 -1x^-1 )*( 17x^3 +11x^2)
            //function will simplify this as : 119x^6 +298x^5 +143x^4 +153x^3 +82x^2 -11x
            //and common element with same expo were sum with each other
        assertEquals(list.print('r'),"119x^6 +298x^5 +143x^4 +153x^3 +82x^2 -11x");
    }

    @Test
    public void test2() {
        //simple test
        PolynomialSolver list = new PolynomialSolver();
        int [][] arr = {{5,1}};
        list.setPolynomial('a',arr);
        assertEquals(list.print('a'),"5x");

        int [][] arr1 = {{6,2}};
        list.setPolynomial('b',arr1);
        assertEquals(list.print('b'),"6x^2");


        //so  A :  5x     b : 6x^2
        list.add('a','b');
        //so R :  5x + 6x^2
        assertEquals(list.print('r'),"6x^2 +5x");

        list.subtract('a','b');
        //so R : (5x )-( 6x^2)
        assertEquals(list.print('r'),"-6x^2 +5x");

        list.multiply('a','b');
        //so R : (5x )*( 6x^2) = 30x^3
        assertEquals(list.print('r'),"30x^3");
    }

    @Test
    public void test3() {
        PolynomialSolver list = new PolynomialSolver();
        int [][] arr = {{5,1},{0,5},{3,2}};
        list.setPolynomial('a',arr);
        //term 0,5 ==> 0*x^5 so == 0
        //so A : 3x^2 +5x
        assertEquals(list.print('a'),"3x^2 +5x");

        int [][] arr1 = {{6,2},{7,0}};
        //term 7,0 ==> 7*x^0 so == 7
        //so B : 6x^2 +7
        list.setPolynomial('b',arr1);
        assertEquals(list.print('b'),"6x^2 +7");


        //so  A :  3x^2 +5x     b : 6x^2 +7
        list.add('a','b');
        //so R :  (3x^2 +5x)+(6x^2 +7)
        assertEquals(list.print('r'),"9x^2 +5x +7");

        list.subtract('a','b');
        //so R : (3x^2 +5x)-(6x^2 +7)
        assertEquals(list.print('r'),"-3x^2 +5x -7");

        list.multiply('a','b');
        //so R : (3x^2 +5x)*(6x^2 +7)
        assertEquals(list.print('r'),"18x^4 +30x^3 +21x^2 +35x");
    }

    @Test
    public void test4() {
        PolynomialSolver list = new PolynomialSolver();
        //we want to calcuate {(5x^2 + 5)*(6x^2)} + (5x)
        // so first we will calculate (5x^2 + 5)*(6x^2) ==> R
        //then R + C
        int [][] a = {{5,2},{5,0}};
        int [][] b = {{6,2}};
        int [][] c = {{5,1}};
        list.setPolynomial('a',a);
        list.setPolynomial('b',b);
        list.setPolynomial('c',c);
        list.multiply('a','b');
        //now R = A*B  = 30x^4 +30x^2
        assertEquals(list.print('r'),"30x^4 +30x^2");

        list.add('r','c');
        assertEquals(list.print('r'),"30x^4 +30x^2 +5x");
    }
}