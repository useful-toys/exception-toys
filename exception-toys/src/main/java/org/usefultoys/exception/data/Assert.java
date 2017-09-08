/*
 * Copyright 2017 Daniel Felix Ferber
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.usefultoys.exception.data;

import java.util.Collection;
import java.util.List;

/**
 * A collection typical validation methods.
 * Simplifies raising specialized exceptions for assertion errors.
 *
 * @author Daniel Felix Ferber
 */
public abstract class Assert {
	private Assert() {
		// cannot create instance
	}

	public static final Assert Argument = new Argument();
	
	public static class Argument extends Assert {
		@Override protected RuntimeException createException(Object rule) { return new IllegalArgumentData(rule); }
		@Override protected RuntimeException createException(String message) { return new IllegalArgumentData(message); }
		@Override protected RuntimeException createException() { return new IllegalArgumentData(); }
	};

	public static final Assert Attribute = new Attribute ();
	
	public static class Attribute extends Assert {
		@Override protected RuntimeException createException(Object rule) { return new IllegalAttributeData(rule); }
		@Override protected RuntimeException createException(String message) { return new IllegalAttributeData(message); }
		@Override protected RuntimeException createException() { return new IllegalAttributeData(); }
	};

	public static final Assert Environment = new Environment();

	public static class Environment extends Assert {
		@Override protected RuntimeException createException(Object rule) { return new IllegalEnvironmentData(rule); }
		@Override protected RuntimeException createException(String message) { return new IllegalEnvironmentData(message); }
		@Override protected RuntimeException createException() { return new IllegalEnvironmentData(); }

		public final boolean exist(String name) throws RuntimeException {
			if (System.getenv(name) == null) throw createException(String.format("%s must exist in environment", name));
			return true;
		}
	};

	public static final Assert Precondition = new Precondition();
	
	public static class Precondition extends Assert {
		@Override protected RuntimeException createException(Object rule) { return new IllegalPreCondition(rule); }
		@Override protected RuntimeException createException(String message) { return new IllegalPreCondition(message); }
		@Override protected RuntimeException createException() { return new IllegalPreCondition(); }
	};

	public static final Assert Poscondition = new Poscondition();

	public static class Poscondition extends Assert {
		@Override protected RuntimeException createException(Object rule) { return new IllegalPosCondition(rule); }
		@Override protected RuntimeException createException(String message) { return new IllegalPosCondition(message); }
		@Override protected RuntimeException createException() { return new IllegalPosCondition(); }
	};

	public static final Assert Invariant = new Invariant();
	
	public static class Invariant extends Assert {
		@Override protected RuntimeException createException(Object rule) { return new IllegalInvariant(rule); }
		@Override protected RuntimeException createException(String message) { return new IllegalInvariant(message); }
		@Override protected RuntimeException createException() { return new IllegalInvariant(); }
	};

	protected abstract RuntimeException createException();
	protected abstract RuntimeException createException(String message);
	protected abstract RuntimeException createException(Object rule);

	// ************************************************************************
	// *** CUSTOM *************************************************************
	// ************************************************************************
	
	/** A custom condition must be satisfied. */
	public final boolean check(boolean condition) throws RuntimeException {
		if (! condition) throw createException();
		return true;
	}

	/** A custom condition must be satisfied. */
	public final boolean check(Object rule, boolean expression) throws RuntimeException {
		if (! expression) throw createException(rule);
		return true;
	}

	/** All custom conditions must be satisfied. */
	public final boolean checkAll(boolean ... conditions) throws RuntimeException {
		for (boolean b : conditions) {
			if (!b) throw createException();
		}
		return true;
	}

	/** All custom conditions must be satisfied. */
	public final boolean checkAll(Object rule, boolean ... conditions) throws RuntimeException {
		for (boolean b : conditions) {
			if (!b) throw createException(rule);
		}
		return true;
	}

	// ************************************************************************
	// *** NON NULL ***********************************************************
	// ************************************************************************

	/** Argument(s) must not be <code>null</code>. */
	public final <T> boolean notNull(T argument) throws RuntimeException {
		if (argument == null) throw createException("null");
		return true;
	}

	/** Argument(s) must not be <code>null</code>. */
	public final boolean notNull(Object ... arguments) throws RuntimeException {
		for (Object b : arguments) {
			if (b == null) throw createException("null");
		}
		return true;
	}

	// ************************************************************************
	// *** EQUAL **************************************************************
	// ************************************************************************

	/** Given value must be equal to the expected value. */
	public final boolean equal(int value, int expected) throws RuntimeException {
		if (value != expected) throw createException(String.format("%d must be equal to %d]", value, expected));
		return true;
	}

	/** Given value must be equal to the expected value. */
	public final boolean equal(long value, long expected) throws RuntimeException {
		if (value != expected) throw createException(String.format("%d must be equal to %d]", value, expected));
		return true;
	}

