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
package org.usefultoys.exception.unimplemented;



/**
 * Marks a method that has not yet been implemented. Signals that there is
 * pending work of design or programming to be done.
 *
 * @author Daniel Felix Ferber
 */
public class UnimplementedMethod extends UnimplementedConstrol {
	private static final long serialVersionUID = 1L;
	public UnimplementedMethod() { super(); }
	public UnimplementedMethod(String message) { super(message); }
}
