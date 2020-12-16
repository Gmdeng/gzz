package com.gzz.mybatis.dao;

import com.gzz.mybatis.pojo.MiLogin;
import com.gzz.mybatis.pojo.MiLoginExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface MiLoginMapper {
    @SelectProvider(type=MiLoginSqlProvider.class, method="countByExample")
    long countByExample(MiLoginExample example);

    @DeleteProvider(type=MiLoginSqlProvider.class, method="deleteByExample")
    int deleteByExample(MiLoginExample example);

    @Insert({
        "insert into mi_login (id, member_code, ",
        "user_id, passwd, ",
        "login_on, login_num, ",
        "login_ipaddr, allow_ipaddr, ",
        "status, notes, change_on, ",
        "safe_passwd, update_on, ",
        "update_by, create_on, ",
        "create_by)",
        "values (#{id,jdbcType=BIGINT}, #{memberCode,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=VARCHAR}, #{passwd,jdbcType=VARCHAR}, ",
        "#{loginOn,jdbcType=INTEGER}, #{loginNum,jdbcType=INTEGER}, ",
        "#{loginIpaddr,jdbcType=VARCHAR}, #{allowIpaddr,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER}, #{notes,jdbcType=VARCHAR}, #{changeOn,jdbcType=INTEGER}, ",
        "#{safePasswd,jdbcType=VARCHAR}, #{updateOn,jdbcType=INTEGER}, ",
        "#{updateBy,jdbcType=VARCHAR}, #{createOn,jdbcType=INTEGER}, ",
        "#{createBy,jdbcType=VARCHAR})"
    })
    int insert(MiLogin record);

    @InsertProvider(type=MiLoginSqlProvider.class, method="insertSelective")
    int insertSelective(MiLogin record);

    @SelectProvider(type=MiLoginSqlProvider.class, method="selectByExample")
    @ConstructorArgs({
        @Arg(column="id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="member_code", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="user_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="passwd", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="login_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="login_num", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="login_ipaddr", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="allow_ipaddr", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="status", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="notes", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="change_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="safe_passwd", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="update_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="update_by", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="create_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_by", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<MiLogin> selectByExampleWithRowbounds(MiLoginExample example, RowBounds rowBounds);

    @SelectProvider(type=MiLoginSqlProvider.class, method="selectByExample")
    @ConstructorArgs({
        @Arg(column="id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="member_code", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="user_id", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="passwd", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="login_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="login_num", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="login_ipaddr", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="allow_ipaddr", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="status", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="notes", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="change_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="safe_passwd", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="update_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="update_by", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="create_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_by", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<MiLogin> selectByExample(MiLoginExample example);

    @UpdateProvider(type=MiLoginSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MiLogin record, @Param("example") MiLoginExample example);

    @UpdateProvider(type=MiLoginSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MiLogin record, @Param("example") MiLoginExample example);
}