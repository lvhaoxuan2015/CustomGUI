/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gson;

import java.util.Map;
import java.util.Set;

import com.google.gson.internal.LinkedTreeMap;

public class JsonObject extends JsonElement {

	public LinkedTreeMap<String, JsonElement> members = new LinkedTreeMap<>();

	public void add(String property, JsonElement value) {
		if (value == null) {
			value = JsonNull.INSTANCE;
		}
		members.put(property, value);
	}

	public void addProperty(String property, Boolean value) {
		add(property, createJsonElement(value));
	}

	public void addProperty(String property, Character value) {
		add(property, createJsonElement(value));
	}

	public void addProperty(String property, Number value) {
		add(property, createJsonElement(value));
	}

	public void addProperty(String property, String value) {
		add(property, createJsonElement(value));
	}

	private JsonElement createJsonElement(Object value) {
		return value == null ? JsonNull.INSTANCE : new JsonPrimitive(value);
	}

	@Override
	public JsonObject deepCopy() {
		JsonObject result = new JsonObject();
		for (Map.Entry<String, JsonElement> entry : members.entrySet()) {
			result.add(entry.getKey(), entry.getValue().deepCopy());
		}
		return result;
	}

	public Set<Map.Entry<String, JsonElement>> entrySet() {
		return members.entrySet();
	}

	@Override
	public boolean equals(Object o) {
		return (o == this) || (o instanceof JsonObject && ((JsonObject) o).members.equals(members));
	}

	public JsonElement get(String memberName) {
		return members.get(memberName);
	}

	public JsonArray getAsJsonArray(String memberName) {
		return (JsonArray) members.get(memberName);
	}

	public JsonObject getAsJsonObject(String memberName) {
		return (JsonObject) members.get(memberName);
	}

	public JsonPrimitive getAsJsonPrimitive(String memberName) {
		return (JsonPrimitive) members.get(memberName);
	}

	public boolean has(String memberName) {
		return members.containsKey(memberName);
	}

	@Override
	public int hashCode() {
		return members.hashCode();
	}

	public Set<String> keySet() {
		return members.keySet();
	}

	public JsonElement remove(String property) {
		return members.remove(property);
	}

	public int size() {
		return members.size();
	}
}
