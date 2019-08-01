/*
 * Copyright (c) 2009, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package javax.annotation;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * The <code>ManagedBean</code> annotation marks a POJO (Plain Old Java Object)
 * as a ManagedBean.  A ManagedBean supports a small set of basic services
 * such as resource injection, lifecycle callbacks and interceptors.
 *
 * @since Common Annotations 1.1
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface ManagedBean {
    /**
     * The name of the Managed Bean. Managed Bean names must be unique within a
     * Jakarta EE module. For each named Managed Bean, Java EE containers must make
     * available the following entries in JNDI, using the same naming scheme used
     * for EJB components.
     * <p>
     * In the application namespace: <p>
     * java:app/&lt;module-name&gt;/&lt;bean-name&gt; <p>
     * In the module namespace of the module containing the Managed Bean:
     * <p> java:module/&lt;bean-name&gt;
     *
     */
    public String value() default "";
}
