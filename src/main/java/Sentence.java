import java.util.Arrays;

public class Sentence {

    private Word[] words;

    public Sentence (Word[] symbols) {
        this.words = symbols;
    }

    public Word[] getWords () {
        return words;
    }

    @Override
    public String toString () {
        return "[" + Arrays.toString (words) + "]";
    }

    public void setWords (Word[] words) {
        this.words = words;
    }
}
