
/*
* Name: Noal Balint
* Date: Oct. 2 2018
* Filename: TaskListLL.java
* Details: CSC115 Assignment 2
*/

public class TaskListLL implements TaskList
{

    private TaskListNode head;
    private int count;

    public TaskListLL() {
      head = null;
      count = 0;      // initiallize the 'ecosystem' of the linked list?
    }




    public int getLength() {
        return count;
    }


    public boolean isEmpty() {
        if(head == null){
          return true;
        }
        return false;
    }

    public Task removeHead() {
        Task result; // was given
        TaskListNode curr;
        if(head != null) // list not empty
        {
          curr = head;
          head = curr.next;
          result = curr.task;
          count --;
          return result;
        } else { return null; }

    }


    public Task remove(int number)
    {

      if(head == null)
      {
        return null; // empty list situation protocol
      }

      TaskListNode previous = head; // just to initialize it. will get changed before use.
      TaskListNode current = head;

      while(current != null) // iterate till end of list
      {

        if(current.task.getNumber() == number) // if number exists in array
        {
          if (current == head) {  // if it is first node in list (regardless of if it's only node)
                                  // SPECIAL CASE for first node in list

          head = current.next;    // set head to point to the thing after 1st node. Prev and curr
          count--;
          return current.task;    // STILL POINT TO IT though so will not get collected by grabage collector?
                                  // current = null; this would destroy the data in current?
                                  // this is a method for deletion?
          }
          else {  // (need the else or else code will run regarless of if() result)
            previous.next = current.next; // skip over current node (that which contains task ##)

            count--;
            return current.task;
          }
          // because of our insert implementation, we know that only one instance of each
          // task number can exist in the list.
        }
        previous = current;
        current = current.next;
      }

        // count doesn't change
        return null;
    }



    public boolean insert(int priority, int number)
    {

      Task newTask = new Task(priority, number);
      TaskListNode newNode = new TaskListNode(newTask, null);
      TaskListNode current = head;
      TaskListNode prev = head;
      int highestPriority = 0;

      if(head == null) // special case: empty list
      {                // only node; no need to sort

        head = newNode;
        count++;
        return true;
      }

      while(current != null) // iterate to check number
      {
        if(current.task.getPriority() > highestPriority){
          highestPriority = current.task.getPriority();  // keep track of highest priority in list for
                                                        // use in 'special case: insert front'
        }

        if(current.task.getNumber() == number) // make sure task number doesn't already exist
        {
            return false;
        }

        current = current.next; // update iteration
      }

      current = head; //reset

      if(priority > highestPriority) //special case: insert to front of list
      {
        newNode.next = head;
        head = newNode;
        count++;
        return true;
      }

      while(current != null) // iterate to check priority
      {
        if(current.next == null) // inserting at very end of the list
        {
          if(current.task.getPriority() >= priority)
          {
            newNode.next = current.next;
            current.next = newNode;
            count++;
            return true;
          }
        }
        if(current.task.getPriority() >= priority && (priority > current.next.task.getPriority() || current.next == null) )
        // can't just check current.task.getPriority() >= priority, because then you would always insert
        // at the first time you find it to be bigger. Rather you should at the first time the next is FASLE
        {
          newNode.next = current.next;
          current.next = newNode;
          count++;
          return true;
        }

        current = current.next;
      }

      return false; // nothing got inserted
    }



    public Task retrieve(int pos)
    {

      if(head == null)
      {
        return null; // list is empty. could also have used isEmpty == true here eh?
      }

      if(count <= pos) // count = 3 pos = 0 so no run
      {
        return null;
      }

      TaskListNode current = head;

    for(int i = 0; i < pos; i++)
    {
      current = current.next;
    }

    return current.task;

    }
}
