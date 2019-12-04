package a8;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ConwaysGameOfLifeModel {

	private int field_width;
	private int field_height;
	private JSpotBoard field;
	private List<ConwaysGameOfLifeObserver> observers;
	
	public ConwaysGameOfLifeModel() {
		this.field_width = 10;
		this.field_height = 10;
		this.observers = new ArrayList<ConwaysGameOfLifeObserver>();
	}
	
	public JSpotBoard createField(int width, int length) {
		field_width = width;
		field_height = length;
		return new JSpotBoard(width, length, new Color(0.8f,0.8f,0.8f));
	}

	public int getFieldWidth() {
		return field_width;
	}
	
	public int getFieldHeight() {
		return field_height;
	}
	
	public JSpotBoard getField() {
		return field;
	}
	
	public void addObserver(ConwaysGameOfLifeObserver o) {
		observers.add(o);
	}
	
	public void removeObserver(ConwaysGameOfLifeObserver o) {
		observers.remove(o);
	}

}
