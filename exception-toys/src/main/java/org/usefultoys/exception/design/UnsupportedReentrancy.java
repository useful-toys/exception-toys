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
package org.usefultoys.exception.design;

/**
 * The method was not intended to be called simultaneously by multiple threads.
 * A better design should support concurrency gracefully, but this is not always worth or possible.
 * <p>
 * Example:
 *
 * @author Daniel Felix Ferber
 */
public class UnsupportedReentrancy extends UnsupportedControl {
	private static final long serialVersionUID = 1L;
	
	public UnsupportedReentrancy() { super(); }
	public UnsupportedReentrancy(String message) { super(message); }
}
