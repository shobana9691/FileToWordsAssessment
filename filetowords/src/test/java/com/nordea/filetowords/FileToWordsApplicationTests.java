package com.nordea.filetowords;

import com.nordea.filetowords.service.FileToWordServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static com.nordea.filetowords.util.UtilTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;

@TestPropertySource("/")
@SpringBootTest
class FileToWordsApplicationTests {

	private static final Logger log =  LoggerFactory.getLogger(FileToWordsApplicationTests.class);

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private FileToWordServiceImpl fileToWordService;


	static File file;
	public static String fileLocation =CLASSPATH_URL_PREFIX+ FILE;
	@BeforeAll
	@DisplayName("Loading file for Unit tests")
	public static void  loadFile() {
		try {
			file = ResourceUtils.getFile(fileLocation);
			assertNotNull(file);
		} catch (FileNotFoundException e) {
			log.error("Error in locating file at {} and exception is {} " + fileLocation, e.getMessage(), e);
		} catch (Exception e) {
			log.error("Error in loading file exception is  " + e.getMessage(), e);
		}
	}



	@Test
	@DisplayName("Test XML and CSV file generation")
	public void testFileExists() {
		try {

			assertNotNull(fileToWordService.generateXML(fileToWordService.computeLines(new File(RESOURCES_FILE))));
		} catch (Exception e) {
			log.error("Error in XML and CSV generation from file "+  e.getMessage(), e );

		}

	}



	@Test
	@DisplayName("Lines from file to format")
	public void testSmallFileFormatter()  {

		try {
			assertEquals(3, fileToWordService.computeLines(file).getSentenceList().size() , "Files contain 3 lines");
		} catch (Exception e) {
			log.error("Error in computing lines from file "+  e.getMessage(), e );

		}
	}


	@Test
	@DisplayName("Words from file must not be empty")
	public void testWordsNotEmpty()  {

		try {
			assertFalse(fileToWordService.computeLines(file).getSentenceList().get(0).getWordsList().isEmpty() , "Words must be retrived properly");
		} catch (Exception e) {
			log.error("Error in computing words from file "+  e.getMessage(), e );

		}
	}

	@Test
	@DisplayName("Words count from file to format")
	public void testWordsFileFormatter()  {

		try {

			assertEquals(5, fileToWordService.computeLines(file).getSentenceList().get(0).getWordsList().size() , "Files contain 5 word in first line");
		} catch (Exception e) {
			log.error("Error in computing words from file "+  e.getMessage(), e );

		}
	}



	@DisplayName("Test each word from line")
	@ParameterizedTest(name="value={0},expected{1}")
	@CsvFileSource(resources = "/TestData.csv")
	public void testWordsExpected(int value, String expected)  {

		try {

			assertEquals(expected,fileToWordService.computeLines(file).getSentenceList().get(0).getWordsList().get(value));
		} catch (Exception e) {
			log.error("Error in computing words from file "+  e.getMessage(), e );

		}
	}



}
