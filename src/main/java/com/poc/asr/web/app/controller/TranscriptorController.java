package com.poc.asr.web.app.controller;

import com.poc.asr.web.app.service.Transcriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/transcript")
public class TranscriptorController {

  @Autowired
  private Transcriptor transcriptor;


  @PostMapping("/")
  public List<String> transcriptFile(@RequestParam("file") MultipartFile file) {
    return transcriptor.transalte(file);
  }
}
