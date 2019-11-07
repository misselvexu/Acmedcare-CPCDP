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
import com.acmedcare.framework.cpcdp.gson.serializer.DistressCaseDetailSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.Date;

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
          .registerTypeAdapter(
              FirstAidBean.DistressCaseDetail[].class, new DistressCaseDetailSerializer())
          .setDateFormat("yyyy-MM-dd HH:mm:ss")
          .setPrettyPrinting()
          .create();

  @Test
  public void test01() throws Exception {

    PatientRegisterBean register =
        PatientRegisterBean.builder()
            .age(50)
            .birthday("2019-01-01")
            .gender(PatientRegisterBean.Gender.MALE)
            .hospitalId("1231ujs-88811")
            .name("Miss")
            .maritalStatus(PatientRegisterBean.MaritalStatus.M01)
            .contactPhone("1391011111")
            .culturedegree(PatientRegisterBean.Culturedegree.C01)
            .job(PatientRegisterBean.Job.J01)
            .height(175)
            .credentialsType(PatientRegisterBean.CredentialsType.ID_CARD)
            .idCard("111123321321321312")
            .nation("汉族")
            .status(Status.S01)
            .weight(65.3f)
            .registerId("98uiujjs-112321jss-12321jnsd-00ssxb")
            .id("6541gshja-98usbcn-9wssdasww-8hsbdvk")
            .build();

    System.out.println(gson.toJson(register));
  }

  @Test
  public void test02() throws Exception {

    FirstAidBean firstAidBean =
        FirstAidBean.builder()
            .province("江苏省")
            .city("苏州市")
            .area("相城区")
            .attackAddress("东长路")
            .attackTime(new Date())
            .attackZone(FirstAidBean.AttackZone.A1)
            .bloodPressure("90/101")
            .consciousnessType(FirstAidBean.ConsciousnessType.C1)
            .distressCase(FirstAidBean.DistressCase.DC1)
            .distressCaseDetails(
                new FirstAidBean.DistressCaseDetail[] {
                  FirstAidBean.DistressCaseDetail.DCD1,
                  FirstAidBean.DistressCaseDetail.DCD2,
                  FirstAidBean.DistressCaseDetail.DCD3
                })
            .heartRate(90)
            .inpatientId("776sgxju-91726312hnnsadsa-123123")
            .isNullAttackDetailTime("1")
            .medicalInsuranceNo("1111123")
            .medicalInsuranceType(FirstAidBean.MedicalInsuranceType.MI1)
            .outpatientId("aajshdbxnx-12312418-asdasdas")
            .pulseRate(11)
            .respirationRate(40)
            .temperature(38.1f)

            .cw120AmbulanceDepartment(FirstAidBean.Cw120AmbulanceDepartment.CD1)
            .cw120ArrivedHospitalTime(new Date())
            .cw120FirstDoctorName("Miey")
            .cw120FirstDoctorTime(new Date())
            .cw120FirstMcTime(new Date())
            .cw120HelpTime(new Date())
            .cw120IsTransHospital("1")
            .cwComingWayCode(FirstAidBean.CwComingWayCode.C1)

            .build();

    System.out.println(gson.toJson(firstAidBean));
  }
}
