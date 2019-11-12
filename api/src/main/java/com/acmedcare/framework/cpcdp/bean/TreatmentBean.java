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

import com.acmedcare.framework.cpcdp.SerializerBean;
import com.acmedcare.framework.cpcdp.annotation.*;
import com.acmedcare.framework.cpcdp.consts.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.acmedcare.framework.cpcdp.annotation.Condition.MatchingStrategy.ANY_VALUE_WITHIN_ENUMS_ARRAY;

/**
 * {@link TreatmentBean}
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
public class TreatmentBean extends SerializerBean implements Serializable {

  // *************** 心电图参数 *****************

  /**
   * 是否有心电图
   *
   * <pre>
   * 取值：
   *    0:无 1:有
   * </pre>
   */
  @Required
  @AllowValues(
      value = {"0", "1"},
      message = "0:无 1:有")
  private String hasEcgImage;

  /**
   * 未获得原因
   *
   * <pre>
   *   条件:
   *      《心电图》为“无”
   * </pre>
   */
  @Required
  @Condition(field = "hasEcgImage", type = String.class, expectValue = "0")
  private String noEcgImageReason;

  /**
   * 心电图集合
   *
   * <pre>
   *   条件:
   *      《心电图》为“有”
   *   备注:
   *      参照“心电图” 最多只能上传三个
   *
   * </pre>
   */
  @Required
  @Condition(field = "hasEcgImage", type = String.class, expectValue = "1")
  private List<EcgBean> ecgs;

  /**
   * 远程心电图传输
   *
   * <pre>
   *  取值:
   *     1:接收 120/网络医院心电图 2:未传输
   *
   *  备注:
   *     多选用竖线隔开。 如"1|2|3" (// TODO 疑问)
   * </pre>
   */
  @Required
  @AllowValues(
      value = {"1", "2"},
      message = "1:接收 120/网络医院心电图 2:未传输")
  private String remoteEcgTransmission;

  /**
   * 接收 120/网络医院心电图时间
   *
   * <pre>
   *   条件:
   *      《远程心电图传输》为“接收 120/网络 医院心电图时间”
   *
   * </pre>
   */
  @Required
  @Condition(field = "remoteEcgTransmission", expectValue = "1", type = String.class)
  private Date tranTime;

  /**
   * 传输方式
   *
   * <pre>
   *   取值:
   *      1:实时监控 2:微信群
   *
   *   条件:
   *      《远程心电图传输》为“接收 120/网络 医院心电图时间”
   *
   * </pre>
   */
  @Required
  @Condition(field = "remoteEcgTransmission", expectValue = "1", type = String.class)
  @AllowValues(
      value = {"1", "2"},
      message = "1:实时监控 2:微信群")
  private String isRemoteEcgtran;

  // *************** 实验室检查 ***************

  /**
   * 肌钙蛋白
   *
   * <pre>
   *   取值：
   *    0:否 1:是
   * </pre>
   */
  @Required
  @AllowValues({"0", "1"})
  private String isCtnt;

  /**
   * 肌钙蛋白集合
   *
   * <pre>
   *   条件：
   *    《肌钙蛋白》为“是”
   * </pre>
   */
  @Required
  @Condition(field = "isCtnt", expectValue = "1", type = String.class)
  private List<Ctntbean> ctnts;

  /**
   * 血清肌酐
   *
   * <pre>
   *   取值：
   *    0:无 1:有
   * </pre>
   */
  @Required
  @AllowValues({"0", "1"})
  private String isCr;

  /**
   * 血清肌酐(数值)
   *
   * <pre>
   *   取值：
   *    0:无 1:有
   *
   *   条件:
   *    《血清肌酐》为“是”
   *
   *   备注:
   *     保留 2 位小数
   * </pre>
   */
  @Required
  @Condition(field = "isCr", expectValue = "1", type = String.class)
  private float crValue;

  /**
   * D-二聚体
   *
   * <pre>
   *   取值：
   *    0:无 1:有
   * </pre>
   */
  @Required
  @AllowValues({"0", "1"})
  @JsonKey("IS_Ddimer")
  private String isDdimer;

  /**
   * D-二聚体(数值)
   *
   * <pre>
   *   取值：
   *    0:无 1:有
   *
   *   条件:
   *    《D-二聚体》为“是”
   *
   *   备注:
   *     保留 2 位小数
   * </pre>
   */
  @Required
  @Condition(field = "isDdimer", expectValue = "1", type = String.class)
  @JsonKey("Ddimer_VALUE")
  private float ddimerValue;

  /**
   * D-二聚体(单位)
   *
   * <pre>
   *   取值:
   *     1:ng/mL 2:ug/mL 3:ug/L
   *   条件:
   *    《D-二聚体》为“是”
   * </pre>
   */
  @Required
  @Condition(field = "isDdimer", expectValue = "1", type = String.class)
  @JsonKey("Ddimer_UNIT")
  private DdimerUnit ddimerUnit;

  /**
   * BNP
   *
   * <pre>
   *   取值：
   *    0:无 1:有
   * </pre>
   */
  @Required
  @AllowValues({"0", "1"})
  private String isBnp;

  /**
   * BNP(数值)
   *
   * <pre>
   *   条件:
   *    《BNP》为“是”
   *
   *   备注:
   *     保留 2 位小数
   * </pre>
   */
  @Required
  @Condition(field = "isBnp", expectValue = "1", type = String.class)
  @JsonKey("BNP_VALUE")
  private float bnpValue;

  /**
   * NT-proBNP
   *
   * <pre>
   *   取值：
   *    0:无 1:有
   * </pre>
   */
  @Required
  @AllowValues({"0", "1"})
  @JsonKey("IS_NTproBNP")
  private String isNtprobnp;

  /**
   * NT-proBNP(数值)
   *
   * <pre>
   *   条件:
   *    《NT-proBNP》为“是”
   *
   *   备注:
   *     保留 2 位小数
   * </pre>
   */
  @Required
  @Condition(field = "isNtprobnp", expectValue = "1", type = String.class)
  @JsonKey("NTproBNP_VALUE")
  private float ntprobnpValue;

  /**
   * Myo
   *
   * <pre>
   *   取值：
   *    0:无 1:有
   * </pre>
   */
  @Required
  @AllowValues({"0", "1"})
  @JsonKey("IS_MYO")
  private String isMyo;

  /**
   * Myo(数值)
   *
   * <pre>
   *   条件:
   *    《Myo》为“是”
   *
   *   备注:
   *     保留 2 位小数
   * </pre>
   */
  @Required
  @Condition(field = "isMyo", expectValue = "1", type = String.class)
  @JsonKey("MYO_VALUE")
  private float myoValue;

  /**
   * Myo(单位)
   *
   * <pre>
   *   取值:
   *     1:ng/mL 2:ug/L
   *   条件:
   *    《Myo》为“是”
   * </pre>
   */
  @Required
  @Condition(field = "isMyo", expectValue = "1", type = String.class)
  @JsonKey("MYO_UNIT")
  private MyoUnit myoUnit;

  /**
   * CKMB
   *
   * <pre>
   *   取值：
   *    0:无 1:有
   * </pre>
   */
  @Required
  @AllowValues({"0", "1"})
  @JsonKey("IS_CKMB")
  private String isCkmb;

  /**
   * CKMB(数值)
   *
   * <pre>
   *   条件:
   *    《CKMB》为“是”
   *
   *   备注:
   *     保留 2 位小数
   * </pre>
   */
  @Required
  @Condition(field = "isCkmb", expectValue = "1", type = String.class)
  @JsonKey("CKMB_VALUE")
  private float ckmbValue;

  /**
   * CKMB(单位)
   *
   * <pre>
   *   取值:
   *     1:ng/mL 2:ug/L 3:u/L
   *   条件:
   *    《CKMB》为“是”
   * </pre>
   */
  @Required
  @Condition(field = "isCkmb", expectValue = "1", type = String.class)
  @JsonKey("CKMB_UNIT")
  private CkmbUnit ckmbUnit;

  // *************** 心内科会诊 ***************

  /**
   * 心内科会诊
   *
   * <pre>
   *   取值：
   *    0:否 1:是
   * </pre>
   */
  @Required
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String isNoticeImcd;

  /**
   * 会诊类型
   *
   * <pre>
   *   取值:
   *     1:现场会诊 2:远程会诊
   *   条件:
   *    《心内科会诊》为“是”
   * </pre>
   */
  @Required
  @Condition(field = "isNoticeImcd", expectValue = "1", type = String.class)
  private ImcdType imcdType;

  /**
   * 通知会诊时间
   *
   * <pre>
   *   条件:
   *    《心内科会诊》为“是”
   * </pre>
   */
  @Required
  @Condition(field = "isNoticeImcd", expectValue = "1", type = String.class)
  private Date noticeImcdTime;

  /**
   * 会诊时间
   *
   * <pre>
   *   条件:
   *    《心内科会诊》为“是”
   * </pre>
   */
  @Required
  @Condition(field = "isNoticeImcd", expectValue = "1", type = String.class)
  private Date consultationTime;

  // *************** 诊断 ***************

  /**
   * 初步诊断
   *
   * <pre>
   *   取值：
   *      1:STEMI
   *      2:NSTEMI
   *      3:UA
   *      4:主动脉夹层
   *      5:肺动脉栓塞
   *      6:非 ACS 心源性胸痛
   *      7:其它非心源性胸痛
   *      8:待查
   * </pre>
   */
  @Required private CpDiagnosisCode cpDiagnosisCode;

  // *************** 诊断-STEMI ***************

  /**
   * 患者自愿放弃后续治疗
   *
   * <pre>
   *   取值：
   *      0:无 1:是
   *   条件：
   *      《初步诊断》为“STEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "1")
  @AllowValues(
      value = {"0", "1"},
      message = "0:无 1:是")
  private String stemiGiveUpTreatment;

  /**
   * 初步诊断时间
   *
   * <pre>
   *   条件：
   *      《初步诊断》为“STEMI”
   * </pre>
   */
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "1")
  private Date stemiDiagnosisTime;

  /**
   * 医生
   *
   * <pre>
   *   条件：
   *      《初步诊断》为“STEMI”
   * </pre>
   */
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "1")
  private String stemiDoctorName;

  /**
   * 心功能分级
   *
   * <pre>
   *   取值：
   *      1:I 级(no CHF)
   *      2:II 级(rales and/or JVD)
   *      3:III 级(pulmonary edema)
   *      4:IV 级(cardiogenic shock)
   *   条件：
   *      《初步诊断》为“STEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "1")
  @JsonKey("STEMI_KILLIP_LEVEL")
  private KillipLevel stemiKillipLevel;

  /**
   * 绕行急诊
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“STEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "1")
  private String stemiIsBypassEmergency;

  /**
   * 绕行 CCU
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“STEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "1")
  private String stemiIsBypassCcu;

  // ************* 诊断-STEMI-药物 *************

  /**
   * 抗血小板治疗
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“STEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "1")
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String stemiIsDrug;

  /**
   * 阿司匹林(剂量)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《抗血小板治疗》为“是”
   *   备注：
   *      保留 0 位小数
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(field = "stemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private float stemiAspirinDose;

  /**
   * 阿司匹林(时间)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《抗血小板治疗》为“是”
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(field = "stemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private Date stemiAspirinTime;

  /**
   * 氯吡格雷(剂量)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《抗血小板治疗》为“是”
   *   备注：
   *      保留 0 位小数
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(field = "stemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private float stemiClopidogrelDose;

  /**
   * 氯吡格雷(时间)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《抗血小板治疗》为“是”
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(field = "stemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private Date stemiClopidogrelTime;

  /**
   * 替格瑞洛(剂量)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《抗血小板治疗》为“是”
   *   备注：
   *      保留 0 位小数
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(field = "stemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private float stemiTicagrelorDose;

  /**
   * 替格瑞洛(时间)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《抗血小板治疗》为“是”
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(field = "stemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private Date stemiTicagrelorTime;

  /**
   * 抗凝
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“STEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "1")
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String stemiIsAnticoagulation;

  /**
   * 抗凝(抗凝)
   *
   * <pre>
   *   取值：
   *      1:普通肝素 2:低分子肝素 3:比伐卢定 4:磺达肝癸钠
   *
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《抗凝》为“是”
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsAnticoagulation",
        type = String.class,
        isCpcEnum = true,
        expectValue = "1")
  })
  @JsonKey("STEMI_ANTICOAGULATION_DRUG")
  private AnticoagulationDrug stemiAnticoagulationDrug;

  /**
   * 抗凝(剂量)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《抗凝》为“是”
   *   备注：
   *      保留 1 位小数
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsAnticoagulation",
        type = String.class,
        isCpcEnum = true,
        expectValue = "1")
  })
  private float stemiAnticoagulationDose;

  /**
   * 抗凝(时间)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《抗凝》为“是”
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsAnticoagulation",
        type = String.class,
        isCpcEnum = true,
        expectValue = "1")
  })
  private Date stemiAnticoagulationTime;

  /**
   * 他汀治疗
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“STEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "1")
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String stemiIntensifyStatinsTreat;

  /**
   * β受体阻滞剂
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“STEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "1")
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String stemiReceptorRetardantUsing;

  // ******************* 诊断-STEMI-再灌注措施 ********************

  /**
   * 再灌注措施
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“STEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "1")
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String stemiIsReperfusion;

  /**
   * 无再灌注措施
   *
   * <pre>
   *   取值：
   *      1:无明确胸痛，生命体征平稳
   *      2:错过再灌注时间
   *      3:出血
   *      4:严重肝肾功能不全
   *      5:经济原因
   *      6:家庭放弃
   *      9:其他原因
   *
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“否”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "0")
  })
  private StemiNoReperfusionReason stemiNoReperfusionReason;

  /**
   * 措施
   *
   * <pre>
   *   取值：
   *      1:直接 PCI
   *      2: 溶栓
   *      3:择期介入
   *      4:CABG
   *      5:转运 PCI
   *
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1")
  })
  private StemiMeasures stemiMeasures;

  /**
   * 措施(溶栓)
   *
   * <pre>
   *   取值：
   *      1:补救 PCI
   *      2:溶栓后介入
   *
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“溶栓”
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = "2")
  })
  private StemiMeasuresThrombolysis stemiMeasuresThrombolysis;

  /**
   * 决定医生
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“直接 PCI”s
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = "1")
  })
  private String stemiMeasuresDoctorName;

  /**
   * 决定介入手术时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *
   *      3、《措施》为“直接 PCI” 或(“溶栓”并为“补救 PCI”)或“择期介入”
   * </pre>
   */
  @Required
  @ComplexCondition(
      value = {
        @Conditions({
          @Condition(
              field = "cpDiagnosisCode",
              type = CpDiagnosisCode.class,
              isCpcEnum = true,
              expectValue = "1"),
          @Condition(
              field = "stemiIsReperfusion",
              type = String.class,
              isCpcEnum = false,
              expectValue = "1"),
          @Condition(
              field = "stemiMeasures",
              type = StemiMeasures.class,
              isCpcEnum = true,
              expectValue = {"1", "3"})
        }),
        @Conditions({
          @Condition(
              field = "cpDiagnosisCode",
              type = CpDiagnosisCode.class,
              isCpcEnum = true,
              expectValue = "1"),
          @Condition(
              field = "stemiIsReperfusion",
              type = String.class,
              isCpcEnum = false,
              expectValue = "1"),
          @Condition(
              field = "stemiMeasures",
              type = StemiMeasures.class,
              isCpcEnum = true,
              expectValue = {"2"}),
          @Condition(
              field = "stemiMeasuresThrombolysis",
              isCpcEnum = true,
              type = StemiMeasuresThrombolysis.class,
              expectValue = "1")
        })
      },
      symbol = ComplexCondition.Symbol.OR)
  private Date stemiDecisionOperationTime;

  /**
   * 启动导管室时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“直接 PCI”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"1"})
  })
  private Date stemiStartConduitTime;

  /**
   * 开始知情同意时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“直接 PCI”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"1"})
  })
  private Date stemiStartAgreeTime;

  /**
   * 签署知情同意时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“直接 PCI”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"1"})
  })
  private Date stemiSignAgreeTime;

  /**
   * 造影开始时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“直接 PCI”
   * </pre>
   */
  @Required
  @ComplexCondition(
      value = {
        @Conditions({
          @Condition(
              field = "cpDiagnosisCode",
              type = CpDiagnosisCode.class,
              isCpcEnum = true,
              expectValue = "1"),
          @Condition(
              field = "stemiIsReperfusion",
              type = String.class,
              isCpcEnum = false,
              expectValue = "1"),
          @Condition(
              field = "stemiMeasures",
              type = StemiMeasures.class,
              isCpcEnum = true,
              expectValue = {"3"})
        }),
        @Conditions({
          @Condition(
              field = "cpDiagnosisCode",
              type = CpDiagnosisCode.class,
              isCpcEnum = true,
              expectValue = "1"),
          @Condition(
              field = "stemiIsReperfusion",
              type = String.class,
              isCpcEnum = false,
              expectValue = "1"),
          @Condition(
              field = "stemiMeasures",
              type = StemiMeasures.class,
              isCpcEnum = true,
              expectValue = {"2"}),
          @Condition(
              field = "stemiMeasuresThrombolysis",
              isCpcEnum = true,
              type = StemiMeasuresThrombolysis.class,
              expectValue = "1")
        })
      },
      symbol = ComplexCondition.Symbol.OR)
  private Date stemiStartRadiographyTime;

  /**
   * 决定 CABG 时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“直接 PCI”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"4"})
  })
  private Date stemiDecisionCabgTime;

  /**
   * 开始 CABG 时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“直接 PCI”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"4"})
  })
  private Date stemiStartCabgTime;

  /**
   * 转运 PCI
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“转运 PCI”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"5"})
  })
  private String stemiTpciType;

  // ************************ 诊断-STEMI-院内溶栓治疗 *************************

  /**
   * 溶栓筛查
   *
   * <pre>
   *   取值：
   *      1:合适 2:不合适 3:未筛查
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“ 溶栓”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"2"})
  })
  private StemiScreening stemiScreening;

  /**
   * 溶栓治疗
   *
   * <pre>
   *   取值：
   *      0:无 1:有
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“ 溶栓”
   *      4、《溶栓筛查》为“合适”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "stemiScreening",
        type = StemiScreening.class,
        isCpcEnum = true,
        expectValue = {"1"})
  })
  @AllowValues(
      value = {"0", "1"},
      message = "0:无 1:有")
  private String stemiIsThrombolysis;

  /**
   * 直达溶栓场所
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“ 溶栓”
   *      4、《溶栓筛查》为“合适”
   *      5、《溶栓治疗》为“有”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "stemiScreening",
        type = StemiScreening.class,
        isCpcEnum = true,
        expectValue = {"1"}),
    @Condition(
        field = "stemiIsThrombolysis",
        type = String.class,
        isCpcEnum = false,
        expectValue = {"1"})
  })
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String stemiIsDirect;

  /**
   * 溶栓场所
   *
   * <pre>
   *   取值：
   *      1:本院急诊科 2:本院心内科 3:其他科室
   *
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“ 溶栓”
   *      4、《溶栓筛查》为“合适”
   *      5、《溶栓治疗》为“有”
   *
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "stemiScreening",
        type = StemiScreening.class,
        isCpcEnum = true,
        expectValue = {"1"}),
    @Condition(
        field = "stemiIsThrombolysis",
        type = String.class,
        isCpcEnum = false,
        expectValue = {"1"})
  })
  @JsonKey("STEMI_DIAGNOSIS_UNIT_CODE_DT")
  private StemiDiagnosisUnitCodeDT stemiDiagnosisUnitCodeDT;

  /**
   * 开始知情同意
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“ 溶栓”
   *      4、《溶栓筛查》为“合适”
   *      5、《溶栓治疗》为“有”
   *
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "stemiScreening",
        type = StemiScreening.class,
        isCpcEnum = true,
        expectValue = {"1"}),
    @Condition(
        field = "stemiIsThrombolysis",
        type = String.class,
        isCpcEnum = false,
        expectValue = {"1"})
  })
  private Date stemiThromStartAgreeTime;

  /**
   * 签署知情同意书
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“ 溶栓”
   *      4、《溶栓筛查》为“合适”
   *      5、《溶栓治疗》为“有”
   *
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "stemiScreening",
        type = StemiScreening.class,
        isCpcEnum = true,
        expectValue = {"1"}),
    @Condition(
        field = "stemiIsThrombolysis",
        type = String.class,
        isCpcEnum = false,
        expectValue = {"1"})
  })
  private Date stemiThromSignAgreeTime;

  /**
   * 开始溶栓时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“ 溶栓”
   *      4、《溶栓筛查》为“合适”
   *      5、《溶栓治疗》为“有”
   *
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "stemiScreening",
        type = StemiScreening.class,
        isCpcEnum = true,
        expectValue = {"1"}),
    @Condition(
        field = "stemiIsThrombolysis",
        type = String.class,
        isCpcEnum = false,
        expectValue = {"1"})
  })
  private Date stemiThromStartTimeDt;

  /**
   * 溶栓结束时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“ 溶栓”
   *      4、《溶栓筛查》为“合适”
   *      5、《溶栓治疗》为“有”
   *
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "stemiScreening",
        type = StemiScreening.class,
        isCpcEnum = true,
        expectValue = {"1"}),
    @Condition(
        field = "stemiIsThrombolysis",
        type = String.class,
        isCpcEnum = false,
        expectValue = {"1"})
  })
  private Date stemiThromEndTimeDt;

  /**
   * 药物
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“ 溶栓”
   *      4、《溶栓筛查》为“合适”
   *      5、《溶栓治疗》为“有”
   *
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "stemiScreening",
        type = StemiScreening.class,
        isCpcEnum = true,
        expectValue = {"1"}),
    @Condition(
        field = "stemiIsThrombolysis",
        type = String.class,
        isCpcEnum = false,
        expectValue = {"1"})
  })
  @JsonKey("STEMI_THROM_DRUG_TYPE_DT")
  private ThromDrugType stemiThromDrugTypeDt;

  /**
   * 剂量
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“ 溶栓”
   *      4、《溶栓筛查》为“合适”
   *      5、《溶栓治疗》为“有”
   *
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "stemiScreening",
        type = StemiScreening.class,
        isCpcEnum = true,
        expectValue = {"1"}),
    @Condition(
        field = "stemiIsThrombolysis",
        type = String.class,
        isCpcEnum = false,
        expectValue = {"1"})
  })
  @JsonKey("STEMI_THROM_DRUG_CODE_DT")
  private ThromDrugCode stemiThromDrugCodeDt;

  /**
   * 溶栓再通
   *
   * <pre>
   *   取值:
   *      0:否 1:是
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《再灌注措施》为“是”
   *      3、《措施》为“ 溶栓”
   *      4、《溶栓筛查》为“合适”
   *      5、《溶栓治疗》为“有”
   *
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "1"),
    @Condition(
        field = "stemiIsReperfusion",
        type = String.class,
        isCpcEnum = false,
        expectValue = "1"),
    @Condition(
        field = "stemiMeasures",
        type = StemiMeasures.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "stemiScreening",
        type = StemiScreening.class,
        isCpcEnum = true,
        expectValue = {"1"}),
    @Condition(
        field = "stemiIsThrombolysis",
        type = String.class,
        isCpcEnum = false,
        expectValue = {"1"})
  })
  @JsonKey("STEMI_IS_REPATENCY_DT")
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String stemiIsRepatencyDt;

  // *****************  诊断-NSTEMI ****************

  /**
   * 患者自愿放弃后续治疗
   *
   * <pre>
   *   取值：
   *      0:无 1:是
   *   条件：
   *      《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  @AllowValues(
      value = {"0", "1"},
      message = "0:无 1:是")
  private String nstemiGiveUpTreatment;

  /**
   * 初步诊断时间
   *
   * <pre>
   *   条件：
   *      《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  private Date nstemiDiagnosisTime;

  /**
   * 医生
   *
   * <pre>
   *   条件：
   *      《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  private String nstemiDoctorName;

  /**
   * 心功能分级
   *
   * <pre>
   *   取值：
   *      1:I 级(no CHF)
   *      2:II 级(rales and/or JVD)
   *      3:III 级(pulmonary edema)
   *      4:IV 级(cardiogenic shock)
   *   条件：
   *      《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  @JsonKey("NSTEMI_KILLIP_LEVEL")
  private KillipLevel nstemiKillipLevel;

  /**
   * 绕行急诊
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  private String nstemiIsBypassEmergency;

  /**
   * 绕行 CCU
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  private String nstemiIsBypassCcu;

  // ****************** 诊断-NSTEMI-药物 ******************

  /**
   * 抗血小板治疗
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String nstemiIsDrug;

  /**
   * 阿司匹林(剂量)
   *
   * <pre>
   *   条件：
   *      《初步诊断》为“NSTEMI”
   *      2、《抗血小板治疗》为“是”
   *   备注：
   *      保留 0 位小数
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(field = "nstemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private float nstemiAspirinDose;

  /**
   * 阿司匹林(时间)
   *
   * <pre>
   *   条件：
   *      《初步诊断》为“NSTEMI”
   *      2、《抗血小板治疗》为“是”
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(field = "nstemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private Date nstemiAspirinTime;

  /**
   * 氯吡格雷(剂量)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《抗血小板治疗》为“是”
   *   备注：
   *      保留 0 位小数
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(field = "stemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private float nstemiClopidogrelDose;

  /**
   * 氯吡格雷(时间)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《抗血小板治疗》为“是”
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(field = "nstemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private Date nstemiClopidogrelTime;

  /**
   * 替格瑞洛(剂量)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“STEMI”
   *      2、《抗血小板治疗》为“是”
   *   备注：
   *      保留 0 位小数
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(field = "nstemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private float nstemiTicagrelorDose;

  /**
   * 替格瑞洛(时间)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《抗血小板治疗》为“是”
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(field = "nstemiIsDrug", type = String.class, isCpcEnum = true, expectValue = "1")
  })
  private Date nstemiTicagrelorTime;

  /**
   * 抗凝
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String nstemiIsAnticoagulation;

  /**
   * 抗凝(抗凝)
   *
   * <pre>
   *   取值：
   *      1:普通肝素 2:低分子肝素 3:比伐卢定 4:磺达肝癸钠
   *
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《抗凝》为“是”
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiIsAnticoagulation",
        type = String.class,
        isCpcEnum = true,
        expectValue = "1")
  })
  @JsonKey("NSTEMI_ANTICOAGULATION_DRUG")
  private AnticoagulationDrug nstemiAnticoagulationDrug;

  /**
   * 抗凝(剂量)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《抗凝》为“是”
   *   备注：
   *      保留 1 位小数
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiIsAnticoagulation",
        type = String.class,
        isCpcEnum = true,
        expectValue = "1")
  })
  private float nstemiAnticoagulationDose;

  /**
   * 抗凝(时间)
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《抗凝》为“是”
   * </pre>
   */
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiIsAnticoagulation",
        type = String.class,
        isCpcEnum = true,
        expectValue = "1")
  })
  private Date nstemiAnticoagulationTime;

  /**
   * 他汀治疗
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String nstemiIntensifyStatinsTreat;

  /**
   * β受体阻滞剂
   *
   * <pre>
   *   取值：
   *      0:否 1:是
   *   条件：
   *      《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  @AllowValues(
      value = {"0", "1"},
      message = "0:否 1:是")
  private String nstemiIsBetaBlocker;

  // ****************** 诊断-NSTEMI-Grace 评估 *********************

  /**
   * Grace 评估
   *
   * <pre>
   *   取值:
   *      1:发病后曾出现心脏骤停
   *      2:心电图 ST 段改变
   *      3:心肌坏死标志物升高
   *
   *   条件:
   *      1、《初步诊断》为“NSTEMI”
   *
   *   备注:
   *      多选用竖线隔开。 如"1|2|3"
   * </pre>
   */
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  @JsonKey("NSTEMI_GRACE_ESTIMATE")
  private NstemiGraceEstimate[] nstemiGraceEstimates;

  /**
   * Grace 极高危条件
   *
   * <pre>
   *   取值:
   *      1:急性心力衰竭伴难治性心绞痛和 ST 段改变
   *      2:危及生命的心律失常或心脏骤停
   *      3:心源性休克或血流动力学不稳定
   *      4:心肌梗死机械性并发症
   *      5:再发 ST-T 动态演变，尤其是伴有间 歇性 ST 段抬高
   *
   *   条件:
   *      1、《初步诊断》为“NSTEMI”
   *
   *   备注:
   *      多选用竖线隔开。 如"1|2|3"
   * </pre>
   */
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  @JsonKey("NSTEMI_GRACE_HR_CONDITION")
  private NstemiGraceHrCondition[] nstemiGraceHrCondition;

  /**
   * Grace 分值
   *
   * <pre>
   *   条件:
   *      1、《初步诊断》为“NSTEMI”，并且《Grace 极高危条件》勾选了其中任何一项
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiGraceHrCondition",
        type = NstemiGraceHrCondition.class,
        isCpcEnum = true,
        strategy = ANY_VALUE_WITHIN_ENUMS_ARRAY)
  })
  private String nstemiGraceValue;

  /**
   * Grace 危险分层
   *
   * <pre>
   *   取值：
   *      1:极高危 2:高危 3:中危 4:低危
   *
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  private NstemiRiskLamination nstemiRiskLamination;

  // **************** 诊断-NSTEMI-再次危险分层 *******************

  /**
   * Grace 危险分层
   *
   * <pre>
   *   取值：
   *      0:未做 1:转为 STEMI 2:极高危 3:高危 4:中危 5:低危
   *
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   * </pre>
   */
  @Required
  @Condition(
      field = "cpDiagnosisCode",
      type = CpDiagnosisCode.class,
      isCpcEnum = true,
      expectValue = "2")
  @JsonKey("NSTEMI_RISK_LAMINATION_AG")
  private NstemiRiskLaminationAG nstemiRiskLaminationAG;

  /**
   * 再次危险分层时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《再次危险分层》为“转为 STEMI ” 或“极高危”或“高危”或“中危”或“低危”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiRiskLaminationAG",
        type = NstemiRiskLaminationAG.class,
        isCpcEnum = true,
        expectValue = {"1", "2", "3", "4", "5"})
  })
  @JsonKey("NSTEMI_RISK_LAMINATION_AG_TIME")
  private Date nstemiRiskLaminationAgTime;

  // ****************** 诊断-NSTEMI-处理策略 ********************

  /**
   * 策略
   *
   * <pre>
   *   取值：
   *      1:保守治疗(仅药物治疗)
   *      2:侵入性策略
   *
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《再次危险分层》为“未做”或“极高 危”或“高危”或“中危”或“低危”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiRiskLaminationAG",
        type = NstemiRiskLaminationAG.class,
        isCpcEnum = true,
        expectValue = {"0", "2", "3", "4", "5"})
  })
  private NstemiStrategy nstemiStrategy;

  /**
   * 侵入性策略
   *
   * <pre>
   *   取值：
   *      1:紧急介入治疗
   *      2:24H 内介入治疗
   *      3:72H 内介入治疗
   *      4:择期介入治疗
   *      5:CABG
   *
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《再次危险分层》为“未做”或“极高 危”或“高危”或“中危”或“低危”
   *      3、《策略》为“侵入性策略”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiRiskLaminationAG",
        type = NstemiRiskLaminationAG.class,
        isCpcEnum = true,
        expectValue = {"0", "2", "3", "4", "5"}),
    @Condition(
        field = "nstemiStrategy",
        type = NstemiStrategy.class,
        isCpcEnum = true,
        expectValue = {"2"})
  })
  private NstemiInvasiveStrategy nstemiInvasiveStrategy;

  /**
   * 决定医生
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《再次危险分层》为“未做”或“极高 危”或“高危”或“中危”或“低危”
   *      3、《策略》为“侵入性策略”
   *      4、《侵入性策略》为“紧急介入治疗”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiRiskLaminationAG",
        type = NstemiRiskLaminationAG.class,
        isCpcEnum = true,
        expectValue = {"0", "2", "3", "4", "5"}),
    @Condition(
        field = "nstemiStrategy",
        type = NstemiStrategy.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "nstemiInvasiveStrategy",
        type = NstemiInvasiveStrategy.class,
        isCpcEnum = true,
        expectValue = {"1"})
  })
  private String nstemiStrategyDoctorName;

  /**
   * 决定介入手术时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《再次危险分层》为“未做”或“极高 危”或“高危”或“中危”或“低危”
   *      3、《策略》为“侵入性策略”
   *      4、《侵入性策略》为“紧急介入治疗”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiRiskLaminationAG",
        type = NstemiRiskLaminationAG.class,
        isCpcEnum = true,
        expectValue = {"0", "2", "3", "4", "5"}),
    @Condition(
        field = "nstemiStrategy",
        type = NstemiStrategy.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "nstemiInvasiveStrategy",
        type = NstemiInvasiveStrategy.class,
        isCpcEnum = true,
        expectValue = {"1"})
  })
  private Date nstemiDecisionOperationTime;

  /**
   * 启动导管室时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《再次危险分层》为“未做”或“极高 危”或“高危”或“中危”或“低危”
   *      3、《策略》为“侵入性策略”
   *      4、《侵入性策略》为“紧急介入治疗”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiRiskLaminationAG",
        type = NstemiRiskLaminationAG.class,
        isCpcEnum = true,
        expectValue = {"0", "2", "3", "4", "5"}),
    @Condition(
        field = "nstemiStrategy",
        type = NstemiStrategy.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "nstemiInvasiveStrategy",
        type = NstemiInvasiveStrategy.class,
        isCpcEnum = true,
        expectValue = {"1"})
  })
  private Date nstemiStartConduitTime;

  /**
   * 开始知情同意时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《再次危险分层》为“未做”或“极高 危”或“高危”或“中危”或“低危”
   *      3、《策略》为“侵入性策略”
   *      4、《侵入性策略》为“紧急介入治疗”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiRiskLaminationAG",
        type = NstemiRiskLaminationAG.class,
        isCpcEnum = true,
        expectValue = {"0", "2", "3", "4", "5"}),
    @Condition(
        field = "nstemiStrategy",
        type = NstemiStrategy.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "nstemiInvasiveStrategy",
        type = NstemiInvasiveStrategy.class,
        isCpcEnum = true,
        expectValue = {"1"})
  })
  private Date nstemiStartAgreeTime;

  /**
   * 签署知情同意时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《再次危险分层》为“未做”或“极高 危”或“高危”或“中危”或“低危”
   *      3、《策略》为“侵入性策略”
   *      4、《侵入性策略》为“紧急介入治疗”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiRiskLaminationAG",
        type = NstemiRiskLaminationAG.class,
        isCpcEnum = true,
        expectValue = {"0", "2", "3", "4", "5"}),
    @Condition(
        field = "nstemiStrategy",
        type = NstemiStrategy.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "nstemiInvasiveStrategy",
        type = NstemiInvasiveStrategy.class,
        isCpcEnum = true,
        expectValue = {"1"})
  })
  private Date nstemiSignAgreeTime;

  /**
   * 实际介入治疗时间
   *
   * <pre>
   *   条件：
   *      1、《初步诊断》为“NSTEMI”
   *      2、《再次危险分层》为“未做”或“极高 危”或“高危”或“中危”或“低危”
   *      3、《策略》为“侵入性策略”
   *      4、《侵入性策略》为“24H 内介入治疗”
   * </pre>
   */
  @Required
  @Conditions({
    @Condition(
        field = "cpDiagnosisCode",
        type = CpDiagnosisCode.class,
        isCpcEnum = true,
        expectValue = "2"),
    @Condition(
        field = "nstemiRiskLaminationAG",
        type = NstemiRiskLaminationAG.class,
        isCpcEnum = true,
        expectValue = {"0", "2", "3", "4", "5"}),
    @Condition(
        field = "nstemiStrategy",
        type = NstemiStrategy.class,
        isCpcEnum = true,
        expectValue = {"2"}),
    @Condition(
        field = "nstemiInvasiveStrategy",
        type = NstemiInvasiveStrategy.class,
        isCpcEnum = true,
        expectValue = {"2"})
  })
  private Date nstemiActualInterventTime;

  // ****************** 诊断-UA ********************

}
