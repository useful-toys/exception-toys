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
package org.usefultoys.exception.design;

import org.usefultoys.exception.Failure;

/**
 * Common class for errors caused by control flow (execution) achieving a state
 * that should not be called by convention. This exception supplements the
 * standard {@link RuntimeException} by providing a more semantically rich
 * description of the problem.
 *
 * @author Daniel Felix Ferber
 */
class UnsupportedControl extends Failure {
	private static final long serialVersionUID = 1L;
	
	protected UnsupportedControl() { super(); }
	protected UnsupportedControl(String message, Throwable cause) { super(message, cause); }
	protected UnsupportedControl(String message) { super(message); }
	protected UnsupportedControl(Throwable cause) { super(cause); }
}
