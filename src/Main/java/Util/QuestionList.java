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
        Iterator<Question> iter = new Iterator<Question>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < N;
            }

            @Override
            public Question next() {
                return table[cursor++];
            }
        };
        return iter;
    }

    @Override
    public Object[] toArray() {
        return table;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Question question) {
        if( N >= size ) doubleSize();
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
        Question tmp = table[index];
        table[index] = element;
        return tmp;
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
        Question question;
        try{
            question = (Question) o;
        } catch (ClassCastException e){
            return -1;
        }
        for (int i = 0; i < N; i++) {
           if(question.id == table[i].id)
               return -1;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    private void quickSort(int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(begin, end);

            quickSort(begin, partitionIndex-1);
            quickSort(partitionIndex+1, end);
        }
    }

    private int partition(int begin, int end) {
        Question pivot = table[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (table[j].compareTo(pivot) >= 0) {
                i++;

                Question swapTemp = table[i];
                table[i] = table[j];
                table[j] = swapTemp;
            }
        }

        Question swapTemp = table[i+1];
        table[i+1] = table[end];
        table[end] = swapTemp;

        return i+1;
    }

    @Override
    public ListIterator<Question> listIterator() {
        quickSort(0,N-1);
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
