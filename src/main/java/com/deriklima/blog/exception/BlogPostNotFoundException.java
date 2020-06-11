package com.deriklima.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Blog Post Not Found")
public class BlogPostNotFoundException extends RuntimeException {

  public BlogPostNotFoundException(String message) {
    super(message);
  }

}
