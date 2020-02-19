package com.gzz.mybatis.dao;

import com.gzz.mybatis.pojo.MiMember;
import com.gzz.mybatis.pojo.MiMemberExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

@Mapper
public interface MiMemberMapper {
    @SelectProvider(type=MiMemberSqlProvider.class, method="countByExample")
    long countByExample(MiMemberExample example);

    @DeleteProvider(type=MiMemberSqlProvider.class, method="deleteByExample")
    int deleteByExample(MiMemberExample example);

    @Delete({
        "delete from mi_member",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into mi_member (id, member_no, ",
        "name, nick_name, ",
        "cert_type, cert_no, ",
        "birthday, sex, ",
        "mobile, email, address, ",
        "rank, lvl, type, ",
        "join_on, status, create_on, ",
        "create_by, update_on, ",
        "update_by, source, ",
        "notes, avatar)",
        "values (#{id,jdbcType=BIGINT}, #{memberNo,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{nickName,jdbcType=VARCHAR}, ",
        "#{certType,jdbcType=VARCHAR}, #{certNo,jdbcType=VARCHAR}, ",
        "#{birthday,jdbcType=TIMESTAMP}, #{sex,jdbcType=VARCHAR}, ",
        "#{mobile,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{rank,jdbcType=INTEGER}, #{lvl,jdbcType=INTEGER}, #{type,jdbcType=VARCHAR}, ",
        "#{joinOn,jdbcType=BIGINT}, #{status,jdbcType=INTEGER}, #{createOn,jdbcType=INTEGER}, ",
        "#{createBy,jdbcType=VARCHAR}, #{updateOn,jdbcType=INTEGER}, ",
        "#{updateBy,jdbcType=VARCHAR}, #{source,jdbcType=VARCHAR}, ",
        "#{notes,jdbcType=VARCHAR}, #{avatar,jdbcType=VARCHAR})"
    })
    int insert(MiMember record);

    @InsertProvider(type=MiMemberSqlProvider.class, method="insertSelective")
    int insertSelective(MiMember record);

    @SelectProvider(type=MiMemberSqlProvider.class, method="selectByExample")
    @ConstructorArgs({
        @Arg(column="id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="member_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="nick_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="cert_type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="cert_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="birthday", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="sex", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="mobile", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="address", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="rank", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="lvl", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="join_on", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="status", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_by", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="update_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="update_by", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="source", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="notes", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="avatar", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<MiMember> selectByExampleWithRowbounds(MiMemberExample example, RowBounds rowBounds);

    @SelectProvider(type=MiMemberSqlProvider.class, method="selectByExample")
    @ConstructorArgs({
            @Arg(column="id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
            @Arg(column="member_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="nick_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="cert_type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="cert_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="birthday", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="sex", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="mobile", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="address", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="rank", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="lvl", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="join_on", javaType=Long.class, jdbcType=JdbcType.BIGINT),
            @Arg(column="status", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="create_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="create_by", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="update_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="update_by", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="source", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="notes", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="avatar", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<MiMember> selectByExamples(MiMemberExample example);

    @SelectProvider(type=MiMemberSqlProvider.class, method="selectByExample")
    @ConstructorArgs({
        @Arg(column="id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="member_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="nick_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="cert_type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="cert_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="birthday", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="sex", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="mobile", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="address", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="rank", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="lvl", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="join_on", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="status", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_by", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="update_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="update_by", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="source", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="notes", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="avatar", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    MiMember selectByExample(MiMemberExample example);

    @Select({
        "select",
        "id, member_no, name, nick_name, cert_type, cert_no, birthday, sex, mobile, email, ",
        "address, rank, lvl, type, join_on, status, create_on, create_by, update_on, ",
        "update_by, source, notes, avatar",
        "from mi_member",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="member_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="nick_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="cert_type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="cert_no", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="birthday", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="sex", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="mobile", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="address", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="rank", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="lvl", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="type", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="join_on", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="status", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_by", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="update_on", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="update_by", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="source", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="notes", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="avatar", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    MiMember selectByPrimaryKey(Long id);

    @UpdateProvider(type=MiMemberSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MiMember record, @Param("example") MiMemberExample example);

    @UpdateProvider(type=MiMemberSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MiMember record, @Param("example") MiMemberExample example);

    @UpdateProvider(type=MiMemberSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MiMember record);

    @Update({
        "update mi_member",
        "set member_no = #{memberNo,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "nick_name = #{nickName,jdbcType=VARCHAR},",
          "cert_type = #{certType,jdbcType=VARCHAR},",
          "cert_no = #{certNo,jdbcType=VARCHAR},",
          "birthday = #{birthday,jdbcType=TIMESTAMP},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "rank = #{rank,jdbcType=INTEGER},",
          "lvl = #{lvl,jdbcType=INTEGER},",
          "type = #{type,jdbcType=VARCHAR},",
          "join_on = #{joinOn,jdbcType=BIGINT},",
          "status = #{status,jdbcType=INTEGER},",
          "create_on = #{createOn,jdbcType=INTEGER},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "update_on = #{updateOn,jdbcType=INTEGER},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "source = #{source,jdbcType=VARCHAR},",
          "notes = #{notes,jdbcType=VARCHAR},",
          "avatar = #{avatar,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MiMember record);
}