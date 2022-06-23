package com.nordea.filetowords;

import com.nordea.filetowords.service.FileToWordServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static com.nordea.filetowords.util.UtilTest.FILE;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;


@SpringBootTest
class FileToWordsApplicationTests {

	private static final Logger log =  LoggerFactory.getLogger(FileToWordsApplicationTests.class);

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private FileToWordServiceImpl fileToWordService;




	@Test
	void contextLoads() {
	}

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
			assertTrue(fileToWordService.computeLines(file).getSentenceList().get(0).getWordsList().isEmpty() , "Words must be retrived properly");
		} catch (Exception e) {
			log.error("Error in computing lines from file "+  e.getMessage(), e );

		}
	}

	@Test
	@DisplayName("Words from file to format")
	public void testWordsFileFormatter()  {

		try {
			assertEquals(5, fileToWordService.computeLines(file).getSentenceList().get(0).getWordsList().size() , "Files contain 5 word in first line");
		} catch (Exception e) {
			log.error("Error in computing lines from file "+  e.getMessage(), e );

		}
	}

}
