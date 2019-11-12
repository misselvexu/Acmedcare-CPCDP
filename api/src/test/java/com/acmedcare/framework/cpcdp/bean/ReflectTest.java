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

package com.acmedcare.framework.cpcdp.bean;

import com.acmedcare.framework.cpcdp.annotation.Condition;
import com.acmedcare.framework.cpcdp.consts.NstemiGraceHrCondition;
import com.acmedcare.framework.cpcdp.kits.Reflections;
import lombok.*;
import org.junit.Test;

import java.io.Serializable;

import static com.acmedcare.framework.cpcdp.annotation.Condition.MatchingStrategy.ANY_VALUE_WITHIN_ENUMS_ARRAY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * {@link ReflectTest}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2019/11/12.
 */
public class ReflectTest {

  @Test
  public void test01() throws Exception {

    DemoBean demoBean =
        DemoBean.builder()
            .name("name")
            .nstemiGraceHrCondition(
                new NstemiGraceHrCondition[] {
                  NstemiGraceHrCondition.NSGRC_1, NstemiGraceHrCondition.NSGRC_2
                })
            .build();

    Object[] anyValueWithinEnumsArraysValues =
        (Object[]) Reflections.getFieldValue(demoBean, "nstemiGraceHrCondition");

    assertNotNull(anyValueWithinEnumsArraysValues);
    assertEquals(2, anyValueWithinEnumsArraysValues.length);

    for (Object anyValueWithinEnumsArraysValue : anyValueWithinEnumsArraysValues) {
      System.out.println(
          Reflections.invokeMethod(anyValueWithinEnumsArraysValue, "key", null, null)
              + " - "
              + Reflections.invokeMethod(anyValueWithinEnumsArraysValue, "value", null, null));
    }
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class DemoBean implements Serializable {

    private String name;

    private NstemiGraceHrCondition[] nstemiGraceHrCondition;

    @Condition(
        field = "nstemiGraceHrCondition",
        type = NstemiGraceHrCondition.class,
        isCpcEnum = true,
        strategy = ANY_VALUE_WITHIN_ENUMS_ARRAY)
    private String nstemiGraceValue;
  }
}
