package a7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import a7.TicTacToeWidget.Player;

public class ConnectFourWidget extends JPanel implements ActionListener, SpotListener {
	private enum Player {RED, BLACK};
	
	private JSpotBoard2 _board;		/* SpotBoard playing area. */
	private JLabel _message;		/* Label for messages. */
	private boolean _game_won;		/* Indicates if games was been won already.*/
	private Player _next_to_play;	/* Identifies who has next turn. */
	private int[] index;
	public ConnectFourWidget() {
		
		/* Create SpotBoard and message label. */
		
		_board = new JSpotBoard2(7,6);
		_message = new JLabel();
		
		/* Set layout and place SpotBoard at center. */
		
		setLayout(new BorderLayout());
		add(_board, BorderLayout.CENTER);

		/* Create subpanel for message area and reset button. */
		
		JPanel reset_message_panel = new JPanel();
		reset_message_panel.setLayout(new BorderLayout());

		/* Reset button. Add ourselves as the action listener. */
		
		JButton reset_button = new JButton("Restart");
		reset_button.addActionListener(this);
		reset_message_panel.add(reset_button, BorderLayout.EAST);
		reset_message_panel.add(_message, BorderLayout.CENTER);

		/* Add subpanel in south area of layout. */
		
		add(reset_message_panel, BorderLayout.SOUTH);

		/* Add ourselves as a spot listener for all of the
		 * spots on the spot board.
		 */
		_board.addSpotListener(this);

		/* Reset game. */
		resetGame();
	}

	/* resetGame
	 * 
	 * Resets the game by clearing all the spots on the board,
	 * picking a new secret spot, resetting game status fields, 
	 * and displaying start message.
	 * 
	 */

	private void resetGame() {
		/* Clear all spots on board. Uses the fact that SpotBoard
		 * implements Iterable<Spot> to do this in a for-each loop.
		 */

		for (Spot s : _board) {
			s.clearSpot();
			s.unhighlightSpot();
		}
		
		index =   new int[] {0,0,0,0,0,0,0};
		
		_game_won = false;
		_next_to_play = Player.RED;		
		
		_message.setText("Welcome to the ConnectFour game. RED to play");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* Handles reset game button. Simply reset the game. */
				resetGame();
	}

	/* Implementation of SpotListener below. Implements game
	 * logic as responses to enter/exit/click on spots.
	 */
	@Override
	public void spotClicked(Spot s) {
		if (index[s.getSpotX()] > 5) {
			return;
		}

		if (_game_won) {
			return;
		}
		
		
		String player_name = null;
		String next_player_name = null;
		Color player_color = null;
		
		if (_next_to_play == Player.RED) {
			player_color = Color.RED;
			player_name = "red";
			next_player_name = "black";
			_next_to_play = Player.BLACK;
		} else {
			player_color = Color.BLACK;
			player_name = "black";
			next_player_name = "red";
			_next_to_play = Player.RED;			
		}
			

		_board.getSpotAt(s.getSpotX(), 5-index[s.getSpotX()]).setSpotColor(player_color);
		_board.getSpotAt(s.getSpotX(), 5-index[s.getSpotX()]).toggleSpot();
		if (horizontalWin(s.getSpotX(),5-index[s.getSpotX()])||verticalWin(s.getSpotX(),5-index[s.getSpotX()])
				||topRightWin(s.getSpotX(),5-index[s.getSpotX()])||leftRightWin(s.getSpotX(),5-index[s.getSpotX()])) {
			_game_won = true;
		}
		boolean noneLeft = true;
		for (Spot ss : _board) {
			if (ss.isEmpty()) {
				noneLeft = false;
			}
		}
		
		index[s.getSpotX()]++;
		
		// add method to determine winning state
		 
		
			if (_game_won)  {
				_message.setText(player_name + " wins!! " + " end the game.");
			} else if (noneLeft){
				_message.setText(" draw game");
			} else {
				_message.setText(" Game continues. " + next_player_name + " to play.");
			}
		
	}
	

	@Override
	public void spotEntered(Spot s) {
		/* Highlight spot if game still going on. */
		
		if (_game_won || s.isEmpty() == false) {
			return;
		}
		for (int i = 0; i < 6; i++) {
			
			if (_board.getSpotAt(s.getSpotX(), i).isEmpty()) {
				_board.getSpotAt(s.getSpotX(), i).highlightSpot();
			}
		}
		
	}

