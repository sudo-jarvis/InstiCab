package com.InstiCab.models;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private Long id;
    private String name;
    private List<User> users = new ArrayList<>();
}
