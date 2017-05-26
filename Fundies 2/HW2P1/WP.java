// assignment 2
// Tran Anh     
// anhtran9     
// Boudro Dylan   
// Dboody




import tester.Tester;

// to represent a list of items

/*Template:
 * Fields:
 * 
 * Methods: 
 * this.getImageSize()                                -- int
 * this.getTextLength()                               -- int
 * this.getImageString()                              -- String
 * this.isEmpty()                                     -- boolean
 * this.getImages()                                   -- ILoItem
 * 
 */

interface ILoItem { 
    int getImageSize();
    int getTextLength();
    String getImageString();
    boolean isEmpty();
    ILoItem getImages();
}

// to represent an empty list
class MtLoItem implements ILoItem {

    MtLoItem() { //empty list doesn't have any fields 
    }

    // returns the image-size of an empty item, 0.
    public int getImageSize() {
        return 0;
    }

    // returns the text-length of an empty item, 0.
    public int getTextLength() {
        return 0;
    }

    // returns the Image String of an empty item, ""
    public String getImageString() {
        return "";
    }
    // checks if the the list is empty
    public boolean isEmpty() {
        return true;
    }
    // pulls out Images from the list and put into a new list
    public ILoItem getImages() {
        return this;
    }

}

// to represent a non empty list

/*Template:
 * Fields:
 * this.first()                               -- Item
 * this.rest()                                -- ILoItem
 * 
 * Methods:
 * this.getImageSize()                        -- int
 * this.getTextLenght()                       -- int
 * this.getImages()                           -- ILoItem
 * this.getImageString()                      -- String
 * this.isEmpty()                             -- boolean
 * 
 * Methods for fields:
 * this.first.computeImageSize()              -- int
 * this.rest.getImageSize()                   -- int
 * this.first.computeTextLength()             -- int
 * this.rest.getTextLength()                  -- int
 * this.first.hasImage()                      -- boolean
 * this.rest.getImages()                      -- ILoItem
 * this.rest.isEmpty()                        -- boolean
 * this.first.concatImageString()             -- String
 * this.rest.getImageString()                 -- String
 */
class ConsLoItem implements ILoItem {
    Item first;
    ILoItem rest;

    ConsLoItem(Item first, ILoItem rest) {
        this.first = first;
        this.rest = rest;
    }

    // calls a helper function on first and recurses on the rest
    public int getImageSize() {
        return this.first.computeImageSize() + this.rest.getImageSize();
    }

    // calls a helper function on first and recurses on the rest
    public int getTextLength() {
        return this.first.computeTextLength() + this.rest.getTextLength();
    }

    // pulls out the String of Image's title and format
    public String getImageString() {
        if (this.rest.isEmpty()) {
            return this.first.concatImageString();
        }
        else {
            return this.first.concatImageString() + ", " +
                    this.rest.getImageString();
        }
    }

    // checks if the list is empty or not
    public boolean isEmpty() {
        return false;
    }
    // calls a helper function on first and recurses on the rest
    public ILoItem getImages() {
        if (this.first.hasImage()) {
            return new ConsLoItem(this.first, this.rest.getImages());
        }
        else    {
            return this.rest.getImages();
        }
    }

}



// to represent a list Item
interface Item {
    int computeImageSize(); 
    int computeTextLength();
    String concatImageString();
    boolean hasImage();

}


// to represent a block of text

/*Template:
 * Fields:
 * this.contents()                        -- String
 * 
 * Methods:
 * this.computeImageSize()                -- int
 * this.computeTextLength()               -- int
 * this.concatImageString()               -- String
 * this.hasImage()                        -- boolean
 * 
 * Methods for fields:
 * this.contents.length()                 -- int
 * 
 */
class Text implements Item {
    String contents;

