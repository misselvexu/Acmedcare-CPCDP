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
import com.acmedcare.framework.cpcdp.annotation.Required;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class TreatmentBean implements Serializable {

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





}
