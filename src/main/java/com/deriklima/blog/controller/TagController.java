package com.deriklima.blog.controller;

import com.deriklima.blog.dto.TagDTO;
import com.deriklima.blog.exception.ErrorResponse;
import com.deriklima.blog.service.TagService;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/tag")
@RequiredArgsConstructor
public class TagController {

  private final TagService tagService;

  @GetMapping
  public String viewTagList(Model model) {
    model.addAttribute("tags", tagService.findAll());
    model.addAttribute("newTag", new TagDTO());
    return "admin/tag/list-tag";
  }

  @PostMapping
  public ModelAndView saveTag(
      @Valid @RequestBody TagDTO tagDTO) {
    tagService.save(tagDTO);
    return new ModelAndView(
        "admin/tag/list-tag :: tags-list",
        "tags",
        tagService.findAll()
    );
  }

  @DeleteMapping("/{id}")
  public ModelAndView deleteTag(@PathVariable("id") Long id) {
    tagService.deleteById(id);
    return new ModelAndView(
        "admin/tag/list-tag :: tags-list",
        "tags",
        tagService.findAll()
    );
  }

  /*
  Usually this could be part of a global exception handler (@ControllerAdvice).
  However, this method handles cases very specific to tag management by now.
   */
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(
      HttpServletRequest request,
      DataIntegrityViolationException ex) {

    ErrorResponse errorResponse = ErrorResponse.builder()
        .status(HttpStatus.CONFLICT.value())
        .exceptionName(ex.getClass().getName())
        .message(ex.getMessage())
        .build();

    return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
  }

}
