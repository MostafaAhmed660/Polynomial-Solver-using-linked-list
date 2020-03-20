package eg.edu.alexu.csd.datastructure.linkedList;

import org.jetbrains.annotations.NotNull;
import java.awt.Point;

public class PolynomialSolver implements IPolynomialSolver {

    public Slinkedlist A = new Slinkedlist();
    public Slinkedlist B = new Slinkedlist();
    public Slinkedlist C = new Slinkedlist();
    public Slinkedlist R = new Slinkedlist();

    public Slinkedlist Arraytolinkedlist(@NotNull int [][] terms) {
        Slinkedlist list = new Slinkedlist();
        for (int i = 0 ; i < terms.length ; i++)
        {
            java.awt.Point p = new java.awt.Point (terms[i][0],terms[i][1]);
            list.add(p);
        }
        return list;
    }

    public int[][] Linkedlisttoarray (@NotNull Slinkedlist list){
        int [][] terms = new int[list.size][2] ;

        for (int i = 0 ; i < list.size ; i++){
            java.awt.Point p = (Point) list.get(i);   //(point) to get coordinates as (x , y)
            terms[i][0] = p.x ;
            terms[i][1] = p.y ;
        }
        return terms ;
    }

    public int[][] bubblesort(int[][] term){
        boolean issorted = false;
        int temp;
        while(!issorted) {
            issorted = true;
            for(int i =0; i<term.length-1 ; i++) {
                if(term[i][1]<term[i+1][1]) {
                    issorted = false;
                    ////swaping(term[i][1],term[i+1][1])
                    temp = term[i][1];
                    term[i][1]=term[i+1][1];
                    term[i+1][1]=temp;
                    ////swaping(term[i][0],term[i+1][0])
                    temp = term[i][0];
                    term[i][0]=term[i+1][0];
                    term[i+1][0]=temp;
                }
            }
        }
        return term;
    }

    public void setPolynomial(char poly, int[][] terms){
        terms = bubblesort(terms) ;

        for (int i = 0 ; i < terms.length-1 ; i++){
            for (int j = i+1 ; j < terms.length ; j++){
                if (terms[i][1] == terms[j][1]) {
                    terms[i][0] += terms[j][0];
                    terms[j][1] = 0 ;
                    terms[j][0] = 0 ;
                }
            }
        }
        /////this part because if we have for ex: 2x^2 + 4x^2 + 4X^2 + 5
        /////                 wil save as :   10x^2 + 5

        if (poly == 'a' || poly == 'A')
            A = Arraytolinkedlist(terms) ;
        else if (poly == 'b' || poly == 'B')
            B = Arraytolinkedlist(terms) ;
        else if (poly == 'c' || poly == 'C')
            C = Arraytolinkedlist(terms);
        else
            System.out.printf("ERROR NOT VALID VARIABLE");
    }

    public String print(char poly){
        String  polynomial = "";
        Slinkedlist polynomiallist = null;

        if (poly == 'a' || poly == 'A')
            polynomiallist = A ;
        else if (poly == 'b' || poly == 'B')
            polynomiallist = B ;
        else if (poly == 'c' || poly == 'C')
            polynomiallist = C ;
        else if (poly == 'r' || poly == 'R')
            polynomiallist = R ;

        int terms[][] = bubblesort(Linkedlisttoarray(polynomiallist)) ;
        int i ;

        for ( i = 0 ; i < terms.length ; i++){
            if (terms[i][0] != 0) {
                if (terms[i][1] == 0){
                    polynomial+=terms[i][0];
                    break;
                }
                ///printing value
                if (terms[i][0] == 1)
                    polynomial+="x";
                else
                    polynomial+=terms[i][0]+"x";
                ///printig expo
                if (terms[i][1] != 1)
                    polynomial+="^"+terms[i][1];
                break;
            }
        }
        ///first for loop to get the first element
        ///second to complete a sequance
        for (int j = i+1 ; j < terms.length ; j++){
            if (terms[j][0] != 0) {
                if (terms[j][1] == 0){
                    polynomial+=" +"+terms[j][0];
                    continue;
                }
                ///printing value
                if (terms[j][0] == 1)
                    polynomial+=" +x";
                else {
                    if(terms[j][0] > 0)
                        polynomial += " +"+terms[j][0]+"x";
                    else
                        polynomial += " -"+(-1*terms[j][0])+"x";
                }
                ///printig expo
                if (terms[j][1] != 1)
                    polynomial+="^"+terms[j][1];
            }
        }
        if (polynomial == "") polynomial+="0" ;
        return polynomial ;
    }

    public float evaluatePolynomial(char poly, float value) {
        float result =0;
        Slinkedlist resultlist = new Slinkedlist();
        if (poly == 'a'||poly =='A')
            resultlist = A;
        else if (poly == 'b'||poly =='B')
            resultlist = B;
        else if (poly == 'c'||poly =='C')
            resultlist = C;
        else if (poly == 'r'||poly =='R')
            resultlist = R;

        int terms[][] = Linkedlisttoarray(resultlist) ;

        for (int i = 0 ; i < terms.length ;i++)
            result += (float) terms[i][0] * Math.pow(value, terms[i][1]);

        return result;
    }

