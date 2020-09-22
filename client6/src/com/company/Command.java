package com.company;

import Classes.User;

import java.io.IOException;
import java.io.Serializable;

/**
 * Шаблон, по которому описывается команда
 */

public abstract class Command implements Serializable {
    private static final long serialVersionUID = 32L;
    protected abstract void writeInfo();
    protected abstract void execute(String[] args, User user) throws IOException, ClassNotFoundException, InterruptedException;
}