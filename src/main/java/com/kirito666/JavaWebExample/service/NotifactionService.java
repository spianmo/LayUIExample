package com.kirito666.JavaWebExample.service;

import com.kirito666.JavaWebExample.bean.Notifaction;
import com.kirito666.JavaWebExample.dao.NotifactionDao;

import java.util.List;

/**
 * @author Finger
 */
public class NotifactionService {
    private final NotifactionDao notifactionDao = new NotifactionDao();

    public List<Notifaction> queryAll() {
        return notifactionDao.selectAll();
    }

    public int count() {
        return notifactionDao.count();
    }

    public String addNotifaction(String title, String detail) {
        int result = notifactionDao.insertNotifaction(title, detail);
        if (result > 0) {
            return "新建通知成功";
        } else {
            return "新建通知失败";
        }
    }
}
