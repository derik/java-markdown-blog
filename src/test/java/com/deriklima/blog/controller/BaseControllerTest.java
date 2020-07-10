package com.deriklima.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SqlGroup({
    @Sql(
        executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:db/h2/schema.sql", "classpath:db/h2/data.sql"}),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/h2/drop.sql")
})
public abstract class BaseControllerTest {

  @Autowired
  protected MockMvc mockMvc;

}
