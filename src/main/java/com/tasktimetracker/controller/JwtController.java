package com.tasktimetracker.controller;

import com.tasktimetracker.dto.jwt.JwtDTO;
import com.tasktimetracker.entity.UserDetailsImpl;
import com.tasktimetracker.security.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/jwt")
@Tag(name = "JWT", description = "JWT операции")
public class JwtController {

    private final JwtService jwtService;


    @Autowired
    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Operation(
            summary = "Создает JWT"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT успешно создан",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtDTO.class))),
    })
    @PostMapping("/create")
    public ResponseEntity<JwtDTO> createJwtToken() {
        UserDetailsImpl userDetails = new UserDetailsImpl("test");
        JwtDTO jwtDto = new JwtDTO(UUID.randomUUID(), jwtService.generateToken(userDetails));
        return ResponseEntity.ok(jwtDto);
    }
}
