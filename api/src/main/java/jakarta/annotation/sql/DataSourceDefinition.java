/*
 * Copyright (c) 2009, 2024 Oracle and/or its affiliates. All rights reserved.
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

package jakarta.annotation.sql;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation used to define a container {@code DataSource} to
 * be registered with JNDI. The {@code DataSource} may be configured by
 * setting the annotation elements for commonly used {@code DataSource}
 * properties.  Additional standard and vendor-specific properties may be
 * specified using the {@code properties} element.
 * <p>
 *
 * The data source will be registered under the name specified in the
 * {@code name} element. It may be defined to be in any valid
 * Jakarta EE namespace, which will determine the accessibility of
 * the data source from other components.
 * <p>
 * A JDBC driver implementation class of the appropriate type, either
 * {@code DataSource}, {@code ConnectionPoolDataSource}, or
 * {@code XADataSource}, must be indicated by the {@code className}
 * element. The availability of the driver class will be assumed at runtime.
 *<p>
 * DataSource properties should not be specified more than once. If
 * the url annotation element contains a DataSource property that was also
 * specified using the corresponding annotation element or was specified in
 * the properties annotation element, the precedence order is undefined
 * and implementation specific:
 * <p>
 * {@snippet :
 * @DataSourceDefinition(name="java:global/MyApp/MyDataSource",
 *     className="org.apache.derby.jdbc.ClientDataSource",
 *     url="jdbc:derby://localhost:1527/myDB;user=bill",
 *     user="lance",
 *     password="secret",
 *     databaseName="testDB",
 *     serverName="luckydog"
 * )// DO NOT DO THIS!!!
 * }
 * <p>
 * In the above example, the {@code databaseName}, {@code user}
 * and {@code serverName} properties were specified as part of the
 * {@code url} property and using the corresponding
 * annotation elements. This should be avoided.
 * <p>
 * If the {@code properties} annotation element is used and contains a
 * DataSource property that was also specified using the corresponding
 * annotation element, the annotation element value takes precedence.
 * For example:
 * <p>
 * {@snippet :
 * @DataSourceDefinition(name="java:global/MyApp/MyDataSource",
 *     className="org.apache.derby.jdbc.ClientDataSource",
 *     user="lance",
 *     password="secret",
 *     databaseName="testDB",
 *     serverName="luckydog",
 *     properties= {"databaseName=myDB", "databaseProp=doThis"}
 *   )// DO NOT DO THIS!!!
 * }
 * <p>
 * This would result in the following values being used when configuring
 * the DataSource:
 * <ul>
 * <li>serverName=luckydog</li>
 * <li>portNumber=1527</li>
 * <li>databaseName=testDB</li>
 * <li>user=lance</li>
 * <li>password=secret</li>
 * <li>databaseProp=doThis</li>
 * </ul>
 * <p>
 * Vendors are not required to support properties that do not normally
 * apply to a specific data source type. For example, specifying the
 * {@code transactional} property to be {@code true} but supplying
 * a value for {@code className} that implements a data source class
 * other than {@code XADataSource} may not be supported.
 * <p>
 * Vendor-specific properties may be combined with or used to
 *  override standard data source properties defined using this annotation.
 * <p>
 * {@code DataSource} properties that are specified and are not supported
 * in a given configuration or cannot be mapped to a vendor specific
 * configuration property may be ignored.
 * <p>
 * Examples:
 * <br>
 * {@snippet :
 * @DataSourceDefinition(name="java:global/MyApp/MyDataSource",
 *     className="com.foobar.MyDataSource",
 *     portNumber=6689,
 *     serverName="myserver.com",
 *     user="lance",
 *     password="secret"
 * )
 * }
 * <p>
 * Using a {@code URL}:
 * <br>
 * {@snippet :
 * @DataSourceDefinition(name="java:global/MyApp/MyDataSource",
 *     className="org.apache.derby.jdbc.ClientDataSource",
 *     url="jdbc:derby://localhost:1527/myDB",
 *     user="lance",
 *     password="secret"
 * )
 * }
 * <p>
 * An example lookup of the DataSource from an Jakarta Enterprise Beans:
 * {@snippet :
 * @Stateless
 * public class MyStatelessEJB {
 *     @Resource(lookup="java:global/MyApp/myDataSource")
 *     DataSource myDB;
 *     ...
 * }
 * }
 *
 * @see javax.sql.DataSource
 * @see javax.sql.XADataSource
 * @see javax.sql.ConnectionPoolDataSource
 * @since Common Annotations 1.1
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(DataSourceDefinitions.class)
public @interface DataSourceDefinition {

    /**
     * JNDI name by which the data source will be registered.
     * @since 1.1
     */
     String name();

    /**
     * Name of a DataSource class that implements
     *  {@code javax.sql.DataSource} or {@code javax.sql.XADataSource}
     * or {@code javax.sql.ConnectionPoolDataSource}.
     * @since 1.1
     */
    String className();

    /**
     * Description of this data source
     * @since 1.1
     */
    String description() default "";

    /**
     * A JDBC URL.  If the {@code url}  annotation element contains a
     * DataSource property that was also specified using the corresponding
     * annotation element, the precedence order is undefined and
     * implementation specific.
     * @since 1.1
     */
    String url() default "";

    /**
     * User name to use for connection authentication.
     * @since 1.1
     */
    String user() default "";

    /**
     * Password to use for connection authentication.
     * @since 1.1
     */
    String password() default "";

    /**
     * Name of a database on a server.
     * @since 1.1
     */
    String databaseName() default "";

    /**
     * Port number where a server is listening for requests.
     * @since 1.1
     */
    int portNumber() default -1;

    /**
     * Database server name.
     * @since 1.1
     */
    String serverName() default "localhost";

    /**
     * Isolation level for connections. The Isolation level
     * must be one of the following:
     *
     * <ul>
     * <li>Connection.TRANSACTION_NONE,
     * <li>Connection.TRANSACTION_READ_UNCOMMITTED,
     * <li>Connection.TRANSACTION_READ_COMMITTED,
     * <li>Connection.TRANSACTION_REPEATABLE_READ,
     * <li>Connection.TRANSACTION_SERIALIZABLE
     *</ul>
     *
     * Default is vendor-specific.
     * @since 1.1
     */
    int isolationLevel() default -1;

    /**
     * Set to {@code false} if connections should not participate
     * in transactions.
     * <p>
     * Default is to enlist in a transaction when one is active or becomes
     * active.
     * @since 1.1
     */
    boolean transactional() default true;

    /**
     * Number of connections that should be created when a connection pool
     * is initialized.
     * <p>
     * Default is vendor-specific
     * @since 1.1
     */
    int initialPoolSize() default -1;

    /**
     * Maximum number of connections that should be concurrently allocated for a
     * connection pool.
     * <p>
     * Default is vendor-specific.
     * @since 1.1
     */
    int maxPoolSize() default -1;

    /**
     * Minimum number of connections that should be allocated for a
     * connection pool.
     * <p>
     * Default is vendor-specific.
     * @since 1.1
     */
    int minPoolSize() default -1;

    /**
     * The number of seconds that a physical connection
     * should remain unused in the pool before the
     * connection is closed for a connection pool.
     * <p>
     * Default is vendor-specific
     * @since 1.1
     */
    int maxIdleTime() default -1;

    /**
     * The total number of statements that a connection pool should keep open.
     * A value of 0 indicates that the caching of statements is disabled for
     * a connection pool.
     * <p>
     * Default is vendor-specific
     * @since 1.1
     */
    int maxStatements() default -1;
    /**
     * Used to specify vendor-specific properties and less commonly
     * used {@code DataSource} properties such as:
     *
     * <ul>
     * <li>dataSourceName
     * <li>networkProtocol
     * <li>propertyCycle
     * <li>roleName
     * </ul>
     *
     *  Properties are specified using the format:
     *  <i>propertyName=propertyValue</i>  with one property per array element.
     * <p>
     * If a DataSource property is specified in the {@code properties}
     * element and the annotation element for the  property is also
     * specified, the annotation element value takes precedence.
     * @since 1.1
     */
    String[] properties() default {};


    /**
     * Sets the maximum time in seconds that this data source will wait while
     * attempting to connect to a database. A value of zero specifies that
     * the timeout is the default system timeout if there is one; otherwise,
     * it specifies that there is no timeout.
     * <p>
     * Default is vendor-specific.
     * @since 1.1
     */
    int loginTimeout() default 0;
}
