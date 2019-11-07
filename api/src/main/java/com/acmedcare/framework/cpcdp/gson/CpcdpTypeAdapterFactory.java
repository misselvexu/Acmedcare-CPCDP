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

package com.acmedcare.framework.cpcdp.gson;

import com.acmedcare.framework.cpcdp.Status;
import com.acmedcare.framework.cpcdp.bean.PatientRegisterBean;
import com.acmedcare.framework.cpcdp.gson.adapter.*;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * {@link CpcdpTypeAdapterFactory}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2019/11/6.
 */
public class CpcdpTypeAdapterFactory implements TypeAdapterFactory {

  private static final Logger log = LoggerFactory.getLogger(CpcdpTypeAdapterFactory.class);

  private static Map<Class, TypeAdapter> typeAdapters = Maps.newConcurrentMap();

  static {
    typeAdapters.put(PatientRegisterBean.Gender.class, new GenderEnumTypeAdapter());
    typeAdapters.put(PatientRegisterBean.Job.class, new JobEnumTypeAdapter());
    typeAdapters.put(PatientRegisterBean.MaritalStatus.class, new MaritalStatusEnumTypeAdapter());
    typeAdapters.put(PatientRegisterBean.Culturedegree.class, new CulturedegreeEnumTypeAdapter());
    typeAdapters.put(PatientRegisterBean.CredentialsType.class, new CredentialsTypeEnumTypeAdapter());
    typeAdapters.put(Status.class, new StatusEnumTypeAdapter());
  }

  /**
   * Returns a type adapter for {@code type}, or null if this factory doesn't support {@code type}.
   *
   * @param gson {@link Gson} instance
   * @param type {@link TypeToken} instance
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

    log.debug("[==CPCDP==] gson type is: {}", type);

    Class<?> clazz = type.getRawType();

    log.debug("[==CPCDP==] gson type raw class is: {}", clazz);

    if (typeAdapters.containsKey(clazz)) {
      return typeAdapters.get(clazz);
    }

    // default null
    return null;
  }
}