package com.projeto.intentsimplicitas.utils;

import java.lang.reflect.Type;
import java.util.Base64;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DataTypeDeserializer implements JsonDeserializer<byte[]> {
	
	@Override
	public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		String base64String = json.getAsString();
		byte[] base64Decoded = Base64.getDecoder().decode(base64String);
		return base64Decoded;
	}

}