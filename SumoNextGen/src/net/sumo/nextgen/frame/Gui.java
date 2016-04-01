package net.sumo.nextgen.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.stage.StageType;
import net.sumo.nextgen.stage.TaskTest;

public class Gui extends JPanel {

	private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);

	private static final String ADD_BUTTON_LABEL = "Add >>";

	private static final String REMOVE_BUTTON_LABEL = "<< Remove";

	private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Available Choices";

	private static final String DEFAULT_DEST_CHOICE_LABEL = "Your Choices";

	private JLabel sourceLabel;

	private JList<Stage> sourceList;

	private SortedListModel sourceListModel;

	private JList<Stage> destList;

	private SortedListModel destListModel;

	private JLabel destLabel;

	private JButton addButton;

	private JButton removeButton;

	public Gui() {
		initScreen();
	}

	public String getSourceChoicesTitle() {
		return sourceLabel.getText();
	}

	public void setSourceChoicesTitle(String newValue) {
		sourceLabel.setText(newValue);
	}

	public String getDestinationChoicesTitle() {
		return destLabel.getText();
	}

	public void setDestinationChoicesTitle(String newValue) {
		destLabel.setText(newValue);
	}

	public void clearSourceListModel() {
		sourceListModel.clear();
	}

	public void clearDestinationListModel() {
		destListModel.clear();
	}

	public void addSourceElements(ListModel newValue) {
		fillListModel(sourceListModel, newValue);
	}

	public void addSourceElements(Object newValue) {
		fillListModel(sourceListModel, newValue);
	}

	public void setSourceElements(ListModel newValue) {
		clearSourceListModel();
		addSourceElements(newValue);
	}

	public void addDestinationElements(ListModel newValue) {
		fillListModel(destListModel, newValue);
	}

	private void fillListModel(SortedListModel model, ListModel newValues) {
		int size = newValues.getSize();
		for (int i = 0; i < size; i++) {
			model.add(newValues.getElementAt(i));
		}
	}

	private void fillListModel(SortedListModel model, Object newValues) {
		model.add(newValues);
	}

	public void addSourceElements(Object newValue[]) {
		fillListModel(sourceListModel, newValue);
	}

	public void setSourceElements(Object newValue[]) {
		clearSourceListModel();
		addSourceElements(newValue);
	}

	public void addDestinationElements(Object newValue[]) {
		fillListModel(destListModel, newValue);
	}

	public void addDestinationElements(Object newValue) {
		fillListModel(destListModel, newValue);
	}

	private void fillListModel(SortedListModel model, Object newValues[]) {
		model.addAll(newValues);
	}

	public Iterator sourceIterator() {
		return sourceListModel.iterator();
	}

	public Iterator destinationIterator() {
		return destListModel.iterator();
	}

	public void setSourceCellRenderer(ListCellRenderer newValue) {
		sourceList.setCellRenderer(newValue);
	}

	public ListCellRenderer getSourceCellRenderer() {
		return sourceList.getCellRenderer();
	}

	public void setDestinationCellRenderer(ListCellRenderer newValue) {
		destList.setCellRenderer(newValue);
	}

	public ListCellRenderer getDestinationCellRenderer() {
		return destList.getCellRenderer();
	}

	public void setVisibleRowCount(int newValue) {
		sourceList.setVisibleRowCount(newValue);
		destList.setVisibleRowCount(newValue);
	}

	public int getVisibleRowCount() {
		return sourceList.getVisibleRowCount();
	}

	public void setSelectionBackground(Color newValue) {
		sourceList.setSelectionBackground(newValue);
		destList.setSelectionBackground(newValue);
	}

	public Color getSelectionBackground() {
		return sourceList.getSelectionBackground();
	}

	public void setSelectionForeground(Color newValue) {
		sourceList.setSelectionForeground(newValue);
		destList.setSelectionForeground(newValue);
	}

	public Color getSelectionForeground() {
		return sourceList.getSelectionForeground();
	}

	private void clearSourceSelected() {
		Object selected[] = sourceList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {

			sourceListModel.removeElement(selected[i]);
		}
		sourceList.getSelectionModel().clearSelection();
	}

	private void clearDestinationSelected() {
		Object selected[] = destList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			destListModel.removeElement(selected[i]);
		}
		destList.getSelectionModel().clearSelection();
	}

	private void initScreen() {
	    setBorder(BorderFactory.createEtchedBorder());
	    sourceLabel = new JLabel(DEFAULT_SOURCE_CHOICE_LABEL);
	    sourceLabel.setBounds(32, 2, 111, 16);
	    sourceListModel = new SortedListModel();
	    sourceList = new JList(sourceListModel);
	    setLayout(null);
	    add(sourceLabel);
	    JScrollPane scrollPane = new JScrollPane(sourceList);
	    scrollPane.setBounds(2, 23, 171, 274);
	    add(scrollPane);

	    addButton = new JButton(ADD_BUTTON_LABEL);
	    addButton.setBounds(200, 122, 93, 29);
	    add(addButton);
	    addButton.addActionListener(new AddListener());

	    destLabel = new JLabel(DEFAULT_DEST_CHOICE_LABEL);
	    destLabel.setBounds(335, 2, 83, 16);
	    destListModel = new SortedListModel();
	    destList = new JList(destListModel);
	    add(destLabel);
	    JScrollPane scrollPane_1 = new JScrollPane(destList);
	    scrollPane_1.setBounds(305, 23, 143, 274);
	    add(scrollPane_1);
	    removeButton = new JButton(REMOVE_BUTTON_LABEL);
	    removeButton.setBounds(185, 170, 117, 29);
	    add(removeButton);
	    
	    JButton btnStart = new JButton("START");
	    btnStart.setBounds(185, 242, 117, 29);
	    add(btnStart);
	    btnStart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Resources.TASKTEST_LIST.add(new TaskTest(Stage.COOKS_ASSISTANT));
				for(int i = 0; i<Resources.TASKTEST_LIST.size();){
					Resources.TASKTEST_LIST.remove(Resources.TASKTEST_LIST.get(i));
					i++;
				}
				for(int i = 0; i<destList.getModel().getSize();){
					
					Stage grabben = destList.getModel().getElementAt(i);
					if(!Resources.STAGE_LIST.contains(grabben)){
					Resources.TASKTEST_LIST.add(new TaskTest(grabben));
					}
					i++;
				}
				
				
				/*for(int i = 0; i<destList.getModel().getSize();){
					
					Stage grabben = destList.getModel().getElementAt(i);
					if(!Resources.STAGE_LIST.contains(grabben)){
					Resources.STAGE_LIST.add(grabben);
					}
					i++;
				}
				for(int i = 0; i<sourceList.getModel().getSize();){
					Stage grabben = sourceList.getModel().getElementAt(i);
					if(Resources.STAGE_LIST.contains(grabben)){
					Resources.STAGE_LIST.remove(grabben);
					}
					i++;
				}
				
				GuiChoiceHandler g = new GuiChoiceHandler() {
					
					

					@Override
					public void parameters() {
						this.data = "31";
					}
					
				};
				
				g.parameters();
				
				g.data;*/
				
			}
	    	
	    });
	    removeButton.addActionListener(new RemoveListener());
	  }

	private class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			List<Stage> selected = sourceList.getSelectedValuesList();
			selected.forEach(sel -> {
				Object test = sel;
				addDestinationElements(sel);
				if (sel.getType() == StageType.QUEST) {
					clearSourceSelected();
				}
			});
		}
	}

	private class RemoveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object selected[] = destList.getSelectedValues();
			addSourceElements(selected);
			clearDestinationSelected();
		}
	}
}

class SortedListModel extends AbstractListModel {

	SortedSet model;

	public SortedListModel() {
		model = new TreeSet();
	}

	public int getSize() {
		return model.size();
	}

	public Object getElementAt(int index) {
		return model.toArray()[index];
	}

	public void add(Object element) {
		if (model.add(element)) {
			fireContentsChanged(this, 0, getSize());
		}
	}

	public void addAll(Object elements[]) {
		Collection c = Arrays.asList(elements);
		model.addAll(c);
		fireContentsChanged(this, 0, getSize());
	}

	public void clear() {
		model.clear();
		fireContentsChanged(this, 0, getSize());
	}

	public boolean contains(Object element) {
		return model.contains(element);
	}

	public Object firstElement() {
		return model.first();
	}

	public Iterator iterator() {
		return model.iterator();
	}

	public Object lastElement() {
		return model.last();
	}

	public boolean removeElement(Object element) {
		boolean removed = model.remove(element);
		if (removed) {
			fireContentsChanged(this, 0, getSize());
		}
		return removed;
	}
}
