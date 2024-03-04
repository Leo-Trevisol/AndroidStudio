package com.projeto.intentsimplicitas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DateDeserializer implements JsonDeserializer<Date> {

	private static final String[] DATE_FORMATS = new String[] { "MMM dd, yyyy HH:mm:ss", "MMM dd, yyyy", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd" };

	@Override
	public Date deserialize(JsonElement arg0, java.lang.reflect.Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		try {
			int i = 0;
			for (String format : DATE_FORMATS) {
				try {
					i = i + 1;
					return new SimpleDateFormat(format, Locale.getDefault()).parse(arg0.getAsString());

				} catch (ParseException e) {
					if (i == DATE_FORMATS.length) {
						throw new ParseException(e.getMessage(), i);
					}
				}

			}
		} catch (ParseException e) {

			return new Date(arg0.getAsJsonPrimitive().getAsLong());
		}

		throw new JsonParseException("Unparseable date: \"" + arg0.getAsString() + "\". Supported formats: "
				+ Arrays.toString(DATE_FORMATS));
	}

}