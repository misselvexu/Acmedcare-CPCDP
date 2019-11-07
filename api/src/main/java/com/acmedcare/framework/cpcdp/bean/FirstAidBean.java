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

  /**
   * 发病区间
   *
   * @version ${project.version}
   */
  public enum AttackZone {

    /** */
    A1("1", "凌晨(0 点到 6 点)"),
    A2("2", "清晨(6 到 8 点)"),
    A3("3", "上午(8 到 12 点)"),
    A4("4", "中午(12 到 14 点)"),
    A5("5", "下午(14 到 17 点)"),
    A6("6", "傍晚(17 到 19 点)"),
    A7("7", "晚上(19 到 24 点)"),
    ;
    String key;
    String value;

    AttackZone(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String key() {
      return this.key;
    }

    public String value() {
      return value;
    }
  }

  /**
   * 医保类型
   *
   * @version ${project.version}
   */
  public enum MedicalInsuranceType {

    /** */
    MI1("1", "城镇职工基本医疗保险"),
    MI2("2", "新型农村合作医疗"),
    MI3("3", "城镇居民基本医疗保险"),
    MI4("4", "自费"),
    ;

    String key;
    String value;

    MedicalInsuranceType(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String key() {
      return this.key;
    }

    public String value() {
      return value;
    }
  }

  /**
   * 意识
   *
   * @version ${project.version}
   */
  public enum ConsciousnessType {

    /** */
    C1("1", "清醒"),
    C2("2", "对语言有反应"),
    C3("3", "对刺痛有反应"),
    C4("4", "对任何刺激无反应"),
    ;

    String key;
    String value;

    ConsciousnessType(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String key() {
      return this.key;
    }

    public String value() {
      return value;
    }
  }

  /**
   * 病情评估
   *
   * @version ${project.version}
   */
  public enum DistressCase {

    /** */
    DC1("1", "持续性胸闷/胸痛"),
    DC2("2", "间歇性胸闷/胸痛"),
    DC3("3", "症状已缓解"),
    ;

    String key;
    String value;

    DistressCase(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String key() {
      return this.key;
    }

    public String value() {
      return value;
    }
  }

  /**
   * 病情评估明细
   *
   * @version ${project.version}
   */
  public enum DistressCaseDetail {

    /** */
    DCD1("1", "呼吸困难"),
    DCD2("2", "腹痛"),
    DCD3("3", "齿痛"),
    DCD4("4", "肩背痛"),
    DCD5("5", "合并出血"),
    DCD6("6", "合并心衰"),
    DCD7("7", "合并恶性心律失常"),
    DCD8("8", "不明原因的昏厥"),
    DCD9("9", "自汗、大汗淋漓"),
    DCD10("10", "心慌心悸"),
    DCD11("11", "烦躁不安"),
    DCD12("12", "颈前部束缚感"),
    DCD13("13", "乏力"),
    DCD14("14", "气喘"),
    DCD99("3", "其他"),
    ;

    String key;
    String value;

    DistressCaseDetail(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String key() {
      return this.key;
    }

    public String value() {
      return value;
    }
  }

  /**
   * 来院方式
   *
   * @version ${project.version}
   */
  public enum CwComingWayCode {

    /** */
    C1("1", "呼叫(120 或其他)出车"),
    C2("2", "转院(包含任何机构)"),
    C3("3", "自行来院"),
    C4("4", "院内发病"),
    ;

    String key;
    String value;

    CwComingWayCode(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String key() {
      return this.key;
    }

    public String value() {
      return value;
    }
  }

  /**
   * 出车单位
   *
   * @version ${project.version}
   */
  public enum Cw120AmbulanceDepartment {

    /** */
    CD1("1", "120 救护车"),
    CD2("2", "本院救护车"),
    CD3("3", "外院救护车"),
    ;

    String key;
    String value;

    Cw120AmbulanceDepartment(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String key() {
      return this.key;
    }

    public String value() {
      return value;
    }
  }
}
