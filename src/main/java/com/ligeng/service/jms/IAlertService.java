package com.ligeng.service.jms;

import com.ligeng.entity.Spittle;

/**
 * Created by dev on 16-8-17.
 */
public interface IAlertService {
    void sendSpitterAlert(Spittle spittle);
}
