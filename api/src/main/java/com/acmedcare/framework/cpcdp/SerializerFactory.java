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

package com.acmedcare.framework.cpcdp;

import com.acmedcare.framework.cpcdp.annotation.*;
import com.acmedcare.framework.cpcdp.consts.DistressCaseDetail;
import com.acmedcare.framework.cpcdp.gson.CpcdpFieldNamingStrategy;
import com.acmedcare.framework.cpcdp.gson.CpcdpTypeAdapterFactory;
import com.acmedcare.framework.cpcdp.gson.serializer.DistressCaseDetailSerializer;
import com.acmedcare.framework.cpcdp.kits.Assert;
import com.acmedcare.framework.cpcdp.kits.Reflections;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * {@link com.acmedcare.framework.cpcdp.SerializerFactory}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2019/11/8.
 */
public final class SerializerFactory {

  private static final Logger log = LoggerFactory.getLogger(SerializerFactory.class);

  private static final String PRETTY_ENABLED_KEY = "cpcdp.json.pretty.enabled";

  private static final String DEFAULT_DATA_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

  private static final Boolean ENABLED_PRETTY = Boolean.valueOf(System.getProperty(PRETTY_ENABLED_KEY, "false"));

  private static final String CPC_ENUM_KEY_METHOD_NAME = "key";

  private static final String CPC_ENUM_VALUE_METHOD_NAME = "value";

  private static Gson gson;

  static {
    GsonBuilder builder =
        new GsonBuilder()
            .setFieldNamingStrategy(new CpcdpFieldNamingStrategy())
            .registerTypeAdapterFactory(new CpcdpTypeAdapterFactory())
            .registerTypeAdapter(DistressCaseDetail[].class, new DistressCaseDetailSerializer())
            .setDateFormat(DEFAULT_DATA_FORMAT_PATTERN);

    if (ENABLED_PRETTY) {
      builder.setPrettyPrinting();
    }

    gson = builder.create();
  }

  public static String toJson(Object src) {
    return toJson(src, src.getClass());
  }

  /**
   * This method serializes the specified object, including those of generic types, into its
   * equivalent Json representation. This method must be used if the specified object is a generic
   * type. For non-generic objects, use {@link #toJson(Object)} instead.
   *
   * @param src the object for which JSON representation is to be created
   * @param typeOfSrc The specific genericized type of src. You can obtain this type by using the
   *     {@link com.google.gson.reflect.TypeToken} class. For example, to get the type for {@code
   *     Collection<Foo>}, you should use:
   *     <pre>
   * Type typeOfSrc = new TypeToken&lt;Collection&lt;Foo&gt;&gt;(){}.getType();
   * </pre>
   *
   * @return Json representation of {@code src}
   */
  public static String toJson(Object src, Type typeOfSrc) {

    // Check @Condition & @Required & @AllowValues & @ComplexCondition

    Field[] fields = src.getClass().getDeclaredFields();

    Map<String, Field> fieldsMap = Maps.newConcurrentMap();

    Stream.of(fields).parallel().forEach(field -> fieldsMap.put(field.getName(), field));

    try {

      for (Field field : fields) {
        validate(fieldsMap, field, src);
      }

    } catch (InvalidBeanParameterException e) {
      throw new CpcdpException(e.getMessage(), e);
    } catch (Exception e) {
      throw new CpcdpException("校验异常", e);
    }

    return gson.toJson(src, typeOfSrc);
  }

  private static void validate(Map<String, Field> fieldsMap, Field field, Object instance)
      throws InvalidBeanParameterException {

    if (log.isDebugEnabled()) {
      log.debug("------------------------------------------");
      log.debug("Field : {}" , field.getName());
    }

    boolean required = field.isAnnotationPresent(Required.class);
    boolean allowValues = field.isAnnotationPresent(AllowValues.class);
    boolean condition = field.isAnnotationPresent(Condition.class);
    boolean conditions = field.isAnnotationPresent(Conditions.class);
    boolean complexCondition = field.isAnnotationPresent(ComplexCondition.class);

    // Check Required
    if (required) {
      Object value = Reflections.getFieldValue(instance, field.getName());
      if (value == null) {
        if (condition || conditions || complexCondition) {
          // empty biz
        } else {
          throw new InvalidBeanParameterException("属性: <" + field.getName() + "> 不能为空.");
        }
      } else {
        if (allowValues) {
          AllowValues allows = field.getAnnotation(AllowValues.class);
          Set<String> definedAllowValues = Sets.newHashSet(allows.value());
          String message = allows.message();
          if (!definedAllowValues.contains(value.toString())) {
            throw new InvalidBeanParameterException("属性: <" + field.getName() + "> 取值范围: " + (Strings.isNullOrEmpty(message) ? Arrays.toString(definedAllowValues.toArray()) : message));
          }
        }
      }

      if (condition || conditions || complexCondition) {
        // Check Condition(s)
        processConditions(fieldsMap, field, instance);
      }
    }

    if (log.isDebugEnabled()) {
      log.debug("{}, {}, {}", field, instance, fieldsMap);
    }
  }

