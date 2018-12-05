/*
* Name: Noal Balint
* ID: V00906500
* Date: Nov. 3 2018
* Filename: StackEmptyException.java
* Details: CSC115 Assignment 3
*/

public class StackEmptyException extends RuntimeException {

StackEmptyException(String msg)
{
  super(msg);
}

StackEmptyException()
{
  super();
}

}
