package a7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class OthelloWidget extends JPanel implements ActionListener, SpotListener {
private enum Player {BLACK, WHITE};
	
	private JSpotBoard3 _board;		/* SpotBoard playing area. */
	private JLabel _message;		/* Label for messages. */
	private boolean _game_finished;		/* Indicates if games was been won already.*/
	private Player _next_to_play;	/* Identifies who has next turn. */
	private ArrayList<Spot> list;

	public OthelloWidget() {
		
		/* Create SpotBoard and message label. */
		
		_board = new JSpotBoard3(8,8);
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
			s.setSpotColor(Color.GREEN);
		}
		
		_game_finished = false;
		_next_to_play = Player.BLACK;
		_board.getSpotAt(4, 3).setSpotColor(Color.BLACK);
		_board.getSpotAt(4, 3).toggleSpot();
		_board.getSpotAt(3, 4).setSpotColor(Color.BLACK);
		_board.getSpotAt(3, 4).toggleSpot();
		_board.getSpotAt(4, 4).setSpotColor(Color.WHITE);
		_board.getSpotAt(4, 4).toggleSpot();
		_board.getSpotAt(3, 3).setSpotColor(Color.WHITE);
		_board.getSpotAt(3, 3).toggleSpot();
		list = new ArrayList<Spot>();
		_message.setText("Welcome to the Othello game. black to play");
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
		if (s.isEmpty() == false) {
			return;
		}
		
		if (_game_finished) {
			return;
		}
		Color player_color;
		if (_next_to_play == Player.BLACK) {
			player_color = Color.BLACK;
		} else {
			player_color = Color.WHITE;
		}
		north(s,player_color);
		south(s,player_color);
		west(s,player_color);
		east(s,player_color);
		eastNorth(s,player_color);
		eastSouth(s,player_color);
		westNorth(s,player_color);
		westSouth(s,player_color);
		if (list.size() == 0) {
			return;
		}
		for(Spot ss: list) {
			ss.setSpotColor(player_color);
		}
		list = new ArrayList<Spot>();
		s.setSpotColor(player_color);
		s.toggleSpot();
		String next_player_name;
		Color nextColor = null;
		if (a()) {
			_game_finished = true;
		}
		if (_next_to_play == Player.BLACK) {
			_next_to_play = Player.WHITE;
			next_player_name = "white";
			nextColor = Color.WHITE;
		} else {
			_next_to_play = Player.BLACK;
			next_player_name = "black";
			nextColor = Color.BLACK;
		}
		
		boolean oneEmpty = false;
		for (Spot ns : _board) {
			if (ns.isEmpty()) {
				oneEmpty = true;
				break;
			}
		}
		if (oneEmpty == false) {
			_game_finished = true;
		}
		
			if (_game_finished)  {
				int white = 0;int black = 0; Player winner; String name;
				for (Spot ss : _board) {
					if (!ss.isEmpty()) {
						if (ss.getSpotColor() == Color.WHITE) {
							white++;
						} else {
							black++;
						}
					}
				}
				if (white == black) {
					_message.setText("Game over! It's a draw. Score: " + white+":"+black);
				} else {
					if (white > black) {
						winner = Player.WHITE;
						name = "white";
					} else {
						winner = Player.BLACK;
						name = "black";
					}
					_message.setText("Game over! " + name + " wins!! " + " Score:" + white+":" + black);
				}
				
			} else {
				if (!oneValid(nextColor)) {
					if (_next_to_play == Player.BLACK) {
						_next_to_play = Player.WHITE;
						next_player_name = "white";
					} else {
						_next_to_play = Player.BLACK;
						next_player_name = "black";
					}
				}
				_message.setText(" Game continues. " + next_player_name + " to play.");
			}
		
	}
	

	@Override
	public void spotEntered(Spot s) {
		/* Highlight spot if game still going on. */
		
		if (_game_finished || s.isEmpty() == false) {
			return;
		}
		Color player_color;
		if  (_next_to_play == Player.BLACK) {
			player_color = Color.BLACK;
		} else {
			player_color = Color.WHITE;
		}
		north(s,player_color);
		south(s,player_color);
		west(s,player_color);
		east(s,player_color);
		eastNorth(s,player_color);
		eastSouth(s,player_color);
		westNorth(s,player_color);
		westSouth(s,player_color);
		ArrayList<Spot> temp; temp = list; list = new ArrayList<Spot>();
		if (temp.size() !=0) {
			s.highlightSpot();
		}
		
	}
	public boolean oneValid(Color c) {
		boolean b= false;
		for (Spot s: _board) {
			if (s.isEmpty()) {
				north(s,c);
				south(s,c);
				west(s,c);
				east(s,c);
				eastNorth(s,c);
				eastSouth(s,c);
				westNorth(s,c);
				westSouth(s,c);
				if (list.size() !=0) {
					b = true;
				}
			}
		}
		return b;
	}
	@Override
	public void spotExited(Spot s) {
		s.unhighlightSpot();
	}
	
	public void north(Spot s, Color c) {
		if (s.getSpotY() < 2) {
			return;
		}
		
		if (_board.getSpotAt(s.getSpotX(), s.getSpotY()-1).getSpotColor() == reverse(c)) {
			int index = 1;
			while (_board.getSpotAt(s.getSpotX(), s.getSpotY()-index).getSpotColor() == reverse(c)) {
				index++;
				if (s.getSpotY()-index == 0) {
					break;
				}
			}
			if (_board.getSpotAt(s.getSpotX(), s.getSpotY()-index).getSpotColor() == c) {
				for (int i = 1; i < index; i++) {
					list.add(_board.getSpotAt(s.getSpotX(), s.getSpotY()-i));
				}
			}
		}
		
	}
	public void south(Spot s, Color c) {
		if (s.getSpotY() > 5) {
			return;
		}
		
		if (_board.getSpotAt(s.getSpotX(), s.getSpotY()+1).getSpotColor() == reverse(c)) {
			int index = 1;
			while (_board.getSpotAt(s.getSpotX(), s.getSpotY()+index).getSpotColor() == reverse(c)) {
				index++;
				if (s.getSpotY()+index == 7) {
					break;
				}
			}
			if (_board.getSpotAt(s.getSpotX(), s.getSpotY()+index).getSpotColor() == c) {
				for (int i = 1; i < index; i++) {
					list.add(_board.getSpotAt(s.getSpotX(), s.getSpotY()+i));
				}
			}
		}
	}
	public void east(Spot s, Color c) {
		if (s.getSpotX() > 5) {
			return;
		}
		
		if (_board.getSpotAt(s.getSpotX()+1, s.getSpotY()).getSpotColor() == reverse(c)) {
			int index = 1;
			while (_board.getSpotAt(s.getSpotX()+index, s.getSpotY()).getSpotColor() == reverse(c)) {
				index++;
				if (s.getSpotX()+index == 7) {
					break;
				}
			}
			if (_board.getSpotAt(s.getSpotX()+index, s.getSpotY()).getSpotColor() == c) {
				for (int i = 1; i < index; i++) {
					list.add(_board.getSpotAt(s.getSpotX()+i, s.getSpotY()));
				}
			}
		}
	}
	public void west(Spot s, Color c) {
		if (s.getSpotX() < 2) {
			return;
		}
		
		if (_board.getSpotAt(s.getSpotX()-1, s.getSpotY()).getSpotColor() == reverse(c)) {
			int index = 1;
			while (_board.getSpotAt(s.getSpotX()-index, s.getSpotY()).getSpotColor() == reverse(c)) {
				index++;
				if (s.getSpotX()-index == 0) {
					break;
				}
			}
			if (_board.getSpotAt(s.getSpotX()-index, s.getSpotY()).getSpotColor() == c) {
				for (int i = 1; i < index; i++) {
					list.add(_board.getSpotAt(s.getSpotX()-i, s.getSpotY()));
				}
			}
		}
	}
	public void westNorth(Spot s, Color c) {
		if (s.getSpotX()<2||s.getSpotY()<2) {
			return;
		}
		if (_board.getSpotAt(s.getSpotX()-1, s.getSpotY()-1).getSpotColor() == reverse(c)) {
			int index = 1;
			while (_board.getSpotAt(s.getSpotX()-index, s.getSpotY()-index).getSpotColor() == reverse(c)) {
				index++;
				if (s.getSpotX()-index == 0||s.getSpotY()-index==0) {
					break;
				}
			}
			if (_board.getSpotAt(s.getSpotX()-index, s.getSpotY()-index).getSpotColor() == c) {
				for (int i = 1; i < index; i++) {
					list.add(_board.getSpotAt(s.getSpotX()-i, s.getSpotY()-i));
				}
			}
		}
		
	}
	public void westSouth(Spot s, Color c) {
		if (s.getSpotX()>5||s.getSpotY()<2) {
			return;
		}
		if (_board.getSpotAt(s.getSpotX()+1, s.getSpotY()-1).getSpotColor() == reverse(c)) {
			int index = 1;
			while (_board.getSpotAt(s.getSpotX()+index, s.getSpotY()-index).getSpotColor() == reverse(c)) {
				index++;
				if (s.getSpotX()+index == 7||s.getSpotY()-index==0) {
					break;
				}
			}
			if (_board.getSpotAt(s.getSpotX()+index, s.getSpotY()-index).getSpotColor() == c) {
				for (int i = 1; i < index; i++) {
					list.add(_board.getSpotAt(s.getSpotX()+i, s.getSpotY()-i));
				}
			}
		}
	}
	public void eastNorth(Spot s, Color c) {
		if (s.getSpotX()<2||s.getSpotY()>5) {
			return;
		}
		if (_board.getSpotAt(s.getSpotX()-1, s.getSpotY()+1).getSpotColor() == reverse(c)) {
			int index = 1;
			while (_board.getSpotAt(s.getSpotX()-index, s.getSpotY()+index).getSpotColor() == reverse(c)) {
				index++;
				if (s.getSpotX()-index == 0||s.getSpotY()+index==7) {
					break;
				}
			}
			if (_board.getSpotAt(s.getSpotX()-index, s.getSpotY()+index).getSpotColor() == c) {
				for (int i = 1; i < index; i++) {
					list.add(_board.getSpotAt(s.getSpotX()-i, s.getSpotY()+i));
				}
			}
		}
	}
	public void eastSouth(Spot s, Color c) {
		if (s.getSpotX()>5||s.getSpotY()>5) {
			return;
		}
		if (_board.getSpotAt(s.getSpotX()+1, s.getSpotY()+1).getSpotColor() == reverse(c)) {
			int index = 1;
			while (_board.getSpotAt(s.getSpotX()+index, s.getSpotY()+index).getSpotColor() == reverse(c)) {
				index++;
				if (s.getSpotX()+index == 7||s.getSpotY()+index==7) {
					break;
				}
			}
			if (_board.getSpotAt(s.getSpotX()+index, s.getSpotY()+index).getSpotColor() == c) {
				for (int i = 1; i < index; i++) {
					list.add(_board.getSpotAt(s.getSpotX()+i, s.getSpotY()+i));
				}
			}
		}
	}
	public Color reverse(Color c) {
		if (c == Color.BLACK) {
			return Color.WHITE;
		}
		if (c == Color.WHITE) {
			return Color.BLACK;
		}
		return null;
	}
	public boolean a() {
		boolean a = false;
		boolean b = false; 
		for (Spot s: _board) {
			if (s.isEmpty()) {
				north(s,Color.BLACK);
				south(s,Color.BLACK);
				west(s,Color.BLACK);
				east(s,Color.BLACK);
				eastNorth(s,Color.BLACK);
				eastSouth(s,Color.BLACK);
				westNorth(s,Color.BLACK);
				westSouth(s,Color.BLACK);
				if (list.size() !=0) {
					b = true;
				}
				list.clear();
			}
		}
		for (Spot s: _board) {
			if (s.isEmpty()) {
				north(s,Color.WHITE);
				south(s,Color.WHITE);
				west(s,Color.WHITE);
				east(s,Color.WHITE);
				eastNorth(s,Color.WHITE);
				eastSouth(s,Color.WHITE);
				westNorth(s,Color.WHITE);
				westSouth(s,Color.WHITE);
				if (list.size() !=0) {
					a = true;
				}
				list.clear();
			}
		}
		if (!a&&!b) {
			return true;
		} else {
			return false;
		}
	}
}
