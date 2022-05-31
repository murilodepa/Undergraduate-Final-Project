package com.tcc.websocket;

import java.io.File;

public class ClassifierFile {
    private File file;
    private String client;

    public ClassifierFile(String client, File file) {
        this.client = client;
        this.file = file;
    }

    public ClassifierFile(String client) {
        this.client = client;
    }

    public ClassifierFile() {
    }

    public ClassifierFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String getClient() {
        return client;
    }
}
