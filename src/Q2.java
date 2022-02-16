// class to define what the hash table will contain
class Node {
  String key;
  int data;
  Node next;
  Node(String key, int data) {
    this.key = key;
    this.data = data;
  }
}

public class Q2 {

// this interface is to define nested methods that simplify the method calls.
// now we only have to call the function with the variable information rather than with the table as well.
  interface nestedFunctions {
    void insert(String key, int data);
    void remove(String key);
    int get(String key);
  }

// the following methods have an underscore before them to show that we should not directly call them.
  static void _insert(String key, int data, Node[] table) {
    int index = _hash(key, table.length);
    Node currNode = table[index];
    Node prevNode = currNode;
    if (table[index] == null) {
      table[index] = new Node(key, data);
      return;
    }
    while (currNode != null && !currNode.key.equals(key)) {
      prevNode = currNode;
      currNode = currNode.next;
    }
    currNode = new Node(key, data);
    if (prevNode != null) {
      prevNode.next = currNode;
    }
  }

  static int _get(String key, Node[] table) {
    int index = _hash(key, table.length);
    Node currNode = table[index];
    while (currNode != null && !currNode.key.equals(key)) {
      currNode = currNode.next;
    }
    if (currNode == null) {
      return -1;
    }
    return currNode.data;
  }

  static void _remove(String key, Node[] table) {
    int index = _hash(key, table.length);
    if (table[index] != null && table[index].key.equals(key)){
      table[index] = table[index].next;
      return;
    }
    Node currNode = table[index];
    Node prevNode = currNode;
    while (currNode != null && !currNode.key.equals(key)) {
      prevNode = currNode;
      currNode = currNode.next;
    }
    if (currNode == null) {
      return;
    }
    prevNode.next = currNode.next;
  }

  static int _hash(String key, int length){
    int index = 0;
    for (int i = key.length()-1; i > 0; --i) {
      index = (index * 31 + (int) key.charAt(i)) % length;
    }
    return index;
  }

  public static void main(String[] args) {
    
    // our hash table
    Node[] table = new Node[7];

    // these are the nested functions
    nestedFunctions f = new nestedFunctions() {
      @Override
      public void insert(String key, int data) {
        _insert(key, data, table);
      }
      @Override
      public void remove(String key) {
        _remove(key, table);
      }
      @Override
      public int get(String key) {
        return _get(key, table);
      }
    };

    // test case 1. insert, get, remove
    f.insert("justin allard", 251204941);
    System.out.println(f.get("justin allard"));
    f.remove("justin allard");
    // show that the entry has been removed
    System.out.println(f.get("justin allard"));

    // test case 2. algorithm is at its worst when there are collisions at the target index.
    // the following keys produce the same index when passed to the hash function.
    System.out.println("\nKeys with the same has result (collision at index 2) :");
    System.out.println("rachel: " + _hash("rachel", table.length));
    System.out.println("334181211: " + _hash("334181211", table.length));
    System.out.println("Hey: " + _hash("Hey", table.length));
    System.out.println("allard: " + _hash("allard", table.length));
    System.out.println("PO: " + _hash("PO", table.length));

    f.insert("rachel", 1);
    f.insert("334181211", 2);
    f.insert("Hey", 3);
    f.insert("allard", 4);
    f.insert("PO", 5);

    // the 'get' call will have to go through the linked list until it reaches the 5th node.
    System.out.println("\n" + f.get("PO"));
    // as collisions increase, accessing the last element will take longer and longer.
    // increasing the size of the table will reduce the number of collisions
  }
}