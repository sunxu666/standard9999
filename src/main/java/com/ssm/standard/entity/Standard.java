package com.ssm.standard.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * standard
 * @author 
 */
public class Standard implements Serializable {
    /**
     * 标准id，主键，自增
     */
    private Integer id;

    /**
     * 标准号
     */
    private String stdNum;

    /**
     * 中文名字
     */
    private String zhname;

    /**
     * 版本
     */
    private String version;

    /**
     * 关键字/词
     */
    private String keys;

    /**
     * 发布日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    /**
     * 实施日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date implDate;

    /**
     * 附件路径
     */
    private String packagePath;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStdNum() {
        return stdNum;
    }

    public void setStdNum(String stdNum) {
        this.stdNum = stdNum;
    }

    public String getZhname() {
        return zhname;
    }

    public void setZhname(String zhname) {
        this.zhname = zhname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getImplDate() {
        return implDate;
    }

    public void setImplDate(Date implDate) {
        this.implDate = implDate;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
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
        Standard other = (Standard) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStdNum() == null ? other.getStdNum() == null : this.getStdNum().equals(other.getStdNum()))
            && (this.getZhname() == null ? other.getZhname() == null : this.getZhname().equals(other.getZhname()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getKeys() == null ? other.getKeys() == null : this.getKeys().equals(other.getKeys()))
            && (this.getReleaseDate() == null ? other.getReleaseDate() == null : this.getReleaseDate().equals(other.getReleaseDate()))
            && (this.getImplDate() == null ? other.getImplDate() == null : this.getImplDate().equals(other.getImplDate()))
            && (this.getPackagePath() == null ? other.getPackagePath() == null : this.getPackagePath().equals(other.getPackagePath()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStdNum() == null) ? 0 : getStdNum().hashCode());
        result = prime * result + ((getZhname() == null) ? 0 : getZhname().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getKeys() == null) ? 0 : getKeys().hashCode());
        result = prime * result + ((getReleaseDate() == null) ? 0 : getReleaseDate().hashCode());
        result = prime * result + ((getImplDate() == null) ? 0 : getImplDate().hashCode());
        result = prime * result + ((getPackagePath() == null) ? 0 : getPackagePath().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stdNum=").append(stdNum);
        sb.append(", zhname=").append(zhname);
        sb.append(", version=").append(version);
        sb.append(", keys=").append(keys);
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", implDate=").append(implDate);
        sb.append(", packagePath=").append(packagePath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}