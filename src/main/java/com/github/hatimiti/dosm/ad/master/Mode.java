package com.github.hatimiti.dosm.ad.master;

public enum Mode {
    /** 登録: 登録処理を示す */
    Register("01"),
    /** 更新: 更新理を示す */
    Update("02"),
    /** 削除: 削除処理を示す */
    Delete("03"),
    /** 複製: 複製処理を示す */
    Replicate("04")
    ;

    private String code;

    Mode(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