	/** Given value must be equal to the expected value. */
	public final <T> boolean equal(T value, T expected) throws RuntimeException {
		if (value == expected) return true;
		if (value != expected && (value == null || ! value.equals(expected))) throw createException(String.format("%d must be equal to %d]", Assert.nullToStr(value), Assert.nullToStr(expected)));
		return true;
	}

	// ************************************************************************
	// *** RANGE **************************************************************
	// ************************************************************************

	/** Value must be within a range. */
	public final boolean range(int value, int min, int max) throws RuntimeException {
		if (value <= min || value >= max ) throw createException(String.format("%d must be in range [%d-%d]", value, min, max));
		return true;
	}

	/** Value must be within a range. */
	public final boolean range(long value, long min, long max) throws RuntimeException {
		if (value <= min || value >= max ) throw createException(String.format("%d must be in range [%d-%d]", value, min, max));
		return true;
	}

	/** Value must be within a range. */
	public final <T extends Comparable<T>> boolean range(T value, T min, T max) throws RuntimeException {
		if (value == null) throw createException(String.format("value must not be null", value, min, max));
		if (value.compareTo(min) < 0 || value.compareTo(max) > 0 ) throw createException(String.format("%s must be in range [%s-%s]", value.toString(), min.toString(), max.toString()));
		return true;
	}


	// ************************************************************************
	// *** ARRAY **************************************************************
	// ************************************************************************

	/** Value must be an index of given array. */
	public final boolean range(int value, int array[]) throws RuntimeException {
		if (value < 0 || value >= array.length) throw createException(String.format("%d must be in range [0-%d]", value, 0, array.length-1));
		return true;
	}

	/** Value must be an index of given array. */
	public final boolean range(int value, long array[]) throws RuntimeException {
		if (value < 0 || value >= array.length) throw createException(String.format("%d must be in range [0-%d]", value, 0, array.length-1));
		return true;
	}

	/** Value must be an index of given array. */
	public final boolean range(int value, boolean array[]) throws RuntimeException {
		if (value < 0 || value >= array.length) throw createException(String.format("%d must be in range [0-%d]", value, 0, array.length-1));
		return true;
	}

	/** Value must be an index of given array. */
	public final boolean range(int value, float array[]) throws RuntimeException {
		if (value < 0 || value >= array.length) throw createException(String.format("%d must be in range [0-%d]", value, 0, array.length-1));
		return true;
	}

	/** Value must be an index of given array. */
	public final boolean range(int value, double array[]) throws RuntimeException {
		if (value < 0 || value >= array.length) throw createException(String.format("%d must be in range [0-%d]", value, 0, array.length-1));
		return true;
	}

	/** Value must be an index of given array. */
	public final boolean range(int value, Object array[]) throws RuntimeException {
		if (value < 0 || value >= array.length) throw createException(String.format("%d must be in range [0-%d]", value, 0, array.length-1));
		return true;
	}

	public final boolean range(int value, List<?> list) throws RuntimeException {
		if (value < 0 || value >= list.size()) throw createException(String.format("%d must be in range [0-%d]", value, 0, list.size()-1));
		return true;
	}

	// ************************************************************************
	// *** CONTAINS ***********************************************************
	// ************************************************************************

	public final boolean contains(Object value, Collection<?> collection) throws RuntimeException {
		if (! collection.contains(value)) throw createException(String.format("collection must contain %s", value.toString()));
		return true;
	}

	// ************************************************************************
	// *** NON NEGATIVE *******************************************************
	// ************************************************************************

	/** Values must be non negative (greater or equal to zero). */
	public final boolean nonNegative(int value) throws RuntimeException {
		if (value < 0) throw createException(String.format("%d must not be negative", value));
		return true;
	}

	// ************************************************************************
	// *** POSITIVE ***********************************************************
	// ************************************************************************

	/** Argument(s) must not be positive ( > 0). */
	public final boolean positive(int value) throws RuntimeException {
		if (value <= 0) throw createException(String.format("%d must be positive", value));
		return true;
	}
	public final boolean positive(Integer value) throws RuntimeException {
		if (value == null || value.intValue() <= 0) throw createException(String.format("%d must be positive", value));
		return true;
	}
	public final boolean positive(double value) throws RuntimeException {
		if (value <= 0 || Double.isNaN(value) || Double.isInfinite(value)) throw createException(String.format("%f must be positive", Double.valueOf(value)));
		return true;
	}
	public final boolean positive(Double value) throws RuntimeException {
		if (value == null || value.doubleValue() <= 0.0 || value.isNaN() || value.isInfinite()) throw createException(String.format("%f must be positive", value));
		return true;
	}

//
//	/** Argument(s) must not be <code>null</code> as expected by a system rule. */
//	public final <T> boolean notNull(Object rule, T argument) throws RuntimeException {
//		if (argument == null) throw createException(rule);
//		return true;
//	}

	private static Object nullToStr(Object o) {
		if (o == null)
			return "null";
		return o;
	}
}
