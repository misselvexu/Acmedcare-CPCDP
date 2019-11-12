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

import com.acmedcare.framework.cpcdp.annotation.*;
import com.acmedcare.framework.cpcdp.consts.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

import static com.acmedcare.framework.cpcdp.annotation.ComplexCondition.Symbol.OR;

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

  // ************************ 基本信息 ************************

  /** 住院 ID */
  private String inpatientId;

  /** 门诊 ID */
  private String outpatientId;

  /** 发病时间 */
  @Required private Date attackTime;

  /**
   * 发病时间无法精确到分钟
   *
   * <pre>
   *   取值：
   *      1:是, 0:否
   * </pre>
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

  // ************************ 基础生命体征 ************************

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

  // ************************ 病情现况 ************************

  /** 病情评估 */
  @Required private DistressCase distressCase;

  /** 病情评估明细 */
  @JsonKey("DISTRESS_CASE_DETAIL")
  private DistressCaseDetail[] distressCaseDetails;

  // ************************ 来院方式 ************************

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

  // *************************** 120来院参数 ****************************

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

  // *************************** 转院参数 ****************************

  /** 转院类型 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "2",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private CwZyTransType cwZyTransType;

  /** 医院名称 */
  private String cwZyTransHospitalName;

  /** 转院首次医疗接触时间 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "2",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private Date cwZyFirstMcTime;

  /** 转出医院入门时间 */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = "2",
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(
        field = "cwZyTransType",
        expectValue = "1",
        type = CwZyTransType.class,
        isCpcEnum = true)
  })
  @JsonKey("CW_ZY_OUTHOSPITAL_VISIT_TIME")
  private Date cwZyOuthospitalVisitTime;

  /** 决定转院时间 */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = "2",
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(
        field = "cwZyTransType",
        expectValue = "1",
        type = CwZyTransType.class,
        isCpcEnum = true)
  })
  private Date cwZyTransferTime;

  /** 转出医院出门时间 */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = "2",
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(
        field = "cwZyTransType",
        expectValue = "1",
        type = CwZyTransType.class,
        isCpcEnum = true)
  })
  private Date cwZyLeaveOuthospitalTime;

  /** 到达本院大门时间 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "2",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private Date cwZyArrivedHospitalTime;

  /** 到达本院大门时间 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "2",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private Date cwZyAdmissionTime;

  /** 医护人员 */
  private String cwZyFirstDoctorName;

  // *************************** 自行来院参数 ****************************

  /** 到达本院大门时间 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "3",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private Date cwZxlyArrivedHospitalTime;

  /** 首次医疗接触时间 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "3",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private Date cwZxlyFirstMcTime;

  /** 首诊医师接诊时间 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "3",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private Date cwZxlyFirstDoctorTime;

  /** 医护人员 */
  private String cwZxlyFirstDoctorName;

  // *************************** 院内发病参数 ****************************

  /** 发病科室 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "4",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private String cwYnfbAttackDepartment;

  /** 首次医疗接触时间 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "4",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private Date cwYnfbFirstMcTime;

  /** 床位医院接触时间 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "4",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private Date cwYnfbConsultationTime;

  /** 离开科室时间 */
  @Required
  @Condition(
      field = "cwComingWayCode",
      expectValue = "4",
      type = CwComingWayCode.class,
      isCpcEnum = true)
  private Date cwYnfbLeaveDepartmentTime;

  /** 医护人员 */
  private String cwYnfbFirstDoctorName;

  // *************************** 院前溶栓治疗 ****************************

  /**
   * 溶栓筛查
   *
   * <pre>
   *   取值:
   *      1:合适 2:不合适 3:未筛查
   *
   *   条件:
   *    《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true)
  })
  private Screening screening;

  /**
   * 溶栓治疗
   *
   * <pre>
   * 取值:
   *    1:有 0:无
   *
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
  })
  @AllowValues({"0", "1"})
  private String isThrombolysis;

  /**
   * 直达溶栓场所
   *
   * <pre>
   * 取值:
   *    1:有 0:无
   *
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
  })
  @AllowValues({"0", "1"})
  private String isDirect;

  /**
   * 溶栓场所
   *
   * <pre>
   * 取值:
   *    1:其它医院  2:救护车
   *
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
  })
  @AllowValues({"1", "2"})
  private String thromTreatmentPlace;

  /**
   * 开始知情同意
   *
   * <pre>
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
  })
  private Date startAgreeTime;

  /**
   * 签署知情同意书
   *
   * <pre>
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
  })
  private Date signAgreeTime;

  /**
   * 开始溶栓时间
   *
   * <pre>
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
  })
  private Date thromStartTime;

  /**
   * 溶栓结束时间
   *
   * <pre>
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
  })
  private Date thromEndTime;

  /**
   * 药物
   *
   * <pre>
   * 取值:
   *    1:第一代 2:第二代 3:第三代
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   * </pre>
   *
   * @since 2.1
   * @see ThromDrugType
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
  })
  private ThromDrugType thromDrugType;

  /**
   * 计量
   *
   * <pre>
   * 取值:
   *    1:全量 2:半量
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   * </pre>
   *
   * @since 2.1
   * @see ThromDrugType
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
  })
  private ThromDrugCode thromDrugCode;

  /**
   * 溶栓再通
   *
   * <pre>
   * 取值:
   *    1:是 0:否
   *
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
  })
  @AllowValues({"0", "1"})
  private String isRepatency;

  /**
   * 溶栓后造影时间
   *
   * <pre>
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   *    3、《溶栓再通》为“是”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
    @Condition(field = "isRepatency", expectValue = "1", type = String.class)
  })
  private Date startRadiographyTime;

  /**
   * 补救 PCI
   *
   * <pre>
   * 取值:
   *    1:是 0:否
   *
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   *    3、《溶栓再通》为“否”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
    @Condition(field = "isRepatency", expectValue = "0", type = String.class)
  })
  @AllowValues({"0", "1"})
  private String isRepci;

  /**
   * 实际手术时间
   *
   * <pre>
   * 条件1:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   *    3、《溶栓再通》为“是”
   * OR
   * 条件2:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   *    3、《溶栓再通》为“否”
   *    4、《补救 PCI》为“是”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @ComplexCondition(
      value = {
        @Conditions({
          @Condition(
              field = "cwComingWayCode",
              expectValue = {"1", "2"},
              type = CwComingWayCode.class,
              isCpcEnum = true),
          @Condition(
              field = "screening",
              expectValue = "2",
              type = Screening.class,
              isCpcEnum = true),
          @Condition(field = "isRepatency", expectValue = "1", type = String.class)
        }),
        @Conditions({
          @Condition(
              field = "cwComingWayCode",
              expectValue = {"1", "2"},
              type = CwComingWayCode.class,
              isCpcEnum = true),
          @Condition(
              field = "screening",
              expectValue = "2",
              type = Screening.class,
              isCpcEnum = true),
          @Condition(field = "isRepatency", expectValue = "0", type = String.class),
          @Condition(field = "isRepci", expectValue = "1", type = String.class),
        })
      },
      symbol = OR)
  private Date operationTime;

  /**
   * 手术地点
   *
   * <pre>
   * 取值:
   *    1:本院 2:外院
   *
   * 条件:
   *    1、《来院方式》为”呼叫(120 或其他) 出车“或者”转院(包含任何机构)“
   *    2、《溶栓筛查》为“合适”
   *    3、《溶栓再通》为“否”
   *    4、《补救 PCI》为“是”
   * </pre>
   *
   * @since 2.1
   */
  @Required
  @Conditions({
    @Condition(
        field = "cwComingWayCode",
        expectValue = {"1", "2"},
        type = CwComingWayCode.class,
        isCpcEnum = true),
    @Condition(field = "screening", expectValue = "2", type = Screening.class, isCpcEnum = true),
    @Condition(field = "isRepatency", expectValue = "0", type = String.class),
    @Condition(field = "isRepci", expectValue = "1", type = String.class),
  })
  private HospitalPosition hospitalPosition;

  // *************************** 患者情况备注 ****************************

  /** 患者情况备注 */
  private String patientRemark;
}
