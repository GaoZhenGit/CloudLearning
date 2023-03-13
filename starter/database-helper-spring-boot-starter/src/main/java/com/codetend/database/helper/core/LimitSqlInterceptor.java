package com.codetend.database.helper.core;

import com.codetend.database.helper.DatabaseHelperProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.Properties;

@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update",
                args = {
                        MappedStatement.class,
                        Object.class}),
        @Signature(type = Executor.class, method = "query",
                args = {
                        MappedStatement.class,
                        Object.class,
                        RowBounds.class,
                        ResultHandler.class})
})

public class LimitSqlInterceptor implements Interceptor {
    private DatabaseHelperProperties properties;

    public LimitSqlInterceptor(DatabaseHelperProperties properties) {
        this.properties = properties;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("database-helper intercepting");
        String methodName = invocation.getMethod().getName();
        Object result;
        switch (methodName) {
            case "query":
                result = interceptQuery(invocation);
                break;
            case "update":
                result = interceptUpdate(invocation);
                break;
            default:
                result = invocation.proceed();
        }
        log.info("database-helper intercept finished");
        return result;
    }

//    @Override
//    public Object plugin(Object target) {
//        return Interceptor.super.plugin(target);
//    }

//    @Override
//    public void setProperties(Properties properties) {
//        Interceptor.super.setProperties(properties);
//    }

    @SneakyThrows
    private Object interceptQuery(Invocation invocation) {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        SqlSource sqlSource = mappedStatement.getSqlSource();
        BoundSql boundSql = sqlSource.getBoundSql(args[1]);
        //  获取到原始sql
        String sql = boundSql.getSql();
        if (sql.contains("limit") || sql.contains("LIMIT")) {

        } else {
            sql = sql + " limit " + properties.limit;
        }
        //拦截示范，这个不一定是RawSqlSource
        if (sqlSource instanceof RawSqlSource) {
            Field sqlSourceField = RawSqlSource.class.getDeclaredField("sqlSource");
            sqlSourceField.setAccessible(true);
            StaticSqlSource staticSqlSource = (StaticSqlSource) sqlSourceField.get(sqlSource);
            Field sqlField = StaticSqlSource.class.getDeclaredField("sql");
            sqlField.setAccessible(true);
            sqlField.set(staticSqlSource, sql);
        }
        args[0] = copyFromMappedStatement(mappedStatement, sqlSource);
        return invocation.proceed();
    }

    /**
     * 回塞sql
     *
     * @param ms           MappedStatement
     * @param newSqlSource SqlSource
     * @return MappedStatement
     */
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
            builder.keyProperty(ms.getKeyProperties()[0]);
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    private Object interceptUpdate(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        Object ret = invocation.proceed();
        Object[] args = invocation.getArgs();
        if (args.length > 1) {
            Object obj = args[1];
            Class<?> clz = obj.getClass();
            for (Field field : clz.getDeclaredFields()) {
                if (field.getName().equals("name") && field.getType() == String.class) {
                    field.setAccessible(true);
                    String str = (String) field.get(obj);
                    if (str.contains("mock")) {
                        throw new RuntimeException("mock sql error");
                    }
                }
            }
        }
        return ret;
    }
}
