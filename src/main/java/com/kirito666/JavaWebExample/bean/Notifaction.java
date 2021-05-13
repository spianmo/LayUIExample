package com.kirito666.JavaWebExample.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Finger
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notifaction {
    int id;
    String title;
    String detail;
    Date publishDate;
}
