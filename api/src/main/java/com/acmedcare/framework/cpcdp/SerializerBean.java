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

package com.acmedcare.framework.cpcdp;

import com.acmedcare.framework.cpcdp.consts.DistressCaseDetail;
import com.acmedcare.framework.cpcdp.gson.CpcdpFieldNamingStrategy;
import com.acmedcare.framework.cpcdp.gson.CpcdpTypeAdapterFactory;
import com.acmedcare.framework.cpcdp.gson.serializer.DistressCaseDetailSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * {@link SerializerBean}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2019/11/7.
 */
public class SerializerBean implements Serializable {

  static final class SerializerFactory {

    static final String PRETTY_ENABLED_KEY = "cpcdp.json.pretty.enabled";

    static final String DEFAULT_DATA_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    static final Boolean ENABLED_PRETTY = Boolean.valueOf(System.getProperty(PRETTY_ENABLED_KEY, "false"));

    static Gson gson;

    static {
      GsonBuilder builder =
          new GsonBuilder()
              .setFieldNamingStrategy(new CpcdpFieldNamingStrategy())
              .registerTypeAdapterFactory(new CpcdpTypeAdapterFactory())
              .registerTypeAdapter(DistressCaseDetail[].class, new DistressCaseDetailSerializer())
              .setDateFormat(DEFAULT_DATA_FORMAT_PATTERN);

      if (ENABLED_PRETTY) {
        builder.setPrettyPrinting();
      }

      gson = builder.create();
    }
  }

  /**
   * Convert object to json string
   *
   * @return json result
   */
  public String json() {
    return SerializerFactory.gson.toJson(this);
  }
}
