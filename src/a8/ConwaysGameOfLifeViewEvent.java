package a8;

abstract public class ConwaysGameOfLifeViewEvent {
	public boolean isChangeGridEvent() {
		return false;
	}
	public boolean isClearEvent() {
		return false;
	}
	public boolean isRandomizeEvent() {
		return false;
	}
	public boolean isAdvanceEvent() {
		return false;
	}
	
}

class ChangeGridEvent extends ConwaysGameOfLifeViewEvent {
	private int width;
	private int height;
	
	public ChangeGridEvent(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isChangeGridEvent() {
		return true;
	}
}

class ClearEvent extends ConwaysGameOfLifeViewEvent {
	private JSpotBoard field;
	
	public ClearEvent(JSpotBoard field) {
		this.field = field;
	}
	
	public JSpotBoard getField() {
		return field;
	}
	
	public boolean isClearEvent() {
		return true;
	}
}

class RandomizeEvent extends ConwaysGameOfLifeViewEvent {
	private JSpotBoard field;
	
	public RandomizeEvent(JSpotBoard field) {
		this.field = field;
	}
	
	public JSpotBoard getField() {
		return field;
	}
	
	public boolean isRandomizeEvent() {
		return true;
	}
}

class AdvanceEvent extends ConwaysGameOfLifeViewEvent {
private JSpotBoard field;
	
	public AdvanceEvent(JSpotBoard field) {
		this.field = field;
	}
	
	public JSpotBoard getField() {
		return field;
	}
	
	public boolean isAdvanceEvent() {
		return true;
	}
}


