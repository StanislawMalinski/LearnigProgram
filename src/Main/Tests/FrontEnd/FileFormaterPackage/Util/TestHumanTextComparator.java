package Util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class TestHumanTextComparator {
    @Test
    public void Test0Mode(){
        assertTrue(HumanTextComparator.CompareWithLevel("Hahaha", "Hahaha", 0,0));
        assertTrue(HumanTextComparator.CompareWithLevel("Hahaha", "Hahahe", 0,1));
        assertTrue(HumanTextComparator.CompareWithLevel("Hahaha", "Hahehe", 0,2));
        assertTrue(HumanTextComparator.CompareWithLevel("Hahaha", "Hehehe", 0,3));

        assertFalse(HumanTextComparator.CompareWithLevel("HahaHa", "Hahaha", 0,0));
    }

    @Test
    public void Test1Mode(){
        assertTrue(HumanTextComparator.CompareWithLevel("HaHaHa", "hahaha", 1,0));
        assertTrue(HumanTextComparator.CompareWithLevel("HaHaHa", "hahahe", 1,1));
        assertTrue(HumanTextComparator.CompareWithLevel("HaHaHa", "hahehe", 1,2));
        assertTrue(HumanTextComparator.CompareWithLevel("HaHaHa", "hehehe", 1,3));

        assertFalse(HumanTextComparator.CompareWithLevel("HaHaHa", "haheha", 1,0));
    }

    @Test
    public void Test2Mode(){
        assertTrue(HumanTextComparator.CompareWithLevel("Hahaha", "Ha ha ha     ", 2,0));
        assertTrue(HumanTextComparator.CompareWithLevel("Hahaha", "Ha ha he     ", 2,1));
        assertTrue(HumanTextComparator.CompareWithLevel("Hahaha", "Ha he he     ", 2,2));
        assertTrue(HumanTextComparator.CompareWithLevel("Hahaha", "He he he     ", 2,3));

        assertFalse(HumanTextComparator.CompareWithLevel("HahaHa", "Ha ha ha   ", 2,0));
    }

    @Test
    public void Test3Mode(){
        assertTrue(HumanTextComparator.CompareWithLevel("HaHaHa", "Ha ha ha     ", 3,0));
        assertTrue(HumanTextComparator.CompareWithLevel("HaHaHa", "Ha ha he     ", 3,1));
        assertTrue(HumanTextComparator.CompareWithLevel("HaHaHa", "Ha he he     ", 3,2));
        assertTrue(HumanTextComparator.CompareWithLevel("HaHaHa", "He he he     ", 3,3));

        assertFalse(HumanTextComparator.CompareWithLevel("$aHaHa", "Ha ha ha     ", 3,0));
    }
}
