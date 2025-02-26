package org.automation.dto;

public class PatchDTO {

    private String title;
    private String body;
    private int id;
    private int userId;

    public PatchDTO() {
    }

    public PatchDTO(String title, String body, int id, int userId) {
        this.title = title;
        this.body = body;
        this.id = id;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }
}
