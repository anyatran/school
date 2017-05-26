// Tran, Anh
// anhtran9
// Krutiansky, Brett
// brekru

// CS 2510 Spring 2014
// Assignment 4

import tester.*;

// to represent different files in a computer
interface IFile {

    // compute the size of this file
    int size();

    // compute the time (in seconds) to download this file
    // at the given download rate
    int downloadTime(int rate);

    // is the owner of this file the same
    // as the owner of the given file?
    boolean sameOwner(IFile that);

    // a helper function that pull outs the owner field from the file
    public String getOwner();
}

// to represent a text file
class TextFile implements IFile {
    String name;
    String owner;
    int length; // in bytes

    TextFile(String name, String owner, int length) {
        this.name = name;
        this.owner = owner;
        this.length = length;
    }

    // compute the size of this file
    public int size() {
        return this.length;
    }

    // compute the time (in seconds) to download this file
    // at the given download rate
    public int downloadTime(int rate) {
        return this.size() / rate;
    }

    // is the owner of this file the same
    // as the owner of the given file?
    public boolean sameOwner(IFile that) {
        return this.owner.equals(that.getOwner());
    }

    // a helper function that pull outs the owner field from the file
    public String getOwner() {
        return this.owner;
    }
}

// to represent an image file
class ImageFile implements IFile {
    String name;
    String owner;
    int width; // in pixels
    int height; // in pixels

    ImageFile(String name, String owner, int width, int height) {
        this.name = name;
        this.owner = owner;
        this.width = width;
        this.height = height;
    }

    // compute the size of this file
    public int size() {
        return this.width * this.height;
    }

    // compute the time (in seconds) to download this file
    // at the given download rate
    public int downloadTime(int rate) {
        return this.size() / rate;
    }

    // is the owner of this file the same
    // as the owner of the given file?
    public boolean sameOwner(IFile that) {
        return this.owner.equals(that.getOwner());
    }

    // a helper function that pull outs the owner field from the file
    public String getOwner() {
        return this.owner;
    }
}

// to represent an audio file
class AudioFile implements IFile {
    String name;
    String owner;
    int speed; // in bytes per second
    int length; // in seconds of recording time

    AudioFile(String name, String owner, int speed, int length) {
        this.name = name;
        this.owner = owner;
        this.speed = speed;
        this.length = length;
    }

    // compute the size of this file
    public int size() {
        return this.speed * this.length;
    }

    // compute the time (in seconds) to download this file
    // at the given download rate
    public int downloadTime(int rate) {
        return this.size() / rate;
    }

    // is the owner of this file the same
    // as the owner of the given file?
    public boolean sameOwner(IFile that) {
        return this.owner.equals(that.getOwner());
    }

    // a helper function that pull outs the owner field from the file
    public String getOwner() {
        return this.owner;
    }
}

class ExamplesFiles {

    IFile text1 = new TextFile("English paper", "Maria", 1234);
    IFile text2 = new TextFile("Economics paper", "Anya", 110);

    IFile picture = new ImageFile("Beach", "Maria", 400, 200);
    IFile picture2 = new ImageFile("Headshot", "Brett", 500, 0);

    IFile song = new AudioFile("Help", "Pat", 200, 120);
    IFile song2 = new AudioFile("Gummybear", "Brett", 1, 240);

    // test the method size for the classes that represent files
    boolean testSize(Tester t) {
        return t.checkExpect(this.text1.size(), 1234)
                && t.checkExpect(this.text2.size(), 110)
                && t.checkExpect(this.picture.size(), 80000)
                && t.checkExpect(this.picture2.size(), 0)
                && t.checkExpect(this.song.size(), 24000)
                && t.checkExpect(this.song2.size(), 240);

    }

    boolean testDownloadRate(Tester t) {
        return t.checkExpect(this.text1.downloadTime(2), 617)
                && t.checkExpect(this.text2.downloadTime(10), 11)
                && t.checkExpect(this.picture.downloadTime(10), 8000)
                && t.checkExpect(this.picture2.downloadTime(10), 0)
                && t.checkExpect(this.song.downloadTime(10), 2400)
                && t.checkExpect(this.song2.downloadTime(10), 24);

    }

    boolean testSameOwner(Tester t) {
        return t.checkExpect(this.text1.sameOwner(picture), true)
                && t.checkExpect(this.text1.sameOwner(text2), false)
                && t.checkExpect(this.picture2.sameOwner(song2), true)
                && t.checkExpect(this.song2.sameOwner(text2), false);
    }

}