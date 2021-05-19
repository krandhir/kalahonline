package com.kgc.nl.kalah.model.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgc.nl.kalah.model.Game;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
class KalahExceptionHandlerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  void testGameNotFoundException() throws Exception {
    final MockHttpServletRequestBuilder playGameRequest = MockMvcRequestBuilders.put("/games/123/pits/7");
    this.mockMvc.perform(playGameRequest).andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().string("Could not find game 123")).andReturn();
  }

  @Test
  void testIllegalMoveException() throws Exception {
    final MockHttpServletRequestBuilder initGameRequest = MockMvcRequestBuilders.post("/games");
    final String responseString = this.mockMvc.perform(initGameRequest).andReturn().getResponse().getContentAsString();
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    final Game game = objectMapper.readValue(responseString, Game.class);

    final MockHttpServletRequestBuilder playGameRequest =
        MockMvcRequestBuilders.put("/games/" + game.getId() + "/pits/7");
    this.mockMvc.perform(playGameRequest).andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().string("Illegal move: Can not start from house")).andReturn();
  }
}
