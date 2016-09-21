package com.ligeng.test.introduction;

import lombok.Data;

/**
 * Created by dev on 16-4-22.
 */

@Data
public class Tester implements ITester {
    boolean isBusy;

    @Override
    public boolean isBusyAsTester() {
        return isBusy;
    }

    @Override
    public void testSoftWare() {
        System.out.println("确保程序正确");
    }
}
