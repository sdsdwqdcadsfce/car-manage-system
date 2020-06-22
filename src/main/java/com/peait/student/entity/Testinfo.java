package com.peait.student.entity;

public class Testinfo {
    private String id;

    private Integer subjectId;

    private String subjectCode;

    private String subjectName;

    private String testType;

    private String testDate;

    private String testAnpai;

    private String testDidian;

    private String testZuoweihao;

    private String testQingkuang;

    private String testOther;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType == null ? null : testType.trim();
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate == null ? null : testDate.trim();
    }

    public String getTestAnpai() {
        return testAnpai;
    }

    public void setTestAnpai(String testAnpai) {
        this.testAnpai = testAnpai == null ? null : testAnpai.trim();
    }

    public String getTestDidian() {
        return testDidian;
    }

    public void setTestDidian(String testDidian) {
        this.testDidian = testDidian == null ? null : testDidian.trim();
    }

    public String getTestZuoweihao() {
        return testZuoweihao;
    }

    public void setTestZuoweihao(String testZuoweihao) {
        this.testZuoweihao = testZuoweihao == null ? null : testZuoweihao.trim();
    }

    public String getTestQingkuang() {
        return testQingkuang;
    }

    public void setTestQingkuang(String testQingkuang) {
        this.testQingkuang = testQingkuang == null ? null : testQingkuang.trim();
    }

    public String getTestOther() {
        return testOther;
    }

    public void setTestOther(String testOther) {
        this.testOther = testOther == null ? null : testOther.trim();
    }
}