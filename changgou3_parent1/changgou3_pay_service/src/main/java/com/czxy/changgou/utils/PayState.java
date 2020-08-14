package com.czxy.changgou.utils;

import lombok.Getter;

/**
 * @author zhandehuang@itcast.cn
 * //1.枚举
 * PayState.A
 * PayState.B
 * //2.等效代码
 * private static final PayState A = new PayState(0,"");
 * A.getIndex()
 * //3 实例
 * A(0,"哈哈"), B(1,"嘿嘿");
 * PayState(int index,String value){
 * this.index = index;
 * this.value = value;
 * }
 * private int index;
 * private String  value;
 **/
@Getter
public enum PayState {
    NOT_PAY(0, "未支付"), SUCCESS(1, "支付成功"), CLOSED(2, "已关闭"), PAY_ERROR(3, "支付失败");

    PayState(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;           //自定义编码
    private String desc;        //描述信息
}
