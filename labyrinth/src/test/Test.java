package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import infrastructure.Cell;
import infrastructure.Labyrinth;

public class Test {
	public Labyrinth lab;
	public Cell a ;
	public Cell b ;
	public Cell c ;

	public Cell d ;
	public Cell e ;
	public Cell f ;

	public Cell g ;
	public Cell h ;
	public Cell i ;
	public Test(){
		CopyOnWriteArrayList<Cell> cs = new CopyOnWriteArrayList<Cell>();
		// n s w e
		a = new Cell(true, false, true, false, "a");
		b = new Cell(true, false, false, false, "b");
		c = new Cell(true, false, false, true, "c");

		d = new Cell(false, false, true, true, "d");
		e = new Cell(false, false, true, true, "e");
		f = new Cell(false, false, true, true, "f");

		g = new Cell(false, true, true, false, "g");
		h = new Cell(false, true, false, false, "h");
		i = new Cell(false, true, false, true, "i");
		cs.add(a);
		cs.add(b);
		cs.add(c);
		cs.add(d);
		cs.add(e);
		cs.add(f);
		cs.add(g);
		cs.add(h);
		cs.add(i);
		
		lab = new Labyrinth(cs, b, g);
	}
}
