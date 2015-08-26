package unitTest;

import static org.junit.Assert.*;
import infrastructure.Cell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		c.breakWall(Cell.NORTH);
		assertFalse(c.isNorth());
	}

	@Test
	public void testBreakSouthWall() {
		c.breakWall(Cell.SOUTH);
		assertFalse(c.isSouth());
	}

	@Test
	public void testBreakEastWall() {
		c.breakWall(Cell.EAST);
		assertFalse(c.isEast());
	}

	@Test
	public void testBreakWestWall() {
		c.breakWall(Cell.WEST);
		assertFalse(c.isWest());
	}
	
	@Test
	public void testGetOpenWallForN() throws Exception {
		c.setNorth(false);
		assertEquals('N',c.getFirstOpenWallNSWE());
	}
	@Test
	public void testGetOpenWallForS() throws Exception {
		c.setSouth(false);
		assertEquals('S',c.getFirstOpenWallNSWE());
	}
	@Test
	public void testGetOpenWallForW() throws Exception {
		c.setWest(false);
		assertEquals('W',c.getFirstOpenWallNSWE());
	}
	@Test
	public void testGetOpenWallForE() throws Exception {
		c.setEast(false);
		assertEquals('E',c.getFirstOpenWallNSWE());
	}

	@Test
	public void testGetOpenWallForFull() throws Exception {
		assertEquals(new Exception("No open walls"),c.getFirstOpenWallNSWE());
	}

}
