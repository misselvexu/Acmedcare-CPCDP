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

package com.acmedcare.framework.cpcdp.kits;

import java.util.Collection;
import java.util.Map;

/**
 * Assert Kits
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version 2.1.0 - 24/10/2018.
 */
public final class Assert {

  // for code coverage
  private static final Assert INSTANCE = new Assert();

  private Assert() {}

  /**
   * Assert a boolean expression, throwing <code>IllegalArgumentException</code> if the test result
   * is <code>false</code>.
   *
   * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
   *
   * @param expression a boolean expression
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if expression is <code>false</code>
   */
  public static void isTrue(boolean expression, String message) {
    if (!expression) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Assert a boolean expression, throwing <code>IllegalArgumentException</code> if the test result
   * is <code>false</code>.
   *
   * <pre class="code">Assert.isTrue(i &gt; 0);</pre>
   *
   * @param expression a boolean expression
   * @throws IllegalArgumentException if expression is <code>false</code>
   */
  public static void isTrue(boolean expression) {
    isTrue(expression, "[Assertion failed] - this expression must be true");
  }

  /**
   * Assert that an object is <code>null</code> .
   *
   * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
   *
   * @param object the object to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the object is not <code>null</code>
   */
  public static void isNull(Object object, String message) {
    if (object != null) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Assert that an object is <code>null</code> .
   *
   * <pre class="code">Assert.isNull(value);</pre>
   *
   * @param object the object to check
   * @throws IllegalArgumentException if the object is not <code>null</code>
   */
  public static void isNull(Object object) {
    isNull(object, "[Assertion failed] - the object argument must be null");
  }

  /**
   * Assert that an object is not <code>null</code> .
   *
   * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
   *
   * @param object the object to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the object is <code>null</code>
   */
  public static void notNull(Object object, String message) {
    if (object == null) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Assert that an object is not <code>null</code> .
   *
   * <pre class="code">Assert.notNull(clazz);</pre>
   *
   * @param object the object to check
   * @throws IllegalArgumentException if the object is <code>null</code>
   */
  public static void notNull(Object object) {
    notNull(object, "[Assertion failed] - this argument is required; it must not be null");
  }

  /**
   * Assert that the given String is not empty; that is, it must not be <code>null</code> and not
   * the empty String.
   *
   * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
   *
   * @param text the String to check
   * @param message the exception message to use if the assertion fails
   * @see Strings#hasLength
   */
  public static void hasLength(String text, String message) {
    if (!Strings.hasLength(text)) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Assert that the given String is not empty; that is, it must not be <code>null</code> and not
   * the empty String.
   *
   * <pre class="code">Assert.hasLength(name);</pre>
   *
   * @param text the String to check
   * @see Strings#hasLength
   */
  public static void hasLength(String text) {
    hasLength(
        text,
        "[Assertion failed] - this String argument must have length; it must not be null or empty");
  }

  /**
   * Assert that the given String has valid text content; that is, it must not be <code>null</code>
   * and must contain at least one non-whitespace character.
   *
   * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
   *
   * @param text the String to check
   * @param message the exception message to use if the assertion fails
   * @see Strings#hasText
   */
  public static void hasText(String text, String message) {
    if (!Strings.hasText(text)) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Assert that the given String has valid text content; that is, it must not be <code>null</code>
   * and must contain at least one non-whitespace character.
   *
   * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
   *
   * @param text the String to check
   * @see Strings#hasText
   */
  public static void hasText(String text) {
    hasText(
        text,
        "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
  }

  /**
   * Assert that the given text does not contain the given substring.
   *
   * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
   *
   * @param textToSearch the text to search
   * @param substring the substring to find within the text
   * @param message the exception message to use if the assertion fails
   */
  public static void doesNotContain(String textToSearch, String substring, String message) {
    if (Strings.hasLength(textToSearch)
        && Strings.hasLength(substring)
        && textToSearch.indexOf(substring) != -1) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Assert that the given text does not contain the given substring.
   *
   * <pre class="code">Assert.doesNotContain(name, "rod");</pre>
   *
   * @param textToSearch the text to search
   * @param substring the substring to find within the text
   */
  public static void doesNotContain(String textToSearch, String substring) {
    doesNotContain(
        textToSearch,
        substring,
        "[Assertion failed] - this String argument must not contain the substring ["
            + substring
            + "]");
  }

  /**
   * Assert that an array has elements; that is, it must not be <code>null</code> and must have at
   * least one element.
   *
   * <pre class="code">Assert.notEmpty(array, "The array must have elements");</pre>
   *
   * @param array the array to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the object array is <code>null</code> or has no elements
   */
  public static void notEmpty(Object[] array, String message) {
    if (Objects.isEmpty(array)) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Assert that an array has elements; that is, it must not be <code>null</code> and must have at
   * least one element.
   *
   * <pre class="code">Assert.notEmpty(array);</pre>
   *
   * @param array the array to check
   * @throws IllegalArgumentException if the object array is <code>null</code> or has no elements
   */
  public static void notEmpty(Object[] array) {
    notEmpty(
        array,
        "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
  }

  public static void notEmpty(byte[] array, String msg) {
    if (Objects.isEmpty(array)) {
      throw new IllegalArgumentException(msg);
    }
  }

  /**
   * Assert that an array has no null elements. Note: Does not complain if the array is empty!
   *
   * <pre class="code">Assert.noNullElements(array, "The array must have non-null elements");</pre>
   *
   * @param array the array to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the object array contains a <code>null</code> element
   */
  public static void noNullElements(Object[] array, String message) {
    if (array != null) {
      for (int i = 0; i < array.length; i++) {
        if (array[i] == null) {
          throw new IllegalArgumentException(message);
        }
      }
    }
  }

  /**
   * Assert that an array has no null elements. Note: Does not complain if the array is empty!
   *
   * <pre class="code">Assert.noNullElements(array);</pre>
   *
   * @param array the array to check
   * @throws IllegalArgumentException if the object array contains a <code>null</code> element
   */
  public static void noNullElements(Object[] array) {
    noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
  }

  /**
   * Assert that a collection has elements; that is, it must not be <code>null</code> and must have
   * at least one element.
   *
   * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
   *
   * @param collection the collection to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the collection is <code>null</code> or has no elements
   */
  public static void notEmpty(Collection collection, String message) {
    if (collection == null || collection.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Assert that a collection has elements; that is, it must not be <code>null</code> and must have
   * at least one element.
   *
   * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
   *
   * @param collection the collection to check
   * @throws IllegalArgumentException if the collection is <code>null</code> or has no elements
   */
  public static void notEmpty(Collection collection) {
    notEmpty(
        collection,
        "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
  }

  /**
   * Assert that a Map has entries; that is, it must not be <code>null</code> and must have at least
   * one entry.
   *
   * <pre class="code">Assert.notEmpty(map, "Map must have entries");</pre>
   *
   * @param map the map to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the map is <code>null</code> or has no entries
   */
  public static void notEmpty(Map map, String message) {
    if (map == null || map.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Assert that a Map has entries; that is, it must not be <code>null</code> and must have at least
   * one entry.
   *
   * <pre class="code">Assert.notEmpty(map);</pre>
   *
   * @param map the map to check
   * @throws IllegalArgumentException if the map is <code>null</code> or has no entries
   */
  public static void notEmpty(Map map) {
    notEmpty(
        map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
  }

  /**
   * Assert that the provided object is an instance of the provided class.
   *
   * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
   *
   * @param clazz the required class
   * @param obj the object to check
   * @throws IllegalArgumentException if the object is not an instance of clazz
   * @see Class#isInstance
   */
  public static void isInstanceOf(Class clazz, Object obj) {
    isInstanceOf(clazz, obj, "");
  }

  /**
   * Assert that the provided object is an instance of the provided class.
   *
   * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
   *
   * @param type the type to check against
   * @param obj the object to check
   * @param message a message which will be prepended to the message produced by the function
   *     itself, and which may be used to provide context. It should normally end in a ": " or ". "
   *     so that the function generate message looks ok when prepended to it.
   * @throws IllegalArgumentException if the object is not an instance of clazz
   * @see Class#isInstance
   */
  public static void isInstanceOf(Class type, Object obj, String message) {
    notNull(type, "Type to check against must not be null");
    if (!type.isInstance(obj)) {
      throw new IllegalArgumentException(
          message
              + "Object of class ["
              + (obj != null ? obj.getClass().getName() : "null")
              + "] must be an instance of "
              + type);
    }
  }

  /**
   * Assert that <code>superType.isAssignableFrom(subType)</code> is <code>true</code>.
   *
   * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
   *
   * @param superType the super type to check
   * @param subType the sub type to check
   * @throws IllegalArgumentException if the classes are not assignable
   */
  public static void isAssignable(Class superType, Class subType) {
    isAssignable(superType, subType, "");
  }

  /**
   * Assert that <code>superType.isAssignableFrom(subType)</code> is <code>true</code>.
   *
   * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
   *
   * @param superType the super type to check against
   * @param subType the sub type to check
   * @param message a message which will be prepended to the message produced by the function
   *     itself, and which may be used to provide context. It should normally end in a ": " or ". "
   *     so that the function generate message looks ok when prepended to it.
   * @throws IllegalArgumentException if the classes are not assignable
   */
  public static void isAssignable(Class superType, Class subType, String message) {
    notNull(superType, "Type to check against must not be null");
    if (subType == null || !superType.isAssignableFrom(subType)) {
      throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
    }
  }

  /**
   * Assert a boolean expression, throwing <code>IllegalStateException</code> if the test result is
   * <code>false</code>. Call isTrue if you wish to throw IllegalArgumentException on an assertion
   * failure.
   *
   * <pre class="code">
   * Assert.state(providerId == null, "The providerId property must not already be initialized");
   * </pre>
   *
   * @param expression a boolean expression
   * @param message the exception message to use if the assertion fails
   * @throws IllegalStateException if expression is <code>false</code>
   */
  public static void state(boolean expression, String message) {
    if (!expression) {
      throw new IllegalStateException(message);
    }
  }

  /**
   * Assert a boolean expression, throwing {@link IllegalStateException} if the test result is
   * <code>false</code>.
   *
   * <p>Call {@link #isTrue(boolean)} if you wish to throw {@link IllegalArgumentException} on an
   * assertion failure.
   *
   * <pre class="code">Assert.state(providerId == null);</pre>
   *
   * @param expression a boolean expression
   * @throws IllegalStateException if the supplied expression is <code>false</code>
   */
  public static void state(boolean expression) {
    state(expression, "[Assertion failed] - this state invariant must be true");
  }
}