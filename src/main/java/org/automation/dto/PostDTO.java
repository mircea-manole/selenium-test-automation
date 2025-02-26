package org.automation.dto;

public class PostDTO {

    private String title;
    private String body;
    private int id;

    public PostDTO() {
    }

    public PostDTO(String title, String body, int id) {
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getId() {
        return id;
    }
}

