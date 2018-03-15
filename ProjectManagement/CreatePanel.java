// Assignment #: 6
//         title: Shivaani Methuku
//    StudentID: 1212619354
//      Lecture: T, Th 4:30pm
//  Description: Creating a panel that allows you to create a project 

//import statements 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CreatePanel extends JPanel
{
	//instance variables needed to make the panel layout and components
	private Vector<Project> projectList;
	private JButton button1;
	private SelectPanel sPanel;
	private JLabel projTitle;
	private JLabel projNum;
	private JLabel projLoc;
	private JLabel projAdd;
	private JTextArea textArea;
	private JScrollPane myScrollPane;
	private JTextField t1, t2, t3;

	//Constructor initializes components and organize them using certain layouts
	public CreatePanel(Vector<Project> projectList, SelectPanel sPanel)
	{
		this.projectList = projectList;
		this.sPanel = sPanel;

		// Organize components here

		//making the components 
		button1 = new JButton("Create a project");
		button1.addActionListener(new ButtonListener());
		projTitle = new JLabel("Project Title");
		t1 = new JTextField();  
		projNum = new JLabel("Project Number");
		t2 = new JTextField(); 
		projLoc = new JLabel("Project Location");
		t3 = new JTextField(); 
		projAdd = new JLabel();
		textArea = new JTextArea("No Project");
		myScrollPane = new JScrollPane(textArea);

		//making different panels for each element and then making the layouts for them
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3,2));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(3,1));
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

		//adding the project information fields 
		panel1.add(projTitle);
		panel1.add(t1);
		panel1.add(projNum);
		panel1.add(t2);
		panel1.add(projLoc);
		panel1.add(t3);

		//adding the field to indicate projAdd
		panel2.add(projAdd);
		panel2.add(panel1);

		//button field
		panel3.add(button1);
		panel2.add(panel3);

		//textfield and the rest of the components
		setLayout(new GridLayout(1,2));
		add(panel2);
		add(myScrollPane);
	}


	//ButtonListener is a listener class that listens to
	//see if the button "Create a project" is pushed.
	//When the event occurs, it add the project information
	//in the text fields to the text area
	//and the list of project information,
	//and it also does error checking.
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//if the two string field are empty 
			if (t1.getText().equals("") || t3.getText().equals("") || t2.getText().equals(""))
			{
				projAdd.setText("Please enter all fields");
				projAdd.setForeground(Color.red);
			}
			//if the two string fields are not empty but the number is not digits
			else if (t2.getText().chars().allMatch(Character::isDigit) == false)
			{
				projAdd.setText("Please enter an integer for the project number");
				projAdd.setForeground(Color.red);
			}
			//if fields are correctly field out
			else {
				//make a new project with all components 
				//return a list of all the projects including the new one
				Project newProj = new Project();
				newProj.setProjTitle(t1.getText());
				newProj.setProjNumber(Integer.parseInt(t2.getText()));
				newProj.setProjLocation(t3.getText());
				projectList.add(newProj);
				textArea.setText(projectList.get(0).toString());
				for (int i = 1; i < projectList.size(); i++)
					textArea.append(projectList.get(i).toString());
				projAdd.setText("Project added.");
				projAdd.setForeground(Color.red);
				//String title = t1.getText();
				//textArea.setText("Project Title:\t\t" + t1.getText() 
				//+ "\n" + "Project Number:\t" + t2.getText() + "\n" 
				//+ "Project Location:\t" + t3.getText() + "\n");
			}
			// if there is no error, add a project to project list
			// otherwise, show an error message
			//update the selectPanel with this new project information 
			sPanel.updateProjectList();
		} //end of actionPerformed method
	} //end of ButtonListener class

} //end of CreatePanel class