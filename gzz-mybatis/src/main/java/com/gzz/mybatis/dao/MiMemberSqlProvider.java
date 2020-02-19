package com.gzz.mybatis.dao;

import com.gzz.mybatis.pojo.MiMember;
import com.gzz.mybatis.pojo.MiMemberExample;
import com.gzz.mybatis.pojo.MiMemberExample.Criteria;
import com.gzz.mybatis.pojo.MiMemberExample.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class MiMemberSqlProvider {

    public String countByExample(MiMemberExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("mi_member");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(MiMemberExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("mi_member");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(MiMember record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("mi_member");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getMemberNo() != null) {
            sql.VALUES("member_no", "#{memberNo,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getNickName() != null) {
            sql.VALUES("nick_name", "#{nickName,jdbcType=VARCHAR}");
        }
        
        if (record.getCertType() != null) {
            sql.VALUES("cert_type", "#{certType,jdbcType=VARCHAR}");
        }
        
        if (record.getCertNo() != null) {
            sql.VALUES("cert_no", "#{certNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBirthday() != null) {
            sql.VALUES("birthday", "#{birthday,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSex() != null) {
            sql.VALUES("sex", "#{sex,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.VALUES("mobile", "#{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.VALUES("email", "#{email,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getRank() != null) {
            sql.VALUES("rank", "#{rank,jdbcType=INTEGER}");
        }
        
        if (record.getLvl() != null) {
            sql.VALUES("lvl", "#{lvl,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getJoinOn() != null) {
            sql.VALUES("join_on", "#{joinOn,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateOn() != null) {
            sql.VALUES("create_on", "#{createOn,jdbcType=INTEGER}");
        }
        
        if (record.getCreateBy() != null) {
            sql.VALUES("create_by", "#{createBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateOn() != null) {
            sql.VALUES("update_on", "#{updateOn,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateBy() != null) {
            sql.VALUES("update_by", "#{updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getSource() != null) {
            sql.VALUES("source", "#{source,jdbcType=VARCHAR}");
        }
        
        if (record.getNotes() != null) {
            sql.VALUES("notes", "#{notes,jdbcType=VARCHAR}");
        }
        
        if (record.getAvatar() != null) {
            sql.VALUES("avatar", "#{avatar,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(MiMemberExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("member_no");
        sql.SELECT("name");
        sql.SELECT("nick_name");
        sql.SELECT("cert_type");
        sql.SELECT("cert_no");
        sql.SELECT("birthday");
        sql.SELECT("sex");
        sql.SELECT("mobile");
        sql.SELECT("email");
        sql.SELECT("address");
        sql.SELECT("rank");
        sql.SELECT("lvl");
        sql.SELECT("type");
        sql.SELECT("join_on");
        sql.SELECT("status");
        sql.SELECT("create_on");
        sql.SELECT("create_by");
        sql.SELECT("update_on");
        sql.SELECT("update_by");
        sql.SELECT("source");
        sql.SELECT("notes");
        sql.SELECT("avatar");
        sql.FROM("mi_member");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        if (example != null && example.getStartRow() != null) {
            sql.LIMIT(example.getStartRow()).OFFSET(example.getPageSize());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        MiMember record = (MiMember) parameter.get("record");
        MiMemberExample example = (MiMemberExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("mi_member");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getMemberNo() != null) {
            sql.SET("member_no = #{record.memberNo,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getNickName() != null) {
            sql.SET("nick_name = #{record.nickName,jdbcType=VARCHAR}");
        }
        
        if (record.getCertType() != null) {
            sql.SET("cert_type = #{record.certType,jdbcType=VARCHAR}");
        }
        
        if (record.getCertNo() != null) {
            sql.SET("cert_no = #{record.certNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBirthday() != null) {
            sql.SET("birthday = #{record.birthday,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSex() != null) {
            sql.SET("sex = #{record.sex,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.SET("mobile = #{record.mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.SET("email = #{record.email,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{record.address,jdbcType=VARCHAR}");
        }
        
        if (record.getRank() != null) {
            sql.SET("rank = #{record.rank,jdbcType=INTEGER}");
        }
        
        if (record.getLvl() != null) {
            sql.SET("lvl = #{record.lvl,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{record.type,jdbcType=VARCHAR}");
        }
        
        if (record.getJoinOn() != null) {
            sql.SET("join_on = #{record.joinOn,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateOn() != null) {
            sql.SET("create_on = #{record.createOn,jdbcType=INTEGER}");
        }
        
        if (record.getCreateBy() != null) {
            sql.SET("create_by = #{record.createBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateOn() != null) {
            sql.SET("update_on = #{record.updateOn,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateBy() != null) {
            sql.SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getSource() != null) {
            sql.SET("source = #{record.source,jdbcType=VARCHAR}");
        }
        
        if (record.getNotes() != null) {
            sql.SET("notes = #{record.notes,jdbcType=VARCHAR}");
        }
        
        if (record.getAvatar() != null) {
            sql.SET("avatar = #{record.avatar,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("mi_member");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("member_no = #{record.memberNo,jdbcType=VARCHAR}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("nick_name = #{record.nickName,jdbcType=VARCHAR}");
        sql.SET("cert_type = #{record.certType,jdbcType=VARCHAR}");
        sql.SET("cert_no = #{record.certNo,jdbcType=VARCHAR}");
        sql.SET("birthday = #{record.birthday,jdbcType=TIMESTAMP}");
        sql.SET("sex = #{record.sex,jdbcType=VARCHAR}");
        sql.SET("mobile = #{record.mobile,jdbcType=VARCHAR}");
        sql.SET("email = #{record.email,jdbcType=VARCHAR}");
        sql.SET("address = #{record.address,jdbcType=VARCHAR}");
        sql.SET("rank = #{record.rank,jdbcType=INTEGER}");
        sql.SET("lvl = #{record.lvl,jdbcType=INTEGER}");
        sql.SET("type = #{record.type,jdbcType=VARCHAR}");
        sql.SET("join_on = #{record.joinOn,jdbcType=BIGINT}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("create_on = #{record.createOn,jdbcType=INTEGER}");
        sql.SET("create_by = #{record.createBy,jdbcType=VARCHAR}");
        sql.SET("update_on = #{record.updateOn,jdbcType=INTEGER}");
        sql.SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        sql.SET("source = #{record.source,jdbcType=VARCHAR}");
        sql.SET("notes = #{record.notes,jdbcType=VARCHAR}");
        sql.SET("avatar = #{record.avatar,jdbcType=VARCHAR}");
        
        MiMemberExample example = (MiMemberExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(MiMember record) {
        SQL sql = new SQL();
        sql.UPDATE("mi_member");
        
        if (record.getMemberNo() != null) {
            sql.SET("member_no = #{memberNo,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getNickName() != null) {
            sql.SET("nick_name = #{nickName,jdbcType=VARCHAR}");
        }
        
        if (record.getCertType() != null) {
            sql.SET("cert_type = #{certType,jdbcType=VARCHAR}");
        }
        
        if (record.getCertNo() != null) {
            sql.SET("cert_no = #{certNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBirthday() != null) {
            sql.SET("birthday = #{birthday,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSex() != null) {
            sql.SET("sex = #{sex,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.SET("mobile = #{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.SET("email = #{email,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getRank() != null) {
            sql.SET("rank = #{rank,jdbcType=INTEGER}");
        }
        
        if (record.getLvl() != null) {
            sql.SET("lvl = #{lvl,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getJoinOn() != null) {
            sql.SET("join_on = #{joinOn,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateOn() != null) {
            sql.SET("create_on = #{createOn,jdbcType=INTEGER}");
        }
        
        if (record.getCreateBy() != null) {
            sql.SET("create_by = #{createBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateOn() != null) {
            sql.SET("update_on = #{updateOn,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateBy() != null) {
            sql.SET("update_by = #{updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getSource() != null) {
            sql.SET("source = #{source,jdbcType=VARCHAR}");
        }
        
        if (record.getNotes() != null) {
            sql.SET("notes = #{notes,jdbcType=VARCHAR}");
        }
        
        if (record.getAvatar() != null) {
            sql.SET("avatar = #{avatar,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, MiMemberExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}