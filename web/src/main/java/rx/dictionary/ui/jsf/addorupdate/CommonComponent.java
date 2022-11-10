package rx.dictionary.ui.jsf.addorupdate;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommonComponent {
	private static Map<String,String> LANGUAGE_NAME_TO_TAG;
	public static Map<String,String> getLanguageNameToTag() {
		if (LANGUAGE_NAME_TO_TAG == null) {
			Map<String,String> result = new HashMap<>();
			result.put("繁體中文", Locale.TRADITIONAL_CHINESE.toLanguageTag());
			result.put("简体中文", Locale.SIMPLIFIED_CHINESE.toLanguageTag());
			result.put("English", Locale.ENGLISH.toLanguageTag());
			result.put("suomi", new Locale("fi").toLanguageTag());
			result.put("français", Locale.FRENCH.toLanguageTag());
			LANGUAGE_NAME_TO_TAG = result;
		}
		return LANGUAGE_NAME_TO_TAG;
	}
}
