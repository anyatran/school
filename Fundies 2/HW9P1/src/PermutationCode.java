// assignment 9
// Tran, Anh
// anhtran9
// Stephanie Nguyen
// partner2-username

import java.util.*;

import tester.Tester;

/**
  A class that defines a new permutation code, as well as methods for encoding
  and decoding of the messages that use this code.
 */
public class PermutationCode{
    /* The original list of characters to be encoded */
    ArrayList<Character> alphabet = 
            new ArrayList<Character>(Arrays.asList(
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
                    't', 'u', 'v', 'w', 'x', 'y', 'z'));

    ArrayList<Character> code = new ArrayList<Character>(26);

    /** A random number generator */
    Random rand = new Random();

    //Create a new instance of the encoder/decoder with a new permutation code 
    PermutationCode(){
        this.code = this.initEncoder();
    }

    //Create a new instance of the encoder/decoder with the given code 
    PermutationCode(ArrayList<Character> code){
        this.code = code;
    }

    /** Initialize the encoding permutation of the characters */
    ArrayList<Character> initEncoder(){
        ArrayList<Character> copy = 
                new ArrayList<Character>(Arrays.asList(
                        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
                        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
                        't', 'u', 'v', 'w', 'x', 'y', 'z'));
        ArrayList<Character> result = new ArrayList<Character>();

        for(int index = 0; index < 26; index = index + 1){
            result.add(copy.get(this.rand.nextInt(copy.size())));
            copy.remove(result.get(index));
        }
        return result;
    }

    /**
      produce an encoded String from the given String
      @param source the String to encode
      @return the secretly encoded <String>
     */
    String encode(String source){
        String e = "";
        for(int index = 0; index < source.length(); index = index + 1){
            Character c = source.charAt(index);
            e += (this.code.get(this.alphabet.indexOf(c)));
        }
        return e;
    }

    /**
      produce an decoded Stirng from the given String
      @param source the String to decode
      @return the revealed <String>
     */
    String decode(String code){
        String e = "";
        for(int index = 0; index < code.length(); index = index + 1){
            Character c = code.charAt(index);
            e += this.alphabet.get(this.code.indexOf(c));
        } return e;
    } 
}



class ExamplesPermutationCode{
    ArrayList<Character> a1 = 
            new ArrayList<Character>(Arrays.asList(
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
                    't', 'u', 'v', 'w', 'x', 'y', 'z'));

    PermutationCode p1 = new PermutationCode();

    void testEncode(Tester t){
        t.checkExpect(this.p1.initEncoder().size(), 26);
        t.checkExpect(this.p1.decode(this.p1.encode("abc")), "abc");
    }
}


















