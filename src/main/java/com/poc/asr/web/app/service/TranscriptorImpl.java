package com.poc.asr.web.app.service;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class TranscriptorImpl implements Transcriptor {
  @Override
  public List<String> transalte(MultipartFile file) {
    int rate;
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getInputStream());
      AudioFormat audioFormat = audioInputStream.getFormat();
      rate = (int) audioFormat.getFrameRate();
    } catch (UnsupportedAudioFileException | IOException e) {
      throw new RuntimeException(e);
    }

    List<String> response = new ArrayList<>();
    Configuration configuration = new Configuration();

    configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
    configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

    configuration.setSampleRate(rate);

    StreamSpeechRecognizer recognizer;
    InputStream stream = null;
    try {
      recognizer = new StreamSpeechRecognizer(configuration);
      stream = file.getInputStream();
      recognizer.startRecognition(stream);

      SpeechResult result;

      while ((result = recognizer.getResult()) != null) {
        System.out.format("Hypothesis: %s\n", result.getHypothesis());
        response.add(result.getHypothesis());
      }
      recognizer.stopRecognition();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return response;
  }
}
