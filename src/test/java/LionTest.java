package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    Lion lion;

    @Mock
    Feline feline;

    @Before
    public void setLion() throws Exception {
        lion = new Lion("Самец", feline);
    }

    @Test
    public void getHasManeForMale() {
        boolean actual = lion.doesHaveMane();
        assertTrue(actual);
    }

    @Test
    public void getHasManeForFemale() throws Exception {
        Lion femaleLion = new Lion("Самка", feline);
        boolean actual = femaleLion.doesHaveMane();
        assertFalse(actual);
    }

    @Test
    public void getHasManeForInvalidData(){
        Exception exception = assertThrows(Exception.class, () ->
                lion = new Lion("invalidData", feline));
        String expectedMessage = "Используйте допустимые значения пола животного - самей или самка";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getKittens() {
        Mockito.when(feline.getKittens()).thenReturn(1);
        int actual = lion.getKittens();
        assertEquals(1, actual);
    }

    @Test
    public void getFood() throws Exception {
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(expected);
        assertEquals(expected, lion.getFood());
    }

}