  private static void processConditions(
      Map<String, Field> fieldsMap, Field field, Object instance) throws InvalidBeanParameterException{

    boolean condition = field.isAnnotationPresent(Condition.class);
    boolean conditions = field.isAnnotationPresent(Conditions.class);
    boolean complexCondition = field.isAnnotationPresent(ComplexCondition.class);

    // process @Condition
    if(condition) {

      Condition conditionAnnotation = field.getAnnotation(Condition.class);

      if(isConditionMatch(conditionAnnotation,fieldsMap,field,instance)){
        Object value = Reflections.getFieldValue(instance, field.getName());
        if(value == null) {
          throw new InvalidBeanParameterException("属性: <" + field.getName() + "> 不能为空.");
        }
      }
    }

    // process @Conditions
    if(conditions) {

      Conditions conditionsAnnotation = field.getAnnotation(Conditions.class);
      Condition[] conditionsArray = conditionsAnnotation.value();
      List<Boolean> result = Lists.newArrayList();

      for (Condition temp : conditionsArray) {
        result.add(isConditionMatch(temp,fieldsMap,field,instance));
      }

      if(!result.contains(Boolean.FALSE)) {
        Object value = Reflections.getFieldValue(instance, field.getName());
        if(value == null) {
          throw new InvalidBeanParameterException("属性: <" + field.getName() + "> 不能为空.");
        }
      }
    }

    // process @ComplexCondition
    if(complexCondition) {

      ComplexCondition complexConditionAnnotation = field.getAnnotation(ComplexCondition.class);
      Conditions[] conditionsArray = complexConditionAnnotation.value();
      ComplexCondition.Symbol symbol = complexConditionAnnotation.symbol();

      List<Boolean> result = Lists.newArrayList();

      for (Conditions temp : conditionsArray) {
        result.add(isConditionsMatch(temp,fieldsMap,field,instance));
      }

      switch (symbol) {
        case OR:

          if(result.contains(Boolean.TRUE)) {
            Object value = Reflections.getFieldValue(instance, field.getName());
            if(value == null) {
              throw new InvalidBeanParameterException("属性: <" + field.getName() + "> 不能为空.");
            }
          }

          break;
        case AND:

          if(!result.contains(Boolean.FALSE)) {
            Object value = Reflections.getFieldValue(instance, field.getName());
            if(value == null) {
              throw new InvalidBeanParameterException("属性: <" + field.getName() + "> 不能为空.");
            }
          }
          break;
        default:
          break;
      }
    }
  }

  /**
   * Check is {@link Conditions} matched
   *
   * @param conditionsAnnotation instance of {@link Conditions}
   * @param fieldsMap object field(s) map cache
   * @param field instance of object's {@link Field}
   * @param instance object instance
   * @return matched return true , otherwise return false
   */
  private static boolean isConditionsMatch(Conditions conditionsAnnotation, Map<String, Field> fieldsMap, Field field, Object instance) {

    Condition[] conditionsArray = conditionsAnnotation.value();
    List<Boolean> result = Lists.newArrayList();

    for (Condition temp : conditionsArray) {
      result.add(isConditionMatch(temp,fieldsMap,field,instance));
    }

    return !result.contains(Boolean.FALSE);
  }

