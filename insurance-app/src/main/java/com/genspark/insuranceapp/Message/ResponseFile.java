package com.genspark.insuranceapp.Message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseFile {

    private String id;
    private String name;
    private String url;
    private String type;
    private long size;
    private int claimId;
}
