package com.mes_jyproject.master.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ApiProblemResponse extends ProblemDetail {

    private ApiProblemResponse(Builder<?> b) {
        super(b.status.value());
        if (b.title != null) setTitle(b.title);
        if (b.detail != null) setDetail(b.detail);
        if (b.type != null) setType(URI.create(b.type));
        if (b.instance != null) setInstance(URI.create(b.instance));
        b.properties.forEach(this::setProperty);
    }

    public static Builder<?> builder(HttpStatus status) {
        return new Builder<>(status);
    }

    public static final class Builder<T> {
        private final HttpStatus status;
        private String title;
        private String detail;
        private String type;
        private String instance;
        private final Map<String, Object> properties = new LinkedHashMap<>();

        private Builder(HttpStatus status) {
            this.status = status;
        }

        public Builder<T> title(String title) {
            this.title = title;
            return this;
        }

        public Builder<T> detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder<T> type(String type) {
            this.type = type;
            return this;
        }

        public Builder<T> instance(String instance) {
            this.instance = instance;
            return this;
        }


        public <V> Builder<T> property(String key, V value) {
            this.properties.put(key, value);
            return this;
        }

        public Builder<T> properties(Map<String, ?> props) {
            if (props != null) props.forEach(this::property);
            return this;
        }

        public Builder<T> timestampNow() {
            return property("timestamp", OffsetDateTime.now());
        }

        public Builder<T> path(String path) {
            return property("path", path);
        }

        public Builder<T> errorCode(String code) {
            return property("errorCode", code);
        }

        public ApiProblemResponse build() {
            return new ApiProblemResponse(this);
        }
    }
}
