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
 * Grace 极高危条件
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version}
 */
public enum NstemiGraceHrCondition {

  /** */
  NSGRC_1("1", "急性心力衰竭伴难治性心绞痛和 ST 段改变"),
  NSGRC_2("2", "危及生命的心律失常或心脏骤停"),
  NSGRC_3("3", "心源性休克或血流动力学不稳定"),
  NSGRC_4("4", "心肌梗死机械性并发症"),
  NSGRC_5("5", "再发 ST-T 动态演变，尤其是伴有间 歇性 ST 段抬高"),
  ;

  String key;
  String value;

  NstemiGraceHrCondition(String key, String value) {
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
