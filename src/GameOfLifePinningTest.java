import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameOfLifePinningTest {
	/*
	 * READ ME: You may need to write pinning tests for methods from multiple
	 * classes, if you decide to refactor methods from multiple classes.
	 * 
	 * In general, a pinning test doesn't necessarily have to be a unit test; it can
	 * be an end-to-end test that spans multiple classes that you slap on quickly
	 * for the purposes of refactoring. The end-to-end pinning test is gradually
	 * refined into more high quality unit tests. Sometimes this is necessary
	 * because writing unit tests itself requires refactoring to make the code more
	 * testable (e.g. dependency injection), and you need a temporary end-to-end
	 * pinning test to protect the code base meanwhile.
	 * 
	 * For this deliverable, there is no reason you cannot write unit tests for
	 * pinning tests as the dependency injection(s) has already been done for you.
	 * You are required to localize each pinning unit test within the tested class
	 * as we did for Deliverable 2 (meaning it should not exercise any code from
	 * external classes). You will have to use Mockito mock objects to achieve this.
	 * 
	 * Also, you may have to use behavior verification instead of state verification
	 * to test some methods because the state change happens within a mocked
	 * external object. Remember that you can use behavior verification only on
	 * mocked objects (technically, you can use Mockito.verify on real objects too
	 * using something called a Spy, but you wouldn't need to go to that length for
	 * this deliverable).
	 */

	/* TODO: Declare all variables required for the test fixture. */
	Cell[][] cells = new Cell[5][5];
	MainPanel mainPanel = new MainPanel(5);
	RunButton run;
	
	@Before
	public void setUp() {
		/*
		 * TODO: initialize the text fixture. For the initial pattern, use the "blinker"
		 * pattern shown in:
		 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Examples_of_patterns
		 * The actual pattern GIF is at:
		 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#/media/File:Game_of_life_blinker.gif
		 * Start from the vertical bar on a 5X5 matrix as shown in the GIF.
		 */
		cells[0][0] = mock(Cell.class);
		cells[0][1] = mock(Cell.class);
		cells[0][2] = mock(Cell.class);
		cells[0][3] = mock(Cell.class);
		cells[0][4] = mock(Cell.class);
		cells[1][0] = mock(Cell.class);
		cells[1][1] = mock(Cell.class);
		cells[1][2] = mock(Cell.class);
		cells[1][3] = mock(Cell.class);
		cells[1][4] = mock(Cell.class);
		cells[2][0] = mock(Cell.class);
		cells[2][1] = mock(Cell.class);
		cells[2][2] = mock(Cell.class);
		cells[2][3] = mock(Cell.class);
		cells[2][4] = mock(Cell.class);
		cells[3][0] = mock(Cell.class);
		cells[3][1] = mock(Cell.class);
		cells[3][2] = mock(Cell.class);
		cells[3][3] = mock(Cell.class);
		cells[3][4] = mock(Cell.class);
		cells[4][0] = mock(Cell.class);
		cells[4][1] = mock(Cell.class);
		cells[4][2] = mock(Cell.class);
		cells[4][3] = mock(Cell.class);
		cells[4][4] = mock(Cell.class);
		
		when(cells[0][0].getAlive()).thenReturn(false);
		when(cells[0][1].getAlive()).thenReturn(false);
		when(cells[0][2].getAlive()).thenReturn(false);
		when(cells[0][3].getAlive()).thenReturn(false);
		when(cells[0][4].getAlive()).thenReturn(false);
		when(cells[1][0].getAlive()).thenReturn(false);
		when(cells[1][1].getAlive()).thenReturn(false);
		when(cells[1][2].getAlive()).thenReturn(true);
		when(cells[1][3].getAlive()).thenReturn(false);
		when(cells[1][4].getAlive()).thenReturn(false);
		when(cells[2][0].getAlive()).thenReturn(false);
		when(cells[2][1].getAlive()).thenReturn(false);
		when(cells[2][2].getAlive()).thenReturn(true);
		when(cells[2][3].getAlive()).thenReturn(false);
		when(cells[2][4].getAlive()).thenReturn(false);
		when(cells[3][0].getAlive()).thenReturn(false);
		when(cells[3][1].getAlive()).thenReturn(false);
		when(cells[3][2].getAlive()).thenReturn(true);
		when(cells[3][3].getAlive()).thenReturn(false);
		when(cells[3][4].getAlive()).thenReturn(false);
		when(cells[4][0].getAlive()).thenReturn(false);
		when(cells[4][1].getAlive()).thenReturn(false);
		when(cells[4][2].getAlive()).thenReturn(false);
		when(cells[4][3].getAlive()).thenReturn(false);
		when(cells[4][4].getAlive()).thenReturn(false);
	
		mainPanel.setCells(cells);
	}

	/* TODO: Write the three pinning unit tests for the three optimized methods */
	
	/**
	 * Test for MainPanel.calculateNextIteration()
	 * Prerequisites: Vertical pattern initiated with mock cells in the main panel
	 * 		  Set local cells array into horizontal configuration
	 * Execution Steps: Call main panel calculate next iteration
	 * Postconditions: Cell array from main panel and local equal each other
	 */
	@Test
	public void testCalculateNextIteration() {
		when(cells[1][2].getAlive()).thenReturn(false);
		when(cells[3][2].getAlive()).thenReturn(false);
		when(cells[2][1].getAlive()).thenReturn(true);
		when(cells[2][3].getAlive()).thenReturn(true);
		mainPanel.calculateNextIteration();
		Cell[][] tmp = mainPanel.getCells();
		assertArrayEquals(tmp, cells);
	}
	/**
	 * A test for MainPanel.iterateCell()
	 * Preconditions: Vertical pattern initiated with mock cells in the main panel
	 * Execution Steps: Call iterate cell on every cell that is expected to change.
	 * Postconditions: Cells 1,2 and 3,2 turn false, and 2,1/2,2/2,3 are true
	 */
	@Test
	public void testIterateCell() {
		assertEquals(mainPanel.iterateCell(1, 2), false);
		assertEquals(mainPanel.iterateCell(3, 2), false);
		assertEquals(mainPanel.iterateCell(2, 1), true);
		assertEquals(mainPanel.iterateCell(2, 2), true);
		assertEquals(mainPanel.iterateCell(2, 3), true);
		
	}
	/**
	 * A test for Cell.toString()
	 * Preconditions: Initiate 2 Cells, one alive and one dead
	 * Execution Steps: Call toString() on both cells
	 * Post Conditions: The alive cell returns "X" and the dead cell returns "."
	 */
	@Test
	public void testCellToString() {
		Cell tmpAlive = new Cell(true);
		Cell tmpDead = new Cell(false);
		assertEquals(tmpAlive.toString(), "X");
		assertEquals(tmpDead.toString(), ".");
	}
}
