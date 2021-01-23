package top.fjy8018.db.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据库常量
 * @author F嘉阳
 * @date 2021-01-22 22:20
 */
@Getter
@AllArgsConstructor
public enum  DatabaseEnums {

    /**
     * 序列库
     */
    SEQUENCE("basic_sequence"),

    CONSOLE("efp_console"),

    CTR("efp_ctr"),

    CUS("efp_cus"),

    DAYBAT("efp_daybat"),

    EDOC("efp_edoc"),

    FLOW("efp_flow"),

    LIMIT("efp_limit"),

    LOAN("efp_loan"),

    NLS("efp_nls"),

    PBOC("efp_pboc"),

    REPORT("efp_report"),

    RISK("efp_risk"),

    RULE("efp_rule"),
    ;

    private String name;

}
