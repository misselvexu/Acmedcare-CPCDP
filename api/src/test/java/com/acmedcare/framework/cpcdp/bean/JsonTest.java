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

import com.acmedcare.framework.cpcdp.Status;
import com.acmedcare.framework.cpcdp.gson.CpcdpFieldNamingStrategy;
import com.acmedcare.framework.cpcdp.gson.CpcdpTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

/**
 * {@link JsonTest}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2019/11/6.
 */
public class JsonTest {

  Gson gson =
      new GsonBuilder()
          .setFieldNamingStrategy(new CpcdpFieldNamingStrategy())
          .registerTypeAdapterFactory(new CpcdpTypeAdapterFactory())
          .setPrettyPrinting()
          .create();

  @Test
  public void test01() throws Exception {

    PatientRegister register =
        PatientRegister.builder()
            .age(50)
            .birthday("2019-01-01")
            .gender(PatientRegister.Gender.MALE)
            .hospitalId("1231ujs-88811")
            .name("Miss")
            .maritalStatus(PatientRegister.MaritalStatus.M01)
            .contactPhone("1391011111")
            .culturedegree(PatientRegister.Culturedegree.C01)
            .job(PatientRegister.Job.J01)
            .height(175)
            .credentialsType(PatientRegister.CredentialsType.ID_CARD)
            .idCard("111123321321321312")
            .nation("汉族")
            .status(Status.S01)
            .weight(65.3f)
            .registerId("98uiujjs-112321jss-12321jnsd-00ssxb")
            .id("6541gshja-98usbcn-9wssdasww-8hsbdvk")
            .build();

    System.out.println(gson.toJson(register));
  }
}
