package parser;

import scanner.token.Token;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Created by mohammad hosein on 6/25/2015.
 */

public class ParseTable {
    private ArrayList<Map<Token, Action>> actionTable;
    private ArrayList<Map<NonTerminal, Integer>> gotoTable;

    public ParseTable(String jsonTable) throws Exception {
        jsonTable = jsonTable.substring(2, jsonTable.length() - 2);
        String[] Rows = jsonTable.split("\\],\\[");
        Map<Integer, Token> terminals = new HashMap<Integer, Token>();
        Map<Integer, NonTerminal> nonTerminals = new HashMap<Integer, NonTerminal>();
        Rows[0] = Rows[0].substring(1, Rows[0].length() - 1);
        String[] cols = Rows[0].split("\",\"");
        for (int i = 1; i < cols.length; i++) {
            if (cols[i].startsWith("Goto")) {
                String temp = cols[i].substring(5);
                try {
                    nonTerminals.put(i, NonTerminal.valueOf(temp));
                } catch (Exception e) {
                    temp = temp;
                }
            } else {
                terminals.put(i, new Token(Token.getTyepFormString(cols[i]), cols[i]));
            }
        }
        actionTable = new ArrayList<Map<Token, Action>>();
        gotoTable = new ArrayList<Map<NonTerminal, Integer>>();
        for (int i = 1; i < Rows.length; i++) {
            if (i == 100) {
                int a = 1;
                a++;
            }
            Rows[i] = Rows[i].substring(1, Rows[i].length() - 1);
            cols = Rows[i].split("\",\"");
            actionTable.add(new HashMap<Token, Action>());
            gotoTable.add(new HashMap<>());
            for (int j = 1; j < cols.length; j++) {
                if (!cols[j].equals("")) {
                    if (cols[j].equals("acc")) {
                        actionTable.get(actionTable.size() - 1).put(terminals.get(j), new Action(act.accept, 0));
                    } else if (terminals.containsKey(j)) {
//                        try {
                        Token t = terminals.get(j);
                        Action a = new Action(cols[j].charAt(0) == 'r' ? act.reduce : act.shift, Integer.parseInt(cols[j].substring(1)));
                        actionTable.get(actionTable.size() - 1).put(t, a);
//                        }catch (StringIndexOutOfBoundsException e){
//                            e.printStackTrace();
//                        }
                    } else if (nonTerminals.containsKey(j)) {
                        gotoTable.get(gotoTable.size() - 1).put(nonTerminals.get(j), Integer.parseInt(cols[j]));
                    } else {
                        throw new Exception();
                    }
                }
            }
        }
    }

    public int getGotoTable(int currentState, NonTerminal variable) {
//        try {
        return gotoTable.get(currentState).get(variable);
//        }catch (NullPointerException dd)
//        {
//            dd.printStackTrace();
//        }
//        return 0;
    }

    public Action getActionTable(int currentState, Token terminal) {
        return actionTable.get(currentState).get(terminal);
    }

    @Override
    public String toString() {
        return "ParseTable{" +
                "actionTable=" + actionTable +
                ", gotoTable=" + gotoTable +
                '}';
    }


    public void trimToSize() {
        actionTable.trimToSize();
    }

    public void ensureCapacity(int minCapacity) {
        actionTable.ensureCapacity(minCapacity);
    }

    public int size() {
        return actionTable.size();
    }

    public boolean isEmpty() {
        return actionTable.isEmpty();
    }

    public boolean contains(Object o) {
        return actionTable.contains(o);
    }

    public int indexOf(Object o) {
        return actionTable.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return actionTable.lastIndexOf(o);
    }

    public Object[] toArray() {
        return actionTable.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return actionTable.toArray(a);
    }

    public Map<Token, Action> get(int index) {
        return actionTable.get(index);
    }

    public Map<Token, Action> set(int index, Map<Token, Action> element) {
        return actionTable.set(index, element);
    }

    public boolean add(Map<Token, Action> tokenActionMap) {
        return actionTable.add(tokenActionMap);
    }

    public void add(int index, Map<Token, Action> element) {
        actionTable.add(index, element);
    }

    public Map<Token, Action> remove(int index) {
        return actionTable.remove(index);
    }

    public boolean remove(Object o) {
        return actionTable.remove(o);
    }

    public void clear() {
        actionTable.clear();
    }

    public boolean addAll(Collection<? extends Map<Token, Action>> c) {
        return actionTable.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends Map<Token, Action>> c) {
        return actionTable.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return actionTable.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return actionTable.retainAll(c);
    }

    public ListIterator<Map<Token, Action>> listIterator(int index) {
        return actionTable.listIterator(index);
    }

    public ListIterator<Map<Token, Action>> listIterator() {
        return actionTable.listIterator();
    }

    public Iterator<Map<Token, Action>> iterator() {
        return actionTable.iterator();
    }

    public List<Map<Token, Action>> subList(int fromIndex, int toIndex) {
        return actionTable.subList(fromIndex, toIndex);
    }

    public void forEach(Consumer<? super Map<Token, Action>> action) {
        actionTable.forEach(action);
    }

    public Spliterator<Map<Token, Action>> spliterator() {
        return actionTable.spliterator();
    }

    public boolean removeIf(Predicate<? super Map<Token, Action>> filter) {
        return actionTable.removeIf(filter);
    }

    public void replaceAll(UnaryOperator<Map<Token, Action>> operator) {
        actionTable.replaceAll(operator);
    }

    public void sort(Comparator<? super Map<Token, Action>> c) {
        actionTable.sort(c);
    }

    public boolean containsAll(Collection<?> c) {
        return actionTable.containsAll(c);
    }

    public <T> T[] toArray(IntFunction<T[]> generator) {
        return actionTable.toArray(generator);
    }

    public Stream<Map<Token, Action>> stream() {
        return actionTable.stream();
    }

    public Stream<Map<Token, Action>> parallelStream() {
        return actionTable.parallelStream();
    }
}