	@Override
	public void spotExited(Spot s) {
			if (_game_won) {
				return;
			}
			for (int i = 0; i < 6; i++) {
				_board.getSpotAt(s.getSpotX(), i).unhighlightSpot();;
			}
		
	}
	public boolean determine(int a, int b, int c, int d,int e,int f,int g, int h) {
		if (_board.getSpotAt(a, b).isEmpty() ||_board.getSpotAt(c, d).isEmpty()||_board.getSpotAt(e, f).isEmpty()||_board.getSpotAt(g, h).isEmpty()) {
			return false;
		}
		if (_board.getSpotAt(a, b).getSpotColor() == _board.getSpotAt(c, d).getSpotColor()
			&&_board.getSpotAt(a, b).getSpotColor() == _board.getSpotAt(e, f).getSpotColor()
			&&_board.getSpotAt(a, b).getSpotColor() == _board.getSpotAt(g, h).getSpotColor()
			) {
			return true;
		}
		return false;
	}
	public void high(int a, int b, int c, int d,int e,int f,int g, int h) {
		for (Spot ss : _board) {
			ss.unhighlightSpot();
		}
		_board.getSpotAt(a, b).highlightSpot();
		_board.getSpotAt(c, d).highlightSpot();
		_board.getSpotAt(e, f).highlightSpot();
		_board.getSpotAt(g, h).highlightSpot();
	}
	public boolean horizontalWin(int column, int row) {
		boolean t = false;
		
			if (determine(0,row,1,row,2,row,3,row)) {
				t = true;
				high(0,row,1,row,2,row,3,row);
				return t;
			}
		
		
			if (determine(4,row,1,row,2,row,3,row) ) {
				t = true;
				high(4,row,1,row,2,row,3,row);
				return t;
			}
		
		
			if (determine(4,row,5,row,2,row,3,row)) {
				t = true;
				high(4,row,5,row,2,row,3,row);
				return t;
			}
		
		
			if (determine(4,row,5,row,6,row,3,row)) {
				t = true;
				high(4,row,5,row,6,row,3,row);
				return t;
			}
		
		return false;
		
	}

	public boolean verticalWin(int column, int row) {
		if (row > 2) {
			return false;
		} else {
			if (determine(column,0,column,1,column,2,column,3)) {
				high(column,0,column,1,column,2,column,3);
				return true;
			}
		
			if (determine(column,4,column,1,column,2,column,3)) {
				high(column,4,column,1,column,2,column,3);
				return true;
			}
		
			if (determine(column,4,column,5,column,2,column,3)) {
				
				high(column,4,column,5,column,2,column,3);
				return true;
			}
		}
		return false;
	}
	public boolean topRightWin(int col, int row) {
		
		if (col + row < 3 || col + row >=9) {
			return false;
		}
		
			if (determine(3,0,2,1,1,2,0,3)) {
				
				high(3,0,2,1,1,2,0,3);
				return true;
			}
		
		
			if (determine(4,0,3,1,2,2,1,3)) {
				high(4,0,3,1,2,2,1,3);
				return true;
			} else if (determine(0,4,3,1,2,2,1,3)) {
				high(0,4,3,1,2,2,1,3);
				return true;
			}
		
		
			if (determine(5,0,4,1,3,2,2,3)) {
				high(5,0,4,1,3,2,2,3);
				return true;
			} else if(determine(4,1,3,2,2,3,1,4)) {
				high(4,1,3,2,2,3,1,4);
				return true;
			} else if(determine(3,2,2,3,1,4,0,5)) {
				high(3,2,2,3,1,4,0,5);
				return true;
			}
		
		
			if (determine(6,0,5,1,4,2,3,3)) {
				high(6,0,5,1,4,2,3,3);
				return true;
			} else if(determine(5,1,4,2,3,3,2,4)) {
				high(5,1,4,2,3,3,2,4);
				return true;
			} else if (determine(4,2,3,3,2,4,1,5)) {
				high(4,2,3,3,2,4,1,5);
				return true;
			}
		
		
			if (determine(6,1,5,2,4,3,3,4)) {
				high(6,1,5,2,4,3,3,4);
				return true;
			} else if (determine(5,2,4,3,3,4,2,5)) {
				high(5,2,4,3,3,4,2,5);
				return true;
			}
		
		
			if (determine(6,2,5,3,4,4,3,5)) {
				high(6,2,5,3,4,4,3,5);
				return true;
			}
		
		return false;
	}
	public boolean leftRightWin(int col, int row) {
		if (col + 3 <= row||col - 4 >=row) {
			return false;
		}
		
			if (determine(3,0,4,1,5,2,6,3)) {
				
				high(3,0,4,1,5,2,6,3);
				return true;
			}
		
		
			if (determine(2,0,3,1,4,2,5,3)) {
				high(2,0,3,1,4,2,5,3);
				return true;
			} else if (determine(3,1,4,2,5,3,6,4)) {
				high(3,1,4,2,5,3,6,4);
				return true;
			}
		
		
			if (determine(1,0,2,1,3,2,4,3)) {
				
				high(1,0,2,1,3,2,4,3);
				return true;
			} else if(determine(2,1,3,2,4,3,5,4)) {
				high(2,1,3,2,4,3,5,4);
				return true;
			} else if (determine(3,2,4,3,5,4,6,5)) {
				high(3,2,4,3,5,4,6,5);
				return true;
			}
		
		
			if (determine(0,0,1,1,2,2,3,3)) {
				high(0,0,1,1,2,2,3,3);
				return true;
			} else if(determine(1,1,2,2,3,3,4,4)) {
				high(1,1,2,2,3,3,4,4);
				return true;
			} else if(determine(2,2,3,3,4,4,5,5)) {
				high(2,2,3,3,4,4,5,5);
				return true;
			}
		
		
			if (determine(0,1,1,2,2,3,3,4)) {
				high(0,1,1,2,2,3,3,4);
				return true;
			} else if(determine(1,2,2,3,3,4,4,5)) {
				high(1,2,2,3,3,4,4,5);
				return true;
			}
		
		
			if (determine(0,2,1,3,2,4,3,5)) {
				high(0,2,1,3,2,4,3,5);
				return true;
			}
		
		return false;
	}
}
