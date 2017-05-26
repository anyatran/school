/**
 * Interactions driver for the Eliza game.
 * 
 * @since 16 October 2013 
 */

// Tran, Anh
// anhtran9
// Nguyen, Stephanie
// snguyen

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tester.Tester;

public class Interactions {

    /**
     * Run the program - starting with the <code>eliza</code>
     * method.
     * 
     * @param args unused
     */
    public static void main(String[] args) {   

        Interactions i = new Interactions();
        i.eliza();
    }

    /**
     * Run the Eliza game
     */ 
    public void eliza() {

        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ask me a question. \n:>");
        try {
            String s = input.readLine();
            String a = "";
            Eliza e = new Eliza();
            e.init();
         


            while (s != null && s.length() > 0) {
                s = e.firstWord(s);
                if (s.equals(e.replies.get(0).keyword)) { // who
                    a = e.replies.get(0).randomAnswer(s);
                }
                else if (s.equals(e.replies.get(1).keyword)) { // why
                    a = e.replies.get(1).randomAnswer(s);
                }
                else if (s.equals(e.replies.get(2).keyword)) { // 
                    a = e.replies.get(2).randomAnswer(s);
                }
                else if (s.equals(e.replies.get(3).keyword)) { // 
                    a = e.replies.get(3).randomAnswer(s);
                }
                else if (s.equals(e.replies.get(4).keyword)) { // 
                    a = e.replies.get(4).randomAnswer(s);
                }
                else if (s.equals(e.replies.get(5).keyword)) { // 
                    a = e.replies.get(5).randomAnswer(s);
                }
                else {
                    a = e.replies.get(6).randomAnswer(s);
                }


                // mock code: echo the first word
                System.out.println(a);   // REPLACE THIS WITH YOUR CODE !!!
                // that finds out the reply to the question s
                // and prints the reply

                System.out.println(":>");
                s = input.readLine();
                if (s == null || s.length() == 0) {
                    System.out.println("Goodbye");
                }
            }



        }

        catch (IOException e) {
            System.out.println("Goodbye");
        }  
    }
}


// represents a reply
/*
 Template:
 this.keyword              -- String
 this.answer               -- ArrayList<String>
 
 Methods:
 this.randomAnswer(String) -- String
 
 */
class Reply {
    String keyword;
    ArrayList<String> answers;
    Reply(String keyword, ArrayList<String> answers) {
        this.keyword = keyword;
        this.answers = answers;
    }

    // choose a random answer from a list of answers
    public String randomAnswer(String question) {
        /** A random number generator */
        Random rand = new Random();
        Integer randnum = rand.nextInt(this.answers.size());
        return this.answers.get(randnum);
    }

}

// represents Eliza
/*
 Template:
 this.replies             -- ArrayList<Reply>
 
 Methods:
 this.firstWord(String)   -- String
 */
class Eliza {
    ArrayList<Reply> replies;
    Eliza() {
        this.replies = new ArrayList<Reply>();
    }

    public void init() {
        ArrayList<Reply> localReplies = new ArrayList<Reply>();
        ArrayList<String> who = new ArrayList<String>(
                Arrays.asList("you", "no one", "me"));
        ArrayList<String> why = new ArrayList<String>(
                Arrays.asList("because google said so", 
                "that's impossible", "that's how it work"));
        ArrayList<String> what = new ArrayList<String>(
                Arrays.asList("air", "nonsense", "nothing"));
        ArrayList<String> when = new ArrayList<String>(
                Arrays.asList("today", "tomorrow", "never"));
        ArrayList<String> how = new ArrayList<String>(
                Arrays.asList("I don't know, ask google", "I'm not sure",
                "apparently that's how it works"));
        ArrayList<String> where = new ArrayList<String>(
                Arrays.asList("right next to you", "nowhere", "in Alaska"));
        ArrayList<String> other = new ArrayList<String>(
                Arrays.asList("I do not know", "Why do you want to know?"));

        localReplies.add(new Reply("who", who));
        localReplies.add(new Reply("why", why));
        localReplies.add(new Reply("what", what));
        localReplies.add(new Reply("when", when));
        localReplies.add(new Reply("how", how));
        localReplies.add(new Reply("where", where));
        localReplies.add(new Reply("other", other));

        this.replies = localReplies;
    }

    // get the first word of the question
    public String firstWord(String question) {
        String first = "";
        if (question.contains(" ")) {
            first = question.substring(0, question.indexOf(" ")).toLowerCase();
        }
        else {
            first = question.toLowerCase();
        }

        return first;
    }
}


class ExamplesEliza {
    ArrayList<String> a1 = new ArrayList<String>();
    ArrayList<String> a2 = new ArrayList<String>();
    

    Eliza e1 = new Eliza();

    Reply why = new Reply("why", this.a1);
    Reply what = new Reply("what", this.a2);

    Interactions i = new Interactions();

    void init1() {
        a1.add("yes");
        a1.add("no");
        a1.add("maybe");

        a2.add("apple");
        a2.add("nothing");
        a2.add("water");

        this.i.eliza();
    }

    void testRandom(Tester t) {
        e1.init();
        init1();
        //this.i.eliza();

        t.checkOneOf("Random answer",
                this.why.randomAnswer("why are you here"),
                "yes", "no", "maybe");
        t.checkOneOf("random 2",
                this.e1.replies.get(0).randomAnswer("who are you?"),
                "you", "no one", "me");

        t.checkExpect(this.e1.firstWord("What is it?"), "what");
        t.checkExpect(this.e1.firstWord("wHy"), "why");
        //t.checkExpect(this.i.);

    }

}













