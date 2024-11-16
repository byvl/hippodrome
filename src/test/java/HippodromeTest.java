import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    private Hippodrome hippodrome;
    private List<Horse> horses;

    @BeforeEach
    void setUp() {
        horses = new ArrayList<Horse>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("name" + i, i * 3, i * 4));

        }
        hippodrome = new Hippodrome(horses);
    }


    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("when list horses isNull throw IllegalArgumentException")
    void whenListHorsesIsNullThrowIllegalArgumentException(List<Horse> horses) {
        assertThrows(

                IllegalArgumentException.class,
                () -> {
                    //throw new IllegalArgumentException("Exception message");
                    hippodrome = new Hippodrome(horses);
                });
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("when list horses isNull return 'Horses cannot be null.'")
    void whenListHorsesIsNullReturnHorsesCannotBeNull(List<Horse> horses) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    //throw new IllegalArgumentException("Exception message");
                    hippodrome = new Hippodrome(horses);
                });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("when list horses isEmpty return 'Horses cannot be empty.")
    void whenListHorsesIsEmptyReturnHorsesCannotBeEmpty(List<Horse> horses) {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    //throw new IllegalArgumentException("Exception message");
                    hippodrome = new Hippodrome(horses);
                });
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("when list horses isEmpty throw IllegalArgumentException")
    void whenListHorsesIsEmptyThrowIllegalArgumentException(List<Horse> horses) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    //throw new IllegalArgumentException("Exception message");
                    hippodrome = new Hippodrome(horses);
                });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }


    @Test
    void moveShouldCalculateDistanceCorrectly() {
        List<Horse> expected = horses;
        List<Horse> actual = hippodrome.getHorses();
        assertIterableEquals(expected, actual);
        assertEquals(30, actual.size());
        assertEquals("name22", actual.get(22).getName());
    }

    @Test
    void moveShouldCallMoveOnAllHorses() {
        Horse horseMocked = Mockito.mock(Horse.class);
        for (int i = 0; i < 50; i++) {
            horses.add(horseMocked);
        }
        hippodrome.move();
        verify(horseMocked, Mockito.times(50)).move();
    }

    @Test
    void getWinnerShouldReturnHorseWithMaxDistance() {
        hippodrome.move();
        Horse actualWinner = hippodrome.getWinner();
        horses.sort((o1, o2) -> Double.compare(o2.getDistance(), o1.getDistance()));
        Horse expectedWinner = horses.get(0);
        assertEquals(expectedWinner, actualWinner);
    }

}