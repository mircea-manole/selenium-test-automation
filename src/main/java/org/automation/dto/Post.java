package org.automation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private final Integer userId;
    private final Integer id;
    private final String title;
    private final String body;

    public Post(Builder builder) {
        this.userId = builder.userId;
        this.id = builder.id;
        this.title = builder.title;
        this.body = builder.body;
    }

    // acesti getters nu trebuiesc declarati, noi ii folosim aici pentru a putea folosi object mapper
    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public static class Builder {
        private Integer userId = null;
        private Integer id = null;
        private String title;
        private String body;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}


