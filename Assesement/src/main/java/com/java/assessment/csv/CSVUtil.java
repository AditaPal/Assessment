package com.java.assessment.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.java.assessment.entity.Nace;

public class CSVUtil {

	public static String TYPE = "text/csv";

	public static boolean hasCSVFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<Nace> csvToNaceList(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			List<Nace> naceList = new ArrayList<Nace>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord csvRecord : csvRecords) {
				Nace nace = new Nace(Integer.parseInt(csvRecord.get("Order")), Integer.parseInt(csvRecord.get("Level")),
						csvRecord.get("Code"), csvRecord.get("Parent"), csvRecord.get("Description"),
						csvRecord.get("This item includes"), csvRecord.get("This item also includes"),
						csvRecord.get("Rulings"), csvRecord.get("This item excludes"),
						csvRecord.get("Reference to ISIC Rev. 4"));
				naceList.add(nace);
			}
			return naceList;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
}
