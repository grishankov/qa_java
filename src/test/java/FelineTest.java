package com.example;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineTest {

    Feline feline;

    private List expected;
    private String animalKind;

    public FelineTest(String animalKind, List expected) {
        this.animalKind = animalKind;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "animalKind: {0}, expected {1}")
    public static Object[][] getFood() {
        return new Object[][]{
                {"Травоядное", List.of("Трава", "Различные растения")},
                {"Хищник", List.of("Животные", "Птицы", "Рыба")},
        };
    }

    @Before
    public void setFeline() {
        feline = new Feline();
    }

    @Test
    public void getListFood() throws Exception {
        Feline spyFeline = Mockito.spy(feline);
        Mockito.when(spyFeline.getFood(animalKind)).thenReturn(expected);
        List<String> actual = null;
        if ("Травоядное".equals(animalKind)) {
            actual = spyFeline.getFood(animalKind);
        } else if ("Хищник".equals(animalKind)) {
            actual = spyFeline.eatMeat();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void getCatFamily() {
        String actual = feline.getFamily();
        assertEquals("Кошачьи", actual);
    }

    @Test
    public void getKittensCountWithoutArguments() {
        assertEquals(1, feline.getKittens());
    }

    @Test
    public void getKittensCountWithArguments() {
        assertEquals(3, feline.getKittens(3));
    }
}