  /**
   * Check is {@link Condition} matched
   *
   * @param conditionAnnotation instance of {@link Condition}
   * @param fieldsMap object field(s) map cache
   * @param field instance of object's {@link Field}
   * @param instance object instance
   * @return matched return true , otherwise return false
   */
  @SuppressWarnings("DuplicatedCode")
  private static boolean isConditionMatch(Condition conditionAnnotation, Map<String, Field> fieldsMap, Field field, Object instance) {

    String fieldName = conditionAnnotation.field();
    Set<String> expectValues = Sets.newHashSet(conditionAnnotation.expectValue());

    Class clazz = conditionAnnotation.type();
    boolean isCpcEnum = conditionAnnotation.isCpcEnum();

    String expectFieldName = conditionAnnotation.field();
    Object expectFieldOriginValue = Reflections.getFieldValue(instance,fieldName);
    Object expectFieldRealValue = expectFieldOriginValue;

    if(isCpcEnum && expectFieldOriginValue != null) {
      expectFieldRealValue = Reflections.invokeMethod(expectFieldOriginValue,CPC_ENUM_KEY_METHOD_NAME,null,null);
    }

    Field expectField = fieldsMap.get(expectFieldName);
    boolean condition = expectField.isAnnotationPresent(Condition.class);
    boolean conditions = expectField.isAnnotationPresent(Conditions.class);
    boolean complexCondition = expectField.isAnnotationPresent(ComplexCondition.class);

    // recursion check
    if(fieldsMap.containsKey(expectFieldName)){
      if(condition || conditions || complexCondition) {

        if(expectFieldRealValue != null && !expectValues.contains(expectFieldRealValue.toString())) {
          return false;
        }

        if(condition) {

          Condition f0 = expectField.getAnnotation(Condition.class);
          boolean match = isConditionMatch(f0,fieldsMap,expectField,instance);
          if(match) {
            Object value = Reflections.getFieldValue(instance, expectField.getName());
            if(value == null) {
              throw new InvalidBeanParameterException("属性: <" + expectField.getName() + "> 不能为空.");
            }
          }

        }

        if(conditions) {
          Conditions conditionsAnnotation = expectField.getAnnotation(Conditions.class);
          Condition[] conditionsArray = conditionsAnnotation.value();
          List<Boolean> result = Lists.newArrayList();

          for (Condition temp : conditionsArray) {
            result.add(isConditionMatch(temp,fieldsMap,expectField,instance));
          }

          if(!result.contains(Boolean.FALSE)) {
            Object value = Reflections.getFieldValue(instance, expectField.getName());
            if(value == null) {
              throw new InvalidBeanParameterException("属性: <" + expectField.getName() + "> 不能为空.");
            }
          }
        }

        if(complexCondition) {

          ComplexCondition complexConditionAnnotation = expectField.getAnnotation(ComplexCondition.class);
          Conditions[] conditionsArray = complexConditionAnnotation.value();
          ComplexCondition.Symbol symbol = complexConditionAnnotation.symbol();

          List<Boolean> result = Lists.newArrayList();

          for (Conditions temp : conditionsArray) {
            result.add(isConditionsMatch(temp,fieldsMap,expectField,instance));
          }

          switch (symbol) {
            case OR:

              if(result.contains(Boolean.TRUE)) {
                Object value = Reflections.getFieldValue(instance, expectField.getName());
                if(value == null) {
                  throw new InvalidBeanParameterException("属性: <" + expectField.getName() + "> 不能为空.");
                }
              }

              break;
            case AND:

              if(!result.contains(Boolean.FALSE)) {
                Object value = Reflections.getFieldValue(instance, expectField.getName());
                if(value == null) {
                  throw new InvalidBeanParameterException("属性: <" + expectField.getName() + "> 不能为空.");
                }
              }
              break;
            default:
              break;
          }
        }

      }
    }

    Assert.notNull(expectFieldRealValue,"条件属性: <" + fieldName + "> 值不能为空.");

    if(log.isDebugEnabled()) {
      log.debug("Condition: {} | {}, {}, {}, {}", field.getName(), fieldName, expectValues, isCpcEnum, clazz);
    }

    return expectValues.contains(expectFieldRealValue.toString());
  }

  public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
    return gson.fromJson(json, classOfT);
  }

  /**
   * {@link InvalidBeanParameterException}
   *
   * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
   * @version ${project.version} - 2019/11/8.
   */
  public static class InvalidBeanParameterException extends RuntimeException {
    public InvalidBeanParameterException(String message) {
      super(message);
    }
  }
}
