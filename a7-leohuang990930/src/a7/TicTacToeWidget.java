package a7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import a7.ExampleWidget.Player;

public class TicTacToeWidget extends JPanel implements ActionListener, SpotListener {
	public enum Player {WHITE, BLACK};
	
	private JSpotBoard1 _board;		/* SpotBoard playing area. */
	private JLabel _message;		/* Label for messages. */
	private boolean _game_won;		/* Indicates if games was been won already.*/
	private Player _next_to_play;	/* Identifies who has next turn. */
	
	public TicTacToeWidget() {
		
		/* Create SpotBoard and message label. */
		
		_board = new JSpotBoard1(3,3);
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
		}

		/* Reset the background of the old secret spot.
		 * Check _secret_spot for null first because call to 
		 * resetGame from constructor won't have a secret spot 
		 * chosen yet.
		 */
		
		/* Pick a new secret spot. */

		
		/* Reset game won and next to play fields */
		_game_won = false;
		_next_to_play = Player.WHITE;		
		
		/* Display game start message. */
		
		_message.setText("Welcome to the TicTacToeGame. White to play");
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
		if (s.isEmpty()==false) {
			return;
		}
		/* If game already won, do nothing. */
		if (_game_won) {
			return;
		}
		
		
		/* Set up player and next player name strings,
		 * and player color as local variables to
		 * be used later.
		 */
		
		String player_name = null;
		String next_player_name = null;
		Color player_color = null;
		
		if (_next_to_play == Player.WHITE) {
			player_color = Color.WHITE;
			player_name = "white";
			next_player_name = "black";
			_next_to_play = Player.BLACK;
		} else {
			player_color = Color.BLACK;
			player_name = "black";
			next_player_name = "white";
			_next_to_play = Player.WHITE;			
		}
				
		
		/* Set color of spot clicked and toggle. */
		s.setSpotColor(player_color);
		s.toggleSpot();
		boolean oneEmpty = false;
		for (int x = 0; x < 3; x++) {
			for (int y=0; y< 3; y++) {
				if (_board.getSpotAt(x, y).isEmpty()) {
					oneEmpty = true;
				}
			}
		}
		/* Check if spot clicked is secret spot.
		 * If so, mark game as won and update background
		 * of spot to show that it was the secret spot.
		 */
		
		_game_won = determine();
		/* Update the message depending on what happened.
		 * If spot is empty, then we must have just cleared the spot. Update message accordingly.
		 * If spot is not empty and the game is won, we must have
		 * just won. Calculate score and display as part of game won message.
		 * If spot is not empty and the game is not won, update message to
		 * report spot coordinates and indicate whose turn is next.
		 */
		
		
			if (_game_won)  {
				_message.setText(player_name + " wins!! " + " end the game.");
			} else if (oneEmpty == false){
				_message.setText(" draw game");
			} else {
				_message.setText("Player: " + player_name + " chooses " + s.getCoordString() + ". Game continues. " + next_player_name + " to play.");
			}
		
	}

	@Override
	public void spotEntered(Spot s) {
		/* Highlight spot if game still going on. */
		
		if (_game_won || s.isEmpty() == false) {
			return;
		}
		s.highlightSpot();
	}

	@Override
	public void spotExited(Spot s) {
		/* Unhighlight spot. */
		
		s.unhighlightSpot();
	}
	public boolean determine() {
		if (_board.getSpotAt(0, 0).isEmpty() || _board.getSpotAt(1, 1).isEmpty() || _board.getSpotAt(2, 2).isEmpty()) {
			
		} else {
			if ((_board.getSpotAt(0, 0).getSpotColor() == _board.getSpotAt(1, 1).getSpotColor()) && _board.getSpotAt(1, 1).getSpotColor() == _board.getSpotAt(2, 2).getSpotColor()) {
				return true;
			}
		}
		
		
		if (_board.getSpotAt(0, 0).isEmpty() || _board.getSpotAt(0, 1).isEmpty() || _board.getSpotAt(0, 2).isEmpty()) {
			
		} else {
			if ((_board.getSpotAt(0, 0).getSpotColor() == _board.getSpotAt(0, 1).getSpotColor()) && _board.getSpotAt(0, 1).getSpotColor() == _board.getSpotAt(0, 2).getSpotColor()) {
				return true;
			}
		}
		
		
		if (_board.getSpotAt(1, 0).isEmpty() || _board.getSpotAt(1, 1).isEmpty() || _board.getSpotAt(1, 2).isEmpty()) {
			
		} else {
			if ((_board.getSpotAt(1, 0).getSpotColor() == _board.getSpotAt(1, 1).getSpotColor()) && _board.getSpotAt(1, 1).getSpotColor() == _board.getSpotAt(1, 2).getSpotColor()) {
				return true;
			}
		}
		
		
		if (_board.getSpotAt(2, 0).isEmpty() || _board.getSpotAt(2, 1).isEmpty() || _board.getSpotAt(2, 2).isEmpty()) {
			
		} else {
			if ((_board.getSpotAt(2, 0).getSpotColor() == _board.getSpotAt(2, 1).getSpotColor()) && _board.getSpotAt(2, 1).getSpotColor() == _board.getSpotAt(2, 2).getSpotColor()) {
				return true;
			}
		}
		
		
		if (_board.getSpotAt(2, 0).isEmpty() || _board.getSpotAt(1, 1).isEmpty() || _board.getSpotAt(0, 2).isEmpty()) {
			
		} else {
			if ((_board.getSpotAt(2, 0).getSpotColor() == _board.getSpotAt(1, 1).getSpotColor()) && _board.getSpotAt(1, 1).getSpotColor() == _board.getSpotAt(0, 2).getSpotColor()) {
				return true;
			}
		}
		
		
		if (_board.getSpotAt(0, 0).isEmpty() || _board.getSpotAt(1, 0).isEmpty() || _board.getSpotAt(2, 0).isEmpty()) {
			
		} else {
			if ((_board.getSpotAt(0, 0).getSpotColor() == _board.getSpotAt(1, 0).getSpotColor()) && _board.getSpotAt(1, 0).getSpotColor() == _board.getSpotAt(2, 0).getSpotColor()) {
				return true;
			}
		}
		
		
		if (_board.getSpotAt(0, 1).isEmpty() || _board.getSpotAt(1, 1).isEmpty() || _board.getSpotAt(2, 1).isEmpty()) {
			
		} else {
			if ((_board.getSpotAt(0, 1).getSpotColor() == _board.getSpotAt(1, 1).getSpotColor()) && _board.getSpotAt(1, 1).getSpotColor() == _board.getSpotAt(2, 1).getSpotColor()) {
				return true;
			}
		}
		
		
		if (_board.getSpotAt(0, 2).isEmpty() || _board.getSpotAt(1, 2).isEmpty() || _board.getSpotAt(2, 2).isEmpty()) {
			
		} else {
			if ((_board.getSpotAt(0, 2).getSpotColor() == _board.getSpotAt(1, 2).getSpotColor()) && _board.getSpotAt(1, 2).getSpotColor() == _board.getSpotAt(2, 2).getSpotColor()) {
				return true;
			}
		}
		return false;
	}
}
