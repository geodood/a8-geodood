package a8;

import java.awt.Color;
import java.util.Random;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;


public class ConwaysGameOfLifeController implements ConwaysGameOfLifeObserver, ConwaysGameOfLifeViewListener {
	
	private static final Color DEFAULT_SPOT_COLOR = new Color(0.8f,0.8f,0.8f);
	
	private ConwaysGameOfLifeView view;
	private ConwaysGameOfLifeModel model;
	
	public ConwaysGameOfLifeController(ConwaysGameOfLifeModel model, ConwaysGameOfLifeView view) {
		this.model = model;
		this.view = view;
	
		view.addConwaysGameOfLifeViewListener(this);
		model.addObserver(this);
	}

	@Override
	public void handleConwaysGameOfLifeViewEvent(ConwaysGameOfLifeViewEvent e) {
		if (e.isChangeGridEvent()) {
			ChangeGridEvent change_grid = (ChangeGridEvent) e;
			
			if (change_grid.getWidth() < 10 ||
				change_grid.getWidth() > 500) {
				view.displayErrorMessage("Width must be between 10 & 500");
				return;
			}
			if (change_grid.getHeight() < 10 ||
				change_grid.getHeight() > 500) {
				view.displayErrorMessage("Height must be between 10 & 500");
				return;
			}
			
			JSpotBoard new_board = model.createField(change_grid.getWidth(),change_grid.getHeight());
			view.setField(new_board);
			view.displayErrorMessage("change the size of window to update new board");
			
		} else if (e.isClearEvent()) {
			ClearEvent clear_grid = (ClearEvent) e;
			for (Spot s: clear_grid.getField()) {
				s.setSpotColor(new Color(0.8f, 0.8f, 0.8f));
			}
			
		} else if (e.isRandomizeEvent()) {
			RandomizeEvent randomize_event = (RandomizeEvent) e;
			
			for (Spot s: randomize_event.getField()) {
				Random r = new Random();
				int test = r.nextInt(2);
				if (test == 0) {
					s.setSpotColor(new Color(0.8f, 0.8f, 0.8f));
				} else {
					s.setSpotColor(Color.BLACK);
				}
			}
			
		} else if (e.isAdvanceEvent()) {
			AdvanceEvent advance_event = (AdvanceEvent) e;
			testAdvance(advance_event.getField());
		}	
	}

	@Override
	public void update(ConwaysGameOfLifeModel model) {

	}
	
	public void testAdvance(JSpotBoard copy_board) {
		
		int[][] living_count = new int[copy_board.getSpotWidth()][copy_board.getSpotHeight()];
		
		for (Spot s: copy_board) {
			
			int x_position = s.getSpotX();
			int y_position = s.getSpotY();
			
			if (x_position == 0) { 
				if (y_position == 0) {
					// top left corner
					for (int i = x_position; i <= x_position + 1; i++) {
						for (int j = y_position; j <= y_position + 1; j++) {
							if (i == x_position && j == y_position) {
								continue;
							} else if (copy_board.getSpotAt(i, j).getSpotColor() == Color.BLACK) {
								living_count[x_position][y_position]++;
							}
						}
					}
				} else if (y_position == copy_board.getSpotHeight() - 1) {
					// bottom left corner
					for (int i = x_position; i <= x_position + 1; i++) {
						for (int j = y_position-1; j <= y_position; j++) {
							if (i == x_position && j == y_position) {
								continue;
							} else if (copy_board.getSpotAt(i, j).getSpotColor() == Color.BLACK) {
								living_count[x_position][y_position]++;
							}
						}
					}
				} else {
					// left side
					for (int i = x_position; i <= x_position + 1; i++) {
						for (int j = y_position-1; j <= y_position + 1; j++) {
							if (i == x_position && j == y_position) {
								continue;
							} else if (copy_board.getSpotAt(i, j).getSpotColor() == Color.BLACK) {
								living_count[x_position][y_position]++;
							}
						}
					}
				}
			} else if (y_position == 0) {
				if (x_position == copy_board.getSpotWidth() -1 ) {
					// top right corner
					for (int i = x_position - 1; i <= x_position; i++) {
						for (int j = y_position; j <= y_position + 1; j++) {
							if (i == x_position && j == y_position) {
								continue;
							} else if (copy_board.getSpotAt(i, j).getSpotColor() == Color.BLACK) {
								living_count[x_position][y_position]++;
							}
						}
					}
				} else {
					// top side
					for (int i = x_position - 1; i <= x_position + 1; i++) {
						for (int j = y_position; j <= y_position + 1; j++) {
							if (i == x_position && j == y_position) {
								continue;
							} else if (copy_board.getSpotAt(i, j).getSpotColor() == Color.BLACK) {
								living_count[x_position][y_position]++;
							}
						}
					}
				}
			} else if (x_position == copy_board.getSpotWidth() - 1) {
				if (y_position == copy_board.getSpotHeight() - 1) {
					// bottom right corner
					for (int i = x_position - 1; i <= x_position; i++) {
						for (int j = y_position - 1; j <= y_position; j++) {
							if (i == x_position && j == y_position) {
								continue;
							} else if (copy_board.getSpotAt(i, j).getSpotColor() == Color.BLACK) {
								living_count[x_position][y_position]++;
							}
						}
					}
				} else {
					// right side
					for (int i = x_position - 1; i <= x_position; i++) {
						for (int j = y_position - 1; j <= y_position + 1; j++) {
							if (i == x_position && j == y_position) {
								continue;
							} else if (copy_board.getSpotAt(i, j).getSpotColor() == Color.BLACK) {
								living_count[x_position][y_position]++;
							}
						}
					}
				}
			} else if (y_position == copy_board.getSpotHeight() - 1) {
				// bottom side
				for (int i = x_position - 1; i <= x_position + 1; i++) {
					for (int j = y_position - 1; j <= y_position; j++) {
						if (i == x_position && j == y_position) {
							continue;
						} else if (copy_board.getSpotAt(i, j).getSpotColor() == Color.BLACK) {
							living_count[x_position][y_position]++;
						}
					}
				}
			} else {
				// all middle cells
				for (int i = x_position - 1; i <= x_position + 1; i++) {
					for (int j = y_position - 1; j <= y_position + 1; j++) {
						if (i == x_position && j == y_position) {
							continue;
						} else if (copy_board.getSpotAt(i, j).getSpotColor() == Color.BLACK) {
							living_count[x_position][y_position]++;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < copy_board.getSpotWidth(); i++) {
			for (int j = 0; j < copy_board.getSpotHeight(); j++) {
				if (copy_board.getSpotAt(i, j).getSpotColor() == Color.BLACK) {
					if (living_count[i][j] < 2 || living_count[i][j] > 3) {
						copy_board.getSpotAt(i, j).setSpotColor(DEFAULT_SPOT_COLOR);
					}
				} 
				
				if (copy_board.getSpotAt(i, j).getSpotColor().equals(DEFAULT_SPOT_COLOR)){
					if (living_count[i][j] == 3) {
						copy_board.getSpotAt(i, j).setSpotColor(Color.BLACK);
					}
				} 
			}
		}		
	}
}	
