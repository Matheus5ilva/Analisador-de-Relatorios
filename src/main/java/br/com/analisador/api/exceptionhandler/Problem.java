package br.com.analisador.api.exceptionhandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problem {
    private Integer status;
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;

    private Problem(Integer status, LocalDateTime timestamp, String type, String title, String detail,
                    String userMessage) {
        this.status = status;
        this.timestamp = timestamp;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getUserMessage() {
        return userMessage;
    }
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public static ProblemBuilder builder() {
        return new ProblemBuilder();
    }

    public static class ProblemBuilder {
        private Integer status;
        private LocalDateTime timestamp;
        private String type;
        private String title;
        private String detail;
        private String userMessage;

        public ProblemBuilder status(Integer status) {
            this.status = status;
            return this;
        }
        public ProblemBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        public ProblemBuilder type(String type) {
            this.type = type;
            return this;
        }
        public ProblemBuilder title(String title) {
            this.title = title;
            return this;
        }
        public ProblemBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }
        public ProblemBuilder userMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public Problem build() {
            return new Problem(status, timestamp, type, title, detail, userMessage);
        }
    }
}