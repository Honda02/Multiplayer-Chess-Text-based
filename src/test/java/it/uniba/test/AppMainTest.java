package it.uniba.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import it.uniba.main.AppMain;

@Tag("AppMain")
public class AppMainTest {

	@BeforeAll
	static void setUpAll() throws Exception {
		System.out.println();
		System.out.println("--- MAIN TEST ---");
		System.out.println("setUpAll()");
	}

	@BeforeEach
	void setUp() {
		System.out.println("  setUp()");
	}

	@Test
	@DisplayName("Testing main() method")
	void testMain() throws IOException {
		String[] args = null;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		final InputStream original = System.in;
		final FileInputStream fips = new FileInputStream(new File("src/test/resources/test_input.txt"));
		System.setIn(fips);
		AppMain.main(args);
		System.setIn(original);

	}

	@AfterEach
	void tearDown() {
		System.out.println("  tearDown()");
	}

	@AfterAll
	static void tearDownAll() {
		System.out.println("tearDownAll()");
		System.out.println("--- MAIN TEST ---");
		System.out.println();

	}

}
