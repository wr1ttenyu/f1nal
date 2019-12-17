package wr1ttenyu.f1nal.study.designpattern.pattern23.command;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;

/**
 * 在 {@link JdbcTemplate} 中, 不同类型以及是否批量执行sql(比如：查询、更新等)，执行方式不同
 * {@link StatementCallback} 相当于命令接口，不同的实现类执行不同类型或者数量的sql
 * {@link JdbcTemplate} 则充当命令对象，调用具体的 {@link StatementCallback} 实现类
 * {@link ExecuteStatementCallback} 是 {@link JdbcTemplate} 的内部类，实现了命令接口，同时他在内部实现了命令的执行，
 *      也就是说  他即是命令对象  同时也是  命令具体执行的实现
 * {@link QueryStatementCallback}
 * {@link UpdateStatementCallback}
 * {@link BatchUpdateStatementCallback}
 * 上面三个也都是 {@link JdbcTemplate} 中的内部类 并且实现了 {@link StatementCallback}
 */
public class SpringCommandPatternApply {
}
