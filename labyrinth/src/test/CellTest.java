package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.Cell;
import core.Labyrinth;

public class CellTest {
 Cell c;
	@Before
	public void setUp() throws Exception {
		c = new Cell(true,true,true,true,"test");
	}

	@After
	public void tearDown() throws Exception {
		c = null;
	}

	@Test
	public void testBreakNorthWall() {
		c.breakWall(Labyrinth.NORTH);
		assertFalse(c.isNorth());
	}

	@Test
	public void testBreakSouthWall() {
		c.breakWall(Labyrinth.SOUTH);
		assertFalse(c.isSouth());
	}

	@Test
	public void testBreakEastWall() {
		c.breakWall(Labyrinth.EAST);
		assertFalse(c.isEast());
	}

	@Test
	public void testBreakWestWall() {
		c.breakWall(Labyrinth.WEST);
		assertFalse(c.isWest());
	}
	
	@Test
	public void testGetOpenWallForN() {
		c.setNorth(false);
		assertEquals('N',c.getFirstOpenWallNSWE());
	}
	@Test
	public void testGetOpenWallForS() {
		c.setSouth(false);
		assertEquals('S',c.getFirstOpenWallNSWE());
	}
	@Test
	public void testGetOpenWallForW() {
		c.setWest(false);
		assertEquals('W',c.getFirstOpenWallNSWE());
	}
	@Test
	public void testGetOpenWallForE() {
		c.setEast(false);
		assertEquals('E',c.getFirstOpenWallNSWE());
	}

	@Test
	public void testGetOpenWallForFull() {
		assertEquals(' ',c.getFirstOpenWallNSWE());
	}

}
