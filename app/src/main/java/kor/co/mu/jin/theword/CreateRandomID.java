package kor.co.mu.jin.theword;

import java.util.ArrayList;
import java.util.Collections;

public class CreateRandomID {

    ArrayList<String> alpha = new ArrayList<>();
    ArrayList<Integer> numb = new ArrayList<>();

    public String createrandomID(){
        alpha.add("a");
        alpha.add("b");
        alpha.add("c");
        alpha.add("d");
        alpha.add("e");
        alpha.add("f");
        alpha.add("g");
        alpha.add("h");
        alpha.add("i");
        alpha.add("j");
        alpha.add("k");
        alpha.add("l");
        alpha.add("m");
        alpha.add("n");
        alpha.add("o");
        alpha.add("p");
        alpha.add("q");
        alpha.add("r");
        alpha.add("s");
        alpha.add("t");
        alpha.add("u");
        alpha.add("v");
        alpha.add("w");
        alpha.add("x");
        alpha.add("y");
        alpha.add("z");

        for(int i = 0; i<1000000; i++){
            numb.add(i);
        }

        Collections.shuffle(alpha);
        Collections.shuffle(numb);

        String RandomID = alpha.get(0) + numb.get(0);

        return RandomID;
    }

}
