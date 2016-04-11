package net.sumo.nextgen.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.osbot.rs07.api.ui.Skill;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.stage.StageType;
import net.sumo.nextgen.stage.TaskTest;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class GuiTest {

	private JFrame frame;

	JList<String> list;
	private JScrollPane sourceScroll;
	private JList<Stage> sourceList;
	private JScrollPane destScroll;
	private JList<TaskTest> destList;
	private JButton addButton;
	private JButton btnRemoveTask;
	DefaultListModel<TaskTest> destmodel;
	DefaultListModel<Stage> sourcemodel;
	JButton btnStart;

	/**
	 * Launch the application.
	 * 
	 * 
	 * 
	 * /** Create the application.
	 */
	public GuiTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(600, 500);
		frame.setVisible(true);

		JComboBox comboBoxSkillGoal = new JComboBox();
		comboBoxSkillGoal.setBounds(150, 49, 52, 27);
		frame.getContentPane().add(comboBoxSkillGoal);
		for (int i = 1; i <= 99;) {
			comboBoxSkillGoal.addItem(i);
			i++;
		}

		sourcemodel = new DefaultListModel<Stage>();
		sourceScroll = new JScrollPane();
		sourceScroll.setBounds(19, 51, 122, 242);
		frame.getContentPane().add(sourceScroll);

		sourceList = new JList<Stage>(sourcemodel);
		sourceScroll.setViewportView(sourceList);
		destmodel = new DefaultListModel<TaskTest>();
		destScroll = new JScrollPane();
		destScroll.setBounds(303, 51, 117, 247);
		frame.getContentPane().add(destScroll);

		destList = new JList<TaskTest>(destmodel);
		destScroll.setViewportView(destList);

		Arrays.asList(Stage.values()).forEach(stage -> {
			sourcemodel.addElement(stage);
		});
		btnStart = new JButton("START");
		btnStart.setBounds(227, 377, 153, 72);
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < Resources.TASKTEST_LIST.size();) {
					Resources.TASKTEST_LIST.remove(Resources.TASKTEST_LIST.get(i));
					i++;
				}
				for (int i = 0; i < destList.getModel().getSize();) {

					TaskTest grabben = destList.getModel().getElementAt(i);
					if (grabben.getStage().getType() == StageType.QUEST) {
						if (!Resources.STAGE_LIST.contains(grabben)) {
							Resources.taskTest.add(grabben);
						}
					}else if(grabben.getStage().getType() == StageType.SKILL && Integer.parseInt(comboBoxSkillGoal.getSelectedItem().toString()) > 0){
							Resources.taskTest.add(grabben);					
					}else if(grabben.getStage().getType() == StageType.COMBAT && Integer.parseInt(comboBoxSkillGoal.getSelectedItem().toString()) > 0){
						Resources.taskTest.add(grabben);					
				}
					i++;
				}
			}

		});
		frame.getContentPane().add(btnStart);

		addButton = new JButton("ADD >>");
		addButton.setBounds(177, 194, 86, 16);
		frame.getContentPane().add(addButton);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				destmodel.addElement(new TaskTest(sourceList.getSelectedValue(),Integer.parseInt(comboBoxSkillGoal.getSelectedItem().toString()), sourceList.getSelectedValue().getSkill()));
				
				if (sourceList.getModel().getElementAt(sourceList.getSelectedIndex()).getType() == StageType.QUEST) {
					sourcemodel.remove(sourceList.getSelectedIndex());
				}
			}

		});

		btnRemoveTask = new JButton("<< REMOVE");
		btnRemoveTask.setBounds(177, 236, 86, 16);
		btnRemoveTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (destList.getModel().getSize() > 0 && !destList.isSelectionEmpty()) {
					if (destList.getModel().getElementAt(destList.getSelectedIndex()).getStage().getType() == StageType.QUEST) {
						sourcemodel.addElement(destList.getSelectedValue().getStage());
					}
					destmodel.remove(destList.getSelectedIndex());
				}
			}

		});
		frame.getContentPane().add(btnRemoveTask);

	}
	
	public class IngredientListCellRenderer extends DefaultListCellRenderer {
	    public Component getListCellRendererComponent(JList<?> list,
	                                 Object value,
	                                 int index,
	                                 boolean isSelected,
	                                 boolean cellHasFocus) {
	        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        if (value instanceof TaskTest) {
	            TaskTest ingredient = (TaskTest)value;
	            setText("Stage: " + ingredient.getLevelGoal() + ingredient.getStage());
	        }
	        return this;
	    }
	}
}


