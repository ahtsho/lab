package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import model.infrastructure.Cell;

public class IteratorsTest extends Test{

	public static void main (String[] args){
		
		IteratorsTest t = new IteratorsTest();
		CopyOnWriteArrayList<Cell> cells = t.lab.getCells();
		Iterator<Cell> iter = cells.iterator();
		while(iter.hasNext()){
			Cell cell = iter.next();
			
			if(cell.getName()=="c"){
				cells.remove(cell);
			}
			for(Cell c: cells){
			System.out.print(c.getName());
			}
			System.out.println();

		}
		
	}
}
