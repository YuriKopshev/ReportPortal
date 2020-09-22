package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public  class ClientCardInfo {
    private String city;
    private String name;
    private String telNumber;
}
