import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HorseTest {
    private Horse horse;
    private MockedStatic<Horse> horseMockedStatic;

    @BeforeEach
    void setUp() {
        horseMockedStatic = Mockito.mockStatic(Horse.class);
        horse = new Horse("Mary", 20., 105.);
    }

    @AfterEach
    void tearDown() {
        horseMockedStatic.close();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("when HorseName isNull throw IllegalArgumentException")
    void when_HorseNameIsNull_Throw_IllegalArgumentException(String name) {
        assertThrows(

                IllegalArgumentException.class,
                () -> {
                    //throw new IllegalArgumentException("Exception message");
                    horse = new Horse(name, 10, 200);
                });
    }

    //@Test
    @ParameterizedTest
    @NullSource
    @DisplayName("when HorseName isNull return 'NameCannotBeNull.'")
    void whenHorseNameIsNull_Return_NameCannotBeNull(String name) {
        Throwable exception =
                assertThrows(

                        IllegalArgumentException.class,
                        () -> {
                            //throw new IllegalArgumentException("Exception message");
                            horse = new Horse(name, 10, 200);
                        }
                );
        assertEquals("Name cannot be null.", exception.getMessage());

    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    @DisplayName("when HorseName is whitespace " +
            "or contents whitespace characters throw IllegalArgumentException")
    void whenHorseName_is_whitespace_orContentsWhitespaceCharacters_Throw_IllegalArgumentException(String name) {
        assertThrows(

                IllegalArgumentException.class,
                () -> {
                    //throw new IllegalArgumentException("Exception message");
                    horse = new Horse(name, 10, 200);
                }
        );
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    @DisplayName("when HorseName is whitespace " +
            "or contents whitespace characters return 'Name cannot be blank.'")
    void whenHorseName_is_whitespace_orContentsWhitespaceCharacters_return_NameCannotBeBlank(String name) {
        Throwable exception =
                assertThrows(

                        IllegalArgumentException.class,
                        () -> {
                            //throw new IllegalArgumentException("Exception message");
                            horse = new Horse(name, 10, 200);
                        }
                );
        assertEquals("Name cannot be blank.", exception.getMessage());

    }


    @Test
    @DisplayName("when HorseSpeed is Negative Throw IllegalArgumentException")
    void when_HorseSpeedIsNegative_Throw_IllegalArgumentException() {
        assertThrows(

                IllegalArgumentException.class,
                () -> {
                    //throw new IllegalArgumentException("Exception message");
                    horse = new Horse("name", -10, 200);
                }
        );
    }

    @Test
    @DisplayName("when HorseSpeed is Negative return 'Speed cannot be negative.'")
    void when_HorseSpeedIsNegative_Return_SpeedCannotBeNegative() {
        Throwable exception =
                assertThrows(

                        IllegalArgumentException.class,
                        () -> {
                            //throw new IllegalArgumentException("Exception message");
                            horse = new Horse("name", -10, 200);
                        }
                );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("when HorseDistance is Negative Throw IllegalArgumentException")
    void when_HorseDistanceIsNegative_Throw_IllegalArgumentException() {
        assertThrows(

                IllegalArgumentException.class,
                () -> {
                    //throw new IllegalArgumentException("Exception message");
                    horse = new Horse("name", 10, -200);
                }
        );
    }

    @Test
    @DisplayName("when HorseDistance is Negative return 'Speed cannot be negative.'")
    void when_HorseDistanceIsNegative_Return_SpeedCannotBeNegative() {
        Throwable exception =
                assertThrows(

                        IllegalArgumentException.class,
                        () -> {
                            //throw new IllegalArgumentException("Exception message");
                            horse = new Horse("name", 10, -200);
                        }
                );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void whenCallGetNameReturnName() {
        String expected = "Mary";
        String actual = horse.getName();
        assertEquals(expected, actual);
    }

    @Test
    void whenCallGetSpeedReturnSpeed() {
        double expected = 20;
        double actual = horse.getSpeed();
        assertEquals(expected, actual);
    }

    @Test
    void whenCallGetDistanceReturnDistance() {
        double expected = 105;
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void moveShouldCallGetRandomDouble() {
        //given
         //horseMockedStatic = Mockito.mockStatic(Horse.class);
        horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.8)).thenReturn(0.5);
        //when
        double actual = Horse.getRandomDouble(0.2, 0.8);
        //then
        assertEquals(0.5, actual);
        horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.8));
    }

    //@Test
    @ParameterizedTest
    //@ValueSource(doubles = {0.3, 0.7, 0.9})
    @CsvSource({"0.3", "0.7", "0.9"})
    void moveShouldCalculateDistanceCorrectly(double mockRandomValue) {
        //given
         //horseMockedStatic = Mockito.mockStatic(Horse.class);
        horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockRandomValue);
        //when
        double expectedDistance = horse.getDistance() + horse.getSpeed() * mockRandomValue;
        horse.move();
        //then
        assertEquals(expectedDistance, horse.getDistance());
        horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
    }
}