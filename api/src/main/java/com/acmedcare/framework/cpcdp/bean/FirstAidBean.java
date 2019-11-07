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

import com.acmedcare.framework.cpcdp.annotation.AllowValues;
import com.acmedcare.framework.cpcdp.annotation.Condition;
import com.acmedcare.framework.cpcdp.annotation.JsonKey;
import com.acmedcare.framework.cpcdp.annotation.Required;
import com.acmedcare.framework.cpcdp.consts.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * {@link FirstAidBean}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2019/11/7.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("ALL")
public class FirstAidBean implements Serializable {

  // ********** 基本信息 **********

  /** 住院 ID */
  private String inpatientId;

  /** 门诊 ID */
  private String outpatientId;

  /** 发病时间 */
  @Required private Date attackTime;

  /**
   * 发病时间无法精确到分钟
   *
   * <p>1:是, 0:否
   */
  @AllowValues({"0", "1"})
  private String isNullAttackDetailTime;

  /** 发病区间 */
  @Required
  @Condition(field = "isNullAttackDetailTime", expectValue = "1", type = String.class)
  private AttackZone attackZone;

  /** 发病地址(省) */
  private String province;

  /** 发病地址(市) */
  private String city;

  /** 发病地址区(县) */
  private String area;

  /** 发病详细地址 */
  private String attackAddress;

  /** 医保类型 */
  private MedicalInsuranceType medicalInsuranceType;

  /** 医保编号 */
  private String medicalInsuranceNo;

  // ********** 基础生命体征 **********

  /**
   * 意识
   *
   * @see ConsciousnessType
   */
  @Required private ConsciousnessType consciousnessType;

  /** 呼吸 */
  @Required private int respirationRate;

  /** 脉搏 */
  @Required private int pulseRate;

  /** 心率 */
  @Required private int heartRate;

  /**
   * 血压
   *
   * <p>格式:120/90
   */
  @Required private String bloodPressure;

  /**
   * 体温
   *
   * <p>保留 1 位小数
   */
  @Required private float temperature;

  // ********** 病情现况 **********

  /** 病情评估 */
  @Required private DistressCase distressCase;

  /** 病情评估明细 */
  @JsonKey("DISTRESS_CASE_DETAIL")
  private DistressCaseDetail[] distressCaseDetails;

  // ********** 来院方式 **********

  /** 来院方式 */
  @Required private CwComingWayCode cwComingWayCode;

  @Required
  @JsonKey("CW_120_AMBULANCE_DEPARTMENT")
  @Condition(
      field = "cwComingWayCode",
      expectValue = "1",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private Cw120AmbulanceDepartment cw120AmbulanceDepartment;

  /** 呼救时间 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "1",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  @JsonKey("CW_120_HELP_TIME")
  private Date cw120HelpTime;

  /**
   * 到达医院大门时间
   *
   * <p>条件:《来院方式》为”呼叫(120 或其他)出车“
   */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "1",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  @JsonKey("CW_120_ARRIVED_HOSPITAL_TIME")
  private Date cw120ArrivedHospitalTime;

  /**
   * 直接转送上级医院
   *
   * <p>条件:《来院方式》为”呼叫(120 或其他)出车“
   */
  @Required
  @AllowValues({"0", "1"})
  @Condition(
      field = "cwComingWayCode",
      expectValue = "1",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  @JsonKey("CW_120_IS_TRANS_HOSPITAL")
  private String cw120IsTransHospital;

  /**
   * 首次医疗接触时间
   *
   * <p>条件:《来院方式》为”呼叫(120 或其他)出车“
   */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "1",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  @JsonKey("CW_120_FIRST_MC_TIME")
  private Date cw120FirstMcTime;

  /**
   * 首诊医师接诊时间
   *
   * <p>条件:《来院方式》为”呼叫(120 或其他)出车“
   */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "1",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  @JsonKey("CW_120_FIRST_DOCTOR_TIME")
  private Date cw120FirstDoctorTime;

  /**
   * 医护人员
   *
   * <p>条件:《来院方式》为”呼叫(120 或其他)出车“
   */
  @JsonKey("CW_120_FIRST_DOCTOR_NAME")
  private String cw120FirstDoctorName;

  // ====================== 常量参数类定义 ======================

}
