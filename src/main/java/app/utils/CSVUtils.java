package app.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CSVUtils {

	public static List<String[]> readCSV(String filePath) throws IOException {
		List<String[]> records = new ArrayList<>();
		try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
			String[] record;
			while ((record = csvReader.readNext()) != null) {
				String[] parsedRecord = parseCSVRecord(record[0]);
//				
				records.add(parsedRecord);
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return records;
	}

	private static String[] parseCSVRecord(String csvRecord) {
		return csvRecord.split(";");
	}

}