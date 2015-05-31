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
package org.usefultoys.exception.controlstate.design;

import org.usefultoys.exception.controlstate.IllegalControlStateException;

/**
 * The control flow attempted to execute code without having first executed a
 * dependency that was designed to be executed first. An ideal design should not
 * require call dependency within the same class. This is common for objects
 * with methods that are designed to be executed in a certain order.
 *
 * @author Daniel Felix Ferber
 */
public class TemporalDependencyException extends IllegalControlStateException {
	private static final long serialVersionUID = 1L;

	public TemporalDependencyException() { super(); }
	public TemporalDependencyException(String message) { super(message); }
//
//	/** Raises exception if condition is false. */
//	public static boolean apply(boolean condition) {
//		if (!condition) throw new TemporalDependencyException();
//		return true;
//	}
//
//	public static void apply(boolean condition, String message) {
//		if (! condition) throw new TemporalDependencyException(message);
//	}
}
