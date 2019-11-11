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

package com.acmedcare.framework.cpcdp.gson.adapter;

import com.acmedcare.framework.cpcdp.consts.CkmbUnit;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * {@link CkmbUnitEnumTypeAdapter}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2019/11/6.
 */
public class CkmbUnitEnumTypeAdapter extends TypeAdapter<CkmbUnit> {

  private CkmbUnit[] values = CkmbUnit.values();

  /**
   * Writes one JSON value (an array, object, string, number, boolean or null) for {@code value}.
   *
   * @param out
   * @param value the Java object to write. May be null.
   */
  @Override
  public void write(JsonWriter out, CkmbUnit value) throws IOException {
    out.value(value == null ? null : value.key());
  }

  /**
   * Reads one JSON value (an array, object, string, number, boolean or null) and converts it to a
   * Java object. Returns the converted object.
   *
   * @param in
   * @return the converted Java object. May be null.
   */
  @Override
  public CkmbUnit read(JsonReader in) throws IOException {
    if (in.peek() == JsonToken.NULL) {
      in.nextNull();
      return null;
    }

    try {
      String key = in.nextString();
      for (CkmbUnit temp : values) {
        if (temp.key().equals(key)) {
          return temp;
        }
      }

      // default
      return null;
    } catch (Exception e) {
      throw new JsonSyntaxException(e);
    }
  }
}
