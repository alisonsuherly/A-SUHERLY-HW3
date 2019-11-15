package com.example.hw3;

public class Image {
    private String id;
    private String url;
    private String sub_id;
    public String created_at;
    public String original_file_name;

    public Image(String id, String url, String sub_id, String created_at, String original_file_name) {
        this.id = id;
        this.url = url;
        this.sub_id = sub_id;
        this.created_at = created_at;
        this.original_file_name = original_file_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getOriginal_file_name() {
        return original_file_name;
    }

    public void setOriginal_file_name(String original_file_name) {
        this.original_file_name = original_file_name;
    }
}
