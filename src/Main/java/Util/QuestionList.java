package Util;

import BackEnd.Question;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class QuestionList{
    private int size;
    private int N;
    private Question [] table;

    public QuestionList(){
        size = 16;
        int N = 0;
        table = new Question[size];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(Object o) {
        if(o == null) return false;
        if(o.getClass() != Question.class) return false;
        Question question = (Question) o;
        for( int i = 0; i < N; i++){
            if( question.equals(table[i])) return true;
        }
        return false;
    }

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

    public Object[] toArray() {
        return table;
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

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

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    public boolean addAll(Collection<? extends Question> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends Question> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {}

    public Question get(int index) {
        return table[index];
    }

    public Question set(int index, Question element) {
        Question tmp = table[index];
        table[index] = element;
        return tmp;
    }

    public void add(int index, Question element) {}

    public Question remove(int index) {
        return null;
    }

    public int indexOf(Question question) {
        for (int i = 0; i < N; i++) {
           if(question.id == table[i].id)
               return i;
        }
        return -1;
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

    public void SortByDifficulty() { //Sort By difficulty of question
        quickSort(0,N-1);
    }

    public void SortByID() { //Sort By ID
        quickSortID(0, N-1);
    }

    private void quickSortID(int begin, int end) {
        if (begin < end) {
            int partitionIndex = partitionID(begin, end);

            quickSort(begin, partitionIndex-1);
            quickSort(partitionIndex+1, end);
        }
    }

    private int partitionID(int begin, int end) {
        Question pivot = table[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (table[j].id >= pivot.id) {
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
}
