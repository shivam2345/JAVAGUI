// Assignment #: 7
//         title: Shivaani Methuku
//    StudentID: 1212619354
//      Lecture: T, Th 4:30pm
//  Description: Create an object for the wholepanel to be manipulated

import javax.swing.*;

public class Assignment7 extends JApplet 
{

 public void init()
  {
    // create a WholePanel object and add it to the applet
    WholePanel wholePanel = new WholePanel();
    getContentPane().add(wholePanel);

    //set applet size to 400 X 400
    setSize (400, 400);
  }

}




