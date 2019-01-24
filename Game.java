import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Game {
	Position position =new Position() ;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame= new JFrame("Tic Tac Toe");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new GridLayout(3, 3));
				final Game game = new Game();
				final JButton buttons[]=new JButton[9];
				for (int i = 0; i < 9; i++) {
					final int idx=i;
					final JButton button = new JButton();
					buttons[i]=button;
					button.setPreferredSize(new Dimension(100,100));
					button.setOpaque(true);
					button.setFont(new Font(null, Font.PLAIN, 100));
					button.addMouseListener(new MouseListener() {
						
						@Override
						public void mouseReleased(MouseEvent arg0) {
						
						}
						
						@Override
						public void mousePressed(MouseEvent arg0) {
						
						}
						
						@Override
						public void mouseExited(MouseEvent arg0) {
						
						}
						
						@Override
						public void mouseEntered(MouseEvent arg0) {
						
						}
						
						@Override
						public void mouseClicked(MouseEvent arg0) {
						
							button.setText(""+game.position.turn);
							game.move(idx);
							if(!game.position.isGameEnd()){
								int best=game.position.bestMove();
								buttons[best].setText(""+ game.position.turn);
								game.move(best);
							}
							if(game.position.isGameEnd()){
								String message ="";
								if(game.position.isWinFor('x')){
										message="you WON";
								}
								else if(game.position.isWinFor('o')){
									message="Computer WON";
								}
								else
								{
									message="its a tie";
								}
								JOptionPane.showMessageDialog(null, message);
							}
						}
					});
					frame.add(button);
				}
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

	protected void move(int idx) {
		position=position.move(idx);
	}


}