    Text(String contents) {
        this.contents = contents;
    }
    // returns the image-size of text, 0.
    public int computeImageSize() {
        return 0;
    }
    // returns the number of characters in the block of text.
    public int computeTextLength() {
        return this.contents.length();
    }
    // returns the Image-title of a text (none), ""
    public String concatImageString() {
        return "";
    }
    // checks to see if this has an Image as a field
    public boolean hasImage() {
        return false;
    }
}

// to represent an image

/*Template:
 * Fields:
 * this.fileName()                        -- String
 * this.size()                            -- int
 * this.fileType()                        -- String
 * 
 * Methods:
 * this.computeImageSize()                -- int
 * this.computeTextLength()               -- int
 * this.concatImageString()               -- String
 * this.hasImage()                        -- boolean
 * 
 * Methods for fields:
 * this.fileName.length()                 -- int
 * this.fileType.length()                 -- int
 * 
 */
class Image implements Item {
    String fileName;
    int size;
    String fileType;

    Image(String fileName, int size, String fileType) {
        this.fileName = fileName;
        this.size = size;
        this.fileType = fileType;
    }
    // returns the Image-size
    public int computeImageSize() {
        return this.size;
    }
    // returns the length of the file name and type
    public int computeTextLength() {
        return this.fileName.length() + this.fileType.length();

    }
    // creates a string-list of filename and types.
    public String concatImageString() {
        return this.fileName + "." + this.fileType;
    }
    // checks to see if this has an image as a field
    public boolean hasImage() {
        return true;
    }
}

// to represent a link

/*Template:
 * Fields:
 * this.name()                            -- String
 * this.page()                            -- WP
 * 
 * Methods:
 * this.computeImageSize()                -- int
 * this.computeTextLength()               -- int
 * this.concatImageString()               -- String
 * this.hasImage()                        -- boolean
 * 
 * Methods for fields:
 * this.page.totalImageSize()             -- int
 * this.name.length()                     -- int
 * this.page.images()                     -- String
 * 
 */
class Link implements Item {
    String name;
    WP page;

    Link(String name, WP page) {
        this.name = name;
        this.page = page;
    }
    // returns totalImage Size of the webpage mentioned in the link
    public int computeImageSize() {
        return this.page.totalImageSize();
    }
    // computes the text length of the link name
    public int computeTextLength() {
        return this.name.length() + this.page.textLength();
    }
    // calls the images method on the link's webpage
    public String concatImageString() {
        return this.page.images();
    }
    // checks to see if this has an image as a field
    public boolean hasImage() {
        return true;
    }
}

// to represent a web page

/*Template:
 * Fields:
 * this.url()                               -- String
 * this.title()                             -- String
 * this.items()                             -- ILoItem
 * 
 * Methods:
 * this.totalImageSize()                    -- int
 * this.textLength()                        -- int
 * this.images()                            -- String
 */
class WP {
    String url;
    String title;
    ILoItem items;

    WP(String url, String title, ILoItem items) {
        this.url = url;
        this.title = title;
        this.items = items;
    }

    // calls a helper function on the ILoItems in the webpage
    int totalImageSize() {
        return this.items.getImageSize();
    }
    // calls a helper method and returns the title char length
    int textLength() { 
        return this.items.getTextLength() + this.title.length();
    }
    // calls a helper function on the ILoItems of the webpage
    String images() {
        return this.items.getImages().getImageString();
    }
}



// -------------------------------- EXAMPLES ----------------------------------
class ExamplesWP {

    //examples of Text
    Item text1 = new Text("Team picture");
    Item text2 = new Text("Our coach");
    Item text3 = new Text("Jesse");
    // examples of Images
    Item img1 = new Image("team", 340, "jpeg" );
    Item img2 = new Image("coach", 300, "png" );
    Item img3 = new Image("jesse", 300, "jpeg" );
    // empty list item
    ILoItem mt = new MtLoItem();

    // -------- WEBPAGES ---------

    // Jesse
    WP panth = new WP("panther-fans.org", "Panther's Fans", new ConsLoItem(
        text3, new ConsLoItem(img3, mt)));

