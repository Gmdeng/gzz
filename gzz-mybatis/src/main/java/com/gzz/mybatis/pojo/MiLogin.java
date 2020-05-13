package com.gzz.mybatis.pojo;

public class MiLogin {
    private Long id;

    private Long memberCode;

    private String userId;

    private String passwd;

    private Integer loginOn;

    private Integer loginNum;

    private String loginIpaddr;

    private String allowIpaddr;

    private Integer status;

    private String notes;

    private Integer changeOn;

    private String safePasswd;

    private Integer updateOn;

    private String updateBy;

    private Integer createOn;

    private String createBy;

    public MiLogin(Long id, Long memberCode, String userId, String passwd, Integer loginOn, Integer loginNum, String loginIpaddr, String allowIpaddr, Integer status, String notes, Integer changeOn, String safePasswd, Integer updateOn, String updateBy, Integer createOn, String createBy) {
        this.id = id;
        this.memberCode = memberCode;
        this.userId = userId;
        this.passwd = passwd;
        this.loginOn = loginOn;
        this.loginNum = loginNum;
        this.loginIpaddr = loginIpaddr;
        this.allowIpaddr = allowIpaddr;
        this.status = status;
        this.notes = notes;
        this.changeOn = changeOn;
        this.safePasswd = safePasswd;
        this.updateOn = updateOn;
        this.updateBy = updateBy;
        this.createOn = createOn;
        this.createBy = createBy;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberCode() {
        return memberCode;
    }

    public String getUserId() {
        return userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public Integer getLoginOn() {
        return loginOn;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public String getLoginIpaddr() {
        return loginIpaddr;
    }

    public String getAllowIpaddr() {
        return allowIpaddr;
    }

    public Integer getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public Integer getChangeOn() {
        return changeOn;
    }

    public String getSafePasswd() {
        return safePasswd;
    }

    public Integer getUpdateOn() {
        return updateOn;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public Integer getCreateOn() {
        return createOn;
    }

    public String getCreateBy() {
        return createBy;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MiLogin other = (MiLogin) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberCode() == null ? other.getMemberCode() == null : this.getMemberCode().equals(other.getMemberCode()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPasswd() == null ? other.getPasswd() == null : this.getPasswd().equals(other.getPasswd()))
            && (this.getLoginOn() == null ? other.getLoginOn() == null : this.getLoginOn().equals(other.getLoginOn()))
            && (this.getLoginNum() == null ? other.getLoginNum() == null : this.getLoginNum().equals(other.getLoginNum()))
            && (this.getLoginIpaddr() == null ? other.getLoginIpaddr() == null : this.getLoginIpaddr().equals(other.getLoginIpaddr()))
            && (this.getAllowIpaddr() == null ? other.getAllowIpaddr() == null : this.getAllowIpaddr().equals(other.getAllowIpaddr()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getNotes() == null ? other.getNotes() == null : this.getNotes().equals(other.getNotes()))
            && (this.getChangeOn() == null ? other.getChangeOn() == null : this.getChangeOn().equals(other.getChangeOn()))
            && (this.getSafePasswd() == null ? other.getSafePasswd() == null : this.getSafePasswd().equals(other.getSafePasswd()))
            && (this.getUpdateOn() == null ? other.getUpdateOn() == null : this.getUpdateOn().equals(other.getUpdateOn()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getCreateOn() == null ? other.getCreateOn() == null : this.getCreateOn().equals(other.getCreateOn()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberCode() == null) ? 0 : getMemberCode().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPasswd() == null) ? 0 : getPasswd().hashCode());
        result = prime * result + ((getLoginOn() == null) ? 0 : getLoginOn().hashCode());
        result = prime * result + ((getLoginNum() == null) ? 0 : getLoginNum().hashCode());
        result = prime * result + ((getLoginIpaddr() == null) ? 0 : getLoginIpaddr().hashCode());
        result = prime * result + ((getAllowIpaddr() == null) ? 0 : getAllowIpaddr().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getNotes() == null) ? 0 : getNotes().hashCode());
        result = prime * result + ((getChangeOn() == null) ? 0 : getChangeOn().hashCode());
        result = prime * result + ((getSafePasswd() == null) ? 0 : getSafePasswd().hashCode());
        result = prime * result + ((getUpdateOn() == null) ? 0 : getUpdateOn().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getCreateOn() == null) ? 0 : getCreateOn().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        return result;
    }
}