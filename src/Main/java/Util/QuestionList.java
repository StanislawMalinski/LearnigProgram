package Util;

import BackEnd.Question;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class QuestionList implements List<Question> {
    private int size;
    private int N;
    private Question [] table;

    public QuestionList(){
        size = 16;
        int N = 0;
        table = new Question[size];
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public boolean contains(Object o) {
        if(o == null) return false;
        if(o.getClass() != Question.class) return false;
        Question question = (Question) o;
        for( int i = 0; i < N; i++){
            if( question.equals(table[i])) return true;
        }
        return false;
    }

    @Override
    public Iterator<Question> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Question question) {
        if( N+1 >= size ) doubleSize();
        table[N++] = question;
        return true;
    }

    private void doubleSize() {
        Question [] ntable = new Question[size*2];
        System.arraycopy(table,0,ntable,0,N);
        size = size*2;
        table = ntable;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Question> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Question> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Question get(int index) {
        return table[index];
    }

    @Override
    public Question set(int index, Question element) {
        return null;
    }

    @Override
    public void add(int index, Question element) {

    }

    @Override
    public Question remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Question> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Question> listIterator(int index) {
        return null;
    }

    @Override
    public List<Question> subList(int fromIndex, int toIndex) {
        return null;
    }
}
