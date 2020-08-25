package com.desafio.financeiro.service.relatorios;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.desafio.financeiro.service.exception.NegocioException;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class RelatoriosCSV {
	
	private static final String ATTACHMENT_FILENAME = "attachment; filename=";
	private static final String DELIMITER_CSV_DEFAULT = ";";
	
	private RelatoriosCSV() {
	}
	
	/**
	 * 
	 * @param <T>
	 * @param directory
	 * @param filename
	 * @param dataBase
	 * @param separator
	 * @param clazz
	 */
	public static <T> void generateFileWithHeader(String directory, String filename, List<T> dataBase,
			char separator, Class<?> clazz) {

		String path = FilenameUtils.separatorsToSystem(
				new StringBuilder().append(directory).append(File.separator).append(filename).toString());
		
		createDirectory(FilenameUtils.separatorsToSystem(directory));
		
		try (Writer writer = Files.newBufferedWriter(Paths.get(path))) {

			writer.append(buildHeader(clazz));
			
			StatefulBeanToCsv<T> inventoryListCSV = new StatefulBeanToCsvBuilder<T>(writer)
					.withSeparator(separator)
					.withOrderedResults(true)
					.withApplyQuotesToAll(false)
					.build();
			
			inventoryListCSV.write(dataBase);

		} catch (IOException e) {
			throw new NegocioException();
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			throw new NegocioException();
		}
		
	}
	
	/**
	 * Método responsável por realizar a exportação de um arquivo
	 * @author nrfreire
	 * @since 1.3.0
	 * @param path - Nome do path que será salvo o arquivo
	 * @param fileName - Nome do arquivo que será exportado
	 */
	public static ResponseEntity<byte[]> exportFile(String path, String fileName) {
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT_FILENAME.concat(fileName));
		headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);

		byte[] file;
		try {
			file = Files.readAllBytes(Paths.get(FilenameUtils.separatorsToSystem(path)));
			return ResponseEntity.ok().headers(headers).body(file);
		} catch (IOException e) {
			throw new NegocioException();
		}
	}
	
	/**
	 * Método responsável por fazer a ordenação do header dos arquivos 
	 * csv
	 * 
	 * @param clazz
	 * @return
	 */
	private static String buildHeader(Class<?> clazz) {
		return Arrays.stream(clazz.getDeclaredFields())
				.filter(f -> f.getAnnotation(CsvBindByPosition.class) != null
						&& f.getAnnotation(CsvBindByName.class) != null)
				.sorted(Comparator.comparing(f -> f.getAnnotation(CsvBindByPosition.class).position()))
				.map(f -> f.getAnnotation(CsvBindByName.class).column())
				.collect(Collectors.joining(DELIMITER_CSV_DEFAULT)) + StringUtils.LF;
	}
	
	/**
	 * Método para criar diretório
	 * 
	 * @param directory - Diretório que será criado
	 */
	private static void createDirectory(String directory) {
		File file = new File(directory);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
}
