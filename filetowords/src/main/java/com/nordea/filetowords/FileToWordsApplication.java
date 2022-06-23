package com.nordea.filetowords;

import com.nordea.filetowords.service.FileToWordServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;


@SpringBootApplication
public class FileToWordsApplication {

	@Autowired
	private static FileToWordServiceImpl fileToWordServiceImpl = new FileToWordServiceImpl();
	private static final Logger log =  LoggerFactory.getLogger(FileToWordsApplication.class);


	public static void main(String[] args) throws IOException {

		try {
			System.out.println("Enter the absolute path to the file(till file name) : ");
			Scanner scanner = new Scanner(System.in);
			String inputFilePath = scanner.nextLine().trim();
			System.out.println("Entered path is : " + inputFilePath);

			if(inputFilePath.length()==0)
			{
				System.out.println("File is empty!");
				log.info("Provided file is empty!");
			}
			else {
				String outputFilePath = fileToWordServiceImpl.generateXML(fileToWordServiceImpl.computeLines(new File(inputFilePath)));
				System.out.println("File is generated and saved in code project folder! "+outputFilePath);
			}

		}
		catch(NoSuchFileException exception)
		{
			System.out.println("Error no file found in provide path!!!");
			log.error("Error in loading file from provided path : "+ exception.getMessage(), exception );
		}

		SpringApplication.run(FileToWordsApplication.class, args);
	}

}
