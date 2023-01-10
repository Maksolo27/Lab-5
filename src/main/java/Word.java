import java.util.Arrays;

public class Word {

    private Symbol[] symbols;

    public Word (Symbol[] symbols) {
        this.symbols = symbols;
    }

    public Symbol[] getSymbols () {
        return symbols;
    }

    @Override
    public String toString () {
        return  "{" + Arrays.toString (symbols) + "}";
    }

    public void setSymbols (Symbol[] symbols) {
        this.symbols = symbols;
    }
}
