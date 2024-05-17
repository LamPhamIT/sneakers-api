package com.example.sneaker.framework.message.model;

import lombok.Data;

@Data
public class Message {

    private Keys key;

    private String content;

    public enum Keys {
        I0001,
        I0002,
        I0003,
        I0004,
        I0005,
        I0006,
        I0007,
        I9999,
        I9998,
        E0001,
        E0002,
        E0003,
        E0004,
        E0005,
        E0006,
        E0007,
        E0008,
        E0009,
        E0010,
        E0011,
        E0012,
        E0013,
        E0014,
        E0015,
        E0016,
        E0017,
        E0018,
        E0019,
        E0020,
        E0021,
        E0022,
        E0023,
        E0024,
        E0025,
        E0026,
        E0027,
        E0028,
        E0029,
        E0030,
        E0031,
        E0032,
        E0033,
        E0101,
        E0034,
        E0035,

        E0036,
        E0037,
        E0038,
        E0039,
        E0040,
        E0041,
        E0042,
        E0043,
        E0044,
    }

    @Override
    public String toString(){
        return this.content;
    }
}
