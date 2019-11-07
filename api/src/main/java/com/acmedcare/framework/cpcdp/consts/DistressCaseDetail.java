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
 * 病情评估明细
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
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
