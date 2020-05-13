package com.gzz.mybatis.dao;

import com.gzz.mybatis.pojo.MiLogin;
import com.gzz.mybatis.pojo.MiLoginExample.Criteria;
import com.gzz.mybatis.pojo.MiLoginExample.Criterion;
import com.gzz.mybatis.pojo.MiLoginExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class MiLoginSqlProvider {

    public String countByExample(MiLoginExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("mi_login");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(MiLoginExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("mi_login");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(MiLogin record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("mi_login");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getMemberCode() != null) {
            sql.VALUES("member_code", "#{memberCode,jdbcType=BIGINT}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getPasswd() != null) {
            sql.VALUES("passwd", "#{passwd,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginOn() != null) {
            sql.VALUES("login_on", "#{loginOn,jdbcType=INTEGER}");
        }
        
        if (record.getLoginNum() != null) {
            sql.VALUES("login_num", "#{loginNum,jdbcType=INTEGER}");
        }
        
        if (record.getLoginIpaddr() != null) {
            sql.VALUES("login_ipaddr", "#{loginIpaddr,jdbcType=VARCHAR}");
        }
        
        if (record.getAllowIpaddr() != null) {
            sql.VALUES("allow_ipaddr", "#{allowIpaddr,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getNotes() != null) {
            sql.VALUES("notes", "#{notes,jdbcType=VARCHAR}");
        }
        
        if (record.getChangeOn() != null) {
            sql.VALUES("change_on", "#{changeOn,jdbcType=INTEGER}");
        }
        
        if (record.getSafePasswd() != null) {
            sql.VALUES("safe_passwd", "#{safePasswd,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateOn() != null) {
            sql.VALUES("update_on", "#{updateOn,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateBy() != null) {
            sql.VALUES("update_by", "#{updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateOn() != null) {
            sql.VALUES("create_on", "#{createOn,jdbcType=INTEGER}");
        }
        
        if (record.getCreateBy() != null) {
            sql.VALUES("create_by", "#{createBy,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(MiLoginExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("member_code");
        sql.SELECT("user_id");
        sql.SELECT("passwd");
        sql.SELECT("login_on");
        sql.SELECT("login_num");
        sql.SELECT("login_ipaddr");
        sql.SELECT("allow_ipaddr");
        sql.SELECT("status");
        sql.SELECT("notes");
        sql.SELECT("change_on");
        sql.SELECT("safe_passwd");
        sql.SELECT("update_on");
        sql.SELECT("update_by");
        sql.SELECT("create_on");
        sql.SELECT("create_by");
        sql.FROM("mi_login");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        MiLogin record = (MiLogin) parameter.get("record");
        MiLoginExample example = (MiLoginExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("mi_login");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getMemberCode() != null) {
            sql.SET("member_code = #{record.memberCode,jdbcType=BIGINT}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{record.userId,jdbcType=VARCHAR}");
        }
        
        if (record.getPasswd() != null) {
            sql.SET("passwd = #{record.passwd,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginOn() != null) {
            sql.SET("login_on = #{record.loginOn,jdbcType=INTEGER}");
        }
        
        if (record.getLoginNum() != null) {
            sql.SET("login_num = #{record.loginNum,jdbcType=INTEGER}");
        }
        
        if (record.getLoginIpaddr() != null) {
            sql.SET("login_ipaddr = #{record.loginIpaddr,jdbcType=VARCHAR}");
        }
        
        if (record.getAllowIpaddr() != null) {
            sql.SET("allow_ipaddr = #{record.allowIpaddr,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getNotes() != null) {
            sql.SET("notes = #{record.notes,jdbcType=VARCHAR}");
        }
        
        if (record.getChangeOn() != null) {
            sql.SET("change_on = #{record.changeOn,jdbcType=INTEGER}");
        }
        
        if (record.getSafePasswd() != null) {
            sql.SET("safe_passwd = #{record.safePasswd,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateOn() != null) {
            sql.SET("update_on = #{record.updateOn,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateBy() != null) {
            sql.SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateOn() != null) {
            sql.SET("create_on = #{record.createOn,jdbcType=INTEGER}");
        }
        
        if (record.getCreateBy() != null) {
            sql.SET("create_by = #{record.createBy,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("mi_login");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("member_code = #{record.memberCode,jdbcType=BIGINT}");
        sql.SET("user_id = #{record.userId,jdbcType=VARCHAR}");
        sql.SET("passwd = #{record.passwd,jdbcType=VARCHAR}");
        sql.SET("login_on = #{record.loginOn,jdbcType=INTEGER}");
        sql.SET("login_num = #{record.loginNum,jdbcType=INTEGER}");
        sql.SET("login_ipaddr = #{record.loginIpaddr,jdbcType=VARCHAR}");
        sql.SET("allow_ipaddr = #{record.allowIpaddr,jdbcType=VARCHAR}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("notes = #{record.notes,jdbcType=VARCHAR}");
        sql.SET("change_on = #{record.changeOn,jdbcType=INTEGER}");
        sql.SET("safe_passwd = #{record.safePasswd,jdbcType=VARCHAR}");
        sql.SET("update_on = #{record.updateOn,jdbcType=INTEGER}");
        sql.SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        sql.SET("create_on = #{record.createOn,jdbcType=INTEGER}");
        sql.SET("create_by = #{record.createBy,jdbcType=VARCHAR}");
        
        MiLoginExample example = (MiLoginExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    protected void applyWhere(SQL sql, MiLoginExample example, boolean includeExamplePhrase) {
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