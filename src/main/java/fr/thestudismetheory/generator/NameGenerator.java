package fr.thestudismetheory.generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameGenerator {
    ArrayList<String> pre = new ArrayList<String>();
    ArrayList<String> mid = new ArrayList<String>();
    ArrayList<String> sur = new ArrayList<String>();

    final private static char[] vocals = { 'a', 'e', 'i', 'o', 'u', 'y' };
    final private static char[] consonants = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p',
            'q', 'r', 's', 't', 'v', 'w', 'x', 'y' };

    public NameGenerator() throws IOException {
        FileReader input =  new FileReader(""); // TODO : Fichier à placer
        BufferedReader bufRead = new BufferedReader(input);
        String line = "";

        while(line != null){
            line = bufRead.readLine();
            if(line != null && !line.equals("")){
                if(line.charAt(0) == '-'){
                    pre.add(line.substring(1).toLowerCase());
                }
                else if(line.charAt(0) == '+'){
                    sur.add(line.substring(1).toLowerCase());
                }
                else{
                    mid.add(line.toLowerCase());
                }
            }
        }
        bufRead.close();
    }

    private String upper(String s){
        return s.substring(0,1).toUpperCase().concat(s.substring(1));
    }

    private boolean containsConsFirst(ArrayList<String> array){
        for(String s: array){
            if(consonantFirst(s)) return true;
        }
        return false;
    }

    private boolean containsVocFirst(ArrayList<String> array){
        for(String s: array){
            if(vocalFirst(s)) return true;
        }
        return false;
    }

    private boolean allowCons(ArrayList<String> array){
        for(String s: array){
            if(hatesPreviousVocals(s) || hatesPreviousConsonants(s) == false) return true;
        }
        return false;
    }

    private boolean allowVocs(ArrayList<String> array){
        for(String s: array){
            if(hatesPreviousConsonants(s) || hatesPreviousVocals(s) == false) return true;
        }
        return false;
    }

    private boolean expectsVocal(String s){
        if(s.substring(1).contains("+v")) return true;
        else return false;
    }
    private boolean expectsConsonant(String s){
        if(s.substring(1).contains("+c")) return true;
        else return false;
    }
    private boolean hatesPreviousVocals(String s){
        if(s.substring(1).contains("-c")) return true;
        else return false;
    }
    private boolean hatesPreviousConsonants(String s){
        if(s.substring(1).contains("-v")) return true;
        else return false;
    }

    private String pureSyl(String s){
        s = s.trim();
        if(s.charAt(0) == '+' || s.charAt(0) == '-') s = s.substring(1);
        return s.split(" ")[0];
    }

    private boolean vocalFirst(String s){
        return (String.copyValueOf(vocals).contains(String.valueOf(s.charAt(0)).toLowerCase()));
    }

    private boolean consonantFirst(String s){
        return (String.copyValueOf(consonants).contains(String.valueOf(s.charAt(0)).toLowerCase()));
    }

    private boolean vocalLast(String s){
        return (String.copyValueOf(vocals).contains(String.valueOf(s.charAt(s.length()-1)).toLowerCase()));
    }

    private boolean consonantLast(String s){
        return (String.copyValueOf(consonants).contains(String.valueOf(s.charAt(s.length()-1)).toLowerCase()));
    }

    public String compose(int syls){
        int expecting = 0;
        int last = 0;

        String name;
        int a = (int)(Math.random() * pre.size());

        if(vocalLast(pureSyl(pre.get(a)))) last = 1;
        else last = 2;

        if(syls > 2){
            if(expectsVocal(pre.get(a))) expecting = 1;
            if(expectsConsonant(pre.get(a))) expecting = 2;
        }
        else{
            if(expectsVocal(pre.get(a))) expecting = 1;
            if(expectsConsonant(pre.get(a))) expecting = 2;
        }

        int b[] = new int[syls];
        for(int i = 0; i<b.length-2; i++){

            do{
                b[i] = (int)(Math.random() * mid.size());
            }
            while(expecting == 1 && vocalFirst(pureSyl(mid.get(b[i]))) == false || expecting == 2 && consonantFirst(pureSyl(mid.get(b[i]))) == false
                    || last == 1 && hatesPreviousVocals(mid.get(b[i])) || last == 2 && hatesPreviousConsonants(mid.get(b[i])));

            expecting = 0;
            if(expectsVocal(mid.get(b[i]))) expecting = 1;

            if(expectsConsonant(mid.get(b[i]))) expecting = 2;

            if(vocalLast(pureSyl(mid.get(b[i])))) last = 1;
            else last = 2;
        }

        int c;
        do{
            c = (int)(Math.random() * sur.size());
        }
        while(expecting == 1 && vocalFirst(pureSyl(sur.get(c))) == false || expecting == 2 && consonantFirst(pureSyl(sur.get(c))) == false
                || last == 1 && hatesPreviousVocals(sur.get(c)) || last == 2 && hatesPreviousConsonants(sur.get(c)));

        name = upper(pureSyl(pre.get(a).toLowerCase()));
        for(int i = 0; i<b.length-2; i++){
            name = name.concat(pureSyl(mid.get(b[i]).toLowerCase()));
        }
        if(syls > 1)
            name = name.concat(pureSyl(sur.get(c).toLowerCase()));
        return name;
    }
}
