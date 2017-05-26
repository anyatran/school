// assignment 2
// Tran Anh
// anhtran9
// Dylan Boudro
// Dboody

import tester.Tester;

// to represent a cell
/* Template:
 * Fields:
 * ...this.row...                                 -- int
 * ...this.col...                                 -- String
 * ...this.data...                                -- IData
 * 
 * Methods:
 * ...this.value()...                             -- int
 * ...this.countArgs()...                         -- int
 * ...this.countFuns()...                         -- int
 * 
 * Methods for fields:
 *...this.data.getDataVal()                       -- int
 *...this.data.getCount()                         -- int
 *...this.data.getFuns()                          -- int
 */

class Cell {
    int row;
    String col;
    IData data;

    Cell(int row, String col, IData data) {
        this.row = row;
        this.col = col;
        this.data = data;
    }

    // to calculate the value of this Cell

    int value() {
        return this.data.getDataVal();
    }

    // to calculate number of arguments in this Cell
    int countArgs() {
        return this.data.getCount();
    }

    // to calculate the number of operations in this Cell
    int countFuns() {
        return this.data.getFuns();
    }
}

// to represent types of cells
interface IData {

    int getDataVal();

    int getCount();

    int getFuns();
}
/* Template:
 * Fields:
 * ...this.value...                                -- int
 * 
 * Methods:
 * ...this.getDataVal()...                         -- int
 * ...this.getCount()...                           -- int
 * ...this.getFuns()...                            -- int
 * 
 */
// to represent a number
// cellA1 --> 
class Number implements IData {
    int value;

    Number(int value) {
        this.value = value;
    }

    // to extract the value from this Number
    public int getDataVal() {
        return this.value;
    }

    // to calculate how many arguments are used in this number
    public int getCount() {
        return 1;
    }

    // to calculate how many operations are used to calculate this cell
    public int getFuns() {
        return 0;
    }

}

// to represent a cell Formula
/* Template:
 * Fields:
 * ...this.cell1...                                 -- Cell
 * ...this.cell2...                                 -- Cell
 * ...this.function...                              -- IFun
 * 
 * Methods:
 * ...this.getDataVal()...                          -- int
 * ...this.getCount()...                            -- int
 * ...this.getFuns()...                             -- int
 * 
 * Methods for fields:
 *...this.function.compute(Cell, Cell)              -- int
 *...this.cell1.countArgs()...                      -- int
 *...this.cell2.countArgs()...                      -- int
 *...this.cell1.countFuns()...                      -- int
 *...this.cell2.countFuns()...                      -- int
 */
class Formula implements IData {
    Cell cell1;
    Cell cell2;
    IFun function;

    Formula(Cell cell1, Cell cell2, IFun function) {
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.function = function;
    }

    // to get the int value of this Formula
    public int getDataVal() {
        return this.function.compute(cell1, cell2);
    }

    // to calculate how many arguments have been used in this Formula
    public int getCount() {
        return this.cell1.countArgs() + this.cell2.countArgs();
    }

    // to calculate how many math operations have been used in this Formula
    public int getFuns() {
        return this.cell1.countFuns() + this.cell2.countFuns() + 1;
    }
}

// to represent one of the Functions
interface IFun {
    int compute(Cell one, Cell two);
}

/*
 * Template: 
 * Fields: 

 * 
 * Method: 
 * ...calculate(Cell, Cell)                       -- int                 
 * 
 * Methods for fields: 
 * ...this.one.value()                            -- int
 * ...this.two.value()                            -- int
 * 
 */
// to represent the function maximum
class Max implements IFun {
    Max() { // it's empty because Max doesn't have any fields
    }

    // to find the Max value between two cells
    public int compute(Cell one, Cell two) {
        return Math.max(one.value(), two.value());
    }
}

// to represent the function substract
class Sub implements IFun {
    Sub() { // it's empty because Sub doesn't have any fields
    }

    // to calculate the substraction between two cells
    public int compute(Cell one, Cell two) {
        return one.value() - two.value();
    }
}

// to represent the function multiplicate
class Mult implements IFun {
    Mult() { // it's empty because Mult doesn't have any fields
    }
    // to find the product between two cells
    public int compute(Cell one, Cell two) {
        return one.value() * two.value();
    }
}

// to represent examples of cells
class ExamplesExcelCells {
    // Row 1 [Cells with Numbers]
    Cell cellA1 = new Cell(1, "A", new Number(8));
    Cell cellB1 = new Cell(1, "B", new Number(3));
    Cell cellC1 = new Cell(1, "C", new Number(4));
    Cell cellD1 = new Cell(1, "D", new Number(6));
    Cell cellE1 = new Cell(1, "E", new Number(2));

