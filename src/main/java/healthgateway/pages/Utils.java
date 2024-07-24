package healthgateway.pages;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Properties;
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

	public static String getTargetBrowser() throws Exception {
		String env = System.getProperty("browser");
		if (env == null)
			env = getConfigProperty("browser");
		return env;
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

}
