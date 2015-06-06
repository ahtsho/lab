package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.Cell;
import core.Labyrinth;

public class LabyrinthTest {
	Labyrinth l;
	@Before
	public void setUp() throws Exception {
		ArrayList<Cell> cs = new ArrayList<Cell>();
		Cell a = new Cell(true, true, true, true, "a");
		Cell b = new Cell(true, true, true, true, "b");
		Cell c = new Cell(true, true, true, true, "c");
		Cell d = new Cell(true, true, true, true, "d");
		Cell e = new Cell(true, true, true, true, "e");
		Cell f = new Cell(true, true, true, true, "f");
		Cell g = new Cell(true, true, true, true, "g");
		Cell h = new Cell(true, true, true, true, "h");
		Cell i = new Cell(true, true, true, true, "i");
		cs.add(a);
		cs.add(b);
		cs.add(c);
		cs.add(d);
		cs.add(e);
		cs.add(f);
		cs.add(g);
		cs.add(h);
		cs.add(i);
		l = new Labyrinth(cs, b, g);
	}

	@After
	public void tearDown() throws Exception {
		l = null;
	}


	@Test
	public void testGetCellForDirectionNfromTopLeft() {
		assertEquals(null, l.getCellForDirection(l.getCells().get(0), Labyrinth.NORTH));
	}
	
	@Test
	public void testGetCellForDirectionSfromTopLeft() {
		assertEquals("d", l.getCellForDirection(l.getCells().get(0), Labyrinth.SOUTH).getName());
	}

	@Test
	public void testGetCellForDirectionWfromCenter() {
		assertEquals("d", l.getCellForDirection(l.getCells().get(4), Labyrinth.WEST).getName());
	}
	
	@Test
	public void testGetOppositeDirection() {
		fail("Not yet implemented");
	}

	@Test
	public void testMove() {
		fail("Not yet implemented");
	}

}
