package bcvax.pages;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;

public class Utils {
	public final static SimpleDateFormat LOG_TIMESTAMP_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
	public final static String PATH_TO_CONFIG = Paths.get(System.getProperty("user.dir"), "config.properties").toString();

	private static Properties config = new Properties();

	public static String getTargetEnvironment() throws Exception {
		String env = System.getProperty("environment");
		if (env == null)
			env = getConfigProperty("environment");
		return env;
	}

	public static String getClientsDataFile() throws Exception {
		String client_data_file = Utils.getEnvConfigProperty("client_data_file");
		if (client_data_file == null)
			client_data_file = getConfigProperty("client_data_file");
		return client_data_file;
	}

	public static Boolean isCommunityPortal() throws Exception {
		String env = getTargetEnvironment();
		int ind = env.split("_").length - 1;
		if(env.split("_")[ind].equals("org")) {
			return false;
		} else {
			return true;
		}
	}

    public static String getEnvConfigProperty(String propertyToGet) throws Exception {
		return getConfigProperty(getTargetEnvironment()+"."+propertyToGet);
    }

	public static String getLogTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return LOG_TIMESTAMP_FORMAT.format(timestamp);
	}

	public static void log(String msg){
		System.out.println(getLogTime()+" "+msg);
	}

	public static String getConfigProperty(String targetProperty) throws Exception {
		config.load(new FileInputStream(PATH_TO_CONFIG));
		return config.getProperty(targetProperty);
	}

	public static Boolean shoudIUpdateTestRail() throws Exception {
		String yesORno = System.getProperty("test_rail");
		if (yesORno == null){
			yesORno = getConfigProperty("test_rail");
			return yesORno.equalsIgnoreCase("yes");
		} else
			return yesORno.equalsIgnoreCase("yes");
	}

	public static Map<String, Map<String, Object>> getTestData() throws Exception {
		InputStream input = new FileInputStream("resources/test_data.yml");
		Yaml yaml = new Yaml();
		Map<String, Map<String, Object>> testData = yaml.load(input);
		return testData;
	}

	public static Map<String, Object> getTestData(String env) throws Exception {
		Map<String, Map<String, Object>> testData = getTestData();
		return testData.get(env.replace("_org", ""));
	}

	public static Map<String, String> getTestClientData(String data_file_path, String purpose) {
		HashMap my_map = new HashMap();
		try (CSVReader reader = new CSVReader(new FileReader(data_file_path))) {
			List<String[]> my_doc = reader.readAll();
			int index = 0;
			int purpose_col_ind = 0;

			List<String> my_head = new ArrayList<>();
			for(String[] my_line: my_doc) {
				if(index == 0) {
					for(int col_ind = 0; col_ind < my_line.length; col_ind++) {
						my_head.add(my_line[col_ind]);
						if(my_line[col_ind].equals("purpose")) {
							purpose_col_ind = col_ind;
						}
					}
				} else {
					if(my_line[purpose_col_ind].equals(purpose)) {
						for(int i = 0; i < my_line.length; i++) {
							my_map.put(my_head.get(i), my_line[i]);
						}
						break;
					}
				}
				index++;
			}
			System.out.println();
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
		} catch(CsvException ex) {
			System.out.println(ex.getMessage());
		}
		return my_map;
	}

	public static String convertDate(String my_date, String date_format) throws ParseException {
		DateFormat df_old = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat df = new SimpleDateFormat(date_format);
		Date orig_date = df_old.parse(my_date);
		String res_date_string = df.format(orig_date).replace(".", "");
		return res_date_string;
	}
}
