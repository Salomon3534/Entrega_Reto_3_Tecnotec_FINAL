package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

	private static final String FILE_PATH = "log.txt";
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public void writeLog(String action) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
		String timestamp = LocalDateTime.now().format(formatter);
		writer.write("[" + timestamp + "] ACCIÓN: " + action);
		writer.newLine();
	}

	public String readLog() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("\n--- CONTENIDO DEL FICHERO LOG ---\n");

		BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\n");
		}

		return sb.toString();
	}
}