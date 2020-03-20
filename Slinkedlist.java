package eg.edu.alexu.csd.datastructure.linkedList;

import java.sql.SQLOutput;

class Slinkedlist
{
    SNode head ;
    public int size ;

    public static class SNode {
        Object data ;
        SNode next ;
        SNode(Object data){
            this.data = data ;
        }
    }

    public void add(int index,Object element){
        if (index > size - 1) {
            System.out.printf("ERROR INVALID INDEX");
        }
        else {
            size++;
            SNode mynode = new SNode(element);
            if (index == 0) {
                mynode.next = head;
                head = mynode;
            } else {
                SNode n = head;
                for (int i = 0; i < index - 1; i++) {
                    n = n.next;
                }
                mynode.next = n.next;
                n.next = mynode;
            }
        }
    }

    public void add(Object element){
        size++;
        SNode mynode = new SNode(element);
        if (head == null){
            head = mynode ;
        }
        else{
            SNode n = head ;
            while (n.next != null)
                n=n.next;
            n.next = mynode ;
            mynode.next = null;
        }
    }

    public Slinkedlist sublist(int fromIndex, int toIndex){
        Slinkedlist mylist = new Slinkedlist();
        if (toIndex > size-1 || fromIndex > toIndex ){
            System.out.printf("ERROR INVALIED INDEXES");
        }
        else {
            SNode n = head;
            for (int i = 0; i < fromIndex; i++)
                n = n.next;
            for (int i = fromIndex; i <= toIndex; i++) {
                mylist.add(n.data);
                n = n.next;
            }
        }
        return mylist ;
    }

    public void showall(){
        SNode node = head ;
        while (node!=null){
            System.out.printf(node.data+" ");
            node = node.next ;
        }
    }

    public Object get(int index) {
        if (index > size - 1) {
            System.out.printf("ERROR INVALID INDEX");
            return -1 ;
        }
        else {
            SNode n = head;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
            return n.data;

        }
    }

    public void set(int index,Object element) {
        if (index > size - 1) {
            System.out.printf("ERROR INVALID INDEX");
        }
        else {
            SNode n = head;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
            n.data = element;
        }
    }

    public void clear(){
        head = null ;
        size = 0 ;
    }

    public boolean isEmpty(){
        return (head == null);
    }

    public void remove(int index){
        if (index > size -1)
            System.out.printf("ERROR INVALID INDEX");
        else if (head == null)
            System.out.printf("ERROR LINKEDLIST IS EMPTY");
        else if (index == 0){
            size--;
            head = head.next;
        }
        else {
            size--;
            SNode n = head;
            for (int i = 0; i < index-1; i++)
                n = n.next;
            n.next = (n.next).next;
        }
    }

    public int size(){
        return size ;
    }

    public boolean contains(Object o){
        SNode n = head ;
        while (n != null){
            if (n.data == o)
                return true ;
            n = n.next;
        }
        return false ;
    }

}