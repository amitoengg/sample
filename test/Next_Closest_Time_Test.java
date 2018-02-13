import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Next_Closest_Time_Test {

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}

//	@Test
//	@DisplayName("First test")
//	void firstTest() {
//		String input = "19:34";
//		Next_Closest_Time nct = new Next_Closest_Time();
//		String result = nct.nextClosestTime(input);
//		assertEquals(result, "19:39");
//
//	}

	// static Stream<Arguments> stringIntAndListProvider() {
	// return Stream.of(Arguments.of("foo", 1, Arrays.asList("a", "b")),
	// Arguments.of("bar", 2, Arrays.asList("x", "y")));
	// }

	static Stream<Arguments> stringAndStringProvider() {
		return Stream.of(Arguments.of("19:34", "19:39"), Arguments.of("23:59", "22:22"));
	}

	@ParameterizedTest
	@MethodSource("stringAndStringProvider")
	void test1(String input, String expected_output) {
		Next_Closest_Time nct = new Next_Closest_Time();
		String result = nct.nextClosestTime(input);
		assertEquals(result, expected_output);
	}

}
