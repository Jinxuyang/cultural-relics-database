package com.fehead.culturalrelicsdatabase.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author Verge
 * @Date 2021/6/25 10:42
 * @Version 1.0
 */
@Data
public class CeramicElement {

    private String testPoint;

    private String color;

    private Double Na2O;
    private Double MgO;
    private Double Al2O3;
    private Double SiO2;
    private Double P2O5;
    private Double SO3;
    private Double Cl;
    private Double K2O;
    private Double CaO;
    private Double TiO2;
    private Double Cr2O3;
    private Double MnO;
    private Double Fe2O3;
    private Double CoO;
    private Double NiO;
    private Double CuO;
    private Double ZnO;
    private Double Rb2O;
    private Double SrO;
    private Double SnO2;
    private Double Sb2O3;
    private Double PbO;
    private Double BaO;
    private Double Ti;
    private Double Cr;
    private Double Mn;
    private Double Co;
    private Double Ni;
    private Double Cu;
    private Double Zn;
    private Double Rb;
    private Double Sr;
    private Double Sn;
    private Double Sb;
    private Double Pb;
    private Double Ba;

    @JsonProperty("TestPoint")
    public void setTestPoint(String testPoint) {
        this.testPoint = testPoint;
    }
    @JsonProperty("Color")
    public void setColor(String color) {
        this.color = color;
    }
    @JsonProperty("Na2O")
    public void setNa2O(Double na2O) {
        Na2O = na2O;
    }
    @JsonProperty("MgO")
    public void setMgO(Double mgO) {
        MgO = mgO;
    }
    @JsonProperty("Al2O3")
    public void setAl2O3(Double al2O3) {
        Al2O3 = al2O3;
    }
    @JsonProperty("SiO2")
    public void setSiO2(Double siO2) {
        SiO2 = siO2;
    }
    @JsonProperty("P2O5")
    public void setP2O5(Double p2O5) {
        P2O5 = p2O5;
    }
    @JsonProperty("SO3")
    public void setSO3(Double SO3) {
        this.SO3 = SO3;
    }
    @JsonProperty("Cl")
    public void setCl(Double cl) {
        Cl = cl;
    }
    @JsonProperty("K2O")
    public void setK2O(Double k2O) {
        K2O = k2O;
    }
    @JsonProperty("CaO")
    public void setCaO(Double caO) {
        CaO = caO;
    }
    @JsonProperty("TiO2")
    public void setTiO2(Double tiO2) {
        TiO2 = tiO2;
    }
    @JsonProperty("Cr2O3")
    public void setCr2O3(Double cr2O3) {
        Cr2O3 = cr2O3;
    }
    @JsonProperty("MnO")
    public void setMnO(Double mnO) {
        MnO = mnO;
    }
    @JsonProperty("Fe2O3")
    public void setFe2O3(Double fe2O3) {
        Fe2O3 = fe2O3;
    }
    @JsonProperty("CoO")
    public void setCoO(Double coO) {
        CoO = coO;
    }
    @JsonProperty("NiO")
    public void setNiO(Double niO) {
        NiO = niO;
    }
    @JsonProperty("CuO")
    public void setCuO(Double cuO) {
        CuO = cuO;
    }
    @JsonProperty("ZnO")
    public void setZnO(Double znO) {
        ZnO = znO;
    }
    @JsonProperty("Rb2O")
    public void setRb2O(Double rb2O) {
        Rb2O = rb2O;
    }
    @JsonProperty("SrO")
    public void setSrO(Double srO) {
        SrO = srO;
    }
    @JsonProperty("SnO2")
    public void setSnO2(Double snO2) {
        SnO2 = snO2;
    }
    @JsonProperty("Sb2O3")
    public void setSb2O3(Double sb2O3) {
        Sb2O3 = sb2O3;
    }
    @JsonProperty("PbO")
    public void setPbO(Double pbO) {
        PbO = pbO;
    }
    @JsonProperty("BaO")
    public void setBaO(Double baO) {
        BaO = baO;
    }
    @JsonProperty("Ti")
    public void setTi(Double ti) {
        Ti = ti;
    }
    @JsonProperty("Cr")
    public void setCr(Double cr) {
        Cr = cr;
    }
    @JsonProperty("Mn")
    public void setMn(Double mn) {
        Mn = mn;
    }
    @JsonProperty("Co")
    public void setCo(Double co) {
        Co = co;
    }
    @JsonProperty("Ni")
    public void setNi(Double ni) {
        Ni = ni;
    }
    @JsonProperty("Cu")
    public void setCu(Double cu) {
        Cu = cu;
    }
    @JsonProperty("Zn")
    public void setZn(Double zn) {
        Zn = zn;
    }
    @JsonProperty("Rb")
    public void setRb(Double rb) {
        Rb = rb;
    }
    @JsonProperty("Sr")
    public void setSr(Double sr) {
        Sr = sr;
    }
    @JsonProperty("Sn")
    public void setSn(Double sn) {
        Sn = sn;
    }
    @JsonProperty("Sb")
    public void setSb(Double sb) {
        Sb = sb;
    }
    @JsonProperty("Pb")
    public void setPb(Double pb) {
        Pb = pb;
    }
    @JsonProperty("Ba")
    public void setBa(Double ba) {
        Ba = ba;
    }

    public String getTestPoint() {
        return testPoint;
    }

    public String getColor() {
        return color;
    }

    public Double getNa2O() {
        return Na2O;
    }

    public Double getMgO() {
        return MgO;
    }

    public Double getAl2O3() {
        return Al2O3;
    }

    public Double getSiO2() {
        return SiO2;
    }

    public Double getP2O5() {
        return P2O5;
    }

    public Double getSO3() {
        return SO3;
    }

    public Double getCl() {
        return Cl;
    }

    public Double getK2O() {
        return K2O;
    }

    public Double getCaO() {
        return CaO;
    }

    public Double getTiO2() {
        return TiO2;
    }

    public Double getCr2O3() {
        return Cr2O3;
    }

    public Double getMnO() {
        return MnO;
    }

    public Double getFe2O3() {
        return Fe2O3;
    }

    public Double getCoO() {
        return CoO;
    }

    public Double getNiO() {
        return NiO;
    }

    public Double getCuO() {
        return CuO;
    }

    public Double getZnO() {
        return ZnO;
    }

    public Double getRb2O() {
        return Rb2O;
    }

    public Double getSrO() {
        return SrO;
    }

    public Double getSnO2() {
        return SnO2;
    }

    public Double getSb2O3() {
        return Sb2O3;
    }

    public Double getPbO() {
        return PbO;
    }

    public Double getBaO() {
        return BaO;
    }

    public Double getTi() {
        return Ti;
    }

    public Double getCr() {
        return Cr;
    }

    public Double getMn() {
        return Mn;
    }

    public Double getCo() {
        return Co;
    }

    public Double getNi() {
        return Ni;
    }

    public Double getCu() {
        return Cu;
    }

    public Double getZn() {
        return Zn;
    }

    public Double getRb() {
        return Rb;
    }

    public Double getSr() {
        return Sr;
    }

    public Double getSn() {
        return Sn;
    }

    public Double getSb() {
        return Sb;
    }

    public Double getPb() {
        return Pb;
    }

    public Double getBa() {
        return Ba;
    }
}