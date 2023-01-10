import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // 508
        // c3 = 1
        // c17 = 15
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ("sdfj sdknfsn. kjfkl sd sjjw qqqq akdksldaskl.");
        System.out.println (Arrays.toString (replace (deserialize (stringBuffer.toString ()), 4, "aaaa")));
    }

    public static Sentence[] deserialize (String text) {
        String[] textWords = text.split (" ");
        for (int i = 0; i < textWords.length; i++) {
            textWords[i] = textWords[i] + " ";
        }
        List<Symbol> symbolList = new ArrayList<> ();
        for (int i = 0; i < textWords.length; i++) {
            String currentTextWord = textWords[i];
            String[] currentSymbols = currentTextWord.split ("");
            for (int j = 0; j < currentSymbols.length; j++) {
                symbolList.add (new Symbol (currentSymbols[j]));
            }
        }
        for (int i = 0; i < symbolList.size (); i++) {
            Symbol symbol = symbolList.get (i);
            if (Pattern.matches("\\p{Punct}", symbol.getValue ())) {
                symbolList.get (i).setPunctuation (true);
            }
        }


        List<Word> words = new ArrayList<> ();
        int startIndexWord = 0;
        for (int i = 0; i < symbolList.size (); i++) {
            if (symbolList.get (i).getValue ().equals (" ")) {
                words.add (new Word ((Symbol[]) symbolList.subList (startIndexWord, i).toArray (new Symbol[0])));
                startIndexWord = i + 1;
            }
        }
        Map<Integer, Integer> ranges = new HashMap<> ();
        String wordEnds = ".!?";
        int startIndex = 0;
        for (int i = 0; i < words.size (); i++) {
            Symbol[] word = words.get (i).getSymbols ();
            for (int j = 0; j < word.length; j++) {
                if (wordEnds.contains (word[j].getValue ())) {
                    ranges.put (startIndex, i);
                    startIndex = i;
                }
            }
        }

        Sentence[] sentences = new Sentence[ranges.size ()];
        int l = 0;
        for (Map.Entry<Integer, Integer> range :
                ranges.entrySet()) {
            sentences[l] = new Sentence (words.subList (range.getKey (), range.getValue () + 1).toArray (new Word[0]));
            l++;
        }
        return sentences;
    }

    public static Sentence[] replace (Sentence[] text, int length, String str) {
        Symbol[] symbols = new Symbol[str.length ()];
        String[] strArr = str.split ("");
        for (int i = 0; i < strArr.length; i++) {
            symbols[i] = new Symbol (strArr[i]);
        }
        List<Word> wordsWithNeededLength = findWords (text, length);
        for (int i = 0; i < text.length; i++) {
            Sentence sentence = text[i];
            for (int j = 0; j < sentence.getWords ().length; j++) {
                Word[] words = sentence.getWords ();
                for (int k = 0; k < words.length; k++) {
                    if (wordsWithNeededLength.contains (words[k])) {
                        words[k] = new Word (symbols);
                    }
                }

            }
        }
        return text;
    }

    private static List<Word> findWords (Sentence[] sentences, int length) {
        List<Word> words = new ArrayList<> ();
        for (int i = 0; i < sentences.length; i++) {
            words.addAll (Arrays.asList (sentences[ i ].getWords ()));
        }
        return words.stream().filter (word -> word.getSymbols ().length == length).collect (Collectors.toList ());
    }
}
