/*
* Name: Noal Balint
* Date: Nov. 3 2018
* Filename: Node.java
* Details: CSC115 Assignment 3
*/

public class Node
{
  Node next;
  String item;

Node(String item, Node next)
{
  this.item = item;
  this.next = next;
}

Node(String item)
{
  this.item = item;
  this.next = null;
}

Node(Node next)
{
  this.next = next;
  item = null;
}

Node()
{
  next = null;
  item = null;
}

}
