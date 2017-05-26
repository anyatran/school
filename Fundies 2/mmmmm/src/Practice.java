// to represent a book in a bookstore
class Book {
	String title;
	String author;
	int price;

	// the constructor
	Book(String title, String author, int price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}


/* TEMPLATE:
    ... this.title ...         -- String
    ... this.author ...        -- String
    ... this.price ...         -- int

 */

//compute the sale price of this book given today's discount
//in percent of the priginal price
int salePrice(int discount) {
	return this.price - (this.price * discount) / 100;
}
}

//examples and tests for the class hierarchy that represents
//books and authors
class ExamplesBooks {
 ExamplesBooks() {}

 // examples of boooks
 Book htdp = new   Book("HtDP", "FFK", 60);
 Book beaches = new Book("Beaches", "PC", 20);

 /* The tests are defined as methods that produce a boolean value
 that indicates whether the tests passed. We need to add an
 'import' statement on the top of the file that indicates that
 we will use the tester library. The library evaluates the
 checkExpect methods that consume two values and produces
 true or false depending on whether the two values are the same.
 */

 // test the method salePrice for the class Book
 boolean testSalePrice(Tester t) {
     return
     t.checkExpect(this.htdp.salePrice(30), 42) &&
     t.checkExpect(this.beaches.salePrice(20), 16);
 }

 /* We can now run our program and see the test results. */
}

