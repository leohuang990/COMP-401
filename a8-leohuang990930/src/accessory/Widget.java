package accessory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Widget extends JPanel implements ActionListener, SpotListener,Runnable {

	private JSpotBoard _board;		/* SpotBoard playing area. */
	private JLabel _message;
	private boolean start;
	private boolean torus_mode = false;
	private int low_birth;private int high_birth;private int low_survive;private int high_survive;
	private Timer time = new Timer();
	public Widget(int a, int b) {
		
		/* Create SpotBoard and message label. */
		
		_board = new JSpotBoard(a,b);
		_message = new JLabel();
		
		start = false;
		/* Set layout and place SpotBoard at center. */
		
		setLayout(new BorderLayout());
		add(_board, BorderLayout.CENTER);


		low_birth = 3;
		high_birth = 3;
		low_survive = 2;
		high_survive = 3;


		JPanel torus = new JPanel();
		torus.setLayout(new BorderLayout());
		JButton torus_mode = new JButton("Torus mode");
		torus_mode.addActionListener(new Action1());
		
		torus.add(_message, BorderLayout.WEST);
		
		JPanel random = new JPanel();
		
		JButton random_fill = new JButton("random fill");
		random_fill.addActionListener(new Action2());
		random.add(random_fill, BorderLayout.WEST);

		JButton reset_button = new JButton("Clear");
		reset_button.addActionListener(this);
		random.add(reset_button, BorderLayout.SOUTH);
		
		JButton advance = new JButton("advance");
		advance.addActionListener(new Action3());
		random.add(advance, BorderLayout.SOUTH);
		random.add(torus_mode, BorderLayout.EAST);
		JButton start = new JButton("start/stop");
		start.addActionListener(new Action4());
		random.add(start, BorderLayout.SOUTH);
		
		JButton set = new JButton("custom thresholds");
		set.addActionListener(new Action5());
		random.add(set, BorderLayout.SOUTH);

		add(torus, BorderLayout.NORTH);
		add(random, BorderLayout.SOUTH);

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
	
		
		/* Display game start message. */
		
		_message.setText("   Welcome. Threshold: low birth("+low_birth+ "), high birth(" +high_birth+ "), low survive(" + low_survive + "), high survive(" +high_survive + ") Torus mode is off.");
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
		s.switch1();
		if (s.isDead() == false) {
			s.setSpot();
		} else {
			s.clearSpot();
		}
		

	}
	public void run() {
		  
			  if (!torus_mode) {
				  for (Spot s : _board) {
						s.determine(low_birth,high_birth,low_survive,high_survive);
				  }
			  } else {
				  for (Spot s : _board) {
						s.determine1(low_birth,high_birth,low_survive,high_survive);
				  }
			  }
			  for (Spot s : _board) {
				  s.goTo();
				 if (!s.isDead()) {
					 s.setSpot();
				 } else {
					 s.clearSpot();
				 }
			  }
			  
		  
    }
	@Override
	public void spotEntered(Spot s) {
		/* Highlight spot if game still going on. */
		
		
		s.highlightSpot();
	}

	@Override
	public void spotExited(Spot s) {
		/* Unhighlight spot. */
		
		s.unhighlightSpot();
	}
	class Action1 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     
		    torus_mode = !torus_mode;   
		    if (torus_mode) {
		    	_message.setText("   Welcome. Threshold: low birth("+low_birth+ "), high birth(" +high_birth+ "), low survive(" + low_survive + "), high survive(" +high_survive + ") Torus mode is on.");
		    	
		    } else {
		    	_message.setText("   Welcome. Threshold: low birth("+low_birth+ "), high birth(" +high_birth+ "), low survive(" + low_survive + "), high survive(" +high_survive + ") Torus mode is off.");
		    	
		    }
		  }
	}  
	class Action2 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     
			  
			  for (Spot s : _board) {
					s.clearSpot();
				  if (Math.random()<0.5) {
						s.setSpotColor(Color.WHITE);
						s.setSpot();
					}
				}
		  }
	} 
 	class Action3 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     
			  if (!torus_mode) {
				  for (Spot s : _board) {
						s.determine(low_birth,high_birth,low_survive,high_survive);
				  }
			  } else {
				  for (Spot s : _board) {
						s.determine1(low_birth,high_birth,low_survive,high_survive);
				  }
			  }
			  for (Spot s : _board) {
				  s.goTo();
				 if (!s.isDead()) {
					 s.setSpot();
				 } else {
					 s.clearSpot();
				 }
			  }
			  
		  }
	} 
 	class Action4 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     
			  start = !start;
			  if (!start) {
				  time.cancel();
				  return;
				  
			  }
			  time = new Timer();
			  ScheduledTask st = new ScheduledTask();
			  time.schedule(st, 0, 800);
			  
			  
			  
			  
		  }
	}
 	class Action5 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     
			  low_birth = Integer.parseInt(JOptionPane.showInputDialog("Enter the low_birth(positive integer only)"));
			  high_birth = Integer.parseInt(JOptionPane.showInputDialog("Enter the high_birth(positive integer only)"));
			  low_survive = Integer.parseInt(JOptionPane.showInputDialog("Enter the low_survive(positive integer only)"));
			  high_survive = Integer.parseInt(JOptionPane.showInputDialog("Enter the high_survive(positive integer only)"));
			  if (!torus_mode) {
				  _message.setText("   Welcome. Threshold: low birth("+low_birth+ "), high birth(" +high_birth+ "), low survive(" + low_survive + "), high survive(" +high_survive + ") Torus mode is off.");
			  } else {
				  _message.setText("   Welcome. Threshold: low birth("+low_birth+ "), high birth(" +high_birth+ "), low survive(" + low_survive + "), high survive(" +high_survive + ") Torus mode is on.");
			  }
			  
		  }
	}
	class ScheduledTask extends TimerTask {
		public void run() {
			if (start) {
				if (!torus_mode) {
					  for (Spot s : _board) {
							s.determine(low_birth,high_birth,low_survive,high_survive);
					  }
				  } else {
					  for (Spot s : _board) {
							s.determine1(low_birth,high_birth,low_survive,high_survive);
					  }
				  }
				  for (Spot s : _board) {
					  s.goTo();
					 if (!s.isDead()) {
						 s.setSpot();
					 } else {
						 s.clearSpot();
					 }
				  }
			}
		}
	}
	
}
