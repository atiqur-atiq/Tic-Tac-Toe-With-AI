import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PositionTest {
	@Test
	public void testNewPosition() throws Exception {
		Position position= new Position();
		assertEquals("         ", position.toString());
		assertEquals('x', position.turn);
	}
	@Test
	public void testMove() throws Exception {
		Position position= new Position().move(1);
		assertEquals('o', position.turn);
		assertEquals(" x       ", position.toString());
		
	}
	@Test
	public void testUnmove() throws Exception {
		Position position= new Position().move(1).unmove(1);
		assertEquals('x', position.turn);
		assertEquals("         ", position.toString());	
	}
	@Test
	public void testPossibleMoves() throws Exception {
		List<Integer> list= new ArrayList<>();
		for (int i = 0; i < Position.SIZE; i++) {
			list.add(i);	
		}
		list.remove(new Integer(1));
		list.remove(new Integer(2));
		assertEquals(list,new Position().move(1).move(2).possibleMoves());
	}
	@Test
	public void testIsWinFor() throws Exception {
		assertFalse(new Position().isWinFor('x'));
		assertTrue(new Position("xxx      ",'x').isWinFor('x'));
		assertTrue(new Position(
				"x  "
			   +"x  "
			   +"x  ",'x').isWinFor('x'));
		assertTrue(new Position(
				"o  "
			   +" o "
			   +"  o",'o').isWinFor('o'));
		assertTrue(new Position(
				"  o"
			   +" o "
			   +"o  ",'o').isWinFor('o'));
		
	}
	@Test
	public void testMinimax() throws Exception {
		assertEquals( 6, new Position("xxx      ",'x').minimax());
		assertEquals(-6, new Position("ooo      ",'o').minimax());
		assertEquals( 0, new Position("xoxoxooxo",'x').minimax());
		assertEquals( 6, new Position("xx       ",'x').minimax());
		assertEquals(-6, new Position("oo       ",'o').minimax());
		assertEquals( 0, new Position().minimax());
			
	}
	@Test
	public void testBestMove() throws Exception {
		assertEquals(2, new Position("xx       ",'x').bestMove());
		assertEquals(2, new Position("oo       ",'o').bestMove());	
	}
	@Test
	public void testIsGameEnd() throws Exception {
		assertFalse(new Position().isGameEnd());
		assertTrue(new Position("xxx      ",'x').isGameEnd());
		assertTrue(new Position("ooo      ",'x').isGameEnd());
		assertTrue(new Position("xoxxoxoxo",'x').isGameEnd());
	}
}
