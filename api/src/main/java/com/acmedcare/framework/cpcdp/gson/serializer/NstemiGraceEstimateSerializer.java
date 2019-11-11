/*
 * Copyright (c) 2019 Acmedcare+
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.acmedcare.framework.cpcdp.gson.serializer;

import com.acmedcare.framework.cpcdp.consts.NstemiGraceEstimate;
import com.google.common.collect.Maps;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@link NstemiGraceEstimateSerializer}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2019/11/7.
 */
public class NstemiGraceEstimateSerializer
    implements JsonSerializer<NstemiGraceEstimate[]>, JsonDeserializer<NstemiGraceEstimate[]> {

  @SuppressWarnings("RegExpEmptyAlternationBranch")
  private static final String SPLIT = "|";

  private static Map<String, NstemiGraceEstimate> map = Maps.newHashMap();

  static {
    NstemiGraceEstimate[] details = NstemiGraceEstimate.values();
    for (NstemiGraceEstimate detail : details) {
      map.put(detail.key(), detail);
    }
  }

  /**
   * Gson invokes this call-back method during serialization when it encounters a field of the
   * specified type.
   *
   * <p>In the implementation of this call-back method, you should consider invoking {@link
   * JsonSerializationContext#serialize(Object, Type)} method to create JsonElements for any
   * non-trivial field of the {@code src} object. However, you should never invoke it on the {@code
   * src} object itself since that will cause an infinite loop (Gson will call your call-back method
   * again).
   *
   * @param src the object that needs to be converted to Json.
   * @param typeOfSrc the actual type (fully genericized version) of the source object.
   * @param context
   * @return a JsonElement corresponding to the specified object.
   */
  @Override
  public JsonElement serialize(
      NstemiGraceEstimate[] src, Type typeOfSrc, JsonSerializationContext context) {

    if (src == null || src.length == 0) {
      return null;
    }

    StringBuilder result = new StringBuilder();

    for (NstemiGraceEstimate distressCaseDetail : src) {
      if (distressCaseDetail != null
          && distressCaseDetail.key() != null
          && distressCaseDetail.key().trim().length() > 0) {
        result.append(distressCaseDetail.key()).append(SPLIT);
      }
    }

    if (result.toString().endsWith(SPLIT)) {
      return new JsonPrimitive(result.toString().substring(0, result.length() - 1));
    }

    return null;
  }

  /**
   * Gson invokes this call-back method during deserialization when it encounters a field of the
   * specified type.
   *
   * <p>In the implementation of this call-back method, you should consider invoking {@link
   * JsonDeserializationContext#deserialize(JsonElement, Type)} method to create objects for any
   * non-trivial field of the returned object. However, you should never invoke it on the the same
   * type passing {@code json} since that will cause an infinite loop (Gson will call your call-back
   * method again).
   *
   * @param json The Json data being deserialized
   * @param typeOfT The type of the Object to deserialize to
   * @param context
   * @return a deserialized object of the specified type typeOfT which is a subclass of {@code T}
   * @throws JsonParseException if json is not in the expected format of {@code typeofT}
   */
  @Override
  public NstemiGraceEstimate[] deserialize(
      JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {

    String content = json.getAsString();

    List<NstemiGraceEstimate> result = new ArrayList<>();

    if (content != null && content.trim().length() > 0) {
      if (content.contains(SPLIT)) {
        for (String temp : content.split(SPLIT)) {
          if (map.containsKey(temp)) {
            result.add(map.get(temp));
          }
        }
      }
    }

    return result.toArray(new NstemiGraceEstimate[0]);
  }
}
