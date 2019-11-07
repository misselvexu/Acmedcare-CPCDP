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
import com.acmedcare.framework.cpcdp.annotation.Condition;
import com.acmedcare.framework.cpcdp.annotation.Conditions;
import com.acmedcare.framework.cpcdp.annotation.JsonKey;
import com.acmedcare.framework.cpcdp.annotation.Required;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * {@link PatientRegister}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2019/11/6.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("ALL")
public class PatientRegister implements Serializable {

  private String id;

  /** 注册编号 */
  private String registerId;

  /** 医院 ID */
  @Required private String hospitalId;

  /** 姓名 */
  @Required private String name;

  /** 性别 */
  @Required private Gender gender;

  /**
   * Range: 0-250
   *
   * <p>
   */
  @Min(0)
  @Max(255)
  @Required
  private int age;

  /**
   * 出生日期
   *
   * <p>Example: 1999-12-01
   */
  private String birthday;

  /** 民族 */
  private String nation;

  /** 联系电话 */
  private String contactPhone;

  /** 证件类型 */
  @Required private CredentialsType credentialsType;

  /** 证件号 */
  @Required
  @Conditions({
    @Condition(
        field = "credentialsType",
        expectValue = {"1", "2"})
  })
  private String idCard;

  /** 职业 */
  private Job job;

  /** 文化程度 */
  private Culturedegree culturedegree;

  /** 婚姻状况 */
  @JsonKey("MARITALSTATUS")
  private MaritalStatus maritalStatus;

  @Min(0)
  @Max(300)
  private int height;

  /** 体重 */
  @Min(0)
  @Max(300)
  private float weight;

  /**
   * 状态
   *
   * @see Status#S01
   * @see Status#S02
   */
  private Status status;

  // ====== Inner Beans Defined =======

  /**
   * 性别
   *
   * @since ${project.version}
   */
  public enum Gender {

    /** 男 */
    MALE("1", "男"),

    /** 女 */
    FEMALE("2", "女");

    String key;
    String value;

    Gender(String key, String value) {
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
   * 证件类型
   *
   * @since ${project.version}
   */
  public enum CredentialsType {

    /** 无 */
    NON("0", "无"),

    /** 身份证 */
    ID_CARD("1", "身份证"),

    /** 护照 */
    PASSPORT("2", "护照");

    String key;
    String value;

    CredentialsType(String key, String value) {
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
   * 职业
   *
   * @since ${project.version}
   */
  public enum Job {

    /** */
    J01("01", "国家机关、党群组织、企事业单位 负责人"),
    J02("02", "各类专业技术人员"),
    J03("03", "办事人员和有关人员"),
    J04("04", "商业及服务型工作人员"),
    J05("05", "农、林、牧、渔、水利业生产人员"),
    J06("06", "生产工人，运输工人和相关人员"),
    J07("07", "下岗或无业"),
    J08("08", "离退休"),
    J09("09", "学生"),
    J10("10", "军人"),
    J11("11", "不便分类的其他从业人员"),
    ;

    String key;
    String value;

    Job(String key, String value) {
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
   * 职业
   *
   * @since ${project.version}
   */
  public enum Culturedegree {

    /** */
    C01("01", "没上过学"),
    C02("02", "小学"),
    C03("03", "初中"),
    C04("04", "高中或中专(中技)"),
    C05("05", "大专"),
    C06("06", "本科及以上"),
    C09("09", "不详"),
    ;
    String key;
    String value;

    Culturedegree(String key, String value) {
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
   * 婚姻状况
   *
   * @since ${project.version}
   */
  public enum MaritalStatus {

    /** */
    M01("01", "未婚"),
    M02("02", "未婚"),
    M03("03", "丧偶"),
    M04("04", "离异"),
    M09("09", "不详"),
    ;
    String key;
    String value;

    MaritalStatus(String key, String value) {
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
