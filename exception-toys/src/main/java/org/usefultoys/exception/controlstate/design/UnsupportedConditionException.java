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


/**
 * The conditional statement (if/switch) has reached a condition that that is
 * known not to be possible. A better code should not provide flows or data
 * domains that allow conditions that are not possible. However, the domain or
 * interface may have been designed by third, or rigorous and strict domain may
 * not be worth.
 * <p>
 * For example, flags or enumerations may be declared as int values, but not all
 * integers are a valid flag or enumeration value.
 *
 * @author Daniel Felix Ferber
 */
public class UnsupportedConditionException extends UnsupportedConstrolStateException {
	private static final long serialVersionUID = 1L;

	public UnsupportedConditionException() { super(); }
	public UnsupportedConditionException(String message) { super(message); }
}
