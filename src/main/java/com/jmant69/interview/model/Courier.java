package com.jmant69.interview.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Courier {
    long id;
    String name;
    boolean active;
}
