package com.xcu.dao;

import com.xcu.utils.JDBCUtil;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public abstract class BaseDAO {

    /**
     * 通用的增、删、改的方法
     * @param sql sql
     * @param params 给sql中的?设置的值列表，可以是0~n
     * @return 受影响行数
     * @throws SQLException
     */
    protected int executeUpdate(String sql, Object... params) throws SQLException {
        //1.获取连接(JDBCUtilV2)
        Connection connection = JDBCUtil.getConnection();

        //3.预编译SQL语句(BaseDao)
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //4.为占位符赋值，执行SQL语句，获取结果(BaseDao)
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }

        int rows = preparedStatement.executeUpdate();

        //6.释放资源(JDBCUtilV2)
        preparedStatement.close();
        //这里检查下是否开启事务，开启不关闭连接，业务方法关闭!
        //connection.getAutoCommit()为false，不要在这里回收connection，由开启事务的地方回收
        //connection.getAutoCommit()为true，正常回收连接
        //没有开启事务的话，直接回收关闭即可!
        if (connection.getAutoCommit()) {
            JDBCUtil.release();
        }

        return rows;
    }

    /**
     * 通用的查询多个Javabean对象的方法，例如：多个员工对象，多个部门对象等
     * @param sql sql
     * @param clazz 这里的clazz接收的是T类型的Class对象
     * @param params 给sql中的?设置的值列表，可以是0~n
     * @return 查询的对象集合
     * @param <T>
     */
    protected <T> ArrayList<T> executeQuery(String sql, Class<T> clazz, Object... params) throws Exception {
        //1.获取连接(JDBCUtilV2)
        Connection connection = JDBCUtil.getConnection();

        //3.预编译SQL语句(BaseDao)
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //4.为占位符赋值，执行SQL语句，获取结果(BaseDao)
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }

        ArrayList<T> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();

        /*
        获取结果集的元数据对象。
        元数据对象中有该结果集一共有几列、列名称是什么等信息
         */
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount(); //获取结果集列数

        //5.处理结果(BaseDao)
        while (resultSet.next()) {

            T t = clazz.newInstance(); //要求这个类型必须有公共的无参构造

            for (int i = 1; i <= columnCount; i++) {
                //for循环一次，代表取某一行的1个单元格的值
                Object value = resultSet.getObject(i);

                //这个值应该是t对象的某个属性值
                //获取该属性对应的Field对象
                //String columnName = metaData.getColumnName(i);//获取第i列的字段名
                //这里再取别名可能没办法对应上
                String columnName = metaData.getColumnLabel(i);//获取第i列的字段名或字段的别名
                Field field = clazz.getDeclaredField(columnName);
                field.setAccessible(true);//这么做可以操作private的属性

                field.set(t, value);
            }
            list.add(t);
        }

        //6.释放资源(JDBCUtilV2)
        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JDBCUtil.release();
        }


        return list;
    }

    /**
     * 通用的查询多个Javabean对象的方法，例如：多个员工对象，多个部门对象等
     * @param sql sql
     * @param clazz 这里的clazz接收的是T类型的Class对象
     * @param params 给sql中的?设置的值列表，可以是0~n
     * @return 查询的对象实例
     * @param <T>
     */
    protected <T> T executeQueryBean(String sql, Class<T> clazz, Object... params) throws Exception {
        ArrayList<T> list = executeQuery(sql, clazz, params);

        // 如果clazz是基本类型，例如Integer、Long或String，直接处理
        if (clazz == Integer.class || clazz == Long.class || clazz == String.class) {
            if (list == null || list.isEmpty()) {
                return null;
            }
            return list.get(0);
        }

        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 用于查询单个基本数据类型结果的方法
     * @param sql
     * @param clazz
     * @param params
     * @return
     * @param <T>
     * @throws Exception
     */
    protected <T> T executeQuerySingle(String sql, Class<T> clazz, Object... params) throws Exception {
        // 1.获取连接(JDBCUtilV2)
        Connection connection = JDBCUtil.getConnection();

        // 3.预编译SQL语句(BaseDao)
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 4.为占位符赋值，执行SQL语句，获取结果(BaseDao)
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        T result = null;

        // 处理标量查询结果
        if (resultSet.next()) {
            // 直接从结果集中获取标量值并进行类型转换
            if (clazz == Integer.class) {
                result = clazz.cast(resultSet.getInt(1));
            } else if (clazz == Long.class) {
                result = clazz.cast(resultSet.getLong(1));
            } else if (clazz == Double.class) {
                result = clazz.cast(resultSet.getDouble(1));
            } else if (clazz == String.class) {
                result = clazz.cast(resultSet.getString(1));
            } else if (clazz == Boolean.class) {
                result = clazz.cast(resultSet.getBoolean(1));
            } else {
                throw new RuntimeException("Unsupported type: " + clazz.getName());
            }
        }

        // 6.释放资源(JDBCUtilV2)
        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JDBCUtil.release();
        }

        return result;
    }



}
