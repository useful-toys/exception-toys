# slf4j-toys #

**SLF4J-TOYS is set of useful things that work with SLF4J:**
 * Consistent and predictable logger naming convention;
 * Operation demarcation and performance measure; 
 * Application resource usage;
 * Large data logged using formatted print streams;
 * Parsable message for later analysis.

## Consistent and predictable logger naming convention

*Nearly every application reuses the fully qualified class name as logger name. Though simple, there are several disadvantages. It imposes one logger per class. Logger names reflect strict software structure instead of self-documenting logical organization. It implies long string constants conventions between implementation and logger configuration. Any refactor breaks existing logger configuration.*

The **LoggerFactory substitute** eases the consistent and predictable logger naming convention. The original `getLogger(...)` methods were preserved for compatibility.

The `getLogger(Class<?> clazz, String name)` helps to fine tune loggers for specific operations or features of interest provided by your class. For example, you could define loggers to separately track `open()` and `close()` methods.

For a consistent  and predictable logger convention, I suggest creating a logger hierarchy that describes features groups, features and operations. For example, instead of *com.company.application.authentication.dao.hibernate.UserDAOImpl*, one could use *authentication.persistence.hibernate.user*: shorter, more intuitive and does not leak implementation details. The suggested hierarchy is defined purely on and hierarchy of short and self describing names, instand of class hierarchy. One each level, define a logger based on the parent level logger by calling `getLogger(Logger logger, String name)`. This protects your logging configuration against refactoring as the logger name is preserved.

## Operation demarcation and performance measure ##

*Most logging messages intend telling that an operation of interest succeeds of failed. Sometimes, the beginning of the operation is logged too. Further, the performance concerned want log execution count and time. Some are interested on additional input/output data of the operation, of state that influences the operation. Others want to know how system resources were affected by an operation.*

The **Meter** is a special logger that address all these requirements. It clearly delimiters the beginning and ending of an operation, distinguishing success from failure. It counts executions and records its time. It tracks input/output data and relevant state. Further, it traces relevant system resources before and after the operation. The log messages are parsable for later comprehensive analysis. The level of the logger associated to the operation controls the amount of information.

## Similar projects ##

 * [Perf4J](http://perf4j.codehaus.org/) Appearantly discotinued project.
 * [Spped4J](http://perf4j.codehaus.org/) A continuation and enhancement of Perf4J.
