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
package org.usefultoys.exception.bug;

/**
 * Marks a declared exception that is not possible be to thrown. If code is
 * correct, then this exception must not be thrown.
 * <p>
 * Example:
 * <p>
 * <pre>
 * if (! file.exists()) {
 *   return null;
 * }
 * try {
 *   return new FileInputStream(file)
 * } catch (FileNotFoundException e) {
 *   // file existence was already assured
 *   ImpossibleException.ignore(e);
 *   // or
 *   throw new ImpossibleException(e);
 * }
 * </pre>
 *
 * @author Daniel Felix Ferber
 */
public class ImpossibleException extends Impossible {
    private static final long serialVersionUID = 1L;

    public ImpossibleException(Throwable cause) {
        super(cause);
    }

    public ImpossibleException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Simply hides the impossible exception received within the
     * <code>catch</code> block. It will, however, rethrow the exception.
     */
    public static void ignore(Throwable e) {
        throw new ImpossibleException(e);
    }

}
