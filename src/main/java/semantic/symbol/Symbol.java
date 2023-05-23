package semantic.symbol;

import java.util.Objects;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public class Symbol {
    public SymbolType type;
    public int address;

    public Symbol(SymbolType type, int address) {
        this.type = type;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return address == symbol.address && type == symbol.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, address);
    }
}
