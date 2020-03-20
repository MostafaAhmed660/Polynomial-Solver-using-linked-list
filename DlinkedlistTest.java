package eg.edu.alexu.csd.datastructure.linkedList;

import org.junit.Test;

import static org.junit.Assert.*;

public class DlinkedlistTest {

    @Test
    public void test1() {
        Dlinkedlist list = new Dlinkedlist();
        for (int i = 5 ; i <= 25 ; i+=5)
            list.add(i);
        //list : 5 10 15 20 25
        assertTrue(list.contains(5));
        assertEquals(5,list.size);
        list.add(1,100);

        // list : 5 100 10 15 20 25
        assertEquals(20,list.get(4));
        assertEquals(100,list.get(1));
        assertEquals(6,list.size);

        list.remove(1);
        //list : 5 10 15 20 25
        assertEquals(10,list.get(1));
        assertEquals(5,list.size);

        list.add(4,6060);
        //list : 5 10 15 20 6060 25
        assertEquals(6060,list.get(4));
        assertEquals(6,list.size);
    }

    @Test
    public void test2() {
        Dlinkedlist list = new Dlinkedlist();
        Dlinkedlist check = new Dlinkedlist();

        for (int i = 5 ; i <= 25 ; i+=5)
            list.add(i);
        //list : 5 10 15 20 25

        for (int i = 10 ; i <= 25 ; i+=5)
            check.add(i);
        // check : 10 15 20 25
        assertEquals(list.sublist(1,4).get(0),check.get(0));
        assertEquals(list.sublist(1,4).get(1),check.get(1));
        assertEquals(list.sublist(1,4).get(2),check.get(2));
        assertEquals(list.sublist(1,4).get(3),check.get(3));
        assertFalse(check.contains(5));

        list.clear();
        check.clear();
        for (int i = 20 ; i <= 30 ; i++){
            list.add(i);
        }
        assertEquals(list.sublist(0,list.size-1).get(0),list.get(0));
        assertEquals(list.sublist(0,list.size-1).get(list.size-1),list.get(list.size-1));
        //take whole list as a sublist : compare first element and last



        list.clear();
        check.clear();
        list.add(5);
        check.add(5);
        // list : 5
        assertEquals(list.sublist(0,0).get(0),check.get(0));
        //one element in list compare it with check
    }

    @Test
    public void test3() {
        Dlinkedlist list = new Dlinkedlist();
        for (int i = 5 ; i <= 25 ; i+=5)
            list.add(i);
        //list : 5 10 15 20 25
        list.set(0,20);
        assertEquals(list.get(0),20);
        list.set(list.size-1,10000);
        assertEquals(list.get(list.size-1),10000);
    }

    @Test
    public void test4() {
        Dlinkedlist list = new Dlinkedlist();
        for (int i = 5 ; i <= 25 ; i+=5)
            list.add(i);
        //list : 5 10 15 20 25
        list.clear();
        assertEquals(list.size,0);
    }

    @Test
    public void test5() {
        Dlinkedlist list = new Dlinkedlist();
        assertTrue(list.isEmpty());
    }

    @Test
    public void test6() {
        Dlinkedlist list = new Dlinkedlist();
        for (int i = 5 ; i <= 25 ; i+=5)
            list.add(i);
        //list : 5 10 15 20 25
        list.remove(0);
        //list : 10 15 20 25  removing the first element
        assertEquals(list.size,4);
        assertEquals(list.get(0),10);
        assertFalse(list.contains(5));

        list.remove(list.size-1);
        //list : 10 15 20  removing 25 the last element
        assertFalse(list.contains(25));
    }
}