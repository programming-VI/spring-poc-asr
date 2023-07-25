package com.poc.asr.web.app.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Transcriptor {
  List<String> transalte(MultipartFile file);
}
