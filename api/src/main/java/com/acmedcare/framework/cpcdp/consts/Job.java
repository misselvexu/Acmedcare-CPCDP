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

package com.acmedcare.framework.cpcdp.consts;

/**
 * 职业
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
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
