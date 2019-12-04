package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

public class ConwaysGameOfLifeView extends JPanel implements ActionListener, SpotListener {
	
	private static final Color DEFAULT_SPOT_COLOR = new Color(0.8f, 0.8f, 0.8f);
	
	private JSpotBoard field;
	private JTextField width;
	private JTextField height;
	private JButton changeGrid;
	private List<ConwaysGameOfLifeViewListener> listeners;
	
	public ConwaysGameOfLifeView() {
		setLayout(new BorderLayout());

		setField(new JSpotBoard(10,10, DEFAULT_SPOT_COLOR));
				
		JPanel subpanel = new JPanel();
		subpanel.setLayout(new BorderLayout());
		
		JPanel field_size_panel = new JPanel();
		field_size_panel.setLayout(new GridLayout(1,5));
		
		this.width = new JTextField(10);
		this.height = new JTextField(10);
		this.changeGrid = new JButton("Change Grid");
		
		field_size_panel.add(new JLabel("Width: "));
		field_size_panel.add(width);
		field_size_panel.add(new JLabel("Height: "));
		field_size_panel.add(height);
		field_size_panel.add(changeGrid);
		
		subpanel.add(field_size_panel, BorderLayout.NORTH);
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(1,3));
		
		button_panel.add(new JButton("Clear"));
		button_panel.add(new JButton("Randomize"));
		button_panel.add(new JButton("Advance"));
		
		subpanel.add(button_panel, BorderLayout.SOUTH);
		
		add(subpanel, BorderLayout.SOUTH);
		
		for(Component c: field_size_panel.getComponents()) {
			if (c == changeGrid) {
				JButton b = (JButton) c;
				b.addActionListener(this);
			}
		}
		
		for(Component c: button_panel.getComponents()) {
			JButton b = (JButton) c;
			b.addActionListener(this);
		}
		
		this.listeners = new ArrayList<ConwaysGameOfLifeViewListener>();
				
	}
	
	public void setField(JSpotBoard board) {
		if (field != null) {
			remove(field);
		}
		field = board;
		for (Spot s: field) {
			s.toggleSpot();
		}
		field.addSpotListener(this);
		this.add(field, BorderLayout.CENTER);

	}
	
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		dispatchEventByText(button.getText());
	}
	
	public void dispatchEventByText(String text) {
		
		if (text.equals("Change Grid")) {
			fireEvent(new ChangeGridEvent(Integer.parseInt(width.getText()), Integer.parseInt(height.getText())));
		} else if (text.equals("Clear")) {
			fireEvent(new ClearEvent(field));
		} else if (text.equals("Randomize")) {
			fireEvent(new RandomizeEvent(field));
		} else if (text.equals("Advance")) {
			fireEvent(new AdvanceEvent(field));
		}

	}

	public void addConwaysGameOfLifeViewListener(ConwaysGameOfLifeViewListener l) {
		listeners.add(l);
		
	}
	
	public void removeConwaysGameOfLifeViewListener(ConwaysGameOfLifeViewListener l) {
		listeners.remove(l);
		
	}
	
	void displayErrorMessage(String errorMessage) {
	   JOptionPane.showMessageDialog(this, errorMessage);
	}

	public void fireEvent(ConwaysGameOfLifeViewEvent e) {
		for (ConwaysGameOfLifeViewListener l : listeners) {
			l.handleConwaysGameOfLifeViewEvent(e);
		}
	}

	@Override
	public void spotClicked(Spot spot) {
		if (spot.getSpotColor() == Color.BLACK) {
			spot.setSpotColor(DEFAULT_SPOT_COLOR);
		} else {
			spot.setSpotColor(Color.BLACK);
		}
	}

	@Override
	public void spotEntered(Spot spot) {
		spot.highlightSpot();
	}

	@Override
	public void spotExited(Spot spot) {
		spot.unhighlightSpot();
	}
}
