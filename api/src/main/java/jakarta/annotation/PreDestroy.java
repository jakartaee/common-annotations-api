/*
 * Copyright (c) 2005, 2023 Oracle and/or its affiliates. All rights reserved.
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

package jakarta.annotation;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * The {@code PreDestroy} annotation is used on a method as a
 * callback notification to signal that the instance is in the
 * process of being removed by the container. The method annotated
 * with {@code PreDestroy} is typically used to
 * release resources that it has been holding. This annotation must be
 * supported by all container-managed objects that support the use of
 * the {@code PostConstruct} annotation except the Jakarta EE application
 * client. The method on which the {@code PreDestroy} annotation
 * is applied must fulfill all of the following criteria:
 * <ul>
 * <li>The method must not have any parameters except in the case of
 * interceptors in which case it takes an {@code InvocationContext}
 * object as defined by the Jakarta Interceptors specification.</li>
 * <li>The method defined on an interceptor class or superclass of an
 * interceptor class must have one of the following signatures:
 * <p>
 * {@code void <METHOD>(InvocationContext)}
 * <p>
 * {@code Object <METHOD>(InvocationContext) throws Exception}
 * <p>
 * <i>Note: A PreDestroy interceptor method must not throw application
 * exceptions, but it may be declared to throw checked exceptions including
 * the java.lang.Exception if the same interceptor method interposes on
 * business or timeout methods in addition to lifecycle events. If a
 * PreDestroy interceptor method returns a value, it is ignored by
 * the container.</i>
 * </li>
 * <li>The method defined on a non-interceptor class must have the
 * following signature:
 * <p>
 * {@code void <METHOD>()}
 * </li>
 * <li>The method on which PreDestroy is applied may be public, protected,
 * package private or private.</li>
 * <li>The method must not be static.</li>
 * <li>The method should not be final.</li>
 * <li>If the method throws an unchecked exception it is ignored by
 * the container.</li>
 * </ul>
 *
 * @see jakarta.annotation.PostConstruct
 * @see jakarta.annotation.Resource
 * @since 1.6, Common Annotations 1.0
 */

@Documented
@Retention (RUNTIME)
@Target(METHOD)
public @interface PreDestroy {
}
