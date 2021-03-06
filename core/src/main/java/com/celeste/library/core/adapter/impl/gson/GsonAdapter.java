package com.celeste.library.core.adapter.impl.gson;

import com.celeste.library.core.adapter.Json;
import com.celeste.library.core.adapter.exception.JsonDeserializeException;
import com.celeste.library.core.adapter.exception.JsonSerializeException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

public final class GsonAdapter implements Json {

  private static final GsonAdapter INSTANCE;

  static {
    INSTANCE = new GsonAdapter();
  }

  private Gson gson;

  private GsonAdapter() {
    this.gson = new GsonBuilder()
        .enableComplexMapKeySerialization()
        .create();
  }

  @SafeVarargs
  public final <T> void registerAdapter(final Class<T> type, final Class<? extends T>... subTypes) {
    final GsonTypeAdapter<T> adapter = new GsonTypeAdapter<>(type)
        .registerSubtypes(subTypes);

    this.gson = gson.newBuilder()
        .registerTypeAdapterFactory(adapter)
        .create();
  }

  @Override
  public String serialize(final Object value) throws JsonSerializeException {
    try {
      final String json = gson.toJson(value);

      if (json == null) {
        throw new IOException("It was not possible to serialize the object to json");
      }

      return json;
    } catch (Exception exception) {
      throw new JsonSerializeException(exception);
    }
  }

  @Override
  public <T> T deserialize(final String json, final Class<T> clazz)
      throws JsonDeserializeException {
    try {
      final T instance = gson.fromJson(json, clazz);

      if (instance == null) {
        throw new IOException("Could not deserialize String for Object the class"
            + clazz.getSimpleName());
      }

      return instance;
    } catch (Exception exception) {
      throw new JsonDeserializeException(exception);
    }
  }

  public static GsonAdapter getInstance() {
    return INSTANCE;
  }

}
