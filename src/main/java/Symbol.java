import java.util.Objects;

public class Symbol {

    private String value;
    private boolean isPunctuation;

    @Override
    public String toString () {
        return value;
    }

    public Symbol (String value, boolean isPunctuation) {
        this.value = value;
        this.isPunctuation = isPunctuation;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Symbol symbol = (Symbol) o;
        return Objects.equals (value, symbol.value);
    }

    @Override
    public int hashCode () {
        return Objects.hash (value);
    }

    public Symbol (String value) {
        this.value = value;
    }

    public String getValue () {
        return value;
    }

    public void setValue (String value) {
        this.value = value;
    }

    public boolean isPunctuation () {
        return isPunctuation;
    }

    public void setPunctuation (boolean punctuation) {
        isPunctuation = punctuation;
    }
}
