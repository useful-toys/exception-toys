package org.usefultoys.exception;
/*
 * Copyright 2012 Daniel Felix Ferber
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * An all purpose runtime exception that may carry information that further describes
 * the cause of failure and the operation that were running when the exception
 * was raised. This is a all purpose solution to identify all possible failures for
 * an operation, without need to create a full heavy weighted hierarchy of
 * exceptions.
 * <p>
 * The exception may carry three kinds of information.
 * <ul>
 * <li>Pair, Value metadata map with.
 * <li>Objects that describe the operaion that failed.
 * <li>Objects that describe the reason of failure.
 * </ul>
 * Typically, those objects are constants or enumeration values.
 *
 * @author Daniel Felix Ferber
 */
public class RuntimeFailure extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RuntimeFailure.class.getSimpleName());

	/**
	 * Additional structured data carried by the exception for further
	 * information of the error.
	 */
	protected final Map<String, Object> metaData = new TreeMap<String, Object>();

	/**
	 * Object(s) that describe the operation(s) within the context of the
	 * exception. There may be more than one operation if one operation depends
	 * on others.
	 */
	protected final Set<Object> operations = new LinkedHashSet<Object>();
	/**
	 * Object(s) that describe the reasons(s) of the exception. Reason is the
	 * cause of the failure.
	 */
	protected final Set<Object> reasons = new LinkedHashSet<Object>();

	protected final static Object nullToStr(Object o) {
		return o;
	}

	public RuntimeFailure() {
		super();
	}
	
	public RuntimeFailure(Object reason) {
		super();
		if (reason == null) {
			RuntimeFailure.logger.log(Level.SEVERE, "Called RuntimeFailure(reason={0}) with null parameter.", reason);
		}
		this.reasons.add(reason);
	}

	public RuntimeFailure(Object reason, Throwable cause) {
		super(cause);
		if (reason == null || cause == null) {
			RuntimeFailure.logger.log(Level.SEVERE, "Called RuntimeFailure(reason={0}, cause={1}) with null parameter.", new Object[] {RuntimeFailure.nullToStr(reason), RuntimeFailure.nullToStr(cause)});
		}
		this.reasons.add(reason);
	}

	public RuntimeFailure(Object operation, Object reason) {
		super();
		if (operation == null || reason == null) {
			RuntimeFailure.logger.log(Level.SEVERE, "Called RuntimeFailure(operation={0}, reason={1}) with null parameter.", new Object[]{RuntimeFailure.nullToStr(operation), RuntimeFailure.nullToStr(reason)});
		}
		this.reasons.add(reason);
		this.operations.add(operation);
	}

	public RuntimeFailure(Object operation, Object reason, Throwable cause) {
		super(cause);
		if (operation == null || reason == null || cause == null) {
			RuntimeFailure.logger.log(Level.SEVERE, "Called RuntimeFailure(operation={0}, reason={1}, cause={2}) with null parameter.", new Object[] { RuntimeFailure.nullToStr(operation), RuntimeFailure.nullToStr(reason), RuntimeFailure.nullToStr(cause) });
		}
		this.reasons.add(reason);
		this.operations.add(operation);
	}

	/** Builder method to add meta data to the exception. */
	public RuntimeFailure data(String key, Object value) {
		if (key == null || value == null) {
			RuntimeFailure.logger.log(Level.SEVERE, "Called data(key={0}, value={1}) with null parameter.", new Object[] {RuntimeFailure.nullToStr(key), RuntimeFailure.nullToStr(value)});
			return this;
		}
		metaData.put(key, value);
		return this;
	}

	/** Builder method to add meta data to the exception. */
	public RuntimeFailure data(Map<String, ? extends Object> moreMetaData) {
		if (moreMetaData == null) {
			RuntimeFailure.logger.log(Level.SEVERE, "Called data(moreMetaData={0}) with null parameter.", moreMetaData);
			return this;
		}
		metaData.putAll(moreMetaData);
		return this;
	}

	/** Builder method to add a reason to the exception. */
	public RuntimeFailure reason(Object reason) {
		if (reason == null) {
			RuntimeFailure.logger.log(Level.SEVERE, "Called reason(reason={0}) with null parameter.", reason);
			return this;
		}
		reasons.add(reason);
		return this;
	}

	/** Builder method to add a operation to the operation hierarchy of the exception. */
	public RuntimeFailure operation(Object operation) {
		if (operation == null) {
			RuntimeFailure.logger.log(Level.SEVERE, "Called operation(operation={0}) with null parameter.", operation);
			return this;
		}
		operations.add(operation);
		return this;
	}

	public final Object getData(String key) {
		return this.metaData.get(key);
	}

	public final Map<String, Object> getData() {
		return Collections.unmodifiableMap(metaData);
	}

	public final Set<Object> getReasons() {
		return Collections.unmodifiableSet(reasons);
	}

	public final Set<Object> getOperations() {
		return Collections.unmodifiableSet(operations);
	}

	public final boolean isOperation(Object operation) {
		return operations.contains(operation);
	}

	public final boolean hasReason(Object reason) {
		return reasons.contains(reason);
	}

	public final static RuntimeFailure enrich(Throwable e) {
		return RuntimeFailure.enrichImpl(e);
	}

	public final static RuntimeFailure enrich(Throwable e, Object operation) {
		return RuntimeFailure.enrichImpl(e).operation(operation);
	}

	public final static RuntimeFailure enrich(Throwable e, Object operation, Object reason) {
		return RuntimeFailure.enrichImpl(e).operation(operation).reason(reason);
	}

	public final static RuntimeFailure enrichImpl(Throwable e) {
		if (e instanceof RuntimeFailure) {
			return (RuntimeFailure) e;
		}
		RuntimeFailure newE = new RuntimeFailure(e);
		StackTraceElement[] st = newE.getStackTrace();
		st = Arrays.copyOfRange(st, 2, st.length);
		newE.setStackTrace(st);
		return newE;
	}
}
