package com.poc.asr.web.app.controller;

import com.poc.asr.web.app.service.Transcriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/transcript")
public class TranscriptorController {

  @Autowired
  private Transcriptor transcriptor;


  @PostMapping("/")
  public List<String> transcriptFile(@RequestParam("file") MultipartFile file) throws RuntimeException {
    return transcriptor.transalte(file);
  }


}
