package com.gzz.mybatis.pojo;

import java.util.ArrayList;
import java.util.List;

public class MiLoginExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    public MiLoginExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMemberCodeIsNull() {
            addCriterion("member_code is null");
            return (Criteria) this;
        }

        public Criteria andMemberCodeIsNotNull() {
            addCriterion("member_code is not null");
            return (Criteria) this;
        }

        public Criteria andMemberCodeEqualTo(Long value) {
            addCriterion("member_code =", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeNotEqualTo(Long value) {
            addCriterion("member_code <>", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeGreaterThan(Long value) {
            addCriterion("member_code >", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("member_code >=", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeLessThan(Long value) {
            addCriterion("member_code <", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeLessThanOrEqualTo(Long value) {
            addCriterion("member_code <=", value, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeIn(List<Long> values) {
            addCriterion("member_code in", values, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeNotIn(List<Long> values) {
            addCriterion("member_code not in", values, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeBetween(Long value1, Long value2) {
            addCriterion("member_code between", value1, value2, "memberCode");
            return (Criteria) this;
        }

        public Criteria andMemberCodeNotBetween(Long value1, Long value2) {
            addCriterion("member_code not between", value1, value2, "memberCode");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNull() {
            addCriterion("passwd is null");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNotNull() {
            addCriterion("passwd is not null");
            return (Criteria) this;
        }

        public Criteria andPasswdEqualTo(String value) {
            addCriterion("passwd =", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotEqualTo(String value) {
            addCriterion("passwd <>", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThan(String value) {
            addCriterion("passwd >", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("passwd >=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThan(String value) {
            addCriterion("passwd <", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThanOrEqualTo(String value) {
            addCriterion("passwd <=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLike(String value) {
            addCriterion("passwd like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotLike(String value) {
            addCriterion("passwd not like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdIn(List<String> values) {
            addCriterion("passwd in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotIn(List<String> values) {
            addCriterion("passwd not in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdBetween(String value1, String value2) {
            addCriterion("passwd between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotBetween(String value1, String value2) {
            addCriterion("passwd not between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andLoginOnIsNull() {
            addCriterion("login_on is null");
            return (Criteria) this;
        }

        public Criteria andLoginOnIsNotNull() {
            addCriterion("login_on is not null");
            return (Criteria) this;
        }

        public Criteria andLoginOnEqualTo(Integer value) {
            addCriterion("login_on =", value, "loginOn");
            return (Criteria) this;
        }

        public Criteria andLoginOnNotEqualTo(Integer value) {
            addCriterion("login_on <>", value, "loginOn");
            return (Criteria) this;
        }

        public Criteria andLoginOnGreaterThan(Integer value) {
            addCriterion("login_on >", value, "loginOn");
            return (Criteria) this;
        }

        public Criteria andLoginOnGreaterThanOrEqualTo(Integer value) {
            addCriterion("login_on >=", value, "loginOn");
            return (Criteria) this;
        }

        public Criteria andLoginOnLessThan(Integer value) {
            addCriterion("login_on <", value, "loginOn");
            return (Criteria) this;
        }

        public Criteria andLoginOnLessThanOrEqualTo(Integer value) {
            addCriterion("login_on <=", value, "loginOn");
            return (Criteria) this;
        }

        public Criteria andLoginOnIn(List<Integer> values) {
            addCriterion("login_on in", values, "loginOn");
            return (Criteria) this;
        }

        public Criteria andLoginOnNotIn(List<Integer> values) {
            addCriterion("login_on not in", values, "loginOn");
            return (Criteria) this;
        }

        public Criteria andLoginOnBetween(Integer value1, Integer value2) {
            addCriterion("login_on between", value1, value2, "loginOn");
            return (Criteria) this;
        }

        public Criteria andLoginOnNotBetween(Integer value1, Integer value2) {
            addCriterion("login_on not between", value1, value2, "loginOn");
            return (Criteria) this;
        }

        public Criteria andLoginNumIsNull() {
            addCriterion("login_num is null");
            return (Criteria) this;
        }

        public Criteria andLoginNumIsNotNull() {
            addCriterion("login_num is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNumEqualTo(Integer value) {
            addCriterion("login_num =", value, "loginNum");
            return (Criteria) this;
        }

        public Criteria andLoginNumNotEqualTo(Integer value) {
            addCriterion("login_num <>", value, "loginNum");
            return (Criteria) this;
        }

        public Criteria andLoginNumGreaterThan(Integer value) {
            addCriterion("login_num >", value, "loginNum");
            return (Criteria) this;
        }

        public Criteria andLoginNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("login_num >=", value, "loginNum");
            return (Criteria) this;
        }

        public Criteria andLoginNumLessThan(Integer value) {
            addCriterion("login_num <", value, "loginNum");
            return (Criteria) this;
        }

        public Criteria andLoginNumLessThanOrEqualTo(Integer value) {
            addCriterion("login_num <=", value, "loginNum");
            return (Criteria) this;
        }

        public Criteria andLoginNumIn(List<Integer> values) {
            addCriterion("login_num in", values, "loginNum");
            return (Criteria) this;
        }

        public Criteria andLoginNumNotIn(List<Integer> values) {
            addCriterion("login_num not in", values, "loginNum");
            return (Criteria) this;
        }

        public Criteria andLoginNumBetween(Integer value1, Integer value2) {
            addCriterion("login_num between", value1, value2, "loginNum");
            return (Criteria) this;
        }

        public Criteria andLoginNumNotBetween(Integer value1, Integer value2) {
            addCriterion("login_num not between", value1, value2, "loginNum");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrIsNull() {
            addCriterion("login_ipaddr is null");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrIsNotNull() {
            addCriterion("login_ipaddr is not null");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrEqualTo(String value) {
            addCriterion("login_ipaddr =", value, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrNotEqualTo(String value) {
            addCriterion("login_ipaddr <>", value, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrGreaterThan(String value) {
            addCriterion("login_ipaddr >", value, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrGreaterThanOrEqualTo(String value) {
            addCriterion("login_ipaddr >=", value, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrLessThan(String value) {
            addCriterion("login_ipaddr <", value, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrLessThanOrEqualTo(String value) {
            addCriterion("login_ipaddr <=", value, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrLike(String value) {
            addCriterion("login_ipaddr like", value, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrNotLike(String value) {
            addCriterion("login_ipaddr not like", value, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrIn(List<String> values) {
            addCriterion("login_ipaddr in", values, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrNotIn(List<String> values) {
            addCriterion("login_ipaddr not in", values, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrBetween(String value1, String value2) {
            addCriterion("login_ipaddr between", value1, value2, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andLoginIpaddrNotBetween(String value1, String value2) {
            addCriterion("login_ipaddr not between", value1, value2, "loginIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrIsNull() {
            addCriterion("allow_ipaddr is null");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrIsNotNull() {
            addCriterion("allow_ipaddr is not null");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrEqualTo(String value) {
            addCriterion("allow_ipaddr =", value, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrNotEqualTo(String value) {
            addCriterion("allow_ipaddr <>", value, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrGreaterThan(String value) {
            addCriterion("allow_ipaddr >", value, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrGreaterThanOrEqualTo(String value) {
            addCriterion("allow_ipaddr >=", value, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrLessThan(String value) {
            addCriterion("allow_ipaddr <", value, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrLessThanOrEqualTo(String value) {
            addCriterion("allow_ipaddr <=", value, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrLike(String value) {
            addCriterion("allow_ipaddr like", value, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrNotLike(String value) {
            addCriterion("allow_ipaddr not like", value, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrIn(List<String> values) {
            addCriterion("allow_ipaddr in", values, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrNotIn(List<String> values) {
            addCriterion("allow_ipaddr not in", values, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrBetween(String value1, String value2) {
            addCriterion("allow_ipaddr between", value1, value2, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andAllowIpaddrNotBetween(String value1, String value2) {
            addCriterion("allow_ipaddr not between", value1, value2, "allowIpaddr");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andNotesIsNull() {
            addCriterion("notes is null");
            return (Criteria) this;
        }

        public Criteria andNotesIsNotNull() {
            addCriterion("notes is not null");
            return (Criteria) this;
        }

        public Criteria andNotesEqualTo(String value) {
            addCriterion("notes =", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotEqualTo(String value) {
            addCriterion("notes <>", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesGreaterThan(String value) {
            addCriterion("notes >", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesGreaterThanOrEqualTo(String value) {
            addCriterion("notes >=", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLessThan(String value) {
            addCriterion("notes <", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLessThanOrEqualTo(String value) {
            addCriterion("notes <=", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLike(String value) {
            addCriterion("notes like", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotLike(String value) {
            addCriterion("notes not like", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesIn(List<String> values) {
            addCriterion("notes in", values, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotIn(List<String> values) {
            addCriterion("notes not in", values, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesBetween(String value1, String value2) {
            addCriterion("notes between", value1, value2, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotBetween(String value1, String value2) {
            addCriterion("notes not between", value1, value2, "notes");
            return (Criteria) this;
        }

        public Criteria andChangeOnIsNull() {
            addCriterion("change_on is null");
            return (Criteria) this;
        }

        public Criteria andChangeOnIsNotNull() {
            addCriterion("change_on is not null");
            return (Criteria) this;
        }

        public Criteria andChangeOnEqualTo(Integer value) {
            addCriterion("change_on =", value, "changeOn");
            return (Criteria) this;
        }

        public Criteria andChangeOnNotEqualTo(Integer value) {
            addCriterion("change_on <>", value, "changeOn");
            return (Criteria) this;
        }

        public Criteria andChangeOnGreaterThan(Integer value) {
            addCriterion("change_on >", value, "changeOn");
            return (Criteria) this;
        }

        public Criteria andChangeOnGreaterThanOrEqualTo(Integer value) {
            addCriterion("change_on >=", value, "changeOn");
            return (Criteria) this;
        }

        public Criteria andChangeOnLessThan(Integer value) {
            addCriterion("change_on <", value, "changeOn");
            return (Criteria) this;
        }

        public Criteria andChangeOnLessThanOrEqualTo(Integer value) {
            addCriterion("change_on <=", value, "changeOn");
            return (Criteria) this;
        }

        public Criteria andChangeOnIn(List<Integer> values) {
            addCriterion("change_on in", values, "changeOn");
            return (Criteria) this;
        }

        public Criteria andChangeOnNotIn(List<Integer> values) {
            addCriterion("change_on not in", values, "changeOn");
            return (Criteria) this;
        }

        public Criteria andChangeOnBetween(Integer value1, Integer value2) {
            addCriterion("change_on between", value1, value2, "changeOn");
            return (Criteria) this;
        }

        public Criteria andChangeOnNotBetween(Integer value1, Integer value2) {
            addCriterion("change_on not between", value1, value2, "changeOn");
            return (Criteria) this;
        }

        public Criteria andSafePasswdIsNull() {
            addCriterion("safe_passwd is null");
            return (Criteria) this;
        }

        public Criteria andSafePasswdIsNotNull() {
            addCriterion("safe_passwd is not null");
            return (Criteria) this;
        }

        public Criteria andSafePasswdEqualTo(String value) {
            addCriterion("safe_passwd =", value, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andSafePasswdNotEqualTo(String value) {
            addCriterion("safe_passwd <>", value, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andSafePasswdGreaterThan(String value) {
            addCriterion("safe_passwd >", value, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andSafePasswdGreaterThanOrEqualTo(String value) {
            addCriterion("safe_passwd >=", value, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andSafePasswdLessThan(String value) {
            addCriterion("safe_passwd <", value, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andSafePasswdLessThanOrEqualTo(String value) {
            addCriterion("safe_passwd <=", value, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andSafePasswdLike(String value) {
            addCriterion("safe_passwd like", value, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andSafePasswdNotLike(String value) {
            addCriterion("safe_passwd not like", value, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andSafePasswdIn(List<String> values) {
            addCriterion("safe_passwd in", values, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andSafePasswdNotIn(List<String> values) {
            addCriterion("safe_passwd not in", values, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andSafePasswdBetween(String value1, String value2) {
            addCriterion("safe_passwd between", value1, value2, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andSafePasswdNotBetween(String value1, String value2) {
            addCriterion("safe_passwd not between", value1, value2, "safePasswd");
            return (Criteria) this;
        }

        public Criteria andUpdateOnIsNull() {
            addCriterion("update_on is null");
            return (Criteria) this;
        }

        public Criteria andUpdateOnIsNotNull() {
            addCriterion("update_on is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateOnEqualTo(Integer value) {
            addCriterion("update_on =", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnNotEqualTo(Integer value) {
            addCriterion("update_on <>", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnGreaterThan(Integer value) {
            addCriterion("update_on >", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_on >=", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnLessThan(Integer value) {
            addCriterion("update_on <", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnLessThanOrEqualTo(Integer value) {
            addCriterion("update_on <=", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnIn(List<Integer> values) {
            addCriterion("update_on in", values, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnNotIn(List<Integer> values) {
            addCriterion("update_on not in", values, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnBetween(Integer value1, Integer value2) {
            addCriterion("update_on between", value1, value2, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnNotBetween(Integer value1, Integer value2) {
            addCriterion("update_on not between", value1, value2, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andCreateOnIsNull() {
            addCriterion("create_on is null");
            return (Criteria) this;
        }

        public Criteria andCreateOnIsNotNull() {
            addCriterion("create_on is not null");
            return (Criteria) this;
        }

        public Criteria andCreateOnEqualTo(Integer value) {
            addCriterion("create_on =", value, "createOn");
            return (Criteria) this;
        }

        public Criteria andCreateOnNotEqualTo(Integer value) {
            addCriterion("create_on <>", value, "createOn");
            return (Criteria) this;
        }

        public Criteria andCreateOnGreaterThan(Integer value) {
            addCriterion("create_on >", value, "createOn");
            return (Criteria) this;
        }

        public Criteria andCreateOnGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_on >=", value, "createOn");
            return (Criteria) this;
        }

        public Criteria andCreateOnLessThan(Integer value) {
            addCriterion("create_on <", value, "createOn");
            return (Criteria) this;
        }

        public Criteria andCreateOnLessThanOrEqualTo(Integer value) {
            addCriterion("create_on <=", value, "createOn");
            return (Criteria) this;
        }

        public Criteria andCreateOnIn(List<Integer> values) {
            addCriterion("create_on in", values, "createOn");
            return (Criteria) this;
        }

        public Criteria andCreateOnNotIn(List<Integer> values) {
            addCriterion("create_on not in", values, "createOn");
            return (Criteria) this;
        }

        public Criteria andCreateOnBetween(Integer value1, Integer value2) {
            addCriterion("create_on between", value1, value2, "createOn");
            return (Criteria) this;
        }

        public Criteria andCreateOnNotBetween(Integer value1, Integer value2) {
            addCriterion("create_on not between", value1, value2, "createOn");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}