    // Column A Cells [Cells with Formulas]
    Cell cellA2 = new Cell(2, "A", new Formula(cellB1, cellD1, new Max()));
    Cell cellA3 = new Cell(3, "A", new Formula(cellA1, cellA2, new Mult()));

    // Column B Cells [Cells with Formulas]
    Cell cellB2 = new Cell(2, "B", new Formula(cellA1, cellC1, new Sub()));
    Cell cellB3 = new Cell(3, "B", new Formula(cellB2, cellE1, new Mult()));
    Cell cellB4 = new Cell(4, "B", new Formula(cellB3, cellB2, new Mult()));
    Cell cellB5 = new Cell(5, "B", new Formula(cellB4, cellB3, new Sub()));

    // Column C Cells [Cells with Formulas]
    Cell cellC2 = new Cell(2, "C", new Formula(cellA3, cellB1, new Mult()));
    Cell cellC3 = new Cell(3, "C", new Formula(cellA1, cellB1, new Max()));

    // Column D Cells [Cells with Formulas]
    Cell cellD2 = new Cell(2, "D", new Formula(cellB4, cellB5, new Sub()));


    // Column E Cells [Cells with Formulas]
    Cell cellE2 = new Cell(2, "E", new Formula(cellA3, cellA2, new Sub()));
    Cell cellE3 = new Cell(3, "E", new Formula(cellA3, cellD1, new Max()));
    Cell cellE4 = new Cell(4, "E", new Formula(cellB4, cellE3, new Max()));
    Cell cellE5 = new Cell(5, "E", new Formula(cellE4, cellB5, new Sub()));

    // Examples of Numbers
    IData a1 = new Number(8);
    IData b1 = new Number(3);
    IData c1 = new Number(4);
    IData d1 = new Number(6);

    // Examples of Formulas
    IData a2 = new Formula(cellB1, cellD1, new Max());
    IData d2 = new Formula(cellB4, cellB5, new Sub());
    IData c2 = new Formula(cellA3, cellB1, new Mult());

    // tests for value()
    boolean testCellValue(Tester t) {
        return t.checkExpect(cellA1.value(), 8)
                && t.checkExpect(cellB1.value(), 3)
                && t.checkExpect(cellC1.value(), 4)
                && t.checkExpect(cellD1.value(), 6)
                && t.checkExpect(cellE1.value(), 2)
                && t.checkExpect(cellA2.value(), 6)
                && // Max
                t.checkExpect(cellA3.value(), 48)
                && // Mult
                t.checkExpect(cellB2.value(), 4)
                && // Sub
                t.checkExpect(cellB3.value(), 8)
                && t.checkExpect(cellB4.value(), 32);
    }

    // Tests for countArgs()
    boolean testCountArgs(Tester t) {
        return t.checkExpect(cellA1.countArgs(), 1)
                && t.checkExpect(cellA3.countArgs(), 3)
                && t.checkExpect(cellB4.countArgs(), 5);

    }

    // Tests for countFuns()
    boolean testCountFuns(Tester t) {
        return t.checkExpect(cellA1.countFuns(), 0)
                && t.checkExpect(cellA3.countFuns(), 2)
                && t.checkExpect(cellE5.countFuns(), 16)
                && t.checkExpect(cellB4.countFuns(), 4);

    }
    // Tests for getDataVal()
    boolean testGetDataVal(Tester t) {
        return t.checkExpect(a1.getDataVal(), 8) &&
                t.checkExpect(b1.getDataVal(), 3) &&
                t.checkExpect(a2.getDataVal(), 6) &&
                t.checkExpect(c2.getDataVal(), 144) &&
                t.checkExpect(d2.getDataVal(), 8);
    }
    // Tests for getCount()
    boolean testGetCount(Tester t) {
        return t.checkExpect(a1.getCount(), 1) &&
                t.checkExpect(b1.getCount(), 1) &&
                t.checkExpect(a2.getCount(), 2) &&
                t.checkExpect(c2.getCount(), 4) &&
                t.checkExpect(d2.getCount(), 13);
    }
    // Tests for getFuns()
    boolean testGetFuns(Tester t) {
        return t.checkExpect(a1.getFuns(), 0) &&
                t.checkExpect(b1.getFuns(), 0) &&
                t.checkExpect(a2.getFuns(), 1) &&
                t.checkExpect(c2.getFuns(), 3) &&
                t.checkExpect(d2.getFuns(), 12);
    }

}
