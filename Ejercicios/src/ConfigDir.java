

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigDir {

	private Properties props;
	private static ConfigDir conf;

	public ConfigDir() throws IOException {

		props = new Properties();
		InputStream is = getClass().getResourceAsStream("propiedades/conex.properties");
		props.load(is);
	}

	public static ConfigDir getInstance() throws IOException {

		if (null == conf) {
			conf = new ConfigDir();
		}
		return conf;
	}

	public String getProperty(String propiedad) {

		return props.getProperty(propiedad);

	}

	public boolean getBooleanProperty(String cadena) {
		boolean flag = false;

		String guardo = props.getProperty(cadena);

		if (guardo.equalsIgnoreCase("true") || guardo.equalsIgnoreCase("si") || guardo.equalsIgnoreCase("yes")
				|| guardo.equalsIgnoreCase("1")) {

			flag = true;
		}

		return flag;
	}

}
