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
package infra.exception.controlstate.bug;

import infra.exception.controlstate.IllegalControlStateException;

/**
 * Marks a code point that known not being possible to be reached by execution.
 * If code is correct, then this code point must not be achieved.
 * <p>
 * This is a generic mark. There are more specific marks that should be
 * preferred:
 * <ul>
 * <li> {@link ImpossibleConditionException}
 * <li> {@link ImpossibleException}
 * <li> {@link ImpossibleMethodException}
 * </ul>
 * Example:
 *
 * <pre>
 * while (true) {
 *   bla bla bla (no break anywhere);
 * }
 * // Execution never reaches this code point.
 * throw new ImpossibleControlStateException();
 * </pre>
 *
 * @author Daniel Felix Ferber
 */
public class ImpossibleControlStateException extends IllegalControlStateException {
	private static final long serialVersionUID = 1L;
	public ImpossibleControlStateException() { super(); }
	public ImpossibleControlStateException(String message) { super(message); }
	protected ImpossibleControlStateException(Throwable e) { super(e); }
	protected ImpossibleControlStateException(String message, Throwable e) { super(message, e); }
}