    // Our team
    WP myWP = new WP("panthers.org", "Our team",
            new ConsLoItem(this.text1, new ConsLoItem(img1, new ConsLoItem(
                text2, new ConsLoItem(img2, new ConsLoItem(
                    new Link("our fans", panth), mt)))))); 
    // ----------------------------

    // a link example
    Item link1 = new Link("our fans", panth);

    // examples of cons structures
    ILoItem cons1 = new ConsLoItem(text1, new ConsLoItem(img1, new ConsLoItem(
        text2, new ConsLoItem(img2, new ConsLoItem(link1, mt))))  ); 

    ILoItem consJesse = new ConsLoItem(text3, new ConsLoItem(img3, mt));


    // -------------- Tests for the  ILoItem Interface Methods ----------------

    // test for getImageSize()
    boolean testgetImageSize(Tester t) {
        return t.checkExpect(mt.getImageSize(), 0) &&
                t.checkExpect(consJesse.getImageSize(), 300);
    }
    // test for getTextLength()
    boolean testGetTextLength(Tester t) {
        return t.checkExpect(mt.getTextLength(), 0);           
    }
    // tests for getImageString()
    boolean testGetImageString(Tester t) {
        return t.checkExpect(mt.getImageString(), "") &&
                t.checkExpect(consJesse.getImageString(), ", jesse.jpeg");
    } 
    // tests for isEmpty()
    boolean testIsEmpty(Tester t) {
        return t.checkExpect(mt.isEmpty(), true) &&
                t.checkExpect(consJesse.isEmpty(), false);  
    }
    // tests for getImages()
    boolean testGetImages(Tester t) {
        return t.checkExpect(mt.getImages(), mt)
                && t.checkExpect(consJesse.getImages(), new ConsLoItem(
                        new Image("jesse", 300, "jpeg"), mt));
    }

    // --------------- Tests for the Item Interface Methods -----------------
    // test for computeImageSize
    boolean testcomputeImageSize(Tester t) {
        return t.checkExpect(text1.computeImageSize(), 0) &&
                t.checkExpect(img1.computeImageSize(), 340) && 
                t.checkExpect(link1.computeImageSize(), 300 );
    }
    // test for computeTextLength
    boolean testcomputeTextLength(Tester t) {
        return t.checkExpect(text1.computeTextLength(), 12) && 
                t.checkExpect(img1.computeTextLength(), 8) && 
                t.checkExpect(link1.computeTextLength(), 36); 
    }
    // test for concatImageString
    boolean testconcatImageString(Tester t) {
        return t.checkExpect(text1.concatImageString(), "") && 
                t.checkExpect(img1.concatImageString(), "team.jpeg") && 
                t.checkExpect(link1.concatImageString(), "jesse.jpeg"); 
    }
    // test for hasImage
    boolean testhasImage(Tester t) {
        return t.checkExpect(text1.hasImage(), false) && 
                t.checkExpect(img1.hasImage(), true) && 
                t.checkExpect(link1.hasImage(), true); 
    }
    // --------------- Tests for the WP Methods -----------------
    // test for totalImageSize()
    boolean testimages(Tester t) {
        return t.checkExpect(myWP.totalImageSize(), 940) &&
                t.checkExpect(panth.totalImageSize(), 300);
    } 

    // test for TextLength()
    boolean testlength(Tester t) {  
        return t.checkExpect(myWP.textLength(), 81) && 
                t.checkExpect(panth.textLength(), 28); 
    }
    // test for the images()
    boolean testimgString(Tester t) {
        return t.checkExpect(myWP.images(), 
                "team.jpeg, coach.png, jesse.jpeg") &&
                t.checkExpect(panth.images(), "jesse.jpeg");
    }

    // test for getImageSize()
    boolean testGetImageSize(Tester t) {
        return t.checkExpect(mt.getImageSize(), 0) &&
                t.checkExpect(consJesse.getImageSize(), 300);
    }

}


