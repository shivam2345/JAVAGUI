// Assignment #: 6
//         title: Shivaani Methuku
//    StudentID: 1212619354
//      Lecture: T, Th 4:30pm
//  Description: Creating a panel that allows you to manipulate a project (add or remove)

//import statements
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

public class SelectPanel extends JPanel
{
	//create instance variables with the compontents needed for the new select project panel
	private Vector projectList, selectedList;
	protected JList list;
	private JList select;
	private int count = 0;
	private JScrollPane myScrollPane;
	private JTextArea textArea;
	private JScrollPane myScrollPane2;
	private JButton add;
	private JButton remove;
	private JTextArea textArea2;
	private DefaultListModel selectModel;
	private JLabel label5;
	//Constructor initialize each component and arrange them using
	//certain layouts
	public SelectPanel(Vector projectList)
	{
		this.projectList = projectList;
		//adding items to the Jlist 
		list = new JList(projectList);
		//selectlist part 
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//Object obj1 = list.getSelectedValue();

		selectedList  = new Vector();
		//select = new JList (selectedList);
		selectModel = new DefaultListModel();
		select = new JList(selectModel);
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// organize components for the select panel

		//make the components such as the buttons
		add = new JButton("Add");
		add.addActionListener(new ButtonListener());
		remove = new JButton("Remove");
		remove.addActionListener(new ButtonListener());
		JLabel label1 = new JLabel("Available project(s)");
		myScrollPane = new JScrollPane(list);
		myScrollPane2 = new JScrollPane(select); 
		JLabel label3 = new JLabel("Selected project(s)");
		label5 = new JLabel("The total number of selected projects: " + count);

		//make the panels needed and the layout for them
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,2));
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(3,1));
		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(3,1));
		JPanel panel5 = new JPanel();
		panel5.setLayout(new GridLayout(3,1));

		//add the components to their respective panel 
		panel1.add(label1);
		panel1.add(myScrollPane);
		panel2.add(add);
		panel2.add(remove);
		panel3.add(label3);
		panel3.add(myScrollPane2);
		panel3.add(label5);

		// organize components for the select panel
		setLayout(new GridLayout(3,1));
		add(panel1);
		add(panel2);
		add(panel3);
	}

	//This method updates refresh the JList of projects with
	//updated vector information
	public void updateProjectList()
	{
		// call updateUI() for the JList
		list.updateUI();
	}

	//ButtonListener class listens to see if any of
	//buttons is pushed, and perform their corresponding action.
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//when the add button is pressed
			if (event.getSource() == add)
			{
				//update label5 with right amount of projects 
				//add new project from vector to JList
				addItem();

				//myScrollPane2.add(list.getSelectedValue());
				//list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				//select.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				//textArea.setText(list.getSelectedValue().toString());
				//selectModel.addElement(list.getSelectedValue());
				//select.setListData(list.getSelectedValue());
				//String title = ((Project) projectList.get(index)).getProjTitle();
				//int number = ((Project) projectList.get(index)).getProjNumber();
				//String location = ((Project) projectList.get(index)).getProjLocation();
				//textArea.append(this.toString(title, number, location));
			}

			//when the remove button is pressed
			else if (event.getSource() == remove)
			{
				//delete stuff from selected list but not the list (intial)
				removeItem();
			}

		}

		private void addItem() {
			//adding the items from the model and then increasing the count
			selectModel.addElement(list.getSelectedValue());
			count++;
			label5.setText("The total number of selected projects: " + Integer.toString(count));
		}

		private void removeItem() {
			//removing the items from the model and decreasing the count
			selectModel.remove(select.getSelectedIndex());
			count--;
			label5.setText("The total number of selected projects: " + Integer.toString(count));
		}
	} //end of ButtonListener class
} //end of SelectPanel class