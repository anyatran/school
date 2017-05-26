// sorted ( list of authors of books related (in the bibliography of a book 
// you cite or related to such a book and also for WP) to your book
// books and WP

interface ILoDocs { }


class Book implements ILoDocs {
	String title;
	String author;
	ILoDocs biblg;
	Book(String title, String author, ILoDocs biblg) {
		this.title = title;
		this.author = author;
		this.biblg = biblg;
	}
	PrintOnlyBooks relatedBooks() { // return list of this book as printOnlyBooks
// whose related is the related books in this.biblg
	}
}

class WP implements ILoDocs { // return list of related books in this.links
	String url;
	String title;
	String author;
	ILoDocs links;
PrintOnlyBooks relatedBooks() {
}

class printOnlyBooks {
	String title;
	String author;
	ILoPoBooks related;
}

class ExampleBooks {
	IDoc b1 = new Book("hp", "jkr", new MTLoDocs());
	IDoc b2 = new Book("phil of hp", "?", new ConsLoDocs(b1, MTLoDocs()));
	IDoc wp1 = new WP("pottermore.ugh", "tweens", new ConsLoDocs(b1, new MTLoDocs()));
	IDoc wp2 = new WP("fundies.org", "us", new ConsLoDocs(new Book("htpc", "mf", new MTLoDocs()))), 
			new ConsLoDocs(new Book("as", "ar", new MTLoDocs()), new MTLoDocs())))
	IDoc ours = new Book("my thesis", "me", new ConsLoDocs
			(b2, new ConsLoDocs(wp1, new ConsLoDocs(wp2, new MToDocs()))));
	
}