    public void clearPolynomial(char poly){
        if (poly == 'a' || poly == 'A')
            A.clear();
        else if (poly == 'b' || poly == 'B')
            B.clear();
        else if (poly == 'c' || poly == 'C')
            C.clear();
        else
            System.out.printf("ERROR NOT VALID VARIABLE");
    }

    public int[][] add(char poly1, char poly2){
        Slinkedlist x1 = null , y1 = null ;

        if (poly1 == 'a' || poly1 == 'A')
            x1 = A ;
        else if (poly1 == 'b' || poly1 == 'B')
            x1 = B ;
        else if (poly1 == 'c' || poly1 == 'C')
            x1 = C ;
        else if ((poly1 == 'r' || poly1 == 'R'))
            x1 = R ;

        if (poly2 == 'a' || poly2 == 'A')
            y1 = A ;
        else if (poly2 == 'b' || poly2 == 'B')
            y1 = B ;
        else if (poly2 == 'c' || poly2 == 'C')
            y1 = C ;
        else if ((poly2 == 'r' || poly2 == 'R'))
            y1 = R ;

        int [][] x = Linkedlisttoarray(x1);
        int [][] y = Linkedlisttoarray(y1);

        int sizeresult = x.length+y.length ;
        int [][] result = new int[sizeresult][2];
        for (int i = 0 ; i < x.length; i++){
            result[i][0] = x[i][0];
            result[i][1] = x[i][1];
        }
        for (int i = x.length ; i < sizeresult; i++ ){
            result[i][0] = y[i-x.length][0];
            result[i][1] = y[i-x.length][1];
        }

        for (int i = 0 ; i < sizeresult-1 ; i++){
            for (int j = i+1 ; j < sizeresult ; j++){
                if (result[i][1] == result[j][1]) {
                    result[i][0] += result[j][0];
                    result[j][1] = 0 ;
                    result[j][0] = 0 ;
                }
            }
        }
        R.clear();
        R = Arraytolinkedlist(result);
        return result ;
    }

    public int[][] subtract(char poly1, char poly2){
        Slinkedlist x1 = null , y1 = null ;

        if (poly1 == 'a' || poly1 == 'A')
            x1 = A ;
        else if (poly1 == 'b' || poly1 == 'B')
            x1 = B ;
        else if (poly1 == 'c' || poly1 == 'C')
            x1 = C ;
        else if ((poly1 == 'r' || poly1 == 'R'))
            x1 = R ;

        if (poly2 == 'a' || poly2 == 'A')
            y1 = A ;
        else if (poly2 == 'b' || poly2 == 'B')
            y1 = B ;
        else if (poly2 == 'c' || poly2 == 'C')
            y1 = C ;
        else if ((poly2 == 'r' || poly2 == 'R'))
            y1 = R ;

        int [][] x = Linkedlisttoarray(x1);
        int [][] y = Linkedlisttoarray(y1);

        for (int i = 0 ; i < y.length ; i++)
            y[i][0] *= -1 ;
        ///to be like adding but after multibly all of values of poly2 * -1

        int sizeresult = x.length+y.length ;
        int [][] result = new int[sizeresult][2];
        for (int i = 0 ; i < x.length; i++){
            result[i][0] = x[i][0];
            result[i][1] = x[i][1];
        }
        for (int i = x.length ; i < sizeresult; i++ ){
            result[i][0] = y[i-x.length][0];
            result[i][1] = y[i-x.length][1];
        }

        for (int i = 0 ; i < sizeresult-1 ; i++){
            for (int j = i+1 ; j < sizeresult ; j++){
                if (result[i][1] == result[j][1]) {
                    result[i][0] += result[j][0];
                    result[j][1] = 0 ;
                    result[j][0] = 0 ;
                }
            }
        }
        R.clear();
        R = Arraytolinkedlist(result);
        return result ;
    }

    public int[][] multiply(char poly1, char poly2){
        Slinkedlist x1 = null , y1 = null ;

        if (poly1 == 'a' || poly1 == 'A')
            x1 = A ;
        else if (poly1 == 'b' || poly1 == 'B')
            x1 = B ;
        else if (poly1 == 'c' || poly1 == 'C')
            x1 = C ;
        else if ((poly1 == 'r' || poly1 == 'R'))
            x1 = R ;

        if (poly2 == 'a' || poly2 == 'A')
            y1 = A ;
        else if (poly2 == 'b' || poly2 == 'B')
            y1 = B ;
        else if (poly2 == 'c' || poly2 == 'C')
            y1 = C ;
        else if ((poly2 == 'r' || poly2 == 'R'))
            y1 = R ;

        int [][] x = Linkedlisttoarray(x1);
        int [][] y = Linkedlisttoarray(y1);
        int sizeresult = x.length * y.length , flag = 0;
        int [][] result = new int[sizeresult][2] ;

        for (int i = 0 ; i < x.length ; i++){
            for (int j = 0 ; j < y.length ; j++){
                result[flag][0] = x[i][0] * y[j][0] ;
                result[flag][1] = x[i][1] + y[j][1] ;
                flag++;
            }
        }

        for (int i = 0 ; i < sizeresult-1 ; i++){
            for (int j = i+1 ; j < sizeresult ; j++){
                if (result[i][1] == result[j][1]) {
                    result[i][0] += result[j][0];
                    result[j][1] = 0 ;
                    result[j][0] = 0 ;
                }
            }
        }
        R.clear();
        R = Arraytolinkedlist(result);
        return result ;
    }


}













