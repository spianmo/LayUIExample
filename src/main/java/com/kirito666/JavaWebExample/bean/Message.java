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
public class Message {
    int id;
    int cardId;
    String detail;
    Date publicDate;
}
