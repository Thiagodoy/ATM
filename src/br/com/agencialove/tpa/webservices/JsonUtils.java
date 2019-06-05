package br.com.agencialove.tpa.webservices;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class JsonUtils {


	public static boolean isJSONValid(final String test) {
		try {
			new JSONObject(test);
		} catch (final JSONException ex) {
			return false;
		}
		return true;
	}

	public static Map fromJson(final String jsonString) {
		if (jsonString == null) {
			return null;
		}
		if (!JsonUtils.isJSONValid(jsonString)) {
			try {
				throw new Exception(jsonString);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		final Map result = new Gson().fromJson(jsonString, Map.class);
		return result;
	}

}
