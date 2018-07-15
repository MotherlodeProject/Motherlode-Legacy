package motherlode.util;

import java.util.List;

public class ModelUtil {
	public static String addPrependsAndAppends(String string, List<String> prepends, List<String> appends) {
		StringBuilder stringBuilder = new StringBuilder();

		//Add prepends
		for (String prepend : prepends) {
			if (stringBuilder.length() != 0) {
				stringBuilder.append(",");
			}
			stringBuilder.append(prepend);
		}

		// Add string
		if (stringBuilder.length() != 0) {
			stringBuilder.append(",");
		}
		stringBuilder.append(string);

		// Add appends
		for (String append : appends) {
			if (stringBuilder.length() != 0) {
				stringBuilder.append(",");
			}
			stringBuilder.append(append);
		}
		return stringBuilder.toString();
	}
}
