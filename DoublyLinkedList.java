import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
class Node<T> {
 
	T data;
	Node<T> prev, next;
 
}
 
class Operations<T> {
 
	Node<T> head=null;
	int size=0;
	HashMap<T,List<Node<T>>> map = new HashMap<T,List<Node<T>>>();
 
	public void add_first(T val) {
 
		//new node which is to be added
		Node<T> node = new Node<T>();
		node.data=val;
		node.next=head;
 
		// if list is not empty
		if(head!=null)
			head.prev=node;
 
		// changing head to point to first node
		head=node;
		size++;
		addToMap(node);
 
	}
 
	public void add_last(T val) {
 
		// if list is empty
		if(head==null) {
			add_first(val);
			return;
		}
 
		Node<T> node = new Node<T>();
		node.data=val;
		Node<T> p = head;
 
		//After running while loop p will point to last node
		while(p.next!=null) {
			p=p.next;
		}
 
		p.next=node;
		node.prev=p;
		size++;
		addToMap(node);
	}
 
	public void add_at_index(int index,T val) {
 
		if(index==0)  // add at 1st position
			add_first(val);
		else if(index==size) // add at last position
			add_last(val);
		else if(index >size || index<0)
			System.out.println("Cannot add : Invalid Index");  // invalid case
		else {		
			int i=0;
			Node<T> p= head;
			Node <T> node = new Node<T>();
			node.data=val;
 
			while(p!=null) {  // loop to reach at index-1
 
				if(i==index-1) {					
					Node<T> q = p.next;
					node.next=q;
					q.prev=node;
					node.prev=p;
					p.next=node;
					size++;
					addToMap(node);
					return;
				}
				p=p.next;
				i++;
			}
 
		}
 
	}
	public void traverse() {
 
		Node<T> p= head;
        if(p==null) {
            System.out.println("No Elements Present to Traverse");
            return;
        }
        System.out.print("Traversal : ");
		while(p!=null) {
			System.out.printf((T)p.data+"  ");
			p=p.next;
		}
		System.out.println();
	}
	
	public void addToMap(Node<T> node) {
		List<Node<T>> l;
		if(map.get(node.data)!=null) // if map has current node
			l = map.get(node.data);
		else // if map doesn't have current node
		{
			l = new ArrayList<Node<T>>();
			map.put((T)node.data, l);
		}
		l.add(node);
	}
	
	
	public void removeFromMap(Node<T> p) {
		
		 List<Node<T>> l;
			l = map.get(p.data);
			if(l.size()>1) {
				l.remove(p);
			}else {
				map.remove(p.data);
			}
	}
	
	public Node<T> remove_first() {
 
		// if list is empty 
		if(head==null)
			{
				System.out.println("Cannot Remove : list empty");
				return null;
			}
 
 
		Node<T> p= head;
		head=head.next;
		p.next=null;
 
		if(head!=null)
			head.prev=null;
 
	    size--;
	    removeFromMap(p);
 
		return p;
 
	}
 
	public Node<T> remove_last() {
 
		// if list is empty 
		if(head==null )
		{
			System.out.println("Cannot Remove : list empty");
			return null;
		}
 
		// if list has 1 element
		if(head.next==null) {
			size--;
			map.remove(head.data);
			head=null;
			return null;
		}
 
		Node<T> q=head,p= head;
 
		while(p.next!=null) {
			q=p;
			p=p.next;
		}
 
		q.next=null;
		p.prev=null;
		size--;
		removeFromMap(p);
		return p;
 
	}
 
  public Node<T> remove_at_index(int index){
 
	  if(index==0) // remove 1st element
		 return remove_first();
	  else if(index==size-1)  // remove last element
		 return remove_last();
	  else if(index>=size || index<0) {  // invalid case
		  System.out.println("Cannot remove : Invalid Index");
		  return null;
	  }
	  else {  
		  int i=0;
		  Node<T> p= head;
		  while(p!=null) {        // loop to reach at index-1
 
				if(i==index-1) {					
					Node<T> q = p.next;
					p.next=q.next;
					(q.next).prev=p;
					q.prev=null;
					q.next=null;
					size--;
					removeFromMap(q);
					return q;
				}
				p=p.next;
				i++;
			}
 
	  }
	  return null;
  }
 
  public Node<T> find(T val){
 
	 if(map.get(val)!=null)
	 {
		 List<Node<T>> l= map.get(val);
         System.out.println("Node Present at Address : "+l.get(0));
		 return l.get(0);
	 }
	 else {		
		 System.out.println(val+" not present");
		 return null;
	 }
 
  }
}
 
public class DoublyLinkedList {
 
	public static void main(String[] args) {
 
        System.out.println("Test Script 1:\n");
		
        Operations<Integer> op= new Operations<Integer>();
        	op.add_at_index(1,100);
		op.traverse();
		op.remove_first();
		op.find(70);
		op.traverse();
		op.remove_last();
		op.remove_last();
		op.find(70);
		op.traverse();
		op.remove_at_index(2);
		op.traverse();
		op.find(99);
        	op.remove_first();
		op.traverse();
		op.add_last(40);
        	op.traverse();
        	op.find(40);
		op.traverse();
		op.add_at_index(1, 70);
		op.traverse();
		op.add_at_index(3, 70);
		op.traverse();
		op.add_first(20);
		op.traverse();
		op.add_first(10);
		op.traverse();
		op.add_last(50);
		op.traverse();
		op.add_at_index(0, 60);
		op.traverse();
		op.add_at_index(0, 70);
		op.traverse();
		op.add_at_index(0, 70);
		op.traverse();
		op.add_at_index(5,100);
		op.traverse();
		op.remove_first();
		op.find(70);
		op.traverse();
		op.remove_first();
		op.find(70);
		op.traverse();
		op.remove_last();
		op.remove_last();
		op.find(70);
		op.traverse();
		op.remove_at_index(2);
		op.traverse();
 
        System.out.println("\n\nTest Script 2:\n");
        
        Operations<String> op1= new Operations<String>();
		op1.remove_first();
		op1.traverse();
		op1.add_last("a");
		op1.traverse();
		op1.add_at_index(1, "b");
		op1.traverse();
		op1.add_at_index(3, "b");
		op1.traverse();
		op1.add_first("c");
		op1.traverse();
		op1.add_first("d");
		op1.traverse();
		op1.add_last("e");
		op1.traverse();
		op1.add_at_index(0, "f");
		op1.traverse();
		op1.add_at_index(1, "g");
		op1.traverse();
		op1.add_at_index(1, "h");
		op1.traverse();		
		op1.add_at_index(5,"i");
		op1.traverse();
		op1.remove_first();
		op1.traverse();
		op1.remove_last();
		op1.traverse();
		op1.remove_at_index(2);
		op1.traverse();
	}
}